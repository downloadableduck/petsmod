//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;
import net.minecraft.client.font.TextRenderer;

import com.jeff.pets.client.Utils;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import net.minecraft.client.Minecraft;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class TextListEntry extends TooltipListEntry {
    private int savedWidth;
    private int color;
    private String text;

    public TextListEntry(String fieldName, String text) {
        this(fieldName, text, -1);
    }

    public TextListEntry(String fieldName, String text, int color) {
        this(fieldName, text, color, (Supplier)null);
    }

    public TextListEntry(String fieldName, String text, int color, Supplier<Optional<String[]>> tooltipSupplier) {
        super(fieldName, tooltipSupplier, false);
        this.savedWidth = -1;
        this.text = text;
        this.color = color;
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        this.savedWidth = entryWidth;
        int yy = y + 4;
        List<String> strings = Utils.split(this.text, this.savedWidth);

        for(int i = 0; i < strings.size(); ++i) {
            TextRenderer.of(Minecraft.getMinecraft().font).drawWithShadow((String)strings.get(i), x, yy, this.color);
            yy += 12;
        }

    }

    public int getItemHeight() {
        if (this.savedWidth == -1) {
            return 12;
        } else {
            List<String> strings = Utils.split(this.text, this.savedWidth);
            return strings.isEmpty() ? 0 : 15 + strings.size() * 12;
        }
    }

    public Object getValue() {
        return null;
    }

    public Optional<Object> getDefaultValue() {
        return Optional.empty();
    }

    public void save() {
    }

    public List<? extends GuiEventListener> children() {
        return Collections.emptyList();
    }
}
