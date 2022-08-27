package org.layout.core.javaopt.expr;

import org.layout.core.javaopt.CodeEmitter;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.expr
 * @className: BooleanJExpr
 * @author: HuangGen
 * @description: 布尔表达式
 * @date: 2022/8/27 3:26 AM
 * @version: 1.0
 */
public class BooleanJExpr extends BaseJavaExpr{

    private boolean value;

    private BooleanJExpr (boolean value) {
        this.value = value;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        emitter.emit(String.valueOf(value));
    }

    @Override
    public void emitImport(CodeEmitter emitter) {

    }
}
