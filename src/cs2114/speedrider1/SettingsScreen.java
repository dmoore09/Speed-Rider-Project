package cs2114.speedrider1;

import android.content.Context;
import java.io.FileOutputStream;
import java.io.IOException;
import android.widget.Button;
import android.widget.ToggleButton;
import sofia.app.Screen;

/**
 * This class allows the user to set a few values based on how they want to play
 * the game.
 *
 * @author Chris Conley
 * @version Apr 26, 2013
 */
public class SettingsScreen
    extends Screen
{
    /**
     * This controls whether the music is paused or playing througout the app
     */
    static boolean       isPlaying = true;

    private ToggleButton mute;
    private Button       resetScores;


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
        String FILENAME = "listOfTimes";
        try
        {
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
