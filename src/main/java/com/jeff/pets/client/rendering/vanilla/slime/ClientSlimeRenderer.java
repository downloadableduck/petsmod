package com.jeff.pets.client.rendering.vanilla.slime;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSlime;
import net.minecraft.client.render.entity.layer.SlimeOuterLayer;
import net.minecraft.client.render.model.entity.SlimeModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSlimeRenderer extends PetRenderer<@NotNull ClientSlime, @NotNull SlimeModel<ClientSlime>> {

    public ClientSlimeRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SlimeModel<>(16), 0.75f);
        this.addLayer(new SlimeOuterLayer<>(this));
    }

    @Override
    protected void applyScale(ClientSlime slimeRenderState, float f) {
        int slimeapplyScale;
        switch (CONFIG.slimeSkin) {
            case "small":
                slimeapplyScale = 1;
                break;
            case "medium":
                slimeapplyScale = 2;
                break;
            case "large":
                slimeapplyScale = 4;
                break;
            default:
                slimeapplyScale = 1;
                break;
        }
        com.mojang.blaze3d.platform.GlStateManager.scalef(slimeapplyScale, slimeapplyScale, slimeapplyScale);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientSlime livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/slime/slime.png");
    }
}
