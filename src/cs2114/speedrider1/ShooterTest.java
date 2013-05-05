package cs2114.speedrider1;

import android.graphics.PointF;
import java.util.Set;
import cs2114.speedrider1.Shooter.Bullet;
import sofia.graphics.ShapeView;
import student.AndroidTestCase;
/**
 * // -------------------------------------------------------------------------
/**
 *  test shooter methods
 *
 *  @author Daniel
 *  @version May 5, 2013
 */
public class ShooterTest
    extends AndroidTestCase<LevelOneScreen>
{

    private ShapeView shapeView;
    private Shooter shoot;


    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public ShooterTest()
    {
        super(LevelOneScreen.class);


    }

    /**
     * Test shooter when shooting left
     */
    public void testShooterLeft()
    {
        Shooter shoot1 = new Shooter(150, 150, 160, 165, true);
        shapeView.add(shoot1);

        touchDown(shapeView, 100, 100);
        touchUp();

        Set<Bullet> bullets =
            shapeView.getShapesInRange(155, 155, 200, Bullet.class);
        assertFalse(bullets.isEmpty());



    }

    /**
     * Test shooter when shooting right
     */
    public void testShooterRight()
    {

        shoot = new Shooter(150, 150, 160, 160, false);
        shapeView.add(shoot);

        touchDown(shapeView, 100, 100);
        touchUp();

        Set<Bullet> bullets =
            shapeView.getShapesInRange(155, 155, 50, Bullet.class);
        assertFalse(bullets.isEmpty());
    }

    /**
     * Make sure a linear impulse is applied
     */
    public void testBulletColision()
    {
        Rider rider = new Rider(100, 100);
        shapeView.add(rider);
        rider.finishRider();
//        shoot = new Shooter(100, 100, 110, 110, false);
//        shapeView.add(shoot);

        touchDown(shapeView, 100, 100);
        touchUp();
        PointF vel = rider.getLinearVelocity();

        assertTrue(vel.x != 0);
    }


}
