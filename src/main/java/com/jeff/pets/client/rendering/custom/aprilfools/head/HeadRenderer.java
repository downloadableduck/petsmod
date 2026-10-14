package com.jeff.pets.client.rendering.custom.aprilfools.head;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.jeff.pets.client.Central.CONFIG;

public class HeadRenderer extends PetRenderer<@NotNull Head> {

    /** 1.7.10 has no DefaultSkinUtils and no Alex skin; Steve is the only fallback. */
    private static final String DEFAULT_SKIN = ("textures/mob/steve.png");

    private final Map<String, String> PROFILLES = new ConcurrentHashMap<>();

    public HeadRenderer(final net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new HeadModel(), 0.3F);
    }

    /*private String fetchGameProfile(Head head, String name) {
        String identifier = ClientPlayerEntity.getHeadTextureLocation(name);
        ClientPlayerEntity.loadSkinTexture(identifier, name);
        return identifier;
    }*/

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        return CONFIG.headSkin;
    }
}
