package me.shedaniel.clothconfig2.mixin;

import me.shedaniel.clothconfig2.ButtonWidget;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface ButtonWidgetHooks {
    void setOnPress(ButtonWidget.PressAction action);
}