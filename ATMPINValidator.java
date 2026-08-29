import java.util.Scanner;

public class ATMPINValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ATM PIN: ");
        String pin = sc.nextLine();

        if ((pin.length() == 4 || pin.length() == 6) && pin.matches("\\d+")) {
            System.out.println("Valid ATM PIN");
        } else {
            System.out.println("Invalid ATM PIN");
        }

        sc.close();
    }
}