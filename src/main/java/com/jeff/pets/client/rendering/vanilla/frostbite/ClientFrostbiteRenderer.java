package com.jeff.pets.client.rendering.vanilla.frostbite;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientFrostbite;
import com.jeff.pets.mob.vanilla.hostile.ClientZombie;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.monster.zombie.FrostbiteModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;
import static com.jeff.pets.client.Central.CONFIG;

public class ClientFrostbiteRenderer extends PetRenderer<ClientFrostbite, ZombieRenderState, FrostbiteModel> {

    public static final ModelLayerLocation FROSTBITE_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(MOD_ID, "clientfrostbite"), "main");

    public ClientFrostbiteRenderer(EntityRendererProvider.Context context) {
        super(context, new FrostbiteModel(context.bakeLayer(FROSTBITE_LOCATION)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull ZombieRenderState state) {
        return Identifier.withDefaultNamespace("textures/entity/zombie/frostbite.png");
    }

    @Override
    public @NotNull ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

    @Override
    protected void scale(@NotNull ZombieRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public void extractRenderState(ClientFrostbite zombie, ZombieRenderState state, float f) {
        super.extractRenderState(zombie, state, f);
        state.isPassenger = zombie.isPassenger() || zombie.sitting;
    }
}
