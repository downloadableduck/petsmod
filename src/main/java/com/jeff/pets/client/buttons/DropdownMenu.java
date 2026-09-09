package com.jeff.pets.client.buttons;


import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsConfigScreen;
import com.jeff.pets.client.Utils;
import com.jeff.pets.client.enums.BlankEnum;
import com.jeff.pets.client.enums.EnumImpl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
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
        this.values = new ArrayList<>(enumimpl.getAllValues());
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

        GLFW.glfwSetScrollCallback(Minecraft.getInstance().getWindow().handle(), (handle, xo, yo) -> {
            this.onScroll(yo < 0);
        });
    }

    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, int x, int y) {
        y += 15;
        Font font = Minecraft.getInstance().font;
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
            String formattedText = Utils.capitalize(value.toString().replaceAll("_", " "));
            Component textComponent = Component.literal(formattedText);
            List<FormattedCharSequence> lines = font.split(textComponent, availableTextWidth);

            int lineCount = Math.max(1, lines.size());
            int slotHeight = (font.lineHeight * lineCount) + (padding * 2);
            y -= slotHeight;

            boolean hovered = mouseX >= x && mouseX < x + width &&
                    mouseY >= y && mouseY < y + slotHeight;

            Identifier id = hovered ? Utils.withModNamespace("dropdown_menu_slot_highlighted") : Utils.withModNamespace("dropdown_menu_slot");
            graphics.pose().pushMatrix();
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, id, x, y, width, slotHeight, color);
            for (int i = 0; i < lines.size(); i++) {
                int lineY = y + padding + (i * font.lineHeight);
                graphics.text(font, lines.get(i), x + padding, lineY, color, false);
            }
            graphics.pose().popMatrix();

            long window = Minecraft.getInstance().getWindow().handle();
            boolean pressed = GLFW.glfwGetMouseButton(window, GLFW.GLFW_MOUSE_BUTTON_1) == GLFW.GLFW_PRESS;

            if (hovered && pressed && !value.equals(BlankEnum.no_skins_are_available) && !(value instanceof com.jeff.pets.client.enums.PetList)) {
                Utils.setActivePetSkin(String.valueOf(value));
            } else if (hovered && pressed && value instanceof com.jeff.pets.client.enums.PetList) {
                Utils.setActivePet(Utils.getPet(value.toString()), value.toString());
                if (Minecraft.getInstance().player != null) {
                    Central.despawnPet();
                    com.jeff.pets.client.network.NetworkManager.get().broadcastGeneral(Minecraft.getInstance().player.getStringUUID(), CONFIG.petOn, CONFIG.activePet, Utils.getActivePetName(), Utils.getActivePetSkin(), CONFIG.isBaby);
                    Central.summonPet();
                    Central.updateSuggestions(Minecraft.getInstance());
                }
                PetsConfigScreen petsConfigScreen = new PetsConfigScreen();
                petsConfigScreen.ticks = 20;
                Minecraft.getInstance().setScreen(null);
                Minecraft.getInstance().setScreen(petsConfigScreen);
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
}