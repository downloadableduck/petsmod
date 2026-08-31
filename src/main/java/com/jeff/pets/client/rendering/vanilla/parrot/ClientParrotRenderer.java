package com.jeff.pets.client.rendering.vanilla.parrot;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientParrot;
import net.minecraft.client.renderer.entity.RenderParrot;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientParrotRenderer extends PetRenderer<ClientParrot, ClientParrotModel> {

    String parrotTexturePath;

    public ClientParrotRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientParrotModel(), 0.3F);
    }

    public ResourceLocation getEntityTexture(ClientParrot parrotRenderState) {
        if (Objects.equals(CONFIG.parrotSkin, "red")) {
            parrotTexturePath = "textures/entity/parrot/parrot_red_blue.png";
        } else if (Objects.equals(CONFIG.parrotSkin, "blue")) {
            parrotTexturePath = "textures/entity/parrot/parrot_blue.png";
        } else if (Objects.equals(CONFIG.parrotSkin, "green")) {
            parrotTexturePath = "textures/entity/parrot/parrot_green.png";
        } else if (Objects.equals(CONFIG.parrotSkin, "yellow")) {
            parrotTexturePath = "textures/entity/parrot/parrot_yellow_blue.png";
        } else if (Objects.equals(CONFIG.parrotSkin, "gray")) {
            parrotTexturePath = "textures/entity/parrot/parrot_gray.png";
        }
        return new ResourceLocation("minecraft", parrotTexturePath);

    }


    /*@Override
    public void extractRenderState(ClientParrot parrot, ParrotRenderState state, float f) {
        super.extractRenderState(parrot, state, f);
        float flap = net.minecraft.util.math.Math2.lerp(f, parrot.oFlap, parrot.flap);
        float flapSpeed = net.minecraft.util.math.Math2.lerp(f, parrot.oFlapSpeed, parrot.flapSpeed);
        parrot.flapAngle = (net.minecraft.util.math.MathHelper.sin(flap) + 1.0F) * flapSpeed;
    }*/

    @Override
    public float handleRotationFloat(ClientParrot p_77044_1_, float p_77044_2_) {
        return this.getCustomBob(p_77044_1_, p_77044_2_);
    }

    private float getCustomBob(ClientParrot p_192861_1_, float p_192861_2_) {
        float lvt_3_1_ = p_192861_1_.oFlap + (p_192861_1_.flap - p_192861_1_.oFlap) * p_192861_2_;
        float lvt_4_1_ = p_192861_1_.oFlapSpeed + (p_192861_1_.flapSpeed - p_192861_1_.oFlapSpeed) * p_192861_2_;
        return (MathHelper.sin(lvt_3_1_) + 1.0F) * lvt_4_1_;
    }
}
