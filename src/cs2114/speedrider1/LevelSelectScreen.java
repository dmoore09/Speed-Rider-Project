package cs2114.speedrider1;

import sofia.app.Persistent;
import sofia.app.Screen;
/**
 * // -------------------------------------------------------------------------
/**
 *  lets player choose what level he would like to play
 *
 *  @author dmoore09
 *  @version Apr 4, 2013
 */
public class LevelSelectScreen
    extends Screen
{

    @Persistent private boolean lvl2 = false;





    /**
     * starts level 1
     */
    public void level1Clicked()
    {
        this.presentScreen(SpeedRiderLevel.class);
    }


    /**
     * starts level 2 if it is unlocked
     */
    public void level2Clicked()
    {
        if (lvl2 == true)
        {
            this.presentScreen(SpeedRiderLevel.class);
        }
        else
        {
            this.showAlertDialog(
                "Level 2 is locked",
                "Beat the previous level to unlock this one.");
        }
    }

    /**
     * When lvl1 is finished it will unlock level 2
     */
    public void speedRiderLevelFinished()
    {
        lvl2 = true;
    }
}
