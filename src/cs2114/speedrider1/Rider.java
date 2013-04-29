package cs2114.speedrider1;

import sofia.graphics.Color;
import sofia.graphics.ShapeMotion;
import sofia.graphics.OvalShape;

/**
 * // -------------------------------------------------------------------------
 * /** Write a one-sentence summary of your class here. Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 *
 * @author Daniel
 * @version Mar 10, 2013
 */
public class Rider
    extends OvalShape
{

    //boolean to see if the rider is removed
    private boolean isRemoved;

    // ------------------------------------------------------------------------



    /**
     * creates a new rider
     *
     * @param x
     *            position of the rider
     * @param y
     *            position of the rider
     */
    public Rider(float x, float y)
    {

        super(x, y, 8.5f);
        isRemoved = false;
        this.setFilled(true);
        this.setColor(Color.red);

        this.setShapeMotion(ShapeMotion.DYNAMIC);
        this.setDensity(1);
        this.setRestitution(0.3f);
        this.setFriction(0.1f);
    }


    /**
     * When the rider collides with the goal end the level
     *
     * @param finish
     *            is the goal the player is trying to navigate the rider to
     */
    public void onCollisionWith(Goal finish)
    {
        isRemoved = true;

        // When rider collides with the goal end the level
        this.remove();

    }

    /**
     * return value of is removed
     * @return true if removed, false if not
     */
    public boolean getRemoved()
    {
        return isRemoved;
    }

}
