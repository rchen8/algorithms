import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PolishNotation {

	private static boolean isOperator(String s) {
		return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")
				|| s.equals("%") || s.equals("^");
	}

	private static int precedence(String s) {
		switch (s) {
		case "+":
		case "-":
			return 0;
		case "*":
		case "/":
			return 1;
		case "^":
			return 2;
		}
		return -1;
	}

	private static int evaluate(int left, int right, String operator) {
		switch (operator) {
		case "+":
			return left + right;
		case "-":
			return left - right;
		case "*":
			return left * right;
		case "/":
			return left / right;
		case "%":
			return left % right;
		case "^":
			return (int) Math.round(Math.pow(left, right));
		}
		return -1;
	}

	private static String prefixToInfix(String[] prefix, boolean evaluate) {
		Stack<String> stack = new Stack<>();

		for (int i = prefix.length - 1; i >= 0; i--) {
			if (prefix[i].matches("\\w+"))
				stack.push(prefix[i]);
			else {
				if (evaluate) {
					int left = Integer.parseInt(stack.pop());
					int right = Integer.parseInt(stack.pop());
					stack.push("" + evaluate(left, right, prefix[i]));
				} else {
					String left = stack.pop();
					String right = stack.pop();
					stack.push("( " + left + " " + prefix[i] + " " + right
							+ " )");
				}
			}
		}

		return stack.peek();
	}

	private static String postfixToInfix(String[] postfix, boolean evaluate) {
		Stack<String> stack = new Stack<>();

		for (int i = 0; i < postfix.length; i++) {
			if (postfix[i].matches("\\w+")) {
				stack.push(postfix[i]);
			} else {
				if (evaluate) {
					int right = Integer.parseInt(stack.pop());
					int left = Integer.parseInt(stack.pop());
					stack.push("" + evaluate(left, right, postfix[i]));
				} else {
					String right = stack.pop();
					String left = stack.pop();
					stack.push("( " + left + " " + postfix[i] + " " + right
							+ " )");
				}
			}
		}

		return stack.pop();
	}

	private static String infixToPrefix(String[] infix) {
		Stack<String> stack = new Stack<>();
		Stack<String> queue = new Stack<>();

		for (int i = infix.length - 1; i >= 0; i--) {
			if (infix[i].matches("\\w+"))
				queue.push(infix[i]);
			else if (isOperator(infix[i])) {
				while (!stack.isEmpty()
						&& (!infix[i].equals("^")
								&& precedence(infix[i]) <= precedence(stack
										.peek()) || infix[i].equals("^")
								&& precedence(infix[i]) < precedence(stack
										.peek())))
					queue.push(stack.pop());
				stack.push(infix[i]);
			} else if (infix[i].equals(")"))
				stack.push(infix[i]);
			else if (infix[i].equals("(")) {
				while (!stack.peek().equals(")"))
					queue.push(stack.pop());
				stack.pop();
			}
		}

		while (!stack.isEmpty())
			queue.push(stack.pop());

		String prefix = "";
		while (!queue.isEmpty())
			prefix += queue.pop() + " ";
		return prefix.trim();
	}

	private static String infixToPostfix(String[] infix) {
		Stack<String> stack = new Stack<>();
		Queue<String> queue = new LinkedList<>();

		for (int i = 0; i < infix.length; i++) {
			if (infix[i].matches("\\w+"))
				queue.add(infix[i]);
			else if (isOperator(infix[i])) {
				while (!stack.isEmpty()
						&& (!infix[i].equals("^")
								&& precedence(infix[i]) <= precedence(stack
										.peek()) || infix[i].equals("^")
								&& precedence(infix[i]) < precedence(stack
										.peek())))
					queue.add(stack.pop());
				stack.push(infix[i]);
			} else if (infix[i].equals("("))
				stack.push(infix[i]);
			else if (infix[i].equals(")")) {
				while (!stack.peek().equals("("))
					queue.add(stack.pop());
				stack.pop(); // left parenthesis
			}
		}

		while (!stack.isEmpty())
			queue.add(stack.pop());

		String postfix = "";
		while (!queue.isEmpty())
			postfix += queue.poll() + " ";
		return postfix.trim();
	}

	public static void main(String[] args) {
		System.out.println(prefixToInfix("* - + 3 5 4 / + 4 6 2".split("\\s+"),
				true));
		System.out.println(prefixToInfix("+ - + A B * C D ^ E F".split("\\s+"),
				false));

		System.out.println(postfixToInfix("12 5 - 4 5 3 + - *".split("\\s+"),
				true));
		System.out.println(postfixToInfix("6 7 * 9 + 8 2 * -".split("\\s+"),
				false));

		System.out.println();
		System.out.println(infixToPrefix("A + B * C - D".split("\\s+")));
		System.out.println(infixToPostfix("A + B * C - D".split("\\s+")));

		System.out.println();
		System.out.println(infixToPrefix("A ^ B ^ C".split("\\s+")));
		System.out.println(infixToPostfix("A ^ B ^ C".split("\\s+")));
	}

}
