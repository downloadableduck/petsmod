package com.jeff.pets.client;

import com.jeff.pets.client.buttons.*;
import com.jeff.pets.client.enums.EnumImpl;
import com.jeff.pets.client.enums.*;
import com.jeff.pets.client.network.NetworkManager;
import com.jeff.pets.mob.AbstractPet;
import com.mojang.blaze3d.platform.Window;
import net.fabricmc.loader.impl.util.StringUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;

import static com.jeff.pets.client.Central.CONFIG;

public class PetsConfigScreen extends Screen {

    public float ticks;
    public NametagButton button;
    public boolean closing = false;
    public AbstractPet entity;
    public DropdownMenu dropdownMenu;
    public String entityName;
    private final int size;

    public PetsConfigScreen() {
        super(Component.empty());
        ticks = 0;
        if (Minecraft.getInstance().level != null) {
            entity = Utils.getPet(CONFIG.activePet);
            entity.setOwner(Minecraft.getInstance().player);
        }
        size = Minecraft.getInstance().options.guiScale().get();
        this.entityName = Utils.getActivePetName();
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mousex, int mousey, float a) {
        float duration = (float) 4 / 20;
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
        int size = 0;
        if (this.entity != null) {
            size = (int) (75 / Math.max(0.01, this.entity.getBoundingBox().getSize()));
        }
        int boxsize = 250;
        int x = ((this.width - font.width(CONFIG.activePet)) - (this.width / 2));
        int y = this.height - this.font.lineHeight - 15;
        graphics.blit(RenderPipelines.GUI_TEXTURED, Utils.withModNamespace("textures/gui/background.png"), 0, 0, 0, 0, window.getGuiScaledWidth(), window.getGuiScaledHeight(), 128, 128, 128, 128, color);
        graphics.text(this.font, Component.literal(StringUtil.capitalize("Your currently selected pet is: ")), (this.width - font.width("Your currently selected pet is: ")) / 2, this.height - this.font.lineHeight - 35, color);
        graphics.pose().pushMatrix();
        graphics.pose().translate(x, y);
        graphics.pose().scale(2);
        graphics.text(this.font, Component.literal(StringUtil.capitalize(CONFIG.activePet).replaceAll("_", " ")), 0, 0, color);
        graphics.pose().popMatrix();
        if (entity != null) {
            try {
                if (CONFIG.petOn)
                    InventoryScreen.extractEntityInInventoryFollowsMouse(graphics, xo - boxsize, yo - boxsize, xo + boxsize, yo + boxsize, size, 0.0625F, 30, 120, entity);
            } catch (Exception e) {
            }
        } else {
            Component component = Component.literal("⚠ Could not load 3D model of your pet because you are not in a level.").withStyle(ChatFormatting.RED);
            graphics.text(Minecraft.getInstance().font, component, this.width - this.font.width(component) - (Minecraft.getInstance().options.fullscreen().get() ? 10 : 30), this.height / 2, color);
        }
        if (ticks <= 0) {
            Minecraft.getInstance().options.guiScale().set(this.size);
            Minecraft.getInstance().options.save();
        }
    }

    @Override
    public void init() {
        int i = Minecraft.getInstance().options.fullscreen().get() ? 5 : 2;
        Minecraft.getInstance().options.guiScale().set(i);
        int fontWidth = font.width(CONFIG.activePet) * 2;
        int width = this.width / 2;
        int height = this.height - this.font.lineHeight - 15;
        this.button = this.addRenderableWidget(new NametagButton(this.width, this.height - 212, this.minecraft.font, this.minecraft.font.width("1") * 16, this.minecraft.font.lineHeight, Component.literal(this.entityName)));
        this.addRenderableWidget(new ExitButton(this, 10, 10, 80, 20));
        this.addRenderableWidget(new SwitchSkinsButton(this, width - (fontWidth / 2) - 10 - 80, height - 3, 80, 20));
        this.addRenderableWidget(new ChangePetButton(this, width + (fontWidth / 2) + 10, height - 3, 80, 20));
        this.addRenderableWidget(new ToggleButton(this.width - 42, this.height / 2 - 40, Component.literal("Pet On?"), (_) -> {
            CONFIG.petOn = !CONFIG.petOn;
            NetworkManager.get().broadcastTogglePet(Minecraft.getInstance().player.getStringUUID(), Utils.getActivePetName(), CONFIG.petOn);
        }, CONFIG.petOn, this));
        this.addRenderableWidget(new ToggleButton(this.width - 42, this.height / 2 + 40, Component.literal("Baby?"), (_) -> {
            CONFIG.isBaby = !CONFIG.isBaby;
            NetworkManager.get().broadcastToggleBaby(Minecraft.getInstance().player.getStringUUID(), CONFIG.isBaby);
        }, CONFIG.isBaby, this));
    }

    @Override
    public void onClose() {
        if (!this.closing) {
            this.closing = true;
            return;
        }
        super.onClose();
    }

    public DropdownMenu getDropDownMenu() {
        EnumImpl enu = switch (CONFIG.activePet) {
            case "duck" -> DuckSkins.valueOf(CONFIG.duckSkin.replaceAll(" ", "_"));
            case "cat" -> com.jeff.pets.client.enums.CatSkins.valueOf(CONFIG.catSkin.replaceAll(" ", "_"));
            case "racoon" -> RacoonSkins.valueOf(CONFIG.racoonSkin.replaceAll(" ", "_"));
            case "sheep" -> SheepSkins.valueOf(CONFIG.sheepSkin.replaceAll(" ", "_"));
            case "axolotl" -> AxolotlSkins.valueOf(CONFIG.axolotlSkin.replaceAll(" ", "_"));
            case "camel" -> CamelSkins.valueOf(CONFIG.camelSkin.replaceAll(" ", "_"));
            case "chicken" -> ChickenSkins.valueOf(CONFIG.chickenSkin.replaceAll(" ", "_"));
            case "creeper", "nerd_creeper", "smiling_creeper" ->
                    CreeperSkins.valueOf(CONFIG.creeperSkin.replaceAll(" ", "_"));
            case "copper_golem" -> CopperGolemSkins.valueOf(CONFIG.copperGolemSkin.replaceAll(" ", "_"));
            case "cow" -> CowSkins.valueOf(CONFIG.cowSkin.replaceAll(" ", "_"));
            case "frog" -> FrogSkins.valueOf(CONFIG.frogSkin.replaceAll(" ", "_"));
            case "horse" -> HorseSkins.valueOf(CONFIG.horseSkin.replaceAll(" ", "_"));
            case "parrot" -> ParrotSkins.valueOf(CONFIG.parrotSkin.replaceAll(" ", "_"));
            case "pig" -> PigSkins.valueOf(CONFIG.pigSkin.replaceAll(" ", "_"));
            case "rabbit" -> RabbitSkins.valueOf(CONFIG.rabbitSkin.replaceAll(" ", "_"));
            case "snow_golem" -> SnowGolemSkins.valueOf(CONFIG.snowGolemSkin.replaceAll(" ", "_"));
            case "squid" -> SquidSkins.valueOf(CONFIG.squidSkin.replaceAll(" ", "_"));
            case "strider" -> StriderSkins.valueOf(CONFIG.striderSkin.replaceAll(" ", "_"));
            case "villager" -> VillagerSkins.valueOf(CONFIG.villagerSkin.replaceAll(" ", "_"));
            case "mooshroom" -> MooshroomSkins.valueOf(CONFIG.mooshroomSkin.replaceAll(" ", "_"));
            case "bee" -> BeeSkins.valueOf(CONFIG.beeSkin.replaceAll(" ", "_"));
            case "fox" -> FoxSkins.valueOf(CONFIG.foxSkin.replaceAll(" ", "_"));
            case "llama" -> LlamaSkins.valueOf(CONFIG.llamaSkin.replaceAll(" ", "_"));
            case "nautilus" -> NautilusSkins.valueOf(CONFIG.nautilusSkin.replaceAll(" ", "_"));
            case "panda" -> PandaSkins.valueOf(CONFIG.pandaSkin.replaceAll(" ", "_"));
            case "piglin" -> PiglinSkins.valueOf(CONFIG.piglinSkin.replaceAll(" ", "_"));
            case "wolf" -> WolfSkins.valueOf(CONFIG.wolfSkin.replaceAll(" ", "_"));
            case "hoglin" -> HoglinSkins.valueOf(CONFIG.hoglinSkin.replaceAll(" ", "_"));
            case "magma_cube" -> SlimeLikeSkins.valueOf(CONFIG.magmaCubeSkin.replaceAll(" ", "_"));
            case "slime", "tropical_slime" -> SlimeLikeSkins.valueOf(CONFIG.slimeSkin.replaceAll(" ", "_"));
            case "zombie_villager" -> ZombieVillagerSkins.valueOf(CONFIG.zombieVillagerSkin.replaceAll(" ", "_"));
            case "wither" -> WitherSkins.valueOf(CONFIG.witherSkin.replaceAll(" ", "_"));
            case "dumbo_octopus" -> DumboOctopusSkins.valueOf(CONFIG.dumboOctopusSkin.replaceAll(" ", "_"));
            case "traitor" -> TraitorSkins.valueOf(CONFIG.traitorSkin.replaceAll(" ", "_"));
            case null, default -> BlankEnum.no_skins_are_available;
        };
        DropdownMenu menu = new DropdownMenu(this, enu, false);
        this.dropdownMenu = menu;
        return menu;
    }

    public DropdownMenu getPetsMenu() {
        return new DropdownMenu(this, com.jeff.pets.client.enums.PetList.valueOf(CONFIG.activePet), true);
    }

    @Override
    public void tick() {
        if (this.entity == null) return;
        this.entity.tick();
        this.entity.walkAnimation.setSpeed(0.3f);
        super.tick();
    }
}
