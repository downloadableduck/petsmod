package com.jeff.pets.client.rendering.vanilla.chicken;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.MathHelper;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenRenderer extends PetRenderer {

    public ClientChickenRenderer() {
        super(new ClientChickenModel(), 0.3F);
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/chicken.png";
    }

    @Override
    public void preRenderCallback( final EntityLiving state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    protected float handleRotationFloat(EntityLiving livingBase, float partialTicks) {
        float f = (livingBase.ticksExisted + partialTicks) * 0.4F;
        return (MathHelper.sin(f) + 1.0F) * 0.2f;
    }
}

