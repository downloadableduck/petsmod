package com.jeff.pets.client;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.rendering.custom.aprilfools.head.HeadRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus.DumboOctopusRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.koi.KoiRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.stingray.StingrayRenderer;
import com.jeff.pets.client.rendering.custom.first.duck.DuckRenderer;
import com.jeff.pets.client.rendering.custom.first.penguin.PenguinRenderer;
import com.jeff.pets.client.rendering.custom.first.racoon.RacoonRenderer;
import com.jeff.pets.client.rendering.vanilla.bat.ClientBatRenderer;
import com.jeff.pets.client.rendering.vanilla.bee.ClientBeeRenderer;
import com.jeff.pets.client.rendering.vanilla.blaze.ClientBlazeRenderer;
import com.jeff.pets.client.rendering.vanilla.cat.ClientCatRenderer;
import com.jeff.pets.client.rendering.vanilla.cavespider.ClientCaveSpiderRenderer;
import com.jeff.pets.client.rendering.vanilla.chicken.ClientChickenRenderer;
import com.jeff.pets.client.rendering.vanilla.cod.ClientCodRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowRenderer;
import com.jeff.pets.client.rendering.vanilla.creeper.ClientCreeperRenderer;
import com.jeff.pets.client.rendering.vanilla.dolphin.ClientDolphinRenderer;
import com.jeff.pets.client.rendering.vanilla.donkey.ClientDonkeyRenderer;
import com.jeff.pets.client.rendering.vanilla.drowned.ClientDrownedRenderer;
import com.jeff.pets.client.rendering.vanilla.elderguardian.ClientElderGuardianRenderer;
import com.jeff.pets.client.rendering.vanilla.enderdragon.ClientEnderDragonRenderer;
import com.jeff.pets.client.rendering.vanilla.enderman.ClientEndermanRenderer;
import com.jeff.pets.client.rendering.vanilla.endermite.ClientEndermiteRenderer;
import com.jeff.pets.client.rendering.vanilla.evoker.ClientEvokerRenderer;
import com.jeff.pets.client.rendering.vanilla.fox.ClientFoxRenderer;
import com.jeff.pets.client.rendering.vanilla.ghast.ClientGhastRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianRenderer;
import com.jeff.pets.client.rendering.vanilla.hoglin.ClientHoglinRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseRenderer;
import com.jeff.pets.client.rendering.vanilla.husk.ClientHuskRenderer;
import com.jeff.pets.client.rendering.vanilla.irongolem.ClientIronGolemRenderer;
import com.jeff.pets.client.rendering.vanilla.llama.ClientLlamaRenderer;
import com.jeff.pets.client.rendering.vanilla.magmacube.ClientMagmaCubeRenderer;
import com.jeff.pets.client.rendering.vanilla.mooshroom.ClientMooshroomRenderer;
import com.jeff.pets.client.rendering.vanilla.panda.ClientPandaRenderer;
import com.jeff.pets.client.rendering.vanilla.parrot.ClientParrotRenderer;
import com.jeff.pets.client.rendering.vanilla.phantom.ClientPhantomRenderer;
import com.jeff.pets.client.rendering.vanilla.pig.ClientPigRenderer;
import com.jeff.pets.client.rendering.vanilla.piglin.ClientPiglinRenderer;
import com.jeff.pets.client.rendering.vanilla.pillager.ClientPillagerRenderer;
import com.jeff.pets.client.rendering.vanilla.polarbear.ClientPolarBearRenderer;
import com.jeff.pets.client.rendering.vanilla.pufferfish.ClientPufferFishRenderer;
import com.jeff.pets.client.rendering.vanilla.rabbit.ClientRabbitRenderer;
import com.jeff.pets.client.rendering.vanilla.ravager.ClientRavagerRenderer;
import com.jeff.pets.client.rendering.vanilla.salmon.ClientSalmonRenderer;
import com.jeff.pets.client.rendering.vanilla.sheep.ClientSheepRenderer;
import com.jeff.pets.client.rendering.vanilla.shulker.ClientShulkerRenderer;
import com.jeff.pets.client.rendering.vanilla.silverfish.ClientSilverfishRenderer;
import com.jeff.pets.client.rendering.vanilla.skeleton.ClientSkeletonRenderer;
import com.jeff.pets.client.rendering.vanilla.slime.ClientSlimeRenderer;
import com.jeff.pets.client.rendering.vanilla.snowgolem.ClientSnowGolemRenderer;
import com.jeff.pets.client.rendering.vanilla.spider.ClientSpiderRenderer;
import com.jeff.pets.client.rendering.vanilla.squid.ClientSquidRenderer;
import com.jeff.pets.client.rendering.vanilla.stray.ClientStrayRenderer;
import com.jeff.pets.client.rendering.vanilla.strider.ClientStriderRenderer;
import com.jeff.pets.client.rendering.vanilla.turtle.ClientTurtleRenderer;
import com.jeff.pets.client.rendering.vanilla.vex.ClientVexRenderer;
import com.jeff.pets.client.rendering.vanilla.villager.ClientVillagerRenderer;
import com.jeff.pets.client.rendering.vanilla.vindicator.ClientVindicatorRenderer;
import com.jeff.pets.client.rendering.vanilla.wanderingtrader.ClientWanderingTraderRenderer;
import com.jeff.pets.client.rendering.vanilla.witch.ClientWitchRenderer;
import com.jeff.pets.client.rendering.vanilla.wither.ClientWitherRenderer;
import com.jeff.pets.client.rendering.vanilla.witherskeleton.ClientWitherSkeletonRenderer;
import com.jeff.pets.client.rendering.vanilla.wolf.ClientWolfRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie.ClientZombieRenderer;
import com.jeff.pets.client.rendering.vanilla.zombievillager.ClientZombieVillagerRenderer;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
@Mod(MOD_ID)
@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PetsClientInitializer {

    public static List<String> ADDONS = new ArrayList<>();

    public static KeyMapping openConfigScreen;

    /**
     * Misc rendering stuff
     */
    public PetsClientInitializer() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        //bus.register(this);
        //bus.addListener(PetsClientInitializer::registerModelLayers);
        bus.addListener(PetsClientInitializer::register);
    }

    @SubscribeEvent
    public static void printAddons(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            PetsInitializer.LOGGER.info("PetsMod addons loaded:{}", ADDONS);
        });
    }

    /*
    @SubscribeEvent
    static void registerModelLayers(RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HeadModel.LAYER_LOCATION, HeadModel::getTexturedModelData);
        event.registerLayerDefinition(RacoonRenderer.RACOON_LOCATION, RacoonModel::getTexturedModelData);
        event.registerLayerDefinition(DuckModel.LAYER_LOCATION, DuckModel::getTexturedModelData);
        event.registerLayerDefinition(PenguinModel.PENGUIN_LOCATION, PenguinModel::getTexturedModelData);
        event.registerLayerDefinition(ClientSheepRenderer.SHEEP_LOCATION, ClientSheepModel::createBodyLayer);
        event.registerLayerDefinition(ClientSheepWoolLayer.SHEEP_WOOL_LOCATION, ClientSheepModel::createBodyLayer);
        event.registerLayerDefinition(ClientCatRenderer.CAT_LOCATION, ClientCatRenderer::createCatBodyLayer);
        event.registerLayerDefinition(ClientAxolotlRenderer.AXOLOTL_LOCATION, AxolotlModel::createBodyLayer);
        event.registerLayerDefinition(ClientBatRenderer.BAT_LOCATION, BatModel::createBodyLayer);
        event.registerLayerDefinition(ClientChickenRenderer.CHICKEN_LOCATION, ClientChickenModel::createBodyLayer);
        event.registerLayerDefinition(ClientCodRenderer.COD_LOCATION, CodModel::createBodyLayer);
        event.registerLayerDefinition(ClientCowRenderer.COW_LOCATION, ClientCowModel::createBodyLayer);
        event.registerLayerDefinition(ClientDonkeyRenderer.DONKEY_LOCATION, ClientDonkeyRenderer::createBodyLayer);
        event.registerLayerDefinition(ClientHorseRenderer.HORSE_LOCATION, ClientHorseRenderer::createBaseHorseLayer);
        event.registerLayerDefinition(ClientMooshroomRenderer.MOOSHROOM_LOCATION, ClientCowModel::createBodyLayer);
        event.registerLayerDefinition(ClientParrotRenderer.PARROT_LOCATION, ParrotModel::createBodyLayer);
        event.registerLayerDefinition(ClientPigRenderer.PIG_LOCATION, ClientPigRenderer::createBasePigModel);
        event.registerLayerDefinition(ClientRabbitRenderer.RABBIT_LOCATION, ClientRabbitRenderer::createBaseRabbitLayer);
        event.registerLayerDefinition(ClientSalmonRenderer.SALMON_LOCATION, ClientSalmonModel::getTexturedModelData);
        event.registerLayerDefinition(ClientSnowGolemRenderer.SNOW_GOLEM, SnowGolemModel::createBodyLayer);
        event.registerLayerDefinition(ClientSquidRenderer.SQUID_LOCATION, SquidModel::createBodyLayer);
        event.registerLayerDefinition(ClientStriderRenderer.STRIDER_LOCATION, StriderModel::createBodyLayer);
        event.registerLayerDefinition(ClientTurtleRenderer.TURTLE_LOCATION, TurtleModel::createBodyLayer);
        event.registerLayerDefinition(ClientVillagerRenderer.VILLAGER_LOCATION, ClientVillagerRenderer::createBaseVillagerLayer);
        event.registerLayerDefinition(ClientWanderingTraderRenderer.WANDERING_TRADER_LOCATION, ClientVillagerRenderer::createBaseVillagerLayer);
        event.registerLayerDefinition(ClientBeeRenderer.BEE_LOCATION, BeeModel::createBodyLayer);
        event.registerLayerDefinition(ClientDolphinRenderer.DOLPHIN_LOCATION, DolphinModel::createBodyLayer);
        event.registerLayerDefinition(ClientEndermanRenderer.ENDERMAN_LOCATION, EndermanModel::createBodyLayer);
        event.registerLayerDefinition(ClientFoxRenderer.FOX_LOCATION, ClientFoxModel::createBodyLayer);
        event.registerLayerDefinition(ClientGoatRenderer.GOAT_LOCATION, ClientGoatModel::createBodyLayer);
        event.registerLayerDefinition(ClientIronGolemRenderer.IRON_GOLEM_LOCATION, IronGolemModel::createBodyLayer);
        event.registerLayerDefinition(ClientLlamaRenderer.LLAMA_LOCATION, ClientLlamaRenderer::createLlamaLayer);
        event.registerLayerDefinition(ClientPandaRenderer.PANDA_LOCAITON, PandaModel::createBodyLayer);
        event.registerLayerDefinition(ClientPiglinRenderer.PIGLIN_LOCATION, ClientPiglinRenderer::createBodyLayer);
        event.registerLayerDefinition(ClientPolarBearRenderer.POLAR_BEAR_LOCATION, ClientPolarBearRenderer::createBodyLayer);
        event.registerLayerDefinition(ClientPufferFishRenderer.PUFFERFISH_LOCATION, PufferfishBigModel::createBodyLayer);
        event.registerLayerDefinition(ClientSpiderRenderer.SPIDER_LOCATION, SpiderModel::createSpiderBodyLayer);
        event.registerLayerDefinition(ClientWolfRenderer.WOLF_LOCATION, ClientWolfRenderer::createBodyLayer);
        event.registerLayerDefinition(ClientElderGuardianRenderer.ELDER_GUARDIAN_LOCATION, GuardianModel::createBodyLayer);
        event.registerLayerDefinition(ClientBlazeRenderer.BLAZE_LOCATION, BlazeModel::createBodyLayer);
        event.registerLayerDefinition(ClientCreeperRenderer.CREEPER_LOCATION, ClientCreeperRenderer::createBaseCreeperLayer);
        event.registerLayerDefinition(ClientDrownedRenderer.DROWNED_LOCATION, ClientDrownedRenderer::createBaseDrownedLayer);
        event.registerLayerDefinition(ClientEndermiteRenderer.ENDERMITE_LOCATION, EndermiteModel::createBodyLayer);
        event.registerLayerDefinition(ClientEvokerRenderer.EVOKER_LOCATION, ClientEvokerModel::createBodyLayer);
        event.registerLayerDefinition(ClientGhastRenderer.GHAST_LOCATION, GhastModel::createBodyLayer);
        event.registerLayerDefinition(ClientGuardianRenderer.GUARDIAN_LOCATION, GuardianModel::createBodyLayer);
        event.registerLayerDefinition(ClientHoglinRenderer.HOGLIN_LOCATION, ClientHoglinModel::createBodyLayer);
        event.registerLayerDefinition(ClientHuskRenderer.HUSK_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
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
        event.registerLayerDefinition(ClientWitchRenderer.WITCH_LOCATION, WitchModel::createBodyLayer);
        event.registerLayerDefinition(ClientWitherSkeletonRenderer.WITHER_SKELETON_LOCATION, SkeletonModel::createBodyLayer);
        event.registerLayerDefinition(ClientZombieRenderer.ZOMBIE_LOCATION, ClientZombieRenderer::createBaseZombieLayer);
        event.registerLayerDefinition(ClientEnderDragonRenderer.ENDER_DRAGON_LOCATION, EnderDragonRenderer::createBodyLayer);
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
    }*/

    @SubscribeEvent
    static void register(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.HEAD.get(), (dispatcher) -> new HeadRenderer(dispatcher));
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.DUCK.get(), DuckRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.RACOON.get(), RacoonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.PENGUIN.get(), PenguinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.SHEEP.get(), ClientSheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.CAT.get(), ClientCatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.BAT.get(), ClientBatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.CHICKEN.get(), ClientChickenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.COD.get(), ClientCodRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.COW.get(), ClientCowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.DONKEY.get(), ClientDonkeyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.HORSE.get(), ClientHorseRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.MOOSHROOM.get(), ClientMooshroomRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.PARROT.get(), ClientParrotRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.PIG.get(), ClientPigRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.RABBIT.get(), ClientRabbitRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.SALMON.get(), ClientSalmonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.SNOW_GOLEM.get(), ClientSnowGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.SQUID.get(), ClientSquidRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.STRIDER.get(), ClientStriderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.TURTLE.get(), ClientTurtleRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.VILLAGER.get(), ClientVillagerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.WANDERING_TRADER.get(), ClientWanderingTraderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.BEE.get(), ClientBeeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.CAVE_SPIDER.get(), ClientCaveSpiderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.DOLPHIN.get(), ClientDolphinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.ENDERMAN.get(), ClientEndermanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.FOX.get(), ClientFoxRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.IRON_GOLEM.get(), ClientIronGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.LLAMA.get(), ClientLlamaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.PANDA.get(), ClientPandaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.PIGLIN.get(), ClientPiglinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.POLAR_BEAR.get(), ClientPolarBearRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.PUFFERFISH.get(), ClientPufferFishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.SPIDER.get(), ClientSpiderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.WOLF.get(), ClientWolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.ELDER_GUARDIAN_COOKIE.get(), ClientElderGuardianRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.BLAZE.get(), ClientBlazeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.CREEPER.get(), ClientCreeperRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.DROWNED.get(), ClientDrownedRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.ENDERMITE.get(), ClientEndermiteRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.EVOKER.get(), ClientEvokerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.GHAST.get(), ClientGhastRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.GUARDIAN.get(), ClientGuardianRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.HOGLIN.get(), ClientHoglinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.HUSK.get(), ClientHuskRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.MAGMA_CUBE.get(), ClientMagmaCubeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.PHANTOM.get(), ClientPhantomRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.PILLAGER.get(), ClientPillagerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.RAVAGER.get(), ClientRavagerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.SHULKER.get(), ClientShulkerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.SILVERFISH.get(), ClientSilverfishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.SKELETON.get(), ClientSkeletonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.SLIME.get(), ClientSlimeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.STRAY.get(), ClientStrayRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.VEX.get(), ClientVexRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.VINDICATOR.get(), ClientVindicatorRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.WITCH.get(), ClientWitchRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.WITHER_SKELETON.get(), ClientWitherSkeletonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.ZOMBIE.get(), ClientZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.ZOMBIE_VILLAGER.get(), ClientZombieVillagerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.ENDER_DRAGON.get(), ClientEnderDragonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.WITHER.get(), ClientWitherRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.DUMBO_OCTOPUS.get(), DumboOctopusRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.KOI.get(), KoiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PetsInitializer.Entities.STINGRAY.get(), StingrayRenderer::new);
    }

    /**
     * Registers the key binding and an {@code END_CLIENT_TICK} event to check if the key
     * is pressed
     */

    @SubscribeEvent
    void createKeyBinding(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
        openConfigScreen = new KeyMapping("Open Pets Menu", GLFW.GLFW_KEY_P, "petsmod.keymapping");

            ClientRegistry.registerKeyBinding(openConfigScreen);
        });
    }
}