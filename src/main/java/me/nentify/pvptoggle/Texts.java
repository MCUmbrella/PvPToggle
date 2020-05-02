package me.nentify.pvptoggle;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;

public class Texts {
    public static Text toggleText = Text.builder("[PVP控制]")
            .color(TextColors.YELLOW)
            .onHover(TextActions.showText(Text.of("控制自身的PVP状态")))
            .onClick(TextActions.runCommand("/pvp"))
            .build();
}
