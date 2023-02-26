package com.arithmetic;
import java.util.ArrayList;

public class ArithmeticExpressionEvaluator {
    static int precedence(String c){
        if(c.equals("+") || c.equals("-")) {
            return 1;
        } else if(c.equals("*") || c.equals("/")) {
            return 2;
        } else if(c.equals("^")) {
            return 3;
        } else {
            return -1;
        }
    }

    static String infixToPostFix(String expression){

        String[] splitString = expression.split("\\s+");
        String postFixExpression = "";
        Stack<String> stack = new Stack<>();
        ArrayList<String> postFixArrayList= new ArrayList<>();
        for (int i = 0; i < splitString.length ; i++) {
            String c = splitString[i];

            //first check if its an open parentheses
            if(c.equals("(")){
                stack.push(c);
            //next check if it's a close parenthesis pop until the open
            } else if(c.equals(")")){
                String x = stack.pop();
                while(!x.equals("(")){
                    postFixArrayList.add(x);
                    x = stack.pop();
                }
            //if it's an operator, check the precedence and act
            } else if(precedence(c) > 0){
                while(!stack.isEmpty() && precedence(stack.top()) >= precedence(c)){
                    postFixArrayList.add(stack.pop());
                }
                stack.push(c);
            //if it's a number just add it
            } else{
                postFixArrayList.add(c);
            }
        }
        //pop everything left!
        for (int i = 0; i <= stack.size() ; i++) {
            postFixArrayList.add(stack.pop());
        }

        for (int i = 0; i < postFixArrayList.size(); i++) {
            postFixExpression += postFixArrayList.get(i) + " ";
        }
        return postFixExpression;
    }

    public static Double operate(double a, double b, char operator){
        if(operator == '*') {
            return a * b;
        } else if(operator == '/') {
            if (a == 0)
                throw new
                        UnsupportedOperationException("Cannot divide by zero");
            return b / a;
        } else if(operator == '+') {
            return a + b;
        } else if(operator == '-') {
            return b - a;
        } else {
            return 0.0;
        }
    }
    public static Double evaluatePostFix(String expression) {

        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            //if its a space we want to move on
            if(c==' ')
                continue;

            //if its an operator we must do the operation
            if (c == '*' || c == '/' || c == '^' || c == '+' || c == '-') {
                double a = stack.pop();
                double b = stack.pop();
                double temp;
                if(c == '*') {
                    temp = a * b;
                } else if(c == '/') {
                    if (a == 0)
                        throw new
                                UnsupportedOperationException("Cannot divide by zero");
                    temp = b / a;
                } else if(c == '+') {
                    temp = a + b;
                } else if(c == '-') {
                    temp = b - a;
                } else if(c == '^') {
                    temp = Math.pow(b, a);
                } else {
                    temp = 0.0;
                }
                stack.push(temp);
            //if it's a number we want to make sure we get all the digits
            } else {
                double number = 0;

                while(Character.isDigit(c)) {
                    number = number * 10 + (c - '0');
                    i++;
                    c = expression.charAt(i);
                }
                i--;
                stack.push(number);
            }
        }
        double result = stack.pop();
        return result;
    }




    public static void main(String[] args) {
        String exp = "15 * ( 78 * ( 3 + 9 ) ) / 300 - 2 ^ 3";
        System.out.println("Infix Expression: " + exp);
        System.out.println("Postfix Expression: " + infixToPostFix(exp));
        System.out.println("Result: " + evaluatePostFix(infixToPostFix(exp)));

        String exp2 = "25 + 30 * 17 / ( 16 - 8 )";
        System.out.println("Infix Expression: " + exp2);
        System.out.println("Postfix Expression: " + infixToPostFix(exp2));
        System.out.println("Result: " + evaluatePostFix(infixToPostFix(exp2)));
    }

}
