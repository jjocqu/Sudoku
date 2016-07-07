package Logic.Utils;

/**
 * Created by jens on 7/7/2016.
 *
 * A simple timer to compare the different implementations
 *
 */
public class Timer {

    private long start, end;

    public void startTimer() {
        start = System.currentTimeMillis();
    }

    //ends timer and prints time
    public long endTimer() {
        end = System.currentTimeMillis();

        return end-start;
    }

}
