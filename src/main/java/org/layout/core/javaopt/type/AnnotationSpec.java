package org.layout.core.javaopt.type;

import org.layout.core.javaopt.CodeEmitable;
import org.layout.core.javaopt.CodeEmitter;
import org.layout.core.javaopt.expr.TypeName;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.type
 * @className: AnnotationSpec
 * @author: HuangGen
 * @description: 注解描述
 * @date: 2022/9/2 1:55 AM
 * @version: 1.0
 */
public class AnnotationSpec implements CodeEmitable {
    private TypeName type;

    private String content;

    public AnnotationSpec(Class<?> clazz) {
        this.type = new TypeName(clazz);
    }

    public AnnotationSpec(String classFullName) {
        this.type = new TypeName(classFullName);
    }

    public AnnotationSpec(Class<?> clazz, String content) {
        this.type = new TypeName(clazz);
        this.content = content;
    }

    public AnnotationSpec(String classFullName,String content) {
        this.type = new TypeName(classFullName);
        this.content = content;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        emitter.indent().emit("@").emit(type);
        if(content != null && !"".equals(content)) {
            emitter.emit("(" + content +")");
        }
        emitter.newLine();
    }

    @Override
    public void emitImport(CodeEmitter emitter) {
        emitter.emitImport(type);
    }
}
