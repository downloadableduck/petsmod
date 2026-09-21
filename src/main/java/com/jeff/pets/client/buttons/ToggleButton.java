package com.jeff.pets.client.buttons;

import com.jeff.pets.client.PetsConfigScreen;
import com.jeff.pets.client.Utils;
import com.jeff.pets.client.network.NetworkManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import java.util.function.Supplier;

import static com.jeff.pets.client.Central.CONFIG;

public class ToggleButton extends GuiButton {

    private final FontRenderer font;
    private Supplier<Boolean> toggle;
    private final PetsConfigScreen screen;
    private final int offset;
    private final Runnable runnable;

    public ToggleButton(int x, int y, String message, Runnable runnable, Supplier<Boolean> bool, PetsConfigScreen screen, int offset) {
        super(x, y, 32, 16, message);
        this.toggle = bool;
        this.screen = screen;
        this.font = screen.mc.fontRenderer;
        this.offset = offset;
        this.runnable = runnable;
    }

    public ToggleButton(int x, int y, String message, Runnable onPress, Supplier<Boolean> bool, PetsConfigScreen screen) {
        this(x, y, message, onPress, bool, screen, 0);
    }

    @Override
    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        super.func_146112_a(mc, mouseX, mouseY);
        this.blit(this.getTexture(), this.x, this.y, this.width, this.height, this.screen.button.color);
        this.drawString(this.font, this.message, this.x - offset, this.y - (this.font.FONT_HEIGHT - this.width), this.screen.button.color);
    }

    public ResourceLocation getTexture() {
        if (this.toggle() && !this.isMouseOver()) {
            return Utils.withModNamespace("textures/gui/sprites/toggle_on.png");
        } else if (this.toggle() && this.isMouseOver()) {
            return Utils.withModNamespace("textures/gui/sprites/toggle_on_highlighted.png");
        } else if (!this.toggle() && !this.isMouseOver()) {
            return Utils.withModNamespace("textures/gui/sprites/toggle_off.png");
        } else if (!this.toggle() && this.isMouseOver()) {
            return Utils.withModNamespace("textures/gui/sprites/toggle_off_highlighted.png");
        }
        return Utils.withModNamespace("textures/gui/sprites/toggle_on.png");
    }

    @Override
    public void onPress() {
        this.runnable.run();
        if (Minecraft.getInstance().player != null) {
            NetworkManager.get().broadcastGeneral(Minecraft.getInstance().player.getGameProfile().getId().toString(), CONFIG.petOn, CONFIG.activePet, Utils.getActivePetName(), Utils.getActivePetSkin(), CONFIG.isBaby);
        }
    }

    private boolean toggle() {
        return this.toggle.get();
    }
}
