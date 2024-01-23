package com.csed26.prosim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Machine {
    private AssemblerLine assemblerLine;

    private String name;
    private Queue postQueue;
    private List<Queue> preQueues;
    private Manager manager;
    private long serviceTime;
    private Item item;
    private Thread consumerThread;
    private Thread producerThread;

    private boolean stop = false;
    private String color;

    private boolean lock = false;


    public Machine(String name, AssemblerLine ass) {
        this.assemblerLine = ass;
        this.name = name;
        this.serviceTime = (int) (randomgenerate() * 1000);
        this.manager = Manager.getInstance();
        this.preQueues = new ArrayList<>();
        manager.addObserver(this.name, new MachineObserver(this.name));
        System.out.println(this.serviceTime);
    }
    public AssemblerLine getAssemblerLine(){
        return this.assemblerLine;
    }

    public void setPostQueue(Queue postQueue) {
        this.postQueue = postQueue;
        System.out.println("done assigning postqueue");
    }

    public void setPreQueues(Queue preQueue) {
        this.preQueues.add(preQueue);
        System.out.println("done assigning prequeue");
    }

    public String getName() {
        return this.name;
    }
    public long getServiceTime() {
        return serviceTime;
    }

    public String getColor() {
        return this.color;
    }
     public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void startWork() {
        stop = false;
        consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!consumerThread.isInterrupted()) {
                    //System.out.println(getName() +preQueues.size());
                    Queue preQueue = preQueues.remove(0);
                    preQueues.add(preQueue);
                    synchronized (preQueue) {
                        try {
                            while (preQueue.isEmpty()) {
                                preQueue.wait();
                            };

                            //System.out.println(preQueue.size() + " " + getName());
                            setItem(preQueue.dequeue(getAssemblerLine()));
                            color = item.getColor();
                            lock = true;
                            preQueue.wait();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if(stop) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!producerThread.isInterrupted()) {
                    //System.out.println(getName() +preQueues.size());
                    Queue preQueue = preQueues.remove(0);
                    preQueues.add(preQueue);
                    synchronized (preQueue) {
                        try {
                            while (lock && item != null) {
                                Thread.sleep(getServiceTime());
                                postQueue.enqueue(item, getAssemblerLine());
                                setItem(null);
                                lock = false;
                                preQueue.wait();
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if(stop) {
                        Thread.currentThread().interrupt();
                    }
                        //producerThread.interrupt();
                }
            }
        });
        consumerThread.start();
        producerThread.start();
    }

    public void stopWork(boolean interrupt) {
        stop = true;
    }

    private double randomgenerate() {
        double random = 1 + Math. random() * (5 - 1);
        return random;
    }

    public void suspend() {
//        consumerThread.suspend();
//        producerThread.suspend();
    }
}
