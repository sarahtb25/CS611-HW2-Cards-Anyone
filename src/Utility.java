import java.util.Scanner;

public class Utility {
    public static char checkYesNo(){
        Scanner scn = new Scanner(System.in);
        char ch = scn.next().charAt(0);
        while (ch != 'Y' && ch != 'y' && ch != 'N' && ch != 'n') {
            System.out.println("Invalid response. Please enter Y/y or N/n.");
            ch = scn.next().charAt(0);
        }
        if(ch == 'Y' || ch == 'y')
            return 'Y';
        else
            return 'N';
    }
}
