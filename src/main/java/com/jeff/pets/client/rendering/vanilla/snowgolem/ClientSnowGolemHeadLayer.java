package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.SnowmanEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.item.ItemStack;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSnowGolemHeadLayer implements FeatureRenderer<ClientSnowGolem> {
    private final ClientSnowGolemRenderer renderer;

    public ClientSnowGolemHeadLayer(ClientSnowGolemRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void render(ClientSnowGolem snowGolem, float f, float g, float h, float i, float j, float k, float l) {
        if (CONFIG.snowGolemSkin.equals("pumpkin_on")) {
            boolean bl = snowGolem.isGlowing() && snowGolem.isInvisible();
            if (!snowGolem.isInvisible() || bl) {
                com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
                ((SnowmanEntityModel) this.renderer.getModel()).method_18942().preRender(0.0625F);
                GlStateManager.translate(0.0F, -0F, 0.0F);
                com.mojang.blaze3d.platform.GlStateManager.scale(0.625F, -0.625F, -0.625F);
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                MinecraftClient.getInstance().method_18201().method_19139(snowGolem, new ItemStack(Blocks.CARVED_PUMPKIN), ModelTransformation.Mode.HEAD);
                com.mojang.blaze3d.platform.GlStateManager.popMatrix();
            }
        }
    }

    @Override
    public boolean combineTextures() {
        return false;
    }
}