package com.example.producerconsumer;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Queue {

    private String name;
    private List<Item> queue;

    public Queue(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public void enqueue(Item item) {
        queue.add(item);
    }

    public Item dequeue() {
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
