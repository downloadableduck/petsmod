package com.jeff.pets.client.addon;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.PetsConfig;
import com.jeff.pets.client.addon.impl.PetRunnable;
import com.jeff.pets.client.addon.impl.PetSkin;
import com.jeff.pets.client.addon.impl.PetsModAddonException;
import com.jeff.pets.client.addon.impl.SafePetRunnable;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * The base class for creating Pets Mod addons. Each instance is supposed to
 * represent an addon. Create an instance with {@link PetsModAddons#registerAddon}
 * <p></p>
 * Additionally, it has a few internal helper methods for me to manage addons
 *
 * @see Central
 */
@SuppressWarnings("unused")
public class PetsModAddons {

    private static final List<PetsModAddons> instances = new CopyOnWriteArrayList<>();
    /**
     * The ID of the addon. Not strictly enforced, but is recommended to use your mod ID
     * here for the sake of consistency.
     * <p>
     * Grab the name with
     */
    private final String id;
    private final List<EntityType<? extends @NotNull AbstractPet>> entities = new CopyOnWriteArrayList<>();
    private final Map<EntityType<? extends @NotNull AbstractPet>, String> entityList = new ConcurrentHashMap<>();
    private final Map<EntityType<? extends @NotNull AbstractPet>, Field> entityNameList = new ConcurrentHashMap<>();
    private final Map<EntityType<? extends @NotNull AbstractPet>, Optional<PetSkin>> skinList = new ConcurrentHashMap<>();
    private final Map<EntityType<? extends @NotNull AbstractPet>, AbstractPet> entityTypeMap = new ConcurrentHashMap<>();
    private final Map<String, Field> settingsMap = new ConcurrentHashMap<>();
    private final List<Field> fieldsList = new CopyOnWriteArrayList<>();

    /**
     * Use {@link PetsModAddons#registerAddon} instead
     */
    private PetsModAddons(String id) {
        instances.add(this);
        this.id = id;
        PetsClientInitializer.ADDONS.add(id);
    }

    /**
     * Method used to create and register a new addon. Store it in a static accessible field somewhere
     * so you can access it easily.
     * <p>
     * <h3>Example Usage</h3>
     * <pre>
     * {@code public static final String MY_MOD_ID = "my_mod_id";
     *
     * public static final PetsModAddons ADDON = PetsModAddons.registerAddon(MY_MOD_ID);
     * }</pre>
     *
     */
    public static PetsModAddons registerAddon(String id) {
        return new PetsModAddons(id);
    }

    /**
     * Internal method
     */
    @ApiStatus.Internal
    public static void forEachAddedPet(PetRunnable runnable) {
        for (PetsModAddons INSTANCE : instances) {
            for (EntityType<? extends @NotNull AbstractPet> entityType : INSTANCE.entities) {
                try {
                    AbstractPet pet;
                    if (INSTANCE.entityTypeMap.get(entityType) == null) {
                        pet = entityType.create(Objects.requireNonNull(Minecraft.getInstance().level), EntitySpawnReason.COMMAND);
                        INSTANCE.entityTypeMap.put(entityType, pet);
                    } else {
                        pet = INSTANCE.entityTypeMap.get(entityType);
                    }
                    if (pet == null || pet.isRemoved() || pet.level() != Minecraft.getInstance().level) {
                        pet = entityType.create(Objects.requireNonNull(Minecraft.getInstance().level), EntitySpawnReason.COMMAND);
                        INSTANCE.entityTypeMap.put(entityType, pet);
                    }
                    runnable.run(pet, INSTANCE.entityList.get(entityType), INSTANCE.entityNameList.get(entityType), Objects.requireNonNull(pet).getClass(), entityType, INSTANCE.skinList.get(entityType));
                } catch (Exception e) {
                    throw new PetsModAddonException(INSTANCE.id, AddonStage.getCurrentStage(), INSTANCE.entityList.get(entityType), e);
                }
            }
        }
    }

    /**
     * Internal method
     */
    @ApiStatus.Internal
    public static void forEachAddedPetSafe(SafePetRunnable runnable) {
        for (PetsModAddons INSTANCE : instances) {
            for (EntityType<? extends @NotNull AbstractPet> entityType : INSTANCE.entities) {
                try {
                    runnable.run(INSTANCE.entityList.get(entityType), INSTANCE.entityNameList.get(entityType), entityType, INSTANCE.skinList.get(entityType));
                } catch (Exception e) {
                    throw new PetsModAddonException(INSTANCE.id, AddonStage.getCurrentStage(), INSTANCE.entityList.get(entityType), e);
                }
            }
        }
    }

    @ApiStatus.Internal
    public static void forEachAddedField(Consumer<Field> consumer) {
        for (PetsModAddons INSTANCE : instances) {
            INSTANCE.fieldsList.forEach(consumer);
        }
    }

    /**
     * Internal method
     */
    @ApiStatus.Internal
    public static String getIdByEntityType(EntityType<?> type) {
        for (PetsModAddons INSTANCE : instances) {
            if (INSTANCE.entityTypeMap.containsKey(type)) {
                return INSTANCE.id;
            }
        }
        return "unknown";
    }

    @ApiStatus.Internal
    public static void forEachAddedSetting(BiConsumer<String, Field> consumer) {
        for (PetsModAddons INSTANCE : instances) {
            INSTANCE.settingsMap.forEach(consumer);
        }
    }

    /**
     * The standard method to add a pet. Generally you would want to call it in
     * {@link ClientModInitializer#onInitializeClient}
     * <h1>Example Usage </h1>
     * *                   <pre> {@code
     *
     *      public static String entityName = ""; //important: MUST BE STATIC
     *
     *      //fabric
     *      @Override
     *      public void onInitializeClient() {
     *          ADDON.addPet(MyEntityType.MY_PET, "pet", PetsModAddons.getField(this, "entityName"), Optional.empty());
     *      }
     *
     *      //neo
     *      @SubscribeEvent
     *      public static void onInit(FMLClientSetupEvent event) {
     *           *          ADDON.addPet(MyEntityType.MY_PET, "pet", PetsModAddons.getField(this, "entityName"), Optional.empty());
     *      }
     *
     *      }</pre>
     *
     * @param type        The entity type to be used.
     * @param entityValue What your pet will be referred to in code. Generally just
     *                    what the pet is called (for example, if your pet is a goose, you would pass
     *                    {@code "goose"} here.)
     * @param entityName  This one is a bit of a doozy, so stay with me - this field needs to be serialized and deserialized into {@code petsconfig.json} to avoid
     *                    forcing developers to serialize their own stuff. Refer to the 'example usage' section for
     *                    further instructions.
     * @return The instance that you are currently on
     */
    @SuppressWarnings("all")
    public PetsModAddons addPet(@NotNull EntityType<? extends AbstractPet> type, @NotNull String entityValue, @NotNull Field entityName, Optional<PetSkin> skin) {
        try {
            if (entityName.get(null) == null || !(entityName.get(null) instanceof String)) {
                throw new PetsModAddonException(id, AddonStage.ADDPET, entityValue, new NullPointerException("An issue occurred evaluating the entities name! Make sure that the field is accessible, mutable, non-null, static, and a String. (This exception was caused by " + this.id + ")"));
            }
            if (entityValue == null) {
                throw new PetsModAddonException(id, AddonStage.ADDPET, entityValue, new NullPointerException("Your pet instance or pet value is null! (This exception was caused by " + this.id + ")"));
            }
            entities.add(type);
            entityList.put(type, entityValue);
            entityNameList.put(type, entityName);
            skinList.put(type, skin);
            Central.refreshChatSuggestor(Minecraft.getInstance());
            Central.createPetsList();
        } catch (Exception e) {
            throw new PetsModAddonException(id, AddonStage.ADDPET, entityValue, e);
        }
        return this;
    }

    /**Allows you to add a custom field to serialize into {@link PetsConfig}
     * @param field The field to serialize*/
    public PetsModAddons addSerializableField(@NotNull Field field) {
        this.fieldsList.add(field);
        return this;
    }

    /**Allows you to add a setting which can be accessed via {@code /pet settings settingName}.
     * The {@code settingField} parameter should be serialized in {@link PetsModAddons#addSerializableField(Field)}.
     * @param settingName The name of the setting (what comes directly after /pet settings)
     * @param settingField A boolean field that represents the setting*/
    public PetsModAddons addSetting(String settingName, Field settingField) {
        try {
            if (!(settingField.get(null) instanceof Boolean)) {
                throw new IllegalArgumentException("The field must be a boolean!");
            }
            this.settingsMap.put(settingName, settingField);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    /**Helper so you don't have to add a try/catch block every time*/
    public static Field getField(Class<?> clazz, String string) {
        try {
            return clazz.getDeclaredField(string);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    /**@see PetsModAddons#getField(Class, String) */
    public static Field getField(Object obj, String string) {
        return getField(obj.getClass(), string);
    }
}
