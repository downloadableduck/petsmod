package com.jeff.pets.client.rendering.vanilla.magmacube;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientMagmaCube;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMagmaCubeRenderer extends PetRenderer<@NotNull ClientMagmaCube, @NotNull SlimeEntityModel<ClientMagmaCube>> {

    public ClientMagmaCubeRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new SlimeEntityModel<>(0), 0.75f);
    }

    @Override
    protected void scale(ClientMagmaCube slimeRenderState, float a) {
        int magmaCubeScale;
        switch (CONFIG.magmaCubeSkin) {
            case "small":
                magmaCubeScale = 1;
                break;
            case "medium":
                magmaCubeScale = 2;
                break;
            case "large":
                magmaCubeScale = 4;
                break;
            default:
                magmaCubeScale = 1;
                break;
        }
        com.mojang.blaze3d.platform.GlStateManager.scalef(magmaCubeScale, magmaCubeScale, magmaCubeScale);
    }

    @Override
    public @NotNull Identifier getTexture(ClientMagmaCube livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/slime/magmacube.png");
    }
}
