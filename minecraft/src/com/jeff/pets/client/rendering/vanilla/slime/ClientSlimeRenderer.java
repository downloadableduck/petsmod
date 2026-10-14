package com.jeff.pets.client.rendering.vanilla.slime;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelSlime;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSlimeRenderer extends PetRenderer {

    public ClientSlimeRenderer() {
        super(new ModelSlime(16), 0.75f);
        this.setPetLayer(new LayerSlimeGel(this));
    }

    @Override
    public void preRenderCallback( final EntityLiving slimeRenderState, float f) {
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
        GL11.glScalef(slimeScale, slimeScale, slimeScale);

    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/slime/slime.png";
    }
}

