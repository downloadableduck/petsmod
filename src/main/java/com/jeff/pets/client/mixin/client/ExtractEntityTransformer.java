package com.jeff.pets.client.mixin.client;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class ExtractEntityTransformer {

    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        boolean hooked = false;

        for (MethodNode method : classNode.methods) {
            // Target method: extractEntity(Entity, float) -> EntityRenderState
            if ("extractEntity".equals(method.name) && method.desc.startsWith("(Lnet/minecraft/world/entity/Entity;")) {
                
                for (AbstractInsnNode insn : method.instructions.toArray()) {
                    if (insn.getOpcode() == Opcodes.INVOKEVIRTUAL) {
                        MethodInsnNode minsn = (MethodInsnNode) insn;
                        if ("getRenderer".equals(minsn.name)) {

                            InsnList toInject = new InsnList();

                            // Stack has [EntityRenderer] from getRenderer()
                            // Push local variable 1 ('entity') onto stack -> [EntityRenderer, Entity]
                            toInject.add(new VarInsnNode(Opcodes.ALOAD, 1));

                            // Delegate guard check
                            toInject.add(new MethodInsnNode(
                                    Opcodes.INVOKESTATIC,
                                    "com/jeff/pets/client/mixin/client/ExtractEntityDelegate",
                                    "guardRenderer",
                                    "(Lnet/minecraft/client/renderer/entity/EntityRenderer;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/client/renderer/entity/EntityRenderer;",
                                    false
                            ));

                            method.instructions.insert(insn, toInject);
                            hooked = true;
                            System.out.println("[Agent] Injected null guard into EntityRenderDispatcher.extractEntity()");
                            break;
                        }
                    }
                }
            }
        }

        if (!hooked) {
            System.err.println("[Agent] FAILED to inject null guard into extractEntity()");
        }

        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}