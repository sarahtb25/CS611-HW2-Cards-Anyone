import java.util.List;

public class CardPlayer extends Player_HW1 {
    // CardPlayer is a player i.e. it extends player
    // It can be any type of card player
    private int bet;
    private int initBalance;    // initial balance at the start of the round
    private int finalBalance;   // final balance at the end of the round
    //    private int valOfCards;
    private CardHand hand;

    CardPlayer(int id, String name) {
        super(id, name);
        hand = new CardHand();
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
        return hand.getValOfCards();
    }

    public void setValOfCards(int valOfCards) {
        hand.setValOfCards(valOfCards);
    }

    public List<PlayingCard> getHand() {
        return hand.getHand();
    }

    public void setHand(CardHand hand) {
        this.hand = hand;
    }

    public void clearHand(){
        hand.clearHand();
    }

    public void addPlayingCardToHand(PlayingCard card, boolean isFaceDown) {
        hand.addPlayingCardToHand(card, isFaceDown);
    }

    @Override
    public String toString() {
        return "CardPlayer{" +
                "bet=" + bet +
                ", initBalance=" + initBalance +
                ", finalBalance=" + finalBalance +
                ", hand=" + hand +
                '}';
    }
}
