package com.jeff.pets.client.screen;

import com.jeff.pets.client.PetsConfigScreen;
import com.jeff.pets.client.Utils;
import com.jeff.pets.client.screen.buttons.DropDownButton;
import com.jeff.pets.client.screen.buttons.ExitButton;
import com.jeff.pets.client.screen.buttons.PanelButton;
import com.jeff.pets.client.screen.buttons.Type;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class KeyBindingsConfigScreen extends Screen {

    private final PetsConfigScreen screen;

    public KeyBindingsConfigScreen(PetsConfigScreen screen) {
        super(Component.empty());
        this.screen = screen;
    }

    @Override
    public void extractBackground(@NotNull GuiGraphicsExtractor extractor, int mouseX, int mouseY, float a) {
        super.extractBackground(extractor, mouseX, mouseY, a);
        Window window = Minecraft.getInstance().getWindow();
        extractor.pose().pushMatrix();
        extractor.blit(RenderPipelines.GUI_TEXTURED, Utils.withModNamespace("textures/gui/background.png"), 0, 0, 0, 0, window.getGuiScaledWidth(), window.getGuiScaledHeight(), 128, 128, 128, 128);
        extractor.pose().popMatrix();
    }

    @Override
    public void init() {
        this.addRenderableWidget(new PanelButton(this.width / 2 - 35, 0, 70, 20, Component.translatable("message.pets-mod.screen.general"), (_) -> Minecraft.getInstance().setScreen(this.screen), this.screen, () -> Minecraft.getInstance().screen instanceof PetsConfigScreen));
        this.addRenderableWidget(new PanelButton(this.width / 2 + 45, 0, 70, 20, Component.translatable("message.pets-mod.screen.other"), (_) -> Minecraft.getInstance().setScreen(new OtherConfigScreen(this.screen)), this.screen, () -> Minecraft.getInstance().screen instanceof OtherConfigScreen));
        this.addRenderableWidget(new PanelButton(this.width / 2 - 115, 0, 70, 20, Component.translatable("message.pets-mod.screen.keybinding"), (_) -> {
        }, this.screen, () -> Minecraft.getInstance().screen instanceof KeyBindingsConfigScreen));
        this.addRenderableWidget(new ExitButton(this.screen, 10, 10, 80, 20));
        this.addRenderableWidget(new DropDownButton(this.screen, this.width / 2 - 40, this.height / 2, 80, 20, Component.translatable("message.pets-mod.screen.interact"), Type.KEYBIND, "interact"));
        this.addRenderableWidget(new DropDownButton(this.screen, this.width / 2 - 130, this.height / 2, 80, 20, Component.translatable("message.pets-mod.screen.pick-up"), Type.KEYBIND, "pick up"));
        this.addRenderableWidget(new DropDownButton(this.screen, this.width / 2 + 50, this.height / 2, 80, 20, Component.translatable("message.pets-mod.screen.sit"), Type.KEYBIND, "sit"));
    }
}
