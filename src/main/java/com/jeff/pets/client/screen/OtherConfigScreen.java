package com.jeff.pets.client.screen;

import com.jeff.pets.client.PetsConfigScreen;
import com.jeff.pets.client.Utils;
import com.jeff.pets.client.screen.buttons.*;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class OtherConfigScreen extends Screen {

    private final PetsConfigScreen screen;

    public OtherConfigScreen(PetsConfigScreen screen) {
        super(Component.empty());
        this.screen = screen;
    }

    @Override
    public void extractBackground(@NotNull GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float a) {
        Window window = Minecraft.getInstance().getWindow();
        graphics.pose().pushMatrix();
        graphics.fill(0, 0, this.width, this.height, 0xFF000000);
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, Utils.withModNamespace("textures/gui/background.png"), 0, 0, 0, 0, window.getGuiScaledWidth(), window.getGuiScaledHeight(), 128, 128, 128, 128);
        graphics.pose().popMatrix();
    }

    @Override
    public void init() {
        this.addRenderableWidget(new ExitButton(this.screen, 10, 10, 80, 20));
        this.addRenderableWidget(new BuyMeACoffeeButton(this.screen, this.getRectangle().right() - 90, this.getRectangle().bottom() - 25, 80, 20));
        this.addRenderableWidget(new PanelButton(this.width / 2 - 35, 0, 70, 20, Component.translatable("message.pets-mod.screen.general"), (_) -> Minecraft.getInstance().gui.setScreen(this.screen), this.screen, () -> Minecraft.getInstance().gui.screen() instanceof PetsConfigScreen));
        this.addRenderableWidget(new PanelButton(this.width / 2 + 45, 0, 70, 20, Component.translatable("message.pets-mod.screen.other"), (_) -> {
        }, this.screen, () -> Minecraft.getInstance().gui.screen() instanceof OtherConfigScreen));
        this.addRenderableWidget(new PanelButton(this.width / 2 - 115, 0, 70, 20, Component.translatable("message.pets-mod.screen.keybinding"), (_) -> Minecraft.getInstance().gui.setScreen(new KeyBindingsConfigScreen(this.screen)), this.screen, () -> false));
        this.addRenderableWidget(new ExitButton(this.screen, 10, 10, 80, 20));
        this.addRenderableWidget(new ToggleButton(this.getRectangle().left() + 42, this.height / 2 + 40, Component.translatable("option.pets-mod.always-show-nametag"), (_) -> CONFIG.alwaysRenderNametag = !CONFIG.alwaysRenderNametag, () -> CONFIG.alwaysRenderNametag, this.screen));
        this.addRenderableWidget(new ToggleButton(this.getRectangle().left() + 42, this.height / 2 - 40, Component.translatable("option.pets-mod.show-pet-hitboxes"), (_) -> CONFIG.renderPetHitbox = !CONFIG.renderPetHitbox, () -> CONFIG.renderPetHitbox, this.screen));
        this.addRenderableWidget(new ToggleButton(this.getRectangle().left() + 130, this.height / 2 + 40, Component.translatable("option.pets-mod.pet-wandering"), (_) -> CONFIG.wanderingEnabled = !CONFIG.wanderingEnabled, () -> CONFIG.wanderingEnabled, this.screen));
        this.addRenderableWidget(new ToggleButton(this.getRectangle().left() + 130, this.height / 2 - 40, Component.translatable("option.pets-mod.ghost-hand-pets"), (_) -> CONFIG.hitThroughPets = !CONFIG.hitThroughPets, () -> CONFIG.hitThroughPets, this.screen));
        this.addRenderableWidget(new BasicButton(this.screen, this.getRectangle().right() - 90, this.getRectangle().bottom() - 55, 80, 20, Component.translatable("link.pets-mod.report-issues"), (_) -> Util.getPlatform().openUri("https://github.com/downloadableduck/petsmod/issues")));
        this.addRenderableWidget(new BasicButton(this.screen, this.getRectangle().right() - 90, this.getRectangle().bottom() - 85, 80, 20, Component.translatable("link.pets-mod.browse-addons"), (_) -> Util.getPlatform().openUri("https://modrinth.com/organization/pets")));
        this.addRenderableWidget(new BasicButton(this.screen, this.getRectangle().right() - 90, this.getRectangle().bottom() - 115, 80, 20, Component.translatable("message.pets-mod.screen.tips"), (_) -> Minecraft.getInstance().gui.setScreen(new TipsScreen(this.screen))));
    }
}
