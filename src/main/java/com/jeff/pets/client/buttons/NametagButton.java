package com.jeff.pets.client.buttons;

import com.jeff.pets.client.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.resource.Identifier;

import java.util.Random;

public class NametagButton extends TextFieldWidget {

    public int color;
    public int width;
    TextRenderer font;
    int screenWidth;

    public NametagButton(int screenWidth, int y, TextRenderer font, int width, int height,
                         String narration
    ) {
        super(new Random().nextInt(), font, 0, y, width, height);
        this.setHasBorder(false);
        this.screenWidth = screenWidth;
        this.font = font;
        this.width = width;
        this.setText(Utils.getActivePetName());
        this.setMaxLength(32);
        this.centerX();
    }

    private void centerX() {
        int width2 = this.font.getWidth(this.getText()) + 16;
        this.width = (width2);

        this.x = ((this.screenWidth - width2 * 2) / 2);
    }

    @Override
    public void render() {
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.x, this.y, 0);
        GlStateManager.scalef(2, 2, 0);
        this.setCursor(this.getCursor() * 2);
        this.blit(new Identifier("blocks/air.png"), this.x, this.y, this.getInnerWidth(), this.height, 255/*, 20, 0*/);
        GlStateManager.translatef(-this.x, -this.y, 0);
        this.setEditableColor(color);
        super.render();
        GlStateManager.popMatrix();
    }

    @Override
    public void setText(String value) {
        super.setText(value);
        Utils.setActivePetName(value);
        this.centerX();
    }

    protected void blit(Identifier resourceLocation, int x, int y, int width, int height, int color) {
        GlStateManager.enableBlend();
        //GlStateManager.color4f(255, 255, 255, color);
        Minecraft.getInstance().getTextureManager().bind(resourceLocation);
        this.drawTexture(x, y, width, height, 200, 20);
        GlStateManager.disableBlend();
    }
}