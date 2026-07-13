package com.jeff.pets.client.rendering.vanilla.vex;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ClientVexModel extends BipedModel<ClientVex> {
    private final ModelRenderer leftWing;
    private final ModelRenderer rightWing;

    public ClientVexModel() {
        super(0.0F, 0.0F, 64, 64);
        this.leftLeg.visible = false;
        this.hat.visible = false;
        this.rightLeg = new ModelRenderer(this, 32, 0);
        this.rightLeg.addBox(-1.0F, -1.0F, -2.0F, 6.0F, 10.0F, 4.0F, 0.0F);
        this.rightLeg.setPos(-1.9F, 12.0F, 0.0F);
        this.rightWing = new ModelRenderer(this, 0, 32);
        this.rightWing.addBox(-20.0F, 0.0F, 0.0F, 20.0F, 12.0F, 1.0F);
        this.leftWing = new ModelRenderer(this, 0, 32);
        this.leftWing.mirror = true;
        this.leftWing.addBox(0.0F, 0.0F, 0.0F, 20.0F, 12.0F, 1.0F);
    }

    protected Iterable<ModelRenderer> bodyParts() {
        return Iterables.concat(super.bodyParts(), ImmutableList.of(this.rightWing, this.leftWing));
    }

    public void setupAnim(ClientVex vex, float f, float g, float h, float i, float j) {
        super.setupAnim(vex, f, g, h, i, j);

        ModelRenderer var10000 = this.rightLeg;
        var10000.xRot += ((float) Math.PI / 5F);
        this.rightWing.z = 2.0F;
        this.leftWing.z = 2.0F;
        this.rightWing.y = 1.0F;
        this.leftWing.y = 1.0F;
        this.rightWing.yRot = 0.47123894F + net.minecraft.util.math.MathHelper.cos(h * 0.8F) * (float) Math.PI * 0.05F;
        this.leftWing.yRot = -this.rightWing.yRot;
        this.leftWing.zRot = -0.47123894F;
        this.leftWing.xRot = 0.47123894F;
        this.rightWing.xRot = 0.47123894F;
        this.rightWing.zRot = 0.47123894F;
    }
}
