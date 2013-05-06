package cs2114.speedrider1;

import sofia.app.ShapeScreen;
import android.graphics.Typeface;
import android.widget.TextView;
import sofia.app.Persistent;

/**
 * // -------------------------------------------------------------------------
 * /** lets player choose what level he would like to play
 *
 * @author dmoore09
 * @version Apr 4, 2013
 */
public class LevelSelectScreen
    extends ShapeScreen
{

    @Persistent
    private boolean  lvl2 = false;

    @Persistent
    private boolean  lvl3 = false;

    @Persistent
    private boolean  lvl4 = false;

    private TextView level1;

    private TextView level2;
    private TextView level3;
    private TextView LevelSelectTitle;


    /**
     * Initializes the state of the Level select screen
     */
    public void initialize()
    {
        BackgroundPaper back =
            new BackgroundPaper(0, 0, getWidth(), getHeight());
        back.setSensor(true);
        add(back);

        Typeface typeFace =
            Typeface.createFromAsset(getAssets(), "fonts/roughage.ttf");
        level1.setTypeface(typeFace);
        level2.setTypeface(typeFace);
        level3.setTypeface(typeFace);
        LevelSelectTitle.setTypeface(typeFace);

    }


    /**
     * starts level 1
     */
    public void level1Clicked()
    {
        this.presentScreen(LevelOneScreen.class);
    }


    /**
     * starts level 2 if it is unlocked
     */
    public void level2Clicked()
    {
        if (lvl2 == true)
        {
            this.presentScreen(LevelTwoScreen.class);
        }
        else
        {
            this.showAlertDialog(
                "Level 2 is locked",
                "Beat the previous level to unlock this one.");
        }
    }


    /**
     * starts level 3 if it is unlocked
     */
    public void level3Clicked()
    {
        if (lvl3 == true)
        {
            this.presentScreen(LevelThreeScreen.class);
        }
        else
        {
            this.showAlertDialog(
                "Level 3 is locked",
                "Beat the previous level to unlock this one.");
        }
    }


    /**
     * starts level 4 if it is unlocked
     */
    public void level4Clicked()
    {
        if (lvl4 == true)
        {
            this.presentScreen(LevelTwoScreen.class);
        }
    }


    /**
     * When lvl1 is finished it will unlock level 2
     *
     * @param finished
     *            tells wheter or not level 1 was finished
     */
    public void levelOneScreenFinished(boolean finished)
    {
        lvl2 = finished;
    }


    /**
     * When lvl1 is finished it will unlock level 3
     *
     * @param finished
     *            tells wheter or not level 2 was finished
     */
    public void levelTwoScreenFinished(boolean finished)
    {
        lvl3 = finished;
    }


    /**
     * When lvl1 is finished it will unlock level 4
     *
     * @param finished
     *            tells wheter or not level 3 was finished
     */
    public void levelThreeScreenFinished(boolean finished)
    {
        lvl4 = finished;
    }
}
