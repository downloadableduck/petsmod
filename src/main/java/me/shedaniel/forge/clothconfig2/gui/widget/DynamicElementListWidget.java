package me.shedaniel.forge.clothconfig2.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.util.ResourceLocation;



public abstract class DynamicElementListWidget<E extends DynamicElementListWidget.ElementEntry<E>> extends DynamicNewSmoothScrollingEntryListWidget<E> {
    
    public DynamicElementListWidget(Minecraft client, int width, int height, int top, int bottom, ResourceLocation backgroundLocation) {
        super(client, width, height, top, bottom, backgroundLocation);
    }
    
    /*public boolean changeFocus(boolean boolean_1) {
        boolean boolean_2 = super.func(boolean_1);
        if (boolean_2)
            this.ensureVisible(this.getFocused());
        return boolean_2;
    }*/
    
    protected boolean isSelected(int int_1) {
        return false;
    }
    
    
    public abstract static class ElementEntry<E extends ElementEntry<E>> extends Entry<E> implements IGuiEventListener {
        private IGuiEventListener focused;
        private boolean dragging;
        
        public ElementEntry() {
        }
        
        public boolean func_195071_s() {
            return this.dragging;
        }
        
        public void func_195072_d(boolean boolean_1) {
            this.dragging = boolean_1;
        }
        
        public IGuiEventListener getFocused() {
            return this.focused;
        }
        
        public void setFocused(IGuiEventListener element_1) {
            this.focused = element_1;
        }
    }
}

