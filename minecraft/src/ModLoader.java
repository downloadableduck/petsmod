//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.security.DigestException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public final class ModLoader {
    private static final Map blockModels = new HashMap();
    private static final Map blockSpecialInv = new HashMap();
    private static final File cfgdir = new File(Minecraft.getMinecraftDir(), "/config/");
    private static final File cfgfile;
    public static Level cfgLoggingLevel;
    private static Map classMap = null;
    private static long clock = 0L;
    private static Field field_armorList = null;
    private static Field field_modifiers = null;
    private static Field field_TileEntityRenderers = null;
    private static boolean hasInit = false;
    private static final Map inGameHooks = new HashMap();
    private static final Map inGUIHooks = new HashMap();
    private static Minecraft instance = null;
    private static final Map keyList = new HashMap();
    private static String langPack = null;
    private static Map localizedStrings = new HashMap();
    private static final File logfile = new File(Minecraft.getMinecraftDir(), "ModLoader.txt");
    private static final Logger logger = Logger.getLogger("ModLoader");
    private static FileHandler logHandler = null;
    private static Method method_RegisterEntityID = null;
    private static Method method_RegisterTileEntity = null;
    private static final File modDir = new File(Minecraft.getMinecraftDir(), "/mods/");
    private static final LinkedList<BaseMod> modList = new LinkedList<BaseMod>();
    private static int nextBlockModelID = 1000;
    private static final Map overrides = new HashMap();
    private static final Map packetChannels = new HashMap();
    public static final Properties props = new Properties();
    private static BiomeGenBase[] standardBiomes;
    public static final String VERSION = "ModLoader 1.5.2";
    private static NetClientHandler clientHandler = null;
    private static final List<ICommand> commandList = new LinkedList<ICommand>();
    private static final Map tradeItems = new HashMap();
    private static final Map containerGUIs = new HashMap();
    private static final Map trackers = new HashMap();
    private static final Map dispenserBehaviors = new HashMap();
    private static final Map customTextures = new HashMap();
    private static SoundPool soundPoolSounds;
    private static SoundPool soundPoolStreaming;
    private static SoundPool soundPoolMusic;

    public static void addAchievementDesc(Achievement achievement, String s, String s1) {
        try {
            if (achievement.getName().contains(".")) {
                String[] as = achievement.getName().split("\\.");
                if (as.length == 2) {
                    String s2 = as[1];
                    addLocalization("achievement." + s2, s);
                    addLocalization("achievement." + s2 + ".desc", s1);
                    setPrivateValue(StatBase.class, achievement, "statName", StatCollector.translateToLocal("achievement." + s2));
                    setPrivateValue(Achievement.class, achievement, "achievementDescription", StatCollector.translateToLocal("achievement." + s2 + ".desc"));
                } else {
                    setPrivateValue(StatBase.class, achievement, "statName", s);
                    setPrivateValue(Achievement.class, achievement, "achievementDescription", s1);
                }
            } else {
                setPrivateValue(StatBase.class, achievement, "statName", s);
                setPrivateValue(Achievement.class, achievement, "achievementDescription", s1);
            }
        } catch (IllegalArgumentException illegalargumentexception) {
            logger.throwing("ModLoader", "AddAchievementDesc", illegalargumentexception);
            throwException(illegalargumentexception);
        } catch (SecurityException securityexception) {
            logger.throwing("ModLoader", "AddAchievementDesc", securityexception);
            throwException(securityexception);
        } catch (NoSuchFieldException nosuchfieldexception) {
            logger.throwing("ModLoader", "AddAchievementDesc", nosuchfieldexception);
            throwException(nosuchfieldexception);
        }

    }

    public static void addEntityTracker(BaseMod mod, Class entityClass, int id, int viewDistance, int updateFrequency, boolean trackMotion) {
        if (entityClass == null) {
            throw new IllegalArgumentException();
        } else {
            if (!Entity.class.isAssignableFrom(entityClass)) {
                Exception exception = new Exception(entityClass.getCanonicalName() + " is not an entity.");
                logger.throwing("ModLoader", "addEntityTracker", exception);
                throwException(exception);
            }

            trackers.put(entityClass, new EntityTrackerNonliving(mod, entityClass, id, viewDistance, updateFrequency, trackMotion));
        }
    }

    public static Map getTrackers() {
        return Collections.unmodifiableMap(trackers);
    }

    public static int addAllFuel(int id, int metadata) {
        logger.finest("Finding fuel for " + id);
        int result = 0;

        for(Iterator iter = modList.iterator(); iter.hasNext() && result == 0; result = ((BaseMod)iter.next()).addFuel(id, metadata)) {
        }

        if (result != 0) {
            logger.finest("Returned " + result);
        }

        return result;
    }

    public static void addAllRenderers(Map renderers) {
        if (!hasInit) {
            init();
            logger.fine("Initialized");
        }

        for(BaseMod mod : modList) {
            mod.addRenderer(renderers);
        }

    }

    public static int addArmor(String s) {
        try {
            String[] as = (String[])field_armorList.get((Object)null);
            List list = Arrays.asList(as);
            ArrayList arraylist = new ArrayList();
            arraylist.addAll(list);
            if (!arraylist.contains(s)) {
                arraylist.add(s);
            }

            int i = arraylist.indexOf(s);
            field_armorList.set((Object)null, arraylist.toArray(new String[0]));
            return i;
        } catch (IllegalArgumentException illegalargumentexception) {
            logger.throwing("ModLoader", "AddArmor", illegalargumentexception);
            throwException("An impossible error has occured!", illegalargumentexception);
        } catch (IllegalAccessException illegalaccessexception) {
            logger.throwing("ModLoader", "AddArmor", illegalaccessexception);
            throwException("An impossible error has occured!", illegalaccessexception);
        }

        return -1;
    }

    public static void addBiome(BiomeGenBase biomegenbase) {
        BiomeGenBase[] abiomegenbase = GenLayerBiome.biomeArray;
        List list = Arrays.asList(abiomegenbase);
        ArrayList arraylist = new ArrayList();
        arraylist.addAll(list);
        if (!arraylist.contains(biomegenbase)) {
            arraylist.add(biomegenbase);
        }

        GenLayerBiome.biomeArray = (BiomeGenBase[])arraylist.toArray(new BiomeGenBase[0]);
    }

    public static void addCommand(ICommand cmd) {
        commandList.add(cmd);
    }

    public static void addDispenserBehavior(Item item, IBehaviorDispenseItem behavior) {
        dispenserBehaviors.put(item, behavior);
    }

    public static void registerServer(MinecraftServer server) {
        ICommandManager manager = server.getCommandManager();
        if (manager instanceof CommandHandler) {
            CommandHandler handler = (CommandHandler)manager;

            for(ICommand cmd : commandList) {
                handler.registerCommand(cmd);
            }

            for(Object behaviorObj : dispenserBehaviors.entrySet()) {
                Map.Entry behavior = (Map.Entry)behaviorObj;
                BlockDispenser.dispenseBehaviorRegistry.putObject(behavior.getKey(), behavior.getValue());
            }

        }
    }

    public static void addLocalization(String s, String s1) {
        addLocalization(s, "en_US", s1);
    }

    public static void addLocalization(String s, String s1, String s2) {
        Map obj;
        if (localizedStrings.containsKey(s1)) {
            obj = (Map)localizedStrings.get(s1);
        } else {
            obj = new HashMap();
            localizedStrings.put(s1, obj);
        }

        obj.put(s, s2);
    }

    public static void addTrade(int profession, TradeEntry entry) {
        List list = null;
        if (tradeItems.containsKey(profession)) {
            list = (List)tradeItems.get(profession);
        } else {
            list = new LinkedList();
            tradeItems.put(profession, list);
        }

        list.add(entry);
    }

    public static List getTrades(int profession) {
        if (profession != -1) {
            return tradeItems.containsKey(profession) ? Collections.unmodifiableList((List)tradeItems.get(profession)) : null;
        } else {
            List list = new LinkedList();

            for(Object entryObj : tradeItems.values()) {
                list.addAll((List)entryObj);
            }

            return list;
        }
    }

    private static void addMod(ClassLoader classloader, String s) {
        try {
            String s1 = s.split("\\.")[0];
            if (s1.contains("$")) {
                return;
            }

            if (props.containsKey(s1) && (props.getProperty(s1).equalsIgnoreCase("no") || props.getProperty(s1).equalsIgnoreCase("off"))) {
                return;
            }

            Package package1 = ModLoader.class.getPackage();
            if (package1 != null) {
                s1 = package1.getName() + "." + s1;
            }

            Class class1 = classloader.loadClass(s1);
            if (!BaseMod.class.isAssignableFrom(class1)) {
                return;
            }

            setupProperties(class1);
            BaseMod basemod = (BaseMod)class1.newInstance();
            if (basemod != null) {
                modList.add(basemod);
                logger.fine("Mod Initialized: \"" + basemod.toString() + "\" from " + s);
                System.out.println("Mod Initialized: " + basemod.toString());
            }
        } catch (Throwable throwable) {
            logger.fine("Failed to load mod from \"" + s + "\"");
            System.out.println("Failed to load mod from \"" + s + "\"");
            logger.throwing("ModLoader", "addMod", throwable);
            throwException(throwable);
        }

    }

    public static void addName(Object obj, String s) {
        addName(obj, "en_US", s);
    }

    public static void addName(Object obj, String s, String s1) {
        String s2 = null;
        if (obj instanceof Item) {
            Item item = (Item)obj;
            if (item.getUnlocalizedName() != null) {
                s2 = item.getUnlocalizedName() + ".name";
            }
        } else if (obj instanceof Block) {
            Block block = (Block)obj;
            if (block.getUnlocalizedName() != null) {
                s2 = block.getUnlocalizedName() + ".name";
            }
        } else if (obj instanceof ItemStack) {
            ItemStack itemstack = (ItemStack)obj;
            String s3 = Item.itemsList[itemstack.itemID].getUnlocalizedName(itemstack);
            if (s3 != null) {
                s2 = s3 + ".name";
            }
        } else {
            Exception exception = new Exception(obj.getClass().getName() + " cannot have name attached to it!");
            logger.throwing("ModLoader", "AddName", exception);
            throwException(exception);
        }

        if (s2 != null) {
            addLocalization(s2, s, s1);
        } else {
            Exception exception1 = new Exception(obj + " is missing name tag!");
            logger.throwing("ModLoader", "AddName", exception1);
            throwException(exception1);
        }

    }

    public static void addRecipe(ItemStack itemstack, Object... aobj) {
        try {
            Method method = CraftingManager.class.getDeclaredMethod("addRecipe", ItemStack.class, Object[].class);
            method.setAccessible(true);
            method.invoke(CraftingManager.getInstance(), new Object[]{itemstack, aobj});
        } catch (Exception exception) {
            logger.throwing("ModLoader", "addRecipe", exception);
            throwException(exception);
        }

    }

    public static void addShapelessRecipe(ItemStack itemstack, Object... aobj) {
        try {
            Method method = CraftingManager.class.getDeclaredMethod("addShapelessRecipe", ItemStack.class, Object[].class);
            method.setAccessible(true);
            method.invoke(CraftingManager.getInstance(), new Object[]{itemstack, aobj});
        } catch (Exception exception) {
            logger.throwing("ModLoader", "addShapelessRecipe", exception);
            throwException(exception);
        }

    }

    public static void addSmelting(int i, ItemStack itemstack, float xp) {
        FurnaceRecipes.smelting().addSmelting(i, itemstack, xp);
    }

    public static void addSpawn(Class class1, int i, int j, int k, EnumCreatureType enumcreaturetype) {
        addSpawn((Class)class1, i, j, k, enumcreaturetype, (BiomeGenBase[])null);
    }

    public static void addSpawn(Class class1, int i, int j, int k, EnumCreatureType enumcreaturetype, BiomeGenBase[] abiomegenbase) {
        if (class1 == null) {
            throw new IllegalArgumentException("entityClass cannot be null");
        } else if (enumcreaturetype == null) {
            throw new IllegalArgumentException("spawnList cannot be null");
        } else {
            if (abiomegenbase == null) {
                abiomegenbase = standardBiomes;
            }

            for(int l = 0; l < abiomegenbase.length; ++l) {
                List list = abiomegenbase[l].getSpawnableList(enumcreaturetype);
                if (list != null) {
                    boolean flag = false;

                    for(Object spawnObj : list) {
                        SpawnListEntry spawnlistentry = (SpawnListEntry)spawnObj;
                        if (spawnlistentry.entityClass == class1) {
                            setItemWeight(spawnlistentry, i);
                            spawnlistentry.minGroupCount = j;
                            spawnlistentry.maxGroupCount = k;
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        list.add(new SpawnListEntry(class1, i, j, k));
                    }
                }
            }

        }
    }

    public static void addSpawn(String s, int i, int j, int k, EnumCreatureType enumcreaturetype) {
        addSpawn((String)s, i, j, k, enumcreaturetype, (BiomeGenBase[])null);
    }

    public static void addSpawn(String s, int i, int j, int k, EnumCreatureType enumcreaturetype, BiomeGenBase[] abiomegenbase) {
        Class class1 = (Class)classMap.get(s);
        if (class1 != null && EntityLiving.class.isAssignableFrom(class1)) {
            addSpawn(class1, i, j, k, enumcreaturetype, abiomegenbase);
        }

    }

    public static void genericContainerRemoval(World world, int i, int j, int k) {
        IInventory iinventory = (IInventory)world.getBlockTileEntity(i, j, k);
        if (iinventory != null) {
            for(int l = 0; l < iinventory.getSizeInventory(); ++l) {
                ItemStack itemstack = iinventory.getStackInSlot(l);
                if (itemstack != null) {
                    double d = world.rand.nextDouble() * 0.8 + 0.1;
                    double d1 = world.rand.nextDouble() * 0.8 + 0.1;

                    EntityItem entityitem;
                    for(double d2 = world.rand.nextDouble() * 0.8 + 0.1; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
                        int i1 = world.rand.nextInt(21) + 10;
                        if (i1 > itemstack.stackSize) {
                            i1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= i1;
                        entityitem = new EntityItem(world, (double)i + d, (double)j + d1, (double)k + d2, new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
                        double d3 = 0.05;
                        entityitem.motionX = world.rand.nextGaussian() * d3;
                        entityitem.motionY = world.rand.nextGaussian() * d3 + 0.2;
                        entityitem.motionZ = world.rand.nextGaussian() * d3;
                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }

                    iinventory.setInventorySlotContents(l, (ItemStack)null);
                }
            }
        }

    }

    public static List getLoadedMods() {
        return Collections.unmodifiableList(modList);
    }

    public static Logger getLogger() {
        return logger;
    }

    public static Minecraft getMinecraftInstance() {
        if (instance == null) {
            try {
                ThreadGroup threadgroup = Thread.currentThread().getThreadGroup();
                int i = threadgroup.activeCount();
                Thread[] athread = new Thread[i];
                threadgroup.enumerate(athread);

                for(int j = 0; j < athread.length; ++j) {
                    System.out.println(athread[j].getName());
                }

                for(int k = 0; k < athread.length; ++k) {
                    if (athread[k].getName().equals("Minecraft main thread")) {
                        instance = (Minecraft)getPrivateValue(Thread.class, athread[k], "target");
                        break;
                    }
                }
            } catch (SecurityException securityexception) {
                logger.throwing("ModLoader", "getMinecraftInstance", securityexception);
                throw new RuntimeException(securityexception);
            } catch (NoSuchFieldException nosuchfieldexception) {
                logger.throwing("ModLoader", "getMinecraftInstance", nosuchfieldexception);
                throw new RuntimeException(nosuchfieldexception);
            }
        }

        return instance;
    }

    public static Object getPrivateValue(Class class1, Object obj, int i) throws IllegalArgumentException, SecurityException, NoSuchFieldException {
        try {
            Field field = class1.getDeclaredFields()[i];
            field.setAccessible(true);
            return field.get(obj);
        } catch (IllegalAccessException illegalaccessexception) {
            logger.throwing("ModLoader", "getPrivateValue", illegalaccessexception);
            throwException("An impossible error has occured!", illegalaccessexception);
            return null;
        }
    }

    public static Object getPrivateValue(Class class1, Object obj, String s) throws IllegalArgumentException, SecurityException, NoSuchFieldException {
        try {
            Field field = class1.getDeclaredField(s);
            field.setAccessible(true);
            return field.get(obj);
        } catch (IllegalAccessException illegalaccessexception) {
            logger.throwing("ModLoader", "getPrivateValue", illegalaccessexception);
            throwException("An impossible error has occured!", illegalaccessexception);
            return null;
        }
    }

    public static int getUniqueBlockModelID(BaseMod basemod, boolean flag) {
        int i = nextBlockModelID++;
        blockModels.put(i, basemod);
        blockSpecialInv.put(i, flag);
        return i;
    }

    private static void init() {
        hasInit = true;

        try {
            instance = Minecraft.getMinecraft();
            instance.entityRenderer = new EntityRendererProxy(instance);
            classMap = (Map)getPrivateValue(EntityList.class, (Object)null, "b");
            soundPoolSounds = (SoundPool)getPrivateValue(SoundManager.class, instance.sndManager, 1);
            soundPoolStreaming = (SoundPool)getPrivateValue(SoundManager.class, instance.sndManager, 2);
            soundPoolMusic = (SoundPool)getPrivateValue(SoundManager.class, instance.sndManager, 3);
            field_modifiers = Field.class.getDeclaredField("modifiers");
            field_modifiers.setAccessible(true);
            field_TileEntityRenderers = TileEntityRenderer.class.getDeclaredFields()[0];
            field_TileEntityRenderers.setAccessible(true);
            field_armorList = RenderPlayer.class.getDeclaredFields()[3];
            field_modifiers.setInt(field_armorList, field_armorList.getModifiers() & -17);
            field_armorList.setAccessible(true);
            Field[] afield = BiomeGenBase.class.getDeclaredFields();
            LinkedList linkedlist = new LinkedList();

            for(int j = 0; j < afield.length; ++j) {
                Class class1 = afield[j].getType();
                if ((afield[j].getModifiers() & 8) != 0 && class1.isAssignableFrom(BiomeGenBase.class)) {
                    BiomeGenBase biomegenbase = (BiomeGenBase)afield[j].get((Object)null);
                    if (!(biomegenbase instanceof BiomeGenHell) && !(biomegenbase instanceof BiomeGenEnd)) {
                        linkedlist.add(biomegenbase);
                    }
                }
            }

            standardBiomes = (BiomeGenBase[])linkedlist.toArray(new BiomeGenBase[0]);

            try {
                method_RegisterTileEntity = TileEntity.class.getDeclaredMethod("a", Class.class, String.class);
            } catch (NoSuchMethodException var6) {
                method_RegisterTileEntity = TileEntity.class.getDeclaredMethod("addMapping", Class.class, String.class);
            }

            method_RegisterTileEntity.setAccessible(true);

            try {
                method_RegisterEntityID = EntityList.class.getDeclaredMethod("a", Class.class, String.class, Integer.TYPE);
            } catch (NoSuchMethodException var5) {
                method_RegisterEntityID = EntityList.class.getDeclaredMethod("addMapping", Class.class, String.class, Integer.TYPE);
            }

            method_RegisterEntityID.setAccessible(true);
        } catch (SecurityException securityexception) {
            logger.throwing("ModLoader", "init", securityexception);
            throwException(securityexception);
            throw new RuntimeException(securityexception);
        } catch (NoSuchFieldException nosuchfieldexception) {
            logger.throwing("ModLoader", "init", nosuchfieldexception);
            throwException(nosuchfieldexception);
            throw new RuntimeException(nosuchfieldexception);
        } catch (NoSuchMethodException nosuchmethodexception) {
            logger.throwing("ModLoader", "init", nosuchmethodexception);
            throwException(nosuchmethodexception);
            throw new RuntimeException(nosuchmethodexception);
        } catch (IllegalArgumentException illegalargumentexception) {
            logger.throwing("ModLoader", "init", illegalargumentexception);
            throwException(illegalargumentexception);
            throw new RuntimeException(illegalargumentexception);
        } catch (IllegalAccessException illegalaccessexception) {
            logger.throwing("ModLoader", "init", illegalaccessexception);
            throwException(illegalaccessexception);
            throw new RuntimeException(illegalaccessexception);
        }

        try {
            loadConfig();
            if (props.containsKey("loggingLevel")) {
                cfgLoggingLevel = Level.parse(props.getProperty("loggingLevel"));
            }

            if (props.containsKey("grassFix")) {
                RenderBlocks.cfgGrassFix = Boolean.parseBoolean(props.getProperty("grassFix"));
            }

            logger.setLevel(cfgLoggingLevel);
            if ((logfile.exists() || logfile.createNewFile()) && logfile.canWrite() && logHandler == null) {
                logHandler = new FileHandler(logfile.getPath());
                logHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(logHandler);
            }

            logger.fine("ModLoader 1.5.2 Initializing...");
            System.out.println("ModLoader 1.5.2 Initializing...");
            File file = Minecraft.getMinecraftDir().toPath().resolve("mods").toFile();
                    modDir.mkdirs();
            readFromClassPath(file);
            readFromModFolder(modDir);
            sortModList();

            for(BaseMod basemod : modList) {
                basemod.load();
                logger.fine("Mod Loaded: \"" + basemod.toString() + "\"");
                System.out.println("Mod Loaded: " + basemod.toString());
                if (!props.containsKey(basemod.getClass().getSimpleName())) {
                    props.setProperty(basemod.getClass().getSimpleName(), "on");
                }
            }

            for(BaseMod basemod1 : modList) {
                basemod1.modsLoaded();
            }

            System.out.println("Done.");
            props.setProperty("loggingLevel", cfgLoggingLevel.getName());
            props.setProperty("grassFix", Boolean.toString(RenderBlocks.cfgGrassFix));
            instance.gameSettings.keyBindings = registerAllKeys(instance.gameSettings.keyBindings);
            instance.gameSettings.loadOptions();
            initStats();
            saveConfig();
        } catch (Throwable throwable) {
            logger.throwing("ModLoader", "init", throwable);
            throwException("ModLoader has failed to initialize.", throwable);
            if (logHandler != null) {
                logHandler.close();
            }

            throw new RuntimeException(throwable);
        }
    }

    private static void initStats() {
        for(int i = 0; i < Block.blocksList.length; ++i) {
            if (StatList.getOneShotStat(16777216 + i) == null && Block.blocksList[i] != null && Block.blocksList[i].getEnableStats()) {
                String s = StatCollector.translateToLocalFormatted("stat.mineBlock", new Object[]{Block.blocksList[i].getLocalizedName()});
                StatList.mineBlockStatArray[i] = (new StatCrafting(16777216 + i, s, i)).registerStat();
                StatList.objectMineStats.add(StatList.mineBlockStatArray[i]);
            }
        }

        for(int j = 0; j < Item.itemsList.length; ++j) {
            if (StatList.getOneShotStat(16908288 + j) == null && Item.itemsList[j] != null) {
                String s1 = StatCollector.translateToLocalFormatted("stat.useItem", new Object[]{Item.itemsList[j].getStatName()});
                StatList.objectUseStats[j] = (new StatCrafting(16908288 + j, s1, j)).registerStat();
                if (j >= Block.blocksList.length) {
                    StatList.itemStats.add(StatList.objectUseStats[j]);
                }
            }

            if (StatList.getOneShotStat(16973824 + j) == null && Item.itemsList[j] != null && Item.itemsList[j].isDamageable()) {
                String s2 = StatCollector.translateToLocalFormatted("stat.breakItem", new Object[]{Item.itemsList[j].getStatName()});
                StatList.objectBreakStats[j] = (new StatCrafting(16973824 + j, s2, j)).registerStat();
            }
        }

        HashSet hashset = new HashSet();

        for(Object var2Obj : CraftingManager.getInstance().getRecipeList()) {
            IRecipe var2 = (IRecipe)var2Obj;
            if (var2.getRecipeOutput() != null) {
                hashset.add(Integer.valueOf(var2.getRecipeOutput().itemID));
            }
        }

        for(Object var4Obj : FurnaceRecipes.smelting().getSmeltingList().values()) {
            ItemStack var4 = (ItemStack)var4Obj;
            hashset.add(Integer.valueOf(var4.itemID));
        }

        for(Object var5 : hashset) {
            int k = ((Integer)var5).intValue();
            if (StatList.getOneShotStat(16842752 + k) == null && Item.itemsList[k] != null) {
                String s3 = StatCollector.translateToLocalFormatted("stat.craftItem", new Object[]{Item.itemsList[k].getStatName()});
                StatList.objectCraftStats[k] = (new StatCrafting(16842752 + k, s3, k)).registerStat();
            }
        }

    }

    public static boolean isGUIOpen(Class class1) {
        Minecraft minecraft = getMinecraftInstance();
        if (class1 == null) {
            return minecraft.currentScreen == null;
        } else {
            return minecraft.currentScreen == null && class1 != null ? false : class1.isInstance(minecraft.currentScreen);
        }
    }

    public static boolean isModLoaded(String s) {
        for(BaseMod basemod : modList) {
            if (s.contentEquals(basemod.getName())) {
                return true;
            }
        }

        return false;
    }

    public static void loadConfig() throws IOException {
        cfgdir.mkdir();
        if (cfgfile.exists() || cfgfile.createNewFile()) {
            if (cfgfile.canRead()) {
                FileInputStream fileinputstream = new FileInputStream(cfgfile);
                props.load(fileinputstream);
                fileinputstream.close();
            }

        }
    }

    public static BufferedImage loadImage(RenderEngine renderengine, String s) throws Exception {
        TexturePackList texturepacklist = (TexturePackList)getPrivateValue(RenderEngine.class, renderengine, "texturePack");
        InputStream inputstream = texturepacklist.getSelectedTexturePack().getResourceAsStream(s);
        if (inputstream == null) {
            throw new Exception("Image not found: " + s);
        } else {
            BufferedImage bufferedimage = ImageIO.read(inputstream);
            if (bufferedimage == null) {
                throw new Exception("Image corrupted: " + s);
            } else {
                return bufferedimage;
            }
        }
    }

    public static void onItemPickup(EntityPlayer entityplayer, ItemStack itemstack) {
        for(BaseMod basemod : modList) {
            basemod.onItemPickup(entityplayer, itemstack);
        }

    }

    public static void onTick(float f, Minecraft minecraft) {
        minecraft.mcProfiler.endSection();
        minecraft.mcProfiler.endSection();
        minecraft.mcProfiler.startSection("modtick");
        if (!hasInit) {
            init();
            logger.fine("Initialized");
        }

        if (langPack == null || StringTranslate.getInstance().getCurrentLanguage() != langPack) {
            Properties properties = null;

            try {
                properties = (Properties)getPrivateValue(StringTranslate.class, StringTranslate.getInstance(), 1);
            } catch (SecurityException securityexception) {
                logger.throwing("ModLoader", "AddLocalization", securityexception);
                throwException(securityexception);
            } catch (NoSuchFieldException nosuchfieldexception) {
                logger.throwing("ModLoader", "AddLocalization", nosuchfieldexception);
                throwException(nosuchfieldexception);
            }

            langPack = StringTranslate.getInstance().getCurrentLanguage();
            if (properties != null) {
                if (localizedStrings.containsKey("en_US")) {
                    properties.putAll((Map)localizedStrings.get("en_US"));
                }

                if (!langPack.contentEquals("en_US") && localizedStrings.containsKey(langPack)) {
                    properties.putAll((Map)localizedStrings.get(langPack));
                }
            }
        }

        long l = 0L;
        if (minecraft.thePlayer != null && minecraft.thePlayer.worldObj != null) {
            l = minecraft.thePlayer.worldObj.getWorldTime();
            Iterator iterator = inGameHooks.entrySet().iterator();

            while(iterator.hasNext()) {
                Map.Entry entry1 = (Map.Entry)iterator.next();
                if ((clock != l || !(Boolean)entry1.getValue()) && !((BaseMod)entry1.getKey()).onTickInGame(f, minecraft)) {
                    iterator.remove();
                }
            }
        }

        if (minecraft.currentScreen != null) {
            Iterator iterator1 = inGUIHooks.entrySet().iterator();

            while(iterator1.hasNext()) {
                Map.Entry entry2 = (Map.Entry)iterator1.next();
                if ((clock != l || !(Boolean)entry2.getValue() || minecraft.thePlayer == null || minecraft.thePlayer.worldObj == null) && !((BaseMod)entry2.getKey()).onTickInGUI(f, minecraft, minecraft.currentScreen)) {
                    iterator1.remove();
                }
            }
        }

        if (clock != l) {
            for(Object entryObj : keyList.entrySet()) {
                Map.Entry entry = (Map.Entry)entryObj;
                for(Object entry3Obj : ((Map)entry.getValue()).entrySet()) {
                    Map.Entry entry3 = (Map.Entry)entry3Obj;
                    int i = ((KeyBinding)entry3.getKey()).keyCode;
                    boolean flag;
                    if (i < 0) {
                        i += 100;
                        flag = Mouse.isButtonDown(i);
                    } else {
                        flag = Keyboard.isKeyDown(i);
                    }

                    boolean[] aflag = (boolean[])entry3.getValue();
                    boolean flag1 = aflag[1];
                    aflag[1] = flag;
                    if (flag && (!flag1 || aflag[0])) {
                        ((BaseMod)entry.getKey()).keyboardEvent((KeyBinding)entry3.getKey());
                    }
                }
            }
        }

        clock = l;
        minecraft.mcProfiler.endSection();
        minecraft.mcProfiler.startSection("render");
        minecraft.mcProfiler.startSection("gameRenderer");
    }

    public static void openGUI(EntityPlayer entityplayer, GuiScreen guiscreen) {
        if (!hasInit) {
            init();
            logger.fine("Initialized");
        }

        Minecraft minecraft = getMinecraftInstance();
        if (minecraft.thePlayer == entityplayer) {
            if (guiscreen != null) {
                minecraft.displayGuiScreen(guiscreen);
            }

        }
    }

    public static void populateChunk(IChunkProvider ichunkprovider, int i, int j, World world) {
        if (!hasInit) {
            init();
            logger.fine("Initialized");
        }

        Random random = new Random(world.getSeed());
        long l = random.nextLong() / 2L * 2L + 1L;
        long l1 = random.nextLong() / 2L * 2L + 1L;
        random.setSeed((long)i * l + (long)j * l1 ^ world.getSeed());

        for(BaseMod basemod : modList) {
            if (world.provider.isSurfaceWorld()) {
                basemod.generateSurface(world, random, i << 4, j << 4);
            } else if (world.provider.isHellWorld) {
                basemod.generateNether(world, random, i << 4, j << 4);
            }
        }

    }

    private static void readFromClassPath(File file) throws FileNotFoundException, IOException {
        logger.finer("Adding mods from " + file.getCanonicalPath());
        ClassLoader classloader = ModLoader.class.getClassLoader();
        if (file.isFile() && (file.getName().endsWith(".jar") || file.getName().endsWith(".zip"))) {
            logger.finer("Zip found.");
            URL zipURL = file.toURL();
            FileInputStream fileinputstream = new FileInputStream(file);
            ZipInputStream zipinputstream = new ZipInputStream(fileinputstream);
            Object obj = null;

            while(true) {
                ZipEntry zipentry = zipinputstream.getNextEntry();
                if (zipentry == null) {
                    fileinputstream.close();
                    break;
                }

                String path = zipentry.getName();
                if (!zipentry.isDirectory()) {
                    if (path.startsWith("mod_") && path.endsWith(".class")) {
                        addMod(classloader, path);
                    } else if (path.startsWith("resources/streaming/")) {
                        String name = path.substring(20);
                        soundPoolStreaming.addSound(name, new URL(String.format("jar:%s!/%s", zipURL, path)));
                    } else if (path.startsWith("resources/music/")) {
                        String name = path.substring(16);
                        soundPoolMusic.addSound(name, new URL(String.format("jar:%s!/%s", zipURL, path)));
                    } else if (path.startsWith("resources/sound/")) {
                        String name = path.substring(16);
                        soundPoolSounds.addSound(name, new URL(String.format("jar:%s!/%s", zipURL, path)));
                    }
                }
            }
        } else if (file.isDirectory()) {
            Package package1 = ModLoader.class.getPackage();
            if (package1 != null) {
                String s = package1.getName().replace('.', File.separatorChar);
                file = new File(file, s);
            }

            logger.finer("Directory found.");
            File[] afile = file.listFiles();
            if (afile != null) {
                for(int i = 0; i < afile.length; ++i) {
                    String s2 = afile[i].getName();
                    if (afile[i].isFile() && s2.startsWith("mod_") && s2.endsWith(".class")) {
                        addMod(classloader, s2);
                    }
                }
            }
        }

    }

    private static void readFromModFolder(File file) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
        ClassLoader classloader = Minecraft.class.getClassLoader();
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        method.setAccessible(true);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("folder must be a Directory.");
        } else {
            File[] afile = file.listFiles();
            Arrays.sort(afile);
            if (classloader instanceof URLClassLoader) {
                for(int i = 0; i < afile.length; ++i) {
                    File file1 = afile[i];
                    if (file1.isDirectory() || file1.isFile() && (file1.getName().endsWith(".jar") || file1.getName().endsWith(".zip"))) {
                        method.invoke(classloader, file1.toURL());
                    }
                }
            }

            for(int j = 0; j < afile.length; ++j) {
                File file2 = afile[j];
                if (file2.isDirectory() || file2.isFile() && (file2.getName().endsWith(".jar") || file2.getName().endsWith(".zip"))) {
                    logger.finer("Adding mods from " + file2.getCanonicalPath());
                    if (!file2.isFile()) {
                        if (file2.isDirectory()) {
                            Package package1 = ModLoader.class.getPackage();
                            if (package1 != null) {
                                String s = package1.getName().replace('.', File.separatorChar);
                                file2 = new File(file2, s);
                            }

                            logger.finer("Directory found.");
                            File[] afile1 = file2.listFiles();
                            if (afile1 != null) {
                                for(int k = 0; k < afile1.length; ++k) {
                                    String s2 = afile1[k].getName();
                                    if (afile1[k].isFile() && s2.startsWith("mod_") && s2.endsWith(".class")) {
                                        addMod(classloader, s2);
                                    }
                                }
                            }
                        }
                    } else {
                        logger.finer("Zip found.");
                        URL zipURL = file2.toURL();
                        FileInputStream fileinputstream = new FileInputStream(file2);
                        ZipInputStream zipinputstream = new ZipInputStream(fileinputstream);
                        Object obj = null;

                        while(true) {
                            ZipEntry zipentry = zipinputstream.getNextEntry();
                            if (zipentry == null) {
                                zipinputstream.close();
                                fileinputstream.close();
                                break;
                            }

                            String path = zipentry.getName();
                            if (!zipentry.isDirectory()) {
                                if (path.startsWith("mod_") && path.endsWith(".class")) {
                                    addMod(classloader, path);
                                } else if (path.startsWith("resources/streaming/")) {
                                    String name = path.substring(20);
                                    soundPoolStreaming.addSound(name, new URL(String.format("jar:%s!/%s", zipURL, path)));
                                } else if (path.startsWith("resources/music/")) {
                                    String name = path.substring(16);
                                    soundPoolMusic.addSound(name, new URL(String.format("jar:%s!/%s", zipURL, path)));
                                } else if (path.startsWith("resources/sound/")) {
                                    String name = path.substring(16);
                                    soundPoolSounds.addSound(name, new URL(String.format("jar:%s!/%s", zipURL, path)));
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    public static void clientCustomPayload(Packet250CustomPayload packet) {
        if (packet.channel.equals("ML|OpenTE")) {
            try {
                DataInputStream stream = new DataInputStream(new ByteArrayInputStream(packet.data));
                int guiID = stream.read();
                int contID = stream.readInt();
                int x = stream.readInt();
                int y = stream.readInt();
                int z = stream.readInt();
                int dim = (byte)stream.read();
                EntityClientPlayerMP player = instance.thePlayer;
                if (player.dimension != dim) {
                    return;
                }

                if (containerGUIs.containsKey(contID)) {
                    BaseMod basemod = (BaseMod)containerGUIs.get(contID);
                    if (basemod != null) {
                        GuiContainer gui = basemod.getContainerGUI(player, contID, x, y, z);
                        if (gui == null) {
                            return;
                        }

                        instance.displayGuiScreen(gui);
                        player.openContainer.windowId = guiID;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (packetChannels.containsKey(packet.channel)) {
            BaseMod basemod = (BaseMod)packetChannels.get(packet.channel);
            if (basemod != null) {
                basemod.clientCustomPayload(clientHandler, packet);
            }
        }

    }

    public static void serverCustomPayload(NetServerHandler serverHandler, Packet250CustomPayload packet250custompayload) {
        if (packetChannels.containsKey(packet250custompayload.channel)) {
            BaseMod basemod = (BaseMod)packetChannels.get(packet250custompayload.channel);
            if (basemod != null) {
                basemod.serverCustomPayload(serverHandler, packet250custompayload);
            }
        }

    }

    public static void registerContainerID(BaseMod mod, int id) {
        containerGUIs.put(id, mod);
    }

    public static void clientOpenWindow(Packet100OpenWindow par1Packet100OpenWindow) {
    }

    public static void serverOpenWindow(EntityPlayerMP player, Container container, int id, int x, int y, int z) {
        try {
            Field winIDField = EntityPlayerMP.class.getDeclaredFields()[16];
            winIDField.setAccessible(true);
            int winID = winIDField.getInt(player);
            winID = winID % 100 + 1;
            winIDField.setInt(player, winID);
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            DataOutputStream stream = new DataOutputStream(bytestream);
            stream.write(winID);
            stream.writeInt(id);
            stream.writeInt(x);
            stream.writeInt(y);
            stream.writeInt(z);
            stream.write(player.dimension);
            player.playerNetServerHandler.sendPacketToPlayer(new Packet250CustomPayload("ML|OpenTE", bytestream.toByteArray()));
            player.openContainer = container;
            player.openContainer.windowId = winID;
            player.openContainer.addCraftingToCrafters(player);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static KeyBinding[] registerAllKeys(KeyBinding[] akeybinding) {
        LinkedList linkedlist = new LinkedList();
        linkedlist.addAll(Arrays.asList(akeybinding));

        for(Object mapObj : keyList.values()) {
            Map map = (Map)mapObj;
            linkedlist.addAll(map.keySet());
        }

        return (KeyBinding[])linkedlist.toArray(new KeyBinding[0]);
    }

    public static void registerBlock(Block block) {
        registerBlock(block, (Class)null);
    }

    public static void registerBlock(Block block, Class class1) {
        try {
            if (block == null) {
                throw new IllegalArgumentException("block parameter cannot be null.");
            }

            int i = block.blockID;
            ItemBlock itemblock = null;
            if (class1 != null) {
                itemblock = (ItemBlock)class1.getConstructor(Integer.TYPE).newInstance(i - 256);
            } else {
                itemblock = new ItemBlock(i - 256);
            }

            if (Block.blocksList[i] != null && Item.itemsList[i] == null) {
                Item.itemsList[i] = itemblock;
            }
        } catch (IllegalArgumentException illegalargumentexception) {
            logger.throwing("ModLoader", "RegisterBlock", illegalargumentexception);
            throwException(illegalargumentexception);
        } catch (IllegalAccessException illegalaccessexception) {
            logger.throwing("ModLoader", "RegisterBlock", illegalaccessexception);
            throwException(illegalaccessexception);
        } catch (SecurityException securityexception) {
            logger.throwing("ModLoader", "RegisterBlock", securityexception);
            throwException(securityexception);
        } catch (InstantiationException instantiationexception) {
            logger.throwing("ModLoader", "RegisterBlock", instantiationexception);
            throwException(instantiationexception);
        } catch (InvocationTargetException invocationtargetexception) {
            logger.throwing("ModLoader", "RegisterBlock", invocationtargetexception);
            throwException(invocationtargetexception);
        } catch (NoSuchMethodException nosuchmethodexception) {
            logger.throwing("ModLoader", "RegisterBlock", nosuchmethodexception);
            throwException(nosuchmethodexception);
        }

    }

    public static void registerEntityID(Class class1, String s, int i) {
        try {
            method_RegisterEntityID.invoke((Object)null, class1, s, i);
        } catch (IllegalArgumentException illegalargumentexception) {
            logger.throwing("ModLoader", "RegisterEntityID", illegalargumentexception);
            throwException(illegalargumentexception);
        } catch (IllegalAccessException illegalaccessexception) {
            logger.throwing("ModLoader", "RegisterEntityID", illegalaccessexception);
            throwException(illegalaccessexception);
        } catch (InvocationTargetException invocationtargetexception) {
            logger.throwing("ModLoader", "RegisterEntityID", invocationtargetexception);
            throwException(invocationtargetexception);
        }

    }

    public static void registerEntityID(Class class1, String s, int i, int j, int k) {
        registerEntityID(class1, s, i);
        EntityList.entityEggs.put(Integer.valueOf(i), new EntityEggInfo(i, j, k));
    }

    public static void registerKey(BaseMod basemod, KeyBinding keybinding, boolean flag) {
        Map obj = (Map)keyList.get(basemod);
        if (obj == null) {
            obj = new HashMap();
        }

        boolean[] aflag = new boolean[2];
        aflag[0] = flag;
        obj.put(keybinding, aflag);
        keyList.put(basemod, obj);
    }

    public static void registerPacketChannel(BaseMod basemod, String s) {
        if (s.length() < 16) {
            packetChannels.put(s, basemod);
        } else {
            throw new RuntimeException(String.format("Invalid channel name: %s. Must be less than 16 characters.", s));
        }
    }

    public static void registerTileEntity(Class class1, String s) {
        registerTileEntity(class1, s, (TileEntitySpecialRenderer)null);
    }

    public static void registerTileEntity(Class class1, String s, TileEntitySpecialRenderer tileentityspecialrenderer) {
        try {
            method_RegisterTileEntity.invoke((Object)null, class1, s);
            if (tileentityspecialrenderer != null) {
                TileEntityRenderer tileentityrenderer = TileEntityRenderer.instance;
                Map map = (Map)field_TileEntityRenderers.get(tileentityrenderer);
                map.put(class1, tileentityspecialrenderer);
                tileentityspecialrenderer.setTileEntityRenderer(tileentityrenderer);
            }
        } catch (IllegalArgumentException illegalargumentexception) {
            logger.throwing("ModLoader", "RegisterTileEntity", illegalargumentexception);
            throwException(illegalargumentexception);
        } catch (IllegalAccessException illegalaccessexception) {
            logger.throwing("ModLoader", "RegisterTileEntity", illegalaccessexception);
            throwException(illegalaccessexception);
        } catch (InvocationTargetException invocationtargetexception) {
            logger.throwing("ModLoader", "RegisterTileEntity", invocationtargetexception);
            throwException(invocationtargetexception);
        }

    }

    public static void removeBiome(BiomeGenBase biomegenbase) {
        BiomeGenBase[] abiomegenbase = GenLayerBiome.biomeArray;
        List list = Arrays.asList(abiomegenbase);
        ArrayList arraylist = new ArrayList();
        arraylist.addAll(list);
        if (arraylist.contains(biomegenbase)) {
            arraylist.remove(biomegenbase);
        }

        GenLayerBiome.biomeArray = (BiomeGenBase[])arraylist.toArray(new BiomeGenBase[0]);
    }

    public static void removeSpawn(Class class1, EnumCreatureType enumcreaturetype) {
        removeSpawn((Class)class1, enumcreaturetype, (BiomeGenBase[])null);
    }

    public static void removeSpawn(Class class1, EnumCreatureType enumcreaturetype, BiomeGenBase[] abiomegenbase) {
        if (class1 == null) {
            throw new IllegalArgumentException("entityClass cannot be null");
        } else if (enumcreaturetype == null) {
            throw new IllegalArgumentException("spawnList cannot be null");
        } else {
            if (abiomegenbase == null) {
                abiomegenbase = standardBiomes;
            }

            for(int i = 0; i < abiomegenbase.length; ++i) {
                List list = abiomegenbase[i].getSpawnableList(enumcreaturetype);
                if (list != null) {
                    Iterator iterator = list.iterator();

                    while(iterator.hasNext()) {
                        SpawnListEntry spawnlistentry = (SpawnListEntry)iterator.next();
                        if (spawnlistentry.entityClass == class1) {
                            iterator.remove();
                        }
                    }
                }
            }

        }
    }

    public static void removeSpawn(String s, EnumCreatureType enumcreaturetype) {
        removeSpawn((String)s, enumcreaturetype, (BiomeGenBase[])null);
    }

    public static void removeSpawn(String s, EnumCreatureType enumcreaturetype, BiomeGenBase[] abiomegenbase) {
        Class class1 = (Class)classMap.get(s);
        if (class1 != null && EntityLiving.class.isAssignableFrom(class1)) {
            removeSpawn(class1, enumcreaturetype, abiomegenbase);
        }

    }

    public static boolean renderBlockIsItemFull3D(int i) {
        if (!blockSpecialInv.containsKey(i)) {
            return i == 35;
        } else {
            return (Boolean)blockSpecialInv.get(i);
        }
    }

    public static void renderInvBlock(RenderBlocks renderblocks, Block block, int i, int j) {
        BaseMod basemod = (BaseMod)blockModels.get(j);
        if (basemod != null) {
            basemod.renderInvBlock(renderblocks, block, i, j);
        }
    }

    public static boolean renderWorldBlock(RenderBlocks renderblocks, IBlockAccess iblockaccess, int i, int j, int k, Block block, int l) {
        BaseMod basemod = (BaseMod)blockModels.get(l);
        return basemod == null ? false : basemod.renderWorldBlock(renderblocks, iblockaccess, i, j, k, block, l);
    }

    public static void saveConfig() throws IOException {
        cfgdir.mkdir();
        if (cfgfile.exists() || cfgfile.createNewFile()) {
            if (cfgfile.canWrite()) {
                FileOutputStream fileoutputstream = new FileOutputStream(cfgfile);
                props.store(fileoutputstream, "ModLoader Config");
                fileoutputstream.close();
            }

        }
    }

    public static void clientChat(String s) {
        for(BaseMod mod : modList) {
            mod.clientChat(s);
        }

    }

    public static void serverChat(NetServerHandler netserverhandler, String s) {
        for(BaseMod mod : modList) {
            mod.serverChat(netserverhandler, s);
        }

    }

    public static void clientConnect(NetClientHandler netclienthandler, Packet1Login packet1login) {
        clientHandler = netclienthandler;
        if (packetChannels.size() > 0) {
            Packet250CustomPayload packet250custompayload = new Packet250CustomPayload();
            packet250custompayload.channel = "REGISTER";
            StringBuilder stringbuilder = new StringBuilder();
            Iterator iterator1 = packetChannels.keySet().iterator();
            stringbuilder.append((String)iterator1.next());

            while(iterator1.hasNext()) {
                stringbuilder.append("\u0000");
                stringbuilder.append((String)iterator1.next());
            }

            packet250custompayload.data = stringbuilder.toString().getBytes(Charset.forName("UTF8"));
            packet250custompayload.length = packet250custompayload.data.length;
            clientSendPacket(packet250custompayload);
        }

        for(BaseMod mod : modList) {
            mod.clientConnect(netclienthandler);
        }

    }

    public static void clientDisconnect() {
        for(BaseMod mod : modList) {
            mod.clientDisconnect(clientHandler);
        }

        clientHandler = null;
    }

    public static void clientSendPacket(Packet packet) {
        if (clientHandler != null) {
            clientHandler.addToSendQueue(packet);
        }

    }

    public static void serverSendPacket(NetServerHandler serverHandler, Packet packet) {
        if (serverHandler != null) {
            serverHandler.sendPacketToPlayer(packet);
        }

    }

    public static void setInGameHook(BaseMod basemod, boolean flag, boolean flag1) {
        if (flag) {
            inGameHooks.put(basemod, flag1);
        } else {
            inGameHooks.remove(basemod);
        }

    }

    public static void setInGUIHook(BaseMod basemod, boolean flag, boolean flag1) {
        if (flag) {
            inGUIHooks.put(basemod, flag1);
        } else {
            inGUIHooks.remove(basemod);
        }

    }

    public static void setPrivateValue(Class class1, Object obj, int i, Object obj1) throws IllegalArgumentException, SecurityException, NoSuchFieldException {
        try {
            Field field = class1.getDeclaredFields()[i];
            field.setAccessible(true);
            int j = field_modifiers.getInt(field);
            if ((j & 16) != 0) {
                field_modifiers.setInt(field, j & -17);
            }

            field.set(obj, obj1);
        } catch (IllegalAccessException illegalaccessexception) {
            logger.throwing("ModLoader", "setPrivateValue", illegalaccessexception);
            throwException("An impossible error has occured!", illegalaccessexception);
        }

    }

    public static void setPrivateValue(Class class1, Object obj, String s, Object obj1) throws IllegalArgumentException, SecurityException, NoSuchFieldException {
        try {
            Field field = class1.getDeclaredField(s);
            int i = field_modifiers.getInt(field);
            if ((i & 16) != 0) {
                field_modifiers.setInt(field, i & -17);
            }

            field.setAccessible(true);
            field.set(obj, obj1);
        } catch (IllegalAccessException illegalaccessexception) {
            logger.throwing("ModLoader", "setPrivateValue", illegalaccessexception);
            throwException("An impossible error has occured!", illegalaccessexception);
        }

    }

    private static void setItemWeight(SpawnListEntry spawnlistentry, int i) {
        try {
            Field field = WeightedRandomItem.class.getDeclaredField("itemWeight");
            field.setAccessible(true);
            int j = field_modifiers.getInt(field);
            if ((j & 16) != 0) {
                field_modifiers.setInt(field, j & -17);
            }

            field.setInt(spawnlistentry, i);
        } catch (Exception exception) {
            logger.throwing("ModLoader", "setItemWeight", exception);
            throwException(exception);
        }

    }

    private static void setupProperties(Class class1) throws IllegalArgumentException, IllegalAccessException, IOException, SecurityException, NoSuchFieldException, NoSuchAlgorithmException, DigestException {
        LinkedList<Field> linkedlist = new LinkedList<Field>();
        Properties properties = new Properties();
        int i = 0;
        int j = 0;
        File file = new File(cfgdir, class1.getSimpleName() + ".cfg");
        if (file.exists() && file.canRead()) {
            properties.load(new FileInputStream(file));
        }

        if (properties.containsKey("checksum")) {
            j = Integer.parseInt(properties.getProperty("checksum"), 36);
        }

        Field[] afield;
        for(Field field : afield = class1.getDeclaredFields()) {
            if ((field.getModifiers() & 8) != 0 && field.isAnnotationPresent(MLProp.class)) {
                linkedlist.add(field);
                Object obj = field.get((Object)null);
                i += obj.hashCode();
            }
        }

        StringBuilder stringbuilder = new StringBuilder();

        for(Field field1 : linkedlist) {
            if ((field1.getModifiers() & 8) != 0 && field1.isAnnotationPresent(MLProp.class)) {
                Class class2 = field1.getType();
                MLProp mlprop = (MLProp)field1.getAnnotation(MLProp.class);
                String s = mlprop.name().length() != 0 ? mlprop.name() : field1.getName();
                Object obj1 = field1.get((Object)null);
                StringBuilder stringbuilder1 = new StringBuilder();
                if (mlprop.min() != Double.NEGATIVE_INFINITY) {
                    stringbuilder1.append(String.format(",>=%.1f", mlprop.min()));
                }

                if (mlprop.max() != Double.POSITIVE_INFINITY) {
                    stringbuilder1.append(String.format(",<=%.1f", mlprop.max()));
                }

                StringBuilder stringbuilder2 = new StringBuilder();
                if (mlprop.info().length() > 0) {
                    stringbuilder2.append(" -- ");
                    stringbuilder2.append(mlprop.info());
                }

                stringbuilder.append(String.format("%s (%s:%s%s)%s\n", s, class2.getName(), obj1, stringbuilder1, stringbuilder2));
                if (j == i && properties.containsKey(s)) {
                    String s1 = properties.getProperty(s);
                    Object obj2 = null;
                    if (class2.isAssignableFrom(String.class)) {
                        obj2 = s1;
                    } else if (class2.isAssignableFrom(Integer.TYPE)) {
                        obj2 = Integer.parseInt(s1);
                    } else if (class2.isAssignableFrom(Short.TYPE)) {
                        obj2 = Short.parseShort(s1);
                    } else if (class2.isAssignableFrom(Byte.TYPE)) {
                        obj2 = Byte.parseByte(s1);
                    } else if (class2.isAssignableFrom(Boolean.TYPE)) {
                        obj2 = Boolean.parseBoolean(s1);
                    } else if (class2.isAssignableFrom(Float.TYPE)) {
                        obj2 = Float.parseFloat(s1);
                    } else if (class2.isAssignableFrom(Double.TYPE)) {
                        obj2 = Double.parseDouble(s1);
                    }

                    if (obj2 != null) {
                        if (obj2 instanceof Number) {
                            double d = ((Number)obj2).doubleValue();
                            if (mlprop.min() != Double.NEGATIVE_INFINITY && d < mlprop.min() || mlprop.max() != Double.POSITIVE_INFINITY && d > mlprop.max()) {
                                continue;
                            }
                        }

                        logger.finer(s + " set to " + obj2);
                        if (!obj2.equals(obj1)) {
                            field1.set((Object)null, obj2);
                        }
                    }
                } else {
                    logger.finer(s + " not in config, using default: " + obj1);
                    properties.setProperty(s, obj1.toString());
                }
            }
        }

        properties.put("checksum", Integer.toString(i, 36));
        if (!properties.isEmpty() && (file.exists() || file.createNewFile()) && file.canWrite()) {
            properties.store(new FileOutputStream(file), stringbuilder.toString());
        }

    }

    private static void sortModList() throws Exception {
        HashMap hashmap = new HashMap();

        for(Object basemodObj : getLoadedMods()) {
            BaseMod basemod = (BaseMod)basemodObj;
            hashmap.put(basemod.getClass().getSimpleName(), basemod);
        }

        LinkedList linkedlist = new LinkedList();

        for(int i = 0; linkedlist.size() != modList.size() && i <= 10; ++i) {
            label129:
            for(BaseMod basemod1 : modList) {
                if (!linkedlist.contains(basemod1)) {
                    String s = basemod1.getPriorities();
                    if (s != null && s.length() != 0 && s.indexOf(58) != -1) {
                        if (i > 0) {
                            int j = -1;
                            int k = Integer.MIN_VALUE;
                            int l = Integer.MAX_VALUE;
                            String[] as;
                            if (s.indexOf(59) > 0) {
                                as = s.split(";");
                            } else {
                                as = new String[]{s};
                            }

                            for(int i1 = 0; i1 < as.length; ++i1) {
                                String s1 = as[i1];
                                if (s1.indexOf(58) != -1) {
                                    String[] as1 = s1.split(":");
                                    String s2 = as1[0];
                                    String s3 = as1[1];
                                    if (s2.contentEquals("required-before") || s2.contentEquals("before") || s2.contentEquals("after") || s2.contentEquals("required-after")) {
                                        if (s3.contentEquals("*")) {
                                            if (!s2.contentEquals("required-before") && !s2.contentEquals("before")) {
                                                if (s2.contentEquals("required-after") || s2.contentEquals("after")) {
                                                    j = linkedlist.size();
                                                }
                                                break;
                                            }

                                            j = 0;
                                            break;
                                        }

                                        if ((s2.contentEquals("required-before") || s2.contentEquals("required-after")) && !hashmap.containsKey(s3)) {
                                            throw new Exception(String.format("%s is missing dependency: %s", basemod1, s3));
                                        }

                                        BaseMod basemod2 = (BaseMod)hashmap.get(s3);
                                        if (!linkedlist.contains(basemod2)) {
                                            continue label129;
                                        }

                                        int j1 = linkedlist.indexOf(basemod2);
                                        if (!s2.contentEquals("required-before") && !s2.contentEquals("before")) {
                                            if (s2.contentEquals("required-after") || s2.contentEquals("after")) {
                                                j = j1 + 1;
                                                if (j > k) {
                                                    k = j;
                                                } else {
                                                    j = k;
                                                }
                                            }
                                        } else {
                                            j = j1;
                                            if (j1 < l) {
                                                l = j1;
                                            } else {
                                                j = l;
                                            }
                                        }
                                    }
                                }
                            }

                            if (j != -1) {
                                linkedlist.add(j, basemod1);
                            }
                        }
                    } else {
                        linkedlist.add(basemod1);
                    }
                }
            }
        }

        modList.clear();
        modList.addAll(linkedlist);
    }

    public static void takenFromCrafting(EntityPlayer entityplayer, ItemStack itemstack, IInventory iinventory) {
        for(BaseMod basemod : modList) {
            basemod.takenFromCrafting(entityplayer, itemstack, iinventory);
        }

    }

    public static void takenFromFurnace(EntityPlayer entityplayer, ItemStack itemstack) {
        for(BaseMod basemod : modList) {
            basemod.takenFromFurnace(entityplayer, itemstack);
        }

    }

    public static void throwException(String s, Throwable throwable) {
        Minecraft minecraft = getMinecraftInstance();
        if (minecraft != null) {
            //minecraft.displayCrashReport(new CrashReport(s, throwable));
        } else {
            //throw new RuntimeException(throwable);
        }
    }

    private static void throwException(Throwable throwable) {
        throwException("Exception occured in ModLoader", throwable);
    }

    public static String getCrashReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mods loaded: ");
        sb.append(getLoadedMods().size() + 1);
        sb.append('\n');
        sb.append("ModLoader 1.5.2");
        sb.append('\n');

        for(Object modObj : getLoadedMods()) {
            BaseMod mod = (BaseMod)modObj;
            sb.append(mod.getName());
            sb.append(' ');
            sb.append(mod.getVersion());
            sb.append('\n');
        }

        return sb.toString();
    }

    public static void addCustomAnimationLogic(String name, TextureStitched tex) {
        customTextures.put(name, tex);
    }

    public static TextureStitched getCustomAnimationLogic(String name) {
        return !customTextures.containsKey(name) ? null : (TextureStitched)customTextures.get(name);
    }

    private ModLoader() {
    }

    static {
        cfgfile = new File(cfgdir, "ModLoader.cfg");
        cfgLoggingLevel = Level.FINER;
    }
}
