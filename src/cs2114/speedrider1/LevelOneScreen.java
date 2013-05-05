package cs2114.speedrider1;

import sofia.app.Persistent;
import sofia.graphics.Shape;
import java.util.Stack;
import java.nio.charset.Charset;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import android.content.Context;
import android.view.MenuItem;
import android.view.Menu;
import android.view.MenuInflater;
import cs2114.speedrider1.Goal;
import cs2114.speedrider1.Ground;
import cs2114.speedrider1.Rider;

// -------------------------------------------------------------------------
/**
 * Level One Screen.
 *
 * @author Chris Conley
 * @author Dan Moore
 * @author Harjas Singh
 * @version 2013.04.24
 */
public class LevelOneScreen
    extends Level
{
    private Rider        rider;
    private Goal         goal;
    private StopWatch    timer;
    private String       FILENAME;

    @Persistent
    private long         highScore = 999;
    private long         elapsedTime;

    private float        x1;
    private float        y1;

    // keeps track of whether or not the player is drawing, erasing, adding
    // a speed booster, or started the animation
    private boolean      draw;
    private boolean      erase;
    private boolean      booster;
    private boolean      started;

    // stack for undo function
    private Stack<Shape> undo1;


    // ~ Public methods ........................................................
    /**
     * Initializes the state of the screen: its background color, the coordinate
     * system, gravity, and the shapes in the field. Automatically defines the
     * amount of line segments that can be drawn as 500
     */
    public void initialize()
    {
        draw = false;
        booster = false;
        started = false;
        timer = new StopWatch();

        undo1 = new Stack<Shape>();

        BackgroundPaper back =
            new BackgroundPaper(0, 0, getWidth(), getHeight());
        back.setSensor(true);
        add(back);

        // create bounds so the player does not fall off the screen
        Ground bound1 = new Ground(0, 0, this.getWidth(), 0);
        this.add(bound1);

        Ground bound2 = new Ground(0, 0, 0, this.getHeight());
        this.add(bound2);

        Ground bound3 =
            new Ground(0, this.getHeight(), this.getWidth(), this.getHeight());
        this.add(bound3);

        Ground bound4 =
            new Ground(this.getWidth(), 0, this.getWidth(), this.getHeight());
        this.add(bound4);

        // create a goal for the level
        goal = new Goal(this.getWidth() - 50, this.getHeight() - 50, 30);
        this.add(goal);

        // set the gravity level for the course
        this.setGravity(0, 20f);

        // create a new rider
        rider = new Rider(10, 10);
        this.add(rider);
        rider.setGravityScale(0);
    }


    /**
     * finish the rider
     */
    public void afterInitialize()
    {
        rider.finishRider();
        FILENAME = "listOfTimes";
    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.draw:
                this.draw();
                return true;
            case R.id.booster:
                this.speedBoost();
                return true;
            case R.id.erase:
                this.erase();
                return true;
            case R.id.scores:
                this.getScores();
                return true;
            case R.id.undo:
                this.undo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * create a line segment when player touches down
     *
     * @param newx1
     *            the first x location
     * @param newy1
     *            the first y location
     */
    public void onTouchDown(float newx1, float newy1)
    {
        x1 = newx1;
        y1 = newy1;
        super.onTouchDown(newx1, newy1, booster, started, rider, undo1, y1, x1, timer);
    }



    /**
     * When touch is released, the x and y coordinates at the end of the line
     * are drawn
     *
     * @param newX
     *            the second x location
     * @param newY
     *            the second y location
     */
    public void onTouchMove(float newX, float newY)
    {
        super.onTouchMove(newX, newY, x1, y1, draw, erase, undo1);
        x1 = newX;
        y1 = newY;
    }

    /**
     * when a user touches screen create line segment or erase a line segment.
     * Only creates line if user has not used up all of the available segments
     * or draw is true. If erase is true then line segments will be erased
     *
     * @param newx1
     *            the first x location
     * @param newy1
     *            the first y location
     * @param newx2
     *            the second x location
     * @param newy2
     *            the second y location
     */
    public void processTouch(float newx1, float newy1, float newx2, float newy2)
    {
        super.processTouch(newx1, newy1, newx2, newy2, draw, erase, undo1);

    }


    /**
     * set draw field to true when draw button is clicked. set all others to
     * false
     */
    public void draw()
    {
        draw = true;
        booster = false;
        erase = false;
    }


    /**
     * set erase field to true when erase button is clicked. set all others to
     * false
     */
    public void erase()
    {
        draw = false;
        booster = false;
        erase = true;
    }


    /**
     * set booster field to true when booster button is clicked. set all others
     * to false
     */
    public void speedBoost()
    {
        draw = false;
        booster = true;
        erase = false;
    }


    /**
     * This lets the player control when the animation starts
     */
    public void start()
    {
        super.start(started, rider);
        started = true;

    }


    /**
     * erases past 10 lines drawn, or last speed booster
     */
    public void undo()
    {
        super.undo(undo1);
    }


    private String readFile()
    {
        FileInputStream fis;
        String result = "";
        try
        {
            fis = openFileInput(FILENAME);
            FileChannel fc = fis.getChannel();
            MappedByteBuffer bb =
                fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            result = Charset.defaultCharset().decode(bb).toString();
            fis.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Reads files and presents a pop up dialog that shows previous scores
     */
    public void getScores()
    {
        String result = this.readFile();

        if (highScore == 999 || result.equals(""))
            showAlertDialog(
                "Previous Times - Current High Score: No Data",
                result);
        else
        {
            showAlertDialog(
                "Previous Times - Current High Score: "
                    + String.valueOf(highScore) + " seconds",
                result);
        }
    }


    /**
     * Sets the highScore to elapesTime if needed.
     */
    private void updateHighScore()
    {
        highScore = elapsedTime;
    }


    /**
     * Reads and writes to the listOfScores file used in superclass method
     * process touch
     */
    @SuppressWarnings("unused")
    private void updateTime()
    {
        // Stops the StopWatch and stores its data in a byte array
        timer.stop();
        elapsedTime = timer.getElapsedTimeSecs();
        String time = String.valueOf(elapsedTime + " seconds\n");
        byte[] currentTimeInBytes = time.getBytes();

        // Updates high score
        if (elapsedTime < highScore)
        {
            this.updateHighScore();
        }

        // Reads the current file and stores its data in a byte array
        String result = this.readFile();
        byte[] pastTimesInBytes = result.getBytes();

        // Writes the current time followed by the past times to the file
        FileOutputStream fos;
        try
        {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(currentTimeInBytes);
            fos.write(pastTimesInBytes);
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
