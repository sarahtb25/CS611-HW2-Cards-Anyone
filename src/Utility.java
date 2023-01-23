import java.util.Scanner;

public class Utility {
    // Utility class created for some utility methods used in this assignment
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
    public static void nextLine(){
        System.out.println();
    }

    public static boolean checkIsNumber(String val) {
        int num;

        try {
            num = Integer.parseInt(val);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
