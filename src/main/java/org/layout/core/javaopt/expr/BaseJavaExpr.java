package org.layout.core.javaopt.expr;

import org.layout.core.javaopt.CodeEmitter;
import org.layout.core.javaopt.model.OperatorEnum;
import org.layout.core.javaopt.statement.AssignStatement;
import org.layout.core.javaopt.statement.ExprStatement;
import org.layout.core.javaopt.statement.StatementSpec;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.expr
 * @className: BaseJavaExpr
 * @author: HuangGen
 * @description: JAVA表达式基类，发布通用的表达式方法
 * @date: 2022/8/27 1:49 AM
 * @version: 1.0
 */
public abstract class BaseJavaExpr implements JavaExpr{

    public PointerJExpr child(String child) {
        return new PointerJExpr(this,new VariableJExpr(child));
    }

    public PointerJExpr call(String name, JavaExpr ...parameters) {
        CallMethodJExpr call = new CallMethodJExpr(name);
        call.addParameters(parameters);
        return new PointerJExpr(this, call);
    }

    public PointerJExpr put(String name,JavaExpr expr) {
        CallMethodJExpr call = new CallMethodJExpr("put");
        call.addParameters(new StringJExper(name),expr);
        return new PointerJExpr(this,call);
    }

    public PointerJExpr get(String name) {
        CallMethodJExpr call = new CallMethodJExpr("get");
        call.addParameters(new StringJExper(name));
        return new PointerJExpr(this, call);
    }

    public AssignStatement assign(JavaExpr left) {
        return new AssignStatement(this, left);
    }

    public StatementSpec end() {
        return new ExprStatement(this);
    }

    public OperatorJExpr GT(JavaExpr right) {
        return new OperatorJExpr(this, OperatorEnum.GT.getOperator(), right);
    }

    public OperatorJExpr GE(JavaExpr right) {
        return new OperatorJExpr(this, OperatorEnum.GE.getOperator(), right);
    }

    public OperatorJExpr LT(JavaExpr right) {
        return new OperatorJExpr(this, OperatorEnum.LT.getOperator(), right);
    }

    public OperatorJExpr LE(JavaExpr right) {
        return new OperatorJExpr(this, OperatorEnum.LE.getOperator(), right);
    }

    public OperatorJExpr EQ(JavaExpr right) {
        return new OperatorJExpr(this, OperatorEnum.EQ.getOperator(), right);
    }

    public PointerJExpr dot(JavaExpr right) {
        return new PointerJExpr(this, right);
    }

    @Override
    public String toString() {
        CodeEmitter emitter = new CodeEmitter();
        this.emit(emitter);
        return emitter.buildString();
    }


}
