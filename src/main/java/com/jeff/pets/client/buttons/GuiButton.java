package com.jeff.pets.client.buttons;

import me.shedaniel.forge.clothconfig2.gui.ClothConfigTabButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public abstract class GuiButton extends net.minecraft.client.gui.GuiButton {
    protected String message;
    public GuiButton(int x, int y, int width, int height, String p_i1020_4_) {
        super(new Random().nextInt(), x, y, "");
        this.width = width;
        this.height = height;
        this.message = p_i1020_4_;
    }

    protected void blit(ResourceLocation resourceLocation, int x, int y, int width, int height, int color) {
        GlStateManager.enableBlend();
        //GlStateManager.color4f(255, 255, 255, color);
        Minecraft.getInstance().getTextureManager().bindTexture(resourceLocation);
        Gui.drawScaledCustomSizeModalRect(x, y, 0, 0, 200, 20, width, height, 200, 20);
        GlStateManager.disableBlend();
    }

    public abstract void onPress();
}
