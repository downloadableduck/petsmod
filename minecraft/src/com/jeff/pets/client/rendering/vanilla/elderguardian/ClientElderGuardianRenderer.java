package com.jeff.pets.client.rendering.vanilla.elderguardian;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianModel;
import org.lwjgl.opengl.GL11;

public class ClientElderGuardianRenderer extends PetRenderer {

    public ClientElderGuardianRenderer() {
        super(new ClientGuardianModel(), 0.75f);
    }

    @Override
    public void preRenderCallback( final EntityLiving elderGuardian, float f) {
        GL11.glScalef(2.35f, 2.35f, 2.35f);

    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        //livingEntityRenderState.spike = 1;
        return "textures/entity/guardian_elder.png";
    }
}

