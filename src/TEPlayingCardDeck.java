import java.util.List;

public class TEPlayingCardDeck extends PlayingCardDeck {
    private static final int numDeck = 2;

    TEPlayingCardDeck() {
        super(numDeck);
    }

    public void createTEPlayingCardDeck() {
        createCardDecks(numDeck);
    }

    public List<PlayingCard> getTEPlayingCardDeck() {
        return getCardDeck();
    }

    public void shuffleTEPlayingCards() {
        shufflePlayingCards();
    }

    public PlayingCard drawTEPlayingCard() {
        return drawCard();
    }
}
