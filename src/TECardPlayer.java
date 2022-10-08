public class TECardPlayer extends Player {
    private boolean isBanker;
    private int bet;
    private int initBalance;    // initial balance at the start of the round
    private int finalBalance;   // final balance at the end of the round
    private int valOfCards;
    private char currentStatus;
    private boolean isBust;
//    private List<PlayigCard> hand;

    public TECardPlayer(int id, String name) {
        super(id, name);
    }


}
