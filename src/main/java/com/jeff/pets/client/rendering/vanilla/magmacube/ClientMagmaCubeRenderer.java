package com.jeff.pets.client.rendering.vanilla.magmacube;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientMagmaCube;
import net.minecraft.client.render.model.entity.SlimeModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMagmaCubeRenderer extends PetRenderer<@NotNull ClientMagmaCube, @NotNull SlimeModel<ClientMagmaCube>> {

    public ClientMagmaCubeRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SlimeModel<>(0), 0.75f);
    }

    @Override
    protected void applyScale(ClientMagmaCube slimeRenderState, float a) {
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
        com.mojang.blaze3d.platform.GlStateManager.scale(magmaCubeapplyScale, magmaCubeapplyScale, magmaCubeapplyScale);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientMagmaCube livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/slime/magmacube.png");
    }
}
