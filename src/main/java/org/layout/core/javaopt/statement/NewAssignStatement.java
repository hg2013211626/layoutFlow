package org.layout.core.javaopt.statement;

import org.layout.core.javaopt.CodeEmitter;
import org.layout.core.javaopt.expr.JavaExpr;
import org.layout.core.javaopt.expr.TypeName;
import org.layout.core.javaopt.expr.VariableJExpr;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.statement
 * @className: NewAssignStatement
 * @author: HuangGen
 * @description: New一个对象
 * @date: 2022/9/2 3:03 AM
 * @version: 1.0
 */
public class NewAssignStatement implements StatementSpec{

    private TypeName type;

    private TypeName genericType;

    private VariableJExpr left;

    private JavaExpr right;

    public NewAssignStatement(Class<?> clazz, String name, JavaExpr right) {
        this.type = new TypeName(clazz);
        this.left = new VariableJExpr(name);
        this.right = right;
    }

    public NewAssignStatement(Class<?> clazz, Class<?> genericClass, String name, JavaExpr right) {
        this.type = new TypeName(clazz);
        this.genericType = new TypeName(genericClass);
        this.left = new VariableJExpr(name);
        this.right = right;
    }

    public NewAssignStatement(String classFullName, String name, JavaExpr right) {
        this.type = new TypeName(classFullName);
        this.left = new VariableJExpr(name);
        this.right = right;
    }

    public String getName() {
        return left.getName();
    }

    public TypeName getType() {
        return type;
    }

    public void setType(TypeName type) {
        this.type = type;
    }

    public TypeName getGenericType() {
        return genericType;
    }

    public void setGenericType(TypeName genericType) {
        this.genericType = genericType;
    }

    public VariableJExpr getLeft() {
        return left;
    }

    public void setLeft(VariableJExpr left) {
        this.left = left;
    }

    public JavaExpr getRight() {
        return right;
    }

    public void setRight(JavaExpr right) {
        this.right = right;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        emitter.indent().emit(type);
        if(null != genericType) {
           emitter.emit('<').emit(genericType).emit('>');
        }
        emitter.emit(' ').emit(left).emit(" = ").emit(right).emit(";").newLine();
    }

    @Override
    public void emitImport(CodeEmitter emitter) {
        emitter.emitImport(type).emitImport(genericType).emitImport(left).emitImport(right);
    }
}
