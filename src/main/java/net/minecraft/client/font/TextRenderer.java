package net.minecraft.client.font;

import net.minecraft.client.render.font.FontRenderer;

/**
 * Modern text-renderer shape backed by BTA's {@link FontRenderer}.
 */
public final class TextRenderer {
    private final FontRenderer delegate;

    private TextRenderer(FontRenderer delegate) {
        this.delegate = delegate;
    }

    public static TextRenderer of(FontRenderer delegate) {
        return new TextRenderer(delegate);
    }

    public FontRenderer delegate() {
        return this.delegate;
    }

    public int getWidth(CharSequence text) {
        return this.delegate.stringWidth(text);
    }

    public int getWidth(String text) {
        return this.delegate.stringWidth((CharSequence)text);
    }

    public int getStringWidth(CharSequence text) {
        return this.getWidth(text);
    }

    public int getHeight() {
        return 9;
    }

    public void draw(CharSequence text, int x, int y, int color) {
        this.delegate.render(text, x, y).setColor(color).call();
    }

    public void draw(String text, int x, int y, int color) {
        this.draw((CharSequence)text, x, y, color);
    }

    public void drawWithShadow(CharSequence text, int x, int y, int color) {
        this.delegate.render(text, x, y).setColor(color).setShadow().call();
    }

    public void drawWithShadow(String text, int x, int y, int color) {
        this.drawWithShadow((CharSequence)text, x, y, color);
    }

    public void drawCenteredWithShadow(CharSequence text, int centerX, int y, int color) {
        this.drawWithShadow(text, centerX - this.getWidth(text) / 2, y, color);
    }

    public void drawCenteredWithShadow(String text, int centerX, int y, int color) {
        this.drawCenteredWithShadow((CharSequence)text, centerX, y, color);
    }
}
