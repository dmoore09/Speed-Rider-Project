package cs2114.speedrider1;

import android.graphics.Typeface;
import android.widget.TextView;
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
    private Rider      rider1;
    private TextView   title;
    private TextView   start;
    private TextView   settings;
    private TextView   help;


    /**
     * initialize the screen
     */
    public void initialize()
    {
        Typeface typeFace =
            Typeface.createFromAsset(getAssets(), "fonts/roughage.ttf");
        title.setTypeface(typeFace);
        start.setTypeface(typeFace);
        help.setTypeface(typeFace);
        settings.setTypeface(typeFace);

        player = MediaPlayer.create(this, R.raw.intro);
        player.start();
        player.setLooping(true);

        BackgroundPaper back =
            new BackgroundPaper(0, 0, getWidth(), getHeight());
        back.setSensor(true);
        add(back);

        this.setGravity(0, 9.8f);

        Ground bound1 = new Ground(0, 0, this.getWidth(), 0);
        this.add(bound1);

        bound1.setRestitution(3);

        Ground bound2 = new Ground(0, 0, 0, this.getHeight());
        this.add(bound2);

        bound2.setRestitution(3);

        Ground bound3 =
            new Ground(0, this.getHeight(), this.getWidth(), this.getHeight());
        this.add(bound3);

        bound3.setRestitution(3);

        Ground bound4 =
            new Ground(this.getWidth(), 0, this.getWidth(), this.getHeight());
        this.add(bound4);

        bound4.setRestitution(3);

        rider1 = new Rider(50f, 50f);

        rider1.setGravityScale(0);
        rider1.setRestitution(1.0f);
        this.add(rider1);

    }


    /**
     * State of screen after initialization
     */
    public void afterInitialize()
    {
        rider1.finishRider();
        rider1.setGravityScale(1);
        Rider.wheel1.setGravityScale(1f);
        Rider.wheel2.setGravityScale(1f);
        rider1.setLinearVelocity(50f, 100f);
    }


    /**
     * This lets the player start the game
     */
    public void startClicked()
    {
        this.presentScreen(LevelSelectScreen.class);
        this.finish();
    }


    /**
     * Brings up the settings menu.
     */
    public void settingsClicked()
    {
        this.presentScreen(SettingsScreen.class);
    }


    /**
     * Brings up the help menu.
     */
    public void helpClicked()
    {
        this.presentScreen(HelpScreen.class);
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
