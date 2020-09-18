/**
*  An interface that encapsulates communication between the keypad
*  and a keypad receiver.  GUI can write to this interface to
*  communicate key presses.
*/
public interface KeypadReceiver {
    public void addKeyPress(char key);
    public boolean hasNext();
   
}
