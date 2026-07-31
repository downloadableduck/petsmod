package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Central;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.Commands;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.lang.reflect.Field;

public class CommandManagerMixin {

    public static void registerCommands(Commands commands) {
        try {
            Field field = Commands.class.getDeclaredField("dispatcher");
            field.setAccessible(true);
            CommandDispatcher dispatcher;
            dispatcher = (CommandDispatcher) field.get(commands);
            Central.get().createPetSkinCommand(dispatcher);
            Central.get().createPetNameCommand(dispatcher);
            Central.get().createPetHelpCommand(dispatcher);
            Central.get().createPetSpeciesCommand(dispatcher);
            Central.get().createPetTeleportCommand(dispatcher);
            Central.get().createToggleCommand(dispatcher);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        for (MethodNode method : classNode.methods) {
            if ("<init>".equals(method.name)) {
                for (AbstractInsnNode insn : method.instructions.toArray()) {
                    if (insn.getOpcode() == Opcodes.RETURN) {

                        InsnList toInject = new InsnList();
                        toInject.add(new VarInsnNode(Opcodes.ALOAD, 0));

                        toInject.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "com/jeff/pets/client/mixin/client/CommandManagerMixin",
                                "registerCommands",
                                "(Lnet/minecraft/commands/Commands;)V",
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