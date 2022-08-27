package org.layout.core.javaopt.model;

/**
 * @projectName: layoutFlow
 * @package: org.layout.core.javaopt.model
 * @className: OperationEnum
 * @author: HuangGen
 * @description: 操作符枚举
 * @date: 2022/8/27 4:07 AM
 * @version: 1.0
 */
public enum OperatorEnum {
    GT(">","大于"),
    GE(">=", "大于等于"),
    LT("<", "小于"),
    LE("<=","小于等于"),
    EQ("=","等于");

    private String operator;

    private String description;

    OperatorEnum(String operator, String description) {
        this.operator = operator;
        this.description = description;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
