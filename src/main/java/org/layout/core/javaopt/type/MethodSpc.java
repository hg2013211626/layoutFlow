package org.layout.core.javaopt.type;

import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;
import org.layout.core.javaopt.CodeEmitable;
import org.layout.core.javaopt.CodeEmitter;
import org.layout.core.javaopt.expr.TypeName;
import org.layout.core.javaopt.statement.NewAssignStatement;
import org.layout.core.javaopt.statement.StatementSpec;

import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.type
 * @className: MethodSpc
 * @author: HuangGen
 * @description: 函数描述
 * @date: 2022/9/2 2:07 AM
 * @version: 1.0
 */
public class MethodSpc implements CodeEmitable {

    private List<Modifier> modifierList = new ArrayList<>();
    private TypeName returnType;
    private List<TypeName> returnGeneric;
    private String name;
    private List<ParameterSpec> parameterSpecs = new ArrayList<>(0);
    private List<StatementSpec> statementSpecs = new ArrayList<>();
    private List<AnnotationSpec> annotationSpecs = new ArrayList<>(0);
    private Set<String> variables = new HashSet<>();
    private CommentSpec commentSpec;

    public MethodSpc(String name) {
        this.name = name;
    }

    public MethodSpc addModifiers (Modifier... modifiers) {
        Collections.addAll(this.modifierList, modifiers);
        return this;
    }

    public MethodSpc addParameter(Class<?> clazz, String name) {
        ParameterSpec parameterSpec = new ParameterSpec(clazz, name);
        parameterSpecs.add(parameterSpec);
        variables.add(name);
        return this;
    }

    public MethodSpc addParameter(String classFullName, String name) {
        ParameterSpec parameterSpec = new ParameterSpec(classFullName, name);
        parameterSpecs.add(parameterSpec);
        variables.add(name);
        return this;
    }

    public MethodSpc addParameter(String classFullName, String genericClassName, String name) {
        ParameterSpec parameterSpec = new ParameterSpec(classFullName, null == genericClassName ? null : Collections.singletonList(genericClassName), name);
        parameterSpecs.add(parameterSpec);
        variables.add(name);
        return this;
    }

    public MethodSpc addParameter(Class<?> typeClass, Class<?> genericsClass, String name) {
        ParameterSpec parameterSpec = new ParameterSpec(typeClass, null == genericsClass ? null : Collections.singletonList(genericsClass), name);
        parameterSpecs.add(parameterSpec);
        variables.add(name);
        return this;
    }

    public MethodSpc addParameter(String classFullName, List<String> genericClassName, String name) {
        ParameterSpec parameterSpec = new ParameterSpec(classFullName,genericClassName,name);
        parameterSpecs.add(parameterSpec);
        variables.add(name);
        return this;
    }

    public MethodSpc addParameter(ParameterSpec... parameterSpecs) {
        Collections.addAll(this.parameterSpecs, parameterSpecs);
        this.parameterSpecs.forEach(parameterSpec -> variables.add(parameterSpec.getName()));

        for(ParameterSpec parameterSpec : parameterSpecs) {
            variables.add(parameterSpec.getName());
        }

        return this;
    }

    public MethodSpc returnType(Class<?> clazz) {
        returnType = new TypeName(clazz);
        return this;
    }

    public MethodSpc returnType(Class<?> clazz, Class<?> genericsClazz) {
        returnType = new TypeName(clazz);
        if(null != genericsClazz) {
            returnGeneric = Collections.singletonList(new TypeName(genericsClazz));
        }
        return this;
    }

    public MethodSpc returnType(String classFullName) {
        returnType = new TypeName(classFullName);
        return this;
    }

    public MethodSpc returnType(String classFullName, String genericsClassName) {
        returnType = new TypeName(classFullName);
        if(null != genericsClassName) {
            returnGeneric = Collections.singletonList(new TypeName(genericsClassName));
        }
        return this;
    }

    public MethodSpc returnType(TypeName type, List<TypeName> generics) {
        returnType = type;
        returnGeneric = generics;
        return this;
    }

    public MethodSpc addStatements(StatementSpec... statementSpecs) {
        Collections.addAll(this.statementSpecs, statementSpecs);
        for(StatementSpec statementSpec : statementSpecs) {
            if(statementSpec instanceof NewAssignStatement) {
                variables.add(((NewAssignStatement)statementSpec).getName());
            }
        }
        return this;
    }

    public MethodSpc addStatements(List<StatementSpec> statementSpecs) {
        this.statementSpecs.addAll(statementSpecs);
        for(StatementSpec statementSpec : statementSpecs) {
            if(statementSpec instanceof NewAssignStatement) {
                variables.add(((NewAssignStatement)statementSpec).getName());
            }
        }
        return this;
    }

    public MethodSpc addAnnotation(Class<?> clazz) {
        this.annotationSpecs.add(new AnnotationSpec(clazz));
        return this;
    }

    public List<StatementSpec> getStatementSpecs() {
        return statementSpecs;
    }

    public Boolean hasVariable(String name) {
        return variables.contains(name);
    }

    public String getName() {
        return name;
    }

    public MethodSpc setComment(String comment) {
        this.commentSpec = new CommentSpec(comment);
        return this;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        if(this.commentSpec != null) {
            this.commentSpec.emit(emitter);
        }

        emitter.newLine();
        annotationSpecs.forEach(emitter::emit);
        emitter.indent();
        modifierList.forEach(modifier -> emitter.emit(modifier.toString()).emit(" "));

        if(null == returnType) {
            emitter.emit("void");
        } else {
            if(null == returnGeneric || returnGeneric.isEmpty()) {
                emitter.emit(returnType.getName());
            } else {
                String joinedGeneric = returnGeneric.stream().filter(Objects::isNull).map(TypeName::getName).collect(Collectors.joining(","));
                if(!joinedGeneric.isEmpty()) {
                    emitter.emit(returnType).emit("<").emit(joinedGeneric).emit(">");
                } else {
                    emitter.emit(returnType.getName());
                }
            }
        }

        emitter.emit(" ").emit(name).emit("(");

        for(int i=0;i<parameterSpecs.size(); i++) {
            if(i != 0) {
                emitter.emit(", ");
            }
            parameterSpecs.get(i).emit(emitter);
        }

        emitter.emit(") {").newLine();

        emitter.incIndent();
        statementSpecs.forEach(statementSpec -> statementSpec.emit(emitter));
        emitter.decIndent();

        emitter.emitLine("}");

    }

    @Override
    public void emitImport(CodeEmitter emitter) {

        if(null != returnType) {
            returnType.emitImport(emitter);
        }

        if(null != returnGeneric) {
            returnGeneric.forEach(generic -> generic.emitImport(emitter));
        }

        for (AnnotationSpec annotation : this.annotationSpecs) {
            emitter.emitImport(annotation);
        }

        parameterSpecs.forEach(emitter::emitImport);

        for(StatementSpec statementSpec:statementSpecs) {
            emitter.emitImport(statementSpec);
        }

    }
}
