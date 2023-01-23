public class PlayingCard extends Piece_HW1 {
    // Extended PlayingCard class from a Piece class and added additional functionalities
    // This class is a generic class and can be used to play any type of Playing card game
    // Ex (poker, black-jack, rummy, etc.)

    private int value;
    private boolean isFaceDown = false;
    private char suit;

    PlayingCard(int id, char suit, boolean isFaceDown) {
        super(id, getPlayingCardChar(id), suit);
        setCardValue(id);
        setIsFaceDown(isFaceDown);
    }

    private static char getPlayingCardChar(int id) {
        char displayChar = 'N';

        if (id >= 2 && id <= 10) {
            displayChar = (char) (id + '0');
            if(id == 10){
                displayChar = (char) (0 + '0');
            }
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

    public void setCardValue(int id) {
        this.value = id;
    }

    public void setValue(int value) { // For Ace, can be 1 or 11
        this.value = value;
    }

    public int getValue() {
        return value;
    }

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

    public String printCard() {
        String str = String.valueOf(getType()) + "-" + String.valueOf(getPlayingCardChar(getId()));
        if(getId() == 10){
            str = String.valueOf(getType()) + "-10";
        }
        return str;
    }

    public void setFaceDown(boolean faceDown) {
        isFaceDown = faceDown;
    }

    @Override
    public String toString() {
        return "PlayingCard{" +
                "value=" + value +
                ", isFaceDown=" + isFaceDown +
                ", suit=" + suit +
                '}';
    }
}
