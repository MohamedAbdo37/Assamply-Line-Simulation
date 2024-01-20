package com.example.producerconsumer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class CareTaker {

    private final ArrayList<SnapShot> store=new ArrayList<>() ;
    public final Generate Generatee=new Generate();

    public void Addline( AssemblerLine line){
        Generatee.setAssembler(line);
        SnapShot s =Generatee.takeSnapshot();
        store.add(s);
    }
    public void undo(int i){
         if(store.isEmpty( )){
             return;
         }
        Generatee.restore(store.get(i));
    }
    public int GetSize(){
        return store.size();
    }
    public void ClearHistory(){
        store.clear();
    }
}