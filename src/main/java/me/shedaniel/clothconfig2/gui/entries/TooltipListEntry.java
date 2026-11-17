//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.QueuedTooltip;
import me.shedaniel.math.Point;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class TooltipListEntry<T> extends AbstractConfigListEntry<T> {
    private Supplier<Optional<String[]>> tooltipSupplier;

    public TooltipListEntry(String fieldName, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, requiresRestart);
        this.tooltipSupplier = tooltipSupplier;
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        if (this.isMouseInside(mouseX, mouseY, x, y, entryWidth, entryHeight)) {
            Optional<String[]> tooltip = this.getTooltip();
            if (tooltip.isPresent() && ((String[])tooltip.get()).length > 0) {
                this.getScreen().queueTooltip(QueuedTooltip.create(new Point(mouseX, mouseY), Arrays.asList((String[])tooltip.get())));
            }
        }

    }

    public boolean isMouseInside(int mouseX, int mouseY, int x, int y, int entryWidth, int entryHeight) {
        return mouseX >= x && mouseY >= y && mouseX <= x + entryWidth && mouseY <= y + entryHeight && this.getParent().isMouseOver((double)mouseX, (double)mouseY);
    }

    public Optional<String[]> getTooltip() {
        return this.tooltipSupplier != null ? (Optional)this.tooltipSupplier.get() : Optional.empty();
    }

    public Supplier<Optional<String[]>> getTooltipSupplier() {
        return this.tooltipSupplier;
    }

    public void setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
    }
}
