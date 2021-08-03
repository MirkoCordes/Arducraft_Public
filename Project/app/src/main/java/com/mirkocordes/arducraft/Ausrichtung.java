package com.mirkocordes.arducraft;

import android.os.Build;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class Ausrichtung {
    private SeekBar ausrichtungBar;
    private Switch ausrichtungBlockSwitch;
    private TextView ausrichtungsAnzeige;

    long ausrichtung;



    public Ausrichtung(SeekBar ausrichtungBar, Switch ausrichtungBlockSwitch, TextView ausrichtungsAnzeige){
        //localen Attribute einstellen
        setAusrichtungBar(ausrichtungBar);
        setAusrichtungBlockSwitch(ausrichtungBlockSwitch);
        setAusrichtungsAnzeige(ausrichtungsAnzeige);
        //Ausrichtung auf 0 und sperren
        blockSwitchChecked(true);

        //bluetoothThread = new BluetoothThread();

        //Warte auf Änderungen der "SeekBar"
        ausrichtungBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //Wenn der "progress"-Wert verändert wurde, bitte "aendereAusrichtung()" ausführen
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aendereAusrichtung(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //Wenn die  Auswahlleiste losgelassen wird
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    seekBar.setProgress(90,true);       //Leiste auf 50% (90) stellen und dabei animieren
                }
            }
        });

        //Warte auf Änderungen des Schalters (Switch)
        ausrichtungBlockSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //Wenn der Wert des Schalters verändert wurde, bitte "blockSwitchChecked()" ausführen
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                blockSwitchChecked(isChecked);
            }
        });
    }

    //Ausrichtung ändern
    public void aendereAusrichtung(int progress){
        ausrichtungsAnzeige.setText("Ausrichtung: " + progress + "°");  //Text der Ausrichtung ändern
        //ausrichtung auf X                                             //generelle Ausrichtung auf X Grad stellen
        ausrichtung = progress;
        //zusammenfassung.setAusrichtung(ausrichtung);

    }
    public  void blockSwitchChecked(boolean isChecked){
        if(isChecked){                                          //WENN Schalter AN, dann...
            ausrichtungBar.setEnabled(false);                   //Auswahlleiste (SeekBar) sperren
            ausrichtungBar.setAlpha(0.1f);                      //Auswahlleiste transparent machen
            ausrichtungsAnzeige.setText("Ausrichtung: 0°");     //Text der Ausrichtung ändern
            //ausrichtung auf 0                                 //generelle Ausrichtung auf 0 Grad stellen
            ausrichtung = 0;
            //zusammenfassung.setAusrichtung(0);
        } else {                                                //SONST
            ausrichtungBar.setEnabled(true);                    //Auswahlleiste (SeekBar) freischalten
            ausrichtungBar.setAlpha(1f);                        //Transparenz aufheben
            ausrichtungsAnzeige.setText("Ausrichtung: 90°");    //Text der Ausrichtung ändern
            //ausrichtung auf 90                                //generelle Ausrichtung auf 90 Grad stellen
            //zusammenfassung.setAusrichtung(90);
            ausrichtung = 90;
        }
    }

    public long getAusrichtung() {
        return ausrichtung;
    }

    //Attribute vorinitialisieren
    public void setAusrichtungBar(SeekBar ausrichtungBar) {
        this.ausrichtungBar = ausrichtungBar;
    }

    public void setAusrichtungBlockSwitch(Switch ausrichtungBlockSwitch) {
        this.ausrichtungBlockSwitch = ausrichtungBlockSwitch;
    }

    public void setAusrichtungsAnzeige(TextView ausrichtungsAnzeige) {
        this.ausrichtungsAnzeige = ausrichtungsAnzeige;
    }
}
