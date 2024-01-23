package com.csed26.prosim;

public class Q {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private int size;

    public int getSize() {
        return size;
    }
    public void setSize(int color) {
        this.size = color;
    }

    public Q(String name, int size){
        this.name = name;
        this.size = size;
    }

}
