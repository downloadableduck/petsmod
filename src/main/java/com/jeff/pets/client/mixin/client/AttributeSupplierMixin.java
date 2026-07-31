package com.jeff.pets.client.mixin.client;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.aprilfools.*;
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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.jeff.pets.PetsInitializer.*;

public class AttributeSupplierMixin {

    public static void registerAttributes() {
        try {
            Field field = DefaultAttributes.class.getDeclaredField("SUPPLIERS");
            field.setAccessible(true);
            Map<EntityType<? extends LivingEntity>, AttributeSupplier> SUPPLIERS = (Map<EntityType<? extends LivingEntity>, AttributeSupplier>) field.get(null);

            Map<EntityType<?>, AttributeSupplier> map = new HashMap<>();
            map.putAll(SUPPLIERS);

            map.put(RACOON, Racoon.createAttributes().build());
            map.put(DUCK, Duck.createAttributes().build());
            map.put(PENGUIN, Penguin.createAttributes().build());
            map.put(SHEEP, ClientSheep.createAttributes().build());
            map.put(CAT, ClientCat.createAttributes().build());
            map.put(ALLAY, ClientAllay.createAttributes().build());
            map.put(ARMADILLO, ClientArmadillo.createAttributes().build());
            map.put(AXOLOTL, ClientAxolotl.createAttributes().build());
            map.put(BAT, ClientBat.createAttributes().build());
            map.put(CAMEL, ClientCamel.createAttributes().build());
            map.put(CHICKEN, ClientChicken.createAttributes().build());
            map.put(COD, ClientCod.createAttributes().build());
            map.put(COPPER_GOLEM, ClientCopperGolem.createAttributes().build());
            map.put(COW, ClientCow.createAttributes().build());
            map.put(DONKEY, ClientDonkey.createAttributes().build());
            map.put(FROG, ClientFrog.createAttributes().build());
            map.put(HORSE, ClientHorse.createAttributes().build());
            map.put(MOOSHROOM, ClientMooshroom.createAttributes().build());
            map.put(PARROT, ClientParrot.createAttributes().build());
            map.put(PIG, ClientPig.createAttributes().build());
            map.put(RABBIT, ClientRabbit.createAttributes().build());
            map.put(SALMON, ClientSalmon.createAttributes().build());
            map.put(SNIFFER, ClientSniffer.createAttributes().build());
            map.put(SNOW_GOLEM, ClientSnowGolem.createAttributes().build());
            map.put(SQUID, ClientSquid.createAttributes().build());
            map.put(STRIDER, ClientStrider.createAttributes().build());
            map.put(TADPOLE, ClientTadpole.createAttributes().build());
            map.put(TROPICAL_FISH, ClientTropicalFish.createAttributes().build());
            map.put(TURTLE, ClientTurtle.createAttributes().build());
            map.put(VILLAGER, ClientVillager.createAttributes().build());
            map.put(WANDERING_TRADER, ClientWanderingTrader.createAttributes().build());
            map.put(BEE, ClientBee.createAttributes().build());
            map.put(CAVE_SPIDER, ClientCaveSpider.createAttributes().build());
            map.put(DOLPHIN, ClientDolphin.createAttributes().build());
            map.put(ENDERMAN, ClientEnderman.createAttributes().build());
            map.put(FOX, ClientFox.createAttributes().build());
            map.put(GOAT, ClientGoat.createAttributes().build());
            map.put(IRON_GOLEM, ClientIronGolem.createAttributes().build());
            map.put(LLAMA, ClientLlama.createAttributes().build());
            map.put(NAUTILUS, ClientNautilus.createAttributes().build());
            map.put(PANDA, ClientPanda.createAttributes().build());
            map.put(PIGLIN, ClientWanderingTrader.createAttributes().build());
            map.put(POLAR_BEAR, ClientPolarBear.createAttributes().build());
            map.put(PUFFERFISH, ClientPufferFish.createAttributes().build());
            map.put(SPIDER, ClientSpider.createAttributes().build());
            map.put(WOLF, ClientWolf.createAttributes().build());
            map.put(BLAZE, ClientBlaze.createAttributes().build());
            map.put(BREEZE, ClientBreeze.createAttributes().build());
            map.put(CREAKING, ClientCreaking.createAttributes().build());
            map.put(CREEPER, ClientCreeper.createAttributes().build());
            map.put(ELDER_GUARDIAN_COOKIE, ClientElderGuardian.createAttributes().build());
            map.put(ENDERMITE, ClientEndermite.createAttributes().build());
            map.put(EVOKER, ClientEvoker.createAttributes().build());
            map.put(GHAST, ClientGhast.createAttributes().build());
            map.put(HAPPY_GHAST, ClientHappyGhast.createAttributes().build());
            map.put(GUARDIAN, ClientGuardian.createAttributes().build());
            map.put(HOGLIN, ClientHoglin.createAttributes().build());
            map.put(MAGMA_CUBE, ClientMagmaCube.createAttributes().build());
            map.put(PHANTOM, ClientPhantom.createAttributes().build());
            map.put(PILLAGER, ClientPillager.createAttributes().build());
            map.put(RAVAGER, ClientRavager.createAttributes().build());
            map.put(SHULKER, ClientShulker.createAttributes().build());
            map.put(SILVERFISH, ClientSilverfish.createAttributes().build());
            map.put(SKELETON, ClientSkeleton.createAttributes().build());
            map.put(SLIME, ClientSlime.createAttributes().build());
            map.put(VEX, ClientVex.createAttributes().build());
            map.put(VINDICATOR, ClientVindicator.createAttributes().build());
            map.put(WARDEN, ClientWarden.createAttributes().build());
            map.put(WITCH, ClientWitch.createAttributes().build());
            map.put(ZOMBIE, ClientZombie.createAttributes().build());
            map.put(ZOMBIE_VILLAGER, ClientZombieVillager.createAttributes().build());
            map.put(HUSK, ClientHusk.createAttributes().build());
            map.put(DROWNED, ClientDrowned.createAttributes().build());
            map.put(BOGGED, ClientBogged.createAttributes().build());
            map.put(PARCHED, ClientParched.createAttributes().build());
            map.put(STRAY, ClientStray.createAttributes().build());
            map.put(WITHER_SKELETON, ClientWitherSkeleton.createAttributes().build());
            map.put(ENDER_DRAGON, ClientEnderDragon.createAttributes().build());
            map.put(WITHER, ClientWither.createAttributes().build());
            map.put(ANGRY_GHAST, AngryGhast.createAttributes().build());
            map.put(BATATO, Batato.createAttributes().build());
            map.put(DIAMOND_CHICKEN, DiamondChicken.createAttributes().build());
            map.put(LOVE_GOLEM, LoveGolem.createAttributes().build());
            map.put(MEGA_SPUD, MegaSpud.createAttributes().build());
            map.put(MOON_COW, MoonCow.createAttributes().build());
            map.put(NERD_CREEPER, NerdCreeper.createAttributes().build());
            map.put(PINK_WITHER, PinkWither.createAttributes().build());
            map.put(PLAGUEWHALE_SLAB, PlaguewhaleSlab.createAttributes().build());
            map.put(POISONOUS_POTATO_ZOMBIE, PoisonousPotatoZombie.createAttributes().build());
            map.put(RAY_TRACING, RayTracing.createAttributes().build());
            map.put(REDSTONE_BUG, RedstoneBug.createAttributes().build());
            map.put(SMILING_CREEPER, SmilingCreeper.createAttributes().build());
            map.put(TOXIFIN_SLAB, ToxifinSlab.createAttributes().build());
            map.put(POTATO_HUSK, PotatoHusk.createAttributes().build());
            map.put(HEAD, Head.createAttributes().build());
            map.put(TRAITOR, Traitor.createAttributes().build());
            map.put(DUMBO_OCTOPUS, DumboOctopus.createAttributes().build());
            map.put(KOI, Koi.createAttributes().build());
            map.put(STINGRAY, Stingray.createAttributes().build());

            field.set(null, map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        for (MethodNode method : classNode.methods) {
            if ("<clinit>".equals(method.name)) {

                for (AbstractInsnNode insn : method.instructions.toArray()) {
                    if (insn.getOpcode() == Opcodes.RETURN) {

                        InsnList toInject = new InsnList();

                        toInject.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "com/jeff/pets/client/mixin/client/AttributeSupplierMixin",
                                "registerAttributes",
                                "()V",
                                false
                        ));
                        method.instructions.insertBefore(insn, toInject);
                    }
                }
                break;
            }
        }

        for (FieldNode field : classNode.fields) {
            if ("SUPPLIERS".equals(field.name)) {
                field.access &= ~Opcodes.ACC_FINAL;
                field.access &= ~Opcodes.ACC_PRIVATE;
                field.access &= ~Opcodes.ACC_PROTECTED;
                field.access |= Opcodes.ACC_PUBLIC;
                break;
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}
