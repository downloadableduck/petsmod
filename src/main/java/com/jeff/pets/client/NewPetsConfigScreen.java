package com.jeff.pets.client;

import com.jeff.pets.Utils;
import com.jeff.pets.client.buttons.DropdownMenu;
import com.jeff.pets.client.buttons.ExitButton;
import com.jeff.pets.client.buttons.NametagButton;
import com.jeff.pets.client.buttons.SwitchSkinsButton;
import com.jeff.pets.client.enums.EnumImpl;
import com.jeff.pets.mob.AbstractPet;
import com.jeff.pets.mob.SlimeLikePet;
import com.jeff.pets.rendering.custom.first.duck.DuckRenderState;
import com.mojang.blaze3d.opengl.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.impl.util.StringUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.entity.state.AxolotlRenderState;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FontDescription;
import net.minecraft.resources.Identifier;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import oshi.software.os.mac.MacInstalledApps;
import com.jeff.pets.enums.*;

import static com.jeff.pets.Central.*;

public class NewPetsConfigScreen extends Screen {

    public float ticks;
    public NametagButton button;
    public boolean closing = false;

    public NewPetsConfigScreen() {
        super(Component.empty());
        ticks = 0;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mousex, int mousey, float a) {
        float duration = (float) 4 /20;
        if (!this.closing) {
            ticks = Math.min(1.0f, ticks + (a * duration));
        } else {
            ticks = Math.min(1.0f, ticks + (a - duration));
        }

        if (this.closing && this.ticks <= 0) {
            super.onClose();
        }
        int alpha = (int) (ticks * 255);

        int color = (alpha << 24) | 0xFFFFFF;
        if (this.button != null) {
            this.button.color = color;
        }
        super.extractBackground(graphics, mousex, mousey, a);
        Window window = Minecraft.getInstance().getWindow();
        int xo = this.width / 2;
        int yo = this.height / 2;
        AbstractPet pet = Utils.getPet(CONFIG.activePet);
        int size = (int) (75 / Math.max(0.01, pet.getBoundingBox().getSize()));
        int boxsize = 250;
        int x = ((this.width - font.width(CONFIG.activePet)) - (this.width / 2));
        int y = this.height - this.font.lineHeight - 15;
        graphics.blit(RenderPipelines.GUI_TEXTURED, Utils.withModNamespace("textures/gui/background.png"), 0, 0, 0, 0, window.getGuiScaledWidth(), window.getGuiScaledHeight(), 128, 128, 128, 128, color);
        graphics.text(this.font, Component.literal(StringUtil.capitalize("Your currently selected pet is: ")), (this.width - font.width("Your currently selected pet is: ")) / 2, this.height - this.font.lineHeight - 35, color);
        graphics.pose().pushMatrix();
        graphics.pose().translate(x, y);
        graphics.pose().scale(2);
        graphics.text(this.font, Component.literal(StringUtil.capitalize(CONFIG.activePet)), 0, 0, color);
        graphics.pose().popMatrix();
        InventoryScreen.extractEntityInInventoryFollowsMouse(graphics, xo -boxsize, yo - boxsize, xo + boxsize, yo + boxsize, size, 0.0625F, 30, 120, Utils.getPet(CONFIG.activePet));
    }

    @Override
    public void init() {
        this.button = this.addRenderableWidget(new NametagButton(this.width, this.height - 212, this.minecraft.font, this.minecraft.font.width("1") * 16, this.minecraft.font.lineHeight, Component.literal(Utils.getActivePetName())));
        this.addRenderableWidget(new ExitButton(this, 10, 10, 80, 20));
        this.addRenderableWidget(new SwitchSkinsButton(this, this.width / 2, this.height / 2, 80, 20));
    }

    @Override
    public void onClose() {
        if (!this.closing) {
            this.closing = true;
            return;
        }
        if (ticks > 0) return;
        super.onClose();
    }

    public DropdownMenu getDropDownMenu() {
        EnumImpl enu = switch (CONFIG.activePet) {
            case "duck" ->
                    DuckSkins.valueOf(CONFIG.duckSkin.replaceAll(" ", "_"));
            case "cat" ->
                    com.jeff.pets.enums.CatSkins.valueOf(CONFIG.catSkin.replaceAll(" ", "_"));
            case "racoon" ->
                    RacoonSkins.valueOf(CONFIG.racoonSkin.replaceAll(" ", "_"));
            case "sheep" ->
                    SheepSkins.valueOf(CONFIG.sheepSkin.replaceAll(" ", "_"));
            case "axolotl" ->
                    AxolotlSkins.valueOf(CONFIG.axolotlSkin.replaceAll(" ", "_"));
            case "camel" ->
                    CamelSkins.valueOf(CONFIG.camelSkin.replaceAll(" ", "_"));
            case "chicken" ->
                    ChickenSkins.valueOf(CONFIG.chickenSkin.replaceAll(" ", "_"));
            case "creeper", "nerd_creeper", "smiling_creeper" ->
                    CreeperSkins.valueOf(CONFIG.creeperSkin.replaceAll(" ", "_"));
            case "copper_golem" ->
                    CopperGolemSkins.valueOf(CONFIG.copperGolemSkin.replaceAll(" ", "_"));
            case "cow" ->
                    CowSkins.valueOf(CONFIG.cowSkin.replaceAll(" ", "_"));
            case "frog" ->
                    FrogSkins.valueOf(CONFIG.frogSkin.replaceAll(" ", "_"));
            case "horse" ->
                    HorseSkins.valueOf(CONFIG.horseSkin.replaceAll(" ", "_"));
            case "parrot" ->
                    ParrotSkins.valueOf(CONFIG.parrotSkin.replaceAll(" ", "_"));
            case "pig" ->
                    PigSkins.valueOf(CONFIG.pigSkin.replaceAll(" ", "_"));
            case "rabbit" ->
                    RabbitSkins.valueOf(CONFIG.rabbitSkin.replaceAll(" ", "_"));
            case "snow_golem" ->
                    SnowGolemSkins.valueOf(CONFIG.snowGolemSkin.replaceAll(" ", "_"));
            case "squid" ->
                    SquidSkins.valueOf(CONFIG.squidSkin.replaceAll(" ", "_"));
            case "strider" ->
                    StriderSkins.valueOf(CONFIG.striderSkin.replaceAll(" ", "_"));
            case "villager" ->
                    VillagerSkins.valueOf(CONFIG.villagerSkin.replaceAll(" ", "_"));
            case "mooshroom" ->
                    MooshroomSkins.valueOf(CONFIG.mooshroomSkin.replaceAll(" ", "_"));
            case "bee" ->
                    BeeSkins.valueOf(CONFIG.beeSkin.replaceAll(" ", "_"));
            case "fox" ->
                    FoxSkins.valueOf(CONFIG.foxSkin.replaceAll(" ", "_"));
            case "llama" ->
                    LlamaSkins.valueOf(CONFIG.llamaSkin.replaceAll(" ", "_"));
            case "nautilus" ->
                    NautilusSkins.valueOf(CONFIG.nautilusSkin.replaceAll(" ", "_"));
            case "panda" ->
                    PandaSkins.valueOf(CONFIG.pandaSkin.replaceAll(" ", "_"));
            case "piglin" ->
                    PiglinSkins.valueOf(CONFIG.piglinSkin.replaceAll(" ", "_"));
            case "wolf" ->
                    WolfSkins.valueOf(CONFIG.wolfSkin.replaceAll(" ", "_"));
            case "hoglin" ->
                    HoglinSkins.valueOf(CONFIG.hoglinSkin.replaceAll(" ", "_"));
            case "magma_cube" ->
                    SlimeLikeSkins.valueOf(CONFIG.magmaCubeSkin.replaceAll(" ", "_"));
            case "slime", "tropical_slime" ->
                    SlimeLikeSkins.valueOf(CONFIG.slimeSkin.replaceAll(" ", "_"));
            case "zombie_villager" ->
                    ZombieVillagerSkins.valueOf(CONFIG.zombieVillagerSkin.replaceAll(" ", "_"));
            case "wither" ->
                    WitherSkins.valueOf(CONFIG.witherSkin.replaceAll(" ", "_"));
            case "dumbo_octopus" ->
                    DumboOctopusSkins.valueOf(CONFIG.dumboOctopusSkin.replaceAll(" ", "_"));
            case null, default ->
                    BlankEnum.no_skins_are_available;
        };
        return new DropdownMenu(this, enu);
    }
}
