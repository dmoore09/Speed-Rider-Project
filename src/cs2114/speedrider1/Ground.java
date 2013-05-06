package cs2114.speedrider1;

import sofia.graphics.LineShape;

// -------------------------------------------------------------------------
/**
 * Represents the ground.
 *
 * @author Daniel Moore
 * @author Chris Conley
 * @author Harjas Singh
 * @version 2013.03.05
 */
public class Ground
    extends LineShape
{
    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a new ground segment with the specified coordinates.
     *
     * @param left
     *            the x-coordinate of the first point
     * @param top
     *            the y-coordinate of the first point
     * @param right
     *            the x-coordinate of the second point
     * @param bottom
     *            the y-coordinate of the second point
     */
    public Ground(float left, float top, float right, float bottom)
    {
        super(left, top, right, bottom);
        setFriction(0);
    }
}
