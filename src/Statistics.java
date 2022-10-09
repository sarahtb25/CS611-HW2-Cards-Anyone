import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private static List<RoundHistory> rounds;

    Statistics(){
        rounds = new ArrayList<RoundHistory>();
    }

    public void add(RoundHistory playerHistory){
        rounds.add(playerHistory);
    }
}
