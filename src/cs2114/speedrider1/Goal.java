package cs2114.speedrider1;

import sofia.graphics.OvalShape;

/**
 * // -------------------------------------------------------------------------
 * /** the goal that a player must reach in order to finish any given level
 *
 * @author Daniel Moore
 * @author Chris Conley
 * @author Harjas Singh
 * @version Mar 11, 2013
 */
public class Goal
    extends OvalShape
{

    /**
     * creates a goal for the player
     *
     * @param x
     *            position of center of goal
     * @param y
     *            position of center of goal
     * @param radius
     *            of goal
     */
    public Goal(float x, float y, float radius)
    {
        super(x, y, radius);
        this.setZIndex(10);
        setImage("goal");
    }


    /**
     * Place a description of your method here.
     *
     * @param oval
     *            that collides
     */
    public void onCollisionWith(OvalShape oval)
    {
        oval.remove();

    }
}
