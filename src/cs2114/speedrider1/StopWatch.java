package cs2114.speedrider1;

/**
 * Models a stopwatch for the game
 *
 * @author Chris
 * @version May 5, 2013
 */
public class StopWatch
{
    private long    startTime = 0;
    private long    stopTime  = 0;
    private boolean running   = false;


    // ----------------------------------------------------------
    /**
     * Starts the stop watch
     */
    public void start()
    {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }


    /**
     * Stops the stop watch
     */
    public void stop()
    {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }


    /**
     * elaspsed time in milliseconds
     *
     * @return long the time
     */
    public long getElapsedTime()
    {
        long elapsed;
        if (running)
        {
            elapsed = (System.currentTimeMillis() - startTime);
        }
        else
        {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }


    /**
     * elaspsed time in seconds
     *
     * @return long the time
     */
    public long getElapsedTimeSecs()
    {
        long elapsed;
        if (running)
        {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        }
        else
        {
            elapsed = ((stopTime - startTime) / 1000);
        }
        return elapsed;
    }
}
