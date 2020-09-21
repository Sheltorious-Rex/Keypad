import javafx.animation.PauseTransition;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Controller implements Runnable{

    private Key key;
    private Notifications notifications;
    private Timer keyTimer;
    private Timer gateTimer;
    private AccessCodes accessCodes;
    private Gate gate;

    /*Fields for the Demo*/
    private HBox mainBox;
    private int keyPadNumber;

    public Controller (){
        key = new Key();
        notifications = new Notifications();
        keyTimer = new Timer();
        gateTimer = new Timer();
        accessCodes = new AccessCodes();
        gate = new Gate();
    }

    @Override
    public void run() {

        VBox keypad = keyPadNumber == 1 ? (VBox) mainBox.getChildren().get(0) : (VBox) mainBox.getChildren().get(2);
        HBox gate = (HBox)mainBox.getChildren().get(1);
        StringBuffer buffer;
        boolean gateUnlocked = false;
        Timer loopTimer = new Timer();
        loopTimer.set(400);
        while (true){
            if(loopTimer.timeout()){
                //empties the buffer between attempts to interact with the system
                buffer  = new StringBuffer();
                char userInput = key.getKeyTyped();
                /*If an attempt change a password has been initiated*/
                if(userInput == '*' || userInput == '#'){
                    //start the key timer, 3000 milliseconds = 3 seconds
                    keyTimer.set(3000);
                    boolean checkingManagerCode = true;
                    loopTimer.reset();
                    loopTimer.set(400);

                    while(!keyTimer.timeout() && buffer.length() < 4){
                        if(loopTimer.timeout()){
                            char localUserInput = key.getKeyTyped();
                            if(localUserInput != '~'){
                                keyTimer.reset();
                                buffer.append(localUserInput);
                                keyTimer.set(3000);
                            }
                            loopTimer.reset();
                            loopTimer.set(400);
                        }
                        /*if buffer.length = 4 and this is supposed to be the manager code and it matches.
                         * If we are going in here, then the buffer made it to length 4, meaning keyTimer just
                         * got reset, so it will not be timed out*/
                        if(buffer.length() == 4){
                            if(checkingManagerCode && accessCodes.checkMgrCode(buffer.toString())) {
                                //restart with a timer reset and with a new buffer, show the green and yellow light
                                notifications.lightOn(Color.YELLOW, (HBox)keypad.getChildren().get(0));
                                notifications.lightOn(Color.GREEN, (HBox)keypad.getChildren().get(0));
                                keyTimer.reset();
                                keyTimer.set(4000);
                                //set this to false to go into the other if below when buffer's length = 4 again
                                checkingManagerCode = false;
                                //empty the buffer
                                buffer = new StringBuffer();
                            }
                             /*if !checkingManagerCode = true, then it made it successfully through validating
                             manager code. Also make sure that the new code given does not contain an invalid
                             passcode char '*' or '#'     */
                            else if(!checkingManagerCode &&
                                    !buffer.toString().contains("#") &&
                                    !buffer.toString().contains("*")) {
                                boolean changeSuccessful;
                                //if changing the manager code
                                if(userInput == '#') {
                                    changeSuccessful = accessCodes.changeMgrCode(buffer.toString());
                                }
                                //it could only be '*' in this case, if changing userCode
                                else {
                                    changeSuccessful = accessCodes.changeUserCode(buffer.toString());
                                }
                                //turn off orange and green. Use a pause transition to simulate blinking lights
                                notifications.lightOff((HBox)keypad.getChildren().get(0));
                                /*now if the change was successful, lights on and lights off */
                                if(changeSuccessful) {
                                    //green light on
                                    notifications.lightOn(Color.GREEN,(HBox)keypad.getChildren().get(0));
                                    //don't empty buffer so that the while loop exits
                                }
                                //else the change failed
                                else {
                                    //red light on
                                    notifications.lightOn(Color.RED, (HBox) keypad.getChildren().get(0));
                                }
                                //don't empty buffer so that the while loop exits
                            }
                            /*else the code to change to has a '*' or '#' in it or accessCodes.checkMgrCode(buffer)
                            returned false   */
                            else{
                                //turn off orange and green if they are on
                                notifications.lightOff((HBox)keypad.getChildren().get(0));
                                //use a little pause transition to simulate blinking lights
                                PauseTransition pt = new PauseTransition();
                                pt.setDuration(Duration.seconds(0.6));
                                pt.setOnFinished(e -> {
                                    //turn on red
                                    notifications.lightOn(Color.RED, (HBox)keypad.getChildren().get(0));
                                });
                                pt.play();//turn on red
                                //don't empty buffer so that the while loop exits*/
                            }
                        }
                    }
                    //turn off lights if timeout occurred timeout or a botched input
                    PauseTransition pt2 = new PauseTransition();
                    pt2.setDuration(Duration.seconds(3));
                    pt2.setOnFinished(e -> {
                        //turn off red
                        notifications.lightOff((HBox)keypad.getChildren().get(0));
                    });
                    pt2.play();
                }
                //else if a common user interaction has began. Using ascii chart comparisons, if userInput is 0-9
                else if(userInput > '/' && userInput < ':'){
                    //add this first key to the buffer
                    buffer.append(userInput);
                    //start the key timer
                    keyTimer.set(3000);
                    loopTimer.reset();
                    loopTimer.set(400);
                    //while timeout has not occurred and buffer is less than length 4
                    while(!keyTimer.timeout() && buffer.length() < 4){
                        if(loopTimer.timeout()){
                            char localUserInput = key.getKeyTyped();//look for more keys
                            if(localUserInput != '~'){//if not null key
                                //reset timer and add to buffer
                                keyTimer.reset();
                                buffer.append(localUserInput);
                                keyTimer.set(4000);
                            }
                            loopTimer.reset();
                            loopTimer.set(400);
                        }
                        /* if buffer.length = 4 and this matches the userCode.
                         * If we are going in here, then the buffer made it to length 4, meaning keyTimer just got
                         * reset, so it will not be timed out*/
                        if(buffer.length() == 4){
                            if(accessCodes.checkUserCode(buffer.toString())) {
                            /*reset in case a user has opened the gate after another user has opened the gate and
                            it has not yet been closed*/
                                gateTimer.reset();
                                //10000 milliseconds = 10 seconds
                                gateTimer.set(10000);
                                //open lock

                                this.gate.openLock(gate);
                                gateUnlocked = true;
                                /*use pause transition to simulate blinking lights
                                Turn on green light                */
                                notifications.lightOn(Color.GREEN, (HBox)keypad.getChildren().get(0));

                            }
                            //else, userCode does not match
                            else{
                            /*use pause transition to simulate blinking lights
                            Turn on red light     */
                                notifications.lightOn(Color.RED, (HBox)keypad.getChildren().get(0));
                            }
                            //not emptying buffer will cause the while loop to exit
                        }
                    }
                    /*Turn off the light that is on after 3 seconds*/
                    PauseTransition pt2 = new PauseTransition();
                    pt2.setDuration(Duration.seconds(3));
                    pt2.setOnFinished(e -> {
                        //turn off lights
                        notifications.lightOff((HBox)keypad.getChildren().get(0));
                    });
                    pt2.play();
                }
                /*Check if the gate is unlocked. If it is, and the gateTimer has timed out*/
                if(gateUnlocked && gateTimer.timeout()){
                    //lock the gate
                    gateUnlocked = false;
                    this.gate.closeLock(gate);
                    //don't need to reset here because it gets reset before being set every time.
                }

                loopTimer.reset();
                loopTimer.set(400);
            }
        }
    }
    public void setMainBox(HBox mainBox, int keyPadNumber) {
        this.mainBox = mainBox;
        this.keyPadNumber = keyPadNumber;
    }
    public Key getKey() {
        return key;
    }
}
