package com.jeff.pets.client.rendering.vanilla.magmacube;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientMagmaCube;
import net.minecraft.client.render.model.entity.SlimeModel;
import net.minecraft.client.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMagmaCubeRenderer extends PetRenderer<ClientMagmaCube> {

    public ClientMagmaCubeRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SlimeModel(0), 0.75f);
    }

    @Override
    protected void applyScale(net.minecraft.entity.living.LivingEntity entity, float a) {
        ClientMagmaCube slimeRenderState = (ClientMagmaCube) entity;
        int magmaCubeapplyScale;
        switch (CONFIG.magmaCubeSkin) {
            case "small":
                magmaCubeapplyScale = 1;
                break;
            case "medium":
                magmaCubeapplyScale = 2;
                break;
            case "large":
                magmaCubeapplyScale = 4;
                break;
            default:
                magmaCubeapplyScale = 1;
                break;
        }
        com.jeff.pets.compat.GlStateManager.scalef(magmaCubeapplyScale, magmaCubeapplyScale, magmaCubeapplyScale);
    }

    @Override
    public @NotNull Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        ClientMagmaCube livingEntityRenderState = (ClientMagmaCube) entity;
        return new Identifier("minecraft", "textures/entity/slime/magmacube.png");
    }
}
