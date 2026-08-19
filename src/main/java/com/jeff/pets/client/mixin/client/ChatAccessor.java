package com.jeff.pets.mixin.client;

import com.jeff.pets.Central;
import net.minecraft.client.gui.components.CommandSuggestions;
import net.minecraft.client.gui.screens.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Accesses the {@link ChatScreen#commandSuggestions} field for use in {@link Central#refreshChatSuggestor}
 */
@Mixin(ChatScreen.class)
public interface ChatAccessor {
    @Accessor("commandSuggestions")
    CommandSuggestions getChatInputSuggestor();
}