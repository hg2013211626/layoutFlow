package org.layout.core.javaopt.expr;

import org.layout.core.javaopt.CodeEmitable;
import org.layout.core.javaopt.CodeEmitter;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.expr
 * @className: TypeName
 * @author: HuangGen
 * @description: 类型名封装
 * @date: 2022/8/27 4:14 AM
 * @version: 1.0
 */
public class TypeName extends BaseJavaExpr implements CodeEmitable {
    private Class<?> clazz;
    private String classFullName;
    private String name;
    private TypeName insideType;

    public static TypeName build(Class<?> clazz) {
        return new TypeName(clazz);
    }

    public TypeName(Class<?> clazz) {
        this.clazz = clazz;
        this.classFullName = clazz.getName();
        this.name = clazz.getSimpleName();
    }

    public TypeName(String classFullName) {
        this.classFullName = removeGeneric(classFullName);
        if(classFullName.indexOf('.') > 0) {
            this.name = classFullName.substring(classFullName.lastIndexOf('.') + 1);
        } else {
            this.name = classFullName;
        }
    }

    public TypeName(String classFullName, TypeName insideType) {
        this.classFullName = removeGeneric(classFullName);
        this.insideType = insideType;
        if(classFullName.indexOf(".") > 0) {
            this.name = classFullName.substring(classFullName.lastIndexOf('.') + 1);
        } else {
            this.name = classFullName;
        }
    }

    private String removeGeneric(String classFullName) {
        if(classFullName.matches("^.*<.*>$")) {
            return classFullName.substring(0, classFullName.indexOf("<"));
        }
        return classFullName;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeName getInsideType() {
        return insideType;
    }

    public void setInsideType(TypeName insideType) {
        this.insideType = insideType;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        if(null != insideType) {
            emitter.emit(name).emit("<").emit(insideType).emit(">");
        } else {
            emitter.emit(name);
        }

    }

    @Override
    public void emitImport(CodeEmitter emitter) {
        if(null != clazz) {
            emitter.emit(clazz);
        }else if(null != classFullName) {
            emitter.emitImport(classFullName);
        }
        if(null != insideType) {
            emitter.emitImport(insideType);
        }
    }

    public static void main(String[] args) {
        TypeName typeName = new TypeName("com.alibaba.Test");
        System.out.println(typeName.getName() + ":" +typeName.getClassFullName());
        System.out.println(int.class.getName());
    }


}
