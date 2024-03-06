import java.util.Scanner;

public class StaircaseClimb {

    // Function to calculate the number of distinct ways to climb the staircase
    public static Integer climbStairs(Integer n) {
        // Base cases for 1 and 2 steps
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        // Create an array to store the number of ways to reach each step
        Integer[] dp = new Integer[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        // Calculate the number of ways for each step using dynamic programming
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // Print the climbing process step by step
        printClimbingProcess(n, dp);

        // Return the result for the top step
        return dp[n];
    }

    // Function to print the climbing process step by step
    private static void printClimbingProcess(Integer n, Integer[] dp) {
        System.out.println("Ways to climb each step:");

        for (int i = 1; i <= n; i++) {
            System.out.println("Step " + i + ": " + dp[i]);
        }

        System.out.println("Total ways to climb " + n + " steps: " + dp[n]);
    }

    public static void main(String[] args) {
        // Create a Scanner object to take user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the number of steps
        System.out.print("Enter the number of steps in the staircase: ");

        // Read the user input
        Integer n = scanner.nextInt();

        // Call the climbStairs function to calculate the result
        Integer waysToClimb = climbStairs(n);

        // Display the final result
        System.out.println("Final result: Ways to climb " + n + " steps: " + waysToClimb);

        // Close the Scanner to avoid resource leaks
        scanner.close();
    }
}
