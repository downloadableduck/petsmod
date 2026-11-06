package com.jeff.pets.javaagent.asm;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.api.Loader;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.main.GameConfig;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public class MinecraftMixin {
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

                instructionsToInject.add(new MethodInsnNode(
                        Opcodes.INVOKESTATIC,
                        "com/jeff/pets/client/PetsClientInitializer",
                        "createKeyBindingWatcher",
                        "()V"
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
                                "com/jeff/pets/javaagent/PetsModJavaAgentInit",
                                "loadResourcePack",
                                "()V",
                                false
                        ));

                        toInject.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "com/jeff/pets/javaagent/asm/KeyMappingDelegate",
                                "createKeyBinding",
                                "()V",
                                false
                        ));

                        toInject.add(new VarInsnNode(Opcodes.ALOAD, 1));

                        toInject.add(new FieldInsnNode(
                                Opcodes.GETFIELD,
                                "net/minecraft/client/main/GameConfig",
                                "location",
                                "Lnet/minecraft/client/main/GameConfig$FolderData;"
                        ));

                        toInject.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "com/jeff/pets/javaagent/asm/MinecraftMixin",
                                "initCentral",
                                "(Lnet/minecraft/client/main/GameConfig$FolderData;)V"
                        ));

                        toInject.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "com/jeff/pets/client/autoupdate/AutoUpdater",
                                "onInitializeClient",
                                "()V",
                                false
                        ));

                        method.instructions.insertBefore(insn, toInject);
                    }
                }
            }
            if ("updateLevelInEngines".equals(method.name) && ("(Lnet/minecraft/client/multiplayer/ClientLevel;Z)V").equals(method.desc)) {
                for (AbstractInsnNode insn : method.instructions.toArray()) {
                    if (insn.getOpcode() == Opcodes.RETURN) {
                        InsnList toInject = new InsnList();

                        toInject.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "com/jeff/pets/client/Central",
                                "createJoinHandler",
                                "()V",
                                false
                        ));

                        toInject.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "com/jeff/pets/client/network/PetsNetworked",
                                "createLevelChangeHandler",
                                "()V",
                                false
                        ));

                        method.instructions.insertBefore(insn, toInject);
                    }
                }
            }
            if ("run".equals(method.name)) {
                method.instructions.insertBefore(method.instructions.get(1), new MethodInsnNode(
                        Opcodes.INVOKESTATIC,
                        "com/jeff/pets/client/Central",
                        "registerAddonConfigData",
                        "()V"
                ));
            }
        }
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classNode.accept(writer);
        return writer.toByteArray();
    }

    public static void initCentral(GameConfig.FolderData data) {
        Central.instantiate(new Loader() {
            @Override
            public Path configDir() {
                return data.gameDirectory.toPath().resolve("config");
            }

            @Override
            public String build() {
                return MinecraftMixin.class.getPackage().getImplementationVersion();
            }

            @Override
            public Collection<String> modsInstalled() {
                return List.of();
            }

            @Override
            public boolean devEnv() {
                return false;
            }
        });
        Central.get().onInitializeClient();
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, _) -> Central.get().setupCommands(() -> (CommandDispatcher) dispatcher)));
    }
}