package com.jeff.pets.client.buttons;

import com.jeff.pets.client.Utils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class NametagButton extends EditBox {

    public int color;
    public int width;
    Font font;
    int screenWidth;

    public NametagButton(int screenWidth, int y, Font font, int width, int height,
                         Component narration
    ) {
        super(font, 0, y, width, height, narration);
        this.setBordered(false);
        this.screenWidth = screenWidth;
        this.font = font;
        this.width = width;
        this.insertText(Utils.getActivePetName());
        this.setMaxLength(32);
        this.centerX();
    }

    private void centerX() {
        int width2 = this.font.width(this.getValue()) + 16;
        this.setWidth(width2);

        this.setX((this.screenWidth - width2 * 2) / 2);
    }

    @Override
    public void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mousex, int mousey, float a) {
        graphics.pose().pushMatrix();
        graphics.pose().translate(this.getX(), this.getY());
        graphics.pose().scale(2, 2);
        this.setCursorPosition(this.getCursorPosition() * 2);
        graphics.blit(RenderPipelines.GUI_TEXTURED, Identifier.withDefaultNamespace("blocks/air.png"), this.getX(), this.getY(), 0, 0, this.getWidth(), this.getHeight(), 200, 20, 0);
        graphics.pose().translate(-this.getX(), -this.getY());
        this.setTextColor(color);
        super.extractWidgetRenderState(graphics, mousex, mousey, a);
        graphics.pose().popMatrix();
    }

    public void onValueChange(String value) {
        //super.onValueChange(value);
        Utils.setActivePetName(value);
        this.centerX();
    }
}