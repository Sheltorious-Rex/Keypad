public class Controller implements Runnable
{

    private Timer timeoutTimer;
    private Timer loopTimer;
    private KeypadReceiver keypadReceiver;
    private AccessCodes accessCodes;

    public Controller(Timer timeoutTimer, Timer loopTimer, KeypadReceiver keypadReceiver, AccessCodes accessCodes) {
        this.timeoutTimer = timeoutTimer;
        this.loopTimer = loopTimer;
        this.keypadReceiver = keypadReceiver;
        this.accessCodes = accessCodes;
    }

    @Override
    public void run() {
        loopTimer.set(20);
        while(true){
            if(loopTimer.timeout()){
                if(keypadReceiver.hasNext()){
                    processInput();
                }
                loopTimer.set(20);
            }
            if(timeoutTimer.timeout()){
                controllerReset();
            }
        }
    }

    private void processInput() {
        //todo: implement logic of processing input
    }

    private void controllerReset() {
        //todo:  implement logic to reset
    }
}
