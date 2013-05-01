package cs2114.speedrider1;

import android.media.MediaPlayer;
import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * The Startup Screen of the App
 *
 * @author Daniel
 * @version Mar 20, 2013
 */
public class StartScreen
    extends ShapeScreen
{

    /**
     * This allows music to be played throughout the app. It is static so all
     * class can access it. It playes the background music.
     */
    static MediaPlayer player;
    private Rider rider1;


    /**
     * initialize the screen
     */
    public void initialize()
    {
        player = MediaPlayer.create(this, R.raw.intro);
        player.start();
        player.setLooping(true);

        BackgroundPaper back = new BackgroundPaper(0, 0, getWidth(),
            getHeight());
        back.setSensor(true);
        add(back);

        this.setGravity(0, 9.8f);

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

        rider1 = new Rider(50f, 50f);
        rider1.setLinearVelocity(100f, 200f);
        rider1.setGravityScale(1.0f);
        rider1.setRestitution(1.0f);
        this.add(rider1);


    }

    public void afterInitialize()
    {
        rider1.finishRider();
    }


    /**
     * This lets the player start the game
     */
    public void startClicked()
    {
        this.presentScreen(LevelSelectScreen.class);
    }


    /**
     * Brings up the settings menu.
     */
    public void settingsClicked()
    {
        this.presentScreen(SettingsScreen.class);
    }


    /**
     * Method is called then the user presses the back button. Closes app.
     */
    public void onBackPressed()
    {
        player.stop();
        this.finish();
    }
}
