package org.layout.core.javaopt.type;

import org.layout.core.javaopt.CodeEmitable;
import org.layout.core.javaopt.CodeEmitter;
import org.layout.core.javaopt.expr.TypeName;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private List<Modifier> modifiers = new ArrayList<>();
    private CommentSpec commentSpec;
    private List<AnnotationSpec> annotationSpecs = new ArrayList<>(0);

    public FieldSpec(TypeName type,  String name) {
        this.type = type;
        this.name = name;
    }

    public FieldSpec(TypeName type,  TypeName generics, String name) {
        this.type= type;
        this.generics = Collections.singletonList(generics);
        this.name = name;
    }

    public FieldSpec(TypeName type, List<TypeName> generics, String name){
        this.type = type;
        this.generics =  generics;
        this.name =  name;
    }

    public FieldSpec setComment(String comment) {
        this.commentSpec = new CommentSpec(comment);
        return this;
    }

    public FieldSpec addModifiers(Modifier... modifiers) {
        Collections.addAll(this.modifiers, modifiers);
        return this;
    }

    public FieldSpec addAnnotation(String classFullName)  {
        this.annotationSpecs.add(new AnnotationSpec(classFullName));
        return this;
    }

    public FieldSpec addAnnotation(String clazz, String content) {
        this.annotationSpecs.add(new AnnotationSpec(clazz, content));
        return this;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Modifier> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<Modifier> modifiers) {
        this.modifiers = modifiers;
    }

    public CommentSpec getCommentSpec() {
        return commentSpec;
    }

    public void setCommentSpec(CommentSpec commentSpec) {
        this.commentSpec = commentSpec;
    }

    public List<AnnotationSpec> getAnnotationSpecs() {
        return annotationSpecs;
    }

    public void setAnnotationSpecs(List<AnnotationSpec> annotationSpecs) {
        this.annotationSpecs = annotationSpecs;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        if(null != this.commentSpec) {
            this.commentSpec.emit(emitter);
        }
        emitter.newLine();
        annotationSpecs.forEach(emitter::emit);
        emitter.indent();
        modifiers.forEach(modifier -> {
            emitter.emit(modifier.toString()).emit(" ");
        });

        if(null == generics || generics.isEmpty()) {
            emitter.emit(type.getName() + " " + name).emit(";");
        } else {
            String joinedGeneric = generics.stream().filter(Objects::nonNull).map(TypeName::getName).collect(Collectors.joining(","));
            if(!joinedGeneric.isEmpty())  {
                emitter.emit(type).emit("<").emit(joinedGeneric).emit("> ").emit(name).emit(";");
            } else {
                emitter.emit(type.)
            }
        }

    }

    @Override
    public void emitImport(CodeEmitter emitter) {

    }
}
