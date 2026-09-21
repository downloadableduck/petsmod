package com.jeff.pets.client.mixin.client;

import com.jeff.pets.mob.AbstractPet;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemBlock.class)
public class GhostHandMixin {

    @Inject(at = @At(value = "HEAD"), method = "func_179222_a", cancellable = true)
    private void pets$onGetMouseOver(World p_179222_1_, BlockPos pos, EnumFacing p_179222_3_, EntityPlayer p_179222_4_, ItemStack p_179222_5_, CallbackInfoReturnable<Boolean> cir) {
        BlockPos pos2 = pos.offset(p_179222_3_);
        AxisAlignedBB bb = new AxisAlignedBB(pos2.getX(), pos2.getY(), pos2.getZ(), pos2.getX() + 1, pos2.getY() + 1, pos2.getZ() +1);
        List<AbstractPet> entities = p_179222_1_.getEntitiesWithinAABB(AbstractPet.class, bb);
        if (!entities.isEmpty()) {
            cir.setReturnValue(true);
        }
    }
}