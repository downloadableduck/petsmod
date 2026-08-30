package me.shedaniel.clothconfig2.impl;

import net.minecraft.client.render.platform.InputConstants;
import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.options.KeyBinding;

import java.util.UUID;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class FakeKeyBindings extends KeyBinding {
    private final UUID uuid;
    private final ModifierKeyCode keyCode;
    private final ModifierKeyCode defaultKeyCode;
    private final Consumer<ModifierKeyCode> onChanged;
    
    public FakeKeyBindings(String key, ModifierKeyCode keyCode, ModifierKeyCode defaultKeyCode, String category, Consumer<ModifierKeyCode> onChanged) {
        super(UUID.randomUUID().toString(), InputConstants.Type.KEYSYM, -1, category);
        uuid = UUID.fromString(getName());
        ((KeyBindingHooks) this).cloth_setId("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ" + key);
        this.keyCode = keyCode;
        this.defaultKeyCode = ModifierKeyCode.copyOf(defaultKeyCode);
        this.onChanged = onChanged;
    }
    
    @Override
    public InputConstants.Key getDefaultKey() {
        return defaultKeyCode.getKeyCode();
    }
    
    @Override
    public void setKey(InputConstants.Key inputUtil$KeyCode_1) {
        keyCode.setKeyCode(inputUtil$KeyCode_1);
        keyCode.setModifier(Modifier.none());
        onChanged.accept(keyCode);
    }
    
    @Override
    public boolean isUnbound() {
        return keyCode.isUnknown();
    }
    
    @Override
    public boolean matches(int int_1, int int_2) {
        return keyCode.matchesKey(int_1, int_2);
    }
    
    @Override
    public boolean matchesMouse(int int_1) {
        return keyCode.matchesMouse(int_1);
    }
    
    @Override
    public String getDisplayName() {
        return keyCode.getLocalizedName();
    }
    
    @Override
    public boolean isDefault() {
        return keyCode.equals(defaultKeyCode);
    }
    
    @Override
    public String saveString() {
        return keyCode.getLocalizedName();
    }
    
    @Override
    public String getName() {
        if (uuid == null)
            return super.getName();
        return super.getName().substring(77);
    }
}
