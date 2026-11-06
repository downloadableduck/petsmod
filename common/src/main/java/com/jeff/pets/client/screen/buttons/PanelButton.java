package com.jeff.pets.client.screen.buttons;

import com.jeff.pets.client.PetsConfigScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.Supplier;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class PanelButton extends Button {

    private final PetsConfigScreen screen;
    private final Supplier<Boolean> selected;
    private final int expandedHeight;
    private final int actualHeight;

    public PanelButton(int x, int y, int width, int height, Component message, OnPress onPress, PetsConfigScreen screen, Supplier<Boolean> selected) {
        super(x, y, width, height, message, onPress, Supplier::get);
        this.screen = screen;
        this.selected = selected;
        this.actualHeight = height;
        this.expandedHeight = this.height + 2;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        graphics.pose().pushMatrix();
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, this.getTexture(), this.getX(), this.getY(), this.width, this.height, this.screen.button.color);
        graphics.text(this.screen.getFont(), this.message, this.getX() + this.width / 2 - this.screen.getFont().width(this.message) / 2, this.getY() + this.screen.getFont().lineHeight / 2, this.screen.button.color);
        if (this.selected.get()) {
            this.height = this.expandedHeight;
        } else {
            this.height = this.actualHeight;
        }
        graphics.pose().popMatrix();
    }

    public Identifier getTexture() {
        if (this.isHovered()) {
            return Identifier.fromNamespaceAndPath(MOD_ID, "panel_button_highlighted");
        }
        return Identifier.fromNamespaceAndPath(MOD_ID, "panel_button");
    }
}
