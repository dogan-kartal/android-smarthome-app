# arduino-android-smarthome
### This is a simple simulation project written for NodeMCU esp8266  <br>
**The view of card:**                                                <br>

![gorselnodemcu-1](https://user-images.githubusercontent.com/60793259/111488987-ce18e600-874a-11eb-9de7-580d0727b2d4.jpg)



**First create a Firebase account and open Realtime Database**       <br>

![1](https://user-images.githubusercontent.com/60793259/111489753-77f87280-874b-11eb-9905-79a00d3b9f9d.png)


**Then write the simple speech to text application**                  <br>

![Screenshot_1615926134](https://user-images.githubusercontent.com/60793259/111490475-1be21e00-874c-11eb-9c7e-3d1a34b6e001.png)

**This application will send your commands to Firebase**               <br>

![3](https://user-images.githubusercontent.com/60793259/111491055-957a0c00-874c-11eb-8bb2-9f845bdf16bf.png)

**Finally, Arduino read commands from Realtime Database and will do the operations like:** <br>
* **When you says "turn on the air conditioner" or "turn of the air conditioner" the led on esp8266 will turn on and off**  <br>
- **When you says "open the lights" or "close the lights" 8 LEDs on the card will turn on and off**                         <br>
+ **When you says "open the door" or "close the door" servo motor will open and close 90 degrees**                          <br>
