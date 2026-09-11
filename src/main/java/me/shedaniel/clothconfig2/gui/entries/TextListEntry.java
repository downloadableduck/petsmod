package me.shedaniel.clothconfig2.gui.entries;

import net.minecraft.client.Minecraft;
import me.shedaniel.clothconfig2.compat.GuiEventListener;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class TextListEntry extends TooltipListEntry {
    
    private int savedWidth = -1;
    private int color;
    private String text;
    
    public TextListEntry(String fieldName, String text) {
        this(fieldName, text, -1);
    }
    
    public TextListEntry(String fieldName, String text, int color) {
        this(fieldName, text, color, null);
    }
    
    public TextListEntry(String fieldName, String text, int color, Supplier<Optional<String[]>> tooltipSupplier) {
        super(fieldName, tooltipSupplier, false);
        this.text = text;
        this.color = color;
    }
    
    @Override
    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        this.savedWidth = entryWidth;
        int yy = y + 4;
        List<String> strings = Minecraft.getInstance().textRenderer.split(text, savedWidth);
        for(int i = 0; i < strings.size(); i++) {
            Minecraft.getInstance().textRenderer.drawWithShadow(strings.get(i), x, yy, color);
            yy += Minecraft.getInstance().textRenderer.fontHeight + 3;
        }
    }
    
    @Override
    public int getItemHeight() {
        if (savedWidth == -1)
            return 12;
        List<String> strings = Minecraft.getInstance().textRenderer.split(text, savedWidth);
        if (strings.isEmpty())
            return 0;
        return 15 + strings.size() * 12;
    }
    
    @Override
    public Object getValue() {
        return null;
    }
    
    @Override
    public Optional<Object> getDefaultValue() {
        return Optional.empty();
    }

    @Override
    public void save() {

    }

    @Override
    public List<? extends GuiEventListener> children() {
        return Collections.emptyList();
    }
    
}
