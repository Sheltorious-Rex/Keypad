package Components;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Controller implements Runnable{
    private AccessCodes accessCode;
    private Gate gate;
    private Key key;
    private Notification notification;
    private Timer keyTimer;
    private Timer gateTimer;
    private Timer notifTimer;

    private Circle[] leds;
    private ImageView imageView;
    private int sdNumber;

    private StringBuffer stringBuffer;
    private int state;
    private int whichChange;

    public Controller(ImageView imageView, Circle[] leds,int sdNumber){
        accessCode = new AccessCodes(sdNumber);
        gate = new Gate();
        key = new Key();
        notification = new Notification();
        keyTimer = new Timer();
        gateTimer = new Timer();
        notifTimer = new Timer();

        this.leds = leds;
        this.imageView = imageView;
        this.sdNumber = sdNumber;

        stringBuffer = new StringBuffer(4);
        state = 0;
        whichChange = 0;

    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            //We want to check timers for any timeouts and reset accordingly.
            if(gateTimer.isRunning() && gateTimer.timeout()){
                //If door was opened and timer timeout we need
                //to close door.
                gateTimer.reset();
                gate.closeLock(imageView,1);
            }
            if(keyTimer.isRunning() && keyTimer.timeout()){
                //If our user input timer expired we reset to idle state
                keyTimer.reset();
                stringBuffer.delete(0,stringBuffer.length());
                state = 0;
                whichChange = 0;

                notification.lightOn(true,false,false,leds);
                notifTimer.set(1500);
            }

            if(notifTimer.isRunning() && notifTimer.timeout()){
                //leds[0].fillProperty().get();
                notification.lightOff(leds);
                notifTimer.reset();
            }
            //Actually we might be able to get lights working


            if(key.hasNext()){
                char c = key.getKeyTyped();
                processInput(c);
            }else{
                try {
                    //We don't need to  be running all the time.
                    //Max user click in one second is like 16 (record)
                    Thread.sleep(200);
                }catch (Exception e){
                    System.out.println("Could not sleep");
                    System.exit(-1);
                }
            }
        }
    }

    private void processInput(char c){
        //If we are already processing request and we receive * or # we ignore
        if(state != 0 && (c == '*' || c == '#')){
            return;
        }
        //Initiating some state from idle
        if(stringBuffer.length() == 0 && state == 0){
            keyTimer.set(5000);
            if(c == '#'){
                //Manager changing manager code.
                whichChange = 1;
                state = 2;
            }else if(c == '*'){
                //Manager changing user code.
                whichChange = 2;
                state = 2;
            }else{
                whichChange = 0;
                stringBuffer.append(c);
                state = 1;
            }
            notification.lightOn(false,true,false,leds);
            notifTimer.set(5000);
            keyTimer.set(5000);
            return;
        }
        //If we are not in Idle state jsut accept
        stringBuffer.append(c);
        keyTimer.set(5000);
        notifTimer.set(5000);

        //If our buffer becomes full we process depending on state
        if(stringBuffer.length() == 4){
            if(state == 2){
                //Lets process waiting on managerPassword
                if(accessCode.checkMgrCode(stringBuffer.toString())){
                    //Manager enters correct password
                    state = 3;
                    notification.lightOn(false,true,true,leds);
                }else{
                    //Manager enters incorrect password
                    state = 0;
                    whichChange = 0;
                    notification.lightOn(true,false,false,leds);
                    notifTimer.set(2000);
                    keyTimer.reset();
                }
                stringBuffer.delete(0,4);
            }else if(state == 3){
                //Lets process waiting on updatePassword
                boolean changed = (whichChange == 1) ?
                        accessCode.changeMgrCode(stringBuffer.toString(),sdNumber) :
                        accessCode.changeUserCode(stringBuffer.toString(),sdNumber);

                notification.lightOn(false,false,true,leds);
                notifTimer.set(2000);
                whichChange = 0;
                state = 0;
                keyTimer.reset();
                stringBuffer.delete(0,4);
            }else{
                //Lets process an open door request
                if(accessCode.checkUserCode(stringBuffer.toString())){
                    gate.openLock(imageView,2);
                    gateTimer.set(5000);
                    notification.lightOn(false,false,true,leds);
                }else{
                    notification.lightOn(true,false,false,leds);
                }
                notifTimer.set(2000);
                state = 0;
                keyTimer.reset();
                stringBuffer.delete(0,4);
            }

        }

    }

    public Key getKeyQueue(){
        return this.key;
    }

}
