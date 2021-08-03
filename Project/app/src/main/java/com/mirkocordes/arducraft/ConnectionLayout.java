package com.mirkocordes.arducraft;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ConnectionLayout {
    TextView connectedText;
    Button connectingButton;
    TextView anleitungText;
    SeekBar ausrichtungBar;
    SeekBar leistungBar;
    TextView leistungAnzeige;
    TextView ausrichtungAnzeige;
    Switch blockadeSwitch;
    View divider;
    Button disconnectingButton;

    ConnectionLayout(TextView connectedText, Button connectingButton, TextView anleitungText, SeekBar ausrichtungBar, SeekBar leistungBar, TextView leistungAnzeige, TextView ausrichtungAnzeige, Switch blockadeSwitch, View divider, Button disconnectingButton){
        this.connectedText = connectedText;
        this.connectingButton = connectingButton;
        this.anleitungText = anleitungText;
        this.ausrichtungBar = ausrichtungBar;
        this.leistungBar = leistungBar;
        this.leistungAnzeige = leistungAnzeige;
        this.ausrichtungAnzeige = ausrichtungAnzeige;
        this.blockadeSwitch = blockadeSwitch;
        this.divider = divider;
        this.disconnectingButton = disconnectingButton;
    }

    public void connectedText(){
        //Bei Verbindung
        connectedText.setText("connected");
        connectedText.setTextColor(Color.GREEN);
        objekteSichtbar(true, 1);
    }

    public void error(){
        //Bei Verbindungsproblemen
        connectedText.setText("ERROR");
        connectedText.setTextColor(Color.RED);
        objekteSichtbar(false, 0);
    }

    public void connectingText(){
        //Klick auf verbinden
        connectedText.setText("connecting...");
        connectedText.setTextColor(Color.RED);
    }

    public void disconnectedText(){
        connectedText.setText("disconnected");
        connectedText.setTextColor(Color.RED);
        objekteSichtbar(false, 0);
    }

    //Sichtbarkeit der einzelnen Objekte einstellen
    public void objekteSichtbar(boolean b, int x){
        if(b == false){
            connectingButton.setEnabled(true);
            connectingButton.setAlpha(1);
            anleitungText.setEnabled(true);
            anleitungText.setAlpha(1);
            ausrichtungBar.setAlpha(0);
        } else {
            connectingButton.setEnabled(false);
            connectingButton.setAlpha(0);
            ausrichtungBar.setEnabled(false);
            ausrichtungBar.setAlpha(0.1f);
            anleitungText.setEnabled(false);
            anleitungText.setAlpha(0);
        }

        leistungBar.setEnabled(b);
        leistungBar.setAlpha(x);
        leistungAnzeige.setEnabled(b);
        leistungAnzeige.setAlpha(x);
        ausrichtungAnzeige.setEnabled(b);
        ausrichtungAnzeige.setAlpha(x);
        blockadeSwitch.setEnabled(b);
        blockadeSwitch.setAlpha(x);
        divider.setEnabled(b);
        divider.setAlpha(x);
        disconnectingButton.setEnabled(b);
        disconnectingButton.setAlpha(x);
    }
}
