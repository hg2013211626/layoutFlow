package org.layout.core.javaopt.expr;

import org.layout.core.javaopt.CodeEmitter;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.expr
 * @className: StringJExper
 * @author: HuangGen
 * @description: 字符串表达式
 * @date: 2022/8/27 3:23 AM
 * @version: 1.0
 */
public class StringJExper extends BaseJavaExpr{

    private String text;

    public StringJExper(String text) {
        this.text = text;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        emitter.emit('"').emit(text.replace("\\\"","\"").replace("\"","\\\"").replaceAll("\n","\\\\n")).emit('"');
    }

    @Override
    public void emitImport(CodeEmitter emitter) {

    }
}
