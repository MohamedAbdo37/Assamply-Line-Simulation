package com.example.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Queue {
    private AssemblerLine assemblerLine;
    private String name;
    private Manager manager;
    private List<Item> queue;

    public Queue(String name,AssemblerLine ass) {
        this.name = name;
        queue = new ArrayList<>();
        this.assemblerLine=ass;
        this.manager = Manager.getInstance();
        this.manager.addObserver(this.name, new QueueObserver());
    }
    public AssemblerLine getAssemblerLine(){
        return this.assemblerLine;
    }

    public String getName() {
        return this.name;
    }
    public synchronized void enqueue(Item item , AssemblerLine assemblerLine) {
        synchronized (this) {
            queue.add(item);
            this.manager.notify(this.name, assemblerLine);
            this.notify();
        }

    }

    public Item dequeue(AssemblerLine assemblerLine) throws InterruptedException {
        synchronized (this) {
            while(this.queue.size() == 0) this.wait();
            this.manager.notify(this.name, assemblerLine);
            Item item = queue.remove(0);
            return item;
        }
    }

    public int size() {
        return queue.size();
    }
    public boolean isEmpty() {
        return queue.size() == 0;
    }
}
