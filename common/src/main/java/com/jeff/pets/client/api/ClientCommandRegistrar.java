package com.jeff.pets.client.api;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;

@FunctionalInterface
public interface ClientCommandRegistrar {
    CommandDispatcher<ClientSuggestionProvider> getDispatcher();
}
