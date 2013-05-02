package cs2114.speedrider1;

import sofia.graphics.Color;
import sofia.graphics.OvalShape;
/**
 * // -------------------------------------------------------------------------
/**
 *  the goal that a player must reach in order to finish any given level
 *
 *  @author dmoore09
 *  @version Mar 11, 2013
 */
public class Goal
    extends OvalShape
{

    /**
     * creates a goal for the player
     * @param x position of center of goal
     * @param y position of center of goal
     * @param radius of goal
     */
    public Goal(float x, float y, float radius)
    {
        super(x, y, radius);
        this.setFilled(true);
        this.setColor(Color.green);
        this.setZIndex(10);
    }

    /**
     * Place a description of your method here.
     * @param oval that collides
     */
    public void onCollisionWith(OvalShape oval)
    {
        oval.remove();
    }
}
