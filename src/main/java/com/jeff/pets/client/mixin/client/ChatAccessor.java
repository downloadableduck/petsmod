package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Accesses the {@link ChatScreen#commandSuggestions} field for use in {@link Central#refreshChatSuggestor}
 */
@Mixin(value = ChatScreen.class)
public class ChatAccessor {
    //@Accessor(value = "commandSuggestions", remap = true)
}