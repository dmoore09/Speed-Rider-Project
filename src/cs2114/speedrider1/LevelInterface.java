package cs2114.speedrider1;

// -------------------------------------------------------------------------
/**
 * The main gameplay screen for the Irritated Avians game.
 *
 * @author Tony Allevato
 * @version 2013.03.05
 */
public interface LevelInterface
{
    /**
     * create a line segment when player touches down
     *
     * @param newx1
     *            the first x location
     * @param newy1
     *            the first y location
     */
    public void onTouchDown(float newx1, float newy1);


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
    public void onTouchUp(float newx2, float newy2);


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
    public void processTouch(float newx1, float newy1, float newx2, float newy2);


    /**
     * set draw field to true when draw button is clicked. set all others to
     * false
     */
    public void drawingClicked();


    /**
     * set erase field to true when erase button is clicked. set all others to
     * false
     */
    public void erasingClicked();


    /**
     * set booster field to true when booster button is clicked. set all others
     * to false
     */
    public void speedBoosterClicked();


    /**
     * This lets the player control when the animation starts
     */
    public void startClicked();

}
