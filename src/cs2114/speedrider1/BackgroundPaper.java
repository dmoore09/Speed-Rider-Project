package cs2114.speedrider1;

import sofia.graphics.RectangleShape;

// -------------------------------------------------------------------------
/**
 *  background looks like lined paper
 *
 *  @author Daniel
 *  @version Apr 29, 2013
 */
public class BackgroundPaper
    extends RectangleShape
{

    // ----------------------------------------------------------
    /**
     * Create a new BackgroundPaper object.
     * @param left corner
     * @param top corner
     * @param right corner
     * @param bottom corner
     */
    public BackgroundPaper(float left, float top, float right, float bottom)
    {
        super(left, top, right, bottom);
        setImage("paper");
    }
}
