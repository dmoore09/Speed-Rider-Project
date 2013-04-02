package cs2114.speedrider1;

import sofia.graphics.LineShape;
import android.widget.Button;
import sofia.graphics.ShapeView;
import student.AndroidTestCase;
/**
 * // -------------------------------------------------------------------------
/**
 *  test speed rider methods
 *
 *  @author Daniel
 *  @version Apr 1, 2013
 */
public class SpeedRiderLevelTests
    extends AndroidTestCase<SpeedRiderLevel>
{
    // References to the widgets in layout
    private ShapeView shapeView;
    private Button drawing;
    private Button erasing;
    private Button speedBooster;
    private Button start;

    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public SpeedRiderLevelTests()
    {
        super(SpeedRiderLevel.class);
    }

    /**
     * make sure the lines are drawn correctly
     */
    public void testDraw()
    {
        click(drawing);
        touchDown(shapeView, 100, 100);
        touchMove(200, 100);
        touchUp();

        assertNotNull(shapeView.getShapes().intersecting(100, 100, 200, 100)
                    .withClass(LineShape.class).front());
    }

    /**
     * make sure speedBoosters are drawn correctly
     */
    public void testSpeedBooster()
    {
        click(speedBooster);
        touchDown(shapeView, 100, 100);
        touchUp();

        assertNotNull(shapeView.getShapes().locatedAt(100, 100)
                    .withClass(SpeedBooster.class).front());
    }

    /**
     * make sure the lines are erased correctly
     */
    public void testEraseLines()
    {
        click(drawing);
        touchDown(shapeView, 100, 100);
        touchMove(200, 100);
        touchUp();

        click(erasing);
        touchDown(shapeView, 100, 100);
        touchMove(200, 100);
        touchUp();

        assertNull(shapeView.getShapes().intersecting(100, 100, 200, 100)
                    .withClass(LineShape.class).front());
    }

    /**
     * make sure the speedBoosters are erased correctly
     */
    public void testEraseBooster()
    {
        click(speedBooster);
        touchDown(shapeView, 100, 100);
        touchUp();

        click(erasing);
        touchDown(shapeView, 100, 100);
        touchUp();

        assertNull(shapeView.getShapes().intersecting(100, 100, 200, 100)
                    .withClass(SpeedBooster.class).front());
    }

    /**
     * test start button to make sure the game starts
     */
    public void testStart()
    {
        click(start);
        assertNull(shapeView.getShapes().locatedAt(10, 100)
                    .withClass(Rider.class).front());
    }







}
