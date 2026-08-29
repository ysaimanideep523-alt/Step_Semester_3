import java.util.Scanner;

public class ProductInventoryCSVParser {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter product details (ID,Name,Quantity,Price): ");
        String csv = sc.nextLine();

        String[] products = csv.split(",");

        if (products.length != 4) {
            System.out.println("Invalid CSV format.");
        } else {
            String productId = products[0].trim();
            String productName = products[1].trim();
            String quantity = products[2].trim();
            String price = products[3].trim();

            System.out.println("\nProduct Details");
            System.out.println("----------------");
            System.out.println("Product ID : " + productId);
            System.out.println("Name       : " + productName);
            System.out.println("Quantity   : " + quantity);
            System.out.println("Price      : " + price);
        }

        sc.close();
    }
}