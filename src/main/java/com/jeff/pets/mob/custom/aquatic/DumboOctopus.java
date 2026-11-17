//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.custom.aquatic;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.FlyingPet;
import com.jeff.pets.mob.custom.first.Duck;

public class DumboOctopus extends FlyingPet {
    public static final int IS_SERVER_ENTITY = 20;
    public static final int OCTOPUS_SKIN = 21;
    private final float nextFlap = 1.0F;
    public float tentacleAngle = 0.0F;
    public ServerPlayerEntity owner = (ServerPlayerEntity)this.getOwner();

    public DumboOctopus(World level) {
        super(level);
        this.setBoundingBoxSpacing(0.5F, 0.5F);
        this.setSpeed(0.3F);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(21, 1);
        this.dataTracker.startTracking(20, (byte)0);
    }

    public boolean isServerEntity() {
        return this.dataTracker.getByte(10) == 1;
    }

    public void setServerEntity(Boolean value) {
        this.dataTracker.set(10, (byte)(value ? 1 : 0));
    }

    protected int stopDistance() {
        return 2;
    }

    protected float heartHeight() {
        return 1.5F;
    }

    protected String getRandomSound() {
        return "";
    }

    protected String getHurtSound() {
        return "duck_ambient";
    }

    protected String getDeathSound() {
        return "duck_ambient";
    }

    public @Nullable DumboOctopus makeChild(@NotNull AnimalEntity partner) {
        DumboOctopus octopus = new DumboOctopus(this.world);
        octopus.setServerEntity(true);
        return octopus;
    }

    public boolean isBreedingItem(@NotNull ItemStack itemStack) {
        return itemStack.isItemEqual(new ItemStack(Item.RAW_FISH, 1, 0)) || itemStack.isItemEqual(new ItemStack(Item.RAW_FISH, 1, 1)) || itemStack.isItemEqual(new ItemStack(Item.RAW_FISH, 1, 2));
    }

    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {
            this.yaw = this.getOwner().yaw + 180.0F;
            if (this.vehicle == owner) {
                if (owner.isSneaking() && owner.jumping) {
                    this.dismountFromVehicle();
                    this.lerpVelocity(this.getVelocity().add(0.0, 0.1, 0.0));
                } else {
                    this.setSitting(true);
                }
            }

            double dx = owner.x - this.x;
            double dz = owner.z - this.z;
            Vec3d ownerPos = Vec3d.create(owner.x, owner.y, owner.z).add(0.0, (double)owner.getEyeHeight() * 0.8, 0.0);
            Vec3d vecToOwner = ownerPos.relativize(this.getPosVec());
            double targetYaw = Math.atan2(dz, dx) * 57.29577951308232 - 90.0;
            double distance = (double)this.getDistance(owner);
            float rotation = -this.pitch;
            float rotationToOwner = rotation + -this.getOwner().pitch;
            float bodyYawDiff = Utils.wrapDegrees(this.yaw - this.bodyYaw);
            if (rotationToOwner >= 50.0F) {
                this.bodyYaw = this.yaw - Math.signum(bodyYawDiff) * 50.0F;
            }

            if (distance > 2.0) {
                this.walkAnimationSpeed = 0.5F;
                Vec3d dir = vecToOwner.normalize();
                double speed = 0.2;
                this.bodyYaw = Duck.rotlerp(this.bodyYaw, (float)targetYaw);
                this.bodyYaw += Math.max(-50.0F, Math.min(50.0F, this.yaw - this.bodyYaw));
                this.lerpVelocity(Vec3d.create(-dir.x * speed, dir.y * speed, -dir.z * speed));
            } else {
                this.lerpVelocity(Vec3d.create(this.getVelocity().x * 0.8, this.getVelocity().y * 0.8, this.getVelocity().z * 0.8));
            }

            int yHeightToOwner = (int)(owner.y - this.y);
            if (yHeightToOwner > 1 || this.horizontalCollision) {
                this.jump();
            }

            if (yHeightToOwner > -1) {
                this.lerpVelocity(this.getVelocity().add(0.0, -0.01, 0.0));
            }

            if (!this.onGround) {
            }

            if (Utils.squaredDistanceToOrigin(Vec3d.create(owner.velocityX, owner.velocityY, owner.velocityZ)) < 0.01) {
                ++this.waitingTime;
                if (this.waitingTime > 30) {
                    this.wander();
                }
            } else {
                this.waitingTime = 0;
            }

            this.setYRot(Duck.rotlerp(this.getYRot(), (float)targetYaw));
            if (Math.abs(bodyYawDiff) > 50.0F) {
                this.bodyYaw = this.yaw - Math.signum(bodyYawDiff) * 50.0F;
            } else {
                this.bodyYaw += Math.max(-10.0F, Math.min(10.0F, this.yaw - this.bodyYaw));
            }
        }

        if (owner != null && this.getDistance(owner) >= 10.0F) {
            this.setPosition(owner.x, owner.y, owner.z);
        }

        int ambient = (int)(Math.random() * 1200.0);
        if (ambient == 1) {
            this.world.playSound(this.x, this.y, this.z, "", 1.0F, 1.0F);
        }

    }

    public void writeNbt(@NotNull NbtCompound output) {
        super.writeNbt(output);
        output.putBoolean("isServerEntity", true);
        output.putInt("variant", this.dataTracker.getInt(11));
    }

    public void readNbt(@NotNull NbtCompound input) {
        super.readNbt(input);
        this.setServerEntity(input.getBoolean("isServerEntity"));
        this.dataTracker.set(21, input.getInt("variant"));
    }

    public boolean canBreatheInWater() {
        return true;
    }

    public String getTexture() {
        String yellow = "/mob/dumbo_octopus/yellow.png";
        String red = "/mob/dumbo_octopus/red.png";
        String blue = "/mob/dumbo_octopus/blue.png";
        String green = "/mob/dumbo_octopus/green.png";
        String orange = "/mob/dumbo_octopus/orange.png";
        String pink = "/mob/dumbo_octopus/pink.png";
        String path;
        if (Central.CONFIG.dumboOctopusSkin.equals("yellow")) {
            path = yellow;
        } else if (Central.CONFIG.dumboOctopusSkin.equals("red")) {
            path = red;
        } else if (Central.CONFIG.dumboOctopusSkin.equals("blue")) {
            path = blue;
        } else if (Central.CONFIG.dumboOctopusSkin.equals("green")) {
            path = green;
        } else if (Central.CONFIG.dumboOctopusSkin.equals("orange")) {
            path = orange;
        } else if (Central.CONFIG.dumboOctopusSkin.equals("pink")) {
            path = pink;
        } else {
            path = yellow;
        }

        return path;
    }
}
