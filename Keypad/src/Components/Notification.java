package Components;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Notification {
    public Notification(){
        /*Nothing. This object encapsulates the interface to the LEDs*/
    }
    /**
     * This method sends the turn on signal to the physical LED device with
     * the color 'color'
     * @param color Color of light to turn on
     */
    public void lightOn(Color color){
        /*Send ON signal to the light on the physical LED device
         that has the color 'color'*/
    }
    /**
     * Turns off any lights that are on on the physical LED device
     */
    public void lightOff(){
        /*Send OFF signal to all of the lights on the physical LED device*/
    }



    /*Overloaded version of lightOn and lightOff that activate the lights on the
    GUI*/

    /**
     * Turns on the desired Leds.
     * @param red boolean
     * @param yellow boolean
     * @param green boolean
     * @param leds the circles
     */
    public void lightOn(Boolean red,Boolean yellow, Boolean green,Circle[] leds){
        leds[0].setFill((red) ? Color.RED: Color.GREY);
        leds[1].setFill((yellow)? Color.YELLOW: Color.GREY);
        leds[2].setFill((green)? Color.rgb(0,255,0): Color.GREY);
    }
    /**
     * Turns off the leds by setting them as grey colored;
     * color.
     * @param leds
     */
    public void lightOff(Circle[] leds){
        leds[0].setFill(Color.RED);
        leds[1].setFill(Color.YELLOW);
        leds[2].setFill(Color.rgb(0,255,0));
    }
}
