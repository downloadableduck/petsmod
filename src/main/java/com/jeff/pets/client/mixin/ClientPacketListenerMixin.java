package com.jeff.pets.client.mixin;

import com.jeff.pets.client.Central;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class ClientPacketListenerMixin {
    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        for (MethodNode method : classNode.methods) {
            if ("handleLogin".equals(method.name)) {

                InsnList instructionsToInject = new InsnList();

                instructionsToInject.add(new MethodInsnNode(
                        Opcodes.INVOKESTATIC,
                        "com/jeff/pets/client/network/PetsNetworked",
                        "createConnectHandler",
                        "()V",
                        false
                ));

                instructionsToInject.add(new MethodInsnNode(
                        Opcodes.INVOKESTATIC,
                        "com/jeff/pets/client/autoupdate/AutoUpdater",
                        "onGameJoin",
                        "()V",
                        false
                ));

                method.instructions.insert(instructionsToInject);
            }
            if ("handleDisconnect".equals(method.name)) {
                InsnList instructionsToInject = new InsnList();

                instructionsToInject.add(new MethodInsnNode(
                        Opcodes.INVOKESTATIC,
                        "com/jeff/pets/client/network/PetsNetworked",
                        "createDisconnectHandler",
                        "()V",
                        false
                ));

                method.instructions.insert(instructionsToInject);
            }
            if (method.name.equals("handleCommands")) {
                for (AbstractInsnNode node : method.instructions) {
                    if (node.getOpcode() == Opcodes.RETURN) {
                        method.instructions.insertBefore(node, new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "com/jeff/pets/client/mixin/ClientPacketListenerMixin",
                                "handleCommands",
                                "()V",
                                false
                        ));
                    }
                }
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classNode.accept(writer);
        return writer.toByteArray();
    }

    public static void handleCommands() {
        CommandDispatcher<ClientSuggestionProvider> serverDispatcher = Minecraft.getInstance().getConnection().getCommands();
        CommandNode clientPetNode = Central.dispatcher.getRoot().getChild("pet");
        if (clientPetNode != null) {
            serverDispatcher.getRoot().getChildren().removeIf(node -> node.getName().equals("pet"));
            serverDispatcher.getRoot().addChild(clientPetNode);
        }
    }
}