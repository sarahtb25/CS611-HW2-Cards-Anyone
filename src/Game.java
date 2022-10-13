import java.util.List;

// Here we play the game
public interface Game <T extends Player> { // generic interface

    public void initializePlayers();

    public void playGame();

    public List<T> calculateWinner();


//    public void playRound();

}
