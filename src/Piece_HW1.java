public class Piece_HW1 {
    // Piece class taken from assignment-1 and extended to create a playing card
    // Playing card can be considered as a Piece in a Playing card game
    // Renamed Piece class to Piece_HW1 for readability

    private int id;     // From 1 = Ace till 13 = King
    private char type;  // 4 suites =>
    // H for Heart
    // D for Diamond
    // C for Club
    // S for Spades
    private char displayChar;
    // A = Ace
    // J, Q, K for face cards
    // rest integers

    Piece_HW1(){
        this.id = -1;   // -1 for absence of a piece, an empty piece
        this.type = 'N';
        this.displayChar = ' ';
    }

    Piece_HW1(int pieceId, char disChar){
        this.id = pieceId;
        this.type = 'N';
        this.displayChar = disChar;
    }

    Piece_HW1(char disChar, char type){
        this.id = -1;
        this.type = type;
        this.displayChar = disChar;
    }

    Piece_HW1(int pieceId, char disChar, char type) {
        this.id = pieceId;
        this.displayChar = disChar;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public char getDisplayChar() {
        return displayChar;
    }

    public void setDisplayChar(char displayChar) {
        this.displayChar = displayChar;
    }

    public void printPiece() {
        System.out.print(displayChar);
    }

    public int getValue() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Piece_HW1{" +
                "id=" + id +
                ", type=" + type +
                ", displayChar=" + displayChar +
                '}';
    }
}
