import java.util.*;

public class TicketPriceSlotFinder {

    public static int findSlot(int[] prices, int newPrice) {

        int low = 0;
        int high = prices.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (prices[mid] == newPrice) {
                return mid;
            }

            if (prices[mid] < newPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public static void main(String[] args) {

        int[] prices = {
            120, 150, 200, 260
        };

        int newPrice = 210;

        int result = findSlot(prices, newPrice);

        System.out.println("Insertion Index: " + result);
    }
}