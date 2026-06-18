package com.jeff.pets.client.rendering.vanilla.sulfur_cube;

import com.jeff.pets.client.Utils;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSulfurCube;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.monster.slime.SulfurCubeModel;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SulfurCubeRenderer;
import net.minecraft.client.renderer.entity.layers.SulfurCubeInnerLayer;
import net.minecraft.client.renderer.entity.state.SulfurCubeRenderState;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSulfurCubeRenderer extends PetRenderer<ClientSulfurCube, SulfurCubeRenderState, SulfurCubeModel> {

    SulfurCubeInnerLayer layer = new SulfurCubeInnerLayer(this, EntityModelSet.vanilla());
    private final BlockModelResolver blockModelResolver;
    public static final ModelLayerLocation SULFUR_CUBE_LOCATION = Utils.createModelLayer("sulfur_cube");

    public ClientSulfurCubeRenderer(EntityRendererProvider.Context context) {
        super(context, new SulfurCubeModel(context.bakeLayer(SULFUR_CUBE_LOCATION)), 0.5f);
        this.addLayer(layer);
        this.blockModelResolver = context.getBlockModelResolver();
    }

    @Override
    public Identifier getTextureLocation(SulfurCubeRenderState state) {
        return Identifier.withDefaultNamespace("textures/entity/sulfur_cube/sulfur_cube_outer.png");
    }

    @Override
    public SulfurCubeRenderState createRenderState() {
        return new SulfurCubeRenderState();
    }

    @Override
    public void extractRenderState(ClientSulfurCube cube, SulfurCubeRenderState state, float f) {
        super.extractRenderState(cube, state, f);
        this.blockModelResolver.update(state.containedBlock, Utils.getBlockFromString(CONFIG.sulfurCubeSkin).defaultBlockState(), BlockDisplayContext.create());
    }
}
