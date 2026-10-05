package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import com.jeff.pets.compat.GlStateManager;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.block.BlockRenderer;
import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.client.rendering.PetRenderer;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSnowGolemHeadLayer implements PetRenderLayer {
    private final PetRenderer renderer;
    private final BlockRenderer blockRenderer;

    public ClientSnowGolemHeadLayer(PetRenderer renderer, BlockRenderer BlockRenderer) {
        this.renderer = renderer;
        this.blockRenderer = BlockRenderer;
    }

    @Override
    public void render(net.minecraft.entity.living.LivingEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientSnowGolem snowGolem = (ClientSnowGolem) entity;
        if (CONFIG.snowGolemSkin.equals("pumpkin_on")) {
boolean bl = false;
            if (!snowGolem.isInvisible() || bl) {
                GlStateManager.pushMatrix();
                //this.getContextModel().method_2834().rotate(poseStack);
                float m = 0.625F;
                GlStateManager.translatef(0.0F, -0F, 0.0F);
                //poseStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
                GlStateManager.scalef(0.625F, -0.625F, -0.625F);
                GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
                    this.blockRenderer.renderAsItem(Blocks.PUMPKIN, 0, 1.0F);

                GlStateManager.popMatrix();
            }
        }
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
