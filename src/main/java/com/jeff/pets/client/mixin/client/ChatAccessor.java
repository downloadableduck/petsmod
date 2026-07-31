package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.client.gui.components.CommandSuggestions;
import net.minecraft.client.gui.screens.ChatScreen;

import java.lang.reflect.Field;

/**
 * Accesses the {@link ChatScreen#commandSuggestions} field for use in {@link Central#refreshChatSuggestor}
 */
public interface ChatAccessor {
    public static CommandSuggestions getChatInputSuggestor(ChatScreen chatScreen) {
        try {
            Field field = ChatScreen.class.getDeclaredField("commandSuggestions");
            field.setAccessible(true);
            return (CommandSuggestions) field.get(chatScreen);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}