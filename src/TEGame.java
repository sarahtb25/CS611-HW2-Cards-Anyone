import java.util.*;

public class TEGame implements Game {
    private List<TECardPlayer> players;
    private PlayingCardDeck cards;
    private int numOfRounds;    // Number of games played
    private int cardRounds;      // There can be multiple card rounds in one round of game

    TEGame(){
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
            updateBalance();
            dealAndPlaceBet();
            hitOrStand();
            bankerLastTurn();

            List<TECardPlayer> winners = calculateWinner();
            settleBets(winners);
            resetBets();
            cardRounds = 0;
            setNumOfRounds(getNumOfRounds() + 1);
            sortPlayersByBalance();

            System.out.println("Would you like to play another round (Y/N)? ");
            char ch = Utility.checkYesNo();
            if(ch == 'N'){
                continuePlay = false;
            }
        }
    }

    public List<TECardPlayer> calculateWinner(){
        List<TECardPlayer> winners = new ArrayList<TECardPlayer>();
        int bankerCardVal = 0;
        TECardPlayer bankerPlayer = new TECardPlayer(-1, "None");

        // Case 1: Banker gets natural TE => Banker wins
        for(TECardPlayer player: players){
            int playerCardVal = player.getValOfCards();
            if(player.isBanker()){
                bankerCardVal = playerCardVal;
                bankerPlayer = player;
                if(checkForNaturalTE(player)){
                    winners.add(player);
                    return winners;
                }
                break;
            }
        }

        // Case 2: Players vs banker
        boolean bankerWinOnly = true;
        for(TECardPlayer player: players){
            int playerCardVal = player.getValOfCards();
            if(!player.isBanker()){
                if(checkForNaturalTE(player)){
                    winners.add(player);
                    bankerWinOnly = false;
                } else if (playerCardVal > bankerCardVal && !player.isBust()) {
                    bankerWinOnly = false;
                    winners.add(player);
                }
            }
        }
        if(bankerWinOnly){
            winners.add(bankerPlayer);
        }

        return winners;
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
        if(cardRounds == 0){
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
        else if(cardRounds == 1){
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
        setCardRounds(getCardRounds() + 1);
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
        setCardRounds(getCardRounds() + 1);
    }

    public void hitOrStand(){
//        Scanner scn = new Scanner(System.in);
        for(TECardPlayer player: players){
            if(!player.isBanker() && player.isPlayerActive()){
                System.out.println(player.getName() + " Hit (Y/N)? ");

                char ch = Utility.checkYesNo();
                if (ch == 'Y') {
                    checkDeck();
                    player.addPlayingCardToHand(cards.drawCard(), false);
                }
                else {
                    player.setStand(true);
                }
            }
        }
    }

    public void bankerLastTurn(){

        for(TECardPlayer player: players){
            if(player.isBanker() && player.isPlayerActive()){
                player.faceAllCardsUp();
                boolean takeHitFlag = true;

                while(takeHitFlag) {
                    checkDeck();
                    player.addPlayingCardToHand(cards.drawCard(), false);

                    if(player.getValOfCards() >= 27){
                        takeHitFlag = false;
                    }
                    else{
                        System.out.println("Taking a Hit again ");
                    }
                }
                break;
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

    public boolean checkForNaturalTE(TECardPlayer player) {
        boolean isNaturalTE = false;
        int numAceKQJ = 0;

        List<PlayingCard> playerHand = player.getHand();
        int playerHandVal = player.getValOfCards();

        if (playerHandVal == 31 && playerHand.size() == 3) {
            for(PlayingCard card : playerHand) {
                if (card.getId() == 1 || card.getId() == 11 || card.getId() == 12 || card.getId() == 13) {
                    numAceKQJ++;
                }
            }
        }

        if (numAceKQJ == 3) {
            isNaturalTE = true;
        }

        return isNaturalTE;
    }

    public void settleBets(List<TECardPlayer> winners) {
        // Only Banker wins => everyone else pays the banker
        TECardPlayer banker = new TECardPlayer(-1, "None");

        for (TECardPlayer player : players) {
            if (player.isBanker()) {
                banker = player;
                break;
            }
        }

        if (winners.size() == 1 && winners.get(0).isBanker()) {
            for (TECardPlayer player : players) {
                if (!player.isBanker() && !player.isFold()) {
                    player.setFinalBalance(player.getFinalBalance() - player.getBet());
                    winners.get(0).setFinalBalance(winners.get(0).getFinalBalance() + player.getBet());
                }
            }
        } else { // winners other than bankers
            for (TECardPlayer winner : winners) {
                if (!winner.isBanker()) {
                    winner.setFinalBalance(winner.getFinalBalance() + winner.getBet());
                    banker.setFinalBalance(banker.getFinalBalance() - winner.getBet());
                }
            }
        }

        // losers pay the banker
        for (TECardPlayer player : players) {
            if (!player.isBanker() && !player.isFold() && player.isBust()) {
                player.setFinalBalance(player.getFinalBalance() - player.getBet());
                banker.setFinalBalance(banker.getFinalBalance() + player.getBet());
            }
        }
    }

    public void resetBets() {
        for (TECardPlayer player: players) {
            player.setBet(0);
        }
    }

    public void updateHistory() {

    }

    public void updateBalance() {
        for (TECardPlayer player: players) {
            player.setInitBalance(player.getFinalBalance());
        }
    }

    public void sortPlayersByBalance() {
        Collections.sort(players, new Comparator<TECardPlayer>() {
            @Override
            public int compare(TECardPlayer player1, TECardPlayer player2) {
                if (player1.getFinalBalance() > player2.getFinalBalance()) {
                    return -1;
                } else if (player1.getFinalBalance() < player2.getFinalBalance()) {
                    return 1;
                }

                return 0;
            }
        });
    }
}
