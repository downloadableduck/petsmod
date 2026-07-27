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
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

import java.util.*;

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

    public static final Map<EntityType, Factory> renderSupplierMap = new HashMap();
    public static final Map<EntityRendererManager, Context> renderManagerMap = new WeakHashMap();

    public static KeyBinding openConfigScreen;

    /**
     * Misc rendering stuff
     */
    public PetsClientInitializer() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(PetsClientInitializer::createKeyBinding);
        openConfigScreen = new KeyBinding("Open Pets Menu", GLFW.GLFW_KEY_P, "petsmod.keymapping");

        ClientRegistry.registerKeyBinding(openConfigScreen);
        //bus.register(this);
        //bus.addListener(PetsClientInitializer::registerModelLayers);
       // bus.addListener(PetsClientInitializer::register);
    }

    @SubscribeEvent
    public static void printAddons(FMLClientSetupEvent event) {
        //event.enqueueWork(() -> {
        PetsInitializer.LOGGER.info("PetsMod addons loaded:{}", ADDONS);
        //});
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

    public static void register() {
        System.out.println("running code");
        register(PetsInitializer.HEAD, HeadRenderer::new);
        register(PetsInitializer.DUCK, DuckRenderer::new);
        register(PetsInitializer.RACOON, RacoonRenderer::new);
        register(PetsInitializer.PENGUIN, PenguinRenderer::new);
        register(PetsInitializer.SHEEP, ClientSheepRenderer::new);
        register(PetsInitializer.CAT, ClientCatRenderer::new);
        register(PetsInitializer.BAT, ClientBatRenderer::new);
        register(PetsInitializer.CHICKEN, ClientChickenRenderer::new);
        register(PetsInitializer.COD, ClientCodRenderer::new);
        register(PetsInitializer.COW, ClientCowRenderer::new);
        register(PetsInitializer.DONKEY, ClientDonkeyRenderer::new);
        register(PetsInitializer.HORSE, ClientHorseRenderer::new);
        register(PetsInitializer.MOOSHROOM, ClientMooshroomRenderer::new);
        register(PetsInitializer.PARROT, ClientParrotRenderer::new);
        register(PetsInitializer.PIG, ClientPigRenderer::new);
        register(PetsInitializer.RABBIT, ClientRabbitRenderer::new);
        register(PetsInitializer.SALMON, ClientSalmonRenderer::new);
        register(PetsInitializer.SNOW_GOLEM, ClientSnowGolemRenderer::new);
        register(PetsInitializer.SQUID, ClientSquidRenderer::new);
        register(PetsInitializer.TURTLE, ClientTurtleRenderer::new);
        register(PetsInitializer.VILLAGER, ClientVillagerRenderer::new);
        register(PetsInitializer.WANDERING_TRADER, ClientWanderingTraderRenderer::new);
        register(PetsInitializer.BEE, ClientBeeRenderer::new);
        register(PetsInitializer.CAVE_SPIDER, ClientCaveSpiderRenderer::new);
        register(PetsInitializer.DOLPHIN, ClientDolphinRenderer::new);
        register(PetsInitializer.ENDERMAN, ClientEndermanRenderer::new);
        register(PetsInitializer.FOX, ClientFoxRenderer::new);
        register(PetsInitializer.IRON_GOLEM, ClientIronGolemRenderer::new);
        register(PetsInitializer.LLAMA, ClientLlamaRenderer::new);
        register(PetsInitializer.PANDA, ClientPandaRenderer::new);
        register(PetsInitializer.POLAR_BEAR, ClientPolarBearRenderer::new);
        register(PetsInitializer.PUFFERFISH, ClientPufferFishRenderer::new);
        register(PetsInitializer.SPIDER, ClientSpiderRenderer::new);
        register(PetsInitializer.WOLF, ClientWolfRenderer::new);
        register(PetsInitializer.ELDER_GUARDIAN_COOKIE, ClientElderGuardianRenderer::new);
        register(PetsInitializer.BLAZE, ClientBlazeRenderer::new);
        register(PetsInitializer.CREEPER, ClientCreeperRenderer::new);
        register(PetsInitializer.DROWNED, ClientDrownedRenderer::new);
        register(PetsInitializer.ENDERMITE, ClientEndermiteRenderer::new);
        register(PetsInitializer.EVOKER, ClientEvokerRenderer::new);
        register(PetsInitializer.GHAST, ClientGhastRenderer::new);
        register(PetsInitializer.GUARDIAN, ClientGuardianRenderer::new);
        register(PetsInitializer.HUSK, ClientHuskRenderer::new);
        register(PetsInitializer.MAGMA_CUBE, ClientMagmaCubeRenderer::new);
        register(PetsInitializer.PHANTOM, ClientPhantomRenderer::new);
        register(PetsInitializer.PILLAGER, ClientPillagerRenderer::new);
        register(PetsInitializer.RAVAGER, ClientRavagerRenderer::new);
        register(PetsInitializer.SHULKER, ClientShulkerRenderer::new);
        register(PetsInitializer.SILVERFISH, ClientSilverfishRenderer::new);
        register(PetsInitializer.SKELETON, ClientSkeletonRenderer::new);
        register(PetsInitializer.SLIME, ClientSlimeRenderer::new);
        register(PetsInitializer.STRAY, ClientStrayRenderer::new);
        register(PetsInitializer.VEX, ClientVexRenderer::new);
        register(PetsInitializer.VINDICATOR, ClientVindicatorRenderer::new);
        register(PetsInitializer.WITCH, ClientWitchRenderer::new);
        register(PetsInitializer.WITHER_SKELETON, ClientWitherSkeletonRenderer::new);
        register(PetsInitializer.ZOMBIE, ClientZombieRenderer::new);
        register(PetsInitializer.ZOMBIE_VILLAGER, ClientZombieVillagerRenderer::new);
        register(PetsInitializer.ENDER_DRAGON, ClientEnderDragonRenderer::new);
        register(PetsInitializer.WITHER, ClientWitherRenderer::new);
        register(PetsInitializer.DUMBO_OCTOPUS, DumboOctopusRenderer::new);
        register(PetsInitializer.KOI, KoiRenderer::new);
        register(PetsInitializer.STINGRAY, StingrayRenderer::new);
    }

    /**
     * Registers the key binding and an {@code END_CLIENT_TICK} event to check if the key
     * is pressed
     */

    @SubscribeEvent
    public static void createKeyBinding(FMLClientSetupEvent event) {
        //event.enqueueWork(() -> {
        openConfigScreen = new KeyBinding("Open Pets Menu", GLFW.GLFW_KEY_P, "petsmod.keymapping");

        ClientRegistry.registerKeyBinding(openConfigScreen);
        //});
    }

    public static void register(EntityType entityClass, Factory factory) {
        synchronized(renderSupplierMap) {
            renderSupplierMap.put(entityClass, factory);

            for(EntityRendererManager manager : renderManagerMap.keySet()) {
                renderManagerMap.get(manager).rendererMap.put(entityClass, factory.create(manager, renderManagerMap.get(manager)));
            }
        }
    }

    public static final class Context {
        private final TextureManager textureManager;
        private final IReloadableResourceManager resourceManager;
        private final ItemRenderer itemRenderer;
        private final Map<EntityType, EntityRenderer<? extends Entity>> rendererMap;

        public Context(TextureManager textureManager, IReloadableResourceManager resourceManager, ItemRenderer itemRenderer, Map<EntityType, EntityRenderer<? extends Entity>> rendererMap) {
            super();
            this.textureManager = textureManager;
            this.resourceManager = resourceManager;
            this.itemRenderer = itemRenderer;
            this.rendererMap = rendererMap;
        }

        public TextureManager getTextureManager() {
            return this.textureManager;
        }

        public IReloadableResourceManager getResourceManager() {
            return this.resourceManager;
        }

        public ItemRenderer getItemRenderer() {
            return this.itemRenderer;
        }
    }

    @FunctionalInterface
    public interface Factory {
        EntityRenderer<? extends Entity> create(EntityRendererManager var1, Context var2);
    }
}