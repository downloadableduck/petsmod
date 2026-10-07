package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.client.rendering.PetLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

/**
 * Zombie villager profession overlay, drawn very slightly scaled up so it sits over the base
 * texture without z-fighting. 1.7.10 has no {@code LayerRenderer}, so this is driven from
 * {@link PetRenderer#renderModel}.
 *
 * <p>The 1.8 original bound the profession texture but never issued a second model draw, so
 * the overlay was invisible. The draw is restored here.
 */
public class ClientZombieVillagerProfessionLayer implements PetLayer {
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

    private final PetRenderer parent;

    public ClientZombieVillagerProfessionLayer(PetRenderer parent) {
        this.parent = parent;
    }

    private ResourceLocation resolveProfessionTexture() {
        String skin = CONFIG.zombieVillagerSkin;
        if (Objects.equals(skin, "armorer")) {
            return ARMORER_LOCATION;
        } else if (Objects.equals(skin, "butcher")) {
            return BUTCHER_LOCATION;
        } else if (Objects.equals(skin, "cartographer")) {
            return CARTOGRAPHER_LOCATION;
        } else if (Objects.equals(skin, "cleric")) {
            return CLERIC_LOCATION;
        } else if (Objects.equals(skin, "farmer")) {
            return FARMER_LOCATION;
        } else if (Objects.equals(skin, "fisherman")) {
            return FISHERMAN_LOCATION;
        } else if (Objects.equals(skin, "fletcher")) {
            return FLETCHER_LOCATION;
        } else if (Objects.equals(skin, "leatherworker")) {
            return LEATHERWORKER_LOCATION;
        } else if (Objects.equals(skin, "librarian")) {
            return LIBRARIAN_LOCATION;
        } else if (Objects.equals(skin, "mason")) {
            return MASON_LOCATION;
        } else if (Objects.equals(skin, "nitwit")) {
            return NITWIT_LOCATION;
        } else if (Objects.equals(skin, "shepherd")) {
            return SHEPHERD_LOCATION;
        } else if (Objects.equals(skin, "toolsmith")) {
            return TOOLSMITH_LOCATION;
        } else if (Objects.equals(skin, "weaponsmith")) {
            return WEAPONSMITH_LOCATION;
        }
        return null;
    }

    @Override
    public void render(EntityLivingBase zombieVillager, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        ResourceLocation texture = this.resolveProfessionTexture();
        if (texture == null) {
            return;
        }

        GL11.glPushMatrix();
        GL11.glScalef(1.001F, 1.001F, 1.001F);
        this.parent.bindTexturePublic(texture);
        this.parent.getMainModel().render(zombieVillager, limbSwing, limbSwingAmount, ageInTicks,
                netHeadYaw, headPitch, scale);
        GL11.glPopMatrix();
    }
}
