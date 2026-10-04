package com.jeff.pets.client.buttons;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.PetsConfigScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resource.Identifier;

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
    public void render(Minecraft mc, int mouseX, int mouseY) {
        super.render(mc, mouseX, mouseY);
        if (this.opened) {
            this.screen.getPetsMenu().extractRenderState(mouseX, mouseY, this.x, this.y - 15);
        }
        Identifier id = this.isHovered() ? new Identifier(MOD_ID, "textures/gui/sprites/dark_button_highlighted.png") : new Identifier(PetsInitializer.MOD_ID, "textures/gui/sprites/dark_button.png");
        this.blit(id, this.x, this.y, this.width, this.height, this.screen.button.color);
        this.drawString(mc.textRenderer, this.message, this.x + 9, this.y + 6, this.screen.button.color);
    }

    @Override
    public void onPress() {
        this.opened = !this.opened;
    }
}
