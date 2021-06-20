package ru.appline.logic;

public class Operation {

    private static Operation operation;
    private static String operations = "+-/*";

    private Operation() {

    }

    public static Operation getInstance() {
        if (operation == null) {
            operation = new Operation();
        }
        return operation;
    }

    private double sum(double a, double b) {
        return a + b;
    }

    private double subtraction(double a, double b) {
        return a - b;
    }

    private double multiplication(double a, double b) {
        return a * b;
    }

    private double division(double a, double b) {
        return a / b;
    }

    public Double getResult(String operation, double a, double b) {
        Double result = null;
        switch (operation) {
            case "+":
                result = sum(a, b);
                break;
            case "-":
                result = subtraction(a, b);
                break;
            case "*":
                result = multiplication(a, b);
                break;
            case "/":
                    result = division(a, b);
                break;
            default:

        }
        return result;
    }

    public boolean checkOperation(String operation) {
        return operations.contains(operation);
    }

    public boolean checkData(String a, String b) {
        boolean isCorrect = true;
        try {
            Double.parseDouble(a);
            Double.parseDouble(b);
        }catch (NumberFormatException e) {
            isCorrect = false;
        }
        return isCorrect;
    }
}
