import java.util.Scanner;
import java.util.Stack;

public class ArithmeticExpressionEvaluator {

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private static int applyOperator(char operator, int operand1, int operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                return 0;
        }
    }

    public static int evaluateExpression(String expression) {
        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (Character.isDigit(currentChar)) {
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i));
                    i++;
                }
                i--;
                values.push(Integer.parseInt(number.toString()));
            } else if (currentChar == '(') {
                operators.push(currentChar);
            } else if (currentChar == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    int operand2 = values.pop();
                    int operand1 = values.pop();
                    char operator = operators.pop();
                    values.push(applyOperator(operator, operand1, operand2));
                }
                operators.pop();
            } else if (isOperator(currentChar)) {
                while (!operators.isEmpty() && getPrecedence(operators.peek()) >= getPrecedence(currentChar)) {
                    int operand2 = values.pop();
                    int operand1 = values.pop();
                    char operator = operators.pop();
                    values.push(applyOperator(operator, operand1, operand2));
                }
                operators.push(currentChar);
            }
        }

        while (!operators.isEmpty()) {
            int operand2 = values.pop();
            int operand1 = values.pop();
            char operator = operators.pop();
            values.push(applyOperator(operator, operand1, operand2));
        }

        return values.pop();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter an arithmetic expression:");
        String userInput = scanner.nextLine();

        try {
            int result = evaluateExpression(userInput);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error: Invalid input format");
        } finally {
            scanner.close();
        }
    }
}
