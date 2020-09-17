import java.util.LinkedList;
import java.util.List;

/**
 * @author brian bleck
 * KeyQueue is a class that handles key inputs and stores them in a list.
 * It provides a method to add to the list, to check if the list has a next char,
 * and to pop the next char from the list.
 * Do not call pop() without checking to see if hasNext() is true.
 */
public class KeyQueue implements KeypadReceiver {
    private List<Character> charList;

    public KeyQueue() {
        charList = new LinkedList<Character>();
    }


    @Override
    public void addKeyPress(char key) {
        charList.add(key);
    }

    /**
     * A method to get the next key that was pressed.
     * Assumes the list is not empty.
     * @return next char that was pressed
     */
    public char pop() {
        char tempKey = 0;
        try {
            tempKey = charList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in pop() method: probably was called without first checking hasNext()");
        }
        charList.remove(0);
        return tempKey;
    }

    /**
     * A method that returns true if the char list has something in it.
     * Returns false if the list is empty.
     * @return boolean
     */
    public boolean hasNext() {
        return charList.size() > 0;
    }


}
