package com.example.producerconsumer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class controller {

    private ArrayList<Integer> Ms=new ArrayList<>();
    AssemblerLine ass=new AssemblerLine();
    private ArrayList<Integer> Qs=new ArrayList<>();
    private CareTaker careTaker= new CareTaker();
    @GetMapping("/AddMs")
    public void addMs(@RequestParam int m){
        Ms.add(m);
        Qs.add(2);
        ass.setMs(Ms);
        ass.setQs(Qs);
        careTaker.Addline(ass);
      //  careTaker.undo(0);
      //  System.out.println(careTaker.Generatee.getAssembler().getMs());
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
}
