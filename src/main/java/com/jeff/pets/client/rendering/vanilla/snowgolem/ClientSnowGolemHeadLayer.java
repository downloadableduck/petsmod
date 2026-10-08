package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import org.lwjgl.opengl.GL11;
import net.minecraft.init.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.item.ItemStack;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSnowGolemHeadLayer {
    private final ClientSnowGolemRenderer renderer;

    public ClientSnowGolemHeadLayer(ClientSnowGolemRenderer renderer) {
        this.renderer = renderer;
    }

    public void render(ClientSnowGolem snowGolem, float f, float g, float h, float i, float j, float k, float l) {
        if (CONFIG.snowGolemSkin.equals("pumpkin_on")) {
            if (!snowGolem.isInvisible()) {
                GL11.glPushMatrix();
                ((ModelSnowMan) this.renderer.getMainModel()).head.postRender(0.0625F);
                GL11.glTranslatef(0.0F, -0F, 0.0F);
                GL11.glScalef(0.625F, -0.625F, -0.625F);
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
;
                GL11.glPopMatrix();
            }
        }
    }

    public boolean combineTextures() {
        return false;
    }
}