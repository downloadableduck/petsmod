package com.jeff.pets.client.mixin;

import com.jeff.pets.client.autoupdate.impl.CustomClickEvent;
import net.minecraft.network.chat.ClickEvent;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class ScreenMixin {
    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);
        for (MethodNode methodNode : node.methods) {
            if (methodNode.name.equals("defaultHandleClickEvent")) {
                InsnList list = new InsnList();
                LabelNode returnNode = new LabelNode();
                list.add(new VarInsnNode(Opcodes.ALOAD, 0));
                list.add(new MethodInsnNode(
                        Opcodes.INVOKESTATIC,
                        "com/jeff/pets/client/mixin/ScreenMixin",
                        "pets$handleClickEvent",
                        "(Lnet/minecraft/network/chat/ClickEvent;)Z"
                ));
                list.add(new JumpInsnNode(Opcodes.IFEQ, returnNode));
                list.add(new InsnNode(Opcodes.RETURN));
                list.add(returnNode);
                methodNode.instructions.insertBefore(methodNode.instructions.getFirst(), list);
            }
        }
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        node.accept(writer);
        return writer.toByteArray();
    }

    public static boolean pets$handleClickEvent(ClickEvent event) {
        if (event instanceof CustomClickEvent customClickEvent) {
            customClickEvent.run();
            return true;
        }
        return false;
    }
}
