package cs2114.speedrider1;

import sofia.graphics.OvalShape;
import sofia.graphics.Color;

// -------------------------------------------------------------------------
/**
 *  an object that affects the gravity around a player
 *
 *  @author Daniel
 *  @version Apr 29, 2013
 */
public class GravityField
    extends OvalShape
{

    // ----------------------------------------------------------
    /**
     * Create a new GravityField object.
     * @param left corner
     * @param top corner
     * @param right corner
     * @param bottom corner
     */
    public GravityField(float x, float y)
    {
        super(x, y, 20);

        setGravityScale(0);
        setColor(Color.aqua);

        //create a sensor for field to be set off
        setSensor(true);
        setStrokeWidth(3);
    }

    /**
     * change gravity when player enters field
     * @param player in game
     */
    public void onCollisionWith(Rider player)
    {

    }

    /**
     * pulls player towards it
     */
    public void ChangeGravity()
    {

    }

}
