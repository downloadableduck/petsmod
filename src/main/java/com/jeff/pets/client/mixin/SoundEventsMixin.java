package com.jeff.pets.client.mixin;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class SoundEventsMixin {
    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        for (MethodNode method : classNode.methods) {
            if ("<clinit>".equals(method.name)) {
                InsnList list = new InsnList();
                list.add(new MethodInsnNode(
                        Opcodes.INVOKESTATIC,
                        "com/jeff/pets/PetsSounds",
                        "initialize",
                        "()V",
                        false
                ));
                method.instructions.insert(list);
            }
        }
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}
