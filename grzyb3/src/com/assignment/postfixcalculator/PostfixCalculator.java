package com.assignment.postfixcalculator;

/**
 * Class for PostfixCalculator
 *
 * @author abc
 */
public class PostfixCalculator {
     private LinkedStack<Integer> stack;

    public PostfixCalculator(){
         stack = new LinkedStack<>();
    }

    /**Perform the appropriate calculations
     *
     * @param postfixExpression
     * @return result
     * @throws IllegalArgumentException
     * @throws PostfixCalculatorException
     */
    public int evaluate(String postfixExpression) throws IllegalArgumentException,PostfixCalculatorException {
        int result = 0;
        System.out.println("Input postfixExpression : " + postfixExpression);
        System.out.println("");
        System.out.println("");

        //Eliminating leading and trailing spaces of the String and breaking up that String into pieces using whitespace as a delimiter and storing it in array of String
        String[] splitPostfixExpression = postfixExpression.trim().split("\\s+");

        //Iterating the array of Strings
        for(int i = 0 ; i < splitPostfixExpression.length ; i++){
            String value = splitPostfixExpression[i];

            //Check if value of the array is a arithmetic operator
            if(value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/") || value.equals("%") ){
                System.out.println(value + " arithmetic operation start");
                Integer secondValue = stack.pop();
                Integer firstValue = stack.pop();
                System.out.println("Popping the two values :" + firstValue + "," + secondValue);
                Integer arithmeticOperationResult=0;

                //Performing required Arithmetic Operation
                switch (value){
                    case "+":
                        arithmeticOperationResult = firstValue + secondValue;
                        break;

                    case "-":
                        arithmeticOperationResult = firstValue - secondValue;
                        break;

                    case "*":
                        arithmeticOperationResult = firstValue * secondValue;
                        break;

                    case "/":
                        //if the Second value is zero throw IllegalArgumentException
                        if(secondValue == 0){
                            throw new IllegalArgumentException("Cannot divide by 0" );
                        }
                        arithmeticOperationResult = firstValue / secondValue;
                        break;

                    case "%":
                        //if the Second value is zero throw IllegalArgumentException
                        if(secondValue == 0){
                            throw new IllegalArgumentException("Cannot divide by 0" );
                        }
                        arithmeticOperationResult = firstValue % secondValue;
                        break;
                }
                System.out.println(value + " arithmetic operation result: " + arithmeticOperationResult + " push to stack");
                //Push the arithmetic operation result to stack
                stack.push(arithmeticOperationResult);
                System.out.println("");
                System.out.println("");

            }else{
                Integer parsedNumValue;
                try {
                    parsedNumValue = Integer.parseInt(value);
                } catch (NumberFormatException  nfe) {
                    throw new PostfixCalculatorException(value + " is neither numeric character nor arithmetic operator" );
                }
                System.out.println("Numeric character " + parsedNumValue + " push to stack");
                //Push the numeric character to stack
                stack.push(parsedNumValue);
                System.out.println("");
            }

        }
        System.out.println("Final Result is " + stack.peek());
        return result;
    }
}
