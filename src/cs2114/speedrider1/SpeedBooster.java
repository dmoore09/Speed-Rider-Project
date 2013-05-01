package cs2114.speedrider1;

import cs2114.speedrider1.Rider;
import sofia.graphics.OvalShape;
import android.graphics.PointF;
import sofia.graphics.Color;
/**
 * // -------------------------------------------------------------------------
/**
 *  a object that gives the rider a massive speed boost
 *
 *  @author dmoore09
 *  @version Mar 12, 2013
 */
public class SpeedBooster
    extends OvalShape
{

    /**
     * initialize speed booster
     * @param x coord of center
     * @param y coord of center
     */
    public SpeedBooster(float x, float y)
    {
        super(x, y, 5);
        this.setColor(Color.gold);
        this.setStrokeWidth(3.0);
        this.setSensor(true);
    }

    /**
     * When the rider collides with speed booster apply a force to the rider
     *
     * @param player is the rider that collides with the speed booster
     */
    public void onCollisionWith(Rider player)
    {
        //get the linear velocity of the rider
        PointF velocity = player.getLinearVelocity();


        //apply a linear impulse in the same direction as the player's linear
        //velocity
        player.getWheel().
        applyLinearImpulse(velocity.x * 5000000, velocity.y * 5000000);

        this.remove();
    }

}
