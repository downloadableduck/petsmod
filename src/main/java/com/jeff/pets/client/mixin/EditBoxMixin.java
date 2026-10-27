package com.jeff.pets.client.mixin;

import com.jeff.pets.client.screen.buttons.SearchBox;
import net.minecraft.client.gui.components.EditBox;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class EditBoxMixin {
    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);
        for (MethodNode methodNode : node.methods) {
            if ("insertText".equals(methodNode.name)) {
                for (AbstractInsnNode instruction : methodNode.instructions) {
                    if (instruction instanceof MethodInsnNode methodInsnNode && methodInsnNode.getOpcode() == Opcodes.INVOKEVIRTUAL && methodInsnNode.owner.equals("java/lang/StringBuilder") && methodInsnNode.name.equals("toString")) {
                        InsnList list = new InsnList();
                        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
                        list.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "com/jeff/pets/client/mixin/EditBoxMixin",
                                "pets$onInsertText",
                                "(Ljava/lang/StringBuilder;Lnet/minecraft/client/gui/components/EditBox;)Ljava/lang/String;"));
                        methodNode.instructions.insertBefore(methodInsnNode, list);
                        methodNode.instructions.remove(methodInsnNode);
                    }
                }
            }
        }
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        node.accept(writer);
        return writer.toByteArray();
    }

    public static String pets$onInsertText(StringBuilder instance, EditBox box) {
        if (box instanceof SearchBox) {
            return box.getValue();
        }
        return instance.toString();
    }
}
