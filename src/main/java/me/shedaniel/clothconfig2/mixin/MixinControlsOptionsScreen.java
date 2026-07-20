package me.shedaniel.clothconfig2.mixin;

import me.shedaniel.clothconfig2.api.FakeModifierKeyCodeAdder;
import me.shedaniel.clothconfig2.impl.FakeKeyBindings;
import me.shedaniel.clothconfig2.impl.GameOptionsHooks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.ControlsOptionsScreen;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ControlsOptionsScreen.class)
public class MixinControlsOptionsScreen extends Screen {
    public MixinControlsOptionsScreen(Text text_1) {
        super(text_1);
    }
    
    @Inject(method = "init", at = @At("HEAD"))
    private void initHead(CallbackInfo info) {
        List<KeyBinding> newKeysAll = new ArrayList<>();
        KeyBinding[] var3 = minecraft.options.keyBindings;
        
        for (KeyBinding binding : var3) {
            if (!(binding instanceof FakeKeyBindings)) {
                newKeysAll.add(binding);
            }
        }
        
        newKeysAll.addAll(FakeModifierKeyCodeAdder.INSTANCE.getFakeBindings());
        ((GameOptionsHooks) minecraft.options).cloth_setKeysAll(newKeysAll.toArray(new KeyBinding[0]));
    }
    
    @Inject(method = "init", at = @At("RETURN"))
    private void initReturn(CallbackInfo info) {
        List<KeyBinding> newKeysAll = new ArrayList<>();
        KeyBinding[] var3 = minecraft.options.keyBindings;
        for (KeyBinding binding : var3) {
            if (!(binding instanceof FakeKeyBindings)) {
                newKeysAll.add(binding);
            }
        }
        ((GameOptionsHooks) minecraft.options).cloth_setKeysAll(newKeysAll.toArray(new KeyBinding[0]));
    }
}
