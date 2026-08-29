import java.util.Scanner;

public class ISBNNormalizerValidator {

    public static boolean isValidISBN10(String isbn) {
        if (isbn.length() != 10) {
            return false;
        }

        int sum = 0;

        for (int i = 0; i < 10; i++) {
            char ch = isbn.charAt(i);

            int value;

            if (ch == 'X' && i == 9) {
                value = 10;
            } else if (Character.isDigit(ch)) {
                value = ch - '0';
            } else {
                return false;
            }

            sum += value * (10 - i);
        }

        return sum % 11 == 0;
    }

    public static boolean isValidISBN13(String isbn) {
        if (isbn.length() != 13 || !isbn.matches("\\d{13}")) {
            return false;
        }

        int sum = 0;

        for (int i = 0; i < 13; i++) {
            int digit = isbn.charAt(i) - '0';

            if (i % 2 == 0) {
                sum += digit;
            } else {
                sum += digit * 3;
            }
        }

        return sum % 10 == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ISBN: ");
        String input = sc.nextLine();

        // Remove spaces and hyphens
        String isbn = input.replace("-", "").replace(" ", "").toUpperCase();

        System.out.println("Normalized ISBN: " + isbn);

        if (isValidISBN10(isbn)) {
            System.out.println("Valid ISBN-10");
        } else if (isValidISBN13(isbn)) {
            System.out.println("Valid ISBN-13");
        } else {
            System.out.println("Invalid ISBN");
        }

        sc.close();
    }
}