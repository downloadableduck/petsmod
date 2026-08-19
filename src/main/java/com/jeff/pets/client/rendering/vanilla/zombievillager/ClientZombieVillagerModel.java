package com.jeff.pets.rendering.vanilla.zombievillager;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.zombie.ZombieVillagerModel;
import net.minecraft.client.renderer.entity.state.ZombieVillagerRenderState;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientZombieVillagerModel extends ZombieVillagerModel<@NotNull ZombieVillagerRenderState> {

    private final ModelPart head;

    public ClientZombieVillagerModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    @Override
    public void setupAnim(ZombieVillagerRenderState state) {
        super.setupAnim(state);
        if (CONFIG.isBaby) {
            head.xScale = 1.5f;
            head.yScale = 1.5f;
            head.zScale = 1.5f;
        }
    }
}
