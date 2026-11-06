package com.jeff.pets.client.addon;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsConfig;
import com.jeff.pets.client.Utils;
import com.jeff.pets.client.addon.impl.PetRunnable;
import com.jeff.pets.client.addon.impl.PetsModAddonException;
import com.jeff.pets.client.addon.impl.SafePetRunnable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.jeff.pets.client.Central.CONFIG;
import static com.mojang.text2speech.Narrator.LOGGER;

/**
 * Represents the stages of addon loading and processing in chronological order.
 * These are designed to be for <b>setting up the environment</b>, so things like running commands,
 * checking the config screen values, etc., don't appear in here.
 * {@link AddonStage#SUMMONPET}, {@link AddonStage#DESPAWNPET} and {@link AddonStage#CHECKPETNAMES} are included here because they are lifecycle events.
 *
 * @since 0.8.7
 */
public enum AddonStage {

    /**
     * No reason to execute anything here - this is just an informative constant.
     */
    ADDPET((_, _, _, _, _, _) -> {
    }),

    /**
     * Adds all the pet values to the suggestion list.
     * <p>Called in {@link Central#createPetsList()}
     */
    SETUPLIST((petValue, _, _, _) -> Central.PETS_LIST.add(petValue.replaceAll("_", " "))),

    /**
     * Deserializes the values from the config file
     */
    DESERIALIZATION((petValue, petName, type, petSkin) -> {
        Central.hasDeserializationExecuted = true;
        if (CONFIG.addonDataMap.get(petValue) == null)
            CONFIG.addonDataMap.put(petValue, new PetsConfig.AddonData((String) petName.get(null), petSkin.isPresent() ? (String) petSkin.get().skinField().get(null) : null));
        petName.set(null, CONFIG.addonDataMap.get(petValue).petName());
        petSkin.ifPresent((skin) -> {
            try {
                skin.skinField().set(null, CONFIG.addonDataMap.get(petValue).petSkin());
            } catch (IllegalAccessException e) {
                throw new PetsModAddonException(PetsModAddons.getIdByEntityType(type), AddonStage.valueOf("DESERIALIZATION"), petValue, e);
            }
        });
    }, (field) -> {
        try {
            if (CONFIG.addonFieldsMap.get(field.getName()) != null)
                field.set(null, CONFIG.addonFieldsMap.get(field.getName()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }),

    /**
     * Serializes the values into the config file
     * <p>Called in {@link Central#saveConfig()}
     */
    SERIALIZATION((entityValue, entityName, _, entitySkin) -> {
        if (!Central.hasDeserializationExecuted) throw new IllegalArgumentException("Serialization occurred before deserialization occurred!");
        String petSkin = entitySkin.isEmpty() ? null : (String) entitySkin.get().skinField().get(null);
        CONFIG.addonDataMap.put(entityValue, new PetsConfig.AddonData((String) entityName.get(null), petSkin));
        PetsModAddons.forEachAddedField((field -> {
            try {
                CONFIG.addonFieldsMap.put(field.getName(), field.get(null));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }));
    }),

    /**
     * Reinstantiates the added pets with the current {@link ClientLevel}.
     * <p>Called in {@link Central#summonPet()} @
     */
    INSTANTIATEPET((pet, _, _, _, _, _) -> {
        if (pet != null && !pet.isRemoved()) {
            pet.reinstantiate(Minecraft.getInstance().level);
        }
    }),

    /**
     * Summons the added pets if the value in the {@link Central#CONFIG} is equal.
     * <p>Called in {@link Central#summonPet()}
     */
    SUMMONPET((pet, petValue, petName, _, _, _) -> {
        if (Objects.equals(CONFIG.activePet, petValue)) {
            Utils.summonPet(pet, (String) petName.get(null));
        }
    }),

    /**
     * Refreshes the pet's names.
     * <p>Called in {@link Central#refreshPetNames()}
     */
    CHECKPETNAMES((pet, petValue, petName, _, _, _) -> Utils.checkName(petValue, pet, (String) petName.get(null))),

    /**
     * Despawns the added pets.
     * <p>Called in {@link Central#despawnPet()}.
     */
    DESPAWNPET((pet, _, _, _, _, _) -> Utils.despawnEntity(pet)),

    /**
     * Informative field for when the current stage is unknown, which will come after
     * all other stages
     */
    RUNTIME((_, _, _, _) -> {
    });

    private static AddonStage currentStage;
    private static AddonStage nextStage;
    private final Optional<PetRunnable> runnable;
    private final Optional<SafePetRunnable> safePetRunnable;
    private Optional<Consumer<Field>> consumer;

    AddonStage(PetRunnable runnable) {
        this.runnable = Optional.ofNullable(runnable);
        this.safePetRunnable = Optional.empty();
        this.consumer = Optional.empty();
    }

    AddonStage(SafePetRunnable runnable) {
        this.runnable = Optional.empty();
        this.safePetRunnable = Optional.of(runnable);
        this.consumer = Optional.empty();
    }

    AddonStage(SafePetRunnable runnable, Consumer<Field> consumer) {
        this(runnable);
        this.consumer = Optional.ofNullable(consumer);
    }

    /**
     * Gets the current stage.
     */
    public static AddonStage getCurrentStage() {
        if (nextStage == DESPAWNPET && currentStage == DESPAWNPET) {
            return RUNTIME;
        }
        return currentStage;
    }

    /**
     * Runs the executable.
     */
    public void execute() {
        nextStage = currentStage;
        currentStage = this;
        this.runnable.ifPresent(PetsModAddons::forEachAddedPet);
        this.safePetRunnable.ifPresent(PetsModAddons::forEachAddedPetSafe);
        this.consumer.ifPresent(PetsModAddons::forEachAddedField);
    }
}
