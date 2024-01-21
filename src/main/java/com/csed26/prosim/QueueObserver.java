package com.csed26.prosim;

public class QueueObserver implements IObserver{
    @Override
    public void update(AssemblerLine assemblerLine) {
        assemblerLine.setChange(true);
    }

}
