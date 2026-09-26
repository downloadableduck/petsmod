package com.jeff.pets.client.screen.buttons;

import com.jeff.pets.client.PetsConfigScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class DropDownButton extends Button {

    private final PetsConfigScreen screen;
    private final Type type;
    private final String name;
    public boolean opened = false;

    public DropDownButton(PetsConfigScreen screen, int x, int y, int width, int height, MutableComponent component, Type type, String name) {
        super(x, y, width, height, component, (_) -> {
        }, Supplier::get);
        this.screen = screen;
        this.type = type;
        this.name = name;
    }

    @Override
    protected void extractContents(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        if (this.opened) {
            DropdownMenu menu = this.screen.getCorrectMenuForCase(this.type);
            menu.name = this.name;
            menu.extractRenderState(graphics, mouseX, mouseY, this.getX(), this.getY() - 15);
        }
        Identifier id = this.isHovered() ? Identifier.fromNamespaceAndPath(MOD_ID, "dark_button_highlighted") : Identifier.fromNamespaceAndPath(MOD_ID, "dark_button");
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, id, this.getX(), this.getY(), this.width, this.height, screen.button.color);
        graphics.text(screen.getFont(), this.message, this.getX() + this.width / 2 - this.screen.getFont().width(this.message) / 2, this.getY() + 6, this.screen.button.color);
    }

    @Override
    public void onPress(@NotNull InputWithModifiers input) {
        this.opened = !this.opened;
    }
}
