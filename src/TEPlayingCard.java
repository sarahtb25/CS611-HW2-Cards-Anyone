public class TEPlayingCard extends PlayingCard {

    TEPlayingCard(int id, char suit, boolean isFaceDown) {
        super(id, suit, isFaceDown);
        setValue(id);
    }

    //    @Override
    public void setPlayingCardValue(int id) {
        int value;
        if (id >= 2 && id <= 10) {
            value = id;
        } else if (id == 1) { // A
            value = 11;
        } else { // J, Q, K
            value = 10;
        }

        setValue(value);
    }
}
