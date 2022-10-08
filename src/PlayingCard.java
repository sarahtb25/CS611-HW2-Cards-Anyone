public class PlayingCard extends Piece {
    private int value;

    PlayingCard(int id, char suit) {
        super(id, getPlayingCardChar(id), suit);
        setValue(id);
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

    public int getValue() {
        return value;
    }
}
