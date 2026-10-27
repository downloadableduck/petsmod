package com.jeff.pets.client.screen.buttons;


import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsConfigScreen;
import com.jeff.pets.client.Utils;
import com.jeff.pets.client.addon.PetsModAddons;
import com.jeff.pets.client.enums.Action;
import com.jeff.pets.client.enums.BlankEnum;
import com.jeff.pets.client.network.NetworkManager;
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
import java.util.Optional;

import static com.jeff.pets.client.Central.CONFIG;


public class DropdownMenu {

    public static Optional<SearchBox> search = Optional.empty();
    static int min;
    static int max;
    static int petMin;
    static int petMax;
    final ArrayList<String> values;
    final int color;
    final PetsConfigScreen screen;
    private final Type type;
    public String name;

    public DropdownMenu(PetsConfigScreen screen, ArrayList<String> values, Type type) {
        this.values = values;
        PetsModAddons.forEachAddedPet((_, petValue, _, _, _, petSkin) -> {
            if (type == Type.PET) {
                this.values.add(petValue);
            } else if (type == Type.SKIN) {
                petSkin.ifPresent(skin -> {
                    this.values.addAll(skin.skins());
                    this.values.remove(BlankEnum.no_skins_are_available.name());
                });
            }
        });
        this.values.sort((e1, e2) -> e2.compareToIgnoreCase(e1));
        search.ifPresent(searchBox -> this.values.removeIf(string -> {
            if (searchBox.getValue().isEmpty() || SearchBox.search.contains(searchBox.getValue())) return false;
            return !string.toLowerCase().contains(searchBox.getValue());
        }));
        this.color = screen.button.color;
        this.screen = screen;
        this.type = type;
        if (this.type == Type.PET) {
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

        if (search.isEmpty()) {
            if (this.type == Type.PET) {
                search = Optional.of(new SearchBox(Minecraft.getInstance().font, Component.empty(), this.screen.button.color));
            }
        }
    }

    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, int x, int y) {
        y += 15;
        Font font = Minecraft.getInstance().font;
        int width = 80;
        int padding = 2;
        int availableTextWidth = width - (padding * 2);
        int topY = 0;

        for (String value : values) {
            int index = values.indexOf(value);
            if (this.type == Type.PET) {
                if (index >= petMax || index < petMin) {
                    continue;
                }
            } else {
                if (index >= max || index < min) {
                    continue;
                }
            }
            String formattedText = Utils.capitalize(value.replaceAll("_", " "));
            Component textComponent = Component.nullToEmpty(formattedText);
            List<FormattedCharSequence> lines = font.split(textComponent, availableTextWidth);

            int lineCount = Math.max(1, lines.size());
            int slotHeight = (font.lineHeight * lineCount) + (padding * 2);
            y -= slotHeight;
            topY = y;

            boolean hovered = mouseX >= x && mouseX < x + width &&
                    mouseY >= y && mouseY < y + slotHeight;

            Identifier id = this.getTexture(value, hovered);
            graphics.pose().pushMatrix();
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, id, x, y, width, slotHeight, color);
            for (int i = 0; i < lines.size(); i++) {
                int lineY = y + padding + (i * font.lineHeight);
                graphics.text(font, lines.get(i), x + padding, lineY, color, false);
            }
            graphics.pose().popMatrix();

            long window = Minecraft.getInstance().getWindow().handle();
            boolean pressed = GLFW.glfwGetMouseButton(window, GLFW.GLFW_MOUSE_BUTTON_1) == GLFW.GLFW_PRESS;


            if (hovered && pressed && !value.equals(BlankEnum.no_skins_are_available.name()) && type == Type.SKIN) {
                Utils.setActivePetSkin(value);
            } else if (hovered && pressed && type == Type.PET) {
                Utils.setActivePet(Utils.getPet(value), value);
                if (Minecraft.getInstance().player != null) {
                    Central.despawnPet();
                    NetworkManager.get().broadcastGeneral(Minecraft.getInstance().player.getStringUUID(), CONFIG.petOn, CONFIG.activePet, Utils.getActivePetName(), Utils.getActivePetSkin(), CONFIG.isBaby, CONFIG.wanderingEnabled);
                    Central.summonPet();
                    Central.updateSuggestions(Minecraft.getInstance());
                }
                PetsConfigScreen petsConfigScreen = new PetsConfigScreen();
                petsConfigScreen.ticks = 20;
                Minecraft.getInstance().gui.setScreen(null);
                Minecraft.getInstance().gui.setScreen(petsConfigScreen);
                petsConfigScreen.petsButton.opened = true;
                //fix this
            } else if (hovered && pressed && type == Type.KEYBIND) {
                Action action = Action.valueOf(value.replaceAll(" ", "_"));
                if (this.name != null) {
                    switch (this.name.toLowerCase()) {
                        case "interact" -> CONFIG.interaction = action;
                        case "pick up" -> CONFIG.pickUp = action;
                        case "sit" -> CONFIG.sit = action;
                    }
                    Central.saveConfig();
                }
            }
        }
        int finalTopY = topY;
        search.ifPresent(searchBox -> {
            if (this.type == Type.PET) {
                searchBox.setX(x);
                searchBox.setY(finalTopY - searchBox.getHeight());
                searchBox.extractRenderState(graphics, mouseX, mouseY, Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaTicks());
            }
        });
    }


    @Override
    public String toString() {
        return this.values.toString();
    }


    public void onScroll(boolean down) {
        if (this.values.size() < 8) return;
        int maxScroll = Math.max(0, this.values.size() - 8);

        if (this.type == Type.PET) {
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

    public Identifier getTexture(String value, boolean hovered) {
        Identifier selected = Utils.withModNamespace("dropdown_menu_slot_selected");
        Identifier id = hovered ? Utils.withModNamespace("dropdown_menu_slot_highlighted") : Utils.withModNamespace("dropdown_menu_slot");
        if (this.type == Type.KEYBIND) {
            return switch (this.name.toLowerCase()) {
                case "interact" -> {
                    if (CONFIG.interaction.name().equals(value)) {
                        yield selected;
                    }
                    yield id;
                }
                case "pick up" -> {
                    if (CONFIG.pickUp.name().equals(value)) {
                        yield selected;
                    }
                    yield id;
                }
                case "sit" -> {
                    if (CONFIG.sit.name().equals(value)) {
                        yield selected;
                    }
                    yield id;
                }
                default -> id;
            };
        } else if (this.type == Type.SKIN && !value.equals("not_a_skin")) {
            if (Utils.getActivePetSkin().equals(value)) {
                return Utils.withModNamespace("dropdown_menu_slot_selected");
            }
        } else if (this.type == Type.PET) {
            if (CONFIG.activePet.equals(value)) {
                return Utils.withModNamespace("dropdown_menu_slot_selected");
            }
        }
        return id;
    }
}