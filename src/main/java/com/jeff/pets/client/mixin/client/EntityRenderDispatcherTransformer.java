package com.jeff.pets.client.mixin.client;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class EntityRenderDispatcherTransformer {

    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        for (MethodNode method : classNode.methods) {
            // Hook inside onResourceManagerReload
            if ("onResourceManagerReload".equals(method.name)) {
                for (AbstractInsnNode insn : method.instructions.toArray()) {
                    if (insn.getOpcode() == Opcodes.PUTFIELD) {
                        FieldInsnNode finsn = (FieldInsnNode) insn;
                        if ("renderers".equals(finsn.name)) {
                            InsnList toInject = new InsnList();

                            // Stack top has the Map returned by createEntityRenderers
                            // Pass it through appendCustomRenderers
                            toInject.add(new MethodInsnNode(
                                    Opcodes.INVOKESTATIC,
                                    "com/jeff/pets/client/mixin/client/EntityRenderDispatcherDelegate",
                                    "appendCustomRenderers",
                                    "(Ljava/util/Map;)Ljava/util/Map;",
                                    false
                            ));

                            method.instructions.insertBefore(insn, toInject);
                            System.out.println("[Agent] Successfully hooked EntityRenderDispatcher.renderers field assignment!");
                        }
                    }
                }
            }
            if ("onResourceManagerReload".equals(method.name)) {
                for (AbstractInsnNode insn : method.instructions.toArray()) {
                    if (insn.getOpcode() == Opcodes.PUTFIELD) {
                        FieldInsnNode finsn = (FieldInsnNode) insn;
                        if ("renderers".equals(finsn.name)) {
                            InsnList toInject = new InsnList();

                            // Merge vanilla map with custom map before assignment
                            toInject.add(new MethodInsnNode(
                                    Opcodes.INVOKESTATIC,
                                    "com/jeff/pets/client/mixin/client/EntityRenderDispatcherDelegate",
                                    "appendCustomRenderers",
                                    "(Ljava/util/Map;)Ljava/util/Map;",
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