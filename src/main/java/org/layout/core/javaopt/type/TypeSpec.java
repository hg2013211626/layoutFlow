package org.layout.core.javaopt.type;

import org.layout.core.javaopt.CodeEmitable;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.type
 * @className: TypeSpec
 * @author: HuangGen
 * @description: 接口或者类的描述对象接口
 * @date: 2022/8/27 5:10 AM
 * @version: 1.0
 */
public interface TypeSpec extends CodeEmitable {

    String getName();

    void setName(String name);

}
