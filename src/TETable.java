import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TETable implements Table{
    private List<TECardPlayer> players;
    private PlayingCardDeck cards;
    private int numOfRounds;    // Number of games played
    private int cardRounds;      // There can be multiple card rounds in one round of game

    TETable(){
        cards = new TEPlayingCardDeck();
        numOfRounds = 0;
        cardRounds = 0;
    }

    public List<TECardPlayer> getPlayers() {
        return players;
    }

    public PlayingCardDeck getCards() {
        return cards;
    }

    public int getNumOfRounds() {
        return numOfRounds;
    }

    public int getCardRounds() {
        return cardRounds;
    }

    public void setPlayers(List<TECardPlayer> players) {
        this.players = players;
    }

    public void setCards(PlayingCardDeck cards) {
        this.cards = cards;
    }

    public void setNumOfRounds(int numOfRounds) {
        this.numOfRounds = numOfRounds;
    }

    public void setCardRounds(int cardRounds) {
        this.cardRounds = cardRounds;
    }

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

    public String setBankerRandomly() {
        int numPlayers = players.size();

        Random rand = new Random();
        int playerId = rand.nextInt(numPlayers);

        while(players.get(playerId).isBroke()) {
            playerId = rand.nextInt(numPlayers);
        }

        players.get(playerId).setBanker(true);

        return players.get(playerId).getName();
    }

    public void playGame(){
        boolean continuePlay = true;
        while(continuePlay){


            setNumOfRounds(getNumOfRounds() + 1);
            System.out.println("Would you like to play another round (Y/N)? ");
            Scanner scn = new Scanner(System.in);
            char ch = scn.next().charAt(0);
            while(ch != 'Y')
                if(ch == 'N' || ch == 'n'){
                    continuePlay = false;
                }
                else {

                }
//            Ask whethere you want to play another round
        }
    }

    public void playRound(){

    }
}
