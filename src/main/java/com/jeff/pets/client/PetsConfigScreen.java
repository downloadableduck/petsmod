package com.jeff.pets.client;

import com.jeff.pets.client.buttons.*;
import com.jeff.pets.client.enums.EnumImpl;
import com.jeff.pets.client.enums.*;
import com.jeff.pets.client.network.NetworkManager;
import com.jeff.pets.mob.AbstractPet;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import scala.collection.parallel.ParIterableLike;

import java.io.IOException;
import java.util.function.Supplier;

import static com.jeff.pets.client.Central.CONFIG;
/**
 * func_78326_a() = getSclaedWidth
 * func_78328_b() = getScaledHeight
 * */
public class PetsConfigScreen extends GuiScreen {

    public float ticks;
    public NametagButton button;
    public boolean closing = false;
    public AbstractPet entity;
    public DropdownMenu dropdownMenu;
    public String entityName;
    private int size = 0;

    public PetsConfigScreen() {
        super();
        ticks = 0;
        if (Minecraft.getInstance().world != null) {
            entity = Utils.getPet(CONFIG.activePet);
            entity.func_152115_b(Minecraft.getInstance().player.getGameProfile().getId().toString());
        }
        this.entityName = Utils.getActivePetName();
    }

    @Override
    public void render(int mousex, int mousey, float a) {
        float duration = (float) 4 / 20;
        if (!this.closing) {
            ticks = Math.min(1.0f, ticks + (a * duration));
        } else {
            ticks = Math.min(1.0f, ticks + (a - duration));
        }

        if (this.closing && this.ticks <= 0) {
            super.onGuiClosed();
        }
        int alpha = (int) (ticks * 255);

        int color = (alpha << 24) | 0xFFFFFF;
        if (this.button != null) {
            this.button.color = color;
            this.button.func_146194_f();
        }
        ScaledResolution window = new ScaledResolution(Minecraft.getInstance());
        int xo = this.width / 2;
        int yo = this.height / 2;
        int size = 0;
        if (this.entity != null) {
            size = (int) (75 / Math.max(0.01, this.entity.getBoundingBox().getAverageEdgeLength()));
        }
        int boxsize = 250;
        int x = ((this.width - fontRenderer.getStringWidth(CONFIG.activePet)) - (this.width / 2));
        int y = this.height - this.fontRenderer.FONT_HEIGHT - 15;
        this.blit(Utils.withModNamespace("textures/gui/background.png"), 0, 0, window.func_78326_a(), window.func_78328_b(), color);
        this.drawString(this.fontRenderer, StringUtils.capitalize("Your currently selected pet is: "), (this.width - fontRenderer.getStringWidth("Your currently selected pet is: ")) / 2, this.height - this.fontRenderer.FONT_HEIGHT - 35, color);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(x, y, 1);
        GlStateManager.scalef(2, 2, 0);
        this.drawString(this.fontRenderer, (StringUtils.capitalize(CONFIG.activePet).replaceAll("_", " ")), 0, 0, color);
        GlStateManager.popMatrix();
        if (entity != null) {
            try {
                if (CONFIG.petOn)
                    GuiInventory.drawEntityOnScreen(xo, yo + 50, size, 230, 0, entity);
            } catch (Exception e) {
            }
        } else {
            String component = "⚠ Could not load 3D model of your pet because you are not in a level.";
            this.drawString(Minecraft.getInstance().fontRenderer, component, this.width - this.fontRenderer.getStringWidth(component) - (Minecraft.getInstance().gameSettings.fullScreen ? 10 : 30), this.height / 2, color);
        }
        if (ticks <= 0) {
            Minecraft.getInstance().gameSettings.guiScale = (this.size);
            Minecraft.getInstance().gameSettings.saveOptions();
        }
        super.render(mousex, mousey, a);
    }

    @Override
    public void initGui() {
        if (this.size == 0) {
            this.size = Minecraft.getInstance().gameSettings.guiScale;
        }
        Minecraft.getInstance().gameSettings.guiScale = 5;
        Minecraft.getInstance().gameSettings.saveOptions();
        ScaledResolution res = new ScaledResolution(Minecraft.getInstance());
        this.width = res.func_78326_a();
        this.height = res.func_78328_b();
        int fontWidth = fontRenderer.getStringWidth(CONFIG.activePet) * 2;
        int width = this.width / 2;
        int height = this.height - this.fontRenderer.FONT_HEIGHT - 15;
        this.button = new NametagButton(this.width, this.height - 212, this.mc.fontRenderer, this.mc.fontRenderer.getStringWidth("1") * 16, this.mc.fontRenderer.FONT_HEIGHT, this.entityName);
        this.buttons.add(new ExitButton(this, 10, 10, 80, 20));
        this.buttons.add(new SwitchSkinsButton(this, width - (fontWidth / 2) - 10 - 80, height - 3, 80, 20));
        this.buttons.add(new ChangePetButton(this, width + (fontWidth / 2) + 10, height - 3, 80, 20));
        this.buttons.add(new ToggleButton(this.width - 42, this.height / 2 - 80, "Show hitboxes?", () -> {
            CONFIG.renderPetHitbox = !CONFIG.renderPetHitbox;
        }, () -> CONFIG.renderPetHitbox, this, 34));
        this.buttons.add(new ToggleButton(this.width - 42, this.height / 2 - 40, "Pet On?", () -> {
            CONFIG.petOn = !CONFIG.petOn;
            NetworkManager.get().broadcastTogglePet(Minecraft.getInstance().player.getGameProfile().getId().toString(), Utils.getActivePetName(), CONFIG.petOn);
        }, () -> CONFIG.petOn, this));
        this.buttons.add(new ToggleButton(this.width - 42, this.height / 2 + 40, "Always show nametag?", () -> {
            CONFIG.alwaysRenderNametag = !CONFIG.alwaysRenderNametag;
        }, () -> CONFIG.alwaysRenderNametag, this, 69));
        this.buttons.add(new ToggleButton(this.width - 42, this.height / 2 + 80, "Baby?", () -> {
            CONFIG.isBaby = !CONFIG.isBaby;
            NetworkManager.get().broadcastToggleBaby(Minecraft.getInstance().player.getGameProfile().getId().toString(), CONFIG.isBaby);
        }, () -> CONFIG.isBaby, this));
    }

    @Override
    public void onGuiClosed() {
        if (this.size != 0) {
            Minecraft.getInstance().gameSettings.guiScale = this.size;
            Minecraft.getInstance().gameSettings.saveOptions();
        }
        if (!this.closing) {
            this.closing = true;
            return;
        }
        AutoConfig.getConfigHolder(PetsConfig.class).save();
        super.onGuiClosed();
    }

    public DropdownMenu getDropDownMenu() {
        EnumImpl skin;
        switch (CONFIG.activePet) {
                case "duck":
                    skin = DuckSkins.valueOf(CONFIG.duckSkin.replaceAll(" ", "_"));
                    break;
                case "cat":
                    skin = com.jeff.pets.client.enums.CatSkins.valueOf(CONFIG.catSkin.replaceAll(" ", "_"));
                    break;
                case "racoon":
                    skin = RacoonSkins.valueOf(CONFIG.racoonSkin.replaceAll(" ", "_"));
                    break;
                case "sheep":
                    skin = SheepSkins.valueOf(CONFIG.sheepSkin.replaceAll(" ", "_"));
                    break;
                case "axolotl":
                    skin = AxolotlSkins.valueOf(CONFIG.axolotlSkin.replaceAll(" ", "_"));
                    break;
                case "camel":
                    skin = CamelSkins.valueOf(CONFIG.camelSkin.replaceAll(" ", "_"));
                    break;
                case "chicken":
                    skin = ChickenSkins.valueOf(CONFIG.chickenSkin.replaceAll(" ", "_"));
                    break;
                case "creeper":
                case "nerd_creeper":
                case "smiling_creeper":
                    skin = CreeperSkins.valueOf(CONFIG.creeperSkin.replaceAll(" ", "_"));
                    break;
                case "copper_golem":
                    skin = CopperGolemSkins.valueOf(CONFIG.copperGolemSkin.replaceAll(" ", "_"));
                    break;
                case "cow":
                    skin = CowSkins.valueOf(CONFIG.cowSkin.replaceAll(" ", "_"));
                    break;
                case "frog":
                    skin = FrogSkins.valueOf(CONFIG.frogSkin.replaceAll(" ", "_"));
                    break;
                case "horse":
                    skin = HorseSkins.valueOf(CONFIG.horseSkin.replaceAll(" ", "_"));
                    break;
                case "parrot":
                    skin = ParrotSkins.valueOf(CONFIG.parrotSkin.replaceAll(" ", "_"));
                    break;
                case "pig":
                    skin = PigSkins.valueOf(CONFIG.pigSkin.replaceAll(" ", "_"));
                    break;
                case "rabbit":
                    skin = RabbitSkins.valueOf(CONFIG.rabbitSkin.replaceAll(" ", "_"));
                    break;
                case "snow_golem":
                    skin = SnowGolemSkins.valueOf(CONFIG.snowGolemSkin.replaceAll(" ", "_"));
                    break;
                case "squid":
                    skin = SquidSkins.valueOf(CONFIG.squidSkin.replaceAll(" ", "_"));
                    break;
                case "strider":
                    skin = StriderSkins.valueOf(CONFIG.striderSkin.replaceAll(" ", "_"));
                    break;
                case "villager":
                    skin = VillagerSkins.valueOf(CONFIG.villagerSkin.replaceAll(" ", "_"));
                    break;
                case "mooshroom":
                    skin = MooshroomSkins.valueOf(CONFIG.mooshroomSkin.replaceAll(" ", "_"));
                    break;
                case "bee":
                    skin = BeeSkins.valueOf(CONFIG.beeSkin.replaceAll(" ", "_"));
                    break;
                case "fox":
                    skin = FoxSkins.valueOf(CONFIG.foxSkin.replaceAll(" ", "_"));
                    break;
                case "llama":
                    skin = LlamaSkins.valueOf(CONFIG.llamaSkin.replaceAll(" ", "_"));
                    break;
                case "nautilus":
                    skin = NautilusSkins.valueOf(CONFIG.nautilusSkin.replaceAll(" ", "_"));
                    break;
                case "panda":
                    skin = PandaSkins.valueOf(CONFIG.pandaSkin.replaceAll(" ", "_"));
                    break;
                case "piglin":
                    skin = PiglinSkins.valueOf(CONFIG.piglinSkin.replaceAll(" ", "_"));
                    break;
                case "wolf":
                    skin = WolfSkins.valueOf(CONFIG.wolfSkin.replaceAll(" ", "_"));
                    break;
                case "hoglin":
                    skin = HoglinSkins.valueOf(CONFIG.hoglinSkin.replaceAll(" ", "_"));
                    break;
                case "magma_cube":
                    skin = SlimeLikeSkins.valueOf(CONFIG.magmaCubeSkin.replaceAll(" ", "_"));
                    break;
                case "slime":
                case "tropical_slime":
                    skin = SlimeLikeSkins.valueOf(CONFIG.slimeSkin.replaceAll(" ", "_"));
                    break;
                case "zombie_villager":
                    skin = ZombieVillagerSkins.valueOf(CONFIG.zombieVillagerSkin.replaceAll(" ", "_"));
                    break;
                case "wither":
                    skin = WitherSkins.valueOf(CONFIG.witherSkin.replaceAll(" ", "_"));
                    break;
                case "dumbo_octopus":
                    skin = DumboOctopusSkins.valueOf(CONFIG.dumboOctopusSkin.replaceAll(" ", "_"));
                    break;
                case "traitor":
                    skin = TraitorSkins.valueOf(CONFIG.traitorSkin.replaceAll(" ", "_"));
                    break;
                default:
                    skin = BlankEnum.no_skins_are_available;
                    break;
        };
        DropdownMenu menu = new DropdownMenu(this, skin, false);
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
        this.entity.limbSwingAmount = (0.3f);
        super.tick();
    }

    @Override
    public void func_146284_a(net.minecraft.client.gui.GuiButton button) throws IOException {
        super.func_146284_a(button);
        if (button instanceof GuiButton) {
            ((GuiButton) button).onPress();
        }
    }

    protected void blit(ResourceLocation resourceLocation, int x, int y, int width, int height, int color) {
        GlStateManager.enableBlend();
        //GlStateManager.color4f(255, 255, 255, color);
        Minecraft.getInstance().getTextureManager().bindTexture(resourceLocation);
        Gui.drawScaledCustomSizeModalRect(x, y, 0, 0, 128, 128, width, height, 128, 128);
        GlStateManager.disableBlend();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}