//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.widget;

import com.google.common.collect.Lists;
import com.jeff.pets.compat.GlStateManager;
import me.shedaniel.clothconfig2.api.ScissorsHandler;
import me.shedaniel.clothconfig2.compat.AbstractContainerEventHandler;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import me.shedaniel.clothconfig2.compat.GuiVertexCompat;
import me.shedaniel.clothconfig2.compat.GuiVertexCompat.Builder;
import me.shedaniel.math.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.Tessellator;
import net.minecraft.util.math.MathHelper;

import java.util.*;

public abstract class DynamicEntryListWidget<E extends DynamicEntryListWidget.Entry<E>> extends AbstractContainerEventHandler {
    protected static final int DRAG_OUTSIDE = -2;
    protected final Minecraft client;
    private final List<E> entries = new Entries();
    public int width;
    public int height;
    public int top;
    public int bottom;
    public int right;
    public int left;
    protected boolean verticallyCenter = true;
    protected int yDrag = -2;
    protected boolean visible = true;
    protected boolean renderSelection;
    protected int headerHeight;
    protected double scroll;
    protected boolean scrolling;
    protected E selectedItem;
    protected String backgroundLocation;

    public DynamicEntryListWidget(Minecraft client, int width, int height, int top, int bottom, String backgroundLocation) {
        this.client = client;
        this.width = width;
        this.height = height;
        this.top = top;
        this.bottom = bottom;
        this.left = 0;
        this.right = width;
        this.backgroundLocation = backgroundLocation;
    }

    public void setRenderSelection(boolean boolean_1) {
        this.visible = boolean_1;
    }

    protected void setRenderHeader(boolean boolean_1, int headerHeight) {
        this.renderSelection = boolean_1;
        this.headerHeight = headerHeight;
        if (!boolean_1) {
            this.headerHeight = 0;
        }

    }

    public int getItemWidth() {
        return 220;
    }

    public E getSelectedItem() {
        return this.selectedItem;
    }

    public void selectItem(E item) {
        this.selectedItem = item;
    }

    public E getFocused() {
        return (E)super.getFocused();
    }

    public final List<E> children() {
        return this.entries;
    }

    protected final void clearItems() {
        this.entries.clear();
    }

    protected E getItem(int index) {
        return (E)this.children().get(index);
    }

    protected int addItem(E item) {
        this.entries.add(item);
        return this.entries.size() - 1;
    }

    protected int getItemCount() {
        return this.children().size();
    }

    protected boolean isSelected(int index) {
        return Objects.equals(this.getSelectedItem(), this.children().get(index));
    }

    protected final E getItemAtPosition(double mouseX, double mouseY) {
        int listMiddleX = this.left + this.width / 2;
        int minX = listMiddleX - this.getItemWidth() / 2;
        int maxX = listMiddleX + this.getItemWidth() / 2;
        int currentY = MathHelper.floor(mouseY - (double)this.top) - this.headerHeight + (int)this.getScroll() - 4;
        int itemY = 0;
        int itemIndex = -1;

        for(int i = 0; i < this.entries.size(); ++i) {
            E item = this.getItem(i);
            itemY += item.getItemHeight();
            if (itemY > currentY) {
                itemIndex = i;
                break;
            }
        }

        return mouseX < (double)this.getScrollbarPosition() && mouseX >= (double)minX && mouseX <= (double)maxX && itemIndex >= 0 && currentY >= 0 && itemIndex < this.getItemCount() ? (E)this.children().get(itemIndex) : null;
    }

    public void updateSize(int width, int height, int top, int bottom) {
        this.width = width;
        this.height = height;
        this.top = top;
        this.bottom = bottom;
        this.left = 0;
        this.right = width;
    }

    public void setLeftPos(int left) {
        this.left = left;
        this.right = left + this.width;
    }

    protected int getMaxScrollPosition() {
        List<Integer> list = new ArrayList();
        int i = this.headerHeight;
        Iterator var3 = this.entries.iterator();

        while(var3.hasNext()) {
            E entry = (E)var3.next();
            i += entry.getItemHeight();
            if (entry.getMorePossibleHeight() >= 0) {
                list.add(i + entry.getMorePossibleHeight());
            }
        }

        list.add(i);
        return (Integer)list.stream().max(Integer::compare).orElse(0);
    }

    protected void clickedHeader(int int_1, int int_2) {
    }

    protected void renderHeader(int int_1, int int_2, Tessellator Tesselator) {
    }

    protected void drawBackground() {
    }

    protected void renderDecorations(int int_1, int int_2) {
    }

    /** @deprecated */
    @Deprecated
    protected void renderBackBackground(Builder buffer, Tessellator Tesselator) {
        this.client.textureManager.getTextureId(this.backgroundLocation);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        float float_2 = 32.0F;
        buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
        buffer.vertex((double)this.left, (double)this.bottom, 0.0).texture((double)((float)this.left / 32.0F), (double)((float)(this.bottom + (int)this.getScroll()) / 32.0F)).color(32, 32, 32, 255).nextVertex();
        buffer.vertex((double)this.right, (double)this.bottom, 0.0).texture((double)((float)this.right / 32.0F), (double)((float)(this.bottom + (int)this.getScroll()) / 32.0F)).color(32, 32, 32, 255).nextVertex();
        buffer.vertex((double)this.right, (double)this.top, 0.0).texture((double)((float)this.right / 32.0F), (double)((float)(this.top + (int)this.getScroll()) / 32.0F)).color(32, 32, 32, 255).nextVertex();
        buffer.vertex((double)this.left, (double)this.top, 0.0).texture((double)((float)this.left / 32.0F), (double)((float)(this.top + (int)this.getScroll()) / 32.0F)).color(32, 32, 32, 255).nextVertex();
        Tessellator.INSTANCE.draw();
    }

    public void render(int mouseX, int mouseY, float delta) {
        this.drawBackground();
        int scrollbarPosition = this.getScrollbarPosition();
        int int_4 = scrollbarPosition + 6;
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        Tessellator tesselator = Tessellator.INSTANCE;
        Builder buffer = Builder.of(tesselator);
        this.renderBackBackground(buffer, tesselator);
        int rowLeft = this.getRowLeft();
        int startY = this.top + 4 - (int)this.getScroll();
        if (this.renderSelection) {
            this.renderHeader(rowLeft, startY, tesselator);
        }

        ScissorsHandler.INSTANCE.scissor(new Rectangle(this.left, this.top, this.width, this.bottom - this.top));
        this.renderList(rowLeft, startY, mouseX, mouseY, delta);
        ScissorsHandler.INSTANCE.removeLastScissor();
        this.renderHoleBackground(0, this.top, 255, 255);
        this.renderHoleBackground(this.bottom, this.height, 255, 255);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.shadeModel(7425);
        buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
        buffer.vertex((double)this.left, (double)(this.top + 4), 0.0).texture(0.0, 1.0).color(0, 0, 0, 0).nextVertex();
        buffer.vertex((double)this.right, (double)(this.top + 4), 0.0).texture(1.0, 1.0).color(0, 0, 0, 0).nextVertex();
        buffer.vertex((double)this.right, (double)this.top, 0.0).texture(1.0, 0.0).color(0, 0, 0, 255).nextVertex();
        buffer.vertex((double)this.left, (double)this.top, 0.0).texture(0.0, 0.0).color(0, 0, 0, 255).nextVertex();
        tesselator.draw();
        buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
        buffer.vertex((double)this.left, (double)this.bottom, 0.0).texture(0.0, 1.0).color(0, 0, 0, 255).nextVertex();
        buffer.vertex((double)this.right, (double)this.bottom, 0.0).texture(1.0, 1.0).color(0, 0, 0, 255).nextVertex();
        buffer.vertex((double)this.right, (double)(this.bottom - 4), 0.0).texture(1.0, 0.0).color(0, 0, 0, 0).nextVertex();
        buffer.vertex((double)this.left, (double)(this.bottom - 4), 0.0).texture(0.0, 0.0).color(0, 0, 0, 0).nextVertex();
        tesselator.draw();
        int maxScroll = this.getMaxScroll();
        this.renderScrollBar(tesselator, buffer, maxScroll, scrollbarPosition, int_4);
        this.renderDecorations(mouseX, mouseY);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
    }

    protected void renderScrollBar(Tessellator Tesselator, Builder buffer, int maxScroll, int scrollbarPositionMinX, int scrollbarPositionMaxX) {
        if (maxScroll > 0) {
            int int_9 = (this.bottom - this.top) * (this.bottom - this.top) / this.getMaxScrollPosition();
            int_9 = Math.max(32, Math.min(this.bottom - this.top - 8, int_9));
            int int_10 = (int)this.getScroll() * (this.bottom - this.top - int_9) / maxScroll + this.top;
            if (int_10 < this.top) {
                int_10 = this.top;
            }

            buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
            buffer.vertex((double)scrollbarPositionMinX, (double)this.bottom, 0.0).texture(0.0, 1.0).color(0, 0, 0, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMaxX, (double)this.bottom, 0.0).texture(1.0, 1.0).color(0, 0, 0, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMaxX, (double)this.top, 0.0).texture(1.0, 0.0).color(0, 0, 0, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMinX, (double)this.top, 0.0).texture(0.0, 0.0).color(0, 0, 0, 255).nextVertex();
            Tessellator.INSTANCE.draw();
            buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
            buffer.vertex((double)scrollbarPositionMinX, (double)(int_10 + int_9), 0.0).texture(0.0, 1.0).color(128, 128, 128, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMaxX, (double)(int_10 + int_9), 0.0).texture(1.0, 1.0).color(128, 128, 128, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMaxX, (double)int_10, 0.0).texture(1.0, 0.0).color(128, 128, 128, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMinX, (double)int_10, 0.0).texture(0.0, 0.0).color(128, 128, 128, 255).nextVertex();
            Tessellator.INSTANCE.draw();
            buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
            buffer.vertex((double)scrollbarPositionMinX, (double)(int_10 + int_9 - 1), 0.0).texture(0.0, 1.0).color(192, 192, 192, 255).nextVertex();
            buffer.vertex((double)(scrollbarPositionMaxX - 1), (double)(int_10 + int_9 - 1), 0.0).texture(1.0, 1.0).color(192, 192, 192, 255).nextVertex();
            buffer.vertex((double)(scrollbarPositionMaxX - 1), (double)int_10, 0.0).texture(1.0, 0.0).color(192, 192, 192, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMinX, (double)int_10, 0.0).texture(0.0, 0.0).color(192, 192, 192, 255).nextVertex();
            Tessellator.INSTANCE.draw();
        }

    }

    protected void centerScrollOn(E item) {
        double d = (double)(this.bottom - this.top) / -2.0;

        for(int i = 0; i < this.children().indexOf(item) && i < this.getItemCount(); ++i) {
            d += (double)this.getItem(i).getItemHeight();
        }

        this.capYPosition(d);
    }

    protected void ensureVisible(E item) {
        int rowTop = this.getRowTop(this.children().indexOf(item));
        int int_2 = rowTop - this.top - 4 - item.getItemHeight();
        if (int_2 < 0) {
            this.scroll(int_2);
        }

        int int_3 = this.bottom - rowTop - item.getItemHeight() * 2;
        if (int_3 < 0) {
            this.scroll(-int_3);
        }

    }

    protected void scroll(int int_1) {
        this.capYPosition(this.getScroll() + (double)int_1);
        this.yDrag = -2;
    }

    public double getScroll() {
        return this.scroll;
    }

    public void capYPosition(double double_1) {
        this.scroll = (double)Math.max(0, Math.min(this.getMaxScroll(), (int)double_1));
    }

    protected int getMaxScroll() {
        return Math.max(0, this.getMaxScrollPosition() - (this.bottom - this.top - 4));
    }

    public int getScrollBottom() {
        return (int)this.getScroll() - this.height - this.headerHeight;
    }

    protected void updateScrollingState(double double_1, double double_2, int int_1) {
        this.scrolling = int_1 == 0 && double_1 >= (double)this.getScrollbarPosition() && double_1 < (double)(this.getScrollbarPosition() + 6);
    }

    protected int getScrollbarPosition() {
        return this.width / 2 + 124;
    }

    public boolean mouseClicked(double double_1, double double_2, int int_1) {
        this.updateScrollingState(double_1, double_2, int_1);
        if (!this.isMouseOver(double_1, double_2)) {
            return false;
        } else {
            E item = this.getItemAtPosition(double_1, double_2);
            if (item != null) {
                if (item.mouseClicked(double_1, double_2, int_1)) {
                    this.setFocused(item);
                    this.setDragging(true);
                    return true;
                }
            } else if (int_1 == 0) {
                this.clickedHeader((int)(double_1 - (double)(this.left + this.width / 2 - this.getItemWidth() / 2)), (int)(double_2 - (double)this.top) + (int)this.getScroll() - 4);
                return true;
            }

            return this.scrolling;
        }
    }

    public boolean mouseReleased(double double_1, double double_2, int int_1) {
        if (this.getFocused() != null) {
            this.getFocused().mouseReleased(double_1, double_2, int_1);
        }

        return false;
    }

    public boolean mouseDragged(double double_1, double double_2, int int_1, double double_3, double double_4) {
        if (super.mouseDragged(double_1, double_2, int_1, double_3, double_4)) {
            return true;
        } else if (int_1 == 0 && this.scrolling) {
            if (double_2 < (double)this.top) {
                this.capYPosition(0.0);
            } else if (double_2 > (double)this.bottom) {
                this.capYPosition((double)this.getMaxScroll());
            } else {
                double double_5 = (double)Math.max(1, this.getMaxScroll());
                int int_2 = this.bottom - this.top;
                int int_3 = Math.max(32, Math.min(int_2 - 8, (int)((float)(int_2 * int_2) / (float)this.getMaxScrollPosition())));
                double double_6 = Math.max(1.0, double_5 / (double)(int_2 - int_3));
                this.capYPosition(this.getScroll() + double_4 * double_6);
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean mouseScrolled(double double_1) {
        Iterator var3 = this.entries.iterator();

        Entry entry;
        do {
            if (!var3.hasNext()) {
                this.capYPosition(this.getScroll() - double_1 * (double)(this.getMaxScroll() / this.getItemCount()) / 2.0);
                return true;
            }

            entry = (Entry)var3.next();
        } while(!entry.mouseScrolled(double_1));

        return true;
    }

    public boolean keyPressed(int int_1, int int_2, int int_3) {
        if (super.keyPressed(int_1, int_2, int_3)) {
            return true;
        } else if (int_1 == 264) {
            this.moveSelection(1);
            return true;
        } else if (int_1 == 265) {
            this.moveSelection(-1);
            return true;
        } else {
            return false;
        }
    }

    protected void moveSelection(int int_1) {
        if (!this.children().isEmpty()) {
            int int_2 = this.children().indexOf(this.getSelectedItem());
            int int_3 = Math.max(0, Math.min(this.getItemCount() - 1, int_2 + int_1));
            E itemListWidget$Item_1 = (E)this.children().get(int_3);
            this.selectItem(itemListWidget$Item_1);
            this.ensureVisible(itemListWidget$Item_1);
        }

    }

    public boolean isMouseOver(double double_1, double double_2) {
        return double_2 >= (double)this.top && double_2 <= (double)this.bottom && double_1 >= (double)this.left && double_1 <= (double)this.right;
    }

    protected void renderList(int startX, int startY, int int_3, int int_4, float float_1) {
        int itemCount = this.getItemCount();
        Tessellator tesselator = Tessellator.INSTANCE;
        Builder buffer = Builder.of(tesselator);

        for(int renderIndex = 0; renderIndex < itemCount; ++renderIndex) {
            E item = this.getItem(renderIndex);
            int itemY = startY + this.headerHeight;

            int itemHeight;
            for(itemHeight = 0; itemHeight < this.entries.size() && itemHeight < renderIndex; ++itemHeight) {
                itemY += ((Entry)this.entries.get(itemHeight)).getItemHeight();
            }

            itemHeight = item.getItemHeight() - 4;
            int itemWidth = this.getItemWidth();
            if (this.visible && this.isSelected(renderIndex)) {
                int itemMinX = this.left + this.width / 2 - itemWidth / 2;
                int itemMaxX = itemMinX + itemWidth;
                float float_2 = this.isFocused() ? 1.0F : 0.5F;
                GlStateManager.color4f(float_2, float_2, float_2, 1.0F);
                buffer.begin(7, GuiVertexCompat.POSITION);
                buffer.vertex((double)itemMinX, (double)(itemY + itemHeight + 2), 0.0).nextVertex();
                buffer.vertex((double)itemMaxX, (double)(itemY + itemHeight + 2), 0.0).nextVertex();
                buffer.vertex((double)itemMaxX, (double)(itemY - 2), 0.0).nextVertex();
                buffer.vertex((double)itemMinX, (double)(itemY - 2), 0.0).nextVertex();
                tesselator.draw();
                GlStateManager.color4f(0.0F, 0.0F, 0.0F, 1.0F);
                buffer.begin(7, GuiVertexCompat.POSITION);
                buffer.vertex((double)(itemMinX + 1), (double)(itemY + itemHeight + 1), 0.0).nextVertex();
                buffer.vertex((double)(itemMaxX - 1), (double)(itemY + itemHeight + 1), 0.0).nextVertex();
                buffer.vertex((double)(itemMaxX - 1), (double)(itemY - 1), 0.0).nextVertex();
                buffer.vertex((double)(itemMinX + 1), (double)(itemY - 1), 0.0).nextVertex();
                tesselator.draw();
            }

            int y = this.getRowTop(renderIndex);
            int x = this.getRowLeft();
            this.renderItem(item, renderIndex, y, x, itemWidth, itemHeight, int_3, int_4, this.isMouseOver((double)int_3, (double)int_4) && Objects.equals(this.getItemAtPosition((double)int_3, (double)int_4), item), float_1);
        }

    }

    protected void renderItem(E item, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        item.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
    }

    protected int getRowLeft() {
        return this.left + this.width / 2 - this.getItemWidth() / 2 + 2;
    }

    protected int getRowTop(int index) {
        int integer = this.top + 4 - (int)this.getScroll() + this.headerHeight;

        for(int i = 0; i < this.entries.size() && i < index; ++i) {
            integer += ((Entry)this.entries.get(i)).getItemHeight();
        }

        return integer;
    }

    protected boolean isFocused() {
        return false;
    }

    protected void renderHoleBackground(int int_1, int int_2, int int_3, int int_4) {
        Tessellator tesselator = Tessellator.INSTANCE;
        Builder buffer = Builder.of(tesselator);
        this.client.textureManager.getTextureId(this.backgroundLocation);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        float float_1 = 32.0F;
        buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
        buffer.vertex((double)this.left, (double)int_2, 0.0).texture(0.0, (double)((float)int_2 / 32.0F)).color(64, 64, 64, int_4).nextVertex();
        buffer.vertex((double)(this.left + this.width), (double)int_2, 0.0).texture((double)((float)this.width / 32.0F), (double)((float)int_2 / 32.0F)).color(64, 64, 64, int_4).nextVertex();
        buffer.vertex((double)(this.left + this.width), (double)int_1, 0.0).texture((double)((float)this.width / 32.0F), (double)((float)int_1 / 32.0F)).color(64, 64, 64, int_3).nextVertex();
        buffer.vertex((double)this.left, (double)int_1, 0.0).texture(0.0, (double)((float)int_1 / 32.0F)).color(64, 64, 64, int_3).nextVertex();
        tesselator.draw();
    }

    protected E remove(int int_1) {
        E itemListWidget$Item_1 = (E)this.entries.get(int_1);
        return this.removeEntry((E)this.entries.get(int_1)) ? itemListWidget$Item_1 : null;
    }

    protected boolean removeEntry(E itemListWidget$Item_1) {
        boolean boolean_1 = this.entries.remove(itemListWidget$Item_1);
        if (boolean_1 && itemListWidget$Item_1 == this.getSelectedItem()) {
            this.selectItem((E)null);
        }

        return boolean_1;
    }

    class Entries extends AbstractList<E> {
        private final ArrayList<E> items;

        private Entries() {
            this.items = Lists.newArrayList();
        }

        public void clear() {
            this.items.clear();
        }

        public E get(int int_1) {
            return (E)this.items.get(int_1);
        }

        public int size() {
            return this.items.size();
        }

        public E set(int int_1, E itemListWidget$Item_1) {
            E itemListWidget$Item_2 = (E)this.items.set(int_1, itemListWidget$Item_1);
            itemListWidget$Item_1.parent = DynamicEntryListWidget.this;
            return itemListWidget$Item_2;
        }

        public void add(int int_1, E itemListWidget$Item_1) {
            this.items.add(int_1, itemListWidget$Item_1);
            itemListWidget$Item_1.parent = DynamicEntryListWidget.this;
        }

        public E remove(int int_1) {
            return (E)this.items.remove(int_1);
        }
    }

    public abstract static class Entry<E extends Entry<E>> extends DrawContext implements GuiEventListener {
        /** @deprecated */
        @Deprecated
        DynamicEntryListWidget<E> parent;

        public Entry() {
        }

        public abstract void render(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, float var9);

        public boolean isMouseOver(double double_1, double double_2) {
            return Objects.equals(this.parent.getItemAtPosition(double_1, double_2), this);
        }

        public DynamicEntryListWidget<E> getParent() {
            return this.parent;
        }

        public void setParent(DynamicEntryListWidget<E> parent) {
            this.parent = parent;
        }

        public abstract int getItemHeight();

        /** @deprecated */
        @Deprecated
        public int getMorePossibleHeight() {
            return -1;
        }
    }

    public static final class SmoothScrollingSettings {
        public static final double CLAMP_EXTENSION = 200.0;

        private SmoothScrollingSettings() {
        }
    }
}
