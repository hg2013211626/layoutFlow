package org.layout.core.javaopt.type;

import org.layout.core.javaopt.CodeEmitable;
import org.layout.core.javaopt.CodeEmitter;
import org.layout.core.javaopt.expr.TypeName;

import java.util.List;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.type
 * @className: FieldSpec
 * @author: HuangGen
 * @description: 属性描述对象
 * @date: 2022/9/2 3:54 AM
 * @version: 1.0
 */
public class FieldSpec implements CodeEmitable {
    private TypeName type;

    private List<TypeName> generics;

    private String name;


    @Override
    public void emit(CodeEmitter emitter) {

    }

    @Override
    public void emitImport(CodeEmitter emitter) {

    }
}
