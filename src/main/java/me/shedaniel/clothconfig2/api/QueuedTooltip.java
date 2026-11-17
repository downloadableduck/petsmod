//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.api;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;

import java.util.Collections;
import java.util.List;

public class QueuedTooltip {
    private Point location;
    private List<String> text;

    private QueuedTooltip(Point location, List<String> text) {
        this.location = location;
        this.text = Collections.unmodifiableList(text);
    }

    public static QueuedTooltip create(Point location, List<String> text) {
        return new QueuedTooltip(location, text);
    }

    public static QueuedTooltip create(Point location, String... text) {
        return create(location, (List)Lists.newArrayList(text));
    }

    public Point getLocation() {
        return this.location;
    }

    public int getX() {
        return this.getLocation().x;
    }

    public int getY() {
        return this.getLocation().y;
    }

    public List<String> getText() {
        return this.text;
    }
}
