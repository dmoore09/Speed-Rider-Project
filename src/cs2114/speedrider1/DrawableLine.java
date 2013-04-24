package cs2114.speedrider1;

import sofia.graphics.LineShape;

/**
 * // -------------------------------------------------------------------------
 * /** This class represents the drawn line
 *
 * @author dmoore09
 * @author chrisc93
 * @version Mar 13, 2013
 */
public class DrawableLine
    extends LineShape
{
    /**
     * create a new draw-able object
     *
     * @param x1
     *            the first x location
     * @param y1
     *            the first y location
     * @param x2
     *            the second x location
     * @param y2
     *            the second y location
     */
    public DrawableLine(float x1, float y1, float x2, float y2)
    {
        super(x1, y1, x2, y2);
        this.setFriction(0);
        this.setStrokeWidth(3.0);
        this.setZIndex(1);
    }
}
