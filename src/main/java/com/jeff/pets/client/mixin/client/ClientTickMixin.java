package com.jeff.pets.client.mixin.client;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class ClientTickMixin {
    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        for (MethodNode method : classNode.methods) {
            if ("tick".equals(method.name)) {

                InsnList instructionsToInject = new InsnList();

                instructionsToInject.add(new MethodInsnNode(
                        Opcodes.INVOKESTATIC,
                        "com/jeff/pets/client/Central",
                        "createTickWatcher",
                        "()V",
                        false
                ));

                method.instructions.insert(instructionsToInject);
            }
            if ("<init>".equals(method.name)) {
                for (AbstractInsnNode insn : method.instructions.toArray()) {
                    if (insn.getOpcode() == Opcodes.RETURN) {

                        InsnList toInject = new InsnList();

                        toInject.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "com/jeff/pets/client/PetsClientInitializer",
                                "registerRenderers",
                                "()V",
                                false
                        ));

                        toInject.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "com/jeff/pets/PetsInitializer",
                                "onInitialize",
                                "()V",
                                false
                        ));

                        method.instructions.insertBefore(insn, toInject);
                    }
                }

            }
        }
                ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
                classNode.accept(writer);
                return writer.toByteArray();
            }
        }