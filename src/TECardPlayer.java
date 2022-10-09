import java.util.List;

public class TECardPlayer extends Player {
    private boolean isBanker;
    private int bet;
    private int initBalance;    // initial balance at the start of the round
    private int finalBalance;   // final balance at the end of the round
    private int valOfCards;
    private char currentStatus;
    private boolean isBust;
    private boolean isFold;
    private boolean isStand;
    private List<PlayingCard> hand;

    public TECardPlayer(int id, String name) {
        super(id, name);
    }

    public boolean isBroke() {
        if (finalBalance > 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isPlayerActive(){
        return (!isBroke() && !isBust() && !isFold());
    }

    public boolean isBanker() {
        return isBanker;
    }

    public void setBanker(boolean banker) {
        isBanker = banker;
    }

    public boolean isFold() {
        return isFold;
    }

    public void setFold(boolean fold) {
        isFold = fold;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getInitBalance() {
        return initBalance;
    }

    public void setInitBalance(int initBalance) {
        this.initBalance = initBalance;
    }

    public int getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(int finalBalance) {
        this.finalBalance = finalBalance;
    }

    public int getValOfCards() {
        return valOfCards;
    }

    public void setValOfCards(int valOfCards) {
        this.valOfCards = valOfCards;
    }

    public char getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(char currentStatus) {
        this.currentStatus = currentStatus;
    }

    public boolean isBust() {
        return isBust;
    }

    public void setBust(boolean bust) {
        isBust = bust;
    }

    public boolean isStand() {
        return isStand;
    }

    public void setStand(boolean stand) {
        isStand = stand;
    }

    public void clearPlayingCards() {
        hand.clear();
    }

    public void addPlayingCardToHand(PlayingCard card, boolean isFaceDown) {
        card.setIsFaceDown(isFaceDown);
        hand.add(card);

        if (valOfCards > 31 && !isBust()) {
            setBust(true);
        }
    }
    public List<PlayingCard> getHand() {
        return hand;
    }
}