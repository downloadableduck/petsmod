import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import java.util.Map;
import java.util.Random;

public abstract class BaseMod {
    public int addFuel(int i, int j) {
        return 0;
    }

    public void addRenderer(Map map) {
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public String getPriorities() {
        return "";
    }

    public abstract String getVersion();

    public abstract void load();

    public void modsLoaded() {
    }

    public boolean onTickInGame(float f, Minecraft minecraft) {
        return false;
    }

    public boolean onTickInGUI(float f, Minecraft minecraft, GuiScreen guiscreen) {
        return false;
    }

    public void clientChat(String s) {
    }

    public void clientConnect(NetClientHandler netclienthandler) {
    }

    public void clientDisconnect(NetClientHandler netclienthandler) {
    }

    public void clientCustomPayload(NetClientHandler netclienthandler, Packet250CustomPayload packet) {
    }

    public void serverCustomPayload(NetServerHandler netserverhandler, Packet250CustomPayload packet) {
    }

    public void serverChat(NetServerHandler netserverhandler, String s) {
    }

    public GuiContainer getContainerGUI(EntityClientPlayerMP entityclientplayermp, int i, int j, int k, int l) {
        return null;
    }

    public void renderInvBlock(RenderBlocks renderblocks, Block block, int i, int j) {
    }

    public boolean renderWorldBlock(RenderBlocks renderblocks, IBlockAccess iblockaccess, int i, int j, int k, Block block, int l) {
        return false;
    }

    public void onItemPickup(EntityPlayer entityplayer, ItemStack itemstack) {
    }

    public void keyboardEvent(KeyBinding keybinding) {
    }

    public void takenFromCrafting(EntityPlayer entityplayer, ItemStack itemstack, IInventory iinventory) {
    }

    public void takenFromFurnace(EntityPlayer entityplayer, ItemStack itemstack) {
    }

    public void generateSurface(World world, Random random, int i, int j) {
    }

    public void generateNether(World world, Random random, int i, int j) {
    }

    public String toString() {
        return this.getName() + ' ' + this.getVersion();
    }
}
