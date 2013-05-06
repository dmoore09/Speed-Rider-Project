package cs2114.speedrider1;

import android.graphics.Typeface;
import android.widget.TextView;
import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * Assits the player if they need help
 *
 * @author Daniel Moore
 * @author Chris Conley
 * @author Harjas Singh
 * @version May 5, 2013
 */
public class HelpScreen
    extends ShapeScreen

{
    private TextView help1;


    /**
     * State of the help screen
     */
    public void initialize()
    {
        BackgroundPaper back =
            new BackgroundPaper(0, 0, getWidth(), getHeight());
        back.setSensor(true);
        add(back);

        Typeface typeFace =
            Typeface.createFromAsset(getAssets(), "fonts/roughage.ttf");
        help1.setTypeface(typeFace);

    }
}
