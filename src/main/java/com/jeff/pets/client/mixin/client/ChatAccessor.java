package com.jeff.pets.client.mixin.client;

import net.minecraft.client.gui.GuiChat;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = GuiChat.class)
public class ChatAccessor {
}
