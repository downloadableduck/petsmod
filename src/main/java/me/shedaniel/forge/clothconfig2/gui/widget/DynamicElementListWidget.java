package me.shedaniel.forge.clothconfig2.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class DynamicElementListWidget<E extends DynamicElementListWidget.ElementEntry<E>> extends DynamicNewSmoothScrollingEntryListWidget<E> {

    public DynamicElementListWidget(Minecraft client, int width, int height, int top, int bottom, ResourceLocation backgroundLocation) {
        super(client, width, height, top, bottom, backgroundLocation);
    }

    protected boolean isSelected(int int_1) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public abstract static class ElementEntry<E extends ElementEntry<E>> extends Entry<E> {
        private E focused;
        private boolean dragging;

        public ElementEntry() {
        }

        public boolean isDragging() {
            return this.dragging;
        }

        public void setDragging(boolean boolean_1) {
            this.dragging = boolean_1;
        }

        public E getFocused() {
            return this.focused;
        }

        public void setFocused(E element_1) {
            this.focused = element_1;
        }
    }
}
