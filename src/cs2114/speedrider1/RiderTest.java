package cs2114.speedrider1;

import sofia.graphics.ShapeView;
import android.widget.Button;
import student.AndroidTestCase;

/**
 * // -------------------------------------------------------------------------
/**
 *  test case for rider class
 *
 *  @author Daniel
 *  @version Apr 1, 2013
 */
public class RiderTest
    extends AndroidTestCase<SpeedRiderLevel>
{
    private Button start;
    private ShapeView shapeView;

    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public RiderTest()
    {
        super(SpeedRiderLevel.class);
    }

    /**
     * test the collision for a rider and a goal. The rider should be removed
     *  from the screen
     */
    public void testOnCollision()
    {
        click(start);
        Goal goal = new Goal(10, 100, 12);
        shapeView.add(goal);
        assertNull(shapeView.getShapes().locatedAt(10, 100)
            .withClass(Rider.class).front());
    }
}
