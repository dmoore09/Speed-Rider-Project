package cs2114.speedrider1;

import java.util.ArrayList;
import sofia.app.Persistent;
import sofia.app.Screen;

public class LevelOneScores extends Screen
{
    @Persistent
    private ArrayList<String> scoreList = new ArrayList<String>();

    String FILENAME = "score_list";

    public void addScore(String score)
    {
        scoreList.add(score);
    }

    public int size()
    {
        return scoreList.size();
    }

    public String toString()
    {
        String string = "";
        int i = 0;
        for (String object : scoreList)
        {
            if (i < scoreList.size() - 1)
            {
                string = string + object + " seconds\n";
            }
            else
            {
                string = string + object + " seconds";
            }
            i++;
        }
        string = string + "";
        return string;
    }
}
