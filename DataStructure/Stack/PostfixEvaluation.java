package DataStructure.Stack;

import java.util.Stack;

public class PostfixEvaluation {
    public static double evaluatePostfix(String exp) {
        Stack<Double> stack = new Stack<>();

        final String op = "+-*/";
        Double A, B, result = 0d;

        for (char c : exp.toCharArray()) {
            if (op.indexOf(c) != -1) {
                A = stack.pop();
                B = stack.pop();

                if (c == '+') result = B + A;
                else if (c == '-') result = B - A;
                else if (c == '*') result = B * A;
                else if (c == '/') result = B / A;

                stack.push(result);
            } else {
                stack.push((double) (c - '0'));
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        String expression = "562+*24/-";
        System.out.println("Result: " + evaluatePostfix(expression));
    }
}
