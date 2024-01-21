package com.example.producerconsumer;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class controller {

    private ArrayList<Machine> Ms = new ArrayList<>();
    AssemblerLine ass = new AssemblerLine();
    private ArrayList<Queue> Qs = new ArrayList<>();
    private CareTaker careTaker= new CareTaker();
    @GetMapping("/AddMs")
    public void addMs(@RequestParam String machineId){
        Ms.add(new Machine(machineId, ass));
        ass.setMs(Ms);
        ass.setQs(Qs);
        careTaker.Addline(ass);
    }

    @GetMapping("/AddQs")
    public void addQs(@RequestParam String queueId) {
        Qs.add(new Queue(queueId,ass));
        ass.setMs(Ms);
        ass.setQs(Qs);
        careTaker.Addline(ass);
    }
    @GetMapping("/replay")
    public ArrayList<AssemblerLine> replay(){
        ArrayList<AssemblerLine> lines = new ArrayList<>();
        for(int i=0; i < careTaker.GetSize();i++){
            careTaker.undo(i);
            lines.add(careTaker.Generatee.getAssembler());
        }
        return lines;
    }
    @GetMapping("/connect")
    public void connectMQ(@RequestParam String from, @RequestParam String to) {
        if(to.toCharArray()[0] == 'Q')
            ass.connectQM(from, to);
        else
            ass.connectMQ(from, to);
    }
}