Before we start, please be aware that this is a fairly complex project, and is a lot more hands-on than creating an item or a block. But don't worry about getting lost - I'll be here every step of the way!

# How to Create Your Own Custom Entity in Minecraft

Hello there! Are you looking to learn how to create your own custom entity in Minecraft, whether it be friendly, fearsome, or something else? Then this is the place to be! This guide covers everything you need to know about creating your own entity, from the model to the attributes to the rendering! Without further ado, let's dive into it!

## Creating a Model and Texture

Before we even get started with the coding, we need a model and texture. For this, I would suggest [Blockbench](https://web.blockbench.net/), a free, low-poly 3D modeling software. You can choose to use someone elses model (make sure that you are allowed to use it - don't just go downloading random people's models without asking for permission first!) or you can create your own.

**If you have downloaded someone elses project:** import it into blockbench by hitting 'File -> Open'. Once you have it open, select 'File'. Now click 'Convert Project' -> 'Modded Entity'. The conversion might not come out perfect - you might have to make a few tweaks. 

Now, in the top of the screen, you will see a toolbar, which contains things like moving, rotating, etc. Go to the far right and you will see a button called 'paint'. Select that and color in your entity. You can also import someone else's texture (once again, with permission!), click on 'help', click 'search and run action', and look for 'Select All'. Once you have everything selected, go to the bottom left, where you will see a panel called 'Textures'. Click 'Import Texture' and choose your file.

Once you have the texture imported and all elements are selected, click on your texture and drag it onto the elements, and it should look perfect!

**If you are creating your own model:** You are going to want to create a new 'Modded Entity'. [this](https://www.youtube.com/watch?v=dsax5p4brN8) is a great guide on how to use Blockbench. Remember, you need both a model (the shape of the entity) and the texture (the color of the entity) to work!

> TIP: If you go to 'File -> Plugins' and search for 'CEM' you will see an easy way to easily access and modify Minecraft's models.

**Once you have your model and texture ready:** Download the texture by hitting the little 'save' icon next to it in the textures panel. Once you have this, hit 'File -> Project', and change the dropdown menu 'Export Version' to 'Fabric 1.17+'. Now to 'File -> Export' and hit 'Export Java Entity.' Make sure to keep track of where these files are - we will need them in a little bit.

> TIP: If you want, you can simply use the model and texture I used for my entity. The model file and texture are below:

<details>
<summary>My Model File (click me!)</summary>

```
public class head_player - Converted<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "head_player_- converted"), "main");
	private final ModelPart head;

	public head_player - Converted(ModelPart root) {
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
```
</details>

<details>
<summary>My Texture File (click me!)</summary>
<img width="64" height="64" alt="exampleentity" src="https://github.com/user-attachments/assets/dee1ae65-e4a3-47b8-8ec4-819650d5b3fc" />
	Right click and click on 'Save Image As' to save the image.
</details>

## Setting up your project

The first thing you will need to do is download the example fabric mod that has all of the filepaths and blueprints all set up. Make sure that these specific boxes are checked, or you won't be able to follow along properly:
<img width="1262" height="713" alt="If this image isn't loading, please refresh the page. If this persists, create an [issue](https://github.com/downloadableduck/example-entity/issues) and follow the instructions below." src="https://github.com/user-attachments/assets/927a5835-85fa-45c4-9741-3aa2bb4bc420" />

If you could not tell, make sure the boxes saying 'Mojang Mappings' and "Split client and common sources' are checked, while 'Kotlin Programming Language' and 'Data Generation' are left unchecked. (Having Kotlin Programming Language and Data Generation checked actually will not affect anything - just make sure Mojang Mappings and Split client and common sources are checked.) Once this is done, you will want to download the ZIP, extract it, and open it in the IDE of your choice.

Now, run `gradle genSources` (or `./gradlew genSources` on Mac) to generate Minecraft's sources. Once this is done, it is time to get started on the actual coding!

## Creating the Entity & Logic

> TIP: Remember to replace the icon.png with your own custom mod icon and change the fabric.mod.json to reflect your actual mod!

For our first step, we will jump right in to creating our entity, and focus on rendering it once the base entity has been created. To start, we will create a new class that extends `Animal`, but you can change the extension to anything that you like, such as PathFinderMob, Monster, or FlyingAnimal. Remember, even though we call our entity ExampleEntity, make sure to name the files and variables something that accurately reflects the mob you are making!

`src/main/java/[your package]/ExampleEntity.java`
```
public class ExampleEntity extends Animal {


    /**The first thing we will do is create an arg-constructor matching super that takes EntityType and Level paramaters.*/
    public ExampleEntity(final EntityType<? extends ExampleEntity> type, final Level level) {
        super(type, level);
    }
        /**Creates the attributes that the entity will have - health, movement speed, etc. Make sure to tweak these to match your mob - if you are creating a custom cat, you don't want the movement speed to be 0.25!*/
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 5.0F).add(Attributes.MOVEMENT_SPEED, 0.25F);
    }
}
```

> TIP: You will see a red error stating that 'Class 'ExampleEntity' must either be declared abstract or implement abstract method 'isFood(ItemStack)' in 'Animal'. Don't worry about this - we'll cover it in a minute.

After this, we will set up our sounds for the entity. This controls what plays when the entity dies, moves, gets hit, etc.

`src/main/java/[your package]/ExampleEntity.java`
```

public class ExampleEntity {

 { . . . your other code . . . }

 /**The sound that will be played randomly - think of a chicken bawking.*/
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    /**The sound that will be played when you - or another force - damages the entity.
     * Think of yourself punching a chicken.*/
    protected SoundEvent getHurtSound(final DamageSource source) {
        return SoundEvents.CHICKEN_HURT;
    }

    /**The sound that will be played when the entity dies - think of the screaming sound that
     * endermen play when they die.
     */
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENDERMAN_DEATH;
    }

    /**The sound that will play when the entity is walking/moving - think of a slime making
     * 'splurch' noises while it hops about.
     */
    protected void playStepSound(final BlockPos pos, final BlockState blockState) {
        this.playSound(SoundEvents.SLIME_SQUISH, 0.15F, 1.0F);
    }
}
```

> TIP: If you don't know what sound you want for your mod, you can type `return SoundEvents.` and look at what the available fields are. If you don't like any of the sounds available, you can try creating a new sound by following [this tutorial](https://docs.fabricmc.net/develop/sounds/custom)

Great! We have two things that we have to do. The first is **goals**. Goals are what allow the mobs to function - it essentially turns them from a lifeless statue into a living, breathing being. There are many different goals you could add to your mob, depending on what it is - for a hostile mob, you might want to add a FollowMobGoal, a FleeSunGoal, and a InteractGoal to make it attack the player. However, our mob will be peaceful, and we will only add a handful of goals to make it function: a RandomStrollGoal, which will make it wander around aimlessly occasionally, and a RandomLookAroundGoal, which will make it look around aimlessly.

`src/main/java/[your package]/ExampleEntity.java`
```

publiic class ExampleEntity {
  { . . . your other code here . . . }

  @Override
    public void registerGoals() {
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0D));
    }
}

```

Perfect! Now, we will add one more function - allowing the mob to **breed** with others of its kind to make more of it.

You may have noticed that the line declaring the class is red - we will solve that now by overriding the isFood() method in Animal.

`src/main/java[your package]/ExampleEntity.java`
```
public class ExampleEntity {
    { . . . your other code here . . . }

    /**This is a required override of the Animal class - itemStack.is(Item) decides which
     * item(s) can be used to breed the mob.*/
    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.PLAYER_HEAD);
    }
}
```

Here I used the PLAYER_HEAD item, but you can choose whichever one you like - you can also make multiple items work, like such:
`return itemStack.is(Items.PLAYER_HEAD) || itemStack.is(ItemTags.ARMADILLO_FOOD)`

> TIP: As shown above, you can add multiple items to your entity in one statement by using ItemTags instead of each individual Item.

Right now, this doesn't do anything except allow the player to feed the mob with the item of your choice because we haven't added the logic that allows the entity to breed. However, to first add that logic we will need to jump into our `initializer` class to add a couple lines.

Firstly, let's create an entity that will actually exist ingame, and then add the logic to make it breed. Open your `initializer` class that came with the example template that we generated earlier.

`src/main/java/[your package]/ExampleEntityInitializer.java`
```
public class ExampleEntityInitializer {
    { . . . your MOD_ID and LOGGER here . . . }

    /**A key for our EntityType DUCK initialized below.*/ 
    public static final ResourceKey<EntityType<?>> EXAMPLE_ENTITY_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "exampleentity"));

    @Override
    public void onInitialize() {

    }
}
```

You might have seen in the Javadoc comment that this is a key for our `EntityType DUCK` that we need to create. Let's do that now, and register our entity while we are at it:


`src/main/java/[your package]/ExampleEntityInitializer.java`
```
public class ExampleEntityInitializer {
    { . . . your other code here . . . }

        /**This creates the entity so that it will exist in-game.*/
    public static final EntityType<ExampleEntity> EXAMPLE_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "exampleentity"),
            EntityType.Builder.of(ExampleEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    /*This is how tall the hitbox will be - so where other entities will be able
                     * to hit the entity.*/
                    .eyeHeight(1)
                    .build(EXAMPLE_ENTITY_KEY));
    
    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(EXAMPLE_ENTITY, ExampleEntity.createAttributes().build());
        /*Registers the entities attributes - so how much health it has, how much speed it has, etc.
         * You might get a warning saying that the call to 'register' always fails - but you are
         * free to ignore that. If you check in-game, you will see that your entity works just as
         * intended!
         */
    }
}
```

> TIP: If you are on version 1.21.10 or **below**, replace `ResourceLocation` with `ResourceLocation`.

This makes the entity actually exist in game, and you can summon it using /summon [Your mod ID:your entity ID (defined above in our `ResourceLocation.fromNamespaceAndPath()`)

Now that our entity exists in-game, we can switch to our `ExampleEntity.java` and add the method getBreedOffspring, which will create a new entity of the same type when it breeds.

`src/main/java/[your package]/ExampleEntity.java`
```
public class ExampleEntity {
    { . . . your other code here . . . }
    public ExampleEntity getBreedOffspring(final ServerLevel level, final AgeableMob partner) {
        return EXAMPLE_ENTITY.create(level, EntitySpawnReason.BREEDING);
    }
}
```

However, if you try breeding the entity in-game, you will see that it does not try to breed - this is because we haven't registered a new `BreedGoal` which makes the entity _want_ to breed. 
> TIP: If you want the player to follow the entity while it is holding the item used to breed it, you should add a new `TemptGoal`.

`src/main/java/[your package]/ExampleEntity.java`
```
public class ExampleEntity {
    { . . . your other code here . . . }

    public void registerGoals() {

    { . . . your other goals here . . . }
            this.goalSelector.addGoal(3, new BreedGoal(this, 1.0));
    }
}
```

And that's it for our entity, it should be able to move around and breed now - but what use is an entity if you can't see it?

## Rendering the Entity

In order for an entity to appear in-game, it needs to be **rendered** for the client to be able to see it. Let's dive in by creating a dummy `ExampleEntityRenderState` class that extends `LivingEntityRenderState`. However, since we are doing **rendering**, we should do this in our `client` package instead of our `main` package.

`src/client/java/[your package]/ExampleEntityRenderState.java`
```
public class ExampleEntityRenderState extends LivingEntityRenderState {
}
```

Yep, that's it - we don't need anything in this file, it is just a helper class to assist multiple methods that we will be creating in just a minute! 

Remember that entity that we modeled in our [Creating a Model & Texture section](#creating-a-model-and-texture)? Well, now it's time to put that to use! 

The first thing we will do is create a new file in our `client` package called `ExampleEntityModel`, which will house a ModelLayerLocation and an arg-constructor matching super.

`src/client/java/[your package]/ExampleEntityModel.java`
```
public class ExampleEntityModel extends EntityModel<ExampleEntityRenderState> {
    /**Sets up the layer, which is where the game will place the texture. Main is the module it is in.*/
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(ExampleEntityInitializer.MOD_ID, "exampleentity"), "main"
    );
    public ExampleEntityModel(final ModelPart root) {
        super(root);
    }
}
```
> TIP: If something seems useless now, be patient! LAYER_LOCATION will be utilized soon.

Now that we have that, let's open up our Java file that we exported from Blockbench. You will see a method called `getTexturedModelData()` - copy the method in your file and paste it directly below our current code. This will house all of the different parts that make up the entity - the head, the body, the legs, etc. To keep the example mod simple, I have only added one part to my entity - but yours might have dozens.

`src/client/java/[your package]/ExampleEntity.java`
```
public class ExampleEntityModel extends EntityModel<ExampleEntityRenderState> {
		 { . . . your other code here . . . }

   public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition head = partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));        /**Creates all the cubes that will be present in your entities' model. Your model might have a few of these, or it might have dozens.*/

        /*This creates the layer. Make sure that the dimensions (64, 64 in my case) match the dimensions of your texture.*/
        return LayerDefinition.create(meshDefinition, 64, 64);
    }
}
```

> TIP: Are you seeing a lot of red? You might have exported using Yarn's mappings accidentally, or exported it correctly but are using Yarn's mappings. Take this translation key, it will help you translate between mappings:

<details>
<summary>All Mappings & Their Translations (click me!)</summary>
 Mojang mappings are on the left, Yarn mappings are on the right. Notice the difference between PartDefinition (the class) and partDefinition (meshDefinition.getRoot());

 LayerDefinition -> TexturedModelData
 
 MeshDefinition -> ModelData
 
 PartDefinition -> ModelPartData
 
 partDefinition.addOrReplaceChild() -> partDefinition.addChild()
 
 CubeListBuilder -> ModelPartBuilder
 
 CubeListBuilder.create().textOffs() -> ModelPartBuilder.create.uv()
 
 CubeListBuilder.create().addBox() -> CubeListBuilder.create.cuboid()

 CubeDeformation -> Dilation

 PartPose.offset() -> ModelTransform.pivot()
 
</details>

Finally, right below that we will add a dummy `setupAnim()` method. This space is suitable for adding animations to your model if you wish to do so, but we will just keep it as a dummy method for now.

`src/client/java/[your package]/ExampleEntityModel.java`
```
public class ExampleEntityModel extends EntityModel<ExampleEntityRenderState> {
	{ . . . your other code and model here . . . }

	public void setupAnim(final ExampleEntityRenderState state) {
		super.setupAnim(state);
	}
}
```
Great, that is our model set up! However, we still need a **renderer**. Let's do that right now. Create a new class in your `client` package called `ExampleEntityRenderer`. 

`src/client/java/[your package]/ExampleEntityRenderer.java`
```
public class ExampleEntityRenderer extends MobRenderer<ExampleEntity, ExampleEntityRenderState, ExampleEntityModel> {

	/**Note the use of our LAYER_LOCATION field that we created earlier.*/
    public ExampleEntityRenderer(final EntityRendererProvider.Context context) {
        super(context, new ExampleEntityModel(context.bakeLayer(ExampleEntityModel.LAYER_LOCATION)), 0.3F);
    }

    /**This method creates the 'render state' or how it will look ingame.*/
    public ExampleEntityRenderState createRenderState() {
        return new ExampleEntityRenderState();
    }
}
```

Now, the last thing we will need to do is add a **texture** or the color and design of our entity - how it will look ingame. Take the texture you exported from Blockbench and copy it. Now, create a new directory in your `main/resources` folder: 
`src/main/resources/assets/[your mod]/textures/entity`. Drop your texture in the entity folder, and then hop back to your `ExampleEntityRenderer` class. Add this override at the bottom of your code:

`src/client/java/[your package]/ExampleEntityRenderer.java`
```
public class ExampleEntityRenderer extends MobRenderer<ExampleEntity, ExampleEntityRenderState, ExampleEntityModel> {
	    /**This method gets the texture and 'slaps' it onto the model in-game.
    @Override
    public @NotNull ResourceLocation getTextureLocation(final ExampleEntityRenderState state) {
        return ResourceLocation.fromNamespaceAndPath(ExampleEntityInitializer.MOD_ID, "textures/entity/exampleentity.png");
    }
}
```

> TIP: You might have noticed that we used the filepath from the module root (our **main** folder) rather than what we usually do with ResourceLocation.fromNamespaceAndPath(), which usually automatically appends `textures` and the `.png` extension. This is because of the way Minecraft handles entity registries, so using the path from the module root will work just fine.

Now that we have everything we need for the entity to function visually, let's hop over to our `client` initializer. For me, it is `ExampleEntityClient.java`.

`src/client/java/[your package]/ExampleEntityClient.java`
```
public class ExampleEntityClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ExampleEntityInitializer.EXAMPLE_ENTITY, ExampleEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ExampleEntityModel.LAYER_LOCATION, ExampleEntityModel::getTexturedModelData);
	}
```

Now, launch your game, and type /summon [your mod ID:your entity ID]. You should see it appear in-game, and move around like a normal mob.

> TIP: Is something not working? Re-visit the section that covers that subject and compare your code with mine.

Congrats, you have created your first entity! Now, let's make it spawn naturally.

## Adding Spawns

Since this is a server-sided feature, we are going to be using the `main` package for this task. Create a new file in your package called `ExampleEntitySpawns.java`.

`src/main/java/[your package]/ExampleEntitySpawns.java`
```
public class ExampleEntitySpawns {
    public static void addExampleEntitySpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.CHERRY_GROVE),
                /*Adds the biomes which the entity can spawn in. You can add as many as you want,
                 * and separate them with commas as shown above.*/
                MobCategory.CREATURE, ExampleEntityInitializer.EXAMPLE_ENTITY, /*the weight*/ 100, /*the minimum group size*/ 2, /*the maximum group size*/ 7);
        /*Adds the biomes which the entity can spawn in. You can add as many as you want,
         * but separate them with commas as shown above.
         * Tip: The weight is how often the entity spawns in comparison to other entities. Increasing
         * it will increase the chance of your entity spawning, but also decrease the chance of other
         * entities spawning.*/
        SpawnPlacements.register(ExampleEntityInitializer.EXAMPLE_ENTITY, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE, Animal::checkAnimalSpawnRules);
        /*These are the conditions that our entity needs to spawn - does it spawn
         * underwater, or on land? Does it spawn in the sky?
         */
    }
}
```
Modify this file to your liking - you could make your mob spawn in the Nether, in the End, underwater, in the sky, alone, in groups, pretty much anything you want!

Now that we have our file set up, we need to call our `addExampleEntitySpawns()` method in our `initializer` class. In your `onInitialize()` method, add this line of code:
`src/main/java/[your package]/ExampleEntityInitializer.java`
```
public class ExampleEntityInitializer implements ModInitializer {
	{ . . . your other code here . . . }

	public void onInitialize() {
		        ExampleEntitySpawns.addExampleEntitySpawns();
	}
}
```

There we go! In-game, run `/locate biome` and choose any biome that you included in your `BiomeSelectors.includeByKey` method above. Walk around a bit and you should see your entity hanging around!

# Conclusion

This was a really fun project for me to make, and I hope you had a lot of fun as well. If you run into any issues, please open an [issue](https://github.com/downloadableduck/example-entity/issues). However, you **must** specify whether this is an issue you are having with your mod or if it is an issue with my template mod or my instructions. If you do not, I will not be able to properly help you.

Thank you for reading, I hope you had a lot of fun creating your own custom entity in Minecraft and I hope this was helpful!
