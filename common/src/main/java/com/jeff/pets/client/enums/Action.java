package com.jeff.pets.client.enums;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public enum Action implements EnumImpl {
    Off(() -> false),
    Right_Click(() -> {
        if (shouldReturn()) return false;
        return Minecraft.getInstance().mouseHandler.isRightPressed() && Objects.requireNonNull(Minecraft.getInstance().player).getItemInHand(InteractionHand.MAIN_HAND).isEmpty();
    }),
    Right_Click_and_Jump(() -> {
        if (shouldReturn()) return false;
        return Right_Click.isDown() && !Objects.requireNonNull(Minecraft.getInstance().player).onGround();
    }),
    Right_click_and_Sneak(() -> {
        if (shouldReturn()) return false;
        return Right_Click.isDown() && Objects.requireNonNull(Minecraft.getInstance().player).isShiftKeyDown();
    });
    private final Supplier<Boolean> bl;

    Action(Supplier<Boolean> bl) {
        this.bl = bl;
    }

    public static boolean shouldReturn() {
        return Minecraft.getInstance().player == null;
    }

    public boolean isDown() {
        return bl.get();
    }

    @Override
    public List<Enum<?>> getAllValues() {
        return List.of(Action.values());
    }
}
