package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMooshroomRenderer extends PetRenderer {

    String mooshroomTexturePath;

    public ClientMooshroomRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ClientCowModel(), 0.7F);
        this.setPetLayer(new ClientMushroomCowMushroomLayer(this));
    }

    @Override
    public void preRenderCallback( final EntityLivingBase state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture( final Entity cowRenderState) {
        mooshroomTexturePath = "textures/entity/cow/mooshroom.png";
        return new ResourceLocation("minecraft", mooshroomTexturePath);
    }
}

