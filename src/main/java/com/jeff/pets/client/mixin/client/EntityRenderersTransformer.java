package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.PetsClientInitializer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class EntityRenderersTransformer {

    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        for (MethodNode method : classNode.methods) {
            // Target createEntityRenderers(Context context)
            if ("createEntityRenderers".equals(method.name)) {
                for (AbstractInsnNode insn : method.instructions.toArray()) {
                    if (insn.getOpcode() == Opcodes.ARETURN) {

                        InsnList toInject = new InsnList();

                        // ALOAD 0 (Context parameter)
                        toInject.add(new VarInsnNode(Opcodes.ALOAD, 0));

                        // Call EntityRenderersDelegate.appendCustomRenderers(Map vanillaMap, Context context)
                        toInject.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "com/jeff/pets/client/mixin/client/EntityRenderersDelegate",
                                "appendCustomRenderers",
                                "(Ljava/util/Map;Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)Ljava/util/Map;",
                                false
                        ));

                        method.instructions.insertBefore(insn, toInject);
                        System.out.println("[Agent] Injected renderer hook into EntityRenderers.createEntityRenderers()");
                    }
                }
                break;
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}