import java.util.*;

public class MostPopularCanteenOrder {

    public static String[] mostPopular(String[] orders) {

        HashMap<String, Integer> count = new HashMap<>();

        
        for (String item : orders) {
            count.put(item, count.getOrDefault(item, 0) + 1);
        }

        String popularItem = "";
        int maxCount = 0;

        for (String item : orders) {

            if (count.get(item) > maxCount) {
                maxCount = count.get(item);
                popularItem = item;
            }
        }

        return new String[]{popularItem, String.valueOf(maxCount)};
    }

    public static void main(String[] args) {

        String[] orders = {
            "dosa",
            "idli",
            "vada",
            "dosa",
            "idli",
            "dosa",
            "tea"
        };

        String[] result = mostPopular(orders);

        System.out.println("Most Popular Item: " + result[0]);
        System.out.println("Count: " + result[1]);
    }
}