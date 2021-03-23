package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/*@author ?*/
public class Calculator extends JFrame {

    JLabel label_history = new JLabel("There's no history yet");
    JTextField res = new JTextField("0");
    double operand1 = 0, operand2 = 0;
    String operator = null;
    String html = "<html><body><div style='text-align:right;padding:8px;font-size:14px;max-widht:100%;'>";
    String history = "";
    boolean calc = false;

    public Calculator() {

        JPanel panel_input = new JPanel(new GridLayout(5, 5, 0, 0));
        JPanel panel_type = new JPanel(new BorderLayout());
        JPanel panel_left = new JPanel(new BorderLayout());
        JPanel panel_leftbottom = new JPanel(new BorderLayout());
        JPanel panel_lefttop = new JPanel(new BorderLayout());
        JPanel panel_M = new JPanel(new GridLayout(1, 5, 0, 0));
        JPanel panel_right = new JPanel(new BorderLayout());
        JPanel panel_history = new JPanel(new GridLayout(1, 2, 0, 0));
        JPanel panel_container = new JPanel(new BorderLayout());

        Color c_dark = new Color(225, 225, 225);
        Color c_light_dark = new Color(240, 240, 240);
        Color c_light = new Color(255, 255, 255);

        JButton input_arr[] = new JButton[25];

        input_arr[0] = new JButton("%");
        input_arr[1] = new JButton("CE");
        input_arr[2] = new JButton("C");
        input_arr[3] = new JButton("←");
        input_arr[4] = new JButton("/");
        input_arr[5] = new JButton("√");
        input_arr[6] = new JButton("7");
        input_arr[7] = new JButton("8");
        input_arr[8] = new JButton("9");
        input_arr[9] = new JButton("x");
        input_arr[10] = new JButton("<htmL>x<sup>2</sup></html>");
        input_arr[11] = new JButton("4");
        input_arr[12] = new JButton("5");
        input_arr[13] = new JButton("6");
        input_arr[14] = new JButton("-");
        input_arr[15] = new JButton("<htmL>x<sup>3</sup></html>");
        input_arr[16] = new JButton("1");
        input_arr[17] = new JButton("2");
        input_arr[18] = new JButton("3");
        input_arr[19] = new JButton("+");
        input_arr[20] = new JButton("1/x");
        input_arr[21] = new JButton("±");
        input_arr[22] = new JButton("0");
        input_arr[23] = new JButton(",");
        input_arr[24] = new JButton("=");

        for (int i = 0; i < input_arr.length; i++) {

            try {
                int num = Integer.parseInt(input_arr[i].getText());
                input_arr[i].setBackground(c_light);
                input_arr[i].setFont(new Font("Arial", 1, 20));
            } catch (NumberFormatException e) {
                input_arr[i].setBackground(c_light_dark);
                input_arr[i].setFont(new Font("Arial", 0, 20));
            }

            input_arr[i].setBorderPainted(false);

            panel_input.add(input_arr[i]);

        }
        JButton menu = new JButton("≡");
        menu.setBackground(c_dark);
        menu.setFont(new Font("Arial", 1, 16));
        menu.setBorderPainted(false);

        JLabel type = new JLabel("Standard");
        type.setBackground(c_dark);
        type.setFont(new Font("Arial", 1, 16));
        type.setOpaque(true);

        panel_type.add(menu, BorderLayout.WEST);
        panel_type.add(type, BorderLayout.CENTER);

        res.setHorizontalAlignment(JTextField.RIGHT);
        res.setFont(new Font("Arial", 1, 32));
        res.setPreferredSize(new Dimension(0, 100));
        res.setEditable(false);

        panel_lefttop.add(panel_type, BorderLayout.NORTH);
        panel_lefttop.add(res, BorderLayout.SOUTH);

        panel_leftbottom.add(panel_M, BorderLayout.NORTH);
        panel_leftbottom.add(panel_input, BorderLayout.CENTER);

        panel_left.add(panel_lefttop, BorderLayout.NORTH);
        panel_left.add(panel_leftbottom, BorderLayout.CENTER);

        JButton btn_history = new JButton("History");
        btn_history.setBackground(c_dark);
        btn_history.setFont(new Font("Arial", 1, 16));
        btn_history.setBorderPainted(false);

        panel_history.add(btn_history);

        label_history.setHorizontalAlignment(JLabel.CENTER);
        label_history.setBackground(c_dark);
        label_history.setOpaque(true);
        JScrollPane scroller = new JScrollPane(label_history, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.getVerticalScrollBar().setUnitIncrement(24);
        panel_right.setPreferredSize(new Dimension(300, 0));

        panel_right.add(panel_history, BorderLayout.NORTH);
        panel_right.add(scroller, BorderLayout.CENTER);

        panel_container.add(panel_left, BorderLayout.CENTER);
        panel_container.add(panel_right, BorderLayout.EAST);

        add(panel_container);

        btnNumberListener event = new btnNumberListener();

        for (int i = 0; i < input_arr.length; i++) {
            input_arr[i].addActionListener(event);
        }

    }

    public static void main(String[] args) {

        Calculator frame = new Calculator();
        frame.setTitle("Calculator");
        frame.setVisible(true);
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    class btnNumberListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            String command = ae.getActionCommand();

            try {
                double num = Double.parseDouble(command);
                if (calc) {
                    res.setText("0");
                    calc = false;
                }
                if (res.getText().equalsIgnoreCase("0") || res.getText().equalsIgnoreCase("infinity")) {
                    res.setText(command);
                } else {
                    res.setText(res.getText() + command);
                }
            } catch (NumberFormatException e) {
                if (command.equalsIgnoreCase("←")) {
                    if (res.getText().length() > 1) {
                        res.setText(res.getText().substring(0, res.getText().length() - 1));
                    } else {
                        res.setText("0");
                    }
                } else if (command.equalsIgnoreCase("C")) {
                    res.setText("0");
                } else if (command.equalsIgnoreCase("CE")) {
                    operand1 = 0;
                    operator = null;
                    res.setText("0");
                } else if (command.equalsIgnoreCase(",")) {
                    if (!res.getText().contains(".")) {
                        res.setText(res.getText() + ".");
                    }
                } else if (command.equalsIgnoreCase("√")) {
                    operand1 = Double.parseDouble(res.getText());
                    double result = Math.sqrt(operand1);
                    res.setText(String.valueOf(result));
                    label_history.setHorizontalAlignment(JLabel.RIGHT);
                    label_history.setVerticalAlignment(JLabel.TOP);
                    String temp = "√(" + operand1 + ")<br>= " + result + "<br><br>";
                    history = temp + history;
                    label_history.setText(html + history);
                    operand1 = 0;
                    calc = true;
                } else if (command.equalsIgnoreCase("<htmL>x<sup>2</sup></html>")) {
                    operand1 = Double.parseDouble(res.getText());
                    double result = Math.pow(operand1, 2);
                    res.setText(String.valueOf(result));
                    label_history.setHorizontalAlignment(JLabel.RIGHT);
                    label_history.setVerticalAlignment(JLabel.TOP);
                    String temp = operand1 + "<sup>2</sup><br>= " + result + "<br><br>";
                    history = temp + history;
                    label_history.setText(html + history);
                    operand1 = 0;
                    calc = true;
                } else if (command.equalsIgnoreCase("<htmL>x<sup>3</sup></html>")) {
                    operand1 = Double.parseDouble(res.getText());
                    double result = Math.pow(operand1, 3);
                    res.setText(String.valueOf(result));
                    label_history.setHorizontalAlignment(JLabel.RIGHT);
                    label_history.setVerticalAlignment(JLabel.TOP);
                    String temp = operand1 + "<sup>3</sup><br>= " + result + "<br><br>";
                    history = temp + history;
                    label_history.setText(html + history);
                    operand1 = 0;
                    calc = true;
                } else if (command.equalsIgnoreCase("1/x")) {
                    operand1 = Double.parseDouble(res.getText());
                    double result = 1 / operand1;
                    res.setText(String.valueOf(result));
                    label_history.setHorizontalAlignment(JLabel.RIGHT);
                    label_history.setVerticalAlignment(JLabel.TOP);
                    String temp = "1 / " + operand1 + "<br>= " + result + "<br><br>";
                    history = temp + history;
                    label_history.setText(html + history);
                    operand1 = 0;
                    calc = true;
                } else if (command.equalsIgnoreCase("%")) {
                    operand1 = Double.parseDouble(res.getText());
                    double result = (operand1 * 1) / 100;
                    res.setText(String.valueOf(result));
                    label_history.setHorizontalAlignment(JLabel.RIGHT);
                    label_history.setVerticalAlignment(JLabel.TOP);
                    String temp = operand1 + " %<br>= " + result + "<br><br>";
                    history = temp + history;
                    label_history.setText(html + history);
                    operand1 = 0;
                    calc = true;
                } else if (command.equalsIgnoreCase("±")) {
                    System.out.println(res.getText().substring(0, 1));
                    if (res.getText().substring(0, 1).equalsIgnoreCase("-")) {
                        res.setText(res.getText().substring(1, res.getText().length()));
                    } else {
                        res.setText("-" + res.getText());
                    }
                } else if (command.equalsIgnoreCase("=")) {
                    if (operand1 != 0) {
                        double operand2 = Double.parseDouble(res.getText());
                        double result = 0;
                        String op = "";
                        switch (operator) {
                            case "+":
                                result = operand1 + operand2;
                                op = " + ";
                                break;
                            case "-":
                                result = operand1 - operand2;
                                op = " - ";
                                break;
                            case "/":
                                result = operand1 / operand2;
                                op = " / ";
                                break;
                            case "x":
                                result = operand1 * operand2;
                                op = " x ";
                                break;
                            default:
                                break;
                        }
                        res.setText(String.valueOf(result));
                        label_history.setHorizontalAlignment(JLabel.RIGHT);
                        label_history.setVerticalAlignment(JLabel.TOP);
                        String temp = operand1 + "<br>" + op + operand2 + "<br>= " + result + "<br><br>";
                        history = temp + history;
                        label_history.setText(html + history);
                        operand1 = 0;
                        operator = null;
                        calc = true;
                    }
                } else {
                    if (operand1 == 0) {
                        operand1 = Double.parseDouble(res.getText());
                        res.setText("0");
                        operator = command;
                    }
                }
            }

        }

    }

}
