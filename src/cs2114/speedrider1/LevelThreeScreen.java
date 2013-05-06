package cs2114.speedrider1;

import android.widget.TextView;
import sofia.util.Timer;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Stack;
import sofia.app.Persistent;
import sofia.graphics.Shape;

// -------------------------------------------------------------------------
/**
 * level 3, most difficult level
 *
 * @author Daniel
 * @version May 5, 2013
 */
public class LevelThreeScreen
    extends Level
{
    private Rider        rider;
    private Goal         goal;
    private StopWatch    timer;
    private String       FILENAME;
    private TextView     elapsedTimeLvl3;

    @Persistent
    private long         threeHighScore = 999;
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
                this.undo(undo1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Initializes the state of the screen: its background color, the coordinate
     * system, gravity, and the shapes in the field. Automatically defines the
     * amount of line segments that can be drawn as 500
     */
    public void initialize()
    {
        draw = true;
        booster = false;
        started = false;

        undo1 = new Stack<Shape>();
        timer = new StopWatch();

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
        goal = new Goal(this.getWidth() - 30, this.getHeight() / 2 + 150, 15);
        this.add(goal);

        // add spinners
        Spinner spinner =
            new Spinner(
                this.getWidth() / 2,
                this.getHeight() / 2,
                this.getWidth() / 2 + 10,
                this.getHeight() / 2 + 50);
        this.add(spinner);
        spinner.animate(500).repeat().rotation(360).play();

        // add spinners
        Spinner spinner2 =
            new Spinner(
                this.getWidth() / 2,
                this.getHeight() / 2 - 150,
                this.getWidth() / 2 - 10,
                this.getHeight() / 2 - 100);
        this.add(spinner2);
        spinner2.animate(500).repeat().rotation(360).play();

        // add spinners
        Spinner spinner3 =
            new Spinner(
                this.getWidth() / 2,
                this.getHeight() / 2 + 150,
                this.getWidth() / 2,
                this.getHeight() / 2 + 100);
        this.add(spinner3);
        spinner3.animate(500).repeat().rotation(360).play();

        // set the gravity level for the course
        this.setGravity(0, 20f);

        // create a new rider
        rider = new Rider(20, 20);
        this.add(rider);
        rider.setGravityScale(0);
    }


    /**
     * finish the rider
     */
    public void afterInitialize()
    {
        rider.finishRider();
        FILENAME = "levelThreeTimes.txt";
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
        if (rider.getRemoved())
        {
            this.updateTime();
            boolean x = true;
            this.finish(x);
        }

        Rider rider1 =
            getShapes().locatedAt(newx1, newy1).withClass(Rider.class).front();
        // make sure a rider was found to start
        if (rider1 != null)
        {
            this.start();
            timer.start();
            Timer.callRepeatedly(this, "getElapsedTime", 1000, 1000);
        }

        x1 = newx1;
        y1 = newy1;
        super.onTouchDown(
            newx1,
            newy1,
            booster,
            started,
            rider,
            undo1,
            y1,
            x1,
            timer);
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
        // apply a force to get the rider moving
        rider.setGravityScale(1);
        Rider.wheel1.setGravityScale(1f);
        Rider.wheel2.setGravityScale(1f);
        rider.applyLinearImpulse(0, 20000);
        started = true;
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

        if (threeHighScore == 999 || result.equals(""))
            showAlertDialog(
                "Previous Times - Current High Score: No Data",
                result);
        else
        {
            showAlertDialog(
                "Previous Times - Current High Score: "
                    + String.valueOf(threeHighScore) + " seconds",
                result);
        }
    }


    /**
     * Sets the highScore to elapesTime if needed.
     */
    private void updateHighScore()
    {
        threeHighScore = elapsedTime;
    }


    /**
     * Reads and writes to the listOfScores file called by superclass method
     * process touch
     */
    private void updateTime()
    {
        // Stops the StopWatch and stores its data in a byte array
        timer.stop();
        elapsedTime = timer.getElapsedTimeSecs();
        String time = String.valueOf(elapsedTime + " seconds\n");
        byte[] currentTimeInBytes = time.getBytes();

        // Updates high score
        if (elapsedTime < threeHighScore)
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


    /**
     * Updates time on screen so user can see the elapsed time.
     */
    public void getElapsedTime()
    {
        if (timer.getElapsedTimeSecs() == 1)
        {
            elapsedTimeLvl3.setText(String.valueOf(timer.getElapsedTimeSecs())
                + " second");
        }
        else
        {
            elapsedTimeLvl3.setText(String.valueOf(timer.getElapsedTimeSecs())
                + " seconds");
        }
    }
}
