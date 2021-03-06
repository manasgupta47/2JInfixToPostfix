package infix.postfix;

import java.util.Stack;

public class InfixToPostfix {
    public String infixToPostfix(String infixString){
        String postfix = "";
        Stack<Character> stack = new Stack<>();
        //step 1
        stack.push('(');
        infixString = infixString.concat(String.valueOf(')'));
        //step 2
        for (int i = 0; i < infixString.length() ; i++) {
            char character = infixString.charAt(i);
            //step 3
            if(Character.isAlphabetic(character) || Character.isDigit(character)){
                postfix = postfix.concat(String.valueOf(character));
            }
            //step 4
            else if(character == '('){
                stack.push(character);
            }
            //step 5
            else if(isOperator(character)){
                while(!stack.empty()){
                    if(precedence(stack.peek()) >=
                            precedence(character) ){
                        postfix = postfix.concat(String.valueOf(stack.pop()));
                    }
                    else{
                        stack.push(character);
                        break;
                    }
                }
            }
            //step 6
            else if(character == ')'){
               while (!stack.empty()){
                   if(stack.peek() != '('){
                       postfix = postfix.concat(String.valueOf(stack.pop()));
                   }
                   else{
                       stack.pop();
                       break;
                   }
               }
            }
        }
        return postfix;
    }
    private boolean isOperator(char character){
        boolean response = false;
        switch (character){
            case '^':
            case '/':
            case '*':
            case '+':
            case '-':
                response = true;
        }

        return response;
    }
    private int precedence(char operator){
        int response = 0;
        switch (operator){
            case '^':
                response = 3;
                break;
            case '/':
            case '*':
                response = 2;
                break;
            case '+':
            case '-':
                response = 1;
        }
        return response;
    }
    public double postfixEvaluation(String postfix){
        double  response = 0;
        Stack<Double> stack = new Stack<>();
        //step 1
        postfix = postfix.concat(String.valueOf(')'));
        //step 2
        for (int i = 0; i < postfix.length(); i++) {
            char character = postfix.charAt(i);
            if(Character.isDigit(character)){
                stack.push(Double.valueOf(Character.toString(character)));
            }else if(isOperator(character)){
                double second = stack.pop();
                double first = stack.pop();
                stack.push(evaluate(first, second,character));
                //stack.push(evaluate(stack.pop(),stack.pop(),character));
            }
        }
        response = stack.peek();
        return response;
    }
    private double evaluate(double first, double second, char operator){
        System.out.println(first);
        System.out.println(second);
        double response = 0;
        switch (operator) {
            case '^':
                response = Math.pow(first, second);
                break;
            case '/':
                response = (double) first / second;
                break;
            case '*':
                response = first * second;
                break;
            case '+':
                response = first + second;
                break;
            case '-':
                response = first - second;
        }
        return response;
    }
}
