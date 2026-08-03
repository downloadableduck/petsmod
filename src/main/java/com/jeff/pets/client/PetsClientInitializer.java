package com.jeff.pets.client;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.mixin.client.EntityRenderersAccessor;
import com.jeff.pets.client.mixin.client.ModelLayersAccessor;
import com.jeff.pets.client.rendering.aprilfools.angryghast.AngryGhastRenderer;
import com.jeff.pets.client.rendering.aprilfools.batato.BatatoModel;
import com.jeff.pets.client.rendering.aprilfools.batato.BatatoRenderer;
import com.jeff.pets.client.rendering.aprilfools.diamondchicken.DiamondChickenRenderer;
import com.jeff.pets.client.rendering.aprilfools.lovegolem.LoveGolemRenderer;
import com.jeff.pets.client.rendering.aprilfools.megaspud.MegaSpudModel;
import com.jeff.pets.client.rendering.aprilfools.megaspud.MegaSpudOuterLayer;
import com.jeff.pets.client.rendering.aprilfools.megaspud.MegaSpudRenderer;
import com.jeff.pets.client.rendering.aprilfools.mooncow.LegacyCowModel;
import com.jeff.pets.client.rendering.aprilfools.mooncow.MoonCowRenderer;
import com.jeff.pets.client.rendering.aprilfools.nerdcreeper.NerdCreeperRenderer;
import com.jeff.pets.client.rendering.aprilfools.pinkwither.PinkWitherRenderer;
import com.jeff.pets.client.rendering.aprilfools.plaguewhale.PlaguewhaleRenderer;
import com.jeff.pets.client.rendering.aprilfools.poisonouspotatozombie.PoisonousPotatoZombieRenderer;
import com.jeff.pets.client.rendering.aprilfools.potatohusk.PotatoHuskRenderer;
import com.jeff.pets.client.rendering.aprilfools.raytracing.RayTracingRenderer;
import com.jeff.pets.client.rendering.aprilfools.redstonebug.RedstoneBugRenderer;
import com.jeff.pets.client.rendering.aprilfools.smilingcreeper.SmilingCreeperRenderer;
import com.jeff.pets.client.rendering.aprilfools.toxifin.ToxifinRenderer;
import com.jeff.pets.client.rendering.aprilfools.toxifin.ToxifinSlabModel;
import com.jeff.pets.client.rendering.aprilfools.traitor.TraitorRenderer;
import com.jeff.pets.client.rendering.custom.aprilfools.head.HeadModel;
import com.jeff.pets.client.rendering.custom.aprilfools.head.HeadRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus.DumboOctopusModel;
import com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus.DumboOctopusRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.koi.KoiModel;
import com.jeff.pets.client.rendering.custom.aquatic.koi.KoiRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.stingray.StingrayModel;
import com.jeff.pets.client.rendering.custom.aquatic.stingray.StingrayRenderer;
import com.jeff.pets.client.rendering.custom.first.duck.DuckModel;
import com.jeff.pets.client.rendering.custom.first.duck.DuckRenderer;
import com.jeff.pets.client.rendering.custom.first.penguin.PenguinModel;
import com.jeff.pets.client.rendering.custom.first.penguin.PenguinRenderer;
import com.jeff.pets.client.rendering.custom.first.racoon.RacoonModel;
import com.jeff.pets.client.rendering.custom.first.racoon.RacoonRenderer;
import com.jeff.pets.client.rendering.vanilla.allay.ClientAllayRenderer;
import com.jeff.pets.client.rendering.vanilla.armadillo.ClientArmadilloRenderer;
import com.jeff.pets.client.rendering.vanilla.axolotl.ClientAxolotlRenderer;
import com.jeff.pets.client.rendering.vanilla.bat.ClientBatRenderer;
import com.jeff.pets.client.rendering.vanilla.bee.ClientBeeRenderer;
import com.jeff.pets.client.rendering.vanilla.blaze.ClientBlazeRenderer;
import com.jeff.pets.client.rendering.vanilla.bogged.ClientBoggedRenderer;
import com.jeff.pets.client.rendering.vanilla.breeze.ClientBreezeRenderer;
import com.jeff.pets.client.rendering.vanilla.camel.ClientCamelRenderer;
import com.jeff.pets.client.rendering.vanilla.cat.ClientCatRenderer;
import com.jeff.pets.client.rendering.vanilla.cavespider.ClientCaveSpiderModel;
import com.jeff.pets.client.rendering.vanilla.cavespider.ClientCaveSpiderRenderer;
import com.jeff.pets.client.rendering.vanilla.chicken.ClientChickenModel;
import com.jeff.pets.client.rendering.vanilla.chicken.ClientChickenRenderer;
import com.jeff.pets.client.rendering.vanilla.cod.ClientCodRenderer;
import com.jeff.pets.client.rendering.vanilla.coppergolem.ClientCopperGolemRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowRenderer;
import com.jeff.pets.client.rendering.vanilla.creaking.ClientCreakingRenderer;
import com.jeff.pets.client.rendering.vanilla.creeper.ClientCreeperRenderer;
import com.jeff.pets.client.rendering.vanilla.dolphin.ClientDolphinRenderer;
import com.jeff.pets.client.rendering.vanilla.donkey.ClientDonkeyRenderer;
import com.jeff.pets.client.rendering.vanilla.drowned.ClientDrownedRenderer;
import com.jeff.pets.client.rendering.vanilla.elderguardian.ClientElderGuardianRenderer;
import com.jeff.pets.client.rendering.vanilla.enderdragon.ClientEnderDragonRenderer;
import com.jeff.pets.client.rendering.vanilla.enderman.ClientEndermanRenderer;
import com.jeff.pets.client.rendering.vanilla.endermite.ClientEndermiteRenderer;
import com.jeff.pets.client.rendering.vanilla.evoker.ClientEvokerModel;
import com.jeff.pets.client.rendering.vanilla.evoker.ClientEvokerRenderer;
import com.jeff.pets.client.rendering.vanilla.fox.ClientFoxModel;
import com.jeff.pets.client.rendering.vanilla.fox.ClientFoxRenderer;
import com.jeff.pets.client.rendering.vanilla.frog.ClientFrogRenderer;
import com.jeff.pets.client.rendering.vanilla.ghast.ClientGhastRenderer;
import com.jeff.pets.client.rendering.vanilla.goat.ClientGoatModel;
import com.jeff.pets.client.rendering.vanilla.goat.ClientGoatRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianRenderer;
import com.jeff.pets.client.rendering.vanilla.happyghast.ClientHappyGhastRenderer;
import com.jeff.pets.client.rendering.vanilla.hoglin.ClientHoglinModel;
import com.jeff.pets.client.rendering.vanilla.hoglin.ClientHoglinRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseRenderer;
import com.jeff.pets.client.rendering.vanilla.husk.ClientHuskRenderer;
import com.jeff.pets.client.rendering.vanilla.irongolem.ClientIronGolemRenderer;
import com.jeff.pets.client.rendering.vanilla.llama.ClientLlamaRenderer;
import com.jeff.pets.client.rendering.vanilla.magmacube.ClientMagmaCubeRenderer;
import com.jeff.pets.client.rendering.vanilla.mooshroom.ClientMooshroomRenderer;
import com.jeff.pets.client.rendering.vanilla.nautilus.ClientNautilusRenderer;
import com.jeff.pets.client.rendering.vanilla.panda.ClientPandaRenderer;
import com.jeff.pets.client.rendering.vanilla.parched.ClientParchedRenderer;
import com.jeff.pets.client.rendering.vanilla.parrot.ClientParrotRenderer;
import com.jeff.pets.client.rendering.vanilla.phantom.ClientPhantomRenderer;
import com.jeff.pets.client.rendering.vanilla.pig.ClientPigRenderer;
import com.jeff.pets.client.rendering.vanilla.piglin.ClientPiglinRenderer;
import com.jeff.pets.client.rendering.vanilla.pillager.ClientPillagerModel;
import com.jeff.pets.client.rendering.vanilla.pillager.ClientPillagerRenderer;
import com.jeff.pets.client.rendering.vanilla.polarbear.ClientPolarBearRenderer;
import com.jeff.pets.client.rendering.vanilla.pufferfish.ClientPufferFishRenderer;
import com.jeff.pets.client.rendering.vanilla.rabbit.ClientRabbitRenderer;
import com.jeff.pets.client.rendering.vanilla.ravager.ClientRavagerRenderer;
import com.jeff.pets.client.rendering.vanilla.salmon.ClientSalmonModel;
import com.jeff.pets.client.rendering.vanilla.salmon.ClientSalmonRenderer;
import com.jeff.pets.client.rendering.vanilla.sheep.ClientSheepModel;
import com.jeff.pets.client.rendering.vanilla.sheep.ClientSheepRenderer;
import com.jeff.pets.client.rendering.vanilla.sheep.ClientSheepWoolLayer;
import com.jeff.pets.client.rendering.vanilla.shulker.ClientShulkerRenderer;
import com.jeff.pets.client.rendering.vanilla.silverfish.ClientSilverfishRenderer;
import com.jeff.pets.client.rendering.vanilla.skeleton.ClientSkeletonRenderer;
import com.jeff.pets.client.rendering.vanilla.slime.ClientSlimeRenderer;
import com.jeff.pets.client.rendering.vanilla.sniffer.ClientSnifferRenderer;
import com.jeff.pets.client.rendering.vanilla.snowgolem.ClientSnowGolemRenderer;
import com.jeff.pets.client.rendering.vanilla.spider.ClientSpiderRenderer;
import com.jeff.pets.client.rendering.vanilla.squid.ClientSquidRenderer;
import com.jeff.pets.client.rendering.vanilla.stray.ClientStrayRenderer;
import com.jeff.pets.client.rendering.vanilla.strider.ClientStriderRenderer;
import com.jeff.pets.client.rendering.vanilla.tadpole.ClientTadpoleRenderer;
import com.jeff.pets.client.rendering.vanilla.turtle.ClientTurtleRenderer;
import com.jeff.pets.client.rendering.vanilla.vex.ClientVexRenderer;
import com.jeff.pets.client.rendering.vanilla.villager.ClientVillagerRenderer;
import com.jeff.pets.client.rendering.vanilla.vindicator.ClientVindicatorRenderer;
import com.jeff.pets.client.rendering.vanilla.wanderingtrader.ClientWanderingTraderRenderer;
import com.jeff.pets.client.rendering.vanilla.warden.ClientWardenRenderer;
import com.jeff.pets.client.rendering.vanilla.witch.ClientWitchRenderer;
import com.jeff.pets.client.rendering.vanilla.wither.ClientWitherRenderer;
import com.jeff.pets.client.rendering.vanilla.witherskeleton.ClientWitherSkeletonRenderer;
import com.jeff.pets.client.rendering.vanilla.wolf.ClientWolfRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie.ClientZombieRenderer;
import com.jeff.pets.client.rendering.vanilla.zombievillager.ClientZombieVillagerModel;
import com.jeff.pets.client.rendering.vanilla.zombievillager.ClientZombieVillagerRenderer;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.ambient.BatModel;
import net.minecraft.client.model.animal.allay.AllayModel;
import net.minecraft.client.model.animal.armadillo.AdultArmadilloModel;
import net.minecraft.client.model.animal.axolotl.AdultAxolotlModel;
import net.minecraft.client.model.animal.bee.AdultBeeModel;
import net.minecraft.client.model.animal.camel.AdultCamelModel;
import net.minecraft.client.model.animal.dolphin.DolphinModel;
import net.minecraft.client.model.animal.fish.CodModel;
import net.minecraft.client.model.animal.fish.PufferfishBigModel;
import net.minecraft.client.model.animal.frog.FrogModel;
import net.minecraft.client.model.animal.frog.TadpoleModel;
import net.minecraft.client.model.animal.golem.CopperGolemModel;
import net.minecraft.client.model.animal.golem.IronGolemModel;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.model.animal.nautilus.NautilusModel;
import net.minecraft.client.model.animal.panda.PandaModel;
import net.minecraft.client.model.animal.parrot.ParrotModel;
import net.minecraft.client.model.animal.sniffer.SnifferModel;
import net.minecraft.client.model.animal.squid.SquidModel;
import net.minecraft.client.model.animal.turtle.AdultTurtleModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.blaze.BlazeModel;
import net.minecraft.client.model.monster.breeze.BreezeModel;
import net.minecraft.client.model.monster.creaking.CreakingModel;
import net.minecraft.client.model.monster.dragon.EnderDragonModel;
import net.minecraft.client.model.monster.enderman.EndermanModel;
import net.minecraft.client.model.monster.endermite.EndermiteModel;
import net.minecraft.client.model.monster.ghast.GhastModel;
import net.minecraft.client.model.monster.guardian.GuardianModel;
import net.minecraft.client.model.monster.phantom.PhantomModel;
import net.minecraft.client.model.monster.ravager.RavagerModel;
import net.minecraft.client.model.monster.shulker.ShulkerModel;
import net.minecraft.client.model.monster.silverfish.SilverfishModel;
import net.minecraft.client.model.monster.skeleton.BoggedModel;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.model.monster.slime.MagmaCubeModel;
import net.minecraft.client.model.monster.slime.SlimeModel;
import net.minecraft.client.model.monster.spider.SpiderModel;
import net.minecraft.client.model.monster.strider.AdultStriderModel;
import net.minecraft.client.model.monster.vex.VexModel;
import net.minecraft.client.model.monster.warden.WardenModel;
import net.minecraft.client.model.monster.witch.WitchModel;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;

import static com.jeff.pets.PetsInitializer.LOGGER;

/**
 * Another {@code initializer} class. This class does a couple of things:
 * <p> - Assigns renderers to the entities defined in {@link PetsInitializer}
 * <p> - Bakes models into the layers
 * <p> - Creates the keybind to open the config screen, by default {@code p}
 * <p> Suppresses: Deprecation warnings, as {@link EntityRenderers} is marked as
 * {@code deprecated}. I will likely have to find a suitable replacement sometime, but for now,
 * suppressing the warnings will work.
 *
 * @see PetsInitializer
 * @see Central
 */
public class PetsClientInitializer {

    public static List<String> ADDONS = new ArrayList<>();

    public static KeyMapping keyMapping;

    /**
     * Misc rendering stuff
     */
    public static void registerRenderers() {

        createKeyBinding();

        ModelLayersAccessor.registerModelLayer(HeadModel.LAYER_LOCATION, HeadModel::getTexturedModelData);
        ModelLayersAccessor.registerModelLayer(RacoonRenderer.RACOON_LOCATION, RacoonModel::getTexturedModelData);
        ModelLayersAccessor.registerModelLayer(DuckModel.LAYER_LOCATION, DuckModel::getTexturedModelData);
        ModelLayersAccessor.registerModelLayer(PenguinModel.PENGUIN_LOCATION, PenguinModel::getTexturedModelData);
        ModelLayersAccessor.registerModelLayer(ClientSheepRenderer.SHEEP_LOCATION, ClientSheepModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientSheepWoolLayer.SHEEP_WOOL_LOCATION, ClientSheepModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientCatRenderer.CAT_LOCATION, ClientCatRenderer::createCatBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientAllayRenderer.ALLAY_TEXTURE, AllayModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientArmadilloRenderer.ARMADILLO_LOCATION, AdultArmadilloModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientAxolotlRenderer.AXOLOTL_LOCATION, AdultAxolotlModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientBatRenderer.BAT_LOCATION, BatModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientCamelRenderer.CAMEL_LOCATION, AdultCamelModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientChickenRenderer.CHICKEN_LOCATION, ClientChickenModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientCodRenderer.COD_LOCATION, CodModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientCopperGolemRenderer.COPPER_GOLEM_LOCATION, CopperGolemModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientCowRenderer.COW_LOCATION, ClientCowModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientDonkeyRenderer.DONKEY_LOCATION, ClientDonkeyRenderer::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientFrogRenderer.FROG_LOCATION, FrogModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientHorseRenderer.HORSE_LOCATION, ClientHorseRenderer::createBaseHorseLayer);
        ModelLayersAccessor.registerModelLayer(ClientMooshroomRenderer.MOOSHROOM_LOCATION, ClientCowModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientParrotRenderer.PARROT_LOCATION, ParrotModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientPigRenderer.PIG_LOCATION, ClientPigRenderer::createBasePigModel);
        ModelLayersAccessor.registerModelLayer(ClientRabbitRenderer.RABBIT_LOCATION, ClientRabbitRenderer::createBaseRabbitLayer);
        ModelLayersAccessor.registerModelLayer(ClientSalmonRenderer.SALMON_LOCATION, ClientSalmonModel::getTexturedModelData);
        ModelLayersAccessor.registerModelLayer(ClientSnifferRenderer.SNIFFER_LOCATION, SnifferModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientSnowGolemRenderer.SNOW_GOLEM, SnowGolemModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientSquidRenderer.SQUID_LOCATION, SquidModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientStriderRenderer.STRIDER_LOCATION, AdultStriderModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientTadpoleRenderer.TADPOLE_LOCATION, TadpoleModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientTurtleRenderer.TURTLE_LOCATION, AdultTurtleModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientVillagerRenderer.VILLAGER_LOCATION, ClientVillagerRenderer::createBaseVillagerLayer);
        ModelLayersAccessor.registerModelLayer(ClientWanderingTraderRenderer.WANDERING_TRADER_LOCATION, ClientVillagerRenderer::createBaseVillagerLayer);
        ModelLayersAccessor.registerModelLayer(ClientBeeRenderer.BEE_LOCATION, AdultBeeModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientCaveSpiderRenderer.CAVE_SPIDER_LOCATION, ClientCaveSpiderModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientDolphinRenderer.DOLPHIN_LOCATION, DolphinModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientEndermanRenderer.ENDERMAN_LOCATION, EndermanModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientFoxRenderer.FOX_LOCATION, ClientFoxModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientGoatRenderer.GOAT_LOCATION, ClientGoatModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientIronGolemRenderer.IRON_GOLEM_LOCATION, IronGolemModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientLlamaRenderer.LLAMA_LOCATION, ClientLlamaRenderer::createLlamaLayer);
        ModelLayersAccessor.registerModelLayer(ClientNautilusRenderer.NAUTILUS_LOCATION, NautilusModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientPandaRenderer.PANDA_LOCAITON, PandaModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientPiglinRenderer.PIGLIN_LOCATION, ClientPiglinRenderer::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientPolarBearRenderer.POLAR_BEAR_LOCATION, ClientPolarBearRenderer::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientPufferFishRenderer.PUFFERFISH_LOCATION, PufferfishBigModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientSpiderRenderer.SPIDER_LOCATION, SpiderModel::createSpiderBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientWolfRenderer.WOLF_LOCATION, ClientWolfRenderer::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientElderGuardianRenderer.ELDER_GUARDIAN_LOCATION, GuardianModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientHappyGhastRenderer.GHAST_LOCATION, ClientHappyGhastRenderer::createGhastBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientBlazeRenderer.BLAZE_LOCATION, BlazeModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientBoggedRenderer.BOGGED_LOCATION, BoggedModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientBreezeRenderer.BREEZE_LOCATION, BreezeModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientCreakingRenderer.CREAKING_LOCATION, CreakingModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientCreeperRenderer.CREEPER_LOCATION, ClientCreeperRenderer::createBaseCreeperLayer);
        ModelLayersAccessor.registerModelLayer(ClientDrownedRenderer.DROWNED_LOCATION, ClientDrownedRenderer::createBaseDrownedLayer);
        ModelLayersAccessor.registerModelLayer(ClientEndermiteRenderer.ENDERMITE_LOCATION, EndermiteModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientEvokerRenderer.EVOKER_LOCATION, ClientEvokerModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientGhastRenderer.GHAST_LOCATION, GhastModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientGuardianRenderer.GUARDIAN_LOCATION, GuardianModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientHoglinRenderer.HOGLIN_LOCATION, ClientHoglinModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientHuskRenderer.HUSK_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
        ModelLayersAccessor.registerModelLayer(ClientMagmaCubeRenderer.MAGMA_CUBE_LOCATION, MagmaCubeModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientParchedRenderer.PARCHED_LOCATION, SkeletonModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientPhantomRenderer.PHANTOM_LOCATION, PhantomModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientPillagerRenderer.PILLAGER_LOCATION, ClientPillagerModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientRavagerRenderer.RAVAGER_LOCATION, RavagerModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientShulkerRenderer.SHULKER_LOCATION, ShulkerModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientSilverfishRenderer.SILVERFISH_LOCATION, SilverfishModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientSkeletonRenderer.SKELETON_LOCATION, SkeletonModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientSlimeRenderer.SLIME_LOCATION, SlimeModel::createInnerBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientStrayRenderer.STRAY_LOCATION, SkeletonModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientVexRenderer.VEX_LOCATION, VexModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientVindicatorRenderer.VINDICATOR_LOCATION, ClientEvokerModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientWardenRenderer.WARDEN_LOCATION, WardenModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientWitchRenderer.WITCH_LOCATION, WitchModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientWitherSkeletonRenderer.WITHER_SKELETON_LOCATION, SkeletonModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientZombieRenderer.ZOMBIE_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
        ModelLayersAccessor.registerModelLayer(ClientZombieVillagerRenderer.ZOMBIE_VILLAGER_LOCATION, ClientZombieVillagerModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientEnderDragonRenderer.ENDER_DRAGON_LOCATION, EnderDragonModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(ClientWitherRenderer.WITHER_LOCATION, ClientWitherRenderer::createBaseWitherLayer);
        ModelLayersAccessor.registerModelLayer(AngryGhastRenderer.ANGRY_GHAST_LOCATION, GhastModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(BatatoRenderer.BATATO_LOCAITON, BatatoModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(DiamondChickenRenderer.DIAMOND_CHICKEN_LOCATION, ClientChickenModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(LoveGolemRenderer.LOVE_GOLEM_LOCATION, IronGolemModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(MegaSpudRenderer.MEGA_SPUD_LOCATION, MegaSpudModel::createInnerBodyLayer);
        ModelLayersAccessor.registerModelLayer(MegaSpudOuterLayer.MEGA_SPUD_OUTER_LOCATION, MegaSpudModel::createOuterBodyLayer);
        ModelLayersAccessor.registerModelLayer(MoonCowRenderer.MOON_COW_LOCATION, LegacyCowModel::createLegacyCowModel);
        ModelLayersAccessor.registerModelLayer(NerdCreeperRenderer.NERD_CREEPER_LOCATION, ClientCreeperRenderer::createBaseCreeperLayer);
        ModelLayersAccessor.registerModelLayer(PinkWitherRenderer.PINK_WITHER_LOCATION, ClientWitherRenderer::createBaseWitherLayer);
        ModelLayersAccessor.registerModelLayer(PlaguewhaleRenderer.PLAGUEWHALE_LOCATION, ToxifinSlabModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(PoisonousPotatoZombieRenderer.POISONOUS_POTATO_ZOMBIE_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
        ModelLayersAccessor.registerModelLayer(PotatoHuskRenderer.POTATO_HUSK_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
        ModelLayersAccessor.registerModelLayer(RayTracingRenderer.RAY_TRACING_LOCATION, RayTracingRenderer::createBasePlayerBodyLayer);
        ModelLayersAccessor.registerModelLayer(RedstoneBugRenderer.REDSTONE_BUG_LOCATION, SilverfishModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(SmilingCreeperRenderer.SMILING_CREEPER_LOCATION, ClientCreeperRenderer::createBaseCreeperLayer);
        ModelLayersAccessor.registerModelLayer(ToxifinRenderer.TOXIFIN_LOCATION, ToxifinSlabModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(TraitorRenderer.TRAITOR_LOCATION, ClientEvokerModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(DumboOctopusRenderer.DUMBO_OCTOPUS_LOCATION, DumboOctopusModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(KoiRenderer.KOI_LOCATION, KoiModel::createBodyLayer);
        ModelLayersAccessor.registerModelLayer(StingrayRenderer.STINGRAY_LOCATION, StingrayModel::createBodyLayer);

        //ClientLifecycleEvents.CLIENT_STARTED.register((mc) -> {
            //LOGGER.info("PetsMod addons loaded:{}", ADDONS);
        //});
    }

    /**
     * Registers the key binding and an {@code END_CLIENT_TICK} event to check if the key
     * is pressed
     */
    static void createKeyBinding() {
        keyMapping = new KeyMapping("Open Pets Menu", GLFW.GLFW_KEY_P, new KeyMapping.Category(Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "petsmod.keymapping")));
    }

    @FunctionalInterface
    public static interface TexturedLayerDefinitionProvider {
        /**
         * Creates the textured layer definition for use in a {@link ModelLayerLocation}.
         *
         * @return the textured layer definition for the model layer location.
         */
        LayerDefinition createLayerDefinition();
    }

}