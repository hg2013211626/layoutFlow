package org.layout.core.javaopt.expr;

import org.layout.core.javaopt.CodeEmitable;
import org.layout.core.javaopt.CodeEmitter;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.expr
 * @className: TypeClass
 * @author: HuangGen
 * @description: class类描述
 * @date: 2022/8/27 1:47 AM
 * @version: 1.0
 */
public class TypeClass extends BaseJavaExpr implements CodeEmitable {
    private Class<?> clazz;

    private String classFullName;

    public static TypeName build(Class<?> clazz) {
        return new TypeName(clazz);
    }

    public TypeClass(Class<?> clazz) {
        this.clazz = clazz;
    }

    public TypeClass(String classFullName) {
        this.classFullName = classFullName;
    }


    @Override
    public void emit(CodeEmitter emitter) {

        if(null != clazz) {
            emitter.emit(clazz.getSimpleName()).emit(".class");
        } else {
            String name;
            if(classFullName.contains(".")) {
                name = classFullName.substring(classFullName.lastIndexOf(".") + 1);
            } else {
                name = classFullName;
            }
            emitter.emit(name).emit(".class");
        }
    }

    @Override
    public void emitImport(CodeEmitter emitter) {
        if(null != clazz) {
            emitter.emit(clazz);
        } else if (null != classFullName) {
            emitter.emitImport(classFullName);
        }

    }
}
