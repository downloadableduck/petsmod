package com.jeff.pets.client.screen;

import com.jeff.pets.client.PetsConfigScreen;
import com.jeff.pets.client.Utils;
import com.jeff.pets.client.screen.buttons.BasicButton;
import com.jeff.pets.client.screen.buttons.ExitButton;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;

public class TipsScreen extends Screen {

    private final PetsConfigScreen screen;

    public TipsScreen(PetsConfigScreen screen) {
        super(Component.empty());
        this.screen = screen;
    }

    @Override
    public void extractBackground(@NotNull GuiGraphicsExtractor extractor, int mouseX, int mouseY, float a) {
        extractor.pose().pushMatrix();
        super.extractBackground(extractor, mouseX, mouseY, a);
        Window window = Minecraft.getInstance().getWindow();
        extractor.blit(RenderPipelines.GUI_TEXTURED, Utils.withModNamespace("textures/gui/background.png"), 0, 0, 0, 0, window.getGuiScaledWidth(), window.getGuiScaledHeight(), 128, 128, 128, 128);
        extractor.pose().popMatrix();
    }

    @Override
    public void init() {
        this.addRenderableWidget(new ExitButton(this.screen, 10, 10, 80, 20, (_) -> Minecraft.getInstance().setScreen(new OtherConfigScreen(this.screen))));
        Component component = Component.translatable("message.pets-mod.tips-text");
        this.addRenderableWidget(new BasicButton(this.screen, 40, 40, this.width - 40 * 2, this.height - 40 * 2, component, (_) -> Util.getPlatform().openUri("https://www.youtube.com/watch?v=dQw4w9WgXcQ")));
    }
}
