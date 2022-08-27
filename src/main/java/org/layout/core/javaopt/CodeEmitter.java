package org.layout.core.javaopt;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt
 * @className: CodeEmitter
 * @author: HuangGen
 * @description: 代码组装器
 * @date: 2022/8/27 12:06 PM
 * @version: 1.0
 */
public class CodeEmitter {
    private static final String INDENT = "    ";
    private static final String JAVA_LANG_PKG = "java.lang";

    private Set<String> imports = new LinkedHashSet<>();
    private StringBuilder out = new StringBuilder();
    private int cursor = 0;

    public CodeEmitter emit(Class<?> clazz) {
        if(null != clazz.getPackage() && !JAVA_LANG_PKG.equals(clazz.getPackage().getName())) {
            imports.add(clazz.getName());
        }
        return this;
    }

    public CodeEmitter emitImport(String classFullName) {
        if(classFullName.startsWith(JAVA_LANG_PKG) || !classFullName.contains(".")) {
            return this;
        }
        imports.add(classFullName);
        return this;
    }

    public CodeEmitter emit(String text) {
        out.append(text);
        return this;
    }

    public CodeEmitter emit(char text) {
        out.append(text);
        return this;
    }

    public CodeEmitter emit(CodeEmitable emitable) {
        emitable.emit(this);
        return this;
    }

    public CodeEmitter emitLine(String text) {
        for(int i=0;i<this.cursor;i++) {
            out.append(INDENT);
        }
        out.append(text).append('\n');
        return this;
    }

    public CodeEmitter newLine() {
        return this.emit("\n");
    }

    public CodeEmitter indent() {
        cursor += 1;
        return this;
    }

    public CodeEmitter decIndent() {
        cursor -= 1;
        return this;
    }

    public Set<String> getImports() {
        return imports;
    }

    public String buildString() {
        return out.toString();
    }

    public CodeEmitter emitImport(CodeEmitable emitable) {
        if(null != emitable) {
            emitable.emitImport(this);
        }
        return this;
    }


}
