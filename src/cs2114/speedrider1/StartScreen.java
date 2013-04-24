package cs2114.speedrider1;

import android.app.Activity;
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
     * initialize the screen
     */
    public void initialize()
    {
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

        Rider rider1 = new Rider(50f, 50f);
        rider1.setLinearVelocity(100f, 200f);
        rider1.setGravityScale(1.0f);
        rider1.setRestitution(1.0f);
        this.add(rider1);
    }


    /**
     * This lets the player start the game
     */
    public void start1Clicked()
    {
        this.presentScreen(LevelSelectScreen.class);
    }
}
