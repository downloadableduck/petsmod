package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.mob.vanilla.passive.ClientSheep;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSheepWoolLayer extends LayerRenderer<ClientSheep, ClientSheepModel> {
    private final EntityModel<ClientSheep> model;
    int woolColor;

    public ClientSheepWoolLayer(IEntityRenderer<ClientSheep, ClientSheepModel> renderLayerParent) {
        super(renderLayerParent);
        this.model = new ClientSheepFurModel();
    }

    @Override
    public void render(com.mojang.blaze3d.matrix.MatrixStack poseStack, net.minecraft.client.renderer.IRenderTypeBuffer source, int i, ClientSheep sheepRenderState, float f, float a, float h, float j, float k, float l) {
        if (Objects.equals(CONFIG.sheepSkin, "white")) {
            woolColor = 15132390;
        } else if (Objects.equals(CONFIG.sheepSkin, "orange")) {
            woolColor = 12214293;
        } else if (Objects.equals(CONFIG.sheepSkin, "magenta")) {
            woolColor = 9779853;
        } else if (Objects.equals(CONFIG.sheepSkin, "light_blue")) {
            woolColor = 2852515;
        } else if (Objects.equals(CONFIG.sheepSkin, "yellow")) {
            woolColor = 12493357;
        } else if (Objects.equals(CONFIG.sheepSkin, "lime")) {
            woolColor = 6329623;
        } else if (Objects.equals(CONFIG.sheepSkin, "pink")) {
            woolColor = 11954303;
        } else if (Objects.equals(CONFIG.sheepSkin, "gray")) {
            woolColor = 3488573;
        } else if (Objects.equals(CONFIG.sheepSkin, "light_gray")) {
            woolColor = 7697777;
        } else if (Objects.equals(CONFIG.sheepSkin, "cyan")) {
            woolColor = 1078645;
        } else if (Objects.equals(CONFIG.sheepSkin, "purple")) {
            woolColor = 6694282;
        } else if (Objects.equals(CONFIG.sheepSkin, "blue")) {
            woolColor = 2962303;
        } else if (Objects.equals(CONFIG.sheepSkin, "brown")) {
            woolColor = 6438693;
        } else if (Objects.equals(CONFIG.sheepSkin, "green")) {
            woolColor = 4611344;
        } else if (Objects.equals(CONFIG.sheepSkin, "red")) {
            woolColor = 8659484;
        } else if (Objects.equals(CONFIG.sheepSkin, "black")) {
            woolColor = 1381656;
        } else {
            woolColor = 1381656;
        }

        float r = (float) (woolColor >> 16 & 255) / 255.0F;
        float g = (float) (woolColor >> 8 & 255) / 255.0F;
        float b = (float) (woolColor & 255) / 255.0F;

        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, new ResourceLocation("minecraft", "textures/entity/sheep/sheep_fur.png"), poseStack, source, i, sheepRenderState, f, a, j, k, l, h, r, g, b);
    }
}
