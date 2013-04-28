package cs2114.speedrider1;

import sofia.app.Persistent;
import sofia.app.Screen;

/**
 * // -------------------------------------------------------------------------
 * /** lets player choose what level he would like to play
 *
 * @author dmoore09
 * @version Apr 4, 2013
 */
public class LevelSelectScreen
    extends Screen
{

    @Persistent
    private boolean lvl2 = false;

    @Persistent
    private boolean lvl3 = false;

    @Persistent
    private boolean lvl4 = false;

    @Persistent
    private boolean lvl5 = false;


    /**
     * starts level 1
     */
    public void level1Clicked()
    {
        StartScreen.player.pause();
        this.presentScreen(LevelOneScreen.class);
    }


    /**
     * starts level 2 if it is unlocked
     */
    public void level2Clicked()
    {
        if (lvl2 == true)
        {
            StartScreen.player.pause();
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
            StartScreen.player.pause();
            this.presentScreen(LevelTwoScreen.class);
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
            StartScreen.player.pause();
            this.presentScreen(LevelTwoScreen.class);
        }
        else
        {
            this.showAlertDialog(
                "Level 4 is locked",
                "Beat the previous level to unlock this one.");
        }
    }


    /**
     * starts level 5 if it is unlocked
     */
    public void level5Clicked()
    {
        if (lvl5 == true)
        {
            StartScreen.player.pause();
            this.presentScreen(LevelTwoScreen.class);
        }
        else
        {
            this.showAlertDialog(
                "Level 5 is locked",
                "Beat the previous level to unlock this one.");
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


    /**
     * When lvl1 is finished it will unlock level 5
     *
     * @param finished
     *            tells wheter or not level 4 was finished
     */
    public void levelFourScreenFinished(boolean finished)
    {
        lvl5 = finished;
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        if (SettingsScreen.isPlaying == true)
        {
            StartScreen.player.start();
        }
    }
}
