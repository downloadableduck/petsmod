package com.jeff.pets.client.rendering.vanilla.villager;

import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Villager profession overlay, drawn very slightly scaled up so it sits over the base
 * texture without z-fighting. 1.7.10 has no {@code LayerRenderer}, so this is driven from
 * {@link PetRenderer#renderModel}.
 */
public class ClientVillagerProfessionLayer implements PetLayer {
    static final String ARMORER_LOCATION = "textures/entity/villager/profession/armorer.png";
    static final String BUTCHER_LOCATION = "textures/entity/villager/profession/butcher.png";
    static final String CARTOGRAPHER_LOCATION = "textures/entity/villager/profession/cartographer.png";
    static final String CLERIC_LOCATION = "textures/entity/villager/profession/cleric.png";
    static final String FARMER_LOCATION = "textures/entity/villager/profession/farmer.png";
    static final String FISHERMAN_LOCATION = "textures/entity/villager/profession/fisherman.png";
    static final String FLETCHER_LOCATION = "textures/entity/villager/profession/fletcher.png";
    static final String LEATHERWORKER_LOCATION = "textures/entity/villager/profession/leatherworker.png";
    static final String LIBRARIAN_LOCATION = "textures/entity/villager/profession/librarian.png";
    static final String MASON_LOCATION = "textures/entity/villager/profession/mason.png";
    static final String NITWIT_LOCATION = "textures/entity/villager/profession/nitwit.png";
    static final String SHEPHERD_LOCATION = "textures/entity/villager/profession/shepherd.png";
    static final String TOOLSMITH_LOCATION = "textures/entity/villager/profession/toolsmith.png";
    static final String WEAPONSMITH_LOCATION = "textures/entity/villager/profession/weaponsmith.png";

    private final PetRenderer parent;

    public ClientVillagerProfessionLayer(PetRenderer parent) {
        this.parent = parent;
    }

    @Override
    public void render(EntityLiving villager, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        GL11.glPushMatrix();
        GL11.glScalef(1.001F, 1.001F, 1.001F);
        this.parent.getMainModel().render(villager, limbSwing, limbSwingAmount, ageInTicks,
                netHeadYaw, headPitch, scale);
        GL11.glPopMatrix();
    }
}
