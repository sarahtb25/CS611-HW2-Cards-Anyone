public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Trianta Ena!");

        TEGame teGame = new TEGame();
        teGame.initializePlayers();
        teGame.playGame();
    }

}