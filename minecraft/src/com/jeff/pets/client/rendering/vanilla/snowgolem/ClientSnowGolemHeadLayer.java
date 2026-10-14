package com.jeff.pets.client.rendering.vanilla.snowgolem;

import net.minecraft.src.*;
import com.jeff.pets.client.rendering.PetLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

/**
 * Draws the carved pumpkin on the snow golem's head. 1.7.10 has no {@code LayerRenderer},
 * no {@code ItemCameraTransforms} and no {@code ItemStack#EMPTY}; vanilla 1.7.10's
 * {@code RenderSnowMan#renderEquippedItems} renders the pumpkin as an item through
 * {@code ItemRenderer#renderItem} guarded by {@code RenderBlocks#renderItemIn3d}.
 */
public class ClientSnowGolemHeadLayer implements PetLayer {
    private final PetRenderer parent;

    public ClientSnowGolemHeadLayer(PetRenderer parent) {
        this.parent = parent;
    }

    @Override
    public void render(EntityLiving snowGolem, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        if (!Objects.equals(CONFIG.snowGolemSkin, "pumpkin_on")) {
            return;
        }
        if (!(this.parent.getMainModel() instanceof ModelSnowMan)) {
            return;
        }

        GL11.glPushMatrix();
        ((ModelSnowMan) this.parent.getMainModel()).head.postRender(0.0625F);

        ItemStack pumpkin = new ItemStack(Block.pumpkin, 1);
        if (RenderBlocks.renderItemIn3d(Block.blocksList[pumpkin.getItem().itemID].getRenderType())) {
            float pumpkinScale = 0.625F;
            GL11.glTranslatef(0.0F, -0.34375F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(pumpkinScale, -pumpkinScale, pumpkinScale);
        }

        this.parent.getRenderManager().itemRenderer.renderItem(snowGolem, pumpkin, 0);
        GL11.glPopMatrix();
    }
}
