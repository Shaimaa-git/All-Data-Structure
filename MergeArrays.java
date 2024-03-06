import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MergeKSortedArrays {

    // Helper class to represent an element along with its array index and index in the array
    static class Element implements Comparable<Element> {
        int value;
        int arrayIndex;
        int elementIndex;

        // Constructor for the Element class
        Element(int value, int arrayIndex, int elementIndex) {
            // Assign values to instance variables
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        // CompareTo method for sorting elements in the priority queue
        @Override
        public int compareTo(Element other) {
            return Integer.compare(value, other.value);
        }
    }

    // Method to merge k sorted arrays into a single sorted array
    public static int[] mergeKArrays(List<int[]> arrays) {
        // Initialize a priority queue to store elements along with their array index
        PriorityQueue<Element> minHeap = new PriorityQueue<>();

        // Add the first element from each array to the min heap
        for (int i = 0; i < arrays.size(); i++) {
            if (arrays.get(i).length > 0) {
                minHeap.offer(new Element(arrays.get(i)[0], i, 0));
            }
        }

        // List to store the merged result
        List<Integer> result = new ArrayList<>();

        // Continue extracting the smallest element from the min heap and adding the next element from the corresponding array
        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll();
            result.add(current.value);

            int arrayIndex = current.arrayIndex;
            int elementIndex = current.elementIndex + 1;

            // Add the next element from the current array to the min heap
            if (elementIndex < arrays.get(arrayIndex).length) {
                minHeap.offer(new Element(arrays.get(arrayIndex)[elementIndex], arrayIndex, elementIndex));
            }
        }

        // Convert the result list to an array
        int[] mergedArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            mergedArray[i] = result.get(i);
        }

        // Return the merged and sorted array
        return mergedArray;
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
