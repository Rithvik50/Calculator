import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.text.DecimalFormat;

//This class emulates a calculator using GUI

public class Calculator implements ActionListener {

	private JButton clear, equalsTo, decimal, off;
	private JButton addition, subtraction, multiplication, division, b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
	private JTextField inputField;
	private ArrayList<JButton> numbers = new ArrayList<JButton>(10);
	private StringBuilder expression = new StringBuilder("");

	public Calculator() {
		Color background = new Color(8, 124, 168);
		JFrame w = new JFrame("Calculator");
		w.setBounds(100, 100, 400, 300);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.getContentPane().setBackground(background);
		w.setLayout(new FlowLayout(FlowLayout.CENTER));

		decimal = new JButton(".");
		decimal.setSize(30, 30);
		decimal.setVisible(true);
		decimal.addActionListener(this);

		addition = new JButton("+");
		addition.setSize(30, 30);
		addition.setVisible(true);
		addition.addActionListener(this);

		subtraction = new JButton("-");
		subtraction.setSize(30, 30);
		subtraction.setVisible(true);
		subtraction.addActionListener(this);

		multiplication = new JButton("*");
		multiplication.setSize(30, 30);
		multiplication.setVisible(true);
		multiplication.addActionListener(this);

		division = new JButton("/");
		division.setSize(30, 30);
		division.setVisible(true);
		division.addActionListener(this);

		clear = new JButton("AC");
		clear.setSize(30, 30);
		clear.setVisible(true);
		clear.addActionListener(this);

		equalsTo = new JButton("=");
		equalsTo.setSize(30, 30);
		equalsTo.setVisible(true);
		equalsTo.addActionListener(this);

		off = new JButton("OFF");
		off.setSize(30, 30);
		off.setVisible(true);
		off.addActionListener(this);

		b0 = new JButton("0");
		b0.setSize(30, 30);
		b0.setVisible(true);
		b0.addActionListener(this);
		numbers.add(b0);

		b1 = new JButton("1");
		b1.setSize(30, 30);
		b1.setVisible(true);
		b1.addActionListener(this);
		numbers.add(b1);

		b2 = new JButton("2");
		b2.setSize(30, 30);
		b2.setVisible(true);
		b2.addActionListener(this);
		numbers.add(b2);

		b3 = new JButton("3");
		b3.setSize(30, 30);
		b3.setVisible(true);
		b3.addActionListener(this);
		numbers.add(b3);

		b4 = new JButton("4");
		b4.setSize(30, 30);
		b4.setVisible(true);
		b4.addActionListener(this);
		numbers.add(b4);

		b5 = new JButton("5");
		b5.setSize(30, 30);
		b5.setVisible(true);
		b5.addActionListener(this);
		numbers.add(b5);

		b6 = new JButton("6");
		b6.setSize(30, 30);
		b6.setVisible(true);
		b6.addActionListener(this);
		numbers.add(b6);

		b7 = new JButton("7");
		b7.setSize(30, 30);
		b7.setVisible(true);
		b7.addActionListener(this);
		numbers.add(b7);

		b8 = new JButton("8");
		b8.setSize(30, 30);
		b8.setVisible(true);
		b8.addActionListener(this);
		numbers.add(b8);

		b9 = new JButton("9");
		b9.setSize(30, 30);
		b9.setVisible(true);
		b9.addActionListener(this);
		numbers.add(b9);

		inputField = new JTextField("", 33);
		inputField.setEditable(false);

		w.add(inputField);
		w.add(addition);
		w.add(subtraction);
		w.add(multiplication);
		w.add(division);
		w.add(decimal);
		w.add(equalsTo);
		w.add(clear);
		w.add(b0);
		w.add(b1);
		w.add(b2);
		w.add(b3);
		w.add(b4);
		w.add(b5);
		w.add(b6);
		w.add(b7);
		w.add(b8);
		w.add(b9);
		w.add(off);

		w.setResizable(false);
		w.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() != equalsTo) {
			for (int i = 0; i < numbers.size(); i++) {
				if (e.getSource() == numbers.get(i)) {
					expression.append(i + "");
				}
			}

			if (e.getSource() == decimal) {
				expression.append(".");
			}

			if (e.getSource() == addition) {
				expression.append("+");
			} else if (e.getSource() == subtraction) {
				expression.append("-");
			} else if (e.getSource() == multiplication) {
				expression.append("*");
			} else if (e.getSource() == division) {
				expression.append("/");
			} else {
			}
			inputField.setText(expression + "");

			if (e.getSource() == clear) {
				inputField.setText("");
				expression = new StringBuilder("");
			}
		}

		if (e.getSource() == equalsTo) {
			infixToPostfix();
		}

		if (e.getSource() == off) {
			System.exit(0);
		}
	}

	public void infixToPostfix() {
//		before infix = "1.1+2.2*3.3-4.4/5.5"
//		postfix: "1.1 2.2 3.3 -4.4 * 5.5 / +"
		Stack<String> beforeInfix = new Stack<String>();
		Stack<String> infix = new Stack<String>();
		Stack<String> numFix = new Stack<String>();
		Stack<String> opFix = new Stack<String>();
		StringBuilder infixString = new StringBuilder("");
		Stack<String> postFix = new Stack<String>();
		Stack<Double> accumulator = new Stack<Double>();

		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*'
					|| expression.charAt(i) == '/') {
				beforeInfix.push(infixString + "");
				beforeInfix.push(expression.charAt(i) + "");
				infixString = new StringBuilder("");
			} else {
				infixString.append(expression.charAt(i));
				if (i == expression.length() - 1) {
					beforeInfix.push(infixString + "");
				}
			}
		}

		// reverse beforeInfix and call it infix
		while (beforeInfix.size() != 0) {
			String poppedInfix = beforeInfix.pop();
			infix.push(poppedInfix);
		}

		// converting infix stack to postfix stack
		while (infix.size() != 0) {
			String infixPop = infix.pop();
			if (!((infixPop.equals("+")) || (infixPop.equals("-")) || (infixPop.equals("*"))
					|| (infixPop.equals("/")))) {
				numFix.push(infixPop);
				continue;
			}
			if (opFix.isEmpty() == true) {
				opFix.push(infixPop);
				continue;
			} else {
				if ((opFix.peek().equals("+") || opFix.peek().equals("-"))
						&& (infixPop.equals("*") || infixPop.equals("/"))) {
					opFix.push(infixPop);
					continue;
				} else if ((opFix.peek().equals("+") || opFix.peek().equals("-"))
						&& (infixPop.equals("+") || infixPop.equals("-"))) {
					// + or - cant go ontop of + or - so move symbol from operator stack to
					// numberstack//
					while (opFix.isEmpty() == false) {
						numFix.push(opFix.pop());
					}
					opFix.push(infixPop);
					continue;
				} else if (!(opFix.isEmpty() == true) && (opFix.peek().equals("*") || opFix.peek().equals("/"))) {
					// +-*/ cant go ontop of * or / so move symbols from ooperator stack to number
					// stack till
					// you hit + or - or till your operator stack become empty.
					while (!(opFix.isEmpty() == true) && !(opFix.peek().equals("+") || opFix.peek().equals("-"))) {
						numFix.push(opFix.pop());
					}
					opFix.push(infixPop);
				}

			}
		} // major while loop ends

		// pop each from opFix stack and push it to numFix stack

		while (opFix.isEmpty() == false) {
			numFix.push(opFix.pop());
		} // at this stage, infix has been converted to postfix and stored in numFix

		while (numFix.size() != 0) {
			String poppedNumfix = numFix.pop();
			postFix.push(poppedNumfix);
		}

		while (postFix.size() != 0) {
			String postFixPopped = postFix.pop();

			if (!(postFixPopped.equals("+") || postFixPopped.equals("-") || postFixPopped.equals("*")
					|| postFixPopped.equals("/"))) {
				accumulator.push(Double.parseDouble(postFixPopped));
			} else {
				double answer = 0.0;
				double pop1 = 0.0;
				double pop2 = 0.0;
				switch (postFixPopped) {
				case "+":
					pop1 = accumulator.pop();
					pop2 = accumulator.pop();
					answer = pop2 + pop1;

					accumulator.push(answer);
					break;

				case "-":
					pop1 = accumulator.pop();
					pop2 = accumulator.pop();
					answer = pop2 - pop1;
					accumulator.push(answer);
					break;

				case "*":
					pop1 = accumulator.pop();
					pop2 = accumulator.pop();
					answer = pop2 * pop1;
					accumulator.push(answer);
					break;

				case "/":
					pop1 = accumulator.pop();
					pop2 = accumulator.pop();
					answer = pop2 / pop1;
					accumulator.push(answer);
					break;
				}
			}
		} // evaluation ends here

		DecimalFormat twoDigit = new DecimalFormat(".#####");

		inputField.setText(twoDigit.format(accumulator.peek()) + "");

		// Resetting all stacks
		beforeInfix = new Stack<String>();
		infix = new Stack<String>();
		numFix = new Stack<String>();
		opFix = new Stack<String>();
		infixString = new StringBuilder("");
		postFix = new Stack<String>();
		accumulator = new Stack<Double>();
	} // end infixToPostFix
}
