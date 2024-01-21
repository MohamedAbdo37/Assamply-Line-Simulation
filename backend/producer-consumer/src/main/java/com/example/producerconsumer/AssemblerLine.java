package com.example.producerconsumer;

import java.util.ArrayList;

public class AssemblerLine {

    private ArrayList<Machine> Ms = new ArrayList<>();
    private ArrayList<Queue> Qs=new ArrayList<>();
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
}
