package compro2.ex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CalculatorFrame extends JFrame implements ActionListener {
    private JTextField outputText = new JTextField(20);
    private JPanel p1, p2, p3, p_final;
    private String[] labels = {
            "7", "8", "9", "/",
            "4", "5", "6", "x",
            "1", "2", "3", "-",
            "0", "back", "=", "+"
    };

    private int state = 0; // 0: 아무것도 없는 상태, 1: 좌측 operand 입력 시작, 2: 숫자와 operator 가 들어온 상태
    private JLabel alarm;

    private int operand1, operand2;
    private String operator;

    public CalculatorFrame() {
        setTitle("My Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p2 = new JPanel();
        p2.setLayout(new GridLayout(0, 4, 5, 5));
        p2.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        p3 = new JPanel();
        p3.setLayout(new GridLayout(4, 0, 5, 5));
        p3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        p_final = new JPanel();
        p_final.setLayout(new BorderLayout());

        alarm = new JLabel();
        alarm.setText("Welcome!");
        p1.add(alarm, "North");
        p1.add(outputText);
        JButton b_c = new JButton("C");
        b_c.addActionListener(this);
        p2.add(b_c);
        for (String s : labels){
            JButton b = new JButton(s);
            b.addActionListener(this);
            p3.add(b);
        }

        p_final.add(p1, BorderLayout.NORTH);
        p_final.add(p2, BorderLayout.CENTER);
        p_final.add(p3, BorderLayout.SOUTH);
        add(p_final);
        pack();
        setVisible(true);
    }

    public static boolean isNumeric(String str) {
        if (str.length() != 1) {
            return false;
        }
        char c = str.charAt(0);
        return Character.isDigit(c);
    }

    public void clear(){
        alarm.setText("Cleared!");
        // TODO: 초기화 함수 구현
        outputText.setText("");
        operand1 = 0;
        operand2 = 0;
        operator = "";
        state = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton srcButton = (JButton) e.getSource();
        String btnText = srcButton.getText();

        alarm.setText("처리 중...");
        System.out.println("state: "+state+" operand: "+ operand1);
        if(btnText == "C"){
            clear();
            state = 0;
            return;
        }

        if(state==0){
            if(!isNumeric(btnText)){
                // 아무 것도 없을 때 숫자가 아닌 다른 걸 입력하면 경고 출력
                alarm.setText("숫자 입력이 필요합니다");
                return;
            }
            else{
                // 숫자 하나를 입력하는 경우 숫자 입력을 계속 받는 상태인 state 1로 이동
                operand1 = Integer.parseInt(btnText);
                state = 1;
                outputText.setText(outputText.getText() + btnText);
                return;
            }
        }

        else if(state==1){
            if(btnText=="="){
                // 숫자가 하나 있는 상태에서 = 을 입력하면 아무일도 하지 않음
                return;
            }
            else if(btnText=="back"){
                // back 버튼을 누르면 operand1을 10으로 나누어 자리수를 하나씩 지우고, 하나남은 숫자를 지운경우 글자를 모두 지우고 state 0으로 변경
                operand1 = operand1 / 10;
                if (outputText.getText().length() > 1) {
                    outputText.setText(outputText.getText().substring(0, outputText.getText().length() - 1));
                }else{
                    outputText.setText("");
                    operand1 = 0;
                    state = 0;
                }
            }
            else if(!isNumeric(btnText)){
                // 숫자가 아닌 operator (+, -, /, x) 가 입력되면 operator를 저장하고 state 2로 변경
                operator = btnText;
                state = 2;
                outputText.setText(outputText.getText() + btnText);
                return;
            }
            else{
                int btnNumber = Integer.parseInt(btnText);
                if(operand1 == 0){
                    operand1 = btnNumber;
                    outputText.setText(btnText);
                }else {
                    // TODO: 숫자가 입력되는 경우 operand1 을 10 곱하여서 현재 숫자를 더해주고 text를 바꾸어 줌
                }
            }
        }

        else if(state==2){
            if(btnText=="back"){
                // back 버튼을 누르면 operand2를 10으로 나누어 자리수를 하나씩 지우고,
                // operand2가 없는 상태에서 지운 경우 operator를 지우고 state 1으로 변경
                if(operand2==0 && !Character.isDigit(outputText.getText().charAt(outputText.getText().length()-1))){
                    operator = "";
                    state = 1;
                }

                operand2 = operand2 / 10;
                outputText.setText(outputText.getText().substring(0, outputText.getText().length() - 1));
            }
            else if(!isNumeric(btnText) && !Character.isDigit(outputText.getText().charAt(outputText.getText().length()-1))){
                alarm.setText("숫자 입력이 필요합니다");
            }else if (!isNumeric(btnText)){
                int result = 0;

                // TODO 숫자 두개가 있을 때, 추가적인 연산자가 들어오는 경우 숫자 두개와 연산자를 이용해 계산 수행
                // TODO 0으로 나누는 명령이 들어온 경우 별도로 처리해 주어야 함
                if(operator == "+"){
                    //result = ?
                }

                // 계산 결과는 operand1에 할당
                operand1 = result;
                operand2 = 0;

                // 연산자가 입력된 경우, 앞의 계산에 연산자가 추가로 입력되었다 가정 후 state 2로 이동
                if(btnText=="-" || btnText=="+" || btnText=="/" || btnText=="x"){
                    state = 2;
                    operator = btnText;
                    outputText.setText(result+btnText);
                }else{ // btnText: "="
                    // = 이 입력된 경우, 계산 결과만 출력 후 숫자 추가 입력 혹은 operator 입력을 위한 state1로 이동
                    state = 1;
                    outputText.setText(""+result);
                }
            }
            else{
                int btnNumber = Integer.parseInt(btnText);
                operand2 = operand2 * 10 + btnNumber;
                outputText.setText(outputText.getText() + btnText);
            }
        }
    }
}

public class MyCalculator {
    public static void main(String[] args) {
        new CalculatorFrame();
    }
}
