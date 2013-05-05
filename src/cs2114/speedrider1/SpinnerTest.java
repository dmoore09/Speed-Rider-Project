package cs2114.speedrider1;
import android.graphics.PointF;
import student.AndroidTestCase;
import sofia.graphics.ShapeView;
/**
 * // -------------------------------------------------------------------------
/**
 *  test spinner colisions
 *
 *  @author Daniel
 *  @version May 5, 2013
 */
public class SpinnerTest
    extends AndroidTestCase<LevelOneScreen>
{
    private ShapeView shapeView;


    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public SpinnerTest()
    {
        super(LevelOneScreen.class);
    }


    /**
     * test the collision for a rider and a spinner from the left
     */
    public void testOnCollision1()
    {
        Spinner spin = new Spinner(25, 25, 35, 45);
        shapeView.add(spin);
        touchDown(shapeView, 100, 100);
        touchUp();

        shapeView.add(spin);

        Rider rider = shapeView.getShapes().locatedAt(10, 10).
            withClass(Rider.class).front();


        PointF vel = rider.getLinearVelocity();

        //tell it to run longer?
        assertTrue(vel.x != 0);
    }

    /**
     * test the collision for a rider and a spinner from the right
     */
    public void testOnCollision2()
    {
        Spinner spin = new Spinner(25, 25, 35, 45);
        shapeView.add(spin);
        touchDown(shapeView, 100, 100);
        touchUp();

        shapeView.add(spin);


        Rider rider = shapeView.getShapes().locatedAt(10, 10).
            withClass(Rider.class).front();
        rider.moveBy(20, 5);
        PointF vel = rider.getLinearVelocity();

        assertTrue(vel.x != 0);
    }
}
