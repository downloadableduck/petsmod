package com.jeff.pets;

import com.jeff.pets.rendering.aprilfools.angryghast.AngryGhastRenderer;
import com.jeff.pets.rendering.aprilfools.batato.BatatoModel;
import com.jeff.pets.rendering.aprilfools.batato.BatatoRenderer;
import com.jeff.pets.rendering.aprilfools.diamondchicken.DiamondChickenRenderer;
import com.jeff.pets.rendering.aprilfools.lovegolem.LoveGolemRenderer;
import com.jeff.pets.rendering.aprilfools.megaspud.MegaSpudModel;
import com.jeff.pets.rendering.aprilfools.megaspud.MegaSpudOuterLayer;
import com.jeff.pets.rendering.aprilfools.megaspud.MegaSpudRenderer;
import com.jeff.pets.rendering.aprilfools.mooncow.LegacyCowModel;
import com.jeff.pets.rendering.aprilfools.mooncow.MoonCowRenderer;
import com.jeff.pets.rendering.aprilfools.nerdcreeper.NerdCreeperRenderer;
import com.jeff.pets.rendering.aprilfools.pinkwither.PinkWitherRenderer;
import com.jeff.pets.rendering.aprilfools.plaguewhale.PlaguewhaleRenderer;
import com.jeff.pets.rendering.aprilfools.poisonouspotatozombie.PoisonousPotatoZombieRenderer;
import com.jeff.pets.rendering.aprilfools.potatohusk.PotatoHuskRenderer;
import com.jeff.pets.rendering.aprilfools.raytracing.RayTracingRenderer;
import com.jeff.pets.rendering.aprilfools.redstonebug.RedstoneBugRenderer;
import com.jeff.pets.rendering.aprilfools.smilingcreeper.SmilingCreeperRenderer;
import com.jeff.pets.rendering.aprilfools.toxifin.ToxifinRenderer;
import com.jeff.pets.rendering.aprilfools.toxifin.ToxifinSlabModel;
import com.jeff.pets.rendering.aprilfools.traitor.TraitorRenderer;
import com.jeff.pets.rendering.custom.aprilfools.head.HeadModel;
import com.jeff.pets.rendering.custom.aprilfools.head.HeadRenderer;
import com.jeff.pets.rendering.custom.aquatic.dumbo_octopus.DumboOctopusModel;
import com.jeff.pets.rendering.custom.aquatic.dumbo_octopus.DumboOctopusRenderer;
import com.jeff.pets.rendering.custom.aquatic.koi.KoiModel;
import com.jeff.pets.rendering.custom.aquatic.koi.KoiRenderer;
import com.jeff.pets.rendering.custom.aquatic.stingray.StingrayModel;
import com.jeff.pets.rendering.custom.aquatic.stingray.StingrayRenderer;
import com.jeff.pets.rendering.custom.first.duck.DuckModel;
import com.jeff.pets.rendering.custom.first.duck.DuckRenderer;
import com.jeff.pets.rendering.custom.first.penguin.PenguinModel;
import com.jeff.pets.rendering.custom.first.penguin.PenguinRenderer;
import com.jeff.pets.rendering.custom.first.racoon.RacoonModel;
import com.jeff.pets.rendering.custom.first.racoon.RacoonRenderer;
import com.jeff.pets.rendering.vanilla.allay.ClientAllayRenderer;
import com.jeff.pets.rendering.vanilla.axolotl.ClientAxolotlRenderer;
import com.jeff.pets.rendering.vanilla.bat.ClientBatRenderer;
import com.jeff.pets.rendering.vanilla.bee.ClientBeeRenderer;
import com.jeff.pets.rendering.vanilla.blaze.ClientBlazeRenderer;
import com.jeff.pets.rendering.vanilla.camel.ClientCamelRenderer;
import com.jeff.pets.rendering.vanilla.cat.ClientCatRenderer;
import com.jeff.pets.rendering.vanilla.cavespider.ClientCaveSpiderRenderer;
import com.jeff.pets.rendering.vanilla.chicken.ClientChickenModel;
import com.jeff.pets.rendering.vanilla.chicken.ClientChickenRenderer;
import com.jeff.pets.rendering.vanilla.cod.ClientCodRenderer;
import com.jeff.pets.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.rendering.vanilla.cow.ClientCowRenderer;
import com.jeff.pets.rendering.vanilla.creeper.ClientCreeperRenderer;
import com.jeff.pets.rendering.vanilla.dolphin.ClientDolphinRenderer;
import com.jeff.pets.rendering.vanilla.donkey.ClientDonkeyRenderer;
import com.jeff.pets.rendering.vanilla.drowned.ClientDrownedRenderer;
import com.jeff.pets.rendering.vanilla.elderguardian.ClientElderGuardianRenderer;
import com.jeff.pets.rendering.vanilla.enderdragon.ClientEnderDragonRenderer;
import com.jeff.pets.rendering.vanilla.enderman.ClientEndermanRenderer;
import com.jeff.pets.rendering.vanilla.endermite.ClientEndermiteRenderer;
import com.jeff.pets.rendering.vanilla.evoker.ClientEvokerModel;
import com.jeff.pets.rendering.vanilla.evoker.ClientEvokerRenderer;
import com.jeff.pets.rendering.vanilla.fox.ClientFoxModel;
import com.jeff.pets.rendering.vanilla.fox.ClientFoxRenderer;
import com.jeff.pets.rendering.vanilla.frog.ClientFrogRenderer;
import com.jeff.pets.rendering.vanilla.ghast.ClientGhastRenderer;
import com.jeff.pets.rendering.vanilla.goat.ClientGoatModel;
import com.jeff.pets.rendering.vanilla.goat.ClientGoatRenderer;
import com.jeff.pets.rendering.vanilla.guardian.ClientGuardianRenderer;
import com.jeff.pets.rendering.vanilla.hoglin.ClientHoglinModel;
import com.jeff.pets.rendering.vanilla.hoglin.ClientHoglinRenderer;
import com.jeff.pets.rendering.vanilla.horse.ClientHorseRenderer;
import com.jeff.pets.rendering.vanilla.husk.ClientHuskRenderer;
import com.jeff.pets.rendering.vanilla.irongolem.ClientIronGolemRenderer;
import com.jeff.pets.rendering.vanilla.llama.ClientLlamaRenderer;
import com.jeff.pets.rendering.vanilla.magmacube.ClientMagmaCubeRenderer;
import com.jeff.pets.rendering.vanilla.mooshroom.ClientMooshroomRenderer;
import com.jeff.pets.rendering.vanilla.panda.ClientPandaRenderer;
import com.jeff.pets.rendering.vanilla.parrot.ClientParrotRenderer;
import com.jeff.pets.rendering.vanilla.phantom.ClientPhantomRenderer;
import com.jeff.pets.rendering.vanilla.pig.ClientPigRenderer;
import com.jeff.pets.rendering.vanilla.piglin.ClientPiglinRenderer;
import com.jeff.pets.rendering.vanilla.pillager.ClientPillagerModel;
import com.jeff.pets.rendering.vanilla.pillager.ClientPillagerRenderer;
import com.jeff.pets.rendering.vanilla.polarbear.ClientPolarBearRenderer;
import com.jeff.pets.rendering.vanilla.pufferfish.ClientPufferFishRenderer;
import com.jeff.pets.rendering.vanilla.rabbit.ClientRabbitRenderer;
import com.jeff.pets.rendering.vanilla.ravager.ClientRavagerRenderer;
import com.jeff.pets.rendering.vanilla.salmon.ClientSalmonModel;
import com.jeff.pets.rendering.vanilla.salmon.ClientSalmonRenderer;
import com.jeff.pets.rendering.vanilla.sheep.ClientSheepFurModel;
import com.jeff.pets.rendering.vanilla.sheep.ClientSheepModel;
import com.jeff.pets.rendering.vanilla.sheep.ClientSheepRenderer;
import com.jeff.pets.rendering.vanilla.sheep.ClientSheepWoolLayer;
import com.jeff.pets.rendering.vanilla.shulker.ClientShulkerRenderer;
import com.jeff.pets.rendering.vanilla.silverfish.ClientSilverfishRenderer;
import com.jeff.pets.rendering.vanilla.skeleton.ClientSkeletonRenderer;
import com.jeff.pets.rendering.vanilla.slime.ClientSlimeRenderer;
import com.jeff.pets.rendering.vanilla.snowgolem.ClientSnowGolemRenderer;
import com.jeff.pets.rendering.vanilla.spider.ClientSpiderRenderer;
import com.jeff.pets.rendering.vanilla.squid.ClientSquidRenderer;
import com.jeff.pets.rendering.vanilla.stray.ClientStrayRenderer;
import com.jeff.pets.rendering.vanilla.strider.ClientStriderRenderer;
import com.jeff.pets.rendering.vanilla.tadpole.ClientTadpoleRenderer;
import com.jeff.pets.rendering.vanilla.turtle.ClientTurtleRenderer;
import com.jeff.pets.rendering.vanilla.vex.ClientVexRenderer;
import com.jeff.pets.rendering.vanilla.villager.ClientVillagerRenderer;
import com.jeff.pets.rendering.vanilla.vindicator.ClientVindicatorRenderer;
import com.jeff.pets.rendering.vanilla.wanderingtrader.ClientWanderingTraderRenderer;
import com.jeff.pets.rendering.vanilla.warden.ClientWardenRenderer;
import com.jeff.pets.rendering.vanilla.witch.ClientWitchRenderer;
import com.jeff.pets.rendering.vanilla.wither.ClientWitherRenderer;
import com.jeff.pets.rendering.vanilla.witherskeleton.ClientWitherSkeletonRenderer;
import com.jeff.pets.rendering.vanilla.wolf.ClientWolfRenderer;
import com.jeff.pets.rendering.vanilla.zombie.ClientZombieRenderer;
import com.jeff.pets.rendering.vanilla.zombievillager.ClientZombieVillagerModel;
import com.jeff.pets.rendering.vanilla.zombievillager.ClientZombieVillagerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.entity.EnderDragonRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import org.lwjgl.glfw.GLFW;

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

public class PetsClientInitializer implements ClientModInitializer {

    public static List<String> ADDONS = new ArrayList<>();

    /**
     * Misc rendering stuff
     */
    @Override
    public void onInitializeClient() {

        this.createKeyBinding();

        EntityRenderers.register(PetsInitializer.HEAD, HeadRenderer::new);
        EntityRenderers.register(PetsInitializer.DUCK, DuckRenderer::new);
        EntityRenderers.register(PetsInitializer.RACOON, RacoonRenderer::new);
        EntityRenderers.register(PetsInitializer.PENGUIN, PenguinRenderer::new);
        EntityRenderers.register(PetsInitializer.SHEEP, ClientSheepRenderer::new);
        EntityRenderers.register(PetsInitializer.CAT, ClientCatRenderer::new);
        EntityRenderers.register(PetsInitializer.ALLAY, ClientAllayRenderer::new);
        EntityRenderers.register(PetsInitializer.AXOLOTL, ClientAxolotlRenderer::new);
        EntityRenderers.register(PetsInitializer.BAT, ClientBatRenderer::new);
        EntityRenderers.register(PetsInitializer.CAMEL, ClientCamelRenderer::new);
        EntityRenderers.register(PetsInitializer.CHICKEN, ClientChickenRenderer::new);
        EntityRenderers.register(PetsInitializer.COD, ClientCodRenderer::new);
        EntityRenderers.register(PetsInitializer.COW, ClientCowRenderer::new);
        EntityRenderers.register(PetsInitializer.DONKEY, ClientDonkeyRenderer::new);
        EntityRenderers.register(PetsInitializer.FROG, ClientFrogRenderer::new);
        EntityRenderers.register(PetsInitializer.HORSE, ClientHorseRenderer::new);
        EntityRenderers.register(PetsInitializer.MOOSHROOM, ClientMooshroomRenderer::new);
        EntityRenderers.register(PetsInitializer.PARROT, ClientParrotRenderer::new);
        EntityRenderers.register(PetsInitializer.PIG, ClientPigRenderer::new);
        EntityRenderers.register(PetsInitializer.RABBIT, ClientRabbitRenderer::new);
        EntityRenderers.register(PetsInitializer.SALMON, ClientSalmonRenderer::new);
        EntityRenderers.register(PetsInitializer.SNOW_GOLEM, ClientSnowGolemRenderer::new);
        EntityRenderers.register(PetsInitializer.SQUID, ClientSquidRenderer::new);
        EntityRenderers.register(PetsInitializer.STRIDER, ClientStriderRenderer::new);
        EntityRenderers.register(PetsInitializer.TADPOLE, ClientTadpoleRenderer::new);
        EntityRenderers.register(PetsInitializer.TURTLE, ClientTurtleRenderer::new);
        EntityRenderers.register(PetsInitializer.VILLAGER, ClientVillagerRenderer::new);
        EntityRenderers.register(PetsInitializer.WANDERING_TRADER, ClientWanderingTraderRenderer::new);
        EntityRenderers.register(PetsInitializer.BEE, ClientBeeRenderer::new);
        EntityRenderers.register(PetsInitializer.CAVE_SPIDER, ClientCaveSpiderRenderer::new);
        EntityRenderers.register(PetsInitializer.DOLPHIN, ClientDolphinRenderer::new);
        EntityRenderers.register(PetsInitializer.ENDERMAN, ClientEndermanRenderer::new);
        EntityRenderers.register(PetsInitializer.FOX, ClientFoxRenderer::new);
        EntityRenderers.register(PetsInitializer.GOAT, ClientGoatRenderer::new);
        EntityRenderers.register(PetsInitializer.IRON_GOLEM, ClientIronGolemRenderer::new);
        EntityRenderers.register(PetsInitializer.LLAMA, ClientLlamaRenderer::new);
        EntityRenderers.register(PetsInitializer.PANDA, ClientPandaRenderer::new);
        EntityRenderers.register(PetsInitializer.PIGLIN, ClientPiglinRenderer::new);
        EntityRenderers.register(PetsInitializer.POLAR_BEAR, ClientPolarBearRenderer::new);
        EntityRenderers.register(PetsInitializer.PUFFERFISH, ClientPufferFishRenderer::new);
        EntityRenderers.register(PetsInitializer.SPIDER, ClientSpiderRenderer::new);
        EntityRenderers.register(PetsInitializer.WOLF, ClientWolfRenderer::new);
        EntityRenderers.register(PetsInitializer.ELDER_GUARDIAN_COOKIE, ClientElderGuardianRenderer::new);
        EntityRenderers.register(PetsInitializer.BLAZE, ClientBlazeRenderer::new);
        EntityRenderers.register(PetsInitializer.CREEPER, ClientCreeperRenderer::new);
        EntityRenderers.register(PetsInitializer.DROWNED, ClientDrownedRenderer::new);
        EntityRenderers.register(PetsInitializer.ENDERMITE, ClientEndermiteRenderer::new);
        EntityRenderers.register(PetsInitializer.EVOKER, ClientEvokerRenderer::new);
        EntityRenderers.register(PetsInitializer.GHAST, ClientGhastRenderer::new);
        EntityRenderers.register(PetsInitializer.GUARDIAN, ClientGuardianRenderer::new);
        EntityRenderers.register(PetsInitializer.HOGLIN, ClientHoglinRenderer::new);
        EntityRenderers.register(PetsInitializer.HUSK, ClientHuskRenderer::new);
        EntityRenderers.register(PetsInitializer.MAGMA_CUBE, ClientMagmaCubeRenderer::new);
        EntityRenderers.register(PetsInitializer.PHANTOM, ClientPhantomRenderer::new);
        EntityRenderers.register(PetsInitializer.PILLAGER, ClientPillagerRenderer::new);
        EntityRenderers.register(PetsInitializer.RAVAGER, ClientRavagerRenderer::new);
        EntityRenderers.register(PetsInitializer.SHULKER, ClientShulkerRenderer::new);
        EntityRenderers.register(PetsInitializer.SILVERFISH, ClientSilverfishRenderer::new);
        EntityRenderers.register(PetsInitializer.SKELETON, ClientSkeletonRenderer::new);
        EntityRenderers.register(PetsInitializer.SLIME, ClientSlimeRenderer::new);
        EntityRenderers.register(PetsInitializer.STRAY, ClientStrayRenderer::new);
        EntityRenderers.register(PetsInitializer.VEX, ClientVexRenderer::new);
        EntityRenderers.register(PetsInitializer.VINDICATOR, ClientVindicatorRenderer::new);
        EntityRenderers.register(PetsInitializer.WARDEN, ClientWardenRenderer::new);
        EntityRenderers.register(PetsInitializer.WITCH, ClientWitchRenderer::new);
        EntityRenderers.register(PetsInitializer.WITHER_SKELETON, ClientWitherSkeletonRenderer::new);
        EntityRenderers.register(PetsInitializer.ZOMBIE, ClientZombieRenderer::new);
        EntityRenderers.register(PetsInitializer.ZOMBIE_VILLAGER, ClientZombieVillagerRenderer::new);
        EntityRenderers.register(PetsInitializer.ENDER_DRAGON, ClientEnderDragonRenderer::new);
        EntityRenderers.register(PetsInitializer.WITHER, ClientWitherRenderer::new);
        EntityRenderers.register(PetsInitializer.BATATO, BatatoRenderer::new);
        EntityRenderers.register(PetsInitializer.ANGRY_GHAST, AngryGhastRenderer::new);
        EntityRenderers.register(PetsInitializer.BATATO, BatatoRenderer::new);
        EntityRenderers.register(PetsInitializer.DIAMOND_CHICKEN, DiamondChickenRenderer::new);
        EntityRenderers.register(PetsInitializer.LOVE_GOLEM, LoveGolemRenderer::new);
        EntityRenderers.register(PetsInitializer.MEGA_SPUD, MegaSpudRenderer::new);
        EntityRenderers.register(PetsInitializer.MOON_COW, MoonCowRenderer::new);
        EntityRenderers.register(PetsInitializer.NERD_CREEPER, NerdCreeperRenderer::new);
        EntityRenderers.register(PetsInitializer.PINK_WITHER, PinkWitherRenderer::new);
        EntityRenderers.register(PetsInitializer.PLAGUEWHALE_SLAB, PlaguewhaleRenderer::new);
        EntityRenderers.register(PetsInitializer.POISONOUS_POTATO_ZOMBIE, PoisonousPotatoZombieRenderer::new);
        EntityRenderers.register(PetsInitializer.POTATO_HUSK, PotatoHuskRenderer::new);
        EntityRenderers.register(PetsInitializer.RAY_TRACING, RayTracingRenderer::new);
        EntityRenderers.register(PetsInitializer.REDSTONE_BUG, RedstoneBugRenderer::new);
        EntityRenderers.register(PetsInitializer.SMILING_CREEPER, SmilingCreeperRenderer::new);
        EntityRenderers.register(PetsInitializer.TOXIFIN_SLAB, ToxifinRenderer::new);
        EntityRenderers.register(PetsInitializer.TRAITOR, TraitorRenderer::new);
        EntityRenderers.register(PetsInitializer.DUMBO_OCTOPUS, DumboOctopusRenderer::new);
        EntityRenderers.register(PetsInitializer.KOI, KoiRenderer::new);
        EntityRenderers.register(PetsInitializer.STINGRAY, StingrayRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(HeadModel.LAYER_LOCATION, HeadModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RacoonRenderer.RACOON_LOCATION, RacoonModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(DuckModel.LAYER_LOCATION, DuckModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(PenguinModel.PENGUIN_LOCATION, PenguinModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ClientSheepRenderer.SHEEP_LOCATION, ClientSheepModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientSheepWoolLayer.SHEEP_WOOL_LOCATION, ClientSheepFurModel::createFurLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientSheepWoolLayer.SHEEP_WOOL_BABY_LOCATION, ClientSheepFurModel::createFurLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientCatRenderer.CAT_LOCATION, ClientCatRenderer::createCatBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientAllayRenderer.ALLAY_TEXTURE, AllayModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientAxolotlRenderer.AXOLOTL_LOCATION, AxolotlModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientBatRenderer.BAT_LOCATION, BatModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientCamelRenderer.CAMEL_LOCATION, CamelModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientChickenRenderer.CHICKEN_LOCATION, ClientChickenModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientCodRenderer.COD_LOCATION, CodModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientCowRenderer.COW_LOCATION, ClientCowModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientDonkeyRenderer.DONKEY_LOCATION, ClientDonkeyRenderer::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientFrogRenderer.FROG_LOCATION, FrogModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientHorseRenderer.HORSE_LOCATION, ClientHorseRenderer::createBaseHorseLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientMooshroomRenderer.MOOSHROOM_LOCATION, ClientCowModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientParrotRenderer.PARROT_LOCATION, ParrotModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientPigRenderer.PIG_LOCATION, ClientPigRenderer::createBasePigModel);
        EntityModelLayerRegistry.registerModelLayer(ClientRabbitRenderer.RABBIT_LOCATION, ClientRabbitRenderer::createBaseRabbitLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientSalmonRenderer.SALMON_LOCATION, ClientSalmonModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ClientSnowGolemRenderer.SNOW_GOLEM, SnowGolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientSquidRenderer.SQUID_LOCATION, SquidModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientStriderRenderer.STRIDER_LOCATION, StriderModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientTadpoleRenderer.TADPOLE_LOCATION, TadpoleModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientTurtleRenderer.TURTLE_LOCATION, TurtleModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientVillagerRenderer.VILLAGER_LOCATION, ClientVillagerRenderer::createBaseVillagerLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientWanderingTraderRenderer.WANDERING_TRADER_LOCATION, ClientVillagerRenderer::createBaseVillagerLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientBeeRenderer.BEE_LOCATION, BeeModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientCaveSpiderRenderer.CAVE_SPIDER_LOCATION, SpiderModel::createSpiderBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientDolphinRenderer.DOLPHIN_LOCATION, DolphinModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientEndermanRenderer.ENDERMAN_LOCATION, EndermanModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientFoxRenderer.FOX_LOCATION, ClientFoxModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientGoatRenderer.GOAT_LOCATION, ClientGoatModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientIronGolemRenderer.IRON_GOLEM_LOCATION, IronGolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientLlamaRenderer.LLAMA_LOCATION, ClientLlamaRenderer::createLlamaLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientPandaRenderer.PANDA_LOCAITON, PandaModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientPiglinRenderer.PIGLIN_LOCATION, ClientPiglinRenderer::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientPolarBearRenderer.POLAR_BEAR_LOCATION, ClientPolarBearRenderer::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientPufferFishRenderer.PUFFERFISH_LOCATION, PufferfishBigModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientSpiderRenderer.SPIDER_LOCATION, SpiderModel::createSpiderBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientWolfRenderer.WOLF_LOCATION, ClientWolfRenderer::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientElderGuardianRenderer.ELDER_GUARDIAN_LOCATION, GuardianModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientBlazeRenderer.BLAZE_LOCATION, BlazeModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientCreeperRenderer.CREEPER_LOCATION, ClientCreeperRenderer::createBaseCreeperLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientDrownedRenderer.DROWNED_LOCATION, ClientDrownedRenderer::createBaseDrownedLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientEndermiteRenderer.ENDERMITE_LOCATION, EndermiteModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientEvokerRenderer.EVOKER_LOCATION, ClientEvokerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientGhastRenderer.GHAST_LOCATION, GhastModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientGuardianRenderer.GUARDIAN_LOCATION, GuardianModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientHoglinRenderer.HOGLIN_LOCATION, ClientHoglinModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientHuskRenderer.HUSK_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientMagmaCubeRenderer.MAGMA_CUBE_LOCATION, SlimeModel::createInnerBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientPhantomRenderer.PHANTOM_LOCATION, PhantomModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientPillagerRenderer.PILLAGER_LOCATION, ClientPillagerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientRavagerRenderer.RAVAGER_LOCATION, RavagerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientShulkerRenderer.SHULKER_LOCATION, ShulkerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientSilverfishRenderer.SILVERFISH_LOCATION, SilverfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientSkeletonRenderer.SKELETON_LOCATION, SkeletonModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientSlimeRenderer.SLIME_LOCATION, SlimeModel::createInnerBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientStrayRenderer.STRAY_LOCATION, SkeletonModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientVexRenderer.VEX_LOCATION, VexModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientVindicatorRenderer.VINDICATOR_LOCATION, ClientEvokerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientWardenRenderer.WARDEN_LOCATION, WardenModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientWitchRenderer.WITCH_LOCATION, WitchModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientWitherSkeletonRenderer.WITHER_SKELETON_LOCATION, SkeletonModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientZombieRenderer.ZOMBIE_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientZombieVillagerRenderer.ZOMBIE_VILLAGER_LOCATION, ClientZombieVillagerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientEnderDragonRenderer.ENDER_DRAGON_LOCATION, EnderDragonRenderer::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClientWitherRenderer.WITHER_LOCATION, ClientWitherRenderer::createBaseWitherLayer);
        EntityModelLayerRegistry.registerModelLayer(AngryGhastRenderer.ANGRY_GHAST_LOCATION, GhastModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BatatoRenderer.BATATO_LOCAITON, BatatoModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(DiamondChickenRenderer.DIAMOND_CHICKEN_LOCATION, ClientChickenModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(LoveGolemRenderer.LOVE_GOLEM_LOCATION, IronGolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(MegaSpudRenderer.MEGA_SPUD_LOCATION, MegaSpudModel::createInnerBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(MegaSpudOuterLayer.MEGA_SPUD_OUTER_LOCATION, MegaSpudModel::createOuterBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(MoonCowRenderer.MOON_COW_LOCATION, LegacyCowModel::createLegacyCowModel);
        EntityModelLayerRegistry.registerModelLayer(NerdCreeperRenderer.NERD_CREEPER_LOCATION, ClientCreeperRenderer::createBaseCreeperLayer);
        EntityModelLayerRegistry.registerModelLayer(PinkWitherRenderer.PINK_WITHER_LOCATION, ClientWitherRenderer::createBaseWitherLayer);
        EntityModelLayerRegistry.registerModelLayer(PlaguewhaleRenderer.PLAGUEWHALE_LOCATION, ToxifinSlabModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(PoisonousPotatoZombieRenderer.POISONOUS_POTATO_ZOMBIE_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
        EntityModelLayerRegistry.registerModelLayer(PotatoHuskRenderer.POTATO_HUSK_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
        EntityModelLayerRegistry.registerModelLayer(RayTracingRenderer.RAY_TRACING_LOCATION, RayTracingRenderer::createBasePlayerBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(RedstoneBugRenderer.REDSTONE_BUG_LOCATION, SilverfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(SmilingCreeperRenderer.SMILING_CREEPER_LOCATION, ClientCreeperRenderer::createBaseCreeperLayer);
        EntityModelLayerRegistry.registerModelLayer(ToxifinRenderer.TOXIFIN_LOCATION, ToxifinSlabModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(TraitorRenderer.TRAITOR_LOCATION, ClientEvokerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(DumboOctopusRenderer.DUMBO_OCTOPUS_LOCATION, DumboOctopusModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(KoiRenderer.KOI_LOCATION, KoiModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(StingrayRenderer.STINGRAY_LOCATION, StingrayModel::createBodyLayer);

        ClientLifecycleEvents.CLIENT_STARTED.register((mc) -> {
            LOGGER.info("PetsMod addons loaded:{}", ADDONS);
        });
    }

    /**
     * Registers the key binding and an {@code END_CLIENT_TICK} event to check if the key
     * is pressed
     */
    void createKeyBinding() {
        KeyMapping keyMapping = KeyBindingHelper.registerKeyBinding(new KeyMapping("Open Pets Menu", GLFW.GLFW_KEY_P, "petsmod.keymapping"));

        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            if (keyMapping.consumeClick()) {
                client.setScreen(PetsConfigScreen.getInstance().getModConfigScreenFactory().create(client.screen));
            }
        });
    }
}