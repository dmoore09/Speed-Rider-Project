package cs2114.speedrider1;

import android.content.DialogInterface;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.graphics.Typeface;
import android.widget.TextView;
import java.util.Stack;
import sofia.app.Persistent;
import sofia.graphics.Shape;

public class SandboxLevelScreen
    extends Level
{
    private Rider        rider;
    private Goal         goal;
    private StopWatch    timer;
    private String       FILENAME;
    private TextView     elapsedTimeSand;

    @Persistent
    private long         sandHighScore = 999;
    private long         elapsedTime;

    private float        x1;
    private float        y1;

    // keeps track of whether or not the player is drawing, erasing, adding
    // a speed booster, or started the animation
    private boolean      draw;
    private boolean      erase;
    private boolean      booster;
    private boolean      started;
    private boolean      goalSelected;
    private boolean      shooterSelected;
    private boolean      spinnerSelected;

    // stack for undo function
    private Stack<Shape> undo1;


    public void initialize()
    {
        Typeface typeFace =
            Typeface.createFromAsset(getAssets(), "fonts/roughage.ttf");
        elapsedTimeSand.setTypeface(typeFace);

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
    }


    /**
     * Initialize the name of the text file
     */
    public void afterInitialize()
    {
        FILENAME = "sandboxTimes.txt";
    }


    public void onTouchDown(float x, float y)
    {
        if (goalSelected)
        {
            if (goal != null)
            {
                goal.setPosition(x, y);
            }
            else
            {
                goal = new Goal(x, y, 20);
                add(goal);
            }
        }
        if (shooterSelected)
        {

        }
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
        processTouch(x1, y1, newX, newY);
        x1 = newX;
        y1 = newY;
    }


    public void processTouch(float x1, float y1, float x2, float y2)
    {
        // TODO implement this method - it will be long as fuck...
    }


    /**
     * Options menu
     *
     * @param menu
     *            the menu
     * @return boolean
     */
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_sandbox, menu);
        return true;
    }


    /**
     * Options menu
     *
     * @param item
     *            the menu
     * @return boolean
     */
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.goal:
                this.goal();
                return true;
            case R.id.shooter:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Build a Shooter");
                alert.setMessage("Enter the dimensions of the shooter: ie(");

                // Set an EditText view to get user input
                final EditText input = new EditText(this);
                alert.setView(input);
                alert.show();
                this.shooter();
                return true;
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
     * set draw field to true when draw button is clicked. set all others to
     * false
     */
    public void draw()
    {
        goalSelected = false;
        shooterSelected = false;
        spinnerSelected = false;
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
        goalSelected = false;
        shooterSelected = false;
        spinnerSelected = false;
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
        goalSelected = false;
        shooterSelected = false;
        spinnerSelected = false;
        draw = false;
        booster = true;
        erase = false;
    }


    /**
     * Sets goalSelected to true
     */
    public void goal()
    {
        goalSelected = true;
        shooterSelected = false;
        spinnerSelected = false;
        draw = false;
        booster = false;
        erase = false;
    }


    /**
     * Sets goalSelected to true
     */
    public void shooter()
    {
        goalSelected = false;
        shooterSelected = true;
        spinnerSelected = false;
        draw = false;
        booster = false;
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


    /**
     * erases past 10 lines drawn, or last speed booster
     */
    public void undo()
    {
        super.undo(undo1);
    }


    /**
     * Reads the file from disk
     *
     * @return the file in a string
     */
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

        if (sandHighScore == 999 || result.equals(""))
            showAlertDialog(
                "Previous Times - Current High Score: No Data",
                result);
        else
        {
            showAlertDialog(
                "Previous Times - Current High Score: "
                    + String.valueOf(sandHighScore) + " seconds",
                result);
        }
    }


    /**
     * Sets the highScore to elapesTime if needed.
     */
    private void updateHighScore()
    {
        sandHighScore = elapsedTime;
    }


    /**
     * Reads and writes to the listOfScores file used in superclass method
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
        if (elapsedTime < sandHighScore)
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
            elapsedTimeSand.setText(String.valueOf(timer.getElapsedTimeSecs())
                + " second");
        }
        else
        {
            elapsedTimeSand.setText(String.valueOf(timer.getElapsedTimeSecs())
                + " seconds");
        }
    }
}
