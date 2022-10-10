import java.util.List;

// Here we play the game
public interface Game {

    public void initializePlayers();

    public void playGame();

    public List<TECardPlayer> calculateWinner();


//    public void playRound();

}
