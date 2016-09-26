package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 25/09/2016.
 */
public class RankingItem {

    public String name;
    public int points;
    public int position;
    RankingItem(int position, String name, int points){
        this.position = position;
        this.name = name;
        this.points = points;
    }
}
