package com.example.producerconsumer;

import java.util.List;

public class Machine {
    private String name;
    private Queue postQueue;
    private List<Queue> preQueues;

    private long serviceTime;

    private Thread consumerThread;
    private Thread producerThread;

    public Machine(String name) {
        this.name = name;
        this.serviceTime = (int) (randomgenerate() * 1000);
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
