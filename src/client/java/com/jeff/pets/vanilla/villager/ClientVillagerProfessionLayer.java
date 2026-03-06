package com.jeff.pets.vanilla.villager;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientVillagerProfessionLayer extends RenderLayer<@NotNull LivingEntityRenderState, @NotNull ClientVillagerModel> {

    public static final ModelLayerLocation ARMORER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/armorer.png"), "main");
    public static final ModelLayerLocation BUTCHER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/butcher.png"), "main");
    public static final ModelLayerLocation CARTOGRAPHER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/cartographer.png"), "main");
    public static final ModelLayerLocation CLERIC_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/cleric.png"), "main");
    public static final ModelLayerLocation FARMER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/farmer.png"), "main");
    public static final ModelLayerLocation FISHERMAN_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/fisherman.png"), "main");
    public static final ModelLayerLocation FLETCHER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/fletcher.png"), "main");
    public static final ModelLayerLocation LEATHERWORKER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/leatherworker.png"), "main");
    public static final ModelLayerLocation LIBRARIAN_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/librarian.png"), "main");
    public static final ModelLayerLocation MASON_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/mason.png"), "main");
    public static final ModelLayerLocation NITWIT_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/nitwit.png"), "main");
    public static final ModelLayerLocation SHEPHERD_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/shepherd.png"), "main");
    public static final ModelLayerLocation TOOLSMITH_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/toolsmith.png"), "main");
    public static final ModelLayerLocation WEAPONSMITH_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/villager/profession/weaponsmith.png"), "main");

    public ClientVillagerProfessionLayer(RenderLayerParent<@NotNull LivingEntityRenderState, @NotNull ClientVillagerModel> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void submit(@NotNull PoseStack poseStack, @NotNull SubmitNodeCollector submitNodeCollector, int i, LivingEntityRenderState entityRenderState, float f, float g) {
        poseStack.pushPose();
        poseStack.scale(1.001f, 1.001f, 1.001f);
        if (Objects.equals(CONFIG.villagerSkin, "armorer")) {
            renderColoredCutoutModel(this.getParentModel(), ARMORER_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        } else if (Objects.equals(CONFIG.villagerSkin, "butcher")) {
            renderColoredCutoutModel(this.getParentModel(), BUTCHER_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        } else if (Objects.equals(CONFIG.villagerSkin, "cartographer")) {
            renderColoredCutoutModel(this.getParentModel(), CARTOGRAPHER_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        } else if (Objects.equals(CONFIG.villagerSkin, "cleric")) {
            renderColoredCutoutModel(this.getParentModel(), CLERIC_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        } else if (Objects.equals(CONFIG.villagerSkin, "farmer")) {
            renderColoredCutoutModel(this.getParentModel(), FARMER_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        } else if (Objects.equals(CONFIG.villagerSkin, "fisherman")) {
            renderColoredCutoutModel(this.getParentModel(), FISHERMAN_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        } else if (Objects.equals(CONFIG.villagerSkin, "fletcher")) {
            renderColoredCutoutModel(this.getParentModel(), FLETCHER_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        } else if (Objects.equals(CONFIG.villagerSkin, "leatherworker")) {
            renderColoredCutoutModel(this.getParentModel(), LEATHERWORKER_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        } else if (Objects.equals(CONFIG.villagerSkin, "librarian")) {
            renderColoredCutoutModel(this.getParentModel(), LIBRARIAN_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        } else if (Objects.equals(CONFIG.villagerSkin, "mason")) {
            renderColoredCutoutModel(this.getParentModel(), MASON_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        } else if (Objects.equals(CONFIG.villagerSkin, "nitwit")) {
            renderColoredCutoutModel(this.getParentModel(), NITWIT_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        } else if (Objects.equals(CONFIG.villagerSkin, "shepherd")) {
            renderColoredCutoutModel(this.getParentModel(), SHEPHERD_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        } else if (Objects.equals(CONFIG.villagerSkin, "toolsmith")) {
            renderColoredCutoutModel(this.getParentModel(), TOOLSMITH_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        } else if (Objects.equals(CONFIG.villagerSkin, "weaponsmith")) {
            renderColoredCutoutModel(this.getParentModel(), WEAPONSMITH_LOCATION.model(), poseStack, submitNodeCollector, entityRenderState.lightCoords, entityRenderState, -1, OverlayTexture.NO_OVERLAY);
        }
        poseStack.popPose();
    }
}
