public class Piece {
    int id;     // also equals piece value - used to calculate player winning
    char type;  // for tic tac toe game type doesn't matter, but for a game like chess we can have 2 types of pieces white and black
    char displayChar;

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
