package com.jeff.pets.test.client.render;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.test.client.PetsModTestModule;
import com.jeff.pets.test.mob.TestDummy;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.test.PetsModTestInit.MOD_ID;

public class TestDummyRenderer extends PetRenderer<TestDummy, ZombieRenderState, ZombieModel<@NotNull ZombieRenderState>> {
    public TestDummyRenderer(EntityRendererProvider.Context context) {
        super(context, new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull ZombieRenderState state) {
        if (PetsModTestModule.testDummySkin.equals("red")) {
            return Identifier.fromNamespaceAndPath(MOD_ID, "test-dummy-red");
        }
        return Identifier.fromNamespaceAndPath(MOD_ID, "test-dummy.png");
    }

    @Override
    public @NotNull ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }
}
