package org.layout.core.javaopt.expr;

import org.layout.core.javaopt.CodeEmitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.expr
 * @className: CallMethodJExpr
 * @author: HuangGen
 * @description: 方法调用表达式
 * @date: 2022/8/27 2:53 AM
 * @version: 1.0
 */
public class CallMethodJExpr extends BaseJavaExpr{

    private String name;

    private List<JavaExpr> parameters = new ArrayList<>(0);

    public CallMethodJExpr(String name) {
        this.name = name;
    }

    public CallMethodJExpr(String name, JavaExpr ...parameters) {
        this.name = name;
        Collections.addAll(this.parameters, parameters);
    }

    public CallMethodJExpr addParameters(JavaExpr ...parameters) {
        Collections.addAll(this.parameters, parameters);
        return this;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        emitter.emit(name).emit('(');
        for(int i=0;i<parameters.size();i++) {
            if(i != 0) {
                emitter.emit(", ");
            }
            parameters.get(i).emit(emitter);
        }
        emitter.emit(')');
    }

    @Override
    public void emitImport(final CodeEmitter emitter) {
        parameters.forEach(e -> e.emitImport(emitter));
    }
}
