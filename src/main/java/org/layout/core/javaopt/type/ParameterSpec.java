package org.layout.core.javaopt.type;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.layout.core.javaopt.CodeEmitable;
import org.layout.core.javaopt.CodeEmitter;
import org.layout.core.javaopt.expr.TypeName;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * @Author : Huang Gen
 * @Since : Created in 2022/8/29 8:14
 * @Description : 函数参数 描述对象
 */
public class ParameterSpec implements CodeEmitable {

    private String name;

    private TypeName type;

    private List<TypeName> generics;

    private Boolean varArgs;

    public ParameterSpec(Class<?> clazz, String name)  {
        this.type = new TypeName(clazz);
        this.name = name;
    }

    public ParameterSpec(Class<?> clazz, String name, Boolean varArgs) {
        this.type = new TypeName(clazz);
        this.name = name;
        this.varArgs = varArgs;
    }

    public ParameterSpec(String classFullName, String name){
        this.type = new TypeName(classFullName);
        this.name =  name;
    }

    public ParameterSpec(String classFullName, String name, Boolean varArgs) {
        this.type = new TypeName(classFullName);
        this.name = name;
        this.varArgs = varArgs;
    }

    public ParameterSpec(String classFullName, List<String> genericsClassNames, String name) {
        this.type = new TypeName(classFullName);
        if(genericsClassNames != null) {
            this.generics = genericsClassNames.stream().map(type -> {
                if(type.matches("^.*<.*>$")) {
                    String insideClass  = type.substring(type.indexOf("<") + 1, type.indexOf(">"));
                    return new TypeName(type, new TypeName(insideClass));
                } else {
                    return new TypeName(type);
                }
            }).collect(Collectors.toList());
        }
        this.name  = name;
    }

    public ParameterSpec(Class<?> typeClass, List<Class<?>> genericsClass, String name) {
        this.type = new TypeName(typeClass);
        if(genericsClass != null) {
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

    @Override
    public void emit(CodeEmitter emitter) {
        if(null == generics) {
            if(this.varArgs) {
                emitter.emit(type.getName() + "... " + name);
            } else {
                emitter.emit(type.getName() + " " + name);
            }
        } else {
            String joinedGeneric = generics.stream().map(TypeName::getName).collect(Collectors.joining(","));
            emitter.emit(type).emit("<").emit(joinedGeneric).emit("> ").emit(name);
        }
    }

    @Override
    public void emitImport(CodeEmitter emitter) {
        type.emitImport(emitter);
        if((null != generics)) {
            generics.forEach(generics -> generics.emitImport(emitter));
        }
    }
}
