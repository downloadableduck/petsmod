package com.jeff.pets.client.buttons;

import com.jeff.pets.Utils;
import com.jeff.pets.client.NewPetsConfigScreen;
import com.jeff.pets.client.enums.EnumImpl;
import net.fabricmc.loader.impl.util.StringUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.font.FontManager;
import net.minecraft.client.gui.font.FontOption;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.client.gui.font.FontTexture;
import net.minecraft.client.renderer.RenderPipelines;

import java.util.List;

public class DropdownMenu {
    List<Enum> values;
    int color;
    public DropdownMenu(NewPetsConfigScreen screen, EnumImpl enumimpl) {
        this.values = enumimpl.getAllValues();
        this.color = screen.button.color;
    }

    public void extractRenderState(GuiGraphicsExtractor graphics, int x, int y) {
        for (Enum value : values) {
            y += 5;
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, Utils.withModNamespace("blue_button"), x, y, 80, 30, color);
            graphics.text(Minecraft.getInstance().font, StringUtil.capitalize(value.toString().replaceAll("_", " ")), x, y, color);
        }
    }
}
