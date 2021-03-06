package Components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.LinkedList;
import java.util.Queue;

public class Key {

    private Queue<Character> keyCharacters;
    private EventHandler<ActionEvent> keyListener;

    public Key(){
        keyCharacters = new LinkedList<>();

        /*keyListener is an ActionListener that gets its source, a button from the GUI in this case,
        then gets the value that this button represents, then adds it to the Queue*/
        keyListener = e -> {
            Button keyPressed = (Button) e.getSource();
            //Add the key as a char to the Queue
            keyCharacters.add(keyPressed.getText().charAt(0));
        };
    }
    /**
     * Pull the head of the Queue off and return it
     * @return chars in the order that they were received
     */
    public char getKeyTyped(){
        return keyCharacters.poll();
    }

    /***
     * Checks whether the queue has an available key input.
     * @return
     */
    public boolean hasNext(){
        return !keyCharacters.isEmpty();
    }

    public EventHandler<ActionEvent> getKeyListener() {
        return keyListener;
    }
}

