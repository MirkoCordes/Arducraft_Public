package com.mirkocordes.arducraft;

public class Zusammenfassung {
    long ausrichtung, leistung;
    long ergebnis;
    boolean flag = false;
    boolean changed = false;

    Ausrichtung ausrichtungObjekt;
    Leistung leistungObjekt;
    BluetoothThread bluetoothThread;

    public Zusammenfassung(Leistung l, Ausrichtung a, BluetoothThread b){
        //TODO: Leistung und Ausrichtung Objekte in NewThread erstellen
        this.leistungObjekt = l;
        this.ausrichtungObjekt = a;
        this.bluetoothThread = b;
    }

    //Leistung oder Ausrichtung filtern
    public synchronized void setLeistung(){
        if(flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(changed) {
            try {
                wait(102);
                changed = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            if(leistungObjekt.getLeistung() != this.leistung && !changed){
                this.leistung = leistungObjekt.getLeistung() * 1000;
                ergebnis = leistung + ausrichtung;
                bluetoothThread.sendeDaten(ergebnis);
                changed = true;
            }

            flag = true;
        notify();
    }

    public synchronized void setAusrichtung(){
        if(!flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(changed) {
            try {
                wait(102);
                changed = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(ausrichtungObjekt.getAusrichtung() != this.ausrichtung && !changed) {
            this.ausrichtung = ausrichtungObjekt.getAusrichtung();
            ergebnis = leistung + ausrichtung;
            bluetoothThread.sendeDaten(ergebnis);
            changed = true;
        }
        flag =false;
        notify();
    }
}
