package com.jeff.pets.client.buttons;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.PetsConfigScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class ChangePetButton extends GuiButton {

    private final PetsConfigScreen screen;
    public boolean opened = false;

    public ChangePetButton(PetsConfigScreen screen, int x, int y, int width, int height) {
        super(x, y, width, height, "Change . . . ");
        this.screen = screen;
        this.visible = true;
    }

    @Override
    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        super.func_146112_a(mc, mouseX, mouseY);
        if (this.opened) {
            this.screen.getPetsMenu().extractRenderState(mouseX, mouseY, this.x, this.y - 15);
        }
        ResourceLocation id = this.isMouseOver() ? new ResourceLocation(MOD_ID, "textures/gui/sprites/dark_button_highlighted.png") : new ResourceLocation(PetsInitializer.MOD_ID, "textures/gui/sprites/dark_button.png");
        this.blit(id, this.x, this.y, this.width, this.height, this.screen.button.color);
        this.drawString(mc.fontRenderer, this.message, this.x + 9, this.y + 6, this.screen.button.color);
    }

    @Override
    public void onPress() {
        this.opened = !this.opened;
    }
}
