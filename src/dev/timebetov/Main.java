package dev.timebetov;

import java.util.Scanner;

public class Main {

    // List of operations
    enum Operations {
        DIVIDE("/"), MULTIPLY("*"), SUBTRACT("-"), SUM("+");

        private final String type;

        Operations(String type) {
            this.type = type;
        }

        // Getter
        public String getType() {
            return type;
        }
    }

    // To user interaction via terminal
    private static Scanner scan;

    // Main method where application starts
    public static void main(String[] args) {

        try (Scanner scan = new Scanner(System.in)) {
            Main.scan = scan;
            do {
                mainController();
                System.out.print("\nWould you like to perform another calculation? (yes/no): ");
            } while (scan.next().equalsIgnoreCase("yes"));
            System.out.println("Goodbye!");
        }
    }

    private static void mainController() {

        // Greeting
        System.out.println("Hey, welcome to my simple calculator application made with Java & <3!");
        System.out.println("Enter the 2 numbers:");
        System.out.print("x=");
        double n1 = convertToDouble(scan.next());

        System.out.print("y=");
        double n2 = convertToDouble(scan.next());

        System.out.print("What type of operation you want (/, *, -, +) : ");
        Operations type = convertToOperation(scan.next());


        try {
            double result = handleOperation(n1, n2, type);
            System.out.printf("%.2f %s %.2f = %.2f", n1, type.getType(), n2, result);
        } catch (ArithmeticException e) {
            showError(e.getMessage());
        }
    }

    private static Operations convertToOperation(String type) {

        while (true) {
            try {
                return switch (type) {
                    case "/" -> Operations.DIVIDE;
                    case "*" -> Operations.MULTIPLY;
                    case "+" -> Operations.SUM;
                    case "-" -> Operations.SUBTRACT;
                    default -> throw new IllegalStateException("Unexpected value: " + type);
                };
            } catch (IllegalStateException e) {
                showError("Invalid operation, please try again (/, *, -, +)");
                type = scan.next();
            }
        }
    }

    private static double convertToDouble(String num) {

        double result;

        while (true) {
            try {
                result = Double.parseDouble(num);
                break;
            } catch (NumberFormatException e) {
                showError("Invalid Number, Please try again");
                num = scan.next();
            }
        }
        return result;
    }

    private static void showError(String message) {

        System.out.println("-".repeat(70));
        System.out.printf("ERROR: %s!%n", message);
        System.out.println("-".repeat(70));
    }

    private static double handleOperation(double x, double y, Operations operation) {
        return switch (operation) {
            case DIVIDE -> divide(x, y);
            case MULTIPLY -> x * y;
            case SUM -> x + y;
            case SUBTRACT -> x - y;
        };
    }

    private static double divide(double n1, double n2) {

        if (n2 == 0) {
            throw new ArithmeticException("Division by Zero");
        }

        return n1 / n2;
    }
}
