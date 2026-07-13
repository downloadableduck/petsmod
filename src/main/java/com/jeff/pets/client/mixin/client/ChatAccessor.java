package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.client.gui.CommandSuggestionHelper;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Accesses the {@link ChatScreen#commandSuggestions} field for use in {@link Central#refreshChatSuggestor}
 */
@Mixin(value = ChatScreen.class)
public interface ChatAccessor {
    @Accessor("commandSuggestions")
    CommandSuggestionHelper getChatInputSuggestor();
}