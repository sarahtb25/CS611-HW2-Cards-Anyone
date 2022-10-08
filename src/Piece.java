public class Piece {
    int id;     // From 1 = Ace till 13 = King
    char type;  // 4 suites =>
    // H for Heart
    // D for Diamond
    // C for Club
    // S for Spades
    char displayChar;
    // A = Ace
    // J, Q, K for face cards
    // rest integers

    Piece(){
        this.id = -1;   // -1 for absence of a piece, an empty piece
        this.type = 'N';
        this.displayChar = ' ';
    }
    Piece(int pieceId, char disChar){
        this.id = pieceId;
        this.type = 'N';
        this.displayChar = disChar;
    }
    Piece(char disChar, char type){
        this.id = -1;
        this.type = type;
        this.displayChar = disChar;
    }

    public void printPiece() {
        System.out.print(displayChar);
    }

    public int getValue() {
        return this.id;
    }
}
