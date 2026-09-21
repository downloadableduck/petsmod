package com.jeff.pets.client.buttons;

import com.jeff.pets.client.PetsConfigScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class ExitButton extends GuiButton {

    private final PetsConfigScreen screen;

    public ExitButton(PetsConfigScreen screen, int x, int y, int width, int height) {
        super(x, y, width, height, "<- Exit");
        this.screen = screen;
    }

    @Override
    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        super.func_146112_a(mc, mouseX, mouseY);
        ResourceLocation id = this.isMouseOver() ? new ResourceLocation(MOD_ID, "textures/gui/sprites/dark_button_highlighted.png") : new ResourceLocation(MOD_ID, "textures/gui/sprites/dark_button.png");
        this.blit(id, this.x, this.y, this.width, this.height, screen.button.color);
        drawString(screen.mc.fontRenderer, "<- Exit", this.x + 20, this.y + 6, this.screen.button.color);
    }

    @Override
    public void onPress() {
        this.screen.mc.displayGuiScreen(null);
    }
}
