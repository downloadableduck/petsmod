package com.jeff.pets.client.rendering.vanilla.slime;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSlime;
import net.minecraft.client.render.entity.feature.SlimeOverlayFeatureRenderer;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSlimeRenderer extends PetRenderer<@NotNull ClientSlime, @NotNull SlimeEntityModel<ClientSlime>> {

    public ClientSlimeRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new SlimeEntityModel<>(16), 0.75f);
        this.addFeature(new SlimeOverlayFeatureRenderer<>(this));
    }

    @Override
    protected void scale(ClientSlime slimeRenderState, float f) {
        int slimeScale;
        switch (CONFIG.slimeSkin) {
            case "small":
                slimeScale = 1;
                break;
            case "medium":
                slimeScale = 2;
                break;
            case "large":
                slimeScale = 4;
                break;
            default:
                slimeScale = 1;
                break;
        }
        com.mojang.blaze3d.platform.GlStateManager.scalef(slimeScale, slimeScale, slimeScale);
    }

    @Override
    public @NotNull Identifier getTexture(ClientSlime livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/slime/slime.png");
    }
}
