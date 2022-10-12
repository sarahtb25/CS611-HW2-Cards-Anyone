public class PlayingCard extends Piece {
    private int value;
    private boolean isFaceDown = false;
    private char suit;

    PlayingCard(int id, char suit, boolean isFaceDown) {
        super(id, getPlayingCardChar(id), suit);
        setValue(id);
        setIsFaceDown(isFaceDown);
    }

    private static char getPlayingCardChar(int id) {
        char displayChar = 'N';

        if (id >= 2 && id <= 10) {
            displayChar = (char) id;
        } else if (id == 1) {
            displayChar = 'A';
        } else if (id == 11) {
            displayChar = 'J';
        } else if (id == 12) {
            displayChar = 'Q';
        } else if (id == 13) {
            displayChar = 'K';
        }

        return displayChar;
    }

    public void setValue(int id) {
        if (id >= 2 && id <= 10) {
            value = id;
        } else if (id == 1) { // A
            value = 11;
        } else { // J, Q, K
            value = 10;
        }
    }

    public void setValueGivenVal(int value) { // For Ace, can be 1 or 11
        this.value = value;
    }

    public int getValue() {
        return value;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public char getSuit() {
        return suit;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    public void setIsFaceDown(boolean isFaceDown) {
        this.isFaceDown = isFaceDown;
    }

    public boolean getIsFaceDown() {
        return isFaceDown;
    }

    @Override
    public String toString() {
        return getSuit() + " " + getPlayingCardChar(getId());
    }

    public String printCard() {
        String id = String.valueOf(getId());
        String str = String.valueOf(getType()) + "-" + id;
        return str;
    }
}
