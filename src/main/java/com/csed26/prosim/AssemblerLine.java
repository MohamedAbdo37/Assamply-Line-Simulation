package com.csed26.prosim;


import java.awt.*;
import java.util.ArrayList;

public class AssemblerLine {

    private SystemInput sys;
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

    public void startLine(String initialQueue) {
        Queue q = Qs.stream()
                .filter(queue -> initialQueue.equals(queue.getName()))
                .findAny()
                .orElse(null);
        if(q != null) {
            sys = new SystemInput(q, this);
            sys.play();
            for(Machine m : this.Ms) {
                m.startWork();
            }
        }
    }

    public void clear() {
        //sys.stopInput();
        for(Machine m : Ms) {
            m.stopWork(true);
            Ms.remove(m);
        }
        Qs.clear();
    }

    public void suspend() {
        for(Machine m : this.Ms) {
            m.suspend();
        }
    }

    public static void main(String[] args) {
        AssemblerLine ass = new AssemblerLine();
        ass.Ms.add(new Machine("M1", ass));
        ass.Ms.add(new Machine("M2", ass));
        ass.Ms.add(new Machine("M3", ass));
        ass.Ms.add(new Machine("M4", ass));
        ass.Ms.add(new Machine("M5", ass));


        ass.Qs.add(new Queue("Q0", ass));
        ass.Qs.add(new Queue("Q1", ass));
        ass.Qs.add(new Queue("Q2", ass));
        ass.Qs.add(new Queue("Q3", ass));

        SystemInput sys = new SystemInput(ass.Qs.get(0), ass);

        ass.connectQM("M1", "Q0");
        ass.connectQM("M2", "Q1");
        ass.connectQM("M3", "Q2");

        ass.connectMQ("M1", "Q1");
        ass.connectMQ("M2", "Q2");
        ass.connectMQ("M3", "Q3");

        ass.connectQM("M4", "Q0");
        ass.connectQM("M5", "Q0");

        ass.connectMQ("M4", "Q2");
        ass.connectMQ("M5", "Q3");

        ass.startLine("Q0");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ass.clear();
        for (Queue queue : ass.Qs) {
            System.out.print(queue.size() + " ");
        }
        System.out.println();
    }
}
