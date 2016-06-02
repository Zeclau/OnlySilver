package zotmc.onlysilver.loading;

import static com.google.common.base.Preconditions.checkState;

import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.InstructionAdapter;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

import zotmc.onlysilver.util.init.MethodInfo;

import com.google.common.collect.Lists;

public final class InsnListBuilder extends InstructionAdapter {

  public InsnListBuilder() {
    super(Opcodes.ASM5, new MethodNode());
  }

  public InsnList build() {
    MethodNode mn = (MethodNode) mv;
    InsnList ret = mn.instructions;
    mn.instructions = null;
    checkState(ret != null);
    return ret;
  }

  public void aload(int var) {
    visitVarInsn(Opcodes.ALOAD, var);
  }

  public void iload(int var) {
    visitVarInsn(Opcodes.ILOAD, var);
  }

  public void fload(int var) {
    visitVarInsn(Opcodes.FLOAD, var);
  }

  public void _return() {
    visitInsn(Opcodes.RETURN);
  }

  public void ireturn() {
    visitInsn(Opcodes.IRETURN);
  }

  public void areturn() {
    visitInsn(Opcodes.ARETURN);
  }

  public void iadd() {
    visitInsn(Opcodes.IADD);
  }

  public void invokestatic(MethodPredicate method, boolean itf) {
    MethodInfo m = method.toMethodInfo(0);
    invokestatic(method.getOwner().toString(), m.getName(), m.getDescriptor(), itf);
  }

  public void invokevirtual(MethodPredicate method, boolean itf) {
    MethodInfo m = method.toMethodInfo(0);
    invokevirtual(method.getOwner().toString(), m.getName(), m.getDescriptor(), itf);
  }

  public void getstatic(MethodPredicate field) {
    MethodInfo m = field.toMethodInfo(0);
    getstatic(field.getOwner().toString(), m.getName(), m.getDescriptor());
  }

  public void getfield(MethodPredicate field) {
    MethodInfo m = field.toMethodInfo(0);
    getfield(field.getOwner().toString(), m.getName(), m.getDescriptor());
  }

  public void push(Object cst) {
    if (cst == null) aconst(null);
    else visitLdcInsn(cst);
  }

  public void tryCatchBlock(Label start, Label end, Label handler) {
    MethodNode mn = (MethodNode) mv;
    if (mn.tryCatchBlocks == null) mn.tryCatchBlocks = Lists.newArrayList();
    visitTryCatchBlock(start, end, handler, null);
  }

}
