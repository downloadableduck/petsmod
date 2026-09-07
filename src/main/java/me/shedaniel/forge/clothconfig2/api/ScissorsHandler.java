package me.shedaniel.forge.clothconfig2.api;

import me.shedaniel.forge.clothconfig2.impl.ScissorsHandlerImpl;
import me.shedaniel.forge.math.Rectangle;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public interface ScissorsHandler {
    ScissorsHandler INSTANCE = ScissorsHandlerImpl.INSTANCE;

    void clearScissors();

    List<Rectangle> getScissorsAreas();

    void scissor(Rectangle rectangle);

    void removeLastScissor();

    void applyScissors();
}
