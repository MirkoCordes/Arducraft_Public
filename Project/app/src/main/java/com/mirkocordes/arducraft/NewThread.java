package com.mirkocordes.arducraft;

public class NewThread extends Thread{
    boolean flag;
    Zusammenfassung blubb;

    public NewThread(boolean f, Zusammenfassung b){
        this.flag = f;
        this.blubb = b;
    }

    public void run(){
        while (true){
            if(flag){
                blubb.setLeistung();
            } else {
                blubb.setAusrichtung();
            }
        }

    }
}
