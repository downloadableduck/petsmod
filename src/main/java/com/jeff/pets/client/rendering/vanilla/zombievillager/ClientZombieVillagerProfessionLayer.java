package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerProfessionLayer extends RenderLayer<@NotNull ClientZombieVillager, ClientZombieVillagerModel> {

    public static final ResourceLocation ARMORER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/armorer.png");
    public static final ResourceLocation BUTCHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/butcher.png");
    public static final ResourceLocation CARTOGRAPHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/cartographer.png");
    public static final ResourceLocation CLERIC_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/cleric.png");
    public static final ResourceLocation FARMER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/farmer.png");
    public static final ResourceLocation FISHERMAN_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/fisherman.png");
    public static final ResourceLocation FLETCHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/fletcher.png");
    public static final ResourceLocation LEATHERWORKER_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/leatherworker.png");
    public static final ResourceLocation LIBRARIAN_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/librarian.png");
    public static final ResourceLocation MASON_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/mason.png");
    public static final ResourceLocation NITWIT_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/nitwit.png");
    public static final ResourceLocation SHEPHERD_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/shepherd.png");
    public static final ResourceLocation TOOLSMITH_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/toolsmith.png");
    public static final ResourceLocation WEAPONSMITH_LOCATION = new ResourceLocation("minecraft", "textures/entity/zombie_villager/profession/weaponsmith.png");

    public ClientZombieVillagerProfessionLayer(RenderLayerParent<@NotNull ClientZombieVillager, @NotNull ClientZombieVillagerModel> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int i, ClientZombieVillager entityRenderState, float f, float g, float h, float k, float l, float u) {
        poseStack.pushPose();
        poseStack.scale(1.001f, 1.001f, 1.001f);
        switch (CONFIG.zombieVillagerSkin) {
            case "armorer" ->
                    renderColoredCutoutModel(this.getParentModel(), ARMORER_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            case "butcher" ->
                    renderColoredCutoutModel(this.getParentModel(), BUTCHER_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            case "cartographer" ->
                    renderColoredCutoutModel(this.getParentModel(), CARTOGRAPHER_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            case "cleric" ->
                    renderColoredCutoutModel(this.getParentModel(), CLERIC_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            case "farmer" ->
                    renderColoredCutoutModel(this.getParentModel(), FARMER_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            case "fisherman" ->
                    renderColoredCutoutModel(this.getParentModel(), FISHERMAN_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            case "fletcher" ->
                    renderColoredCutoutModel(this.getParentModel(), FLETCHER_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            case "leatherworker" ->
                    renderColoredCutoutModel(this.getParentModel(), LEATHERWORKER_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            case "librarian" ->
                    renderColoredCutoutModel(this.getParentModel(), LIBRARIAN_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            case "mason" ->
                    renderColoredCutoutModel(this.getParentModel(), MASON_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            case "nitwit" ->
                    renderColoredCutoutModel(this.getParentModel(), NITWIT_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            case "shepherd" ->
                    renderColoredCutoutModel(this.getParentModel(), SHEPHERD_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            case "toolsmith" ->
                    renderColoredCutoutModel(this.getParentModel(), TOOLSMITH_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            case "weaponsmith" ->
                    renderColoredCutoutModel(this.getParentModel(), WEAPONSMITH_LOCATION, poseStack, source, i, entityRenderState, -1, 1, 1);
            default -> {
            }
        }
        poseStack.popPose();
    }
}
