//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.aprilfools.head;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HeadRenderer extends PetRenderer<@NotNull Head> {
    private static final String DEFAULT_SKIN = "textures/mob/steve.png";
    private final Map<String, String> PROFILLES = new ConcurrentHashMap();

    public HeadRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new HeadModel(), 0.3F);
    }

    public @NotNull String getTexture(Entity entity) {
        return Central.CONFIG.headSkin;
    }
}
