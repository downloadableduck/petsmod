package com.jeff.pets.client.autoupdate.impl;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

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
        if (this.messageLines.isEmpty()) {
            this.extractTextLines(graphics, font, this.titleLines, 12, -256);
        } else {
            this.extractTextLines(graphics, font, this.titleLines, 7, -256);
            this.extractTextLines(graphics, font, this.messageLines, 7 + this.titleLines.size() * 12, -1);
        }
    }
}
