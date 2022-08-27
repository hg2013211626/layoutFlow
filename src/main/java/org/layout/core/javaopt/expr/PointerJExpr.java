package org.layout.core.javaopt.expr;

import org.layout.core.javaopt.CodeEmitter;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.expr
 * @className: PointerJExpr
 * @author: HuangGen
 * @description: 指针表达式（代码中的点）
 * @date: 2022/8/27 2:31 AM
 * @version: 1.0
 */
public class PointerJExpr extends BaseJavaExpr{
    private JavaExpr parent;

    private JavaExpr child;

    public PointerJExpr(JavaExpr parent, JavaExpr child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        parent.emit(emitter);
        emitter.emit('.');
        child.emit(emitter);
    }

    @Override
    public void emitImport(CodeEmitter emitter) {
        parent.emitImport(emitter);
        child.emitImport(emitter);
    }

    public JavaExpr getParent() {
        return parent;
    }

    public void setParent(JavaExpr parent) {
        this.parent = parent;
    }

    public JavaExpr getChild() {
        return child;
    }

    public void setChild(JavaExpr child) {
        this.child = child;
    }
}
