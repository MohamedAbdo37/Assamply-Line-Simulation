package com.example.producerconsumer;

import java.awt.*;
import java.util.ArrayList;

public class AssemblerLine {

    public ArrayList<Machine> Ms = new ArrayList<>();
    public ArrayList<Queue> Qs=new ArrayList<>();
    private boolean Change = false;

    public void flipChange(){
        this.Change = !this.Change;
    }
    public void setChange(boolean change){
        this.Change = change;
    }
    public boolean getChange(){
        return this.Change;
    }
    public void setMs(ArrayList<Machine> m) {
       this.Ms = m;
    }

    public ArrayList<Machine> getMs() {
        return Ms;
    }

    public ArrayList<Queue> getQs() {
        return Qs;
    }

    public void setQs(ArrayList<Queue> q) {
        this.Qs = q;
    }


    public void connectMQ(String machineId, String queueId) {
        Machine m = Ms.stream()
                .filter(machine -> machineId.equals(machine.getName()))
                .findAny()
                .orElse(null);
        Queue q = Qs.stream()
                .filter(queue -> queueId.equals(queue.getName()))
                .findAny()
                .orElse(null);

        if (m != null) {
            m.setPostQueue(q);
        }
    }

    public void connectQM(String machineId, String queueId) {
        Machine m = Ms.stream()
                .filter(machine -> machineId.equals(machine.getName()))
                .findAny()
                .orElse(null);
        Queue q = Qs.stream()
                .filter(queue -> queueId.equals(queue.getName()))
                .findAny()
                .orElse(null);

        if (m != null) {
            m.setPreQueues(q);
        }
    }

    public static void main(String[] args) {
        AssemblerLine ass = new AssemblerLine();
        ass.Ms.add(new Machine("M1", ass));
        ass.Ms.add(new Machine("M2", ass));
        ass.Ms.add(new Machine("M3", ass));

        ass.Qs.add(new Queue("Q0", ass));
        ass.Qs.add(new Queue("Q1", ass));
        ass.Qs.add(new Queue("Q2", ass));
        ass.Qs.add(new Queue("Q3", ass));

        ass.connectQM("M1", "Q0");
        ass.connectQM("M2", "Q1");
        ass.connectQM("M3", "Q2");

        ass.connectMQ("M1", "Q1");
        ass.connectMQ("M2", "Q2");
        ass.connectMQ("M3", "Q3");

        ass.Qs.get(0).enqueue(new Item("I", "red"), ass);

        for(Machine m : ass.Ms) {
            m.startWork();
        }

    }
}
