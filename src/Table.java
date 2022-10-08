import java.util.List;
import java.util.Scanner;

// Here we play the game
public class Table {
    private List<TECardPlayer> players;
    private PlayingCardDeck cards;
    private int numOfRounds;    // number of games played
    private int cardRound;

    public void initializePlayers(){
        System.out.println("Enter total number of players playing the game: ");
        Scanner scn = new Scanner(System.in);

        int totalPlayers = scn.nextInt();
        scn.nextLine();

        for(int i = 0; i < totalPlayers; i++){
            System.out.println("Enter player " + (i+1) + " name: ");
            String name = scn.nextLine();
            players.add(new TECardPlayer(i, name));

            scn.nextLine();
        }

    }
}
