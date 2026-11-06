package com.jeff.pets.javaagent.asm;

import com.jeff.pets.PetsInitializer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AttributeSupplierMixin {

    public static void registerAttributes() {
        try {
            Field field = DefaultAttributes.class.getDeclaredField("SUPPLIERS");
            field.setAccessible(true);
            Map<EntityType<? extends @NotNull LivingEntity>, AttributeSupplier> SUPPLIERS = (Map<EntityType<? extends LivingEntity>, AttributeSupplier>) field.get(null);

            Map<EntityType<?>, AttributeSupplier> map = new HashMap<>();
            map.putAll(SUPPLIERS);

            PetsInitializer.onInitialize(map::put);

            field.set(null, map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        for (MethodNode method : classNode.methods) {
            if ("<clinit>".equals(method.name)) {

                for (AbstractInsnNode insn : method.instructions.toArray()) {
                    if (insn.getOpcode() == Opcodes.RETURN) {

                        InsnList toInject = new InsnList();

                        toInject.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "com/jeff/pets/javaagent/asm/AttributeSupplierMixin",
                                "registerAttributes",
                                "()V",
                                false
                        ));
                        method.instructions.insertBefore(insn, toInject);
                    }
                }
                break;
            }
        }

        for (FieldNode field : classNode.fields) {
            if ("SUPPLIERS".equals(field.name)) {
                field.access &= ~Opcodes.ACC_FINAL;
                field.access &= ~Opcodes.ACC_PRIVATE;
                field.access &= ~Opcodes.ACC_PROTECTED;
                field.access |= Opcodes.ACC_PUBLIC;
                break;
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}
