package org.layout.core.javaopt.type;

import org.layout.core.javaopt.CodeEmitable;
import org.layout.core.javaopt.CodeEmitter;
import org.layout.core.javaopt.expr.BooleanJExpr;
import org.layout.core.javaopt.expr.TypeName;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.type
 * @className: ParameterSpec
 * @author: HuangGen
 * @description: 方法级别的
 * @date: 2022/8/30 10:17 AM
 * @version: 1.0
 */
public class ParameterSpec implements CodeEmitable {

    private String name;

    private TypeName type;

    private List<TypeName> generics;

    private Boolean varArgs;

    public ParameterSpec(Class<?> clazz, String name) {
        this.type = new TypeName(clazz);
        this.name = name;
    }

    public ParameterSpec(Class<?> clazz, String name, Boolean varArgs) {
        this.type = new TypeName(clazz);
        this.name = name;
        this.varArgs = varArgs;
    }

    public ParameterSpec(String classFullName, String name) {
        this.type = new TypeName(classFullName);
        this.name = name;
    }

    public ParameterSpec(String classFullName, String name, Boolean varArgs) {
        this.type = new TypeName(classFullName);
        this.name = name;
        this.varArgs = varArgs;
    }

    public ParameterSpec(String classFullName, List<String> genericsClassName, String name) {
        this.type = new TypeName(classFullName);
        if(genericsClassName != null) {
            this.generics = genericsClassName.stream().map(type -> {
                if(type.matches("^.*<.*>$")) {
                    String insideClass = type.substring(type.indexOf("<") + 1, type.indexOf(">"));
                    return new TypeName(type, new TypeName(insideClass));
                } else {
                    return new TypeName(type);
                }
            }).collect(Collectors.toList());
        }
        this.name = name;
    }

    public ParameterSpec(Class<?> typeClass, List<Class<?>> genericsClass, String name) {
        this.type = new TypeName(typeClass);
        if (null != genericsClass) {
            this.generics = genericsClass.stream().map(TypeName::new).collect(Collectors.toList());
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeName getType() {
        return type;
    }

    public void setType(TypeName type) {
        this.type = type;
    }

    public List<TypeName> getGenerics() {
        return generics;
    }

    public void setGenerics(List<TypeName> generics) {
        this.generics = generics;
    }

    public Boolean getVarArgs() {
        return varArgs;
    }

    public void setVarArgs(Boolean varArgs) {
        this.varArgs = varArgs;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        if(null == generics) {
            if(this.varArgs) {
                emitter.emit(type.getName() + "..." + name);
            } else {
                emitter.emit(type.getName() + " " + name);
            }
        } else {
            String joinedGeneric = generics.stream().map(TypeName::getName).collect(Collectors.joining(","));
            emitter.emit(type).emit("<").emit(joinedGeneric).emit(">").emit(name);
        }
    }

    @Override
    public void emitImport(CodeEmitter emitter) {
        type.emitImport(emitter);
        if(null != generics) {
            generics.forEach(generics -> generics.emitImport(emitter));
        }
    }
}
