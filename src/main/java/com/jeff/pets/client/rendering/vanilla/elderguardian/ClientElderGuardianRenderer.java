package com.jeff.pets.client.rendering.vanilla.elderguardian;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianModel;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ClientElderGuardianRenderer extends PetRenderer {

    public ClientElderGuardianRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ClientGuardianModel(), 0.75f);
    }

    @Override
    public void preRenderCallback( final EntityLivingBase elderGuardian, float f) {
        GL11.glScalef(2.35f, 2.35f, 2.35f);

    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        //livingEntityRenderState.spike = 1;
        return new ResourceLocation("minecraft", "textures/entity/guardian_elder.png");
    }
}

