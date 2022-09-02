package org.layout.core.javaopt.type;

import org.layout.core.javaopt.CodeEmitable;
import org.layout.core.javaopt.CodeEmitter;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.type
 * @className: CommentSpec
 * @author: HuangGen
 * @description: 注释
 * @date: 2022/9/2 2:18 AM
 * @version: 1.0
 */
public class CommentSpec implements CodeEmitable {

    private String comment;

    private String date;

    public CommentSpec(String comment) {
        this.comment = comment;
    }

    public CommentSpec(String comment, String date) {
        this.comment = comment;
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public void emit(CodeEmitter emitter) {
        if(null != this.comment) {
            emitter.newLine();
            emitter.indent();
            emitter.emit("/**");
            emitter.newLine();
            emitter.indent();
            emitter.emit(String.format("* %s", this.comment));
            if(null != this.date) {
                emitter.newLine();
                emitter.indent();
                emitter.emit(String.format("* @since %s", this.date));
            }
            emitter.newLine();
            emitter.indent();
            emitter.emit("*/");
        }
    }

    @Override
    public void emitImport(CodeEmitter emitter) {

    }
}
