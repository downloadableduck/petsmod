//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.widget;

import me.shedaniel.clothconfig2.ClothConfigInitializer;
import me.shedaniel.clothconfig2.compat.GuiVertexCompat;
import me.shedaniel.clothconfig2.compat.MouseInput;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;

import java.util.Iterator;

public abstract class DynamicNewSmoothScrollingEntryListWidget<E extends DynamicEntryListWidget.Entry<E>> extends DynamicEntryListWidget<E> {
    protected double target;
    protected boolean smoothScrolling = true;
    protected long start;
    protected long duration;

    public DynamicNewSmoothScrollingEntryListWidget(Minecraft client, int width, int height, int top, int bottom, String backgroundLocation) {
        super(client, width, height, top, bottom, backgroundLocation);
    }

    public boolean isSmoothScrolling() {
        return this.smoothScrolling;
    }

    public void setSmoothScrolling(boolean smoothScrolling) {
        this.smoothScrolling = smoothScrolling;
    }

    public void capYPosition(double double_1) {
        if (!this.smoothScrolling) {
            this.scroll = (double)Math.max(0, Math.min(this.getMaxScroll(), (int)double_1));
        } else {
            this.scroll = ClothConfigInitializer.clamp(double_1, (double)this.getMaxScroll());
            this.target = ClothConfigInitializer.clamp(double_1, (double)this.getMaxScroll());
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

    public boolean mouseScrolled(double double_1) {
        Iterator var3 = this.children().iterator();

        DynamicEntryListWidget.Entry entry;
        do {
            if (!var3.hasNext()) {
                if (!this.smoothScrolling) {
                    this.scroll += 16.0 * -double_1;
                    this.scroll = (double)Math.max(0, Math.min(this.getMaxScroll(), (int)double_1));
                    return true;
                }

                this.offset(ClothConfigInitializer.getScrollStep() * -double_1, true);
                return true;
            }

            entry = (DynamicEntryListWidget.Entry)var3.next();
        } while(!entry.mouseScrolled(double_1));

        return true;
    }

    public void offset(double value, boolean animated) {
        this.scrollTo(this.target + value, animated);
    }

    public void scrollTo(double value, boolean animated) {
        this.scrollTo(value, animated, ClothConfigInitializer.getScrollDuration());
    }

    public void scrollTo(double value, boolean animated, long duration) {
        this.target = ClothConfigInitializer.clamp(value, (double)this.getMaxScroll());
        if (animated) {
            this.start = System.currentTimeMillis();
            this.duration = duration;
        } else {
            this.scroll = this.target;
        }

    }

    public void render(int mouseX, int mouseY, float delta) {
        double[] target = new double[]{this.target};
        this.scroll = ClothConfigInitializer.handleScrollingPosition(target, this.scroll, (double)this.getMaxScroll(), delta, (double)this.start, (double)this.duration);
        this.target = target[0];
        super.render(mouseX, mouseY, delta);
    }

    protected void renderScrollBar(Tessellator Tesselator, GuiVertexCompat.Builder buffer, int maxScroll, int scrollbarPositionMinX, int scrollbarPositionMaxX) {
        if (!this.smoothScrolling) {
            super.renderScrollBar(Tesselator, buffer, maxScroll, scrollbarPositionMinX, scrollbarPositionMaxX);
        } else if (maxScroll > 0) {
            int height = (this.bottom - this.top) * (this.bottom - this.top) / this.getMaxScrollPosition();
            height = Math.max(32, Math.min(this.bottom - this.top - 8, height));
            height = (int)((double)height - Math.min((double)(this.scroll < 0.0 ? (int)(-this.scroll) : (this.scroll > (double)this.getMaxScroll() ? (int)this.scroll - this.getMaxScroll() : 0)), (double)height * 0.95));
            height = Math.max(10, height);
            int minY = Math.min(Math.max((int)this.getScroll() * (this.bottom - this.top - height) / maxScroll + this.top, this.top), this.bottom - height);
            int bottomc = (new Rectangle(scrollbarPositionMinX, minY, scrollbarPositionMaxX - scrollbarPositionMinX, height)).contains(new Point(MouseInput.getX(), MouseInput.getY())) ? 168 : 128;
            int topc = (new Rectangle(scrollbarPositionMinX, minY, scrollbarPositionMaxX - scrollbarPositionMinX, height)).contains(new Point(MouseInput.getX(), MouseInput.getY())) ? 222 : 172;
            buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
            buffer.vertex((double)scrollbarPositionMinX, (double)this.bottom, 0.0).texture(0.0, 1.0).color(0, 0, 0, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMaxX, (double)this.bottom, 0.0).texture(1.0, 1.0).color(0, 0, 0, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMaxX, (double)this.top, 0.0).texture(1.0, 0.0).color(0, 0, 0, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMinX, (double)this.top, 0.0).texture(0.0, 0.0).color(0, 0, 0, 255).nextVertex();
            Tessellator.INSTANCE.draw();
            buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
            buffer.vertex((double)scrollbarPositionMinX, (double)(minY + height), 0.0).texture(0.0, 1.0).color(bottomc, bottomc, bottomc, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMaxX, (double)(minY + height), 0.0).texture(1.0, 1.0).color(bottomc, bottomc, bottomc, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMaxX, (double)minY, 0.0).texture(1.0, 0.0).color(bottomc, bottomc, bottomc, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMinX, (double)minY, 0.0).texture(0.0, 0.0).color(bottomc, bottomc, bottomc, 255).nextVertex();
            Tessellator.INSTANCE.draw();
            buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
            buffer.vertex((double)scrollbarPositionMinX, (double)(minY + height - 1), 0.0).texture(0.0, 1.0).color(topc, topc, topc, 255).nextVertex();
            buffer.vertex((double)(scrollbarPositionMaxX - 1), (double)(minY + height - 1), 0.0).texture(1.0, 1.0).color(topc, topc, topc, 255).nextVertex();
            buffer.vertex((double)(scrollbarPositionMaxX - 1), (double)minY, 0.0).texture(1.0, 0.0).color(topc, topc, topc, 255).nextVertex();
            buffer.vertex((double)scrollbarPositionMinX, (double)minY, 0.0).texture(0.0, 0.0).color(topc, topc, topc, 255).nextVertex();
            Tessellator.INSTANCE.draw();
        }

    }

    public static class Precision {
        public static final float FLOAT_EPSILON = 0.001F;
        public static final double DOUBLE_EPSILON = 1.0E-7;

        public Precision() {
        }

        public static boolean almostEquals(float value1, float value2, float acceptableDifference) {
            return Math.abs(value1 - value2) <= acceptableDifference;
        }

        public static boolean almostEquals(double value1, double value2, double acceptableDifference) {
            return Math.abs(value1 - value2) <= acceptableDifference;
        }
    }

    public static class Interpolation {
        public Interpolation() {
        }

        public static double expoEase(double start, double end, double amount) {
            return start + (end - start) * ClothConfigInitializer.getEasingMethod().apply(amount);
        }
    }
}
