package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;

/**
 * 1.13.2 has no {@code CommandSuggestor} on {@link ChatScreen}, so this accessor is
 * intentionally empty - it exists so {@link Central#refreshChatSuggestor} can reference it.
 */
@Mixin(ChatScreen.class)
public interface ChatAccessor {
}