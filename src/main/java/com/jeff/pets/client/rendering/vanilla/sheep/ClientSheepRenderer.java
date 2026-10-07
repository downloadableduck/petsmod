package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSheepRenderer extends PetRenderer {

    public ClientSheepRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ClientSheepModel(), 0.7F);
        this.setPetLayer(new ClientSheepWoolLayer(this));
    }

    @Override
    public void preRenderCallback( final EntityLivingBase livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/sheep/sheep.png");
    }

    /*@Override
    public void extractRenderState(Sheep sheep, SheepRenderState state, float f) {
        super.extractRenderState(sheep, state, f);
        sheep.sheared = false;
    }*/
}

