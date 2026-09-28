package com.jeff.pets.client.screen.buttons;

import com.jeff.pets.client.PetsConfigScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.net.URI;
import java.util.function.Supplier;

import static com.jeff.pets.PetsInitializer.MOD_ID;
import static com.mojang.blaze3d.Blaze3D.openUri;
import static net.minecraft.util.Util.getPlatform;

public class BuyMeACoffeeButton extends Button {
    private final PetsConfigScreen screen;

    public BuyMeACoffeeButton(PetsConfigScreen screen, int x, int y, int width, int height) {
        super(x, y, width, height, Component.translatable("message.pets-mod.screen.donate"), (_ -> openUri(URI.create("https://buymeacoffee.com/downloadableduck"))), Supplier::get);
        this.screen = screen;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        Identifier id = this.isHovered() ? Identifier.fromNamespaceAndPath(MOD_ID, "dark_button_highlighted") : Identifier.fromNamespaceAndPath(MOD_ID, "dark_button");
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, id, this.getX(), this.getY(), this.width, this.height, screen.button.color);
        graphics.text(screen.getFont(), this.message, this.getX() + 20, this.getY() + 6, this.screen.button.color);
    }
}
