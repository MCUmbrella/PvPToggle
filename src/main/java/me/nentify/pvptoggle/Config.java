package me.nentify.pvptoggle;

import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    private ConfigurationLoader<CommentedConfigurationNode> loader;
    private CommentedConfigurationNode config;

    public boolean defaultPvp;
    public int cooldown;

    public Config(Path configPath) throws IOException {
        loader = HoconConfigurationLoader.builder().setPath(configPath).build();

        if (!Files.exists(configPath)) {
            Files.createFile(configPath);
        }

        config = loader.load();

        check("default-pvp", true, "true = PvP默认开启, false = PvP默认关闭");
        check("cooldown", 10, "执行PVP控制命令的冷却时间");

        loader.save(config);

        defaultPvp = config.getNode("default-pvp").getBoolean();
        cooldown = config.getNode("cooldown").getInt();
    }

    public void check(String node, Object defaultValue, String comment) {
        if (config.getNode(node).isVirtual())
            config.getNode(node).setValue(defaultValue).setComment(comment);
    }
}
