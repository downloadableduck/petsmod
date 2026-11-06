package com.jeff.pets.javaagent;

import com.jeff.pets.client.Central;
import com.jeff.pets.javaagent.asm.*;
import net.fabricmc.fabric.mixin.command.client.ClientPacketListenerMixin;
import net.fabricmc.fabric.mixin.command.client.ClientSuggestionProviderMixin;
import nilloader.NilAgent;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import sun.misc.Unsafe;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

public class Agent implements Runnable {

    public static void premain(String agentArgs, Instrumentation inst) {

        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if (className.contains("net/minecraft"))
                    classfileBuffer = MakeEverythingPublicLol.transform(classfileBuffer);
                switch (className) {
                    case "net/minecraft/world/entity/ai/attributes/DefaultAttributes" -> {
                        return AttributeSupplierMixin.transform(classfileBuffer);
                    }
                    case "net/minecraft/client/multiplayer/ClientPacketListener" -> {
                        return ClientPacketListenerMixin.transform(com.jeff.pets.javaagent.asm.ClientPacketListenerMixin.transform(classfileBuffer));
                    }
                    case "net/minecraft/client/renderer/entity/state/WitherRenderState" -> {
                        return WitherRenderStateAccessor.transform(classfileBuffer);
                    }
                    case "net/minecraft/client/Minecraft" -> {
                        byte[] byt = MinecraftMixin.transform(classfileBuffer);
                        checkforNullObjects();
                        return byt;
                    }
                    case "net/minecraft/world/entity/EntityTypes" -> {
                        return ASMBootstrap.transform(classfileBuffer);
                    }
                    case "net/minecraft/client/model/geom/ModelLayers" -> {
                        return ModelLayersTransformer.transform(classfileBuffer);
                    }
                    case "net/minecraft/client/model/geom/LayerDefinitions" -> {
                        return LayerDefinitionsTransformer.transform(classfileBuffer);
                    }
                    case "net/minecraft/client/renderer/entity/EntityRenderDispatcher" -> {
                        return EntityRenderDispatcherTransformer.transform(classfileBuffer);
                    }
                    case "net/minecraft/client/renderer/entity/EntityRenderers" -> {
                        return EntityRenderersTransformer.transform(classfileBuffer);
                    }
                    case "net/minecraft/client/gui/components/EditBox" -> {
                        return ButtonClassVisitor.transform(EditBoxMixin.transform(classfileBuffer));
                    }
                    case "net/minecraft/client/renderer/entity/state/EntityRenderState" -> {
                        ClassReader reader = new ClassReader(classfileBuffer);
                        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
                        EntityRenderStateClassVisitor visitor = new EntityRenderStateClassVisitor(Opcodes.ASM9, writer);
                        reader.accept(visitor, 0);
                        return writer.toByteArray();
                    }
                    case "net/minecraft/client/renderer/debug/EntityHitboxDebugRenderer" -> {
                        return NoHitboxMixin.transform(classfileBuffer);
                    }
                    case "net/minecraft/sounds/SoundEvents" -> {
                        return SoundEventsMixin.transform(classfileBuffer);
                    }
                    case "net/minecraft/sounds/SoundSource" -> {
                        return SoundSourceMixin.transform(classfileBuffer);
                    }
                    case "net/minecraft/client/multiplayer/ClientSuggestionProvider" -> {
                        ClassReader reader = new ClassReader(classfileBuffer);
                        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
                        ClientSuggestionProviderMixin visitor = new ClientSuggestionProviderMixin(writer);
                        reader.accept(visitor, 0);
                        return writer.toByteArray();
                    }
                    case "net/minecraft/client/gui/screens/Screen" -> {
                        return ScreenMixin.transform(classfileBuffer);
                    }
                    case "net/minecraft/world/entity/Entity" -> {
                        return PlayerRideVehicleMixin.transform(classfileBuffer);
                    }
                }
                return ClassFileTransformer.super.transform(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
            }
        }, true);

        try {
            for (Class<?> clazz : inst.getAllLoadedClasses()) {
                if (clazz.getName().equals("net.minecraft.client.renderer.entity.EntityRenderDispatcher")
                        || clazz.getName().equals("net.minecraft.client.model.geom.LayerDefinitions")) {
                    inst.retransformClasses(clazz);
                }
            }
        } catch (Throwable t) {
        }
    }

    public static void checkforNullObjects() {
        Thread thread = new Thread(() -> {
            Class<?> minecraftClass = null;
            while (minecraftClass == null) {
                try {
                    minecraftClass = Class.forName("net.minecraft.client.Minecraft");
                } catch (ClassNotFoundException ignored) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored2) {
                    }
                }
            }
            try {
                Method getInstanceMethod = minecraftClass.getMethod("getInstance");
                Method executeMethod = minecraftClass.getMethod("execute", Runnable.class);

                Object mcInstance = null;
                while (mcInstance == null) {
                    mcInstance = getInstanceMethod.invoke(null);
                    if (mcInstance == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ignored) {
                        }
                    }
                }

                executeMethod.invoke(mcInstance, (Runnable) () -> {
                    try {
                        Central.checkForNullObjects();
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                });

            } catch (Throwable t) {
                t.printStackTrace();
            }
        }, "agent");

        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unafe = (Unsafe) field.get(null);
            Field field0 = NilAgent.class.getDeclaredField("instrumentation");
            Object base = unafe.staticFieldBase(field0);
            long offset = unafe.staticFieldOffset(field0);
            Instrumentation inst = (Instrumentation) unafe.getObject(base, offset);
            premain("", inst);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}