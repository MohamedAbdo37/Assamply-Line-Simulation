package com.csed26.prosim;

import java.util.ArrayList;

public class SnapShot {
    private AssemblerLine line;

    public SnapShot(AssemblerLine line) {
        this.line = new AssemblerLine();
        this.line.setMs(new ArrayList<>(line.getMs()));  // Copy the 'Ms' list
        this.line.setQs(new ArrayList<>(line.getQs()));  // Copy the 'Qs' list
    }

    public AssemblerLine getLine() {
        return this.line;
    }
}