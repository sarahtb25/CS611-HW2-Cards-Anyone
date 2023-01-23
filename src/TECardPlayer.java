import java.util.ArrayList;
import java.util.List;

public class TECardPlayer extends CardPlayer {
    // TECardPlayer is a specific player created for Trianta Ena game
    // This class contains some additional attributes and methods required for TE player

    private boolean isBust;
    private boolean isFold;
    private boolean isStand;

    public TECardPlayer(int id, String name) {
        super(id, name);
        isBust = false;
        isFold = false;
        isStand = false;
    }
    public TECardPlayer(TECardPlayer player, String name) {
        super(player.getPlayerId(), name);
        setInitBalance(player.getInitBalance());
        setFinalBalance(player.getFinalBalance());
        isBust = false;
        isFold = false;
        isStand = false;
    }
    public TECardPlayer() {
        super(1, "name");
    }

    public boolean isBroke() {
        if (getFinalBalance() > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String getName(){
        String str = super.getName();
        return str;
    }

    @Override
    public void resetPlayer(){
        super.resetPlayer();
        isBust = false;
        isFold = false;
        isStand = false;
    }

    public boolean isPlayerActive(){
        return (!isBroke() && !isBust() && !isFold() && getInitBalance() > 0);
    }

    public List<TEPlayingCard> getTEPlayingCardHand() {
        List<PlayingCard> hand = getHand();
        List<TEPlayingCard> teHand = new ArrayList<TEPlayingCard>();
        for (PlayingCard card : hand) {
            TEPlayingCard tePlayingCard = (TEPlayingCard) card;
            teHand.add(tePlayingCard);
        }

        return teHand;
    }

    public boolean isFold() {
        return isFold;
    }

    public void setFold(boolean fold) {
        isFold = fold;
    }

    public boolean isBust() {
        return isBust;
    }

    public void setBust(boolean bust) {
        isBust = bust;
    }

    public boolean isStand() {
        return isStand;
    }

    public void setStand(boolean stand) {
        isStand = stand;
    }

    public void clearPlayingCards() {
        clearHand();
    }
    public void faceAllCardsUp(){
        for(TEPlayingCard card: getTEPlayingCardHand()){
            card.setIsFaceDown(false);
        }
    }
    public void addPlayingCardToHand(TEPlayingCard card, boolean isFaceDown) {
        checkAceLogic(card);
        super.addPlayingCardToHand(card, isFaceDown);

        if (getValOfCards() > 31 && !isBust()) {
            setBust(true);
        }
    }

    public void checkAceLogic(TEPlayingCard card){
        boolean cardWithVal1 = false;
        if(card.getId() == 1){
            for(TEPlayingCard inHandCard: getTEPlayingCardHand()){
                if(inHandCard.getValue() == 1){
                    cardWithVal1 = true;
                }
            }
            if(!cardWithVal1){
                System.out.println(getName() + " do you want value of this Ace to be 1 (Y/N)?");
                char ch = Utility.checkYesNo();
                if(ch == 'Y'){
                    card.setValue(1);
                } else {
                    card.setValue(11);
                }
            }
        }
    }

    public void showCards() {
        List<TEPlayingCard> hand = getTEPlayingCardHand();
        int size = hand.size();
        for(int i = 0; i < size - 1; i++){
            if(!hand.get(i).getIsFaceDown()) {
                System.out.print(hand.get(i).printCard() + ", ");
            }
            else{
                System.out.print("*-*" + ", ");
            }
        }
        if(!hand.get(size -1).getIsFaceDown())
            System.out.print(hand.get(size -1).printCard());
        else{
            System.out.print("*-*" + ", ");
        }
    }

    public void showMyCards() {
        List<TEPlayingCard> hand = getTEPlayingCardHand();
        int size = hand.size();
        for(int i = 0; i < size - 1; i++){
            System.out.print(hand.get(i).printCard() + ", ");
        }
        System.out.print(hand.get(size -1).printCard());
        Utility.nextLine();
        Utility.nextLine();
    }

    @Override
    public String toString() {
        return "TECardPlayer{" +
                "isBust=" + isBust +
                ", isFold=" + isFold +
                ", isStand=" + isStand +
                '}';
    }
}