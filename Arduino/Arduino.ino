#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

#include <Servo.h>
Servo motor;

const char *ssid = "XXX";                 // SSID of the wireless network to be connected
const char *password = "XXX";             // Password of the wireless network to be connected

#define FIREBASE_HOST "XXXXXXX"                 // firebase project location
#define FIREBASE_AUTH "XXXXXXX"                 // firebase database secrets


//------------------------------------------------------------------------------------
void setup() 
{
  pinMode(D0,OUTPUT); 
  pinMode(D1,OUTPUT);
  pinMode(D2,OUTPUT);
  pinMode(D3,OUTPUT);
  pinMode(D4,OUTPUT);
  pinMode(D5,OUTPUT);
  pinMode(D6,OUTPUT);
  pinMode(D7,OUTPUT);


  motor.attach(D4);        // Servo connected to D4 pin
  
  pinMode(2,OUTPUT);
  Serial.begin(115200);                       
  delay(1000);                      
  WiFi.mode(WIFI_STA);                        // ESP8266 is being switched to station mode. 
  WiFi.begin(ssid, password);                 

  Serial.print("Connecting");
  while (WiFi.status() != WL_CONNECTED)       //Waiting until wifi connection is established.
  { 
    delay(500);                               
    Serial.print(".");                        
  }
  
  Serial.println("");                         
  Serial.println("Connected");    

  Serial.print("Received IP address: ");       
  Serial.println(WiFi.localIP());             // IP address received from the wireless network
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}
//------------------------------------------------------------------------------------
void loop() 
{
  String command;
  command=Firebase.getString("Command");         // Read Command information from Firebase
  if(command=="1"){
     digitalWrite(2,LOW);
  }
  else if(command=="0"){
     digitalWrite(2, HIGH);
  }
  else if(command=="2"){
     digitalWrite(D0,HIGH); 
     digitalWrite(D1,HIGH); 
     digitalWrite(D2,HIGH); 
     digitalWrite(D3,HIGH); 
     digitalWrite(D4,HIGH); 
     digitalWrite(D5,HIGH); 
     digitalWrite(D6,HIGH); 
     digitalWrite(D7,HIGH); 
  }
  else if(command=="3"){
     digitalWrite(D0,LOW);
     digitalWrite(D1,LOW); 
     digitalWrite(D2,LOW); 
     digitalWrite(D3,LOW); 
     digitalWrite(D4,LOW); 
     digitalWrite(D5,LOW); 
     digitalWrite(D6,LOW); 
     digitalWrite(D7,LOW);
  }
  else if(command=="4"){
    motor.write(90);                  // change the angle of the motor
  }
  else if(command=="5"){
    motor.write(0);                  // change the angle of the motor
  }
}
