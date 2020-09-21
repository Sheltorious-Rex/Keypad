import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Gate {

    /**
     * Gate constructor, no inputs, no fields
     * Encapsulates the interface to the magnetic gate
     * locking mechanism.
     */
    public Gate(){

    }

    /**
     * The method that sends the unlocking signal to the
     * physical gate device.
     */
    public void openLock(){
        /*Code to send an unlocking signal to the physical device Gate*/
    }
    /**
     * The method that sends the locking signal to the
     * physcial gate device
     */
    public void closeLock(){
        /*Code to send the locking signal to the physical device Gate*/
    }

    /**
     * Overloaded openLock method for use during demo. Takes a
     * reference to the HBox that holds the Lock status label
     * @param gate HBox
     */
    public void openLock(HBox gate){
        /*Change the color of the lock status label to green for open*/
        Label lockLabel = (Label) gate.getChildren().get(0);
        lockLabel.setBackground(new Background(new BackgroundFill(
                Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    /**
     * Overloaded closeLock method for use during demo. Takes a
     * reference to the HBox that holds the Lock status label
     * @param gate HBox
     */
    public void closeLock(HBox gate){
        /*change color of lock label to red for closed*/
        Label lockLabel = (Label) gate.getChildren().get(0);
        lockLabel.setBackground(new Background(new BackgroundFill(
                Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
