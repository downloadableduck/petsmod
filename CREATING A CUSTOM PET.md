_This guide does not cover how to create and render a custom mob. If you are unsure how to create a custom mob, you can
use_ [my custom made guide](https://github.com/downloadableduck/example-entity-template) _or
the_ [official Fabric API guide](https://docs.fabricmc.net/develop/entities/first-entity).

### Need Help?

I am more familiar with PetsMod's workings and will likely be able to figure out what you are having trouble with.
Please open an issue on [PetsMod's GitHub](https://github.com/downloadableduck/petsmod/tree/26.1.x).

# Creating an Add-On for PetsMod

PetsMod is a Minecraft mod developed by downloadableduck and the pets team. This mod brings custom, client-sided pets
into your game that will follow you around and interact with you. As of 0.7.4, PetsMod supports user-made addons, which
allow new custom pets to exist.

"Add-Ons" refers to a _coded_ project. For creating PetsMod resourcepacks, please see the section on custom
resourcepacks.

## Setting Up Your Development Environment

Your project will need to have PetsMod and all of it's dependencies installed. Add these lines to your build.gradle:

```groovy
repositories {
    maven { url "https://maven.shedaniel.me/" }
    maven { url "https://maven.terraformersmc.com/" }
    maven { url "https://maven.isxander.dev/releases" }
    maven { url "https://api.modrinth.com/maven"}
}
```

```groovy
dependencies {
    implementation "me.shedaniel.cloth:cloth-config2-fabric:${project.cloth_config_version}"
    implementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_api_version}"
    implementation("com.terraformersmc:modmenu:${project.modmenu_version}")
    implementation "dev.isxander:yet-another-config-lib:${project.yacl_version}"
    implementation "maven.modrinth:pets-mod:${project.petsmod_version}"
    //add the sources jar as well
    implementation "maven.modrinth:pets-mod:${project.petsmod_version}:sources"
}
```

Now you will have to add the versions to your gradle.properties. The latest mod versions as of Minecraft 26.1.2 are
available below:

```properties
fabric_api_version=0.144.3+26.1
modmenu_version=18.0.0-alpha.8
yacl_version=3.9.1+26.1-fabric
petsmod_version=0.7.8-26.1.x
cloth_config_version=26.1.154
```

Here are some quick links to each mod's version page:

[Fabric API](https://modrinth.com/mod/fabric-api/versions)
<br>
[ModMenu](https://modrinth.com/mod/modmenu/versions)
<br>
[YetAnotherConfigLib](https://modrinth.com/mod/yacl/versions)
<br>
[PetsMod](https://modrinth.com/mod/pets-mod/versions)
<br>
[Cloth Config](https://modrinth.com/mod/cloth-config/versions)

Additionally, make sure to depend on PetsMod `0.7.4` or greater in your `fabric.mod.json`.

`src/main/resources/fabric.mod.json`

```JSON
"depends": {
    "pets-mod": ">=0.7.4"
}
```

Now that we've added our libraries, let's get started!

## Introduction to PetsMod

This is an introduction to how PetsMod works internally and a couple of it's main classes. Please do not skip this part
as this information may help you along the way.

Most of the activity inside of PetsMod is defined by its config (defined in `Central.class`). For example, when the user
switches their active pet, the config's active pet gets changed, and as a result, the game despawns the current pet and
summons the new one. When the user changes what skin their pet is using, the specific value in the config that holds
that pet's skin is changed.

Please note that the config is stored in the `client` package, and is thus inaccessible from the `main` package.

The pets inside of PetsMod require custom movement and packet logic in order to function even when on a server. All of
these methods are found in the main pet files: AbstractPet, FlyingPet, and GroundPet. Namely, the `tick()` and
`getAddEntityPacket()` methods.

Finally, a lot of helper methods for summoning, despawning, and managing pets are available in `Utils.class`. Make sure
to check it out for a better understanding of how everything works.

Without further ado, let's get started!

### Giving Your Entity Custom Logic

In order to give your custom entity the logic it requires, take your entity class and make it extend `FlyingPet` or
`GroundPet`, depending on whether it can fly or not. You will need to override the following methods:

`src/main/java/com/jeff/pets/CustomEntity.java`

```java
    /**This method is used to determine how far
     * the entity should be when it
     * stops moving towards the player. 
     */
    protected int stopDistance() {
        
    }

    /**Used to define how high the hearts that appear when
     * you right-click on a pet will be.
     * Zero means it would appear right in the middle of 
     * the entity's hitbox. One would make it appear a block 
     * above the entity.*/
    protected float heartHeight() {
    
    }

    /**Used to define the entity's default ambient sound..*/
    protected SoundEvent getAmbientSound() {
    
    }
```

> Note: The `SoundEvents` class has a large number of static fields that can be used in `getAmbientSound()`.

There, it now has custom logic and will function on any server. Let's dive over to our rendering class real quick:

`src/client/com/jeff/pets/CustomEntityRenderer.java`

```java
public class CustomEntityRenderer extends MobRenderer<CustomEntity, GhastRenderState, GhastModel> {

    public static final ModelLayerLocation CUSTOM_ENTITY_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MOD_ID, "custom_entity"), "main");

    public CustomEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new GhastModel(context.bakeLayer(CUSTOM_ENTITY_LOCATION)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GhastRenderState state) {
        return ResourceLocation.withDefaultNamespace("textures/entity/ghast/ghast.png");
    }

    @Override
    public @NotNull GhastRenderState createRenderState() {
        return new GhastRenderState();
    }
}
```

Real quick, go ahead and change `extends MobRenderer` to `extends PetRenderer`. This gives it the logic that will flip
it upside down if the entities' name is **Dinnerbone** or **Grumm**.

`src/client/com/jeff/pets/CustomEntityRenderer.java`

```java
public class CustomEntityRenderer extends PetRenderer<CustomEntity, GhastRenderState, GhastModel> {

    public static final ModelLayerLocation CUSTOM_ENTITY_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MOD_ID, "custom_entity"), "main");

    public CustomEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new GhastModel(context.bakeLayer(CUSTOM_ENTITY_LOCATION)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GhastRenderState state) {
        return ResourceLocation.withDefaultNamespace("textures/entity/ghast/ghast.png");
    }

    @Override
    public @NotNull GhastRenderState createRenderState() {
        return new GhastRenderState();
    }
}
```

Great, now it has built-in logic and custom rendering mechanics. Now, let's get started with actually adding the pet
into PetsMod!

### Creating a Config

The first thing we need to do is set up a config for our mod that contains our pet name, so that it will be stored
across instances.

Create a new class in your `client` module that implements `ConfigData`. Now, add an `@Config` annotation on the class,
like such:

`src/client/com/jeff/pets/ExamplePetsModAddonConfig.java`

```java
@Config(name = "petsmod_addon_config")
public class ExamplePetsModAddonConfig implements ConfigData {
}
```

> Tip: The `name` value in the `@Config` annotation is what the JSON file that stores your data will be called. Make
> sure to make it unique so that other addons aren't accidentally overwriting each other.

Now, add a simple String into this config. Do not assign a value - otherwise, it will be set to that specific value
every time the game reloads - which is not what we want! We want the user to be able to set their own values.

`src/client/com/jeff/pets/ExamplePetsModAddonConfig.java`

`public String customEntityName;`

Great! Let's hop over to our **client initializer** class to do a couple of things. The first thing that we will do is
register our config.

`src/client/com/jeff/pets/ExamplePetsModAddonClient.java`

```java
public class ExamplePetsModAddonClient {
    /**Create the config so that we can access it from our code. Call this as soon as possible,
     * and avoid using CUSTOM_CONFIG before this method is called.*/
    private void setUpConfig() {
        AutoConfig.register(ExamplePetsModAddonConfig.class, GsonConfigSerializer::new);
        CUSTOM_CONFIG = AutoConfig.getConfigHolder(ExamplePetsModAddonConfig.class).getConfig();
    }
}    
```

And call it in your `onInitializeClient()` method:

`src/client/com/jeff/pets/ExamplePetsModAddonClient.java`

```java
public class ExamplePetsModAddonClient {
    @Override
    public void onInitializeClient() {
        this.setUpConfig();
    }
}
```

While we are here, we should also add a `null` check to our config values to make sure they don't cause any unwanted
`NullPointerExceptions`.

`src/client/com/jeff/pets/ExamplePetsModAddonClient.java`

```java
public class ExamplePetsModAddonClient {
    [. . .]
    /**Uses Utils.checkNullString to check if the String provided is null. If it is,
     * it assigns an empty String. If it is not, then it keeps the original String. Make sure to
     * call this method after setUpConfig() has been called.*/
    private void checkForNullObjects() {
        CUSTOM_CONFIG.customEntityName = Utils.checkNullString(CUSTOM_CONFIG.customEntityName);
    }
    [. . .]
}
```

Call it in your `onInitializeClient()` as well:

`src/client/com/jeff/pets/ExamplePetsModAddonClient.java`

```java
public class ExamplePetsModAddonClient {
    @Override
    public void onInitializeClient() {
        this.setUpConfig();
       /**Call this.checkForNullObjects after the config has
        * already been registered, to ensure that the config 
        * itself is not null.*/
        this.checkForNullObjects();
    }
}
```

Finally, we need to add two `static final` fields.

```java
public class ExamplePetsModAddonClient {
    public static final String CUSTOM_ENTITY_VALUE = "custom_entity";
    public static final String CUSTOM_ENTITY_VALUE_NO_SPACES = "custom entity";
}
```

> Note: The first value is what the CONFIG.activePet value will be set to when your custom entity is active. This should
> be consistant with what your pet is labeled as, unique, and most importantly, must be maintaned wherever you are
> checking something relating to CONFIG.activePet.
>
> The second value is the same as the first, but without spaces. This will be here for use in the commands, so when the
> user types `/petspecies custom entity`, it will still work.


Great! Let's get started with mixing in to the Central class to allow the user to summon our custom entity.

## Mixing in to Central.class

As you might remember from our Introduction to PetsMod section, Central contains most of the command logic, which the
core of the mod is based around. What we will do is inject into each of the commands, check if they match our entity,
and if so, set the currently active pet to our custom entity.

Let's create a new file in our `client/mixin` package.

`src/client/java/com/jeff/pets/exampleaddon/client/mixin/CentralMixin.class`

```java
@Mixin(Central.class)
public class CentralMixin {

}
```

Add a quick field to it as well. This will be an instance of our custom entity that can be summoned and de-spawned at
will.

`src/client/java/com/jeff/pets/exampleaddon/client/mixin/CentralMixin.java`

```java
@Unique
private static CustomEntity customEntity;
```

Now that we have our class set up, let's mix in to the most important method, `createSummonCommand`. However, we will
need a custom helper method to help us summon our entity. Add this to your code:
`src/client/java/com/jeff/pets/exampleaddon/client/mixin/CentralMixin.java`

```java
@Unique
    private static void spawnCustomEntity(CommandContext<FabricClientCommandSource> context, Entity entity, String activePet) {
        Utils.setActivePet(entity, activePet);
        AutoConfig.getConfigHolder(PetsConfig.class).save();
        context.getSource().sendFeedback(Component.literal("§b[PetsMod] §aYour active pet has been switched to " + activePet.replace("_", " ")));
        Central.despawnPet();
        Central.summonPet();
    }
```

> Tip: If you are looking to eventually expand this addon into something bigger, consider making a utility class
> containing methods like these.
> Additionally, when calling this method, **always use underscores and not spaces in the `activePet` parameter.**

**What this does**:

- Sets the active pet in the CONFIG
- Saves the config
- Sends the player a notification that their active pet has been switched
- Despawns the currently active pet
- Re-summons the newly active pet

This method is **reusable**. You can use it multiple times for each of your custom pets - just switch up the `entity`
and `activePet` parameters.

After this, we can start injecting. Let's start with, as previously stated, the /petspecies command.

`src/client/java/com/jeff/pets/exampleaddon/client/mixin/CentralMixin.java`

```java
@Inject(at = @At("HEAD"), method = "lambda$createPetSpeciesCommand$1", cancellable = true)
private static void createSummonCommand(CommandContext<FabricClientCommandSource> context, CallbackInfoReturnable<Integer> cir) {
    String species = StringArgumentType.getString(context, "species");
    if (Objects.equals(species, CUSTOM_ENTITY_VALUE_NO_SPACES) || Objects.equals(species, CUSTOM_ENTITY_VALUE)) {
        spawnCustomEntity(context, customEntity, CUSTOM_ENTITY_VALUE);
        cir.setReturnValue(1);
    }
}
```

This method takes the input of `/petspecies`, checks if it is equal to our custom entity, and uses our previously
defined `spawnCustomEntity()` method above if so.

However, we are far from done. We still have to:

- Let this mob be named through `/petname`
- **Important**: Add support for our config screen so that it doesn't crash when the user presses **P**.
- Despawn this pet when the user runs the command again.
- Add the logic to the `summonPet()` method that will actually spawn the entity in the world

Let's focus on one thing at a time, and try to get this entity to spawn in the world first. Add a new `@Inject`, this
time for `summonPet()`.

`src/client/java/com/jeff/pets/exampleaddon/client/mixin/CentralMixin.java`

```java
    /**Uses the Utils.summonPet method to summon the pet in the world if
     * CONFIG.activePet is equal to "custom_entity".*/
    @Inject(at = @At("HEAD"), method = "summonPet")
    private static void summonPet(CallbackInfo ci) {
        customEntity = new CustomEntity(ExamplePetsModAddon.CUSTOM_ENTITY, Minecraft.getInstance().level);
        if (Objects.equals(CONFIG.activePet, CUSTOM_ENTITY_VALUE)) {
            Utils.summonPet(customEntity, CUSTOM_CONFIG.customEntityName);
        }
    }
```

> Tip: Hover over any `Utils` methods to see what they do.

**What this does**:

- Initializes the `customEntity` field safely. We cannot initialize it earlier as it would end in an
  `IllegalArgumentException`. Can you guess why?

<details>
<summary>Answer</summary>
When Minecraft initially launches, Minecraft.getInstance().level is null.
</details>

- Checks if `CONFIG.activePet` - the currently selected pet - is equal to "custom_entity", or whatever value you set it
  your key to.
- If so, summons the entity using `Utils.summonPet()`.

When loading into a game, you should now see that you can use /petspecies to switch your pet. However, the game will not
**suggest** this entity while the user is typing. Let's add that real quick. Hop over to your client initializer and add
this code in your `onInitializeClient()` method.

`src/client/com/jeff/pets/exampleaddon/client/ExamplePetsModAddonClient.java`

```java
public void onInitializeClient() {
        /* Adds our custom entity to the list of suggestions that appear when the user types
         * /petspecies. */
        Central.PETS_LIST.add(CUSTOM_ENTITY_VALUE_NO_SPACES);
}
```

`PETS_LIST` is a static constant inside of Central that `/petspecies` uses for it's suggestions.

Now, you should see your suggestion popping up when you try to run `/petspecies`.

Try running `/petspecies` again. You should see that another pet will spawn, but your custom entity will not _despawn_.
So, let's make that happen with a quick injection into `Central.despawnPet()`.

`src/client/java/com/jeff/pets/exampleaddon/client/mixin/CentralMixin.java`

```java
    /** Utils.despawnEntity checks if the entity is not null,
     * and discards it if it is not.*/
    @Inject(at = @At("HEAD"), method = "despawnPet")
    private static void despawnPet(CallbackInfo ci) {
        Utils.despawnEntity(customEntity);
    }

```

Great, it will now despawn when the user runs `/petspecies`. Let's add the naming logic next. Add this to your
`CentralMixin.java`:

`src/client/java/com/jeff/pets/exampleaddon/client/mixin/CentralMixin.java`

```java

    /** Makes sure that the command assigns the inputted name
     to the correct pet.*/
    @Inject(at = @At("HEAD"), method = "lambda$createPetNameCommand$1")
    private static void init(CommandContext<?> context, CallbackInfoReturnable<Integer> cir) {

        String name = StringArgumentType.getString(context, "name");
        if (Objects.equals(CONFIG.activePet, CUSTOM_ENTITY_VALUE)) {
            CUSTOM_CONFIG.customEntityName = name;
        }
    }
```

**What this does:**
SImply, when the user runs `/petname`, the command logic checks what the active pet is. This addition basically says,
_if the currently active pet is our custom entity, then assign the inputted name to our custom entity._

If you go in-game and try to run `/petname` on your pet, you will see that nothing happens. This is because, while the
value in CUSTOM_CONFIG is changed, the name isn't actually re-assigned to the entity until it spawns. To solve this,
there is a method in `Central` called `refreshPetNames()` that runs every tick. Let's inject into that.

`src/client/java/com/jeff/pets/exampleaddon/client/mixin/CentralMixin.java`

```java

     /** This code runs every tick. Utils.checkName checks if the entity's custom name is
     * equal to the name defined in the config, and if it is not, then re-assign the name.
     */
    @Inject(at = @At("HEAD"), method = "refreshPetNames")
    private static void refreshPetNames(CallbackInfo ci) {
        Utils.checkName(CUSTOM_ENTITY_VALUE, customEntity, CUSTOM_CONFIG.customEntityName);
    }
```

`Utils.checkName`:
The method used in
Central#refreshPetNames(). Checks whether the entities'
name is equal to the name in the config. Additionally,
this method provides a layer of safety
that ensures that Minecraft will not
throw a NullPointerException if `entity` is `null`.

That's it for `Central.class` - we don't need to add anything more to this class. However, we still have to inject into
the config screen to make sure it doesn't crash with an `IllegelStateException`.

In case you don't know, YetAnotherConfigLib's `EnumDropdownControllerBuilder` takes an enum and cycles through each of
the options. Unfortunately, there is no way to inject a new enum constant into an enum, so we will have to create our
own and make YetAnotherConfigLib use that enum.

Create a new enum called `CustomEntityValues`.
Copy all of the enum constants from `PetList.class` and paste it into your enum.

> Note: Every time PetsMod updates, new values will be added to the original PetList enum. Make sure to refer to the
> appropriate file in the UPDATING.md file to make sure that your addon doesn't cause mismatches.

Now, add your custom entity value to the **top** of your enum.

```java
public enum CustomEntityValues {
    custom_entity,
    [the values from PetList should be here.]
}
```

Now, make your enum implement `NameableEnum`. You will see that you must implement `getDisplayName()` now, so go ahead
and do that. The end result should look like this:

```java
public enum CustomEntityValues implements NameableEnum {
    [your values here]
    @Override
    public Component getDisplayName() {
        return String.valueOf(this).replace("_", " ");
    }
}
```

> Tip: Using `.replace()` means that, in the config screen, users see "custom entity" rather than "custom_entity". This
> is much cleaner and easier for the user.

Now, we can inject into `PetsConfigScreen.class` with our custom logic. Add a couple of `@Injects`.

```java
@Mixin(PetsConfigScreen.class)
public class ConfigScreenMixin {
    /**
     * When the user opens the config screen, it will display their current pet. This ensures
     * that the game will not crash with an {@link IllegalArgumentException} upon using opening
     * the screen with our custom entity, as it is not part of the {@link PetList} enum.
     */
    @Inject(at = @At("HEAD"), method = "lambda$getModConfigScreenFactory$4", cancellable = true)
    private static void addCustomEntitySpecies(PetsConfig CONFIG, CallbackInfoReturnable<Enum<?>> cir) {
        if (CONFIG.activePet.equals(CUSTOM_ENTITY_VALUE)) {
            cir.setReturnValue(CustomEntityValues.valueOf(CONFIG.activePet));
        }
    }

    /**
     * Once again, making sure the game does not crash with a 
     * IllegalArgumentException. This time, with the skins. If 
     * you the pet supports multiple skins, you would instead 
     * use CustomEntitySkins.valueOf()
     */
    @Inject(at = @At("HEAD"), method = "lambda$getModConfigScreenFactory$8", cancellable = true)
    private static void addCustomEntitySkin(PetsConfig CONFIG, CallbackInfoReturnable<Enum<?>> cir) {
        if (CONFIG.activePet.equals(CUSTOM_ENTITY_VALUE)) {
            cir.setReturnValue(CustomEntityValues.valueOf(CONFIG.activePet));
        }
    }
}
```

These injections use our custom enum to make sure the game does not crash.

Finally, we have two last injections to ensure that the naming works correctly.

```java
@Mixin(PetsConfigScreen.class)
public class ConfigScreenMixin {
    /**When the user opens the menu, they should see their currently selected pets name. 
     * This ensures that if our custom entity is active, they will see our custom entities 
     * name.*/
    @Inject(at = @At("HEAD"), method = "lambda$getModConfigScreenFactory$6", cancellable = true)
    private static void addCustomEntityName(PetsConfig CONFIG, CallbackInfoReturnable<String> cir) {
        if (CONFIG.activePet.equals(CUSTOM_ENTITY_VALUE)) {
            cir.setReturnValue(CUSTOM_CONFIG.customEntityName);
        }
    }

    /**Similar to above, except it assigns the new value rather than displaying the current one.*/
    @Inject(at = @At("HEAD"), method = "lambda$getModConfigScreenFactory$7")
    private static void setCustomEntityName(String activePet, PetsConfig CONFIG, String name, CallbackInfo ci) {
        if (CONFIG.activePet.equals(CUSTOM_ENTITY_VALUE)) {
            CUSTOM_CONFIG.customEntityName = name;
        }
    }
}
```

There you go! You've made a custom entity. Before we leave, let's add one more thing. Navigate to your initializer
class.

```java
public class ExamplePetsModAddonClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        /*Add this to the list of add-ons registered, so that the logs correctly print which
         * add-ons are active.*/
        PetsClientInitializer.ADDONS.add(MOD_ID);
    }
}
```

Now, we are officially finished. Remember, if you have any questions or issues, please, open an issue on my GitHub. Have
a great day!

### Creating a Skin

To create a skin, we first need to add a new value to our config that represents our skin.

```java
@Config(name = "petsmod_addon_config")
public class ExamplePetsModAddonConfig implements ConfigData {
    public String customEntityName;
    public String customEntitySkin;
}
```

Now, let's make sure that it isn't null in our initializer class.

```java
private void checkForNullObjects() {
        CUSTOM_CONFIG.customEntityName = Utils.checkNullString(CUSTOM_CONFIG.customEntityName);
        CUSTOM_CONFIG.customEntitySkin = Utils.checkNullString(CUSTOM_CONFIG.customEntitySkin, "normal");
}
```

Great, we now have our object! Let's go ahead and make sure that our pet will actually change its texture based on its
skin. To do this, we modify the getTextureLocation() method with an if statement.

```java
public class CustomEntityRenderer extends PetRenderer<CustomEntity, GhastRenderState, GhastModel> {
    @Override
    public void getTextureLocation(GhastRenderState state) {
        if (CUSTOM_CONFIG.customEntitySkin.equals("normal")) {
            return ResourceLocation.withDefaultNamespace("textures/entity/ghast/ghast.png");
        } else {
            return ResourceLocation.withDefaultNamespace("textures/entity/ghast/ghast_shooting.png");
        }
    }
}
```

However, we haven't actually given the user the ability to assign custom skins. Let's do that right now. Head over to
your CentralMixin.

```java
@Mixin(Central.class)
public class CentralMixin {
    @Inject(at = @At("HEAD"), method = "lambda$createPetSkinCommand$1")
    private static void createPetSkinCommand(CommandContext<?> context, CallbackInfoReturnable<Integer> cir) {
        String skin = StringArgumentType.getString(context, "skin");
        if (CONFIG.activePet.equals(CUSTOM_ENTITY_VALUE)) {
            //get what the user inputted and assign it if our custom entity is active
            if (skin.equals("normal")) {
                CUSTOM_CONFIG.customEntitySkin = "normal";
            } else if (skin.equals("angry")) {
                CUSTOM_CONFIG.customEntitySkin = "angry";
            }
        }
        //save the config
        AutoConfig.getConfigHolder(ExamplePetsModAddonConfig.class).save();
    }
}
```

Great, but the user won't be able to actually see the suggestion pop up in chat. Let's add that real quick:

```java
@Mixin(Central.class)
public class CentralMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/suggestion/SuggestionsBuilder;buildFuture()Ljava/util/concurrent/CompletableFuture;"),
            method = "lambda$new$0")
    private static void addSkinSuggestions(CommandContext<?> context, SuggestionsBuilder builder, CallbackInfoReturnable<CompletableFuture> cir) {
        String remaining = builder.getRemainingLowerCase();
        if (CONFIG.activePet.equals(CUSTOM_ENTITY_VALUE)) {
            for (String s : MY_SKIN_SUGGESTIONS) {
                if (s.toLowerCase().startsWith(remaining)) {
                    builder.suggest(s);
                }
            }
        }
    }
}
```

Oop, wait a second! We haven't actually created our suggestions yet. Let's do that. Add this to the top of your file:

```java
@Mixin(Central.class)
public class CentralMixin {
    //these are what the user will see pop up in their suggestion list when they type /petspecies.
    @Unique
    private static final List<String> MY_SKIN_SUGGESTIONS = List.of("normal", "angry");
}
```

Almost done! Let's add this to the end of our createSummonCommand() method.

```java
@Mixin(Central.class)
public class CentralMixin {
    @Inject(at = @At("HEAD"), method = "lambda$createPetSpeciesCommand$1", cancellable = true)
    private static void createSummonCommand(CommandContext<FabricClientCommandSource> context, CallbackInfoReturnable<Integer> cir) {
        String species = StringArgumentType.getString(context, "species");
        if (Objects.equals(species, CUSTOM_ENTITY_VALUE_NO_SPACES) || Objects.equals(species, CUSTOM_ENTITY_VALUE)) {
            spawnCustomEntity(context, customEntity, CUSTOM_ENTITY_VALUE);
            //update the suggestions since we return early
            updateSuggestions(Minecraft.getInstance());
            cir.setReturnValue(1);
        }
    }
}
```

You might have to shadow (inherit the method) updateSuggestions since it was private until 0.7.7.

```java
@Mixin(Central.class)
public class CentralMixin {
    /**Shadow updateSuggestions since it wasn't made public until 0.7.7*/
    @Shadow
    private static void updateSuggestions(Minecraft client) {

    }
}
```