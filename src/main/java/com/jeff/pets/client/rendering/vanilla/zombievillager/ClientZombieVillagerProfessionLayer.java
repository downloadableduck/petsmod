package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ZombieVillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ZombieVillagerRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerProfessionLayer extends RenderLayer<@NotNull ZombieVillagerRenderState, @NotNull ZombieVillagerModel<@NotNull ZombieVillagerRenderState>> {

    public static final ModelLayerLocation ARMORER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/armorer.png"), "main");
    public static final ModelLayerLocation BUTCHER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/butcher.png"), "main");
    public static final ModelLayerLocation CARTOGRAPHER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/cartographer.png"), "main");
    public static final ModelLayerLocation CLERIC_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/cleric.png"), "main");
    public static final ModelLayerLocation FARMER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/farmer.png"), "main");
    public static final ModelLayerLocation FISHERMAN_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/fisherman.png"), "main");
    public static final ModelLayerLocation FLETCHER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/fletcher.png"), "main");
    public static final ModelLayerLocation LEATHERWORKER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/leatherworker.png"), "main");
    public static final ModelLayerLocation LIBRARIAN_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/librarian.png"), "main");
    public static final ModelLayerLocation MASON_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/mason.png"), "main");
    public static final ModelLayerLocation NITWIT_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/nitwit.png"), "main");
    public static final ModelLayerLocation SHEPHERD_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/shepherd.png"), "main");
    public static final ModelLayerLocation TOOLSMITH_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/toolsmith.png"), "main");
    public static final ModelLayerLocation WEAPONSMITH_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/profession/weaponsmith.png"), "main");

    public ClientZombieVillagerProfessionLayer(RenderLayerParent<@NotNull ZombieVillagerRenderState, @NotNull ZombieVillagerModel<@NotNull ZombieVillagerRenderState>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int i, ZombieVillagerRenderState entityRenderState, float f, float g) {
        poseStack.pushPose();
        poseStack.scale(1.001f, 1.001f, 1.001f);
        switch (CONFIG.zombieVillagerSkin) {
            case "armorer" ->
                    renderColoredCutoutModel(this.getParentModel(), ARMORER_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case "butcher" ->
                    renderColoredCutoutModel(this.getParentModel(), BUTCHER_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case "cartographer" ->
                    renderColoredCutoutModel(this.getParentModel(), CARTOGRAPHER_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case "cleric" ->
                    renderColoredCutoutModel(this.getParentModel(), CLERIC_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case "farmer" ->
                    renderColoredCutoutModel(this.getParentModel(), FARMER_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case "fisherman" ->
                    renderColoredCutoutModel(this.getParentModel(), FISHERMAN_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case "fletcher" ->
                    renderColoredCutoutModel(this.getParentModel(), FLETCHER_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case "leatherworker" ->
                    renderColoredCutoutModel(this.getParentModel(), LEATHERWORKER_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case "librarian" ->
                    renderColoredCutoutModel(this.getParentModel(), LIBRARIAN_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case "mason" ->
                    renderColoredCutoutModel(this.getParentModel(), MASON_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case "nitwit" ->
                    renderColoredCutoutModel(this.getParentModel(), NITWIT_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case "shepherd" ->
                    renderColoredCutoutModel(this.getParentModel(), SHEPHERD_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case "toolsmith" ->
                    renderColoredCutoutModel(this.getParentModel(), TOOLSMITH_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case "weaponsmith" ->
                    renderColoredCutoutModel(this.getParentModel(), WEAPONSMITH_LOCATION.model(), poseStack, source, i, entityRenderState, -1);
            case null, default -> {
            }
        }
        poseStack.popPose();
    }
}
