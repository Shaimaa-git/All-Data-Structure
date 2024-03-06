import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MergeKSortedArrays {

    // Helper class to represent an element along with its array index and index in the array
    static class Element {
        int value;
        int arrayIndex;
        int elementIndex;

        // Helper method to initialize an Element
        static Element createElement(int value, int arrayIndex, int elementIndex) {
            Element element = new Element();
            element.value = value;
            element.arrayIndex = arrayIndex;
            element.elementIndex = elementIndex;
            return element;
        }
    }

    // Method to merge k sorted arrays into a single sorted array
    public static int[] mergeKArrays(List<int[]> arrays) {
        // List to store the merged result
        List<Integer> result = new ArrayList<>();

        // Array to store the current index for each array
        int[] currentIndex = new int[arrays.size()];

        // Continue until all arrays are exhausted
        while (true) {
            Element smallest = findSmallest(arrays, currentIndex);

            // If no smallest element is found, break the loop
            if (smallest == null) {
                break;
            }

            // Add the smallest element to the result
            result.add(smallest.value);

            // Move to the next element in the array containing the smallest element
            currentIndex[smallest.arrayIndex]++;
        }

        // Convert the result list to an array
        int[] mergedArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            mergedArray[i] = result.get(i);
        }

        // Return the merged and sorted array
        return mergedArray;
    }

    // Helper method to find the smallest element among the current indices of all arrays
    private static Element findSmallest(List<int[]> arrays, int[] currentIndex) {
        Element smallest = null;

        for (int i = 0; i < arrays.size(); i++) {
            int arrayIndex = i;
            int elementIndex = currentIndex[i];

            // Skip arrays that are exhausted
            if (elementIndex < arrays.get(arrayIndex).length) {
                // Update the smallest element if it's null or the current element is smaller
                if (smallest == null || arrays.get(arrayIndex)[elementIndex] < smallest.value) {
                    smallest = Element.createElement(arrays.get(arrayIndex)[elementIndex], arrayIndex, elementIndex);
                }
            }
        }

        return smallest;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<int[]> inputArrays = new ArrayList<>();

        System.out.println("Enter k sorted arrays. Each array should contain integers separated by spaces.");

        // Prompt the user to enter each sorted array
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter elements for array " + (i + 1) + ": ");
            String inputLine = scanner.nextLine();
            String[] inputElements = inputLine.split(" ");

            int[] array = new int[inputElements.length];
            for (int j = 0; j < inputElements.length; j++) {
                array[j] = Integer.parseInt(inputElements[j]);
            }

            inputArrays.add(array);
        }

        // Merge the arrays
        int[] mergedArray = mergeKArrays(inputArrays);

        // Display the result
        System.out.print("Output: [");
        for (int i = 0; i < mergedArray.length; i++) {
            System.out.print(mergedArray[i]);
            if (i < mergedArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        scanner.close();
    }
}
