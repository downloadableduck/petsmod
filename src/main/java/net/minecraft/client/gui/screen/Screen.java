package net.minecraft.client.gui.screen;

import me.shedaniel.clothconfig2.compat.GuiEventListener;
import me.shedaniel.clothconfig2.impl.WindowUtil;
import me.shedaniel.math.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Modern Cloth Config screen shape backed by BTA's {@link net.minecraft.client.gui.Screen}.
 *
 * <p>The restored Cloth sources were written against later Minecraft versions; this
 * adapter supplies the fields and drawing helpers they expect while delegating all
 * rendering and input to BTA.
 */
public abstract class Screen extends net.minecraft.client.gui.Screen {
    protected final Minecraft minecraft;
    protected final TextRenderer textRenderer;
    public final List<GuiEventListener> children = new ArrayList<>();
    private final DrawContext drawContext = new DrawContext();

    public Screen() {
        this((net.minecraft.client.gui.Screen)null);
    }

    public Screen(Screen parent) {
        this((net.minecraft.client.gui.Screen)parent);
    }

    public Screen(net.minecraft.client.gui.Screen parent) {
        super(parent);
        this.minecraft = Minecraft.getMinecraft();
        this.textRenderer = TextRenderer.of(this.minecraft.font);
    }

    /** Wraps a BTA screen for Cloth APIs that still expect the modern screen shape. */
    public static Screen wrap(net.minecraft.client.gui.Screen parent) {
        return new Screen(parent) {
        };
    }

    public void confirmed(boolean result, int id) {
    }

    public void renderBackground(int unused) {
        this.renderBackground();
    }

    public void fill(int x1, int y1, int x2, int y2, int color) {
        this.drawContext.fill(x1, y1, x2, y2, color);
    }

    public void fillGradient(int x1, int y1, int x2, int y2, int startColor, int endColor) {
        this.drawContext.fillGradient(x1, y1, x2, y2, startColor, endColor);
    }

    public void drawTexture(int x, int y, int u, int v, int width, int height) {
        this.drawContext.drawTexture(x, y, u, v, width, height);
    }

    public void drawTexture(int x, int y, float u, float v, int width, int height) {
        this.drawContext.drawTexture(x, y, u, v, width, height);
    }

    public void drawTextWithShadow(TextRenderer renderer, String text, int x, int y, int color) {
        this.drawContext.drawTextWithShadow(renderer, text, x, y, color);
    }

    public void drawTextWithShadow(TextRenderer renderer, CharSequence text, int x, int y, int color) {
        this.drawContext.drawTextWithShadow(renderer, text, x, y, color);
    }

    public void drawCenteredTextWithShadow(TextRenderer renderer, String text, int centerX, int y, int color) {
        this.drawContext.drawCenteredTextWithShadow(renderer, text, centerX, y, color);
    }

    public void drawCenteredTextWithShadow(TextRenderer renderer, CharSequence text, int centerX, int y, int color) {
        this.drawContext.drawCenteredTextWithShadow(renderer, text, centerX, y, color);
    }

    protected void overlayBackground(Rectangle rect, int red, int green, int blue, int startAlpha, int endAlpha) {
        this.drawContext.overlayBackground(rect, red, green, blue, startAlpha, endAlpha);
    }

    public boolean mouseScrolled(double amount) {
        return false;
    }

    public void keyPressed(char typedChar, int keyCode) {
    }

    /**
     * BTA calls this four-argument overload; restored Cloth screens override the
     * modern two-argument overload. Bridge them instead of dropping all input.
     */
    @Override
    public void keyPressed(char typedChar, int keyCode, int scanCode, int modifiers) {
        super.keyPressed(typedChar, keyCode, scanCode, modifiers);
        this.keyPressed(typedChar, keyCode);
    }

    public int getScaledWidth() {
        return WindowUtil.getScaledWidth();
    }

    public int getScaledHeight() {
        return WindowUtil.getScaledHeight();
    }
}
