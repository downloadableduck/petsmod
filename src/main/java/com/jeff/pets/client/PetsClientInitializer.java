package com.jeff.pets.client;

import com.jeff.pets.PetsInitializer;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

import static com.jeff.pets.PetsInitializer.MOD_ID;

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
@Mod(value=MOD_ID, dist = Dist.CLIENT)
public class PetsClientInitializer {

    public static List<String> ADDONS = new ArrayList<>();

    public static KeyMapping openConfigScreen;

    /**
     * Misc rendering stuff
     */
    public PetsClientInitializer(IEventBus bus) {
        bus.addListener(this::registerModelLayers);
        bus.addListener(this::register);
        bus.addListener(this::createKeyBinding);
    }

    void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HeadModel.LAYER_LOCATION, HeadModel::getTexturedModelData);
        event.registerLayerDefinition(RacoonRenderer.RACOON_LOCATION, RacoonModel::getTexturedModelData);
        event.registerLayerDefinition(DuckModel.LAYER_LOCATION, DuckModel::getTexturedModelData);
        event.registerLayerDefinition(PenguinModel.PENGUIN_LOCATION, PenguinModel::getTexturedModelData);
        event.registerLayerDefinition(ClientSheepRenderer.SHEEP_LOCATION, ClientSheepModel::createBodyLayer);
        event.registerLayerDefinition(ClientSheepWoolLayer.SHEEP_WOOL_LOCATION, ClientSheepModel::createBodyLayer);
        event.registerLayerDefinition(ClientCatRenderer.CAT_LOCATION, ClientCatRenderer::createCatBodyLayer);
        event.registerLayerDefinition(ClientAllayRenderer.ALLAY_TEXTURE, AllayModel::createBodyLayer);
        event.registerLayerDefinition(ClientArmadilloRenderer.ARMADILLO_LOCATION, AdultArmadilloModel::createBodyLayer);
        event.registerLayerDefinition(ClientAxolotlRenderer.AXOLOTL_LOCATION, AdultAxolotlModel::createBodyLayer);
        event.registerLayerDefinition(ClientBatRenderer.BAT_LOCATION, BatModel::createBodyLayer);
        event.registerLayerDefinition(ClientCamelRenderer.CAMEL_LOCATION, AdultCamelModel::createBodyLayer);
        event.registerLayerDefinition(ClientChickenRenderer.CHICKEN_LOCATION, ClientChickenModel::createBodyLayer);
        event.registerLayerDefinition(ClientCodRenderer.COD_LOCATION, CodModel::createBodyLayer);
        event.registerLayerDefinition(ClientCopperGolemRenderer.COPPER_GOLEM_LOCATION, CopperGolemModel::createBodyLayer);
        event.registerLayerDefinition(ClientCowRenderer.COW_LOCATION, ClientCowModel::createBodyLayer);
        event.registerLayerDefinition(ClientDonkeyRenderer.DONKEY_LOCATION, ClientDonkeyRenderer::createBodyLayer);
        event.registerLayerDefinition(ClientFrogRenderer.FROG_LOCATION, FrogModel::createBodyLayer);
        event.registerLayerDefinition(ClientHorseRenderer.HORSE_LOCATION, ClientHorseRenderer::createBaseHorseLayer);
        event.registerLayerDefinition(ClientMooshroomRenderer.MOOSHROOM_LOCATION, ClientCowModel::createBodyLayer);
        event.registerLayerDefinition(ClientParrotRenderer.PARROT_LOCATION, ParrotModel::createBodyLayer);
        event.registerLayerDefinition(ClientPigRenderer.PIG_LOCATION, ClientPigRenderer::createBasePigModel);
        event.registerLayerDefinition(ClientRabbitRenderer.RABBIT_LOCATION, ClientRabbitRenderer::createBaseRabbitLayer);
        event.registerLayerDefinition(ClientSalmonRenderer.SALMON_LOCATION, ClientSalmonModel::getTexturedModelData);
        event.registerLayerDefinition(ClientSnifferRenderer.SNIFFER_LOCATION, SnifferModel::createBodyLayer);
        event.registerLayerDefinition(ClientSnowGolemRenderer.SNOW_GOLEM, SnowGolemModel::createBodyLayer);
        event.registerLayerDefinition(ClientSquidRenderer.SQUID_LOCATION, SquidModel::createBodyLayer);
        event.registerLayerDefinition(ClientStriderRenderer.STRIDER_LOCATION, AdultStriderModel::createBodyLayer);
        event.registerLayerDefinition(ClientTadpoleRenderer.TADPOLE_LOCATION, TadpoleModel::createBodyLayer);
        event.registerLayerDefinition(ClientTurtleRenderer.TURTLE_LOCATION, AdultTurtleModel::createBodyLayer);
        event.registerLayerDefinition(ClientVillagerRenderer.VILLAGER_LOCATION, ClientVillagerRenderer::createBaseVillagerLayer);
        event.registerLayerDefinition(ClientWanderingTraderRenderer.WANDERING_TRADER_LOCATION, ClientVillagerRenderer::createBaseVillagerLayer);
        event.registerLayerDefinition(ClientBeeRenderer.BEE_LOCATION, AdultBeeModel::createBodyLayer);
        event.registerLayerDefinition(ClientCaveSpiderRenderer.CAVE_SPIDER_LOCATION, ClientCaveSpiderModel::createBodyLayer);
        event.registerLayerDefinition(ClientDolphinRenderer.DOLPHIN_LOCATION, DolphinModel::createBodyLayer);
        event.registerLayerDefinition(ClientEndermanRenderer.ENDERMAN_LOCATION, EndermanModel::createBodyLayer);
        event.registerLayerDefinition(ClientFoxRenderer.FOX_LOCATION, ClientFoxModel::createBodyLayer);
        event.registerLayerDefinition(ClientGoatRenderer.GOAT_LOCATION, ClientGoatModel::createBodyLayer);
        event.registerLayerDefinition(ClientIronGolemRenderer.IRON_GOLEM_LOCATION, IronGolemModel::createBodyLayer);
        event.registerLayerDefinition(ClientLlamaRenderer.LLAMA_LOCATION, ClientLlamaRenderer::createLlamaLayer);
        event.registerLayerDefinition(ClientNautilusRenderer.NAUTILUS_LOCATION, NautilusModel::createBodyLayer);
        event.registerLayerDefinition(ClientPandaRenderer.PANDA_LOCAITON, PandaModel::createBodyLayer);
        event.registerLayerDefinition(ClientPiglinRenderer.PIGLIN_LOCATION, ClientPiglinRenderer::createBodyLayer);
        event.registerLayerDefinition(ClientPolarBearRenderer.POLAR_BEAR_LOCATION, ClientPolarBearRenderer::createBodyLayer);
        event.registerLayerDefinition(ClientPufferFishRenderer.PUFFERFISH_LOCATION, PufferfishBigModel::createBodyLayer);
        event.registerLayerDefinition(ClientSpiderRenderer.SPIDER_LOCATION, SpiderModel::createSpiderBodyLayer);
        event.registerLayerDefinition(ClientWolfRenderer.WOLF_LOCATION, ClientWolfRenderer::createBodyLayer);
        event.registerLayerDefinition(ClientElderGuardianRenderer.ELDER_GUARDIAN_LOCATION, GuardianModel::createBodyLayer);
        event.registerLayerDefinition(ClientHappyGhastRenderer.GHAST_LOCATION, ClientHappyGhastRenderer::createGhastBodyLayer);
        event.registerLayerDefinition(ClientBlazeRenderer.BLAZE_LOCATION, BlazeModel::createBodyLayer);
        event.registerLayerDefinition(ClientBoggedRenderer.BOGGED_LOCATION, BoggedModel::createBodyLayer);
        event.registerLayerDefinition(ClientBreezeRenderer.BREEZE_LOCATION, BreezeModel::createBodyLayer);
        event.registerLayerDefinition(ClientCreakingRenderer.CREAKING_LOCATION, CreakingModel::createBodyLayer);
        event.registerLayerDefinition(ClientCreeperRenderer.CREEPER_LOCATION, ClientCreeperRenderer::createBaseCreeperLayer);
        event.registerLayerDefinition(ClientDrownedRenderer.DROWNED_LOCATION, ClientDrownedRenderer::createBaseDrownedLayer);
        event.registerLayerDefinition(ClientEndermiteRenderer.ENDERMITE_LOCATION, EndermiteModel::createBodyLayer);
        event.registerLayerDefinition(ClientEvokerRenderer.EVOKER_LOCATION, ClientEvokerModel::createBodyLayer);
        event.registerLayerDefinition(ClientGhastRenderer.GHAST_LOCATION, GhastModel::createBodyLayer);
        event.registerLayerDefinition(ClientGuardianRenderer.GUARDIAN_LOCATION, GuardianModel::createBodyLayer);
        event.registerLayerDefinition(ClientHoglinRenderer.HOGLIN_LOCATION, ClientHoglinModel::createBodyLayer);
        event.registerLayerDefinition(ClientHuskRenderer.HUSK_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
        event.registerLayerDefinition(ClientMagmaCubeRenderer.MAGMA_CUBE_LOCATION, MagmaCubeModel::createBodyLayer);
        event.registerLayerDefinition(ClientParchedRenderer.PARCHED_LOCATION, SkeletonModel::createBodyLayer);
        event.registerLayerDefinition(ClientPhantomRenderer.PHANTOM_LOCATION, PhantomModel::createBodyLayer);
        event.registerLayerDefinition(ClientPillagerRenderer.PILLAGER_LOCATION, ClientPillagerModel::createBodyLayer);
        event.registerLayerDefinition(ClientRavagerRenderer.RAVAGER_LOCATION, RavagerModel::createBodyLayer);
        event.registerLayerDefinition(ClientShulkerRenderer.SHULKER_LOCATION, ShulkerModel::createBodyLayer);
        event.registerLayerDefinition(ClientSilverfishRenderer.SILVERFISH_LOCATION, SilverfishModel::createBodyLayer);
        event.registerLayerDefinition(ClientSkeletonRenderer.SKELETON_LOCATION, SkeletonModel::createBodyLayer);
        event.registerLayerDefinition(ClientSlimeRenderer.SLIME_LOCATION, SlimeModel::createInnerBodyLayer);
        event.registerLayerDefinition(ClientStrayRenderer.STRAY_LOCATION, SkeletonModel::createBodyLayer);
        event.registerLayerDefinition(ClientVexRenderer.VEX_LOCATION, VexModel::createBodyLayer);
        event.registerLayerDefinition(ClientVindicatorRenderer.VINDICATOR_LOCATION, ClientEvokerModel::createBodyLayer);
        event.registerLayerDefinition(ClientWardenRenderer.WARDEN_LOCATION, WardenModel::createBodyLayer);
        event.registerLayerDefinition(ClientWitchRenderer.WITCH_LOCATION, WitchModel::createBodyLayer);
        event.registerLayerDefinition(ClientWitherSkeletonRenderer.WITHER_SKELETON_LOCATION, SkeletonModel::createBodyLayer);
        event.registerLayerDefinition(ClientZombieRenderer.ZOMBIE_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
        event.registerLayerDefinition(ClientZombieVillagerRenderer.ZOMBIE_VILLAGER_LOCATION, ClientZombieVillagerModel::createBodyLayer);
        event.registerLayerDefinition(ClientEnderDragonRenderer.ENDER_DRAGON_LOCATION, EnderDragonModel::createBodyLayer);
        event.registerLayerDefinition(ClientWitherRenderer.WITHER_LOCATION, ClientWitherRenderer::createBaseWitherLayer);
        event.registerLayerDefinition(AngryGhastRenderer.ANGRY_GHAST_LOCATION, GhastModel::createBodyLayer);
        event.registerLayerDefinition(BatatoRenderer.BATATO_LOCAITON, BatatoModel::createBodyLayer);
        event.registerLayerDefinition(DiamondChickenRenderer.DIAMOND_CHICKEN_LOCATION, ClientChickenModel::createBodyLayer);
        event.registerLayerDefinition(LoveGolemRenderer.LOVE_GOLEM_LOCATION, IronGolemModel::createBodyLayer);
        event.registerLayerDefinition(MegaSpudRenderer.MEGA_SPUD_LOCATION, MegaSpudModel::createInnerBodyLayer);
        event.registerLayerDefinition(MegaSpudOuterLayer.MEGA_SPUD_OUTER_LOCATION, MegaSpudModel::createOuterBodyLayer);
        event.registerLayerDefinition(MoonCowRenderer.MOON_COW_LOCATION, LegacyCowModel::createLegacyCowModel);
        event.registerLayerDefinition(NerdCreeperRenderer.NERD_CREEPER_LOCATION, ClientCreeperRenderer::createBaseCreeperLayer);
        event.registerLayerDefinition(PinkWitherRenderer.PINK_WITHER_LOCATION, ClientWitherRenderer::createBaseWitherLayer);
        event.registerLayerDefinition(PlaguewhaleRenderer.PLAGUEWHALE_LOCATION, ToxifinSlabModel::createBodyLayer);
        event.registerLayerDefinition(PoisonousPotatoZombieRenderer.POISONOUS_POTATO_ZOMBIE_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
        event.registerLayerDefinition(PotatoHuskRenderer.POTATO_HUSK_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
        event.registerLayerDefinition(RayTracingRenderer.RAY_TRACING_LOCATION, RayTracingRenderer::createBasePlayerBodyLayer);
        event.registerLayerDefinition(RedstoneBugRenderer.REDSTONE_BUG_LOCATION, SilverfishModel::createBodyLayer);
        event.registerLayerDefinition(SmilingCreeperRenderer.SMILING_CREEPER_LOCATION, ClientCreeperRenderer::createBaseCreeperLayer);
        event.registerLayerDefinition(ToxifinRenderer.TOXIFIN_LOCATION, ToxifinSlabModel::createBodyLayer);
        event.registerLayerDefinition(TraitorRenderer.TRAITOR_LOCATION, ClientEvokerModel::createBodyLayer);
        event.registerLayerDefinition(DumboOctopusRenderer.DUMBO_OCTOPUS_LOCATION, DumboOctopusModel::createBodyLayer);
        event.registerLayerDefinition(KoiRenderer.KOI_LOCATION, KoiModel::createBodyLayer);
        event.registerLayerDefinition(StingrayRenderer.STINGRAY_LOCATION, StingrayModel::createBodyLayer);
    }

    void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(PetsInitializer.HEAD.get(), HeadRenderer::new);
        event.registerEntityRenderer(PetsInitializer.DUCK.get(), DuckRenderer::new);
        event.registerEntityRenderer(PetsInitializer.RACOON.get(), RacoonRenderer::new);
        event.registerEntityRenderer(PetsInitializer.PENGUIN.get(), PenguinRenderer::new);
        event.registerEntityRenderer(PetsInitializer.SHEEP.get(), ClientSheepRenderer::new);
        event.registerEntityRenderer(PetsInitializer.CAT.get(), ClientCatRenderer::new);
        event.registerEntityRenderer(PetsInitializer.ALLAY.get(), ClientAllayRenderer::new);
        event.registerEntityRenderer(PetsInitializer.ARMADILLO.get(), ClientArmadilloRenderer::new);
        event.registerEntityRenderer(PetsInitializer.AXOLOTL.get(), ClientAxolotlRenderer::new);
        event.registerEntityRenderer(PetsInitializer.BAT.get(), ClientBatRenderer::new);
        event.registerEntityRenderer(PetsInitializer.CAMEL.get(), ClientCamelRenderer::new);
        event.registerEntityRenderer(PetsInitializer.CHICKEN.get(), ClientChickenRenderer::new);
        event.registerEntityRenderer(PetsInitializer.COD.get(), ClientCodRenderer::new);
        event.registerEntityRenderer(PetsInitializer.COPPER_GOLEM.get(), ClientCopperGolemRenderer::new);
        event.registerEntityRenderer(PetsInitializer.COW.get(), ClientCowRenderer::new);
        event.registerEntityRenderer(PetsInitializer.DONKEY.get(), ClientDonkeyRenderer::new);
        event.registerEntityRenderer(PetsInitializer.FROG.get(), ClientFrogRenderer::new);
        event.registerEntityRenderer(PetsInitializer.HORSE.get(), ClientHorseRenderer::new);
        event.registerEntityRenderer(PetsInitializer.MOOSHROOM.get(), ClientMooshroomRenderer::new);
        event.registerEntityRenderer(PetsInitializer.PARROT.get(), ClientParrotRenderer::new);
        event.registerEntityRenderer(PetsInitializer.PIG.get(), ClientPigRenderer::new);
        event.registerEntityRenderer(PetsInitializer.RABBIT.get(), ClientRabbitRenderer::new);
        event.registerEntityRenderer(PetsInitializer.SALMON.get(), ClientSalmonRenderer::new);
        event.registerEntityRenderer(PetsInitializer.SNIFFER.get(), ClientSnifferRenderer::new);
        event.registerEntityRenderer(PetsInitializer.SNOW_GOLEM.get(), ClientSnowGolemRenderer::new);
        event.registerEntityRenderer(PetsInitializer.SQUID.get(), ClientSquidRenderer::new);
        event.registerEntityRenderer(PetsInitializer.STRIDER.get(), ClientStriderRenderer::new);
        event.registerEntityRenderer(PetsInitializer.TADPOLE.get(), ClientTadpoleRenderer::new);
        event.registerEntityRenderer(PetsInitializer.TURTLE.get(), ClientTurtleRenderer::new);
        event.registerEntityRenderer(PetsInitializer.VILLAGER.get(), ClientVillagerRenderer::new);
        event.registerEntityRenderer(PetsInitializer.WANDERING_TRADER.get(), ClientWanderingTraderRenderer::new);
        event.registerEntityRenderer(PetsInitializer.BEE.get(), ClientBeeRenderer::new);
        event.registerEntityRenderer(PetsInitializer.CAVE_SPIDER.get(), ClientCaveSpiderRenderer::new);
        event.registerEntityRenderer(PetsInitializer.DOLPHIN.get(), ClientDolphinRenderer::new);
        event.registerEntityRenderer(PetsInitializer.ENDERMAN.get(), ClientEndermanRenderer::new);
        event.registerEntityRenderer(PetsInitializer.FOX.get(), ClientFoxRenderer::new);
        event.registerEntityRenderer(PetsInitializer.GOAT.get(), ClientGoatRenderer::new);
        event.registerEntityRenderer(PetsInitializer.IRON_GOLEM.get(), ClientIronGolemRenderer::new);
        event.registerEntityRenderer(PetsInitializer.LLAMA.get(), ClientLlamaRenderer::new);
        event.registerEntityRenderer(PetsInitializer.NAUTILUS.get(), ClientNautilusRenderer::new);
        event.registerEntityRenderer(PetsInitializer.PANDA.get(), ClientPandaRenderer::new);
        event.registerEntityRenderer(PetsInitializer.PIGLIN.get(), ClientPiglinRenderer::new);
        event.registerEntityRenderer(PetsInitializer.POLAR_BEAR.get(), ClientPolarBearRenderer::new);
        event.registerEntityRenderer(PetsInitializer.PUFFERFISH.get(), ClientPufferFishRenderer::new);
        event.registerEntityRenderer(PetsInitializer.SPIDER.get(), ClientSpiderRenderer::new);
        event.registerEntityRenderer(PetsInitializer.WOLF.get(), ClientWolfRenderer::new);
        event.registerEntityRenderer(PetsInitializer.ELDER_GUARDIAN_COOKIE.get(), ClientElderGuardianRenderer::new);
        event.registerEntityRenderer(PetsInitializer.HAPPY_GHAST.get(), ClientHappyGhastRenderer::new);
        event.registerEntityRenderer(PetsInitializer.BLAZE.get(), ClientBlazeRenderer::new);
        event.registerEntityRenderer(PetsInitializer.BOGGED.get(), ClientBoggedRenderer::new);
        event.registerEntityRenderer(PetsInitializer.BREEZE.get(), ClientBreezeRenderer::new);
        event.registerEntityRenderer(PetsInitializer.CREAKING.get(), ClientCreakingRenderer::new);
        event.registerEntityRenderer(PetsInitializer.CREEPER.get(), ClientCreeperRenderer::new);
        event.registerEntityRenderer(PetsInitializer.DROWNED.get(), ClientDrownedRenderer::new);
        event.registerEntityRenderer(PetsInitializer.ENDERMITE.get(), ClientEndermiteRenderer::new);
        event.registerEntityRenderer(PetsInitializer.EVOKER.get(), ClientEvokerRenderer::new);
        event.registerEntityRenderer(PetsInitializer.GHAST.get(), ClientGhastRenderer::new);
        event.registerEntityRenderer(PetsInitializer.GUARDIAN.get(), ClientGuardianRenderer::new);
        event.registerEntityRenderer(PetsInitializer.HOGLIN.get(), ClientHoglinRenderer::new);
        event.registerEntityRenderer(PetsInitializer.HUSK.get(), ClientHuskRenderer::new);
        event.registerEntityRenderer(PetsInitializer.MAGMA_CUBE.get(), ClientMagmaCubeRenderer::new);
        event.registerEntityRenderer(PetsInitializer.PARCHED.get(), ClientParchedRenderer::new);
        event.registerEntityRenderer(PetsInitializer.PHANTOM.get(), ClientPhantomRenderer::new);
        event.registerEntityRenderer(PetsInitializer.PILLAGER.get(), ClientPillagerRenderer::new);
        event.registerEntityRenderer(PetsInitializer.RAVAGER.get(), ClientRavagerRenderer::new);
        event.registerEntityRenderer(PetsInitializer.SHULKER.get(), ClientShulkerRenderer::new);
        event.registerEntityRenderer(PetsInitializer.SILVERFISH.get(), ClientSilverfishRenderer::new);
        event.registerEntityRenderer(PetsInitializer.SKELETON.get(), ClientSkeletonRenderer::new);
        event.registerEntityRenderer(PetsInitializer.SLIME.get(), ClientSlimeRenderer::new);
        event.registerEntityRenderer(PetsInitializer.STRAY.get(), ClientStrayRenderer::new);
        event.registerEntityRenderer(PetsInitializer.VEX.get(), ClientVexRenderer::new);
        event.registerEntityRenderer(PetsInitializer.VINDICATOR.get(), ClientVindicatorRenderer::new);
        event.registerEntityRenderer(PetsInitializer.WARDEN.get(), ClientWardenRenderer::new);
        event.registerEntityRenderer(PetsInitializer.WITCH.get(), ClientWitchRenderer::new);
        event.registerEntityRenderer(PetsInitializer.WITHER_SKELETON.get(), ClientWitherSkeletonRenderer::new);
        event.registerEntityRenderer(PetsInitializer.ZOMBIE.get(), ClientZombieRenderer::new);
        event.registerEntityRenderer(PetsInitializer.ZOMBIE_VILLAGER.get(), ClientZombieVillagerRenderer::new);
        event.registerEntityRenderer(PetsInitializer.ENDER_DRAGON.get(), ClientEnderDragonRenderer::new);
        event.registerEntityRenderer(PetsInitializer.WITHER.get(), ClientWitherRenderer::new);
        event.registerEntityRenderer(PetsInitializer.ANGRY_GHAST.get(), AngryGhastRenderer::new);
        event.registerEntityRenderer(PetsInitializer.BATATO.get(), BatatoRenderer::new);
        event.registerEntityRenderer(PetsInitializer.DIAMOND_CHICKEN.get(), DiamondChickenRenderer::new);
        event.registerEntityRenderer(PetsInitializer.LOVE_GOLEM.get(), LoveGolemRenderer::new);
        event.registerEntityRenderer(PetsInitializer.MEGA_SPUD.get(), MegaSpudRenderer::new);
        event.registerEntityRenderer(PetsInitializer.MOON_COW.get(), MoonCowRenderer::new);
        event.registerEntityRenderer(PetsInitializer.NERD_CREEPER.get(), NerdCreeperRenderer::new);
        event.registerEntityRenderer(PetsInitializer.PINK_WITHER.get(), PinkWitherRenderer::new);
        event.registerEntityRenderer(PetsInitializer.PLAGUEWHALE_SLAB.get(), PlaguewhaleRenderer::new);
        event.registerEntityRenderer(PetsInitializer.POISONOUS_POTATO_ZOMBIE.get(), PoisonousPotatoZombieRenderer::new);
        event.registerEntityRenderer(PetsInitializer.POTATO_HUSK.get(), PotatoHuskRenderer::new);
        event.registerEntityRenderer(PetsInitializer.RAY_TRACING.get(), RayTracingRenderer::new);
        event.registerEntityRenderer(PetsInitializer.REDSTONE_BUG.get(), RedstoneBugRenderer::new);
        event.registerEntityRenderer(PetsInitializer.SMILING_CREEPER.get(), SmilingCreeperRenderer::new);
        event.registerEntityRenderer(PetsInitializer.TOXIFIN_SLAB.get(), ToxifinRenderer::new);
        event.registerEntityRenderer(PetsInitializer.TRAITOR.get(), TraitorRenderer::new);
        event.registerEntityRenderer(PetsInitializer.DUMBO_OCTOPUS.get(), DumboOctopusRenderer::new);
        event.registerEntityRenderer(PetsInitializer.KOI.get(), KoiRenderer::new);
        event.registerEntityRenderer(PetsInitializer.STINGRAY.get(), StingrayRenderer::new);
    }

    /**
     * Registers the key binding and an {@code END_CLIENT_TICK} event to check if the key
     * is pressed
     */

     void createKeyBinding(RegisterKeyMappingsEvent event) {
        openConfigScreen = new KeyMapping("Open Pets Menu", GLFW.GLFW_KEY_P, new KeyMapping.Category(Identifier.fromNamespaceAndPath(MOD_ID, "petsmod.keymapping")));

        event.register(openConfigScreen);
    }

    @SubscribeEvent
    public static void printAddons(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            PetsInitializer.LOGGER.info("PetsMod addons loaded:{}", ADDONS);
        });
    }
}