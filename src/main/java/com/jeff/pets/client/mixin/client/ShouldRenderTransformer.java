package com.jeff.pets.client.mixin.client;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class ShouldRenderTransformer {

    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        boolean hooked = false;

        for (MethodNode method : classNode.methods) {
            // Target: shouldRender(Entity, Frustum, double, double, double) -> boolean
            if ("shouldRender".equals(method.name) && method.desc.startsWith("(Lnet/minecraft/world/entity/Entity;")) {

                for (AbstractInsnNode insn : method.instructions.toArray()) {
                    if (insn.getOpcode() == Opcodes.INVOKEVIRTUAL) {
                        MethodInsnNode minsn = (MethodInsnNode) insn;
                        if ("getRenderer".equals(minsn.name)) {

                            InsnList toInject = new InsnList();

                            // 1. Stack top currently has [EntityRenderer] (result of getRenderer)
                            // Push local variable 1 ('entity') onto stack -> [EntityRenderer, Entity]
                            toInject.add(new VarInsnNode(Opcodes.ALOAD, 1));

                            // 2. Pass both to ShouldRenderDelegate.guardRenderer
                            toInject.add(new MethodInsnNode(
                                    Opcodes.INVOKESTATIC,
                                    "com/jeff/pets/client/mixin/client/ShouldRenderDelegate",
                                    "guardRenderer",
                                    "(Lnet/minecraft/client/renderer/entity/EntityRenderer;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/client/renderer/entity/EntityRenderer;",
                                    false
                            ));

                            // Insert everything immediately AFTER invokevirtual getRenderer
                            method.instructions.insert(insn, toInject);

                            hooked = true;
                            System.out.println("[Agent] Injected null guard into EntityRenderDispatcher.shouldRender()");
                            break;
                        }
                    }
                }
            }
        }

        if (!hooked) {
            System.err.println("[Agent] FAILED to inject null guard into shouldRender()");
        }

        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}