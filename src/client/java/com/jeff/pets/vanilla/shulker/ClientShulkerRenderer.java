package com.jeff.pets.vanilla.shulker;

import com.jeff.pets.vanilla.hostile.ClientShulker;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.shulker.ShulkerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ShulkerRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Pet.CONFIG;

public class ClientShulkerRenderer extends MobRenderer<@NotNull ClientShulker, @NotNull ShulkerRenderState, @NotNull ShulkerModel> {

    public static final ModelLayerLocation SHULKER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientshulker"), "main");

    public ClientShulkerRenderer(EntityRendererProvider.Context context) {
        super(context, new ShulkerModel(context.bakeLayer(ModelLayers.SHULKER)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ShulkerRenderState state) {
        String shulkerFile;
        String folderPath = "textures/entity/shulker/";
        switch(CONFIG.shulkerSkin) {
            case "normal" -> shulkerFile = "shulker.png";
            case "black" -> shulkerFile = "shulker_black.png";
            case "brown" -> shulkerFile = "shulker_brown.png";
            case "cyan" -> shulkerFile = "shulker_cyan.png";
            case "light_blue" -> shulkerFile = "shulker_light_blue.png";
            case "light_gray" -> shulkerFile = "shulker_light_gray.png";
            case "lime" -> shulkerFile = "shulker_lime.png";
            case "magenta" -> shulkerFile = "shulker_magenta.png";
            case "orange" -> shulkerFile = "shulker_orange.png";
            case "pink" -> shulkerFile = "shulker_pink.png";
            case "purple" -> shulkerFile = "shulker_purple.png";
            case "red" -> shulkerFile = "shulker_red.png";
            case "white" -> shulkerFile = "shulker_white.png";
            case "yellow" -> shulkerFile = "shulker_yellow.png";
            case null, default -> shulkerFile = "shulker.png";
        }
        return Identifier.withDefaultNamespace(folderPath + shulkerFile);
    }

    @Override
    public ShulkerRenderState createRenderState() {
        return new ShulkerRenderState();
    }

    @Override
    public void extractRenderState(ClientShulker shulker, ShulkerRenderState state, float f) {
        super.extractRenderState(shulker, state, f);
        state.yBodyRot = 180;
        state.peekAmount = 1;
    }
}
