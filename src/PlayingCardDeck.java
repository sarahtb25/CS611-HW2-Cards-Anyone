import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingCardDeck {
    private final int numSuits = 4; // Heart, Diamond, Club, Spade
    private final int numCardsPerSuit = 13; // Number of cards in a suit
    private final char[] suits = {'H', 'D', 'S', 'C'};

    private List<PlayingCard> cardDeck; // has 52 playing cards per deck

    PlayingCardDeck() {
        cardDeck = new ArrayList<PlayingCard>();
    }

    public void setNumberOfDecks(int numDeck) {
        cardDeck.clear();
        for (int i = 0; i < numDeck; i++) {
            for (int j = 0; j < numSuits; j++) {
                for (int k = 0; k < numCardsPerSuit; k++) {
                    cardDeck.add(new PlayingCard(k + 1, suits[j], true)); // Cards always facing down
                }
            }
        }
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
