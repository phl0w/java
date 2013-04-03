package org.phl0w.it07.methods;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Version {

    public static int getCurrentRevision(final String randomWorld) throws IOException {
        int rev = 1;
        while (rev < 100) {
            final Socket s = new Socket(randomWorld, 43594);
            final DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            final DataInputStream dis = new DataInputStream(s.getInputStream());
            dos.writeByte(15);
            dos.writeInt(rev);
            dos.flush();
            byte[] recv = new byte[1];
            dis.read(recv);
            s.close();
            if (recv[0] == 0)
                return rev;
            else
                rev++;
        }
        return -1;
    }

    /**
     * @param client the classnode that represents client.class
     * @return the revision of the client
     */
   /* public static int identifyRSVersion(ClassNode client) {
        for (MethodNode methodNode : client.methods) {
            InstructionSearcher instructionSearcher = getInstructionSearcher("js5io", methodNode);
            if (instructionSearcher != null) {     //if it contains the ldc, then it's the right method
                MethodInsnNode methodInsnNode = (MethodInsnNode) instructionSearcher.getNext(Opcodes.INVOKEVIRTUAL);
                while (methodInsnNode != null) {
                    Type type = Type.getType(methodInsnNode.desc);
                    if (intArguments(type.getArgumentTypes()) && type.getArgumentTypes().length > 1
                            && !methodInsnNode.owner.equals("client")) {
                        AbstractInsnNode toCheck = methodInsnNode.getPrevious().getPrevious();
                        AbstractInsnNode newNode = instructionSearcher.getPrevious(Opcodes.NEW);
                        AbstractInsnNode curr = newNode.getNext();
                        int count = 0;
                        while (curr != null && !curr.equals(toCheck)) {
                            if (curr.getOpcode() == Opcodes.INVOKEVIRTUAL)
                                count++;
                            curr = curr.getNext();
                            if (count > 1) {
                                break;
                            }
                        }
                        if (count == 1) {
                            return version(toCheck);
                        }
                    }
                    methodInsnNode = (MethodInsnNode) instructionSearcher.getNext(Opcodes.INVOKEVIRTUAL);
                }
            }
        }
        return -1;
    }

    private static int version(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode instanceof IntInsnNode) {
            IntInsnNode intInsnNode = (IntInsnNode) abstractInsnNode;
            return intInsnNode.operand;
        } else if (abstractInsnNode.getOpcode() < 9) {
            return abstractInsnNode.getOpcode() - 3;
        }
        return -1;
    }

    private static InstructionSearcher getInstructionSearcher(String cst, MethodNode methodNode) {
        InstructionSearcher instructionSearcher = new InstructionSearcher(methodNode);
        LdcInsnNode ldcInsnNode = (LdcInsnNode) instructionSearcher.getNext(Opcodes.LDC);
        while (ldcInsnNode != null) {
            if (ldcInsnNode.cst.toString().equals(cst)) {
                return instructionSearcher;
            }
            ldcInsnNode = (LdcInsnNode) instructionSearcher.getNext(Opcodes.LDC);
        }
        return null;
    }

    private static boolean intArguments(Type[] types) {
        for (Type t : types) {
            if (!t.getClassName().equals("int")) {
                return false;
            }
        }
        return true;
    } */
}
