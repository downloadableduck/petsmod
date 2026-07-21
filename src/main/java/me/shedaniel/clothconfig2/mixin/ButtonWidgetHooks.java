package me.shedaniel.clothconfig2.mixin;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.LockButtonWidget;
import net.minecraft.unmapped.C_0818095;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(C_0818095.class)
@ApiStatus.Internal
public interface ButtonWidgetHooks {
    @Accessor("onPress")
    void setOnPress(C_0818095.C_6298894 action);
}