package org.layout.core.javaopt.expr;

import org.layout.core.javaopt.CodeEmitter;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.expr
 * @className: VariableJExpr
 * @author: HuangGen
 * @description: 变量表达式
 * @date: 2022/8/27 2:45 AM
 * @version: 1.0
 */
public class VariableJExpr extends BaseJavaExpr{

    private String name;

    public VariableJExpr(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        emitter.emit(name);
    }

    @Override
    public void emitImport(CodeEmitter emitter) {

    }
}
