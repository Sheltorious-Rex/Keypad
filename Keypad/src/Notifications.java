import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Notifications {

    public Notifications(){
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
     * Turns on the light in the HBox lightBox that has the color 'color'
     * @param color Color
     * @param lightBox HBox
     */
    public void lightOn(Color color, HBox lightBox){
        /*make new LED light*/
        Circle LED = new Circle(1);//just a null circle
        //if want to turn on Green LED
        if(color.equals(Color.GREEN)){
            LED = (Circle) lightBox.getChildren().get(0);
        }
        //if want to turn on Yellow LED
        else if(color.equals(Color.YELLOW)){
            LED = (Circle) lightBox.getChildren().get(1);
        }
        //if want to turn on Red LED
        else if(color.equals(Color.RED)){
            LED = (Circle) lightBox.getChildren().get(2);
        }
        LED.setFill(color);
    }
    /**
     * Turn off all the lights by replacing all the
     * LED's in lightBox with a grey
     * color.
     * @param lightBox HBox
     */
    public void lightOff(HBox lightBox){
        //Replace green
        Circle LED = (Circle)lightBox.getChildren().get(0);
        LED.setFill(Color.DARKSLATEGREY);
        //Replace yellow
        LED = (Circle)lightBox.getChildren().get(1);
        LED.setFill(Color.DARKSLATEGREY);
        //Replace Red
        LED = (Circle)lightBox.getChildren().get(2);
        LED.setFill(Color.DARKSLATEGREY);
    }
}
