package com.jeff.pets.client.buttons;

import com.jeff.pets.client.PetsConfigScreen;
import com.jeff.pets.client.Utils;
import com.jeff.pets.client.network.NetworkManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.Supplier;

import static com.jeff.pets.client.Central.CONFIG;

public class ToggleButton extends Button {

    private final Font font;
    private boolean toggle;
    private final PetsConfigScreen screen;

    public ToggleButton(int x, int y, Component message, OnPress onPress, boolean bool, PetsConfigScreen screen) {
        super(x, y, 32, 16, message, onPress, Supplier::get);
        this.toggle = bool;
        this.screen = screen;
        this.font = screen.getFont();
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, this.getTexture(), this.getX(), this.getY(), this.width, this.height, this.screen.button.color);
        graphics.text(this.font, this.message, this.getX(), this.getY() - (this.font.lineHeight - this.width), this.screen.button.color);
    }

    public Identifier getTexture() {
        if (this.toggle && !this.isHovered()) {
            return Utils.withModNamespace("toggle_on");
        } else if (this.toggle && this.isHovered()) {
            return Utils.withModNamespace("toggle_on_highlighted");
        } else if (!this.toggle && !this.isHovered()) {
            return Utils.withModNamespace("toggle_off");
        } else if (!this.toggle && this.isHovered()) {
            return Utils.withModNamespace("toggle_off_highlighted");
        }
        return Utils.withModNamespace("toggle_on");
    }

    @Override
    public void onPress(InputWithModifiers input) {
        super.onPress(input);
        this.toggle = !this.toggle;
        if (Minecraft.getInstance().player != null) {
            NetworkManager.get().broadcastGeneral(Minecraft.getInstance().player.getStringUUID(), this.toggle, CONFIG.activePet, Utils.getActivePetName(), Utils.getActivePetSkin(), CONFIG.isBaby);
        }
    }
}
