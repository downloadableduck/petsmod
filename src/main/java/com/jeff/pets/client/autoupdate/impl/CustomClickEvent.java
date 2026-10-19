package com.jeff.pets.client.autoupdate.impl;

import net.minecraft.network.chat.ClickEvent;
import org.jetbrains.annotations.NotNull;

public record CustomClickEvent(Runnable runnable) implements ClickEvent {

    @Override
    public @NotNull Action action() {
        return Action.CUSTOM;
    }

    public void run() {
        this.runnable.run();
    }
}
