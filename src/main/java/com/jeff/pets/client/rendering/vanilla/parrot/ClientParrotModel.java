package com.jeff.pets.client.rendering.vanilla.parrot;

import com.jeff.pets.mob.vanilla.passive.ClientParrot;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.model.entity.ParrotModel;
import net.minecraft.util.math.MathHelper;

public class ClientParrotModel extends Model<ClientParrot> {
    private final ModelPart field_3458;
    private final ModelPart field_3460;
    private final ModelPart field_3459;
    private final ModelPart field_3455;
    private final ModelPart field_3452;
    private final ModelPart field_3461;
    private final ModelPart field_3451;
    private final ModelPart field_3453;
    private final ModelPart field_3456;
    private final ModelPart field_3450;
    private final ModelPart field_3457;

    public ClientParrotModel() {
        this.f_9972380 /*textureWidth*/ = 32;
        this.f_9233444 /*textureHeight*/ = 32;
        this.field_3458 = new ModelPart(this, 2, 8);
        this.field_3458.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3);
        this.field_3458.setPos(0.0F, 16.5F, -3.0F);
        this.field_3460 = new ModelPart(this, 22, 1);
        this.field_3460.addBox(-1.5F, -1.0F, -1.0F, 3, 4, 1);
        this.field_3460.setPos(0.0F, 21.07F, 1.16F);
        this.field_3459 = new ModelPart(this, 19, 8);
        this.field_3459.addBox(-0.5F, 0.0F, -1.5F, 1, 5, 3);
        this.field_3459.setPos(1.5F, 16.94F, -2.76F);
        this.field_3455 = new ModelPart(this, 19, 8);
        this.field_3455.addBox(-0.5F, 0.0F, -1.5F, 1, 5, 3);
        this.field_3455.setPos(-1.5F, 16.94F, -2.76F);
        this.field_3452 = new ModelPart(this, 2, 2);
        this.field_3452.addBox(-1.0F, -1.5F, -1.0F, 2, 3, 2);
        this.field_3452.setPos(0.0F, 15.69F, -2.76F);
        this.field_3461 = new ModelPart(this, 10, 0);
        this.field_3461.addBox(-1.0F, -0.5F, -2.0F, 2, 1, 4);
        this.field_3461.setPos(0.0F, -2.0F, -1.0F);
        this.field_3452.addChild(this.field_3461);
        this.field_3451 = new ModelPart(this, 11, 7);
        this.field_3451.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1);
        this.field_3451.setPos(0.0F, -0.5F, -1.5F);
        this.field_3452.addChild(this.field_3451);
        this.field_3453 = new ModelPart(this, 16, 7);
        this.field_3453.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
        this.field_3453.setPos(0.0F, -1.75F, -2.45F);
        this.field_3452.addChild(this.field_3453);
        this.field_3456 = new ModelPart(this, 2, 18);
        this.field_3456.addBox(0.0F, -4.0F, -2.0F, 0, 5, 4);
        this.field_3456.setPos(0.0F, -2.15F, 0.15F);
        this.field_3452.addChild(this.field_3456);
        this.field_3450 = new ModelPart(this, 14, 18);
        this.field_3450.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
        this.field_3450.setPos(1.0F, 22.0F, -1.05F);
        this.field_3457 = new ModelPart(this, 14, 18);
        this.field_3457.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
        this.field_3457.setPos(-1.0F, 22.0F, -1.05F);
    }

    @Override
    public void render(ClientParrot parrot, float b, float h, float i, float j, float k, float f) {
        this.field_3458.render(f);
        this.field_3459.render(f);
        this.field_3455.render(f);
        this.field_3460.render(f);
        this.field_3452.render(f);
        this.field_3450.render(f);
        this.field_3457.render(f);
    }

    @Override
    public void setup(ClientParrot parrot, float i, float f, float g, float h, float j, float k) {
        ParrotModel.State pose = parrot.isPassenger() ? ParrotModel.State.ON_SHOULDER : ParrotModel.State.FLYING;
        this.field_3452.rotationX = k * ((float) Math.PI / 180F);
        this.field_3452.rotationY = j * ((float) Math.PI / 180F);
        this.field_3452.rotationZ = 0.0F;
        this.field_3452.x = 0.0F;
        this.field_3458.x = 0.0F;
        this.field_3460.x = 0.0F;
        this.field_3455.x = -1.5F;
        this.field_3459.x = 1.5F;
        switch (pose) {
            case SITTING:
                break;
            case PARTY:
                float l = MathHelper.cos(i);
                float m = MathHelper.sin(i);
                this.field_3452.x = l;
                this.field_3452.y = 15.69F + m;
                this.field_3452.rotationX = 0.0F;
                this.field_3452.rotationY = 0.0F;
                this.field_3452.rotationZ = MathHelper.sin(i) * 0.4F;
                this.field_3458.x = l;
                this.field_3458.y = 16.5F + m;
                this.field_3459.rotationZ = -0.0873F - h;
                this.field_3459.x = 1.5F + l;
                this.field_3459.y = 16.94F + m;
                this.field_3455.rotationZ = 0.0873F + h;
                this.field_3455.x = -1.5F + l;
                this.field_3455.y = 16.94F + m;
                this.field_3460.x = l;
                this.field_3460.y = 21.07F + m;
                break;
            case STANDING:
                ModelPart var10000 = this.field_3450;
                var10000.rotationX += MathHelper.cos(f * 0.6662F) * 1.4F * g;
                var10000 = this.field_3457;
                var10000.rotationX += MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
            case FLYING:
            case ON_SHOULDER:
            default:
                float n = h * 0.3F;
                this.field_3452.y = 15.69F + n;
                this.field_3460.rotationX = 1.015F + MathHelper.cos(f * 0.6662F) * 0.3F * g;
                this.field_3460.y = 21.07F + n;
                this.field_3458.y = 16.5F + n;
                this.field_3459.rotationZ = -0.0873F - h;
                this.field_3459.y = 16.94F + n;
                this.field_3455.rotationZ = 0.0873F + h;
                this.field_3455.y = 16.94F + n;
                this.field_3450.y = 22.0F + n;
                this.field_3457.y = 22.0F + n;
        }

    }

    private void prepare(ParrotModel.State pose) {
        this.field_3456.rotationX = -0.2214F;
        this.field_3458.rotationX = 0.4937F;
        this.field_3459.rotationX = -0.6981F;
        this.field_3459.rotationY = -(float) Math.PI;
        this.field_3455.rotationX = -0.6981F;
        this.field_3455.rotationY = -(float) Math.PI;
        this.field_3450.rotationX = -0.0299F;
        this.field_3457.rotationX = -0.0299F;
        this.field_3450.y = 22.0F;
        this.field_3457.y = 22.0F;
        this.field_3450.rotationZ = 0.0F;
        this.field_3457.rotationZ = 0.0F;
        switch (pose) {
            case SITTING:
                float f = 1.9F;
                this.field_3452.y = 17.59F;
                this.field_3460.rotationX = 1.5388988F;
                this.field_3460.y = 22.97F;
                this.field_3458.y = 18.4F;
                this.field_3459.rotationZ = -0.0873F;
                this.field_3459.y = 18.84F;
                this.field_3455.rotationZ = 0.0873F;
                this.field_3455.y = 18.84F;
                ++this.field_3450.y;
                ++this.field_3457.y;
                ++this.field_3450.rotationX;
                ++this.field_3457.rotationX;
                break;
            case PARTY:
                this.field_3450.rotationZ = -0.34906584F;
                this.field_3457.rotationZ = 0.34906584F;
            case STANDING:
            case ON_SHOULDER:
            default:
                break;
            case FLYING:
                ModelPart var10000 = this.field_3450;
                var10000.rotationX += 0.6981317F;
                var10000 = this.field_3457;
                var10000.rotationX += 0.6981317F;
        }

    }
}
