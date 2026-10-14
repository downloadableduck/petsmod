import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.custom.aprilfools.head.HeadRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus.DumboOctopusRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.koi.KoiRenderer;
import com.jeff.pets.client.rendering.custom.aquatic.stingray.StingrayRenderer;
import com.jeff.pets.client.rendering.custom.first.duck.DuckRenderer;
import com.jeff.pets.client.rendering.custom.first.penguin.PenguinRenderer;
import com.jeff.pets.client.rendering.custom.first.racoon.RacoonRenderer;
import com.jeff.pets.client.rendering.vanilla.bat.ClientBatRenderer;
import com.jeff.pets.client.rendering.vanilla.blaze.ClientBlazeRenderer;
import com.jeff.pets.client.rendering.vanilla.cat.ClientCatRenderer;
import com.jeff.pets.client.rendering.vanilla.cavespider.ClientCaveSpiderRenderer;
import com.jeff.pets.client.rendering.vanilla.chicken.ClientChickenRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowRenderer;
import com.jeff.pets.client.rendering.vanilla.creeper.ClientCreeperRenderer;
import com.jeff.pets.client.rendering.vanilla.donkey.ClientDonkeyRenderer;
import com.jeff.pets.client.rendering.vanilla.elderguardian.ClientElderGuardianRenderer;
import com.jeff.pets.client.rendering.vanilla.enderdragon.ClientEnderDragonRenderer;
import com.jeff.pets.client.rendering.vanilla.enderman.ClientEndermanRenderer;
import com.jeff.pets.client.rendering.vanilla.ghast.ClientGhastRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseRenderer;
import com.jeff.pets.client.rendering.vanilla.irongolem.ClientIronGolemRenderer;
import com.jeff.pets.client.rendering.vanilla.magmacube.ClientMagmaCubeRenderer;
import com.jeff.pets.client.rendering.vanilla.mooshroom.ClientMooshroomRenderer;
import com.jeff.pets.client.rendering.vanilla.pig.ClientPigRenderer;
import com.jeff.pets.client.rendering.vanilla.rabbit.ClientRabbitRenderer;
import com.jeff.pets.client.rendering.vanilla.sheep.ClientSheepRenderer;
import com.jeff.pets.client.rendering.vanilla.silverfish.ClientSilverfishRenderer;
import com.jeff.pets.client.rendering.vanilla.skeleton.ClientSkeletonRenderer;
import com.jeff.pets.client.rendering.vanilla.slime.ClientSlimeRenderer;
import com.jeff.pets.client.rendering.vanilla.snowgolem.ClientSnowGolemRenderer;
import com.jeff.pets.client.rendering.vanilla.spider.ClientSpiderRenderer;
import com.jeff.pets.client.rendering.vanilla.squid.ClientSquidRenderer;
import com.jeff.pets.client.rendering.vanilla.villager.ClientVillagerRenderer;
import com.jeff.pets.client.rendering.vanilla.witch.ClientWitchRenderer;
import com.jeff.pets.client.rendering.vanilla.wither.ClientWitherRenderer;
import com.jeff.pets.client.rendering.vanilla.witherskeleton.ClientWitherSkeletonRenderer;
import com.jeff.pets.client.rendering.vanilla.wolf.ClientWolfRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie.ClientZombieRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie_pigman.ClientZOmbiePigmanRenderer;
import com.jeff.pets.client.rendering.vanilla.zombievillager.ClientZombieVillagerRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;
import com.jeff.pets.mob.custom.aquatic.DumboOctopus;
import com.jeff.pets.mob.custom.aquatic.Koi;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import com.jeff.pets.mob.custom.first.Duck;
import com.jeff.pets.mob.custom.first.Penguin;
import com.jeff.pets.mob.custom.first.Racoon;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import com.jeff.pets.mob.vanilla.boss.ClientWither;
import com.jeff.pets.mob.vanilla.hostile.*;
import com.jeff.pets.mob.vanilla.neutral.*;
import com.jeff.pets.mob.vanilla.passive.*;
import net.minecraft.client.Minecraft;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet250CustomPayload;

import java.util.Map;

public class mod_PetsMod extends BaseMod {
    @Override
    public String getVersion() {
        return "0.7.9";
    }

    @Override
    public void load() {
        new PetsInitializer();
        new Central();
        new PetsClientInitializer();
        ModLoader.setInGameHook(this, true, false);
    }

    @Override
    public void addRenderer(Map map) {
        map.put(Head.class, new HeadRenderer());
        map.put(Duck.class, new DuckRenderer());
        map.put(Racoon.class, new RacoonRenderer());
        map.put(Penguin.class, new PenguinRenderer());
        map.put(ClientSheep.class, new ClientSheepRenderer());
        map.put(ClientCat.class, new ClientCatRenderer());
        map.put(ClientBat.class, new ClientBatRenderer());
        map.put(ClientChicken.class, new ClientChickenRenderer());
        map.put(ClientCow.class, new ClientCowRenderer());
        map.put(ClientDonkey.class, new ClientDonkeyRenderer());
        map.put(ClientHorse.class, new ClientHorseRenderer());
        map.put(ClientMooshroom.class, new ClientMooshroomRenderer());
        map.put(ClientPig.class, new ClientPigRenderer());
        map.put(ClientRabbit.class, new ClientRabbitRenderer());
        map.put(ClientSnowGolem.class, new ClientSnowGolemRenderer());
        map.put(ClientSquid.class, new ClientSquidRenderer());
        map.put(ClientVillager.class, new ClientVillagerRenderer());
        map.put(ClientCaveSpider.class, new ClientCaveSpiderRenderer());
        map.put(ClientEnderman.class, new ClientEndermanRenderer());
        map.put(ClientIronGolem.class, new ClientIronGolemRenderer());
        map.put(ClientSpider.class, new ClientSpiderRenderer());
        map.put(ClientWolf.class, new ClientWolfRenderer());
        map.put(ClientElderGuardian.class, new ClientElderGuardianRenderer());
        map.put(ClientBlaze.class, new ClientBlazeRenderer());
        map.put(ClientCreeper.class, new ClientCreeperRenderer());
        map.put(ClientGhast.class, new ClientGhastRenderer());
        map.put(ClientGuardian.class, new ClientGuardianRenderer());
        map.put(ClientMagmaCube.class, new ClientMagmaCubeRenderer());
        map.put(ClientSilverfish.class, new ClientSilverfishRenderer());
        map.put(ClientSkeleton.class, new ClientSkeletonRenderer());
        map.put(ClientSlime.class, new ClientSlimeRenderer());
        map.put(ClientWitch.class, new ClientWitchRenderer());
        map.put(ClientWitherSkeleton.class, new ClientWitherSkeletonRenderer());
        map.put(ClientZombie.class, new ClientZombieRenderer());
        map.put(ClientZombieVillager.class, new ClientZombieVillagerRenderer());
        map.put(ClientEnderDragon.class, new ClientEnderDragonRenderer());
        map.put(ClientWither.class, new ClientWitherRenderer());
        map.put(DumboOctopus.class, new DumboOctopusRenderer());
        map.put(Koi.class, new KoiRenderer());
        map.put(Stingray.class, new StingrayRenderer());
        map.put(ClientZombiePigman.class, new ClientZOmbiePigmanRenderer());
    }

    @Override
    public boolean onTickInGame(float i, Minecraft minecraft) {
        Central.get().createTickWatcher();
        return true;
    }

    @Override
    public void clientConnect(NetClientHandler handler) {
        new Central().createJoinHandler();
    }

    public void clientChat(String message) {
        message = message.replace("<", "").replace(">", "").replace(Minecraft.getMinecraft().thePlayer.username, "").replaceFirst(" ", "");
        if (message.contains("!petspecies")) {
            String species = message.replace("!petspecies ", "");
            Central.executePetSpeciesCommand(species);
        } else if (message.contains("!pethelp")) {
            Central.get().executePetHelpCommand();
        } else if (message.contains("!petskin")) {
            String skin = message.replace("!petskin ", "");
            Central.get().executePetSkinCommand(skin);
        } else if (message.contains("!petname")) {
            String name = message.replace("!petname ", "");
            Central.get().executePetNameCommand(name);
        } else if (message.equals("!teleportpet")) {
            Central.get().executePetTeleportCommand();
        } else if (message.contains("!pet")) {
            String preference = message.replace("!pet ", "");
            Central.get().executeToggleCommand(preference);
        }
    }
}
