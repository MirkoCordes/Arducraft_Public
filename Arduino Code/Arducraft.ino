#include <Servo.h>             // Libarie für Servomotor holen

Servo servoMotor, ESC;              // Servoobjekt erstellen

//nötigen Variablen vordefinieren
long gotDatas, motorLeistung, servoLeistung;

void setup() {
  //HC modul
  Serial.begin(9600);
  Serial.setTimeout(5);
  pinMode(3, OUTPUT);

  servoMotor.attach(5);    //Servo auf 5. PWM-Pin einstellen
  ESC.attach(3);
  //Alle Werte standartmäßig auf 0 setzen
  gotDatas = 0;
  motorLeistung = 0;
  servoLeistung = 0;

  ESC.write(54);
  servoMotor.write(0);
  delay(50);
  return 0;
}

void loop() {
  if (Serial.available())
   {
    
    gotDatas = Serial.parseInt();                         //Eilesen der Gesamtzahl in (maximal 255.255)
    Serial.println(gotDatas);
    if(gotDatas <= 255180 || gotDatas < 0){
          motorLeistung = gotDatas / 1000;                      //Ersten drei Zahlen für Motor reservieren (255213 / 1000 = 255)
    servoLeistung = gotDatas - (motorLeistung * 1000);    //Letzten drei Zahlen für Servolenkung reservieren (255213 - (255 * 1000) = 213)
   
   Serial.println(motorLeistung);
   Serial.println(servoLeistung);
   
   //analogWrite(3, motorLeistung);     //Leistung des Motors über Pin 5 (PD5)
   //Geschwindigkeit = map(motorLeistung, 0, 255, 0, 180);   // Der "MAP-" Befehl wandelt den Messwert aus der Variablen "Drehregler" um, damit er am ESC verarbeitet werden kann. Der Zahlenbereich 0 bis 1023 wird dabei in einen Zahlenwert zwischen 0 und 180 umgewandelt.
   ESC.write(map(motorLeistung, 0, 255, 54, 171));
  
   servoMotor.write(servoLeistung);     //Leistung des Motors über Pin 6 (PD6)
   delay(50);  
    }
   }
}
