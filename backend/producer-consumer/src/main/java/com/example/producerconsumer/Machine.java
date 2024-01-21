package com.example.producerconsumer;

import java.util.List;

public class Machine {
    private AssemblerLine assemblerLine;
    private String name;
    private Queue postQueue;
    private Queue preQueue;
    private Manager manager;
    private long serviceTime;
    private Item item;
    private Thread consumerThread;
    private Thread producerThread;

    private boolean lock = false;

    public Machine(String name, AssemblerLine ass) {
        this.assemblerLine = ass;
        this.name = name;
        this.serviceTime = (int) (randomgenerate() * 1000);
        this.manager = Manager.getInstance();
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
        this.preQueue = preQueue;
        System.out.println("done assigning prequeue");
    }

    public String getName() {
        return this.name;
    }
    public long getServiceTime() {
        return serviceTime;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void startWork() {
        consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!consumerThread.isInterrupted()) {
                    synchronized (preQueue) {
                        try {
                            while (preQueue.isEmpty()) {
                                preQueue.wait();
                            };

                            //System.out.println(preQueue.size() + " " + getName());
                            setItem(preQueue.dequeue(getAssemblerLine()));
                            lock = true;
                            preQueue.wait();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!consumerThread.isInterrupted()) {
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
                }
            }
        });
        consumerThread.start();
        producerThread.start();
    }

    private double randomgenerate() {
        double random = 1 + Math. random() * (5 - 1);
        return random;
    }
}
