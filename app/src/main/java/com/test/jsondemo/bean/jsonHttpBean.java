package com.test.jsondemo.bean;

/**
 * Created by SoleilLun on 2015/12/25.
 */
public class jsonHttpBean {
    private String name;
    private int id;
    private int version;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }
}
