import java.util.ArrayList;
import java.util.List;
// To store history of each round
public class RoundHistory <T extends Player> {// generic class
    private List players;
//    private List<T extends Player> players;
//    private List<TECardPlayer> players;
//  What is the difference between above two statements ??
    RoundHistory(){
        players = new ArrayList<T>();
    }
    public void add(T P){
        players.add(P);
    }
}

