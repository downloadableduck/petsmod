package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.render.model.entity.VillagerModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientVillagerRenderer extends PetRenderer<ClientVillager> {

    public static final Identifier BUTCHER_LOCATION = new Identifier("minecraft", "textures/entity/villager/butcher.png");
    public static final Identifier FARMER_LOCATION = new Identifier("minecraft", "textures/entity/villager/farmer.png");
    public static final Identifier LIBRARIAN_LOCATION = new Identifier("minecraft", "textures/entity/villager/librarian.png");
    public static final Identifier NITWIT_LOCATION = new Identifier("minecraft", "textures/entity/villager/villager.png");
    public static final Identifier TOOLSMITH_LOCATION = new Identifier("minecraft", "textures/entity/villager/toolsmith.png");

    public ClientVillagerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new VillagerModel(0), 0.5F);
       // this.addLayer(new ClientVillagerDefaultLayer(this));
        //this.addLayer(new ClientVillagerProfessionLayer(this));
    }

    @Override
    protected void applyScale(ClientVillager state, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public Identifier getTextureLocation(ClientVillager villagerRenderState) {
        if (Objects.equals(CONFIG.villagerSkin, "butcher")) {
            return (BUTCHER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "farmer")) {
            return (FARMER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "librarian")) {
            return (LIBRARIAN_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "nitwit")) {
            return (NITWIT_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "toolsmith") || Objects.equals(CONFIG.villagerSkin, "weaponsmith")) {
            return (TOOLSMITH_LOCATION);
        }
        return NITWIT_LOCATION;
    }
}
