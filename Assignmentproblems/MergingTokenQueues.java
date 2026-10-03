import java.util.*;

public class MergingTokenQueues {

    public static ArrayList<Integer> mergeTokens(
            ArrayList<Integer> counterA,
            ArrayList<Integer> counterB) {

        ArrayList<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < counterA.size() && j < counterB.size()) {

            if (counterA.get(i) <= counterB.get(j)) {
                result.add(counterA.get(i));
                i++;
            } else {
                result.add(counterB.get(j));
                j++;
            }
        }

        while (i < counterA.size()) {
            result.add(counterA.get(i));
            i++;
        }

        while (j < counterB.size()) {
            result.add(counterB.get(j));
            j++;
        }

        return result;
    }

    public static void main(String[] args) {

        ArrayList<Integer> counterA =
                new ArrayList<>(Arrays.asList(3, 8, 15, 20));

        ArrayList<Integer> counterB =
                new ArrayList<>(Arrays.asList(5, 8, 12));

        ArrayList<Integer> result =
                mergeTokens(counterA, counterB);

        System.out.println("Merged Tokens: " + result);
    }
}