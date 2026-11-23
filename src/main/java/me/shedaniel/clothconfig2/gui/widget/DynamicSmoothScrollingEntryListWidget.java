//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.widget;

import me.shedaniel.clothconfig2.api.RunSixtyTimesEverySec;
import me.shedaniel.clothconfig2.compat.GuiVertexCompat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;

import java.util.Iterator;

/** @deprecated */
@Deprecated
public abstract class DynamicSmoothScrollingEntryListWidget<E extends DynamicEntryListWidget.Entry<E>> extends DynamicEntryListWidget<E> {
    protected double scrollVelocity;
    protected boolean smoothScrolling = true;
    protected RunSixtyTimesEverySec scroller = () -> {
        if (this.scrollVelocity == 0.0 && this.scroll >= 0.0 && this.scroll <= (double)this.getMaxScroll()) {
            this.scrollerUnregisterTick();
        } else {
            double change = this.scrollVelocity * 0.3;
            if (this.scrollVelocity != 0.0) {
                this.scroll += change;
                this.scrollVelocity -= this.scrollVelocity * (this.scroll >= 0.0 && this.scroll <= (double)this.getMaxScroll() ? 0.2 : 0.4);
                if (Math.abs(this.scrollVelocity) < 0.1) {
                    this.scrollVelocity = 0.0;
                }
            }

            if (this.scroll < 0.0 && this.scrollVelocity == 0.0) {
                this.scroll = Math.min(this.scroll + (0.0 - this.scroll) * 0.2, 0.0);
                if (this.scroll > -0.1 && this.scroll < 0.0) {
                    this.scroll = 0.0;
                }
            } else if (this.scroll > (double)this.getMaxScroll() && this.scrollVelocity == 0.0) {
                this.scroll = Math.max(this.scroll - (this.scroll - (double)this.getMaxScroll()) * 0.2, (double)this.getMaxScroll());
                if (this.scroll > (double)this.getMaxScroll() && this.scroll < (double)this.getMaxScroll() + 0.1) {
                    this.scroll = (double)this.getMaxScroll();
                }
            }
        }

    };

    public DynamicSmoothScrollingEntryListWidget(Minecraft client, int width, int height, int top, int bottom, String backgroundLocation) {
        super(client, width, height, top, bottom, backgroundLocation);
    }

    protected void scrollerUnregisterTick() {
        this.scroller.unregisterTick();
    }

    public double getScrollVelocity() {
        return this.scrollVelocity;
    }

    public void setScrollVelocity(double scrollVelocity) {
        this.scrollVelocity = scrollVelocity;
    }

    public boolean isSmoothScrolling() {
        return this.smoothScrolling;
    }

    public void setSmoothScrolling(boolean smoothScrolling) {
        this.smoothScrolling = smoothScrolling;
    }

    public void capYPosition(double double_1) {
        if (this.smoothScrolling) {
            this.scroll = double_1;
        } else {
            this.scroll = (double)Math.max(0, Math.min(this.getMaxScroll(), (int)double_1));
        }

    }

    public boolean mouseDragged(double double_1, double double_2, int int_1, double double_3, double double_4) {
        if (!this.smoothScrolling) {
            return super.mouseDragged(double_1, double_2, int_1, double_3, double_4);
        } else if (this.getFocused() != null && int_1 == 0 && this.getFocused().mouseDragged(double_1, double_2, int_1, double_3, double_4)) {
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
                this.capYPosition((double)Math.max(0, Math.min(this.getMaxScroll(), (int)(this.getScroll() + double_4 * double_6))));
            }

            return true;
        } else {
            return false;
        }
    }

    protected void scroll(int int_1) {
        super.scroll(int_1);
        this.scrollVelocity = 0.0;
    }

    public boolean mouseScrolled(double double_1) {
        Iterator var3 = this.children().iterator();

        Entry entry;
        do {
            if (!var3.hasNext()) {
                if (!this.smoothScrolling) {
                    this.scrollVelocity = 0.0;
                    this.scroll += 16.0 * -double_1;
                    this.scroll = (double)Math.max(0, Math.min(this.getMaxScroll(), (int)double_1));
                    return true;
                }

                if (this.scroll <= (double)this.getMaxScroll() && double_1 < 0.0) {
                    this.scrollVelocity += 16.0 * -double_1;
                }

                if (this.scroll >= 0.0 && double_1 > 0.0) {
                    this.scrollVelocity += 16.0 * -double_1;
                }

                if (!this.scroller.isRegistered()) {
                    this.scroller.registerTick();
                }

                return true;
            }

            entry = (Entry)var3.next();
        } while(!entry.mouseScrolled(double_1));

        return true;
    }

    protected void renderScrollBar(Tessellator Tesselator, GuiVertexCompat.Builder buffer, int maxScroll, int scrollbarPositionMinX, int scrollbarPositionMaxX) {
        if (!this.smoothScrolling) {
            super.renderScrollBar(Tesselator, buffer, maxScroll, scrollbarPositionMinX, scrollbarPositionMaxX);
        } else if (maxScroll > 0) {
            int height = (this.bottom - this.top) * (this.bottom - this.top) / this.getMaxScrollPosition();
            height = Math.max(32, Math.min(this.bottom - this.top - 8, height));
            height = (int)((double)height - Math.min((double)(this.scroll < 0.0 ? (int)(-this.scroll) : (this.scroll > (double)this.getMaxScroll() ? (int)this.scroll - this.getMaxScroll() : 0)), (double)height * 0.75));
            int minY = Math.min(Math.max((int)this.getScroll() * (this.bottom - this.top - height) / maxScroll + this.top, this.top), this.bottom - height);
            buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
            buffer.vertex((double)scrollbarPositionMinX, (double)this.bottom, 0.0).texture(0.0, 1.0).color(0, 0, 0, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMaxX, (double)this.bottom, 0.0).texture(1.0, 1.0).color(0, 0, 0, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMaxX, (double)this.top, 0.0).texture(1.0, 0.0).color(0, 0, 0, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMinX, (double)this.top, 0.0).texture(0.0, 0.0).color(0, 0, 0, 255).nextVertex();
            Tessellator.INSTANCE.draw();
            buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
            buffer.vertex((double)scrollbarPositionMinX, (double)(minY + height), 0.0).texture(0.0, 1.0).color(128, 128, 128, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMaxX, (double)(minY + height), 0.0).texture(1.0, 1.0).color(128, 128, 128, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMaxX, (double)minY, 0.0).texture(1.0, 0.0).color(128, 128, 128, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMinX, (double)minY, 0.0).texture(0.0, 0.0).color(128, 128, 128, 255).nextVertex();
            Tessellator.INSTANCE.draw();
            buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
            buffer.vertex((double)scrollbarPositionMinX, (double)(minY + height - 1), 0.0).texture(0.0, 1.0).color(192, 192, 192, 255).nextVertex();
            buffer.vertex((double)(scrollbarPositionMaxX - 1), (double)(minY + height - 1), 0.0).texture(1.0, 1.0).color(192, 192, 192, 255).nextVertex();
            buffer.vertex((double)(scrollbarPositionMaxX - 1), (double)minY, 0.0).texture(1.0, 0.0).color(192, 192, 192, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMinX, (double)minY, 0.0).texture(0.0, 0.0).color(192, 192, 192, 255).nextVertex();
            Tessellator.INSTANCE.draw();
        }

    }
}
