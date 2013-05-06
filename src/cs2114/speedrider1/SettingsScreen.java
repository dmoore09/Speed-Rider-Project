package cs2114.speedrider1;

import android.graphics.Typeface;
import android.widget.TextView;
import sofia.app.ShapeScreen;
import android.content.Context;
import java.io.FileOutputStream;
import java.io.IOException;
import android.widget.Button;
import android.widget.ToggleButton;

/**
 * This class allows the user to set a few values based on how they want to play
 * the game.
 *
 * @author Chris Conley
 * @version Apr 26, 2013
 */
public class SettingsScreen
    extends ShapeScreen
{
    /**
     * This controls whether the music is paused or playing througout the app
     */
    static boolean       isPlaying = true;

    private ToggleButton mute;
    private Button       resetScores;
    private TextView     SettingsTitle;
    private TextView     music;
    private TextView     ResetScores;





    /**
     * State of screen
     */
    public void initialize()
    {
        BackgroundPaper back =
            new BackgroundPaper(0, 0, getWidth(), getHeight());
        back.setSensor(true);
        add(back);

        Typeface typeFace =
            Typeface.createFromAsset(getAssets(), "fonts/roughage.ttf");
        SettingsTitle.setTypeface(typeFace);
        music.setTypeface(typeFace);
        ResetScores.setTypeface(typeFace);
    }


    /**
     * This method turns the music on or off.
     */
    public void muteClicked()
    {
        if (isPlaying)
        {
            isPlaying = false;
            StartScreen.player.pause();
        }
        else
        {
            isPlaying = true;
            StartScreen.player.start();
        }
    }


    /**
     * This method rewrites the listOfScores file to an empty file.
     */
    public void resetScoresClicked()
    {
        FileOutputStream fos;

        try
        {
            String FILENAME = "levelOneTimes.txt";
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write("".getBytes());
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            String FILENAME = "levelTwoTimes.txt";
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write("".getBytes());
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            String FILENAME = "levelThreeTimes.txt";
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write("".getBytes());
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        resetScores.setText("Done");
    }


    /**
     * State of screen on resume
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        if (isPlaying)
        {
            mute.setChecked(true);
        }
        else
        {
            mute.setChecked(false);
        }
    }
}
