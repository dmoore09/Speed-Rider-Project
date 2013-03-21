package cs2114.speedrider1;

import sofia.app.Screen;

// -------------------------------------------------------------------------
/**
 *  The Startup Screen of the App
 *
 *  @author Daniel
 *  @version Mar 20, 2013
 */
public class StartScreen
    extends Screen
{

    /**
     * initialize the screen
     */
    public void initialize()
    {
        //
    }

    /**
     * This lets the player start the game
     */
    public void startClicked()
    {
        this.presentScreen(SpeedRiderLevel.class, null);
    }
}
