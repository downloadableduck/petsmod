package com.jeff.pets.client.rendering.custom.aprilfools.head;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;

import java.net.Proxy;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

import static com.jeff.pets.client.Central.CONFIG;

public class HeadRenderer extends PetRenderer {

    private final Map<String, Identifier> PROFILLES = new ConcurrentHashMap<>();

    public HeadRenderer(final net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new HeadModel(), 0.3F);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        Identifier profile = PROFILLES.get(CONFIG.headSkin);
        if (profile == null) {
            profile = ClientPlayerEntity.getSkinId(CONFIG.headSkin);
        }
        return profile;
    }
}
