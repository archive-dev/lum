package lum.compiler.codegen.jvm;

import java.lang.classfile.Opcode;
import java.lang.classfile.TypeKind;
import java.util.HashMap;

final class BytecodeHelper {
    private BytecodeHelper() {}

    private static final HashMap<Opcode, TypeKind> primaryTypeKinds = new HashMap<>();
    private static final HashMap<Opcode, TypeKind> secondaryTypeKinds = new HashMap<>();

    public static TypeKind primaryTypeKind(Opcode opcode) {
        return primaryTypeKinds.get(opcode);
    }

    public static TypeKind secondaryTypeKind(Opcode opcode) {
        return secondaryTypeKinds.get(opcode);
    }

    private static void putTypeKinds(Opcode opcode, TypeKind primary) {
        primaryTypeKinds.put(opcode, primary);
    }

    private static void putTypeKinds(Opcode opcode, TypeKind primary, TypeKind secondary) {
        primaryTypeKinds.put(opcode, primary);
        secondaryTypeKinds.put(opcode, secondary);
    }

    static {
        putTypeKinds(Opcode.ACONST_NULL, TypeKind.REFERENCE);
        putTypeKinds(Opcode.ICONST_M1, TypeKind.INT);
        putTypeKinds(Opcode.ICONST_0, TypeKind.INT);
        putTypeKinds(Opcode.ICONST_1, TypeKind.INT);
        putTypeKinds(Opcode.ICONST_2, TypeKind.INT);
        putTypeKinds(Opcode.ICONST_3, TypeKind.INT);
        putTypeKinds(Opcode.ICONST_4, TypeKind.INT);
        putTypeKinds(Opcode.ICONST_5, TypeKind.INT);
        putTypeKinds(Opcode.LCONST_0, TypeKind.LONG);
        putTypeKinds(Opcode.LCONST_1, TypeKind.LONG);
        putTypeKinds(Opcode.FCONST_0, TypeKind.FLOAT);
        putTypeKinds(Opcode.FCONST_1, TypeKind.FLOAT);
        putTypeKinds(Opcode.FCONST_2, TypeKind.FLOAT);
        putTypeKinds(Opcode.DCONST_0, TypeKind.DOUBLE);
        putTypeKinds(Opcode.DCONST_1, TypeKind.DOUBLE);
        putTypeKinds(Opcode.BIPUSH, TypeKind.BYTE);
        putTypeKinds(Opcode.SIPUSH, TypeKind.SHORT);
        putTypeKinds(Opcode.ILOAD, TypeKind.INT);
        putTypeKinds(Opcode.LLOAD, TypeKind.LONG);
        putTypeKinds(Opcode.FLOAD, TypeKind.FLOAT);
        putTypeKinds(Opcode.DLOAD, TypeKind.DOUBLE);
        putTypeKinds(Opcode.ALOAD, TypeKind.REFERENCE);
        putTypeKinds(Opcode.ILOAD_0, TypeKind.INT);
        putTypeKinds(Opcode.ILOAD_1, TypeKind.INT);
        putTypeKinds(Opcode.ILOAD_2, TypeKind.INT);
        putTypeKinds(Opcode.ILOAD_3, TypeKind.INT);
        putTypeKinds(Opcode.LLOAD_0, TypeKind.LONG);
        putTypeKinds(Opcode.LLOAD_1, TypeKind.LONG);
        putTypeKinds(Opcode.LLOAD_2, TypeKind.LONG);
        putTypeKinds(Opcode.LLOAD_3, TypeKind.LONG);
        putTypeKinds(Opcode.FLOAD_0, TypeKind.FLOAT);
        putTypeKinds(Opcode.FLOAD_1, TypeKind.FLOAT);
        putTypeKinds(Opcode.FLOAD_2, TypeKind.FLOAT);
        putTypeKinds(Opcode.FLOAD_3, TypeKind.FLOAT);
        putTypeKinds(Opcode.DLOAD_0, TypeKind.DOUBLE);
        putTypeKinds(Opcode.DLOAD_1, TypeKind.DOUBLE);
        putTypeKinds(Opcode.DLOAD_2, TypeKind.DOUBLE);
        putTypeKinds(Opcode.DLOAD_3, TypeKind.DOUBLE);
        putTypeKinds(Opcode.ALOAD_0, TypeKind.REFERENCE);
        putTypeKinds(Opcode.ALOAD_1, TypeKind.REFERENCE);
        putTypeKinds(Opcode.ALOAD_2, TypeKind.REFERENCE);
        putTypeKinds(Opcode.ALOAD_3, TypeKind.REFERENCE);
        putTypeKinds(Opcode.IALOAD, TypeKind.INT);
        putTypeKinds(Opcode.LALOAD, TypeKind.LONG);
        putTypeKinds(Opcode.FALOAD, TypeKind.FLOAT);
        putTypeKinds(Opcode.DALOAD, TypeKind.DOUBLE);
        putTypeKinds(Opcode.AALOAD, TypeKind.REFERENCE);
        putTypeKinds(Opcode.BALOAD, TypeKind.BYTE);
        putTypeKinds(Opcode.CALOAD, TypeKind.CHAR);
        putTypeKinds(Opcode.SALOAD, TypeKind.SHORT);
        putTypeKinds(Opcode.ISTORE, TypeKind.INT);
        putTypeKinds(Opcode.LSTORE, TypeKind.LONG);
        putTypeKinds(Opcode.FSTORE, TypeKind.FLOAT);
        putTypeKinds(Opcode.DSTORE, TypeKind.DOUBLE);
        putTypeKinds(Opcode.ASTORE, TypeKind.REFERENCE);
        putTypeKinds(Opcode.ISTORE_0, TypeKind.INT);
        putTypeKinds(Opcode.ISTORE_1, TypeKind.INT);
        putTypeKinds(Opcode.ISTORE_2, TypeKind.INT);
        putTypeKinds(Opcode.ISTORE_3, TypeKind.INT);
        putTypeKinds(Opcode.LSTORE_0, TypeKind.LONG);
        putTypeKinds(Opcode.LSTORE_1, TypeKind.LONG);
        putTypeKinds(Opcode.LSTORE_2, TypeKind.LONG);
        putTypeKinds(Opcode.LSTORE_3, TypeKind.LONG);
        putTypeKinds(Opcode.FSTORE_0, TypeKind.FLOAT);
        putTypeKinds(Opcode.FSTORE_1, TypeKind.FLOAT);
        putTypeKinds(Opcode.FSTORE_2, TypeKind.FLOAT);
        putTypeKinds(Opcode.FSTORE_3, TypeKind.FLOAT);
        putTypeKinds(Opcode.DSTORE_0, TypeKind.DOUBLE);
        putTypeKinds(Opcode.DSTORE_1, TypeKind.DOUBLE);
        putTypeKinds(Opcode.DSTORE_2, TypeKind.DOUBLE);
        putTypeKinds(Opcode.DSTORE_3, TypeKind.DOUBLE);
        putTypeKinds(Opcode.ASTORE_0, TypeKind.REFERENCE);
        putTypeKinds(Opcode.ASTORE_1, TypeKind.REFERENCE);
        putTypeKinds(Opcode.ASTORE_2, TypeKind.REFERENCE);
        putTypeKinds(Opcode.ASTORE_3, TypeKind.REFERENCE);
        putTypeKinds(Opcode.IASTORE, TypeKind.INT);
        putTypeKinds(Opcode.LASTORE, TypeKind.LONG);
        putTypeKinds(Opcode.FASTORE, TypeKind.FLOAT);
        putTypeKinds(Opcode.DASTORE, TypeKind.DOUBLE);
        putTypeKinds(Opcode.AASTORE, TypeKind.REFERENCE);
        putTypeKinds(Opcode.BASTORE, TypeKind.BYTE);
        putTypeKinds(Opcode.CASTORE, TypeKind.CHAR);
        putTypeKinds(Opcode.SASTORE, TypeKind.SHORT);
        putTypeKinds(Opcode.IADD, TypeKind.INT);
        putTypeKinds(Opcode.LADD, TypeKind.LONG);
        putTypeKinds(Opcode.FADD, TypeKind.FLOAT);
        putTypeKinds(Opcode.DADD, TypeKind.DOUBLE);
        putTypeKinds(Opcode.ISUB, TypeKind.INT);
        putTypeKinds(Opcode.LSUB, TypeKind.LONG);
        putTypeKinds(Opcode.FSUB, TypeKind.FLOAT);
        putTypeKinds(Opcode.DSUB, TypeKind.DOUBLE);
        putTypeKinds(Opcode.IMUL, TypeKind.INT);
        putTypeKinds(Opcode.LMUL, TypeKind.LONG);
        putTypeKinds(Opcode.FMUL, TypeKind.FLOAT);
        putTypeKinds(Opcode.DMUL, TypeKind.DOUBLE);
        putTypeKinds(Opcode.IDIV, TypeKind.INT);
        putTypeKinds(Opcode.LDIV, TypeKind.LONG);
        putTypeKinds(Opcode.FDIV, TypeKind.FLOAT);
        putTypeKinds(Opcode.DDIV, TypeKind.DOUBLE);
        putTypeKinds(Opcode.IREM, TypeKind.INT);
        putTypeKinds(Opcode.LREM, TypeKind.LONG);
        putTypeKinds(Opcode.FREM, TypeKind.FLOAT);
        putTypeKinds(Opcode.DREM, TypeKind.DOUBLE);
        putTypeKinds(Opcode.INEG, TypeKind.INT);
        putTypeKinds(Opcode.LNEG, TypeKind.LONG);
        putTypeKinds(Opcode.FNEG, TypeKind.FLOAT);
        putTypeKinds(Opcode.DNEG, TypeKind.DOUBLE);
        putTypeKinds(Opcode.ISHL, TypeKind.INT);
        putTypeKinds(Opcode.LSHL, TypeKind.LONG);
        putTypeKinds(Opcode.ISHR, TypeKind.INT);
        putTypeKinds(Opcode.LSHR, TypeKind.LONG);
        putTypeKinds(Opcode.IUSHR, TypeKind.INT);
        putTypeKinds(Opcode.LUSHR, TypeKind.LONG);
        putTypeKinds(Opcode.IAND, TypeKind.INT);
        putTypeKinds(Opcode.LAND, TypeKind.LONG);
        putTypeKinds(Opcode.IOR, TypeKind.INT);
        putTypeKinds(Opcode.LOR, TypeKind.LONG);
        putTypeKinds(Opcode.IXOR, TypeKind.INT);
        putTypeKinds(Opcode.LXOR, TypeKind.LONG);
        putTypeKinds(Opcode.IINC, TypeKind.INT);
        putTypeKinds(Opcode.I2L, TypeKind.INT, TypeKind.LONG);
        putTypeKinds(Opcode.I2F, TypeKind.INT, TypeKind.FLOAT);
        putTypeKinds(Opcode.I2D, TypeKind.INT, TypeKind.DOUBLE);
        putTypeKinds(Opcode.L2I, TypeKind.LONG, TypeKind.INT);
        putTypeKinds(Opcode.L2F, TypeKind.LONG, TypeKind.FLOAT);
        putTypeKinds(Opcode.L2D, TypeKind.LONG, TypeKind.DOUBLE);
        putTypeKinds(Opcode.F2I, TypeKind.FLOAT, TypeKind.INT);
        putTypeKinds(Opcode.F2L, TypeKind.FLOAT, TypeKind.LONG);
        putTypeKinds(Opcode.F2D, TypeKind.FLOAT, TypeKind.DOUBLE);
        putTypeKinds(Opcode.D2I, TypeKind.DOUBLE, TypeKind.INT);
        putTypeKinds(Opcode.D2L, TypeKind.DOUBLE, TypeKind.LONG);
        putTypeKinds(Opcode.D2F, TypeKind.DOUBLE, TypeKind.FLOAT);
        putTypeKinds(Opcode.I2B, TypeKind.INT, TypeKind.BYTE);
        putTypeKinds(Opcode.I2C, TypeKind.INT, TypeKind.CHAR);
        putTypeKinds(Opcode.I2S, TypeKind.INT, TypeKind.SHORT);
        putTypeKinds(Opcode.LCMP, TypeKind.LONG);
        putTypeKinds(Opcode.FCMPL, TypeKind.FLOAT);
        putTypeKinds(Opcode.FCMPG, TypeKind.FLOAT);
        putTypeKinds(Opcode.DCMPL, TypeKind.DOUBLE);
        putTypeKinds(Opcode.DCMPG, TypeKind.DOUBLE);
        putTypeKinds(Opcode.IFEQ, TypeKind.INT);
        putTypeKinds(Opcode.IFNE, TypeKind.INT);
        putTypeKinds(Opcode.IFLT, TypeKind.INT);
        putTypeKinds(Opcode.IFGE, TypeKind.INT);
        putTypeKinds(Opcode.IFGT, TypeKind.INT);
        putTypeKinds(Opcode.IFLE, TypeKind.INT);
        putTypeKinds(Opcode.IF_ICMPEQ, TypeKind.INT);
        putTypeKinds(Opcode.IF_ICMPNE, TypeKind.INT);
        putTypeKinds(Opcode.IF_ICMPLT, TypeKind.INT);
        putTypeKinds(Opcode.IF_ICMPGE, TypeKind.INT);
        putTypeKinds(Opcode.IF_ICMPGT, TypeKind.INT);
        putTypeKinds(Opcode.IF_ICMPLE, TypeKind.INT);
        putTypeKinds(Opcode.IF_ACMPEQ, TypeKind.REFERENCE);
        putTypeKinds(Opcode.IF_ACMPNE, TypeKind.REFERENCE);
        putTypeKinds(Opcode.GOTO, TypeKind.VOID);
        putTypeKinds(Opcode.IRETURN, TypeKind.INT);
        putTypeKinds(Opcode.LRETURN, TypeKind.LONG);
        putTypeKinds(Opcode.FRETURN, TypeKind.FLOAT);
        putTypeKinds(Opcode.DRETURN, TypeKind.DOUBLE);
        putTypeKinds(Opcode.ARETURN, TypeKind.REFERENCE);
        putTypeKinds(Opcode.RETURN, TypeKind.VOID);
        putTypeKinds(Opcode.ARRAYLENGTH, TypeKind.INT);
        putTypeKinds(Opcode.IFNULL, TypeKind.REFERENCE);
        putTypeKinds(Opcode.IFNONNULL, TypeKind.REFERENCE);
        putTypeKinds(Opcode.GOTO_W, TypeKind.VOID);
        putTypeKinds(Opcode.ILOAD_W, TypeKind.INT);
        putTypeKinds(Opcode.LLOAD_W, TypeKind.LONG);
        putTypeKinds(Opcode.FLOAD_W, TypeKind.FLOAT);
        putTypeKinds(Opcode.DLOAD_W, TypeKind.DOUBLE);
        putTypeKinds(Opcode.ALOAD_W, TypeKind.REFERENCE);
        putTypeKinds(Opcode.ISTORE_W, TypeKind.INT);
        putTypeKinds(Opcode.LSTORE_W, TypeKind.LONG);
        putTypeKinds(Opcode.FSTORE_W, TypeKind.FLOAT);
        putTypeKinds(Opcode.DSTORE_W, TypeKind.DOUBLE);
        putTypeKinds(Opcode.ASTORE_W, TypeKind.REFERENCE);
        putTypeKinds(Opcode.IINC_W, TypeKind.INT);
    }
}
