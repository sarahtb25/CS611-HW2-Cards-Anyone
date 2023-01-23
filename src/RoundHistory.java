import java.util.ArrayList;
import java.util.List;
// To store history of each round
public class RoundHistory <T extends CardPlayer> {
    // Generic class to record history of each round for analysis purpose
    // Round history stores any type of CardPlayer since CardPlayers have bet/money related data
    // Con of this class is that we are storing players instead of a new abstraction
        // related to money calculation

    private List<T> players;
    RoundHistory(){
        this.players = new ArrayList<>();
    }

    public List<T> getPlayers() {
        return players;
    }

    public void setPlayers(List<T> players) {
        this.players = players;
    }

    public void add(T P){
        players.add(P);
    }

    public void declareWinners() {
        Utility.nextLine();
        System.out.println("Round Summary: ");
        String status = "";

        for (T player : players) {
            int diff = player.getFinalBalance() - player.getInitBalance();
            if (diff > 0) {
                status = "won " + diff;
            } else if (diff < 0) {
                status = "lost " + (-1 * diff);
            } else {
                status = "folded";
            }
            System.out.println(player.getName() + " has " + status);
        }
        Utility.nextLine();
    }

    @Override
    public String toString() {
        return "RoundHistory{" +
                "players=" + players +
                '}';
    }
}
