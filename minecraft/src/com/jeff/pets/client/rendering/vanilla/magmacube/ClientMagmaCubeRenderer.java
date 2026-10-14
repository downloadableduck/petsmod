package com.jeff.pets.client.rendering.vanilla.magmacube;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelSlime;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMagmaCubeRenderer extends PetRenderer {

    public ClientMagmaCubeRenderer() {
        super(new ModelSlime(0), 0.75f);
    }

    @Override
    public void preRenderCallback( final EntityLiving slimeRenderState, float a) {
        int magmaCubeScale;
        switch (CONFIG.magmaCubeSkin) {
            case "small":
                magmaCubeScale = 1;
                break;
            case "medium":
                magmaCubeScale = 2;
                break;
            case "large":
                magmaCubeScale = 4;
                break;
            default:
                magmaCubeScale = 1;
                break;
        }
        GL11.glScalef(magmaCubeScale, magmaCubeScale, magmaCubeScale);
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/slime/magmacube.png";
    }
}

