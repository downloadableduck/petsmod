package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.client.rendering.PetLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
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

    public ClientSnowGolemHeadLayer(RenderManager context, PetRenderer parent) {
        this.parent = parent;
    }

    @Override
    public void render(EntityLivingBase snowGolem, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        if (!Objects.equals(CONFIG.snowGolemSkin, "pumpkin_on")) {
            return;
        }
        if (!(this.parent.getMainModel() instanceof ModelSnowMan)) {
            return;
        }

        GL11.glPushMatrix();
        ((ModelSnowMan) this.parent.getMainModel()).head.postRender(0.0625F);

        ItemStack pumpkin = new ItemStack(Blocks.pumpkin, 1);
        if (RenderBlocks.renderItemIn3d(Block.getBlockFromItem(pumpkin.getItem()).getRenderType())) {
            float pumpkinScale = 0.625F;
            GL11.glTranslatef(0.0F, -0.34375F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(pumpkinScale, -pumpkinScale, pumpkinScale);
        }

        this.parent.getRenderManager().itemRenderer.renderItem(snowGolem, pumpkin, 0);
        GL11.glPopMatrix();
    }
}
