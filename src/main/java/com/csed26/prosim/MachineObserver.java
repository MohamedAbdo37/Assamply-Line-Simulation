package com.csed26.prosim;

public class MachineObserver implements IObserver {
    private String machineName;
    public MachineObserver(String machineName){
        this.machineName = machineName;
    }
    @Override
    public void update(AssemblerLine assemblerLine) {
        assemblerLine.setChange(true);
    }

}
