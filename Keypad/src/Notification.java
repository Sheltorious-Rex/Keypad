import javafx.scene.paint.Color;


public class Notification {

    //Only one color active
    private Color active;

    /**
     * Constructor, do we take in an led object? colors[]?
     */
    public Notification(){
        this.active = null;
    }

    /**
     * sets notification on to specified color.
     * @param color the color to set.
     */
    public void lightOn(Color color){
        active = color;
    }

    /**
     * sets notification off.
     */
    public void lightOff(){
        active = null;
    }

    /**
     * gets active color
     * @return Color active
     */
    public Color getActiveLight(){
        return active;
    }
}
