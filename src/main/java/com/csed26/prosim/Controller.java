package com.csed26.prosim;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:8080")
public class Controller {

    private ArrayList<Machine> Ms = new ArrayList<>();
    AssemblerLine ass = new AssemblerLine();
    private ArrayList<Queue> Qs = new ArrayList<>();
    private CareTaker careTaker = new CareTaker();

    @GetMapping("/AddMs")
    public void addMs(@RequestParam String machineId) {
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

    @GetMapping("/connect")
    public void connect(@RequestParam String from, @RequestParam String to) {
        if(from.contains("M"))
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
        return ass.getMs();
    }

    @GetMapping("/getQueueStatus")
    public ArrayList<Queue> getQueueStatus() {
        return ass.getQs();
    }
}