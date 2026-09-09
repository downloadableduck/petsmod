package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.client.rendering.IPetRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSheepWoolLayer extends RenderLayer<@NotNull SheepRenderState, @NotNull ClientSheepModel> {
    public static final ModelLayerLocation SHEEP_WOOL_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("textures/entity/sheep/sheep_wool.png"), "main");
    private final EntityModel<@NotNull SheepRenderState> adultModel;
    private final EntityModel<@NotNull SheepRenderState> babyModel;
    int woolColor;
    private final ClientSheepRenderer renderer;

    public ClientSheepWoolLayer(ClientSheepRenderer renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.renderer = renderLayerParent;
        this.adultModel = new ClientSheepFurModel(entityModelSet.bakeLayer(ModelLayers.SHEEP_WOOL));
        this.babyModel = new ClientSheepFurModel(entityModelSet.bakeLayer(ModelLayers.SHEEP_BABY_WOOL));
    }

    public void submit(@NotNull PoseStack poseStack, @NotNull SubmitNodeCollector submitNodeCollector, int i, SheepRenderState sheepRenderState, float f, float g) {
        String skin = ((IPetRenderState) sheepRenderState).pets$getPetSkin();
        switch (skin) {
            case "white" -> woolColor = 15132390;
            case "orange" -> woolColor = 12214293;
            case "magenta" -> woolColor = 9779853;
            case "light_blue" -> woolColor = 2852515;
            case "yellow" -> woolColor = 12493357;
            case "lime" -> woolColor = 6329623;
            case "pink" -> woolColor = 11954303;
            case "gray" -> woolColor = 3488573;
            case "light_gray" -> woolColor = 7697777;
            case "cyan" -> woolColor = 1078645;
            case "purple" -> woolColor = 6694282;
            case "blue" -> woolColor = 2962303;
            case "brown" -> woolColor = 6438693;
            case "green" -> woolColor = 4611344;
            case "red" -> woolColor = 8659484;
            case "black" -> woolColor = 1381656;
            case null, default -> woolColor = 1381656;
        }

        submitNodeCollector.submitModel(
                adultModel,
                sheepRenderState,
                poseStack,
                RenderTypes.entityCutout(SHEEP_WOOL_LOCATION.model()),
                i,
                LivingEntityRenderer.getOverlayCoords(sheepRenderState, 0.0F),
                woolColor,
                null,
                sheepRenderState.outlineColor,
                null
        );
    }
}
