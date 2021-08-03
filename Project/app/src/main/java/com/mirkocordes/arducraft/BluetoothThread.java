package com.mirkocordes.arducraft;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothThread extends Thread{
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice bluetoothDevice;
    public BluetoothSocket bluetoothSocket;
    OutputStream outputStream;
    ConnectionLayout cLayout;
    Zusammenfassung blubb;
    static final UUID mUUID = UUID.fromString("0001101-0000-1000-8000-00805F9B34FB");

    BluetoothThread(Zusammenfassung b, ConnectionLayout connectionLayout){
        //Adapter auf dem Handy einstellen
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        blubb = b;
        this.cLayout = connectionLayout;
    }

    //Verbinden über startmethode
    public void run(){
            try {
                //Adresse des Arducraft zwischenspeichern
                this.bluetoothDevice = mBluetoothAdapter.getRemoteDevice("98:D3:71:F5:E7:AA");
                //über gemeinsamen Socket verbinden
                bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(mUUID);
                if(!bluetoothSocket.isConnected())
                    bluetoothSocket.connect();
                if (bluetoothSocket.isConnected()) {
                    outputStream = bluetoothSocket.getOutputStream(); //outputstream festlegen
                }
                //Verbunden ausgeben
                //connectionLayout.connectedText();

                outputStream = bluetoothSocket.getOutputStream(); //outputstream festlegen

            } catch (IOException e) {
                e.printStackTrace();
                //Verbindungsfehler
            }

    }

    long ergebnis;

    public void sendeDaten(long data){
        if(data != ergebnis && bluetoothSocket != null){
            ergebnis = data;

                String message = String.valueOf(data);
                try {
                    byte[] msgBuffer = message.getBytes();
                    outputStream.write(msgBuffer); //Werte zusammenfassen und an Bluetoothmodul senden
                } catch (IOException e) {
                    e.printStackTrace();
                }


            System.out.println("NEUER WERT: " + data);
        }
    }

    public void disconnect(){
        try {
            outputStream.write(0);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputStream = null;
        try {
            bluetoothSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bluetoothSocket = null;
        bluetoothDevice = null;
    }

    public void error(){
        bluetoothSocket = null;
        bluetoothDevice = null;
        outputStream = null;
    }


}
