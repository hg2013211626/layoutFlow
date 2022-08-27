package org.layout.core.javaopt;

public interface CodeEmitable {

    void emit(CodeEmitter emitter);

    void emitImport(CodeEmitter emitter);

}
