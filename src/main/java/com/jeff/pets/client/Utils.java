//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.core.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;
import com.jeff.pets.mob.AbstractPet;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Utils {
    public Utils() {
    }

    public static void summonPet(AbstractPet entity, String entityName) {
        Minecraft minecraft = Minecraft.getMinecraft();
        Player player = minecraft.thePlayer;
        World world = minecraft.currentWorld;
        if (entity != null && world != null && player != null) {
            float yaw = (float)Math.toRadians(player.yRot); float pitch = (float)Math.toRadians(player.xRot);
            Vec3 lookAngle = Vec3.getPermanentVec3(-Math.sin(yaw)*Math.cos(pitch), -Math.sin(pitch), Math.cos(yaw)*Math.cos(pitch));
            double x = player.x - lookAngle.x * 0.5;
            double y = player.y + 0.5;
            double z = player.z - lookAngle.z * 0.5;
            entity.setPosition(x, y, z);
            world.entityJoinedWorld(entity);
            entity.setOwnerName(player.username);
            Central.summonedEntity.add(entity);
        }
    }

    public static void checkName(String activePet, AbstractPet entity, String petName) {
    }

    public static boolean checkTeleport(String activePet, AbstractPet entity) {
        return Objects.equals(Central.CONFIG.activePet, activePet) && entity != null;
    }

    public static String checkNullString(String s) {
        return s == null ? "" : s;
    }

    public static String checkNullString(String s, String defaultSkin) {
        return s == null ? defaultSkin : s;
    }

    public static String withModNamespace(String path) {
        return path;
    }

    public static void despawnEntity(Entity e) {
        if (e != null) {
            e.remove();
        }

    }

    public static void setActivePet(Entity e, String s) {
        Iterator var2 = Central.summonedEntity.iterator();

        while(var2.hasNext()) {
            Entity existing = (Entity)var2.next();
            despawnEntity(existing);
        }

        Central.summonedEntity.clear();
        Central.CONFIG.activePet = s;
    }

    public static Block getBlockFromString(String string) {
        try {
            Field[] fields = Block.class.getDeclaredFields();
            Field[] var2 = fields;
            int var3 = fields.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Field field = var2[var4];
                if (Block.class.isAssignableFrom(field.getType()) && Objects.equals(string, field.getName())) {
                    return (Block)field.get((Object)null);
                }
            }
        } catch (Exception var6) {
            Exception e = var6;
            e.printStackTrace();
        }

        return net.minecraft.core.block.Blocks.BED;
    }

    public static float triangleWave(float p_78172_1_, float p_78172_2_) {
        return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
    }

    public static double squaredDistanceToOrigin(Vec3 vec3d) {
        return vec3d.x * vec3d.x + vec3d.y * vec3d.y + vec3d.z * vec3d.z;
    }

    public static float wrapDegrees(float degrees) {
        degrees %= 360.0F;
        if (degrees >= 180.0F) {
            degrees -= 360.0F;
        }

        if (degrees < -180.0F) {
            degrees += 360.0F;
        }

        return degrees;
    }

    public static List split(String text, int width) {
        return Arrays.asList(insertLineBreaks(text, width).split("\n"));
    }

    public static String insertLineBreaks(String text, int width) {
        int i = indexAtWidth(text, width);
        if (text.length() <= i) {
            return text;
        } else {
            String string4 = text.substring(0, i);
            String string5 = string4 + text.substring(i + (text.charAt(i) == ' ' ? 1 : 0));
            return string4 + "\n" + insertLineBreaks(string5, width);
        }
    }

    private static int indexAtWidth(String text, int width) {
        int i = text.length();
        int j = 0;
        int k = 0;
        int l = -1;

        boolean m = false;
        for(; k < i; ++k) {
            int n = text.charAt(k);
            switch (n) {
                case ' ':
                    l = k;
                default:
                    j += Minecraft.getMinecraft().font.stringWidth(String.valueOf(n));
                    if (m) {
                        ++j;
                    }
                    break;
                case '§':
                    if (k != i) {
                        ++k;
                        int o = text.charAt(k);
                        if (o != 'l' && o != 'L') {
                            if (o == 'r' || o == 'R') {
                                m = false;
                            }
                        } else {
                            m = true;
                        }
                    }
            }

            if (n == '\n') {
                ++k;
                l = k;
                break;
            }

            if (j > width) {
                break;
            }
        }

        return k != i && l != -1 && l < k ? l : k;
    }

    public static boolean isBidirectional() {
        return false;
    }
}
