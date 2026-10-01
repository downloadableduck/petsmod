package com.jeff.pets.client.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiElement;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.resource.Identifier;

import java.util.Random;

public abstract class GuiButton extends ButtonWidget {
    protected String message;
    public GuiButton(int x, int y, int width, int height, String p_i1020_4_) {
        super(new Random().nextInt(), x, y, "");
        this.width = width;
        this.height = height;
        this.message = p_i1020_4_;
    }

    protected void blit(Identifier resourceLocation, int x, int y, int width, int height, int color) {
        GlStateManager.enableBlend();
        //GlStateManager.color4f(255, 255, 255, color);
        Minecraft.getInstance().getTextureManager().bind(resourceLocation);
        GuiElement.drawTexture(x, y, 0, 0, 200, 20, width, height, 200, 20);
        GlStateManager.disableBlend();
    }

    public abstract void onPress();
}
