package com.jeff.pets.client.rendering.vanilla.donkey;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.vanilla.passive.ClientDonkey;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class DonkeyModel extends PetModel<ClientDonkey> {
    private final ModelPart head;
    private final ModelPart mouth;
    private final ModelPart left_ear;
    private final ModelPart right_ear;
    private final ModelPart neck;
    private final ModelPart mane;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart front_left_leg;
    private final ModelPart front_right_leg;
    private final ModelPart back_left_leg;
    private final ModelPart back_right_leg;
    private final ModelPart left_chest;
    private final ModelPart right_chest;

    public DonkeyModel() {
        texWidth = 64;
        texHeight = 64;

        head = new ModelPart(this);
        head.setPos(0.0F, 2.0F, -9.0F);
        head.texOffs(0, 13).addBox(-3.0F, -11.0F, -2.0F, 6.0F, 5.0F, 7.0F, 0.0F, false);

        mouth = new ModelPart(this);
        mouth.setPos(0.0F, 2.0F, -9.0F);
        mouth.texOffs(0, 25).addBox(-2.0F, -11.0F, -7.0F, 4.0F, 5.0F, 5.0F, 0.0F, false);

        left_ear = new ModelPart(this);
        left_ear.setPos(1.5F, -7.9F, -5.0F);
        left_ear.texOffs(0, 12).addBox(-1.0F, -7.1F, 0.0F, 2.0F, 7.0F, 1.0F, 0.0F, false);

        right_ear = new ModelPart(this);
        right_ear.setPos(-1.5F, -7.9F, -5.0F);
        right_ear.texOffs(0, 12).addBox(-1.0F, -7.1F, 0.0F, 2.0F, 7.0F, 1.0F, 0.0F, false);

        neck = new ModelPart(this);
        neck.setPos(0.0F, 2.0F, -9.0F);
        neck.texOffs(0, 35).addBox(-2.05F, -6.0F, -2.0F, 4.0F, 12.0F, 7.0F, 0.0F, false);

        mane = new ModelPart(this);
        mane.setPos(0.0F, 2.0F, -9.01F);
        mane.texOffs(56, 36).addBox(-1.0F, -11.0F, 5.01F, 2.0F, 16.0F, 2.0F, 0.0F, false);

        body = new ModelPart(this);
        body.setPos(0.0F, 11.0F, 6.0F);
        body.texOffs(0, 32).addBox(-5.0F, -8.0F, -17.0F, 10.0F, 10.0F, 22.0F, 0.05F, false);

        tail = new ModelPart(this);
        tail.setPos(0.0F, 3.0F, 11.0F);
        tail.texOffs(42, 36).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 14.0F, 4.0F, 0.0F, false);

        front_left_leg = new ModelPart(this);
        front_left_leg.setPos(4.0F, 14.0F, -9.0F);
        front_left_leg.texOffs(48, 21).addBox(-3.0F, -1.0F, -1.9F, 4.0F, 11.0F, 4.0F, 0.0F, true);

        front_right_leg = new ModelPart(this);
        front_right_leg.setPos(-4.0F, 14.0F, -9.0F);
        front_right_leg.texOffs(48, 21).addBox(-1.0F, -1.0F, -1.9F, 4.0F, 11.0F, 4.0F, 0.0F, false);

        back_left_leg = new ModelPart(this);
        back_left_leg.setPos(4.0F, 14.0F, 8.0F);
        back_left_leg.texOffs(48, 21).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 11.0F, 4.0F, 0.0F, true);

        back_right_leg = new ModelPart(this);
        back_right_leg.setPos(-4.0F, 14.0F, 8.0F);
        back_right_leg.texOffs(48, 21).addBox(-1.0F, -1.0F, -1.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);

        left_chest = new ModelPart(this);
        left_chest.setPos(6.0F, 3.0F, 6.0F);
        setRotationAngle(left_chest, 0.0F, -1.5708F, 0.0F);
        left_chest.texOffs(26, 21).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 3.0F, 0.0F, false);

        right_chest = new ModelPart(this);
        right_chest.setPos(-6.0F, 3.0F, 6.0F);
        setRotationAngle(right_chest, 0.0F, 1.5708F, 0.0F);
        right_chest.texOffs(26, 21).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        mouth.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        left_ear.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        right_ear.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        mane.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        front_left_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        front_right_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        back_left_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        back_right_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        left_chest.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        right_chest.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.xRot = x;
        ModelPart.yRot = y;
        ModelPart.zRot = z;
    }

    @Override
    public void setupAnim(ClientDonkey abstractHorse, float f, float g, float h, float a, float b) {
        float i = Mth.rotLerp(h, abstractHorse.yBodyRotO, abstractHorse.yBodyRot);
        float j = Mth.rotLerp(h, abstractHorse.yHeadRotO, abstractHorse.yHeadRot);
        float k = Mth.lerp(h, abstractHorse.xRotO, abstractHorse.xRot);
        float l = j - i;
        float m = k * ((float) Math.PI / 180F);
        if (l > 20.0F) {
            l = 20.0F;
        }

        if (l < -20.0F) {
            l = -20.0F;
        }

        if (g > 0.2F) {
            m += Mth.cos(f * 0.8F) * 0.15F * g;
        }

        float o = 1;
        float n = 1;
        float q = 1;
        float p = 1.0F;
        float r = (float) abstractHorse.tickCount + h;
        this.head.y = 4.0F;
        this.head.z = -12.0F;
        this.body.xRot = 0.0F;
        this.head.xRot = ((float) Math.PI / 6F) + m;
        this.head.yRot = l * ((float) Math.PI / 180F);
        float s = abstractHorse.isInWater() ? 0.2F : 1.0F;
        float t = Mth.cos(s * f * 0.6662F + (float) Math.PI);
        float u = t * 0.8F * g;
        float v = (1.0F - Math.max(o, n)) * (((float) Math.PI / 6F) + m + q * Mth.sin(r) * 0.05F);
        this.head.xRot = o * (0.2617994F + m) + n * (2.1816616F + Mth.sin(r) * 0.05F) + v;
        this.head.yRot = o * l * ((float) Math.PI / 180F) + (1.0F - Math.max(o, n)) * this.head.yRot;
        this.head.y = o * -4.0F + n * 11.0F + (1.0F - Math.max(o, n)) * this.head.y;
        this.head.z = o * -4.0F + n * -12.0F + (1.0F - Math.max(o, n)) * this.head.z;
        this.body.xRot = o * (-(float) Math.PI / 4F) + p * this.body.xRot;
        float w = 0.2617994F * o;
        float x = Mth.cos(r * 0.6F + (float) Math.PI);
        this.front_left_leg.y = 2.0F * o + 14.0F * p;
        this.front_left_leg.z = -6.0F * o - 10.0F * p;
        this.front_right_leg.y = this.front_left_leg.y;
        this.front_right_leg.z = this.front_left_leg.z;
        float y = ((-(float) Math.PI / 3F) + x) * o + u * p;
        float z = ((-(float) Math.PI / 3F) - x) * o - u * p;
        this.back_left_leg.xRot = w - t * 0.5F * g * p;
        this.back_right_leg.xRot = w + t * 0.5F * g * p;
        this.front_left_leg.xRot = y;
        this.front_right_leg.xRot = z;
        this.tail.xRot = ((float) Math.PI / 6F) + g * 0.75F;
        this.tail.y = -5.0F + g;
        this.tail.z = 2.0F + g * 2.0F;
        this.body.y = 10.8F;
    }
}
