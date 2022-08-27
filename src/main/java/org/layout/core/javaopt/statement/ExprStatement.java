package org.layout.core.javaopt.statement;

import org.layout.core.javaopt.CodeEmitter;
import org.layout.core.javaopt.expr.JavaExpr;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.statement
 * @className: ExprStatement
 * @author: HuangGen
 * @description: 表达式语句
 * @date: 2022/8/27 3:35 AM
 * @version: 1.0
 */
public class ExprStatement implements StatementSpec{
    private JavaExpr expr;

    public ExprStatement(JavaExpr expr) {
        this.expr = expr;
    }

    public JavaExpr getExpr() {
        return expr;
    }

    public void setExpr(JavaExpr expr) {
        this.expr = expr;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        emitter.indent().emit(expr).emit(';').newLine();
    }

    @Override
    public void emitImport(CodeEmitter emitter) {
        expr.emitImport(emitter);
    }
}
