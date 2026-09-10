package com.jeff.pets.client.buttons;

import com.jeff.pets.client.PetsConfigScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.Supplier;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class ExitButton extends Button {

    private final PetsConfigScreen screen;

    public ExitButton(PetsConfigScreen screen, int x, int y, int width, int height) {
        super(x, y, width, height, Component.literal("<- Exit"), (_ -> screen.onClose()), Supplier::get);
        this.screen = screen;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        Identifier id = this.isHovered() ? Identifier.fromNamespaceAndPath(MOD_ID, "dark_button_highlighted") : Identifier.fromNamespaceAndPath(MOD_ID, "dark_button");
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, id, this.getX(), this.getY(), this.width, this.height, screen.button.color);
        graphics.text(screen.getFont(), "<- Exit", this.getX() + 20, this.getY() + 6, this.screen.button.color);
    }
}
