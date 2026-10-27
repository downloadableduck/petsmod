package com.jeff.pets.client.mixin;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import static com.mojang.text2speech.Narrator.LOGGER;

public class SoundSourceMixin {
    public static byte[] transform(byte[] basicclass) {
        ClassReader reader = new ClassReader(basicclass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);
        classNode.fields.add(new FieldNode(
                Opcodes.ACC_STATIC | Opcodes.ACC_PUBLIC | Opcodes.ACC_ENUM | Opcodes.ACC_FINAL,
                "PETS",
                "Lnet/minecraft/sounds/SoundSource;",
                null,
                null
        ));

        for (MethodNode method : classNode.methods) {
            if (method.name.equals("$values")) {
                AbstractInsnNode target = null;
                IntInsnNode biPushNode = null;
                for (AbstractInsnNode node : method.instructions) {
                    if (node.getOpcode() == Opcodes.GETSTATIC && node instanceof FieldInsnNode fieldInsnNode) {
                        if (fieldInsnNode.name.equals("UI")) {
                            target = fieldInsnNode;
                        }
                    }
                    if (node instanceof IntInsnNode intNode && intNode.getOpcode() == Opcodes.BIPUSH && intNode.operand == 11) {
                        biPushNode = intNode;
                    }
                }
                if (target != null && biPushNode != null) {
                    biPushNode.operand = 12;
                    AbstractInsnNode node = target.getNext().getNext();
                    method.instructions.insertBefore(node, new InsnNode(
                            Opcodes.DUP
                    ));
                    method.instructions.insertBefore(node, new IntInsnNode(
                            Opcodes.BIPUSH,
                            11
                    ));

                    method.instructions.insertBefore(node, new FieldInsnNode(
                            Opcodes.GETSTATIC,
                            "net/minecraft/sounds/SoundSource",
                            "PETS",
                            "Lnet/minecraft/sounds/SoundSource;"
                    ));

                    method.instructions.insertBefore(node, new InsnNode(
                            Opcodes.AASTORE
                    ));
                }
            }
            if (method.name.equals("<clinit>")) {
                AbstractInsnNode target = null;
                for (AbstractInsnNode node : method.instructions) {
                    if (node.getOpcode() == Opcodes.PUTSTATIC && node instanceof FieldInsnNode methodInsnNode) {
                        if (methodInsnNode.name.equals("UI")) {
                            target = methodInsnNode;
                            break;
                        }
                    }
                }

                if (target != null) {

                    method.instructions.insertBefore(target, new TypeInsnNode(
                            Opcodes.NEW,
                            "net/minecraft/sounds/SoundSource"
                    ));
                    method.instructions.insertBefore(target, new InsnNode(
                            Opcodes.DUP
                    ));
                    method.instructions.insertBefore(target, new LdcInsnNode(
                            "PETS"
                    ));

                    method.instructions.insertBefore(target, new IntInsnNode(
                            Opcodes.BIPUSH,
                            11
                    ));

                    method.instructions.insertBefore(target, new LdcInsnNode("pets"));

                    method.instructions.insertBefore(
                            target,
                            new MethodInsnNode(
                                    Opcodes.INVOKESPECIAL,
                                    "net/minecraft/sounds/SoundSource",
                                    "<init>",
                                    "(Ljava/lang/String;ILjava/lang/String;)V"
                            )
                    );

                    method.instructions.insertBefore(target, new FieldInsnNode(
                            Opcodes.PUTSTATIC,
                            "net/minecraft/sounds/SoundSource",
                            "PETS",
                            "Lnet/minecraft/sounds/SoundSource;"
                    ));
                }
            }
        }
        try {
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            classNode.accept(writer);
            return writer.toByteArray();
        } catch (Exception e) {
            LOGGER.info("An error occured", e);
            throw e;
        }
    }
}
