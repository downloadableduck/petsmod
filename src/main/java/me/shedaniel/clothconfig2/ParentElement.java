package me.shedaniel.clothconfig2;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import me.shedaniel.clothconfig2.gui.widget.DynamicEntryListWidget;
import net.minecraft.class_4122;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.jetbrains.annotations.Nullable;

public interface ParentElement extends class_4122 {
    List<? extends class_4122> children();

    default Optional<class_4122> hoveredElement(double d, double e) {
        for (class_4122 element : this.children()) {
            if (isElementAt(element, d, e)) {
                return Optional.of(element);
            }
        }

        return Optional.empty();
    }

    static boolean isElementAt(class_4122 element, double d, double e) {
        if (element instanceof ButtonWidget) {
            return ((ButtonWidget) element).isHovered();
        } else if (element instanceof DynamicEntryListWidget.Entry) {
            return ((DynamicEntryListWidget.Entry) element).isMouseOver(d, e);
        }
        return false;
    }

    default boolean mouseClicked(double d, double e, int i) {
        for (class_4122 element : this.children()) {
            if (element.mouseClicked(d, e, i)) {
                this.setFocused(element);
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
        return this.hoveredElement(d, e).filter((element) -> element.mouseReleased(d, e, i)).isPresent();
    }

    default boolean mouseDragged(double d, double e, int i, double f, double g) {
        return this.getFocused() != null && this.isDragging() && i == 0 ? this.getFocused().mouseDragged(d, e, i, f, g) : false;
    }

    boolean isDragging();

    void setDragging(boolean bl);

    default boolean mouseScrolled(double f) {
        return this.getFocused() != null && this.getFocused().mouseScrolled(f);
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
    class_4122 getFocused();

    void setFocused(@Nullable class_4122 element);

    default void setInitialFocus(@Nullable class_4122 element) {
        this.setFocused(element);
    }

    default void focusOn(@Nullable class_4122 element) {
        this.setFocused(element);
    }

    default boolean changeFocus(boolean bl) {
        class_4122 element = this.getFocused();
        boolean bl2 = element != null;
        if (bl2) {
            return true;
        } else {
            List<? extends class_4122> list = this.children();
            int i = list.indexOf(element);
            int j;
            if (bl2 && i >= 0) {
                j = i + (bl ? 1 : 0);
            } else if (bl) {
                j = 0;
            } else {
                j = list.size();
            }

            ListIterator<? extends class_4122> listIterator = list.listIterator(j);
            BooleanSupplier booleanSupplier = bl ? listIterator::hasNext : listIterator::hasPrevious;
            Supplier<? extends class_4122> supplier = bl ? listIterator::next : listIterator::previous;

            while (booleanSupplier.getAsBoolean()) {
                class_4122 element2 = (class_4122) supplier.get();
            }

            this.setFocused((class_4122) null);
            return false;
        }
    }
}