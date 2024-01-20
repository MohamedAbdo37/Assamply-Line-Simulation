package com.example.producerconsumer;

import java.util.ArrayList;

public class AssemblerLine {

    private ArrayList<Integer> Ms=new ArrayList<>();
    private ArrayList<Integer> Qs=new ArrayList<>();


    public void setMs(ArrayList<Integer> m) {
       this.Ms = m;
    }

    public ArrayList<Integer> getMs() {
        return Ms;
    }

    public ArrayList<Integer> getQs() {
        return Qs;
    }

    public void setQs(ArrayList<Integer> q) {
        this.Qs = q;
    }


}
