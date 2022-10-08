public class Player {
    int playerId;
    String name;
    int score;
    int roundsPlayed;

    public Player(String name) {
        this.name = name;
        this.playerId = 1;
        this.score = 0;
        this.roundsPlayed = 0;
    }
    public Player(int id, String name) {
        this.playerId = id;
        this.name = name;
        this.score = 0;
        this.roundsPlayed = 0;
    }

    public String getName() {
        return this.name;
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
}
