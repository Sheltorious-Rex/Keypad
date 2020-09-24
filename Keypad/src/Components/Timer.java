package Components;

public class Timer {
    private long time;

    /**
     * Constructor - no args.
     */
    public Timer() {
        time = 0;
    }

    /**
     * Set time to zero.
     */
    public void reset() {
        time = 0;
    }

    /**
     * If the set time is greater than the current time, there is no timeout.
     * @return boolean
     */
    public boolean timeout() {
        return time < System.currentTimeMillis();
    }

    /**
     * Set the time by adding the set time in millis to the current time.
     * @param k int
     */
    public void set(int k) {
        time = k + System.currentTimeMillis();
    }


    /**
     * Checks whether this timer is active.
     * @return
     */
    public boolean isRunning(){
        return (time == 0) ? false : true;
    }

}
