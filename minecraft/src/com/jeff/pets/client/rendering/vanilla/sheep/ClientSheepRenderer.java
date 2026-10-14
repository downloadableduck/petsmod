package com.jeff.pets.client.rendering.vanilla.sheep;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSheepRenderer extends PetRenderer {

    public ClientSheepRenderer() {
        super(new ClientSheepModel(), 0.7F);
        this.setPetLayer(new ClientSheepWoolLayer(this));
    }

    @Override
    public void preRenderCallback( final EntityLiving livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/sheep/sheep.png";
    }

    /*@Override
    public void extractRenderState(Sheep sheep, SheepRenderState state, float f) {
        super.extractRenderState(sheep, state, f);
        sheep.sheared = false;
    }*/
}

