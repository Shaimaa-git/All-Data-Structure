import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PowerSetGenerator {

    // Function to generate the power set of a given set of distinct integers
    public static List<List<Integer>> generatePowerSet(int[] nums) {
        // Result list to store all subsets
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());  // Add the empty set

        // Iterate through each element in the input set
        for (int num : nums) {
            // Temporary list to store new subsets for the current element
            List<List<Integer>> newSubsets = new ArrayList<>();

            // Iterate through existing subsets and create new subsets by adding the current element
            for (List<Integer> subset : result) {
                List<Integer> newSubset = new ArrayList<>(subset);
                newSubset.add(num);
                newSubsets.add(newSubset);
            }

            // Add the new subsets to the result list
            result.addAll(newSubsets);
        }

        // Final result containing all subsets
        return result;
    }

    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the input set
        System.out.print("Enter the elements of the set (comma-separated): ");
        String inputString = scanner.nextLine();

        // Convert the user input string to an array of integers
        int[] inputSet = Arrays.stream(inputString.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        // Generate power set for the user-entered input
        List<List<Integer>> powerSet = generatePowerSet(inputSet);

        // Display the result
        System.out.println("\nPower Set: " + powerSet);
    }
}
