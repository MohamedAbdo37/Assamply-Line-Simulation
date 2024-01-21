package com.example.producerconsumer;

import java.util.List;

public class Machine {
    private AssemblerLine assemblerLine;
    private String name;
    private Queue postQueue;
    private List<Queue> preQueues;
    private Manager manager;
    private long serviceTime;

    private Thread consumerThread;
    private Thread producerThread;

    public Machine(String name,AssemblerLine ass) {
        this.assemblerLine=ass;
        this.name = name;
        this.serviceTime = (int) (randomgenerate() * 1000);
        this.manager = Manager.getInstance();
        manager.addObserver(this.name, new MachineObserver(this.name));
    }
    public AssemblerLine getAssemblerLine(){
        return this.assemblerLine;
    }

    public void setPostQueue(Queue postQueue) {
        this.postQueue = postQueue;
    }

    public void setPreQueues(Queue preQueue) {
        this.preQueues.add(preQueue);
    }

    public String getName() {
        return this.name;
    }

    public void startWork() {

    }

    private double randomgenerate() {
        double random = 1 + Math. random() * (5 - 1);
        return random;
    }
}
