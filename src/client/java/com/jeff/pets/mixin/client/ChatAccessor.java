package com.jeff.pets.mixin.client;

import net.minecraft.client.gui.components.CommandSuggestions;
import net.minecraft.client.gui.screens.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChatScreen.class)
public interface ChatAccessor {
    @Accessor("commandSuggestions")
    CommandSuggestions getChatInputSuggestor();
}