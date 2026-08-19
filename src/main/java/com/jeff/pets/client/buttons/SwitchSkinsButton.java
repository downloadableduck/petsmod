package com.jeff.pets.client.buttons;

import com.jeff.pets.client.NewPetsConfigScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.Supplier;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class SwitchSkinsButton extends Button {

    private final NewPetsConfigScreen screen;
    public boolean opened = false;
    public SwitchSkinsButton(NewPetsConfigScreen screen, int x, int y, int width, int height) {
        super(x, y, width, height, Component.literal("⇆ Switch Skin"), (button) -> {}, Supplier::get);
        this.screen = screen;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        if (this.opened) {
            this.screen.getDropDownMenu().extractRenderState(graphics, this.getX(), this.getY() - 15);
        }
        Identifier id = this.isHovered() ? Identifier.fromNamespaceAndPath(MOD_ID, "dark_button_highlighted") : Identifier.fromNamespaceAndPath(MOD_ID, "dark_button");
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, id, this.getX(), this.getY(), this.width, this.height, screen.button.color);
        graphics.text(screen.getFont(), this.message, this.getX() + 9, this.getY() + 6, this.screen.button.color);
    }

    @Override
    public void onPress(InputWithModifiers input) {
        this.opened = !this.opened;
    }
}
