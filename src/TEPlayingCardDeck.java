import java.util.ArrayList;
import java.util.List;

public class TEPlayingCardDeck extends PlayingCardDeck {
    // Specific class for TE game
    // Since TE game is using 2 decks
    // We created a new class so that we always use 2 decks in while playing Trianta Ena

    private static final int numDeck = 2;

    TEPlayingCardDeck() {
        super(numDeck);
        setNumDeck(numDeck);
        createCardDeck(numDeck);
        shufflePlayingCards();
    }

    @Override
    public void checkAndResetDeck(){
        if(deckSize() < 2 ){
            setNumDeck(numDeck);
            createCardDeck(numDeck);
            shufflePlayingCards();
        }
    }

    @Override
    public void createCardDeck(int numDeck) {
        getCardDeck().clear();
        for (int i = 0; i < numDeck; i++) {
            for (int j = 0; j < getNumSuits(); j++) {
                for (int k = 0; k < getNumCardsPerSuit(); k++) {
                    getCardDeck().add(new TEPlayingCard(k + 1, getSuits()[j], true)); // Cards always facing down
                }
            }
        }
    }

    @Override
    public TEPlayingCard drawCard() {
        TEPlayingCard card = (TEPlayingCard) getCardDeck().get(0);
        getCardDeck().remove(0);

        return card;
    }

    public void createTEPlayingCardDeck() {
        createCardDeck(numDeck);
    }

    public List<TEPlayingCard> getTEPlayingCardDeck() {
        List<TEPlayingCard> TECards = new ArrayList<TEPlayingCard>();
        for(PlayingCard card: getCardDeck()){
            TECards.add((TEPlayingCard) card);
        }
        return TECards;
    }

    public void shuffleTEPlayingCards() {
        shufflePlayingCards();
    }

    public TEPlayingCard drawTEPlayingCard() {
        return (TEPlayingCard)drawCard();
    }

}
