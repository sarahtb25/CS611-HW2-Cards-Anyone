import java.util.List;

public interface GameRules<P extends Player_HW1> {
    // Generic GameRules interface
    // Any game must implement the GameRule interface and returns a list of winners
    // Rules are behaviour of a game and can change from game to game

    public List<P> calculateWinners();
    // In calculateWinner method we implement the game rules and logic for the winning condition
}
