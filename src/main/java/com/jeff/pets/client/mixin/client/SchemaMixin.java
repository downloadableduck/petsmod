package com.jeff.pets.client.mixin.client;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.TaggedChoice;
import net.minecraft.util.datafix.NamespacedSchema;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Method;

@Mixin(NamespacedSchema.class)
public abstract class SchemaMixin extends Schema {

    public SchemaMixin(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    @Inject(at = @At("HEAD"), method = "getChoiceType", cancellable = true)
    private void getChoiceType(DSL.TypeReference type, String choiceName, CallbackInfoReturnable<Type<?>> cir) {
        final TaggedChoice.TaggedChoiceType<?> choiceType = this.findChoiceType(type);
        cir.setReturnValue(choiceType.types().get(choiceName));
    }
}