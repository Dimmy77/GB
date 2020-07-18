package ru.geakbrains.tokens;

public class Calculator {
    private String[] tokens;
    private int pos;


    public static void main(String[] args) {
        //String expr="1 + 2 * ( 3 -4 ) + 6 / 7";
        String expr = "1 + 2 - 3 + 4 - 5 + 6 - 7 + 8";
        Calculator calculator = new Calculator(expr);
        System.out.println(expr + "=" + calculator.calculate());
        System.out.println("Check = " + (1 + 2 - 3 + 4 - 5 + 6 - 7 + 8));
    }

    public Calculator(String expr) {
        this.tokens = expr.split(" ");
        this.pos = 0;
    }



    public double calculate() {
        /*double first = Double.parseDouble(tokens[pos++]);*/
        double first = multiply();

        while (pos < tokens.length) {
            String operator = tokens[pos];
            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            } else {
                pos++;
            }
            double second = Double.parseDouble(tokens[pos++]);
            if (operator.equals("+")) {
                first += second;
            } else {
                first -= second;
            }
        }
        return first;
    }

    public double multiply() {
        double first = factor();

        while (pos < tokens.length) {
            String operator = tokens[pos];
            if (!operator.equals("*") && !operator.equals("/")) {
                break;
            } else {
                pos++;
            }
            double second = Double.parseDouble(tokens[pos++]);
            if (operator.equals("*")) {
                first *= second;
            } else {
                first /= second;

            }
        }
        return first;
    }

    public double factor() {
        String next = tokens[pos];
        double result;
        if (next.equals("(")) {
            pos++;
            result = calculate();
            String closingBracket;
            if (pos < tokens.length) {
                closingBracket = tokens[pos];
            } else {
                throw new IllegalArgumentException("Unexpected end of expression");
            }
            if (closingBracket.equals(")")) {
                pos++;
                return result;
            }
            throw new IllegalArgumentException("')' expected but " + closingBracket + " found");
        }
        pos++;
        return Double.parseDouble(next);
    }
}
