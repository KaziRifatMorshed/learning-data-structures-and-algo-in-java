package DataStracture.Stack;

import java.util.Stack;

public class InfixToPostfix {
    public static String convertInfixToPostfix(String exp) {
        String postfix = "";
        Stack<Character> stack = new Stack<>();
        final String opening = "({[";
        final String closing = ")}]";
        final String operators = "+-*/";
        final String operators2 = "-+/*"; // why ???

        for (char c : exp.toCharArray()) {
            if (opening.indexOf(c) != -1) {
                stack.push(c);
            } else if (closing.indexOf(c) != -1) {
                stack.push(c);
            }

        }

        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }

        return postfix;
    }
}




//package infixToPostfix;
//import stack.LinkedStack;
//
//public class InfixToPostfix {
//
//    public static String convertToPostfix(String expression) {
//        LinkedStack<Character> stack = new LinkedStack<Character>();
//        final String opening = "({[";
//        final String closing = ")}]";
//        final String operator = "+-*/";
//        final String operator2 = "-+/*";
//        String postFix = "";
//        for(char c : expression.toCharArray()) {
//            if(opening.indexOf(c) != -1) {
//                stack.push(c);
//            }
//            else if(operator.indexOf(c) != -1) {
//
//                while(	!stack.isEmpty() &&
//                        opening.indexOf(stack.top()) == -1 &&
//                        (
//                                operator.indexOf(c) <= operator.indexOf(stack.top()) ||
//                                        operator2.indexOf(c) <= operator2.indexOf(stack.top())
//                        )
//                )
//                {
//
//                    postFix += stack.pop();
//
//                } // while end
//
//                stack.push(c);
//
//            }
//            else if(closing.indexOf(c) != -1) {
//                while(closing.indexOf(c) != opening.indexOf(stack.top())) {
//                    postFix += stack.pop();
//                }
//                stack.pop();
//            }
//            else {
//                postFix += c;
//            }
//        }
//
//        while(!stack.isEmpty()) {
//            postFix += stack.pop();
//        }
//        return postFix;
//    }
//
//    public static void main(String[] args) {
//        String expression = "5*(6+2)-(12/4)";
//        System.out.println("PostFix: "+ convertToPostfix(expression));
//    }
//}
