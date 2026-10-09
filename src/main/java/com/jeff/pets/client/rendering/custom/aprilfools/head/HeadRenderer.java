package com.jeff.pets.client.rendering.custom.aprilfools.head;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;
import net.minecraft.client.entity.living.player.ClientPlayerEntity;
import net.minecraft.client.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.jeff.pets.client.Central.CONFIG;

public class HeadRenderer extends PetRenderer<@NotNull Head> {

    /** 1.7.10 has no DefaultSkinUtils and no Alex skin; Steve is the only fallback. */
    private static final Identifier DEFAULT_SKIN = new Identifier("textures/entity/steve.png");

    private final Map<String, Identifier> PROFILLES = new ConcurrentHashMap<>();

    public HeadRenderer(final net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new HeadModel(), 0.3F);
    }

    private Identifier fetchGameProfile(Head head, String name) {
        Identifier identifier = ClientPlayerEntity.getHeadTextureLocation(name);
        ClientPlayerEntity.loadSkinTexture(identifier, name);
        return identifier;
    }

    @Override
    public @NotNull Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        Head state = (Head) entity;
        Identifier identifier = PROFILLES.get(CONFIG.headSkin);
        if (identifier == null) {
            identifier = fetchGameProfile(state, CONFIG.headSkin);
        }
        return identifier;
    }
}
