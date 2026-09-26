package com.jeff.pets.client.screen.buttons;

import com.jeff.pets.client.PetsConfigScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.Arrays;
import java.util.function.Supplier;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class BasicButton extends Button {
    private final PetsConfigScreen screen;

    public BasicButton(PetsConfigScreen screen, int x, int y, int width, int height, Component component, Button.OnPress onPress) {
        super(x, y, width, height, component, onPress, Supplier::get);
        this.screen = screen;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        Identifier id = this.isHovered() ? Identifier.fromNamespaceAndPath(MOD_ID, "dark_button_highlighted") : Identifier.fromNamespaceAndPath(MOD_ID, "dark_button");
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, id, this.getX(), this.getY(), this.width, this.height, screen.button.color);
        String[] strings = this.message.getString().split("\n");
        int y = this.getY() + 6;
        for (String string : strings) {
            if (!(Arrays.stream(strings).toList().indexOf(string) == 0)) {
                y += this.screen.getFont().lineHeight;
            }
            graphics.text(this.screen.getFont(), string, this.getX() + this.width / 2 - this.screen.getFont().width(string) / 2, y, this.screen.button.color);
        }
    }
}
