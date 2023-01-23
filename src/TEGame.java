import java.util.*;

public class TEGame implements Game, GameRules {
    // Specific game class to play Trianta Ena game
    // TEGame class implements Game interface and GameRules interface just any other game
    // Contains methods which help us play the TE game

    private Table<TEPlayingCardDeck> TETable;
    private List<TECardPlayer> players;
    private Banker banker;
    private int numOfRounds;    // Number of games played
    private Statistics gameStats;

    TEGame(){
        TETable = new Table(new TEPlayingCardDeck());
        players = new ArrayList<TECardPlayer>();
        initializePlayers();
        gameStats = new Statistics();
        numOfRounds = 0;
    }

    public List<TECardPlayer> getPlayers() {
        return players;
    }

    public int getNumOfRounds() {
        return numOfRounds;
    }

    public void setPlayers(List<TECardPlayer> players) {
        this.players = players;
    }

    public void setNumOfRounds(int numOfRounds) {
        this.numOfRounds = numOfRounds;
    }

    public void initializePlayers(){
        System.out.print("Please enter total number of players playing the game (min. 3 and max. 10): ");
        Scanner scn = new Scanner(System.in);

        String totalPlayersValue = scn.next();

        while(!Utility.checkIsNumber(totalPlayersValue) || Integer.parseInt(totalPlayersValue) > 10 || Integer.parseInt(totalPlayersValue) < 3) {
            System.out.print("Please enter total number of players playing the game (min. 3 and max. 10): ");
            totalPlayersValue = scn.next();
        }

        int totalPlayers = Integer.parseInt(totalPlayersValue);

        for(int i = 0; i < totalPlayers; i++){
            System.out.print("Enter player " + (i+1) + " name: ");
            String name = scn.next();
            players.add(new TECardPlayer(i, name));
        }
    }

    public Banker setBankerRandomly() {
        System.out.println("Assigning a banker randomly...");
        int numPlayers = players.size();

        Random rand = new Random();
        int playerId = rand.nextInt(numPlayers);

        while(players.get(playerId).isBroke()) {
            playerId = rand.nextInt(numPlayers);
        }

//        players.get(playerId).setBanker(true);
        Banker dealer = new Banker(players.get(playerId));
//                (Banker) players.get(playerId);
        players.remove(players.get(playerId));

        return dealer;
    }

    public void printPlayers(Banker banker){
        System.out.println("Here's the player information: ");
//        System.out.println("Banker: " + banker.getName() + "\t" + "Balance: " + banker.getInitBalance());
        System.out.println(banker.getName() + "\t" + "Balance: " + banker.getInitBalance());
        Utility.nextLine();

        System.out.println("Players: ");
        for(TECardPlayer player: players){
            System.out.print(player.getName() + "\t Balance: " + player.getInitBalance());
            Utility.nextLine();
        }
    }

    public void playGame() {
        System.out.println("Welcome to Trianta Ena!");
        int balance = setInitialBalance();

        // Set player balance according to input and banker balance is 3 times
        for (TECardPlayer player : players) {
            player.setInitBalance(balance);
            player.setFinalBalance(balance);
        }

        banker = setBankerRandomly();
        Utility.nextLine();

        banker.setInitBalance(3 * balance);
        banker.setFinalBalance(3 * balance);

        boolean continuePlay = true;
        while(continuePlay){
            updateBalance();
            printPlayers(banker);
            Utility.nextLine();

            dealAndPlaceBet();
            hitOrStand();
            bankerLastTurn();

            List<TECardPlayer> winners = calculateWinners();
            settleBets(winners);

            TETable.setCardRounds(0);
//            cardRounds = 0;
            setNumOfRounds(getNumOfRounds() + 1);

            RoundHistory gameRound = new RoundHistory();
            gameRound.add(new TECardPlayer(banker, banker.getRealName()));

            for(TECardPlayer player: players){
                gameRound.add(player);
            }
            gameRound.declareWinners();
            gameStats.add(gameRound);

            System.out.println("Would you like to play another round (Y/N)? ");
            char ch = Utility.checkYesNo();
            if(ch == 'N'){
                continuePlay = false;
                gameStats.printStatistics();
                Utility.nextLine();
                gameStats.printOverallStatistics();
                System.exit(1);
            }
            else{
                players.add(new TECardPlayer(banker, banker.getRealName()));
                resetBets();
                sortPlayersByBalance();
                resetPlayers();
                banker = pickNextBanker();
            }
            clearPlayerCards();
        }
    }

    public void resetPlayers(){
        for(TECardPlayer player: players){
            player.resetPlayer();
        }
    }

    public Banker pickNextBanker(){
        Banker banker = new Banker();
//        removeExistingBanker();
        boolean bankerFound = false;
        int i = 0;

        while(!bankerFound){
            System.out.println(players.get(i).getName() + " would you like to be the banker in next round (Y/N)?");
            char ch = Utility.checkYesNo();
            if(ch == 'Y'){
                bankerFound = true;
                System.out.println("For the next round " + players.get(i).getName() +
                        " will be the banker");
//                players.get(i).setBanker(true);
                banker = new Banker(players.get(i));
                players.remove(players.get(i));
            }
            i++;
        }
        if(!bankerFound){
            banker = setBankerRandomly();
        }
        return banker;
    }

//    public void removeExistingBanker(){
//        for(TECardPlayer player: players){
//            if(player.isBanker()){
//                player.setBanker(false);
//                break;
//            }
//        }
//    }

    public List<TECardPlayer> calculateWinners(){
        List<TECardPlayer> winners = new ArrayList<TECardPlayer>();
//        int bankerCardVal = 0;
//        TECardPlayer bankerPlayer = new TECardPlayer(-1, "None");

        // Case 1: Banker gets natural TE => Banker wins
//        for(TECardPlayer player: players){
//            int playerCardVal = player.getValOfCards();
//            if(player.isBanker()){
//                bankerCardVal = playerCardVal;
//            bankerPlayer = player;
        if(checkForNaturalTE(banker)){
            winners.add(banker);
            return winners;
        }
//        }
        // Case 2: Banker has gone bust
        for(TECardPlayer player: players){
            if (banker.isBust() && !player.isBust()) {
                winners.add(player);
            }
        }

        // Case 3: Players vs banker
        boolean bankerWinOnly = true;
        for(TECardPlayer player: players){
            int playerCardVal = player.getValOfCards();
//            if(!player.isBanker()){
            if(checkForNaturalTE(player)){
                winners.add(player);
                bankerWinOnly = false;
            } else if (playerCardVal > banker.getValOfCards() && !player.isBust()) {
                bankerWinOnly = false;
                winners.add(player);
            }
//            }
        }
        if(bankerWinOnly){
            winners.add(banker);
        }

        return winners;
    }

    public void checkDeck(){
        TETable.getCards().checkAndResetDeck();
//        if(cards.deckSize() < 2 ){
//            cards = new TEPlayingCardDeck();
////            cards.resetDeck();
//            clearPlayerCards();
//        }
    }

    public void dealCards(){
        if(TETable.getCardRounds() == 0){
            // Dealer deals 1 card face down to each player, dealer gets 1 face up card
            checkDeck();
            banker.addPlayingCardToHand((TEPlayingCard) TETable.getCards().drawCard(), false);
            for(TECardPlayer player: players){
                if(player.isPlayerActive()) {
                    checkDeck();
                    player.addPlayingCardToHand((TEPlayingCard) TETable.getCards().drawCard(), true);
                }
            }
        }
        else if(TETable.getCardRounds() == 1){
            // Dealer deals 2 cards faceup to each player
            checkDeck();
            banker.addPlayingCardToHand((TEPlayingCard) TETable.getCards().drawCard(), false);
            banker.addPlayingCardToHand((TEPlayingCard) TETable.getCards().drawCard(), false);
            for(TECardPlayer player: players){
                if(player.isPlayerActive()) {
                    checkDeck();
                    player.addPlayingCardToHand((TEPlayingCard) TETable.getCards().drawCard(), false);
                    player.addPlayingCardToHand((TEPlayingCard) TETable.getCards().drawCard(), false);
                }
            }
        }
    }

    public void dealAndPlaceBet(){
        dealCards();    // Deal 1st round of cards
        TETable.setCardRounds(TETable.getCardRounds() + 1);
        System.out.println("It's time to place bets");
        Scanner scn = new Scanner(System.in);

        for(TECardPlayer player: players) {
            if (player.isPlayerActive()) {
                showPlayerAndBankerCards(player);
//                 display cards of all the players and his own card. To help the player place a bet
                System.out.print(player.getName());
                System.out.print(" would you like to place a bet (Y/N)? ");
                char ch = Utility.checkYesNo();

                if (ch == 'Y' || ch == 'y') {
                    System.out.println("Your current balance: " + player.getInitBalance());
                    System.out.println(player.getName() + " place your bets please: ");

                    int bet = scn.nextInt();
                    while (bet > player.getInitBalance()) {
                        System.out.println("Can't place more than you own! Try again");
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
        TETable.setCardRounds(TETable.getCardRounds() + 1);
    }

    public void showPlayerAndBankerCards(TECardPlayer currentPlayer){
        System.out.println(banker.getName());
        banker.showCards();
        Utility.nextLine();
        Utility.nextLine();
        for(TECardPlayer player: players){
            if(player.getPlayerId() != currentPlayer.getPlayerId()) {
                System.out.println(player.getName());
                player.showCards();
                Utility.nextLine();
                Utility.nextLine();
            }
        }
        getCardsAndValue(currentPlayer);
        Utility.nextLine();
    }

    public void getCardsAndValue(TECardPlayer currentPlayer){
        System.out.println("Your (" + currentPlayer.getName() + ") cards: ");
        currentPlayer.showMyCards();
        System.out.println("Current value of Hand: " + currentPlayer.getValOfCards());
    }

    public void hitOrStand(){
        for(TECardPlayer player: players){
            if(player.isPlayerActive()){
                System.out.println(player.getName() + " Cards: ");
                player.showMyCards();
                Utility.nextLine();
                System.out.println("Current value of Hand: " + player.getValOfCards());
                takeHit(player);
            }
        }
    }

    public void bankerLastTurn(){
        if(banker.isPlayerActive()){
            banker.faceAllCardsUp();
            boolean takeHitFlag = true;
            if(banker.getValOfCards() >= 27){
                takeHitFlag = false;
                System.out.println("Banker can't take a hit!");
                getCardsAndValue(banker);
            }
            else{
                System.out.println("Banker is taking a Hit");
            }

            while(takeHitFlag) {
                checkDeck();
                banker.addPlayingCardToHand((TEPlayingCard) TETable.getCards().drawCard(), false);
                getCardsAndValue(banker);

                if(banker.getValOfCards() >= 27){
                    takeHitFlag = false;
                }
                else{
                    System.out.println("Taking a Hit again ");
                }
            }
        }
    }


    public void clearPlayerCards() {
        banker.clearPlayingCards();
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
                player.addPlayingCardToHand((TEPlayingCard) TETable.getCards().drawCard(), false);
                getCardsAndValue(player);

                if(player.isBust()){
                    takeHitFlag = false;
                    System.out.println(player.getName() + " you have gone bust! ");
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

        List<TEPlayingCard> playerHand = player.getTEPlayingCardHand();
        int playerHandVal = player.getValOfCards();

        if (playerHandVal == 31 && playerHand.size() == 3) {
            for(TEPlayingCard card : playerHand) {
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
//        TECardPlayer banker = new TECardPlayer(-1, "None");

//        for (TECardPlayer player : players) {
//            if (player.isBanker()) {
//                banker = player;
//                break;
//            }
//        }

        for (TECardPlayer player : players) {
            if (winners.contains(player)) {
                banker.setFinalBalance(banker.getFinalBalance() - player.getBet());
                player.setFinalBalance(player.getFinalBalance() + player.getBet());
            } else {
                player.setFinalBalance(player.getFinalBalance() - player.getBet());
                banker.setFinalBalance(banker.getFinalBalance() + player.getBet());
            }
        }

        printBetAndBalance(banker);
        for (TECardPlayer player : players) {
            printBetAndBalance(player);
//            System.out.println("Player " + player.getName() + "\n\tInitial Balance: " + player.getInitBalance() + "\n\tBet: " + player.getBet() + "\n\tFinal Balance: " + player.getFinalBalance());
        }
    }

    public void printBetAndBalance(TECardPlayer player){
        System.out.println(player.getName() + "\n\tInitial Balance: " + player.getInitBalance() + "\n\tBet: " + player.getBet() + "\n\tFinal Balance: " + player.getFinalBalance());
    }

    public void resetBets() {
        banker.setBet(0);
        for (TECardPlayer player: players) {
            player.setBet(0);
        }
    }

    public void updateBalance() {
        banker.setInitBalance(banker.getFinalBalance());
        for (TECardPlayer player: players) {
            player.setInitBalance(player.getFinalBalance());
        }
    }

    public void sortPlayersByBalance() {
        Collections.sort(players, (player1, player2) -> {
            if (player1.getFinalBalance() > player2.getFinalBalance()) {
                return -1;
            } else if (player1.getFinalBalance() < player2.getFinalBalance()) {
                return 1;
            }

            return 0;
        });
    }

    public int setInitialBalance() {
        System.out.println("Please enter how much you would like to be assigned as balance: ");
        Scanner scan = new Scanner(System.in);
        String balance = scan.next();

        while(!Utility.checkIsNumber(balance) || Integer.parseInt(balance) <= 0) {
            System.out.println("Balance has to be a positive number!");
            balance = scan.next();
        }

        return Integer.parseInt(balance);
    }
}
