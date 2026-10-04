package com.jeff.pets.client.buttons;

import com.jeff.pets.client.PetsConfigScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resource.Identifier;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class ExitButton extends GuiButton {

    private final PetsConfigScreen screen;

    public ExitButton(PetsConfigScreen screen, int x, int y, int width, int height) {
        super(x, y, width, height, "<- Exit");
        this.screen = screen;
    }

    @Override
    public void render(Minecraft mc, int mouseX, int mouseY) {
        super.render(mc, mouseX, mouseY);
        Identifier id = this.isHovered() ? new Identifier(MOD_ID, "textures/gui/sprites/dark_button_highlighted.png") : new Identifier(MOD_ID, "textures/gui/sprites/dark_button.png");
        this.blit(id, this.x, this.y, this.width, this.height, screen.button.color);
        drawString(mc.textRenderer, "<- Exit", this.x + 20, this.y + 6, this.screen.button.color);
    }

    @Override
    public void onPress() {
        Minecraft.getInstance().openScreen(null);
    }
}
