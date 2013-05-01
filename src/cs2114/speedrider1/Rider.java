package cs2114.speedrider1;

import android.graphics.PointF;
import sofia.graphics.Anchor;
import sofia.graphics.DistanceJoint;
import sofia.graphics.RectangleShape;
import sofia.graphics.Color;
import sofia.graphics.ShapeMotion;
import sofia.graphics.OvalShape;

/**
 * // -------------------------------------------------------------------------
 * the main object the player is trying to manipulate. To beat a level the
 * player must reach the goal
 *
 * @author Daniel
 * @version Mar 10, 2013
 */
public class Rider
    extends RectangleShape
{

    //boolean to see if the rider is removed
    private boolean isRemoved;

    // ------------------------------------------------------------------------
    private OvalShape wheel1;
    private OvalShape wheel2;



    /**
     * creates a new rider. Rectangle shape represents the body. oval shapes
     * for wheels, and more rectangles for arms all are jointed together
     *
     * @param x
     *            position of center the rider
     * @param y
     *            position of center the rider
     */
    public Rider(float x, float y)
    {

        //main body where force is applied
        super(x - 10, y - 8, x + 10, y + 4);

        isRemoved = false;
        this.setFilled(true);
        this.setColor(Color.red);
        PointF point = new PointF(x, y - 8);
        this.setPositionAnchor(point);

        this.setShapeMotion(ShapeMotion.DYNAMIC);
        this.setDensity(1);
        this.setRestitution(0.3f);
        this.setFriction(0.1f);



        //oval shapes for wheels
        wheel1 = new OvalShape(x + 8, y + 9, 6.5f);
        wheel2 = new OvalShape(x - 8, y + 9, 6.5f);

        wheel1.setFilled(true);
        wheel1.setColor(Color.black);

        wheel1.setShapeMotion(ShapeMotion.DYNAMIC);
        wheel1.setDensity(1);
        wheel1.setRestitution(0.3f);
        wheel1.setFriction(0.1f);

        wheel2.setFilled(true);
        wheel2.setColor(Color.black);

        wheel2.setShapeMotion(ShapeMotion.DYNAMIC);
        wheel2.setDensity(1);
        wheel2.setRestitution(0.3f);
        wheel2.setFriction(0.1f);

        wheel1.setGravityScale(1.0f);
        wheel2.setGravityScale(1.0f);



    }

    /**
     * finish rider and create joints
     */
    public void finishRider()
    {


        this.getShapeField().add(wheel1);
        this.getShapeField().add(wheel2);

        //connect to each other
        DistanceJoint wheelJoint = new DistanceJoint(wheel1, wheel2,
            Anchor.CENTER, Anchor.CENTER);
        wheelJoint.setCanShapesCollide(false);
        wheelJoint.connect();

        //connect to body
        DistanceJoint wBodyJ1 = new DistanceJoint(wheel1, this,
            Anchor.CENTER, Anchor.CENTER);
        DistanceJoint wBodyJ2 = new DistanceJoint(wheel2, this,
            Anchor.CENTER, Anchor.CENTER);
        wBodyJ1.setCanShapesCollide(false);
        wBodyJ1.connect();
        wBodyJ2.setCanShapesCollide(false);
        wBodyJ2.connect();

        DistanceJoint wBodyJ3 = new DistanceJoint(wheel1, this,
            Anchor.CENTER, Anchor.BOTTOM_RIGHT);
        DistanceJoint wBodyJ4 = new DistanceJoint(wheel2, this,
            Anchor.CENTER, Anchor.BOTTOM_LEFT);
        wBodyJ3.setCanShapesCollide(false);
        wBodyJ3.connect();
        wBodyJ4.setCanShapesCollide(false);
        wBodyJ4.connect();

//
//
//        //animate wheels
//        wheel1.animate(200).repeat().rotation(360);
//        wheel2.animate(200).repeat().rotation(360);

    }


    /**
     * When the rider collides with the goal end the level
     *
     * @param finish
     *            is the goal the player is trying to navigate the rider to
     */
    public void onCollisionWith(Goal finish)
    {
        isRemoved = true;

        // When rider collides with the goal end the level
        this.remove();
        wheel1.remove();
        wheel2.remove();

    }

    /**
     * return value of is removed
     * @return true if removed, false if not
     */
    public boolean getRemoved()
    {
        return isRemoved;
    }

    /**
     * returns all component shapes that make up the rider
     * @return list of shapes
     */
    public OvalShape getWheel()
    {

        return wheel1;
    }

}
