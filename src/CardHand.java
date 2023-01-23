import java.util.ArrayList;
import java.util.List;

public class CardHand {
    // CardHand can hold any type of card hand i.e. set of playing cards
    // CardHand class is generalized for any type of playing card game (ex: BlackJack, poker, etc.)

    private List<PlayingCard> hand;
    private int valOfCards;

    CardHand() {
        hand = new ArrayList<PlayingCard>();
    }

    public List<PlayingCard> getHand() {
        return hand;
    }

    public void setHand(List<PlayingCard> hand) {
        this.hand = hand;
    }

    public int getValOfCards() {
        return valOfCards;
    }

    public void setValOfCards(int valOfCards) {
        this.valOfCards = valOfCards;
    }

    public void clearHand(){
        hand = new ArrayList<PlayingCard>();
        valOfCards = 0;
    }

    public void addPlayingCardToHand(PlayingCard card, boolean isFaceDown) {
        card.setIsFaceDown(isFaceDown);
        int totalHandValue = getValOfCards();
        setValOfCards(totalHandValue + card.getValue());
        hand.add(card);
    }

    @Override
    public String toString() {
        return "CardHand{" +
                "hand=" + hand +
                ", valOfCards=" + valOfCards +
                '}';
    }
}
