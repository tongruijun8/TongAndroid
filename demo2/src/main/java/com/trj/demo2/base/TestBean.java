package com.trj.demo2.base;

/**
*
* 作者：小童
* 创建时间：2019/3/7 16:11
*
* 描述：测试类型的bean
*
*/
public class TestBean {

    private String name;

    private Class<?> clazz;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
