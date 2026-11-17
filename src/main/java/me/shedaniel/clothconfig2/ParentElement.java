//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2;

import me.shedaniel.clothconfig2.compat.GuiEventListener;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public interface ParentElement extends GuiEventListener {
    List<? extends GuiEventListener> children();

    default Optional<GuiEventListener> hoveredElement(double d, double e) {
        Iterator var5 = this.children().iterator();

        while(var5.hasNext()) {
            GuiEventListener child = (GuiEventListener)var5.next();
            if (child instanceof net.minecraft.client.gui.widget.ButtonWidget) {
                net.minecraft.client.gui.widget.ButtonWidget btn = (net.minecraft.client.gui.widget.ButtonWidget)child;
                if (d >= (double)btn.x && d <= (double)(btn.x + btn.width) && e >= (double)btn.y && e <= (double)(btn.y + 20)) {
                    return Optional.of(child);
                }
            }
        }

        return Optional.empty();
    }

    default boolean mouseClicked(double d, double e, int i) {
        Iterator var6 = this.children().iterator();

        GuiEventListener child;
        do {
            if (!var6.hasNext()) {
                return false;
            }

            child = (GuiEventListener)var6.next();
        } while(!child.mouseClicked(d, e, i));

        this.setFocused(child);
        if (i == 0) {
            this.setDragging(true);
        }

        return true;
    }

    default boolean mouseReleased(double d, double e, int i) {
        this.setDragging(false);
        return this.hoveredElement(d, e).filter((child) -> {
            return child.mouseReleased(d, e, i);
        }).isPresent();
    }

    default boolean mouseDragged(double d, double e, int i, double f, double g) {
        return this.getFocused() != null && this.isDragging() && i == 0 ? this.getFocused().mouseDragged(d, e, i, f, g) : false;
    }

    boolean isDragging();

    void setDragging(boolean var1);

    default boolean mouseScrolled(double d) {
        return this.hoveredElement(d, 0.0).filter((child) -> {
            return child.mouseScrolled(d);
        }).isPresent();
    }

    default boolean keyPressed(int i, int j, int k) {
        return this.getFocused() != null && this.getFocused().keyPressed(i, j, k);
    }

    default boolean keyReleased(int i, int j, int k) {
        return this.getFocused() != null && this.getFocused().keyReleased(i, j, k);
    }

    default boolean charTyped(char c, int i) {
        return this.getFocused() != null && this.getFocused().charTyped(c, i);
    }

    @Nullable GuiEventListener getFocused();

    void setFocused(@Nullable GuiEventListener var1);

    default void setInitialFocus(@Nullable GuiEventListener GuiEventListener) {
        this.setFocused(GuiEventListener);
    }

    default void focusOn(@Nullable GuiEventListener GuiEventListener) {
        this.setFocused(GuiEventListener);
    }

    default void changeFocus(boolean bl) {
        GuiEventListener GuiEventListener = this.getFocused();
        boolean bl2 = GuiEventListener != null;
        if (!bl2) {
            List<? extends GuiEventListener> list = this.children();
            int i = list.indexOf(GuiEventListener);
            int j;
            if (bl2 && i >= 0) {
                j = i + (bl ? 1 : 0);
            } else if (bl) {
                j = 0;
            } else {
                j = list.size();
            }

            ListIterator<? extends GuiEventListener> listIterator = list.listIterator(j);
            BooleanSupplier var10000;
            if (bl) {
                Objects.requireNonNull(listIterator);
                var10000 = listIterator::hasNext;
            } else {
                Objects.requireNonNull(listIterator);
                var10000 = listIterator::hasPrevious;
            }

            BooleanSupplier booleanSupplier = var10000;
            Supplier var11;
            if (bl) {
                Objects.requireNonNull(listIterator);
                var11 = listIterator::next;
            } else {
                Objects.requireNonNull(listIterator);
                var11 = listIterator::previous;
            }

            Supplier<? extends GuiEventListener> supplier = var11;
            if (booleanSupplier.getAsBoolean()) {
                GuiEventListener child = (GuiEventListener)supplier.get();
                this.setFocused(child);
            } else {
                this.setFocused((GuiEventListener)null);
            }
        }
    }
}
