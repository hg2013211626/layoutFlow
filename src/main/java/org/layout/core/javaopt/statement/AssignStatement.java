package org.layout.core.javaopt.statement;

import org.layout.core.javaopt.CodeEmitter;
import org.layout.core.javaopt.expr.JavaExpr;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.statement
 * @className: AssignStatement
 * @author: HuangGen
 * @description: 赋值语句
 * @date: 2022/8/27 3:30 AM
 * @version: 1.0
 */
public class AssignStatement implements StatementSpec{
    private JavaExpr left;
    private JavaExpr right;

    public AssignStatement(JavaExpr left, JavaExpr right) {
        this.left = left;
        this.right = right;
    }

    public JavaExpr getLeft() {
        return left;
    }

    public void setLeft(JavaExpr left) {
        this.left = left;
    }

    public JavaExpr getRight() {
        return right;
    }

    public void setRight(JavaExpr right) {
        this.right = right;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        emitter.indent().emit(left).emit(" = ").emit(right).emit(";").newLine();
    }

    @Override
    public void emitImport(CodeEmitter emitter) {
        left.emitImport(emitter);
        right.emitImport(emitter);
    }
}
