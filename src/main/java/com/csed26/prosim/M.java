package com.csed26.prosim;

public class M {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private long state;

    public long getState() {
        return state;
    }
    public void setStates(long state) {
        this.state = state;
    }
    private String color;
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public M(String name, String color, long state){
        this.name = name;
        this.color = color;
        this.state = state;
    }
}
