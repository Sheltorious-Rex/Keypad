package Components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
     * Opens the lock, in the GUI this means to change image.
     * @param imageView
     * @param whichImage
     */
    public void openLock(ImageView imageView, int whichImage) {
        Image imageOne = new Image(getClass().getResource("/IMG_2657.JPG").toString());
        Image imageTwo = new Image(getClass().getResource("/IMG_2658.JPG").toString());
        imageView.setImage((whichImage == 1) ? imageOne : imageTwo);
    }

    /**
     * Closes the lock, in the GUI this means changing to a closed image.
     * @param imageView
     * @param whichImage
     */
    public void closeLock(ImageView imageView, int whichImage){

        Image imageOne = new Image(getClass().getResource("/IMG_2657.JPG").toString());
        Image imageTwo = new Image(getClass().getResource("/IMG_2658.JPG").toString());
        imageView.setImage((whichImage == 1) ? imageOne: imageTwo);

    }

}
