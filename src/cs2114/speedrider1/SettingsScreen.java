package cs2114.speedrider1;

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


    /**
     * This method turs the music on or off.
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
