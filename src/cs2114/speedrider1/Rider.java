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

    /**
     * creates a new rider
     * @param x position of the rider
     * @param y position of the rider
     */
    public Rider(float x, float y)
    {
        super(x, y, 6);
        this.setFilled(true);
        this.setColor(Color.red);

        this.setShapeMotion(ShapeMotion.DYNAMIC);
        this.setDensity(2);
        this.setRestitution(0.1f);
        this.setFriction(2.0f);
        this.setGravityScale(1);
        this.setLinearVelocity(0, 0);
    }


    /**
     * When the rider collides with the goal end the level
     *
     * @param finish
     *            is the goal the player is trying to navigate the rider to
     */
    public void onCollisionWith(Goal finish)
    {
        // When rider collides with the goal end the level
        this.remove();

    }

}
