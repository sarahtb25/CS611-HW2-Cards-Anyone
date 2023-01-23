public interface Game <T extends Player_HW1> {
    // Generic Game interface. Any game must implement the game interface
    // We are considering Game as a behaviour
    // The con of this is we cannot have any state in Game class now

    public void initializePlayers();

    public void playGame();

}
