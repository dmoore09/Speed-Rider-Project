package cs2114.speedrider1;

import sofia.graphics.ShapeView;
import student.AndroidTestCase;

/**
 * // -------------------------------------------------------------------------
 * /** test case for rider class
 *
 * @author Daniel
 * @version Apr 1, 2013
 */
public class RiderTest
    extends AndroidTestCase<LevelOneScreen>
{
    private ShapeView shapeView;


    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public RiderTest()
    {
        super(LevelOneScreen.class);
    }


    /**
     * test the collision for a rider and a goal. The rider should be removed
     * from the screen
     */
    public void testOnCollision()
    {
        touchDown(shapeView, 100, 100);
        touchUp();
        Goal goal = new Goal(10, 10, 12);
        shapeView.add(goal);
        assertNotNull(shapeView.getShapes().locatedAt(10, 10)
            .withClass(Rider.class).front());
    }
}
