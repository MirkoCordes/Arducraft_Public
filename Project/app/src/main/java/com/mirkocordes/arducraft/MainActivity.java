package com.mirkocordes.arducraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Objekte
    //BluetoothThread bluetoothThread;
    ConnectionLayout connectionLayout;
    Leistung leistungsKlasse;
    Ausrichtung ausrichtungKlasse;
    Button connectingButton;
    Button disconnectingButton;
    Zusammenfassung blubb;
    BluetoothThread bluetoothThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Objekte erzeugen
        //bluetoothThread = new BluetoothThread();
        connectionLayout = new ConnectionLayout(
                (TextView) findViewById(R.id.connected_Text),
                (Button) findViewById(R.id.connecting_Button),
                (TextView) findViewById(R.id.anleitungText),
                (SeekBar) findViewById(R.id.ausrichtungBar),
                (SeekBar) findViewById(R.id.leistungBar),
                (TextView) findViewById(R.id.leistungsAnzeige),
                (TextView) findViewById(R.id.ausrichtungsAnzeige),
                (Switch) findViewById(R.id.blockadeSwitch),
                (View) findViewById(R.id.divider),
                (Button) findViewById(R.id.disconnecting_Button)
        );
        bluetoothThread = new BluetoothThread(blubb, connectionLayout);
        leistungsKlasse = new Leistung(
                (SeekBar) findViewById(R.id.leistungBar),
                (TextView) findViewById(R.id.leistungsAnzeige)
        );
        ausrichtungKlasse = new Ausrichtung(
                (SeekBar) findViewById(R.id.ausrichtungBar),
                (Switch) findViewById(R.id.blockadeSwitch),
                (TextView) findViewById(R.id.ausrichtungsAnzeige)
        );

        connectionLayout.disconnectedText();
        blubb =new Zusammenfassung(leistungsKlasse, ausrichtungKlasse, bluetoothThread);

        //Über Button verbinden
        //Sichtbarkeit von anderen Objekten einstellen
        //objekteSichtbar(false, 0);
        connectingButton = (Button) findViewById(R.id.connecting_Button);
        connectingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                connectionLayout.connectingText();

                bluetoothThread.start();

                try {
                    bluetoothThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (bluetoothThread.bluetoothSocket.isConnected()) {
                    connectionLayout.connectedText();
                    weiterGehts();
                } else {
                    connectionLayout.error();
                }
            }
        });

        disconnectingButton = (Button) findViewById(R.id.disconnecting_Button);
        disconnectingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothThread.disconnect();
                connectionLayout.disconnectedText();
                bluetoothThread.interrupt();
                System.exit(0);
            }
        });
    }

    public void weiterGehts(){


        NewThread newThread1 = new NewThread(true, blubb);
        NewThread newThread2 = new NewThread(false, blubb);

        newThread1.start();
        newThread2.start();
    }


}