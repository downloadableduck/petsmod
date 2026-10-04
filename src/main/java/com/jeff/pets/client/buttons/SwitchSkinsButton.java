package com.jeff.pets.client.buttons;

import com.jeff.pets.client.PetsConfigScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resource.Identifier;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class SwitchSkinsButton extends GuiButton {

    private final PetsConfigScreen screen;
    public boolean opened = false;

    public SwitchSkinsButton(PetsConfigScreen screen, int x, int y, int width, int height) {
        super(x, y, width, height, "⇆ Switch Skin");
        this.screen = screen;
    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY) {
        super.render(mc, mouseX, mouseY);
        if (this.opened) {
            this.screen.getDropDownMenu().extractRenderState(mouseX, mouseY, this.x, this.y - 15);
        }
        Identifier id = this.isHovered() ? new Identifier(MOD_ID, "textures/gui/sprites/dark_button_highlighted.png") : new Identifier(MOD_ID, "textures/gui/sprites/dark_button.png");
        this.blit(id, this.x, this.y, this.width, this.height, screen.button.color);
        this.drawString(Minecraft.getInstance().textRenderer, this.message, this.x + 9, this.y + 6, this.screen.button.color);
    }

    @Override
    public void onPress() {
        this.opened = !this.opened;
    }
}
