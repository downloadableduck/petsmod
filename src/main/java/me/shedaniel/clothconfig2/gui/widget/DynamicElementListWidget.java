package me.shedaniel.clothconfig2.gui.widget;

import me.shedaniel.clothconfig2.ParentElement;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiEventListener;
import net.minecraft.resource.Identifier;

@Environment(EnvType.CLIENT)
public abstract class DynamicElementListWidget<E extends DynamicElementListWidget.ElementEntry<E>> extends DynamicNewSmoothScrollingEntryListWidget<E> {
    
    public DynamicElementListWidget(Minecraft client, int width, int height, int top, int bottom, Identifier backgroundLocation) {
        super(client, width, height, top, bottom, backgroundLocation);
    }

    public void changeFocus(boolean boolean_1) {
        // In 1.13, changeFocus returns void
        // boolean boolean_2 = false;//super.changeFocus(boolean_1);
        // if (boolean_2)
        //     this.ensureVisible(this.getFocused());
    }
    
    protected boolean isSelected(int int_1) {
        return false;
    }
    
    @Environment(EnvType.CLIENT)
    public abstract static class ElementEntry<E extends ElementEntry<E>> extends Entry<E> implements ParentElement {
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

