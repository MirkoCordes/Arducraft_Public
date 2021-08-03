package com.mirkocordes.arducraft;

import android.widget.SeekBar;
import android.widget.TextView;

public class Leistung {

    private SeekBar leistungBar;
    private TextView leistungsAnzeige;

    private long leistung;

    //Zusammenfassung zusammenfassung = new Zusammenfassung();

    public Leistung(SeekBar leistungBar, TextView leistungsAnzeige){
        //localen Attribute einstellen
        setLeistungBar(leistungBar);
        setLeistungsAnzeige(leistungsAnzeige);

        //bluetoothThread = new BluetoothThread();

        //Warte auf Änderungen der "SeekBar"
        leistungBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //Wenn der "progress"-Wert verändert wurde, bitte "aendereLeistung()" ausführen
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aendereLeistung(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    //Leistung ändern
    public void aendereLeistung(int progress){
        //Umrechnung des Wertes (0-255) "progress" in Prozent
        int x = progress * 100;
        int prozent = x / 255;
        leistungsAnzeige.setText("Leistung: " + prozent + "%");     //Ausgabe der Leistung in Prozent

        //Leistung auf progress                                            //generelle Leistung auf progress stellen
        leistung = progress;
        //zusammenfassung.setLeistung(leistung);
    }

    public long getLeistung() {
        return leistung;
    }

    //Attribute vorinitialisieren
    public void setLeistungBar(SeekBar leistungBar) {
        this.leistungBar = leistungBar;
    }

    public void setLeistungsAnzeige(TextView leistungsAnzeige) {
        this.leistungsAnzeige = leistungsAnzeige;
    }
}
