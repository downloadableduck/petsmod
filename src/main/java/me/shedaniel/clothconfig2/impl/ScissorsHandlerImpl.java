package me.shedaniel.clothconfig2.impl;

import com.google.common.collect.Lists;
import net.minecraft.client.render.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.platform.Window;
import me.shedaniel.clothconfig2.ClothConfigInitializer;
import net.minecraft.client.render.platform.Window;
import me.shedaniel.clothconfig2.api.ScissorsHandler;
import net.minecraft.client.render.platform.Window;
import me.shedaniel.math.Rectangle;
import net.minecraft.client.render.platform.Window;
import net.fabricmc.api.EnvType;
import net.minecraft.client.render.platform.Window;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.platform.Window;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.platform.Window;
import org.jetbrains.annotations.NotNull;
import net.minecraft.client.render.platform.Window;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.render.platform.Window;

import java.util.Collections;
import net.minecraft.client.render.platform.Window;
import java.util.List;
import net.minecraft.client.render.platform.Window;
import java.util.concurrent.Executor;
import net.minecraft.client.render.platform.Window;

@Environment(EnvType.CLIENT)
public final class ScissorsHandlerImpl implements ScissorsHandler {
    
    @Deprecated public static final ScissorsHandler INSTANCE = new ScissorsHandlerImpl();
    
    private final List<Rectangle> scissorsAreas;
    
    public ScissorsHandlerImpl() {
        this.scissorsAreas = Lists.newArrayList();
    }
    
    @Override
    public void clearScissors() {
        scissorsAreas.clear();
        applyScissors();
    }
    
    @Override
    public List<Rectangle> getScissorsAreas() {
        return Collections.unmodifiableList(scissorsAreas);
    }
    
    @Override
    public void scissor(Rectangle rectangle) {
        scissorsAreas.add(rectangle);
        applyScissors();
    }
    
    @Override
    public void removeLastScissor() {
        if (!scissorsAreas.isEmpty())
            scissorsAreas.remove(scissorsAreas.size() - 1);
        applyScissors();
    }
    
    @Override
    public void applyScissors() {
        if (!scissorsAreas.isEmpty()) {
            Rectangle r = scissorsAreas.get(0).clone();
            scissorsAreas.stream().skip(1L).forEach(rectangle -> r.setBounds(r.intersects(rectangle) ? r.intersection(rectangle) : new Rectangle()));
            Window window = Minecraft.getInstance().window;
            double scaleFactor = window.getGuiScale();
            GL11.glEnable(GL11.GL_SCISSOR_TEST);
            GL11.glScissor((int) (r.x * scaleFactor), (int) ((window.getGuiScaledHeight() - r.height - r.y) * scaleFactor), (int) (r.width * scaleFactor), (int) (r.height * scaleFactor));
        } else
            GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }
}
