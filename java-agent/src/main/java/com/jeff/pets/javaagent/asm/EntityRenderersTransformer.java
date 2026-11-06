package com.jeff.pets.javaagent.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class EntityRenderersTransformer {
    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);
        for (MethodNode method : node.methods) {
            if (method.name.equals("register")) {
                method.access = (method.access & ~Opcodes.ACC_PRIVATE & ~Opcodes.ACC_PROTECTED & ~Opcodes.ACC_FINAL) | Opcodes.ACC_PUBLIC;
            }
        }
        ClassWriter writer = new ClassWriter(0);
        node.accept(writer);
        return writer.toByteArray();
    }
}
