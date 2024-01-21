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
    public Machine addMs(@RequestParam String machineId){
        Machine machine = new Machine(machineId);
        Ms.add(machine);
        //Qs.add(2);
        ass.setMs(Ms);
        ass.setQs(Qs);
        careTaker.Addline(ass);
        return machine;
      //  careTaker.undo(0);
      //  System.out.println(careTaker.Generatee.getAssembler().getMs());
    }

    @GetMapping("/AddQs")
    public Queue addQs(@RequestParam String queueId) {
        Queue queue = new Queue(queueId);
        Qs.add(queue);
        ass.setMs(Ms);
        ass.setQs(Qs);
        careTaker.Addline(ass);
        return queue;
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

    @GetMapping("/connectMachineQueue")
    public void connectMQ(@RequestParam String machineId, @RequestParam String queueId) {
        ass.connectMQ(machineId, queueId);
    }

    @GetMapping("/connectQueueMachine")
    public void connectQM(@RequestParam String machineId, @RequestParam String queueId) {
        ass.connectQM(machineId, queueId);
    }
}
