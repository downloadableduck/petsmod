package com.jeff.pets.javaagent.asm;

import com.jeff.pets.client.Central;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class PlayerRideVehicleMixin {
    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);
        for (MethodNode method : node.methods) {
            if (method.name.equals("startRiding") && method.desc.equals("(Lnet/minecraft/world/entity/Entity;ZZ)Z")) {
                InsnList list = new InsnList();
                list.add(new VarInsnNode(Opcodes.ALOAD, 0));
                list.add(new VarInsnNode(Opcodes.ALOAD, 1));
                list.add(new MethodInsnNode(
                        Opcodes.INVOKESTATIC,
                        "com/jeff/pets/javaagent/asm/PlayerRideVehicleMixin",
                        "onPlayerStartRiding",
                        "(Ljava/lang/Object;Ljava/lang/Object;)V"
                ));
                method.instructions.insert(list);
            }
        }
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        return writer.toByteArray();
    }

    public static void onPlayerStartRiding(Object obj, Object entityToRide) {
        if (obj instanceof Player && entityToRide instanceof VehicleEntity) {
            Central.onGetInVehicle();
        }
    }
}
