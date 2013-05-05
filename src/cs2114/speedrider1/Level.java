package cs2114.speedrider1;

import java.util.Stack;
import sofia.graphics.Color;
import sofia.graphics.LineShape;
import sofia.graphics.Shape;
import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * Class that defines common mehtods for levels
 *
 * @author Daniel Moore
 * @version 2013.03.05
 */
public class Level
    extends ShapeScreen
{
    /**
     * create a line segment when player touches down
     *
     * @param newx1
     *            the first x location
     * @param newy1
     *            the first y location
     * @param booster
     *            allows boosters to be created
     * @param started
     *            if game is started
     * @param rider
     *            that is main object of the game
     * @param undo1
     *            stack to store method calls
     * @param x1
     *            of touch
     * @param y1
     *            of touch
     * @param timer times player for score purposes
     */
    public void onTouchDown(
        float newx1,
        float newy1,
        boolean booster,
        boolean started,
        Rider rider,
        Stack<Shape> undo1,
        float x1,
        float y1,
        StopWatch timer)
    {

        // if booster is true add a speed booster at location
        if (booster == true)
        {
            SpeedBooster boost = new SpeedBooster(newx1, newy1);
            this.add(boost);
            undo1.push(boost);
        }

        // make sure a rider was found to start
        if (!started)
        {
            this.start(started, rider);
            timer.start();
        }

        if (rider.getRemoved())
        {
            this.updateTime();
            boolean x = true;
            this.finish(x);

        }
    }


    /**
     * When touch is released, the x and y coordinates at the end of the line
     * are drawn
     * @param newX x coord
     * @param newY y coord
     * @param x1 coord field
     * @param y1 coord field
     * @param draw true if draw mode is active
     * @param erase true if erase mode is active
     * @param undo1 stack to hold objects drawn
     */
    public void onTouchMove(float newX,
        float newY,
        float x1,
        float y1,
        boolean draw,
        boolean erase,
        Stack<Shape> undo1)
    {
        this.processTouch(x1, y1, newX, newY, draw, erase, undo1);
//        x1 = newX;
//        y1 = newY;
    }


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
     * @param draw
     *            whether or not lines are drawn
     * @param erase
     *            whether or not lines are erased
     * @param undo1
     *            stack to hold objects drawn
     */
    public void processTouch(
        float newx1,
        float newy1,
        float newx2,
        float newy2,
        boolean draw,
        boolean erase,
        Stack<Shape> undo1)
    {
        // if draw is true create lines
        if (draw == true)
        {
            DrawableLine segment = new DrawableLine(newx1, newy1, newx2, newy2);
            segment.setColor(Color.black);
            this.add(segment);
            undo1.push(segment);
        }
        // if draw is false erase lines
        else if (erase == true)
        {
            // get line at location
            LineShape segment =
                getShapes().intersecting(newx1, newy1, newx2, newy2)
                    .withClass(LineShape.class).front();

            // get booster at location
            SpeedBooster booster1 =
                getShapes().locatedAt(newx1, newy1)
                    .withClass(SpeedBooster.class).front();

            // make sure an item was found to remove
            if (segment != null)
            {
                segment.remove();
            }

            // make sure booster is present
            if (booster1 != null)
            {
                booster1.remove();
            }
        }
    }

    /**
     * This lets the player control when the animation starts
     *
     * @param started
     * @param rider
     */
    public void start(boolean started, Rider rider)
    {
        while (!started)
        {
            // apply a force to get the rider moving
            rider.setGravityScale(1);
            Rider.wheel1.setGravityScale(1f);
            Rider.wheel2.setGravityScale(1f);
            rider.applyLinearImpulse(0, 20000);
        }
    }


    /**
     * erases past 30 lines drawn, or last speed booster
     * @param undo1 stack that holds shapes drawn on the screen
     */
    public void undo(Stack<Shape> undo1)
    {
        Shape lastShape = undo1.peek();
        // see if the shape is a line or a speed booster
        if (lastShape instanceof DrawableLine)
        {
            // remove last 10 lines
            if (undo1.size() >= 10)
            {
                for (int i = 0; i < 10; i++)
                {
                    Shape booster1 = undo1.pop();

                    if (booster1 instanceof SpeedBooster)
                    {
                        break;
                    }
                    else
                    {
                        booster1.remove();
                    }
                }
            }
            // remove the rest of the lines if there are less then 10
            else if (undo1.size() != 0)
            {
                for (int i = 0; i < undo1.size(); i++)
                {
                    undo1.pop();
                }
            }
        }
        else if (undo1.size() != 0)
        {
            undo1.pop();
        }
    }

    /**
     * update the timer
     */
    private void updateTime()
    {
        //defined in subclasses
    }
}


