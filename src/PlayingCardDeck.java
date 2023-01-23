import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayingCardDeck {
    // A generic Playing card deck, can be used to play any type of playing card games
    // Ex (poker, black-jack, rummy, etc.)

    private final int numSuits = 4; // Heart, Diamond, Club, Spade
    private final int numCardsPerSuit = 13; // Number of cards in a suit
    private final char[] suits = {'H', 'D', 'S', 'C'};

    private List<PlayingCard> cardDeck;
    private int numDeck;

    PlayingCardDeck(int numDeck) {
        cardDeck = new ArrayList<PlayingCard>();
        setNumDeck(numDeck);
//        cardDeck = cards;
        createCardDeck(numDeck);
        shufflePlayingCards();
    }

    public int getNumSuits() {
        return numSuits;
    }

    public int getNumCardsPerSuit() {
        return numCardsPerSuit;
    }

    public char[] getSuits() {
        return suits;
    }

    public void setCardDeck(List<PlayingCard> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public int getNumDeck() {
        return numDeck;
    }

    public void checkAndResetDeck(){
        if(deckSize() < 1 ){
            cardDeck = new ArrayList<PlayingCard>();
            setNumDeck(numDeck);
            createCardDeck(numDeck);
            shufflePlayingCards();
        }
    }

    public int deckSize(){
        return cardDeck.size();
    }

    public void setNumDeck(int numDeck) {
        this.numDeck = numDeck;
    }

    public void createCardDeck(int numDeck) {
        cardDeck.clear();
        for (int i = 0; i < numDeck; i++) {
            for (int j = 0; j < numSuits; j++) {
                for (int k = 0; k < numCardsPerSuit; k++) {
//                    System.out.println(String.valueOf(suits[j]));
                    cardDeck.add(new PlayingCard(k + 1, suits[j], true)); // Cards always facing down
                }
            }
        }
    }

    public List<PlayingCard> getCardDeck() {
        return cardDeck;
    }

    public void shufflePlayingCards() {
        Collections.shuffle(cardDeck);
    }

    public PlayingCard drawCard() {
        PlayingCard card = cardDeck.get(0);
        cardDeck.remove(0);

        return card;
    }

    @Override
    public String toString() {
        return "PlayingCardDeck{" +
                "numSuits=" + numSuits +
                ", numCardsPerSuit=" + numCardsPerSuit +
                ", suits=" + Arrays.toString(suits) +
                ", cardDeck=" + cardDeck +
                ", numDeck=" + numDeck +
                '}';
    }
}
