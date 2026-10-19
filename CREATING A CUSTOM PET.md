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
    maven { url "https://api.modrinth.com/maven" }
}
```

```groovy
dependencies {
    implementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_api_version}"
    implementation("com.terraformersmc:modmenu:${project.modmenu_version}")
    implementation "maven.modrinth:pets-mod:${project.petsmod_version}"
    //add the sources jar as well
    implementation "maven.modrinth:pets-mod:${project.petsmod_version}:sources"
}
```

Now you will have to add the versions to your gradle.properties. The latest mod versions as of Minecraft 26.1.2 are
available below:

```properties
fabric_api_version=0.161.0+26.3
modmenu_version=20.0.3
petsmod_version=0.8.6
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

Additionally, make sure to depend on PetsMod `0.8.6` or greater in your `fabric.mod.json`.

`src/main/resources/fabric.mod.json`

```JSON
"depends": {
"pets-mod": ">=0.8.6"
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

    public static final ModelLayerLocation CUSTOM_ENTITY_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(MOD_ID, "custom_entity"), "main");

    public CustomEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new GhastModel(context.bakeLayer(CUSTOM_ENTITY_LOCATION)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull GhastRenderState state) {
        return Identifier.withDefaultNamespace("textures/entity/ghast/ghast.png");
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

    public static final ModelLayerLocation CUSTOM_ENTITY_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(MOD_ID, "custom_entity"), "main");

    public CustomEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new GhastModel(context.bakeLayer(CUSTOM_ENTITY_LOCATION)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull GhastRenderState state) {
        return Identifier.withDefaultNamespace("textures/entity/ghast/ghast.png");
    }

    @Override
    public @NotNull GhastRenderState createRenderState() {
        return new GhastRenderState();
    }
}
```

Great, now it has built-in logic and custom rendering mechanics. Now, let's get started with actually adding the pet
into PetsMod!

### Adding the Pet

Adding the pet is quite easy as of 0.8.6. Locate your client initializer class (the one that implements
`ClientModInitializer`) and add these fields to your class:

```java
    public static final PetsModAddons ADDON = PetsModAddons.registerAddon(MOD_ID);
public static String customEntityName = "";
public static String customEntitySkin = "normal"; //optional
```

Now, in your `onInitializeClient` method, add this little bit:

```java
try{
        ADDON.addPet(ExampleEntityInitializer.EXAMPLE_ENTITY, "example_entity",this.getClass().

getDeclaredField("customEntityName"),Optional.

empty());
        }catch(
NoSuchFieldException e){
        throw new

RuntimeException(e);
}
```

And if you want to add a skin, you would change Optional.empty() to:

```java
Optional.of(new PetSkin(List.of("normal", "red"), this.

getClass().

getDeclaredField("customEntitySkin")));
```

Customize the strings in List.of() to be the skins that you want for your pet. Good job, you added a pet into Pets Mod!