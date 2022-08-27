package org.layout.core.javaopt.expr;

import org.layout.core.javaopt.CodeEmitter;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.expr
 * @className: OperatorJExpr
 * @author: HuangGen
 * @description: 操作符表达式
 * @date: 2022/8/27 3:37 AM
 * @version: 1.0
 */
public class OperatorJExpr extends BaseJavaExpr{

    private JavaExpr left;

    private String operator;

    private JavaExpr right;

    public OperatorJExpr(JavaExpr left, String operator, JavaExpr right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public JavaExpr getLeft() {
        return left;
    }

    public void setLeft(JavaExpr left) {
        this.left = left;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public JavaExpr getRight() {
        return right;
    }

    public void setRight(JavaExpr right) {
        this.right = right;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        left.emit(emitter);
        emitter.emit(' ').emit(operator).emit(' ');

    }

    @Override
    public void emitImport(CodeEmitter emitter) {
        left.emitImport(emitter);
        right.emitImport(emitter);

    }
}
