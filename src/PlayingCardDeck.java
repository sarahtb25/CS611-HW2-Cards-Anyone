import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingCardDeck {
    private final int numSuits = 4; // Heart, Diamond, Club, Spade
    private final int numCardsPerSuit = 13; // Number of cards in a suit
    private final char[] suits = {'H', 'D', 'S', 'C'};

    private List<PlayingCard> cardDeck;
    private int numDeck;

    PlayingCardDeck(int numDeck) {
        setNumDeck(numDeck);
        initPlayingCardDeck();
        createCardDecks(numDeck);
        shufflePlayingCards();
    }

    public void initPlayingCardDeck() {
        cardDeck = new ArrayList<PlayingCard>();
    }

    public void setNumDeck(int numDeck) {
        this.numDeck = numDeck;
    }

    public void createCardDecks(int numDeck) {
        cardDeck.clear();
        for (int i = 0; i < numDeck; i++) {
            for (int j = 0; j < numSuits; j++) {
                for (int k = 0; k < numCardsPerSuit; k++) {
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
}
