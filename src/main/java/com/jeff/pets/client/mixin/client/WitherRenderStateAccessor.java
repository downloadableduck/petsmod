package com.jeff.pets.client.mixin.client;

import net.minecraft.client.renderer.entity.state.WitherRenderState;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.lang.reflect.Field;

public class WitherRenderStateAccessor {

    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        for (FieldNode field : classNode.fields) {
            if ("yHeadRots".equals(field.name)) {
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

    public static void setYHeadRots(WitherRenderState state, float[] floats) {
        try {
            Field field = WitherRenderState.class.getDeclaredField("yHeadRots");
            field.setAccessible(true);
            field.set(state, floats);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
