package com.csed26.prosim;

public class SystemInput {
    private Queue mainQueue;
    private AssemblerLine assemblerLine;
    public SystemInput(Queue mainQueue, AssemblerLine ass) {
        this.mainQueue = mainQueue;
        this.assemblerLine = ass;
    }

    public AssemblerLine getAssemblerLine() {
        return assemblerLine;
    }

    Thread mainThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!mainThread.isInterrupted()) {
                //synchronized (this) {
                    try {
                        Item item = new Item(randomHexGenerate());
                        mainQueue.enqueue(item, getAssemblerLine());
                        Thread.sleep(500);
                        for (Queue queue : assemblerLine.Qs) {
                            System.out.print(queue.size() + " ");
                        }
                        System.out.println();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                //}
            }
        }
    });

    public void play() {
        mainThread.start();
    }
    public String randomHexGenerate() {
        String letters = "0123456789ABCDEF";
        String color = "#";
        for (int i = 0; i < 6; i++)
            color += letters.charAt((int) Math.floor(Math.random() * 16));
        return color;
    }

    private double randomgenerate() {
        double random = 1 + Math. random() * (5 - 1);
        return random;
    }
}
