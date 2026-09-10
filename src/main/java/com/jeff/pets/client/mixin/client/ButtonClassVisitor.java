package com.jeff.pets.client.mixin.client;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ButtonClassVisitor extends ClassVisitor {
    public ButtonClassVisitor(int api, ClassWriter writer) {
        super(api, writer);
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
