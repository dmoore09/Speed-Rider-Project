package cs2114.speedrider1;

import sofia.app.ShapeScreen;

public class HelpScreen
    extends ShapeScreen
{
    public void initialize()
    {
        BackgroundPaper back =
            new BackgroundPaper(0, 0, getWidth(), getHeight());
        back.setSensor(true);
        add(back);
    }
}
