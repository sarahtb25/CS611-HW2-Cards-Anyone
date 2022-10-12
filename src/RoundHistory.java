import java.util.ArrayList;
import java.util.List;
// To store history of each round
public class RoundHistory {
//    private List<? extends Player> players;
//    private List<T extends Player> players;
    private List<TECardPlayer> players;
//  What is the difference between above two statements ??
    RoundHistory(){
        players = new ArrayList<TECardPlayer>();
    }
    public void add(TECardPlayer P){
        players.add(P);
    }
}

