package com.jeff.pets.client.mixin;

import org.objectweb.asm.*;

public class ButtonClassVisitor extends ClassVisitor {
    public ButtonClassVisitor(int api, ClassWriter writer) {
        super(api, writer);
    }

    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
        ButtonClassVisitor transformer = new ButtonClassVisitor(Opcodes.ASM9, writer);
        reader.accept(transformer, 0);
        return writer.toByteArray();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String superName, String[] args) {
        if (name.equals("onValueChange") && descriptor.equals("(Ljava/lang/String;)V")) {
            int access2 = (access & ~Opcodes.ACC_FINAL & ~Opcodes.ACC_PRIVATE) | Opcodes.ACC_PUBLIC;
            return super.visitMethod(access2, name, descriptor, superName, args);
        }
        return super.visitMethod(access, name, descriptor, superName, args);
    }
}
