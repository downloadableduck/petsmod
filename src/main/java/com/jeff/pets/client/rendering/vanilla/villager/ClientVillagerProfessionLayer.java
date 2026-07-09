package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;


import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

//villager layer is blue lmao
public class ClientVillagerProfessionLayer extends RenderLayer< ClientVillager,  VillagerModel<ClientVillager>> {

    public static final ModelLayerLocation ARMORER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/armorer.png"), "main");
    public static final ModelLayerLocation BUTCHER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/butcher.png"), "main");
    public static final ModelLayerLocation CARTOGRAPHER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/cartographer.png"), "main");
    public static final ModelLayerLocation CLERIC_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/cleric.png"), "main");
    public static final ModelLayerLocation FARMER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/farmer.png"), "main");
    public static final ModelLayerLocation FISHERMAN_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/fisherman.png"), "main");
    public static final ModelLayerLocation FLETCHER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/fletcher.png"), "main");
    public static final ModelLayerLocation LEATHERWORKER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/leatherworker.png"), "main");
    public static final ModelLayerLocation LIBRARIAN_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/librarian.png"), "main");
    public static final ModelLayerLocation MASON_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/mason.png"), "main");
    public static final ModelLayerLocation NITWIT_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/nitwit.png"), "main");
    public static final ModelLayerLocation SHEPHERD_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/shepherd.png"), "main");
    public static final ModelLayerLocation TOOLSMITH_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/toolsmith.png"), "main");
    public static final ModelLayerLocation WEAPONSMITH_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "textures/entity/villager/profession/weaponsmith.png"), "main");

    public ClientVillagerProfessionLayer(RenderLayerParent< ClientVillager,  VillagerModel<ClientVillager>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render( PoseStack poseStack,  MultiBufferSource source, int i, ClientVillager entityRenderState, float f, float g, float h, float j, float k, float l) {
        poseStack.pushPose();
        poseStack.scale(1.001f, 1.001f, 1.001f);
        if (Objects.equals(CONFIG.villagerSkin, "armorer")) {
            renderColoredCutoutModel(this.getParentModel(), ARMORER_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "butcher")) {
            renderColoredCutoutModel(this.getParentModel(), BUTCHER_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "cartographer")) {
            renderColoredCutoutModel(this.getParentModel(), CARTOGRAPHER_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "cleric")) {
            renderColoredCutoutModel(this.getParentModel(), CLERIC_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "farmer")) {
            renderColoredCutoutModel(this.getParentModel(), FARMER_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "fisherman")) {
            renderColoredCutoutModel(this.getParentModel(), FISHERMAN_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "fletcher")) {
            renderColoredCutoutModel(this.getParentModel(), FLETCHER_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "leatherworker")) {
            renderColoredCutoutModel(this.getParentModel(), LEATHERWORKER_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "librarian")) {
            renderColoredCutoutModel(this.getParentModel(), LIBRARIAN_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "mason")) {
            renderColoredCutoutModel(this.getParentModel(), MASON_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "nitwit")) {
            renderColoredCutoutModel(this.getParentModel(), NITWIT_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "shepherd")) {
            renderColoredCutoutModel(this.getParentModel(), SHEPHERD_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "toolsmith")) {
            renderColoredCutoutModel(this.getParentModel(), TOOLSMITH_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        } else if (Objects.equals(CONFIG.villagerSkin, "weaponsmith")) {
            renderColoredCutoutModel(this.getParentModel(), WEAPONSMITH_LOCATION.getModel(), poseStack, source, i, entityRenderState, 1, 1, 1);
        }
        poseStack.popPose();
    }
}
