package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import net.minecraft.client.gui.ingame.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Accesses the {@link ChatScreen#commandSuggestor} field for use in {@link Central#refreshChatSuggestor}
 */
@Mixin(ChatScreen.class)
public interface ChatAccessor {
    //@Accessor("commandSuggestor")
    //CommandSuggestor getChatInputSuggestor();
}