package me.shedaniel.clothconfig2.mixin;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.LockButtonWidget;
import net.minecraft.unmapped.C_01559903;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(C_01559903.class)
@ApiStatus.Internal
public interface ButtonWidgetHooks {
    @Accessor("f_19589033")
    void setOnPress(C_01559903.C_55920227 action);
}