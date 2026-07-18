package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Koi;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class KoiRenderer extends PetRenderer<Koi, KoiModel> {

    public KoiRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new KoiModel(), 0.5f);
    }

    @Override
    public @NotNull Identifier getTexture(@NotNull Koi state) {
        return new Identifier(MOD_ID, "textures/entity/koi/koi.png");
    }
}
