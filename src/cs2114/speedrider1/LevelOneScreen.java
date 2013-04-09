package cs2114.speedrider1;

import cs2114.speedrider1.DrawableLine;
import cs2114.speedrider1.Goal;
import cs2114.speedrider1.Ground;
import cs2114.speedrider1.Rider;
import sofia.graphics.LineShape;
import sofia.app.ShapeScreen;
import sofia.graphics.Color;

// -------------------------------------------------------------------------
/**
 * The main gameplay screen for the Irritated Avians game.
 *
 * @author Tony Allevato
 * @version 2013.03.05
 */
public class LevelOneScreen
    extends ShapeScreen
    implements LevelInterface
{
    private Rider   rider;
    private Goal    goal;

    // keeps track of how many segments are left to draw
    private int     segmentAmount;

    private float   x1;
    private float   x2;
    private float   y1;
    private float   y2;

    // keeps track of whether or not the player is drawing, erasing, adding
    // a speed booster, or started the animation
    private boolean draw;
    private boolean erase;
    private boolean booster;
    private boolean started;


    // ~ Public methods ........................................................

    // ----------------------------------------------------------

    /**
     * Initializes the state of the screen: its background color, the coordinate
     * system, gravity, and the shapes in the field. Automatically defines the
     * amount of line segments that can be drawn as 500
     */
    public void initialize()
    {
        draw = true;
        booster = false;
        segmentAmount = 500;
        started = false;

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
        goal = new Goal(this.getWidth() - 500, this.getHeight() - 50, 30);
        this.add(goal);

        // set the gravity level for the course
        this.setGravity(0, 20f);

        // create a new rider
        rider = new Rider(10, 100);
        this.add(rider);
        rider.setGravityScale(0);
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
        this.x1 = newx1;
        this.y1 = newy1;
    }


    // ----------------------------------------------------------
    /**
     * When touch is released, the x and y coordinates at the end of the line
     * are drawn
     *
     * @param newx2
     *            the second x location
     * @param newy2
     *            the second y location
     */
    public void onTouchUp(float newx2, float newy2)
    {
        this.x2 = newx2;
        this.y2 = newy2;

        this.processTouch(x1, y1, x2, y2);
        segmentAmount = segmentAmount - 1;

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
        // if draw is true create lines
        if (draw == true)
        {
            if (segmentAmount != 0)
            {
                DrawableLine segment =
                    new DrawableLine(newx1, newy1, newx2, newy2);
                segment.setColor(Color.black);
                this.add(segment);
            }
        }
        // if draw is false erase lines
        else if (erase == true)
        {
            // get line at location
            LineShape segment =
                getShapes().intersecting(newx1, newy1, newx2, newy2)
                    .withClass(LineShape.class).front();

            // get booster at location
            SpeedBooster booster1 =
                getShapes().locatedAt(newx1, newy1)
                    .withClass(SpeedBooster.class).front();

            // make sure an item was found to remove
            if (segment != null)
            {
                segment.remove();
            }

            // make sure booster is present
            if (booster1 != null)
            {
                booster1.remove();
            }

        }
        // if booster is true add a speed booster at location
        else if (booster == true)
        {
            SpeedBooster boost = new SpeedBooster(newx1, newy1);
            this.add(boost);
        }
        if (rider.getRemoved())
        {

            boolean x = true;
            this.finish(x);

        }

    }


    /**
     * set draw field to true when draw button is clicked. set all others to
     * false
     */
    public void drawingClicked()
    {
        draw = true;
        booster = false;
        erase = false;
    }


    /**
     * set erase field to true when erase button is clicked. set all others to
     * false
     */
    public void erasingClicked()
    {
        draw = false;
        booster = false;
        erase = true;
    }


    /**
     * set booster field to true when booster button is clicked. set all others
     * to false
     */
    public void speedBoosterClicked()
    {
        draw = false;
        booster = true;
        erase = false;
    }


    /**
     * This lets the player control when the animation starts
     */
    public void startClicked()
    {
        while (!started)
        {
            // apply a force to get the rider moving
            rider.setGravityScale(1);
            rider.applyLinearImpulse(30000, 20000);
            started = true;
        }
    }

}
