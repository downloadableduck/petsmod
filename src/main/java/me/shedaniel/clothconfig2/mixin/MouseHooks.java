package me.shedaniel.clothconfig2.mixin;

import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MouseHandler.class)
public interface MouseHooks {
    @Accessor("isMiddlePressed")
    boolean middleButtonClicked();
}
