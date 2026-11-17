//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.widget;

import me.shedaniel.clothconfig2.ParentElement;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import net.minecraft.client.Minecraft;

public abstract class DynamicElementListWidget<E extends DynamicElementListWidget.ElementEntry<E>> extends DynamicNewSmoothScrollingEntryListWidget<E> {
    public DynamicElementListWidget(Minecraft client, int width, int height, int top, int bottom, String backgroundLocation) {
        super(client, width, height, top, bottom, backgroundLocation);
    }

    public void changeFocus(boolean boolean_1) {
    }

    protected boolean isSelected(int int_1) {
        return false;
    }

    public abstract static class ElementEntry<E extends ElementEntry<E>> extends DynamicEntryListWidget.Entry<E> implements ParentElement {
        private GuiEventListener focused;
        private boolean dragging;

        public ElementEntry() {
        }

        public boolean isDragging() {
            return this.dragging;
        }

        public void setDragging(boolean boolean_1) {
            this.dragging = boolean_1;
        }

        public GuiEventListener getFocused() {
            return this.focused;
        }

        public void setFocused(GuiEventListener element_1) {
            this.focused = element_1;
        }
    }
}
