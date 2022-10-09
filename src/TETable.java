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
            dealAndPlaceBet();
            hitOrStand();
            bankerLastTurn();

//            calculateWinner();

            setNumOfRounds(getNumOfRounds() + 1);
            System.out.println("Would you like to play another round (Y/N)? ");
            char ch = Utility.checkYesNo();
            if(ch == 'N'){
                continuePlay = false;
            }
        }
    }

    public void checkDeck(){
        if(cards.deckSize() < 2 ){
            cards = new TEPlayingCardDeck();
//            cards.resetDeck();
            clearPlayerCards();
        }
    }

    public void dealCards(){
//        if(cards.deckSize() < 2 ){
//            cards.resetDeck();
//            clearPlayerCards();
//        }
        if(numOfRounds == 0){
            // Dealer deals 1 card facedown to each player, dealer gets 1 faceup card
            for(TECardPlayer player: players){
                if(player.isPlayerActive()) {
                    if (player.isBanker()) {
                        checkDeck();
                        player.addPlayingCardToHand(cards.drawCard(), false);
                    } else {
                        checkDeck();
                        player.addPlayingCardToHand(cards.drawCard(), true);
                    }
                }
            }
        }
        else if(numOfRounds == 1){
            // Dealer deals 2 cards faceup to each player
            for(TECardPlayer player: players){
                if(player.isPlayerActive()) {
                    checkDeck();
                    player.addPlayingCardToHand(cards.drawCard(), false);
                    player.addPlayingCardToHand(cards.drawCard(), false);
                }
            }
        }
    }

    public void dealAndPlaceBet(){
        dealCards();    // Deal 1st round of cards
        System.out.println("Its time to place your bets");
        Scanner scn = new Scanner(System.in);

        for(TECardPlayer player: players) {
            if (player.isPlayerActive()) {
                // showTable(); display cards of all the players and his own card. To help the player place a bet

                System.out.println("Would you like to place a bet (Y/N)? ");
                char ch = Utility.checkYesNo();

                if (ch == 'Y' || ch == 'y') {
                    System.out.println("Your current balance: " + player.getInitBalance());
                    System.out.println(player.getName() + " place your bets please: ");

                    int bet = scn.nextInt();
                    while (bet > player.getInitBalance()) {
                        System.out.println("Can't place more than you own !! Try again");
                        System.out.println(player.getName() + " place your bets please: ");
                        bet = scn.nextInt();
                    }
                    player.setBet(bet);
                } else {
                    player.setFold(true);
                }
            }
        }
        dealCards();
    }

    public void hitOrStand(){
        Scanner scn = new Scanner(System.in);
        for(TECardPlayer player: players){
            if(!player.isBanker()){
                takeHit(player);
            }
        }
    }

    public void takeHit(TECardPlayer player){
        boolean takeHitFlag = true;
        System.out.println(player.getName() + " Hit (Y/N)? ");
        while(takeHitFlag) {
            char ch = Utility.checkYesNo();
            if (ch == 'Y') {
                checkDeck();
                player.addPlayingCardToHand(cards.drawCard(), false);

                if(player.isBust()){
                    takeHitFlag = false;
                }
                else{
                    System.out.println(player.getName() + " Hit again (Y/N)? ");
                }
            }
            else {
                takeHitFlag = false;
                player.setStand(true);
            }
        }
    }

    public void bankerLastTurn(){
        for(TECardPlayer player: players){
            if(player.isBanker()){
                takeHit(player);
            }
        }
    }

    public void displayTurn(){

    }

    public void clearPlayerCards() {
        for (TECardPlayer player : players) {
            player.clearPlayingCards();
        }
    }
}
