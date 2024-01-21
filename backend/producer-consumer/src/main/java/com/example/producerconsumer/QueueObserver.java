package com.example.producerconsumer;

public class QueueObserver implements IObserver{
    @Override
    public void update(AssemblerLine assemblerLine) {
        assemblerLine.setChange(true);
    }
    
}
