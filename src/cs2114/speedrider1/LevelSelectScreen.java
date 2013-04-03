package cs2114.speedrider1;

import sofia.app.Screen;

public class LevelSelectScreen
    extends Screen
{
    private SpeedRiderLevel lvl = new SpeedRiderLevel();


    public void level1Clicked()
    {
        this.presentScreen(SpeedRiderLevel.class);
    }


    public void level2Clicked()
    {
        if (lvl.isUnlocked())
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
}
