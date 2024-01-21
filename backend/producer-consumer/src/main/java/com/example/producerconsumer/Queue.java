package com.example.producerconsumer;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Queue {

    private String name;
    private Manager manager;
    private List<Item> queue;

    public Queue(String name) {
        this.name = name;
        this.manager = Manager.getInstance();
        this.manager.addObserver(this.name, new QueueObserver());
    }

    public String getName() {
        return this.name;
    }
    public void enqueue(Item item , AssemblerLine assemblerLine) {
        queue.add(item);
        this.manager.notify(this.name, assemblerLine);
        this.notify();
    }

    public Item dequeue(AssemblerLine assemblerLine) {
        this.manager.notify(this.name, assemblerLine);
        Item item = queue.remove(0);
        return item;
    }

    public boolean isEmpty() {
        if(queue.size() == 0) {
            return true;
        }
        return false;
    }
}
