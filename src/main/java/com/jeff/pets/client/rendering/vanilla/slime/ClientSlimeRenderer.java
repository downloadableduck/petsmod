package com.jeff.pets.client.rendering.vanilla.slime;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSlime;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSlimeRenderer extends PetRenderer<ClientSlime, ModelSlime> {

    public ClientSlimeRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSlime(16), 0.75f);
        this.addLayer(new LayerSlimeGel(this));
    }

    @Override
    public void preRenderCallback(ClientSlime slimeRenderState, float f) {
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
        net.minecraft.client.renderer.GlStateManager.scalef(slimeScale, slimeScale, slimeScale);

    }

    @Override
    public ResourceLocation getEntityTexture(ClientSlime livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/slime/slime.png");
    }
}
