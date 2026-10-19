package com.jeff.pets.client.screen.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class SearchBox extends EditBox {

    public static String search = "\uD83D\uDD0E Search ...";

    public int color;

    public SearchBox(Font font, Component narration, int color) {
        super(font, narration);
        this.setSize(80, font.lineHeight + 4);
        this.setEditable(true);
        this.setTextColor(color);
        this.color = color;
    }

    @Override
    public void setValue(@NotNull String value) {
        if (value.equals(search)) super.setValue(value);
        else super.setValue(value.replace(search, ""));
    }

    @Override
    public void insertText(@NotNull String text) {
        super.insertText(text);
        this.setValue(this.getValue() + text);
    }

    @Override
    public void extractWidgetRenderState(@NotNull GuiGraphicsExtractor graphics) {
        super.extractWidgetRenderState(graphics);
        if (this.getValue().isEmpty()) {
            graphics.text(Minecraft.getInstance().font, search, this.textX, this.textY, -9408400);
        }
    }
}
