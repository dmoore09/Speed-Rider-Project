package cs2114.speedrider1;
/**
 * // -------------------------------------------------------------------------
/**
 *  moves up when a player collides with it. Used to get player to higher
 *  parts of the screen
 *
 *  @author Daniel
 *  @version May 6, 2013
 */
public class Elevator
    extends DrawableLine
{
    private DrawableLine wall;

    /**
     * initialize the shape. Creates floor, and a wall to stop the rider from
     * moving
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
    public Elevator(float x1, float y1, float x2, float y2)
    {
        super(x1, y1, x2, y2);
        this.getShapeField().add(this);
        this.setStrokeWidth(15);
        wall = new DrawableLine(x2, y2, x2, y2 + 15);
    }

    /**
     * adds the wall. Must be called after initialize to avoid null
     * pointer exceptions
     */
    public void addWall()
    {
        this.getShapeField().add(wall);

    }

    /**
     * when it collides with a rider move up the screen
     * @param player rider in the game
     */
    public void onCollisionWith(Rider player)
    {
        this.getShapeField().remove(wall);

        while (this.getY() < 50)
        {
            this.moveBy(0, 3);
        }
    }
}
