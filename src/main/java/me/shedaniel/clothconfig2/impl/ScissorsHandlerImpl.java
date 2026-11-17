//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl;

import com.google.common.collect.Lists;
import me.shedaniel.clothconfig2.api.ScissorsHandler;
import me.shedaniel.math.Rectangle;
import org.lwjgl.opengl.GL11;

import java.util.Collections;
import java.util.List;

public final class ScissorsHandlerImpl implements ScissorsHandler {
    /** @deprecated */
    @Deprecated
    public static final ScissorsHandler INSTANCE = new ScissorsHandlerImpl();
    private final List<Rectangle> scissorsAreas = Lists.newArrayList();

    public ScissorsHandlerImpl() {
    }

    public void clearScissors() {
        this.scissorsAreas.clear();
        this.applyScissors();
    }

    public List<Rectangle> getScissorsAreas() {
        return Collections.unmodifiableList(this.scissorsAreas);
    }

    public void scissor(Rectangle rectangle) {
        this.scissorsAreas.add(rectangle);
        this.applyScissors();
    }

    public void removeLastScissor() {
        if (!this.scissorsAreas.isEmpty()) {
            this.scissorsAreas.remove(this.scissorsAreas.size() - 1);
        }

        this.applyScissors();
    }

    public void applyScissors() {
        if (!this.scissorsAreas.isEmpty()) {
            Rectangle r = ((Rectangle)this.scissorsAreas.get(0)).clone();
            this.scissorsAreas.stream().skip(1L).forEach((rectangle) -> {
                r.setBounds(r.intersects(rectangle) ? r.intersection(rectangle) : new Rectangle());
            });
            double scaleFactor = (double) WindowUtil.getGuiScale();
            GL11.glEnable(3089);
            GL11.glScissor((int)((double)r.x * scaleFactor), (int)((double)(WindowUtil.getScaledHeight() - r.height - r.y) * scaleFactor), (int)((double)r.width * scaleFactor), (int)((double)r.height * scaleFactor));
        } else {
            GL11.glDisable(3089);
        }

    }
}
