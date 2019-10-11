package com.example.responsibilityhome.Model;

public class DataElement {
    String name;
    boolean isProvide;

    public DataElement(String name, boolean isProvide) {
        this.name = name;
        this.isProvide = isProvide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isProvide() {
        return isProvide;
    }

    public void setProvide(boolean provide) {
        isProvide = provide;
    }
}