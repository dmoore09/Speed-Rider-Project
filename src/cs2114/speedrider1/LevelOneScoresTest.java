package cs2114.speedrider1;

public class LevelOneScoresTest extends student.TestCase
{
    private LevelOneScores scores;

    public void setUp()
    {
        scores = new LevelOneScores();
        String score = "20";
        scores.addScore(score);
    }

    public void testAdd()
    {
        assertEquals(1, scores.size());
    }

    public void testToString()
    {
        scores.addScore("50");
        assertEquals("{20 seconds\n50 seconds}", scores.toString());
    }
}
