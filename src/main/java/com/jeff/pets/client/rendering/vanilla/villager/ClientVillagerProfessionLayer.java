package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

//villager layer is blue lmao
public class ClientVillagerProfessionLayer extends LayerRenderer<ClientVillager, VillagerModel<ClientVillager>> {

    public static final ResourceLocation ARMORER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/armorer.png");
    public static final ResourceLocation BUTCHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/butcher.png");
    public static final ResourceLocation CARTOGRAPHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/cartographer.png");
    public static final ResourceLocation CLERIC_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/cleric.png");
    public static final ResourceLocation FARMER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/farmer.png");
    public static final ResourceLocation FISHERMAN_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/fisherman.png");
    public static final ResourceLocation FLETCHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/fletcher.png");
    public static final ResourceLocation LEATHERWORKER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/leatherworker.png");
    public static final ResourceLocation LIBRARIAN_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/librarian.png");
    public static final ResourceLocation MASON_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/mason.png");
    public static final ResourceLocation NITWIT_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/nitwit.png");
    public static final ResourceLocation SHEPHERD_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/shepherd.png");
    public static final ResourceLocation TOOLSMITH_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/toolsmith.png");
    public static final ResourceLocation WEAPONSMITH_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/weaponsmith.png");

    public ClientVillagerProfessionLayer(IEntityRenderer<ClientVillager, VillagerModel<ClientVillager>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(com.mojang.blaze3d.matrix.MatrixStack poseStack, net.minecraft.client.renderer.IRenderTypeBuffer source, int i, ClientVillager entityRenderState, float f, float g, float h, float j, float k, float l) {
        poseStack.pushPose();
        poseStack.scale(1.001f, 1.001f, 1.001f);
        if (Objects.equals(CONFIG.villagerSkin, "armorer")) {
            renderColoredCutoutModel(this.getParentModel(), ARMORER_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "butcher")) {
            renderColoredCutoutModel(this.getParentModel(), BUTCHER_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "cartographer")) {
            renderColoredCutoutModel(this.getParentModel(), CARTOGRAPHER_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "cleric")) {
            renderColoredCutoutModel(this.getParentModel(), CLERIC_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "farmer")) {
            renderColoredCutoutModel(this.getParentModel(), FARMER_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "fisherman")) {
            renderColoredCutoutModel(this.getParentModel(), FISHERMAN_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "fletcher")) {
            renderColoredCutoutModel(this.getParentModel(), FLETCHER_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "leatherworker")) {
            renderColoredCutoutModel(this.getParentModel(), LEATHERWORKER_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "librarian")) {
            renderColoredCutoutModel(this.getParentModel(), LIBRARIAN_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "mason")) {
            renderColoredCutoutModel(this.getParentModel(), MASON_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "nitwit")) {
            renderColoredCutoutModel(this.getParentModel(), NITWIT_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "shepherd")) {
            renderColoredCutoutModel(this.getParentModel(), SHEPHERD_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "toolsmith")) {
            renderColoredCutoutModel(this.getParentModel(), TOOLSMITH_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "weaponsmith")) {
            renderColoredCutoutModel(this.getParentModel(), WEAPONSMITH_LOCATION, poseStack, source, i, entityRenderState, 1, 1, 1);
        }
        poseStack.popPose();
    }
}
