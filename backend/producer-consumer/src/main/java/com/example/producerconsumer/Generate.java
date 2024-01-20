package com.example.producerconsumer;

import java.util.ArrayList;

public class Generate {

    private AssemblerLine Assembler =new AssemblerLine();

    public void setAssembler(AssemblerLine assembler){this.Assembler=assembler;}

    public AssemblerLine getAssembler(){
        return this.Assembler;
    }

    public void restore(SnapShot snap) {
        this.Assembler = snap != null ? snap.getLine() : null;
    }

    public SnapShot takeSnapshot(){
        SnapShot m = new SnapShot(this.Assembler);
        return m;
    }


}