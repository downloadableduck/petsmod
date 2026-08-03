package com.jeff.pets.client.mixin.client;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class LayerDefinitionsTransformer {

    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        for (MethodNode method : classNode.methods) {
            if ("createRoots".equals(method.name) && "()Ljava/util/Map;".equals(method.desc)) {
                for (AbstractInsnNode insn : method.instructions.toArray()) {
                    if (insn.getOpcode() == Opcodes.INVOKEVIRTUAL) {
                        MethodInsnNode minsn = (MethodInsnNode) insn;
                        if ("com/google/common/collect/ImmutableMap$Builder".equals(minsn.owner)
                                && "build".equals(minsn.name)) {

                            InsnList toInject = new InsnList();

                            toInject.add(new MethodInsnNode(
                                    Opcodes.INVOKESTATIC,
                                    "com/jeff/pets/client/mixin/client/LayerDefinitionsDelegate",
                                    "populateBuilder",
                                    "(Lcom/google/common/collect/ImmutableMap$Builder;)Lcom/google/common/collect/ImmutableMap$Builder;",
                                    false
                            ));

                            method.instructions.insertBefore(insn, toInject);
                            break;
                        }
                    }
                }
            }
        }

        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}