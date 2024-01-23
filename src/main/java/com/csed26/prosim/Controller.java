package com.csed26.prosim;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class Controller {

    private ArrayList<Machine> Ms = new ArrayList<>();
    AssemblerLine ass = new AssemblerLine();
    private ArrayList<Queue> Qs = new ArrayList<>();
    private CareTaker careTaker = new CareTaker();

    @GetMapping("/AddMs")
    public void addMs(@RequestParam String machineId) {
        System.out.println("AddMs");
        Ms.add(new Machine(machineId, ass));
        ass.setMs(Ms);
        ass.setQs(Qs);
        careTaker.Addline(ass);
    }

    @GetMapping("/AddQs")
    public void addQs(@RequestParam String queueId) {
        Qs.add(new Queue(queueId, ass));
        ass.setMs(Ms);
        ass.setQs(Qs);
        careTaker.Addline(ass);
    }

    @GetMapping("/replay")
    public ArrayList<AssemblerLine> replay() {
        ArrayList<AssemblerLine> lines = new ArrayList<>();
        for (int i = 0; i < careTaker.GetSize(); i++) {
            careTaker.undo(i);
            lines.add(careTaker.Generatee.getAssembler());
        }
        return lines;
    }

    @GetMapping("/Ms")
    public ArrayList<M> getMs() {
        ArrayList<Machine> Ms = new ArrayList<>();
        careTaker.undo(careTaker.GetSize() - 1);    
        Ms = careTaker.Generatee.getAssembler().getMs();
        ArrayList<M> ms = new ArrayList<>();
        for (Machine M : Ms) {
            ms.add(new M(M.getName(), M.getColor(), M.getServiceTime()));
        }
        return ms;
    }

    @GetMapping("/Qs")
    public ArrayList<Q> getQs() {
        ArrayList<Queue> Qs = new ArrayList<>();
        careTaker.undo(careTaker.GetSize() - 1);    
        Qs = careTaker.Generatee.getAssembler().getQs();
        ArrayList<Q> qs = new ArrayList<>();
        for (Queue q : Qs) {
            qs.add(new Q(q.getName(), q.getSize()));
        }
        return qs;
    }

    @GetMapping("/connect")
    public void connect(@RequestParam String from, @RequestParam String to) {
        if (from.contains("M"))
            ass.connectMQ(from, to);
        else
            ass.connectQM(to, from);
    }

    @GetMapping("/play")
    public void play(@RequestParam String initialQueue) {
        ass.startLine(initialQueue);
    }

    @GetMapping("/getMachineStatus")
    public ArrayList<Machine> getMachineStatus() {
        System.out.println("fetching machines");
        return ass.getMs();
    }

    @GetMapping("/getQueueStatus")
    public ArrayList<Queue> getQueueStatus() {
        System.out.println("fetching queues");
        return ass.getQs();
    }
}