import java.util.ArrayList;
import java.util.List;

public class Statistics <T extends CardPlayer>{
    // Generic class to analyze the statistics of the game. Very powerful tool
    // We can implement so many analysis methods in this class
    // We can analyze player's playing patterns, ability to bluff,  get an overall
        // summary of the game and more

    private static List<RoundHistory> rounds;

    Statistics(){
        rounds = new ArrayList<RoundHistory>();
    }

    public void add(RoundHistory playerHistory){
        rounds.add(playerHistory);
    }

    public void printStatistics() {
        for (int i = 0; i < rounds.size(); i++) {
            int roundNum = i + 1;
            System.out.println("Round " + roundNum + ": ");
            rounds.get(i).declareWinners();
        }
    }

    public void printOverallStatistics() {
        List<CardPlayer> round1Players = rounds.get(0).getPlayers();
        List<CardPlayer> lastRoundPlayers = rounds.get(rounds.size() - 1).getPlayers();
        String status;

        System.out.println("Overall,");

        for (CardPlayer firstRoundPlayer : round1Players) {
            for (CardPlayer finalRoundPlayer : lastRoundPlayers) {
                if (firstRoundPlayer.getPlayerId() == finalRoundPlayer.getPlayerId()) {
                    int initBalance = firstRoundPlayer.getInitBalance();
                    int finalBalance = finalRoundPlayer.getFinalBalance();
                    int diff = finalBalance - initBalance;

                    if (diff > 0) {
                        status = "won " + diff;
                    } else if (diff < 0) {
                        status = "lost " + (-1 * diff);
                    } else {
                        status = " neither won nor lost any money";
                    }

                    System.out.println("\t" + firstRoundPlayer.getName() + " has " + status);
                }
            }


        }
    }
    
    public void analysePlayers() {
        // We can analyse players playing strategies using the round history
    }
}
