package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenRenderer extends PetRenderer {

    public ClientChickenRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ClientChickenModel(), 0.3F);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/chicken.png");
    }

    @Override
    public void preRenderCallback( final EntityLivingBase state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    protected float handleRotationFloat(EntityLivingBase livingBase, float partialTicks) {
        float f = (livingBase.ticksExisted + partialTicks) * 0.4F;
        return (MathHelper.sin(f) + 1.0F) * 0.2f;
    }
}

