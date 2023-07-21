package com.hf.menu.utils;

public enum ActionTypeEnum {

    //0查看 1创建 2更新 3删除
    query(0, "查看"),
    create(1, "创建"),
    update(2, "更新"),
    delete(3, "删除");


    private int index;
    private String name;

    // 构造方法
    ActionTypeEnum(int index, String name) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (ActionTypeEnum c : ActionTypeEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
