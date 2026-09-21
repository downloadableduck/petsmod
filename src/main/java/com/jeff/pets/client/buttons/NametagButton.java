package com.jeff.pets.client.buttons;

import com.jeff.pets.client.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class NametagButton extends GuiTextField {

    public int color;
    public int width;
    FontRenderer font;
    int screenWidth;

    public NametagButton(int screenWidth, int y, FontRenderer font, int width, int height,
                         String narration
    ) {
        super(new Random().nextInt(), font, 0, y, width, height);
        this.setEnableBackgroundDrawing(false);
        this.screenWidth = screenWidth;
        this.font = font;
        this.width = width;
        this.setText(Utils.getActivePetName());
        this.setMaxStringLength(32);
        this.centerX();
    }

    private void centerX() {
        int width2 = this.font.getStringWidth(this.getText()) + 16;
        this.width = (width2);

        this.x = ((this.screenWidth - width2 * 2) / 2);
    }

    @Override
    public void func_146194_f() {
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.x, this.y, 0);
        GlStateManager.scalef(2, 2, 0);
        this.setCursorPosition(this.getCursorPosition() * 2);
        this.blit(new ResourceLocation("blocks/air.png"), this.x, this.y, this.getWidth(), this.height, 255/*, 20, 0*/);
        GlStateManager.translatef(-this.x, -this.y, 0);
        this.setTextColor(color);
        super.func_146194_f();
        GlStateManager.popMatrix();
    }

    @Override
    public void setText(String value) {
        super.setText(value);
        Utils.setActivePetName(value);
        this.centerX();
    }

    protected void blit(ResourceLocation resourceLocation, int x, int y, int width, int height, int color) {
        GlStateManager.enableBlend();
        //GlStateManager.color4f(255, 255, 255, color);
        Minecraft.getInstance().getTextureManager().bindTexture(resourceLocation);
        this.drawTexturedModalRect(x, y, width, height, 200, 20);
        GlStateManager.disableBlend();
    }
}