package net.minecraft.client.gui.widget;

import me.shedaniel.clothconfig2.compat.GuiEventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.SliderElement;
import net.minecraft.client.option.Option;

/**
 * Modern slider widget API backed by BTA's SliderElement.
 */
public class SliderWidget extends SliderElement implements GuiEventListener {
    public double progress;
    public String text;
    public boolean active = true;
    public int x;
    public int y;

    public SliderWidget(int id, int x, int y, int width, int height, double value) {
        super(0, x, y, width, height, "cloth.progress", 0.0F);
        this.x = x;
        this.y = y;
        this.progress = value;
        this.sliderValue = value;
        this.displayString = "";
    }

    public SliderWidget(int id, int x, int y, Option<?> option, String text, float value) {
        this(id, x, y, 150, 20, value);
        this.text = text;
    }

    private void sync() {
        this.xPosition = this.x;
        this.yPosition = this.y;
        this.enabled = this.active;
        this.displayString = this.text != null ? this.text : "";
    }

    public void update() {
    }

    @Override
    public void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
        this.sync();
        super.mouseDragged(mc, mouseX, mouseY);
        this.progress = this.sliderValue;
    }

    @Override
    public boolean mouseClicked(Minecraft mc, int mouseX, int mouseY) {
        this.sync();
        return super.mouseClicked(mc, mouseX, mouseY);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        this.sync();
        super.mouseReleased(mouseX, mouseY);
        this.progress = this.sliderValue;
        this.dragging = false;
    }

    public void render(int mouseX, int mouseY, float delta) {
        this.render(Minecraft.getMinecraft(), mouseX, mouseY);
    }

    public void render(Minecraft mc, int mouseX, int mouseY) {
        this.sync();
        super.drawButton(mc, mouseX, mouseY);
    }
}
