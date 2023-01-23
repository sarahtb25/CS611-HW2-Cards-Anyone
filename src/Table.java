import java.util.ArrayList;
import java.util.List;

public class Table<C extends PlayingCardDeck> {
    // Generic Table class for any playing card game
    // Contains a deck of cards placed on the table
    // We draw from this deck of cards

    private C cards;
    private int cardRounds;      // There can be multiple card rounds in one round of game

    Table(C cards){
        this.cards = cards;
        this.cardRounds = 0;
    }

    public C getCards() {
        return cards;
    }

    public void setCards(C cards) {
        this.cards = cards;
    }

    public int getCardRounds() {
        return cardRounds;
    }

    public void setCardRounds(int cardRounds) {
        this.cardRounds = cardRounds;
    }

    @Override
    public String toString() {
        return "Table{" +
                "cards=" + cards +
                ", cardRounds=" + cardRounds +
                '}';
    }
}
