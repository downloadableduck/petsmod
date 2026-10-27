package com.jeff.pets.client.autoupdate.impl;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class PetsModToast extends SystemToast {
    public PetsModToast(SystemToastId id, Component title, @NotNull Component message) {
        title = Component.literal("       ").append(title);
        message = Component.literal("       ").append(message);
        super(id, title, message);
    }

    @Override
    public void extractRenderState(@NotNull GuiGraphicsExtractor graphics, @NotNull Font font, long fullyVisibleForMs) {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, Identifier.fromNamespaceAndPath(MOD_ID, "toast/pets-mod-toast"), 0, 0, this.width(), this.height());
        if (this.getMessageLines().isEmpty()) {
            this.extractTextLines(graphics, font, this.getTitleLines(), 12, -256);
        } else {
            this.extractTextLines(graphics, font, this.getTitleLines(), 7, -256);
            this.extractTextLines(graphics, font, this.getMessageLines(), 7 + this.getTitleLines().size() * 12, -1);
        }
    }

    public List<FormattedCharSequence> getMessageLines() {
        try {
            Field messageLines = SystemToast.class.getDeclaredField("messageLines");
            messageLines.setAccessible(true);
            return (List<FormattedCharSequence>) messageLines.get(this);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FormattedCharSequence> getTitleLines() {
        try {
            Field messageLines = SystemToast.class.getDeclaredField("titleLines");
            messageLines.setAccessible(true);
            return (List<FormattedCharSequence>) messageLines.get(this);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public void extractTextLines(GuiGraphicsExtractor extractor, Font font, List<FormattedCharSequence> lines, int yStart, int color) {
        try {
            Method method = SystemToast.class.getDeclaredMethod("extractTextLines", GuiGraphicsExtractor.class, Font.class, List.class, int.class, int.class);
            method.setAccessible(true);
            method.invoke(this, extractor, font, lines, yStart, color);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}
