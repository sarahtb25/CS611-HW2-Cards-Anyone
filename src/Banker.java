public class Banker extends TECardPlayer{
    // Banker is a special player in TE game
    // Is a relationship between banker and TECardPlayer

    Banker(){
        super();
    }

    Banker(TECardPlayer dealer){
        super(dealer, dealer.getName());
    }

    @Override
    public String getName(){
      String nameStr = super.getName();
      return ("Banker " + nameStr);
    }

    public String getRealName(){
        return super.getName();
    }
}
