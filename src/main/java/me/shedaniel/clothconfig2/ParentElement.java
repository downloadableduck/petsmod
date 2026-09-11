package me.shedaniel.clothconfig2;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import me.shedaniel.clothconfig2.compat.GuiEventListener;
import org.jetbrains.annotations.Nullable;

public interface ParentElement extends GuiEventListener {
    List<? extends GuiEventListener> children();

    default Optional<GuiEventListener> hoveredElement(double d, double e) {
        for(GuiEventListener child : this.children()) {
            if (child instanceof net.minecraft.client.gui.widget.ButtonWidget) {
                net.minecraft.client.gui.widget.ButtonWidget btn = (net.minecraft.client.gui.widget.ButtonWidget) child;
                if (d >= btn.x && d <= btn.x + btn.getWidth() && e >= btn.y && e <= btn.y + 20) {
                    return Optional.of(child);
                }
            }
        }

        return Optional.empty();
    }

    default boolean mouseClicked(double d, double e, int i) {
        for(GuiEventListener child : this.children()) {
            if (child.mouseClicked(d, e, i)) {
                this.setFocused(child);
                if (i == 0) {
                    this.setDragging(true);
                }

                return true;
            }
        }

        return false;
    }

    default boolean mouseReleased(double d, double e, int i) {
        this.setDragging(false);
        return this.hoveredElement(d, e).filter((child) -> child.mouseReleased(d, e, i)).isPresent();
    }

    default boolean mouseDragged(double d, double e, int i, double f, double g) {
        return this.getFocused() != null && this.isDragging() && i == 0 ? this.getFocused().mouseDragged(d, e, i, f, g) : false;
    }

    boolean isDragging();

    void setDragging(boolean bl);

    default boolean mouseScrolled(double d) {
        return this.hoveredElement(d, 0).filter((child) -> child.mouseScrolled(d)).isPresent();
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

    @Nullable
    GuiEventListener getFocused();

    void setFocused(@Nullable GuiEventListener GuiEventListener);

    default void setInitialFocus(@Nullable GuiEventListener GuiEventListener) {
        this.setFocused(GuiEventListener);
    }

    default void focusOn(@Nullable GuiEventListener GuiEventListener) {
        this.setFocused(GuiEventListener);
    }

    default void changeFocus(boolean bl) {
        GuiEventListener GuiEventListener = this.getFocused();
        boolean bl2 = GuiEventListener != null;
        if (bl2) {
            return;
        } else {
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
            BooleanSupplier booleanSupplier = bl ? listIterator::hasNext : listIterator::hasPrevious;
            Supplier<? extends GuiEventListener> supplier = bl ? listIterator::next : listIterator::previous;

            while(booleanSupplier.getAsBoolean()) {
                GuiEventListener child = (GuiEventListener)supplier.get();
                this.setFocused(child);
                return;
            }

            this.setFocused((GuiEventListener)null);
        }
    }
}
