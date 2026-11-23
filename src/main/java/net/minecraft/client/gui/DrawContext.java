package net.minecraft.client.gui;

import me.shedaniel.math.Rectangle;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.texture.Texture;
import net.minecraft.client.render.TextureManager;

/**
 * Modern draw helpers used by Cloth Config, implemented with BTA's Gui.
 */
public class DrawContext extends Gui {
    public void fill(int x1, int y1, int x2, int y2, int color) {
        this.drawRect(x1, y1, x2, y2, color);
    }

    public void fillGradient(int x1, int y1, int x2, int y2, int startColor, int endColor) {
        this.drawGradientRect(x1, y1, x2, y2, startColor, endColor);
    }

    public void drawTexture(int x, int y, int u, int v, int width, int height) {
        this.drawTexturedModalRect(x, y, u, v, width, height);
    }

    public void drawTexture(int x, int y, float u, float v, int width, int height) {
        this.drawTexturedModalRect(x, y, (int)u, (int)v, width, height);
    }

    public void drawTextWithShadow(TextRenderer textRenderer, String text, int x, int y, int color) {
        textRenderer.drawWithShadow(text, x, y, color);
    }

    public void drawTextWithShadow(TextRenderer textRenderer, CharSequence text, int x, int y, int color) {
        textRenderer.drawWithShadow(text, x, y, color);
    }

    public void drawCenteredTextWithShadow(TextRenderer textRenderer, String text, int centerX, int y, int color) {
        textRenderer.drawCenteredWithShadow(text, centerX, y, color);
    }

    public void drawCenteredTextWithShadow(TextRenderer textRenderer, CharSequence text, int centerX, int y, int color) {
        textRenderer.drawCenteredWithShadow(text, centerX, y, color);
    }

    public void overlayBackground(Rectangle rect, int red, int green, int blue, int startAlpha, int endAlpha) {
        this.drawGradientRect(rect.x, rect.y, rect.getMaxX(), rect.getMaxY(),
                argb(red, green, blue, startAlpha), argb(red, green, blue, endAlpha));
    }

    public void bindTexture(TextureManager manager, String location) {
        Texture texture = manager.loadTexture(location);
        if (texture != null) {
            manager.bindTexture(texture);
        }
    }

    private static int argb(int red, int green, int blue, int alpha) {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}
