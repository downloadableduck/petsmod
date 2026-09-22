package com.jeff.pets.client.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Mixin(EntityLootSubProvider.class)
public class JEIPatcher {
    @Shadow
    @Final
    private FeatureFlagSet required;

    @Shadow
    @Final
    private Map<EntityType<?>, Map<ResourceKey<LootTable>, LootTable.Builder>> map;

    @Inject(at = @At(value = "HEAD"), method = "lambda$run$0", require = 0, cancellable = true)
    private void pets$onCreateRegistryProvider(Set seen, Holder.Reference holder, CallbackInfo ci) {
        EntityType<?> type = (EntityType<?>) holder.value();
        Map<ResourceKey<LootTable>, LootTable.Builder> builders = (Map)this.map.get(type);

        Optional<ResourceKey<LootTable>> defaultLootTable = type.getDefaultLootTable();
        if (type.isEnabled(this.required) && (builders == null || !builders.containsKey(defaultLootTable.get()))) {
            ci.cancel();
        }
    }
}
