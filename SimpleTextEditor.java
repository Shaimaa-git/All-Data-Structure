import java.util.Scanner;
import java.util.Stack;

public class SimpleTextEditor {
    // Stack to keep track of previous states of the text
    private static Stack<String> textStack = new Stack<>();
    // Current state of the text
    private static String currentText = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display initial message
        System.out.println("Simple Text Editor - Type 'exit' to quit or 'help' for assistance.");

        while (true) {
            // Prompt user for input
            System.out.print("Input: ");
            String userInput = scanner.nextLine().trim();

            // Check for exit command
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the text editor. Goodbye!");
                break;
            }

            // Check for help command
            if (userInput.equalsIgnoreCase("help")) {
                printHelpList();
            } else {
                // Process user input for other commands
                processInput(userInput);
                // Prompt for the next action
                System.out.println("What would you like to do next? Type 'exit' to quit or 'help' for assistance.");
            }
        }

        // Close the scanner
        scanner.close();
    }

    // Method to print the help list
    private static void printHelpList() {
        System.out.println("Available commands:");
        System.out.println("- append <text>: Add new text to the end of the current text.");
        System.out.println("- remove <count>: Delete the specified number of characters from the end of the current text.");
        System.out.println("- display: Show the current content of the text.");
        System.out.println("- undo: Revert the text to the state before the most recent append or remove operation.");
        System.out.println("- help: Display this help list.");
    }

    // Method to handle user input and perform corresponding operations
    private static void processInput(String userInput) {
        switch (userInput.split(" ")[0]) {
            case "append":
                // Append text command
                appendText(userInput);
                break;
            case "remove":
                // Remove characters command
                removeCharacters(userInput);
                break;
            case "display":
                // Display text command
                displayText();
                break;
            case "undo":
                // Undo operation command
                undoOperation();
                break;
            default:
                // Invalid input
                System.out.println("Invalid input. Please try again or type 'help' for assistance.");
        }
    }

    // Method to append new text to the current text
    private static void appendText(String userInput) {
        // Extract new text to append
        String newText = userInput.substring("append ".length());
        // Push current state to the stack for undo
        textStack.push(currentText);
        // Append the new text to the current text
        currentText += newText;
        // Display the updated text
        System.out.println("(text is now \"" + currentText + "\")");
    }

    // Method to remove a specified number of characters from the end of the current text
    private static void removeCharacters(String userInput) {
        try {
            // Extract the number of characters to remove
            int removeCount = Integer.parseInt(userInput.substring("remove ".length()));
            // Check if the count is valid
            if (removeCount >= 0 && removeCount <= currentText.length()) {
                // Push current state to the stack for undo
                textStack.push(currentText);
                // Remove the specified number of characters from the end
                currentText = currentText.substring(0, currentText.length() - removeCount);
                // Display the updated text
                System.out.println("(text is now \"" + currentText + "\")");
            } else {
                // Invalid 'remove' operation
                System.out.println("Invalid 'remove' operation. Please provide a valid count.");
            }
        } catch (NumberFormatException e) {
            // Invalid 'remove' operation
            System.out.println("Invalid 'remove' operation. Please provide a valid count.");
        }
    }

    // Method to display the current state of the text
    private static void displayText() {
        // Display the current text
        System.out.println("\"" + currentText + "\"");
    }

    // Method to undo the last operation and revert the text to the previous state
    private static void undoOperation() {
        // Check if the stack is not empty
        if (!textStack.isEmpty()) {
            // Pop the previous state from the stack
            currentText = textStack.pop();
            // Display the reverted text
            System.out.println("(text is reverted to \"" + currentText + "\")");
        } else {
            // Nothing to undo
            System.out.println("Nothing to undo. The text is already at its initial state.");
        }
    }
}
