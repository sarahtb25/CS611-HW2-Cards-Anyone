public class Player_HW1 {
    // Player class taken from assignment-1 and extended to create a TECardPlayer
    // TECardPlayer is a player
    // Renamed Player class to Piece_HW1 for readability

    private int playerId;
    private String name;
    private int score;
    private int roundsPlayed;

    public Player_HW1(String name) {
        this.name = name;
        this.playerId = 1;
        this.score = 0;
        this.roundsPlayed = 0;
    }

    public Player_HW1(int id, String name) {
        this.playerId = id;
        this.name = name;
        this.score = 0;
        this.roundsPlayed = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    public void setRoundsPlayed(int roundsPlayed) {
        this.roundsPlayed = roundsPlayed;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", roundsPlayed=" + roundsPlayed +
                '}';
    }

    public void play(){
        roundsPlayed++;
    }
    public void resetPlayer(){
        roundsPlayed = 0;
    }
}
