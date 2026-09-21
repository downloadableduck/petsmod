package com.jeff.pets.client.buttons;


import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsConfigScreen;
import com.jeff.pets.client.Utils;
import com.jeff.pets.client.enums.BlankEnum;
import com.jeff.pets.client.enums.EnumImpl;
import com.jeff.pets.client.network.NetworkManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Mouse;
import scala.swing.event.MouseButtonEvent;
import scala.swing.event.MousePressed;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.jeff.pets.client.Central.CONFIG;


public class DropdownMenu {

    static int min;
    static int max;
    static int petMin;
    static int petMax;
    private final boolean isPet;
    ArrayList<Enum> values;
    int color;
    PetsConfigScreen screen;

    public DropdownMenu(PetsConfigScreen screen, EnumImpl enumimpl, boolean isPet) {
        this.values = new ArrayList<>((Collection) Arrays.asList(enumimpl.getClass().getEnumConstants()));
        this.values.sort((e1, e2) -> e2.name().compareToIgnoreCase(e1.name()));
        this.color = screen.button.color;
        this.screen = screen;
        this.isPet = isPet;
        if (this.isPet) {
            if (petMax > this.values.size() || petMin < 0 || petMax == 0) {
                petMin = Math.max(0, this.values.size() - 8);
                petMax = petMin + 8;
            }
        } else {
            if (max > this.values.size() || min < 0 || max == 0) {
                min = Math.max(0, this.values.size() - 8);
                max = min + 8;
            }
        }
    }

    public void extractRenderState(int mouseX, int mouseY, int x, int y) {
        this.handleScrolling();
        y += 15;
        FontRenderer font = Minecraft.getInstance().fontRenderer;
        int width = 80;
        int padding = 2;
        int availableTextWidth = width - (padding * 2);

        for (Enum value : values) {
            int index = values.indexOf(value);
            if (this.isPet) {
                if (index >= petMax || index < petMin) {
                    continue;
                }
            } else {
                if (index >= max || index < min) {
                    continue;
                }
            }
            String formattedText = StringUtils.capitalize(value.toString().replaceAll("_", " "));
            String textComponent = formattedText;
            List<String> lines = font.listFormattedStringToWidth(textComponent, availableTextWidth);

            int lineCount = Math.max(1, lines.size());
            int slotHeight = (font.FONT_HEIGHT * lineCount) + (padding * 2);
            y -= slotHeight;

            boolean hovered = mouseX >= x && mouseX < x + width &&
                    mouseY >= y && mouseY < y + slotHeight;

            ResourceLocation id = hovered ? Utils.withModNamespace("textures/gui/sprites/dropdown_menu_slot_highlighted.png") : Utils.withModNamespace("textures/gui/sprites/dropdown_menu_slot.png");
            GlStateManager.pushMatrix();
            blit(id, x, y, width, slotHeight, color);
            for (int i = 0; i < lines.size(); i++) {
                int lineY = y + padding + (i * font.FONT_HEIGHT);
                this.drawString(font, lines.get(i), x + padding, lineY, color);
            }
            GlStateManager.popMatrix();

            //long window = Minecraft.getInstance().getWindow().handle();
            boolean pressed = Mouse.isButtonDown(0);

            if (hovered && pressed && !value.equals(BlankEnum.no_skins_are_available) && !(value instanceof com.jeff.pets.client.enums.PetList)) {
                Utils.setActivePetSkin(String.valueOf(value));
            } else if (hovered && pressed && value instanceof com.jeff.pets.client.enums.PetList) {
                Utils.setActivePet(Utils.getPet(value.toString()), value.toString());
                if (Minecraft.getInstance().player != null) {
                    Central.despawnPet();
                    NetworkManager.get().broadcastGeneral(Minecraft.getInstance().player.getUniqueID().toString(), CONFIG.petOn, CONFIG.activePet, Utils.getActivePetName(), Utils.getActivePetSkin(), CONFIG.isBaby);
                    Central.summonPet();
                    Central.updateSuggestions(Minecraft.getInstance());
                }
                PetsConfigScreen petsConfigScreen = new PetsConfigScreen();
                petsConfigScreen.ticks = 20;
                Minecraft.getInstance().displayGuiScreen(null);
                Minecraft.getInstance().displayGuiScreen(petsConfigScreen);
            }
        }
    }


    @Override
    public String toString() {
        return this.values.toString();
    }


    public void onScroll(boolean down) {
        if (this.values.size() < 8) return;
        int maxScroll = Math.max(0, this.values.size() - 8);

        if (this.isPet) {
            if (down) {
                petMin = Math.max(0, petMin - 1);
            } else {
                petMin = Math.min(maxScroll, petMin + 1);
            }
            petMax = petMin + 8;
        } else {
            if (down) {
                min = Math.max(0, min - 1);
            } else {
                min = Math.min(maxScroll, min + 1);
            }
            max = min + 8;
        }
    }

    private void handleScrolling() {
        int wheel = Mouse.getDWheel();
        if (wheel != 0) {
            this.onScroll(wheel < 0);
        }
    }

    public void drawTexturedModalRect(int p_drawTexturedModalRect_1_, int p_drawTexturedModalRect_2_, int p_drawTexturedModalRect_3_, int p_drawTexturedModalRect_4_, int p_drawTexturedModalRect_5_, int p_drawTexturedModalRect_6_) {
        float lvt_7_1_ = 0.00390625F;
        float lvt_8_1_ = 0.00390625F;
        Tessellator lvt_9_1_ = Tessellator.getInstance();
        WorldRenderer lvt_10_1_ = lvt_9_1_.getBuffer();
        lvt_10_1_.begin(7, DefaultVertexFormats.POSITION_TEX);
        lvt_10_1_.pos((double) (p_drawTexturedModalRect_1_ + 0), (double) (p_drawTexturedModalRect_2_ + p_drawTexturedModalRect_6_), (double) 0).func_181673_a((double) ((float) (p_drawTexturedModalRect_3_ + 0) * lvt_7_1_), (double) ((float) (p_drawTexturedModalRect_4_ + p_drawTexturedModalRect_6_) * lvt_8_1_)).endVertex();
        lvt_10_1_.pos((double) (p_drawTexturedModalRect_1_ + p_drawTexturedModalRect_5_), (double) (p_drawTexturedModalRect_2_ + p_drawTexturedModalRect_6_), (double) 0).func_181673_a((double) ((float) (p_drawTexturedModalRect_3_ + p_drawTexturedModalRect_5_) * lvt_7_1_), (double) ((float) (p_drawTexturedModalRect_4_ + p_drawTexturedModalRect_6_) * lvt_8_1_)).endVertex();
        lvt_10_1_.pos((double) (p_drawTexturedModalRect_1_ + p_drawTexturedModalRect_5_), (double) (p_drawTexturedModalRect_2_ + 0), (double) 0).func_181673_a((double) ((float) (p_drawTexturedModalRect_3_ + p_drawTexturedModalRect_5_) * lvt_7_1_), (double) ((float) (p_drawTexturedModalRect_4_ + 0) * lvt_8_1_)).endVertex();
        lvt_10_1_.pos((double) (p_drawTexturedModalRect_1_ + 0), (double) (p_drawTexturedModalRect_2_ + 0), (double) 0).func_181673_a((double) ((float) (p_drawTexturedModalRect_3_ + 0) * lvt_7_1_), (double) ((float) (p_drawTexturedModalRect_4_ + 0) * lvt_8_1_)).endVertex();
        lvt_9_1_.draw();
    }

    protected void blit(ResourceLocation resourceLocation, int x, int y, int width, int height, int color) {
        GlStateManager.enableBlend();
        //GlStateManager.color4f(255, 255, 255, color);
        Minecraft.getInstance().getTextureManager().bindTexture(resourceLocation);
        Gui.drawScaledCustomSizeModalRect(x, y, 0, 0, 200, 20, width, height, 200, 20);
        GlStateManager.disableBlend();
    }

    public void drawString(FontRenderer p_drawString_1_, String p_drawString_2_, int p_drawString_3_, int p_drawString_4_, int p_drawString_5_) {
        p_drawString_1_.drawStringWithShadow(p_drawString_2_, (float)p_drawString_3_, (float)p_drawString_4_, p_drawString_5_);
    }
}