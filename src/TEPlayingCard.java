public class TEPlayingCard extends PlayingCard {
    // TEPlayingCard is a specific type of playing card created for Trianta Ena game
    // Each card game can assign different value to a card depending on the game
    // Therefore this class contains an additional method to set value of a playing card
        // according to TE game

    TEPlayingCard(int id, char suit, boolean isFaceDown) {
        super(id, suit, isFaceDown);
        setCardValue(id);
    }

    @Override
    public void setCardValue(int id) {
        if (id >= 2 && id <= 10) {
            setValue(id);
        } else if (id == 1) { // A
            setValue(11);
        } else { // J, Q, K
            setValue(10);
        }
    }
}
