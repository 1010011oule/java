package assignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
//import java.event.*;

class Calculator_20211611 extends Frame implements WindowListener, ActionListener{
	public JButton[] button;
	public Label up, down;
	int ax = 0;
	String prev = "";
	String n = "";
	
	ArrayList<String> equation = new ArrayList<String>();
	
	public Calculator_20211611() {
		super("Calc");
		
		Panel window = new Panel();
		Panel w1 = new Panel();
		Panel w2 = new Panel();
		Panel w3 = new Panel();
		Panel w4 = new Panel();
		Panel w5 = new Panel();
		Panel w6 = new Panel();
		Panel w7 = new Panel();
		
		String[] buttons = {"!", "(", ")","%","AC","ln","7","8","9","/","log","4","5","6","×","√","1","2","3","-","^","0",".","=","+"};
		
		button = new JButton[25];
		
		int i = 0;
		int j = 0;
		while(i != 25) {
			button[i] = new JButton(buttons[i]);
			button[i].setBackground(Color.BLUE);
			button[i].setOpaque(true);
			i++;
		}
		
		
		up = new Label("", Label.RIGHT);
		up.setBackground(new Color(255,255,255));
		down = new Label("", Label.RIGHT);
		down.setBackground(new Color(255, 255, 255));
		
		 up.setFont(new Font("Serif", Font.BOLD, 30)); // Font 지정
		 up.setBackground(Color.WHITE); // 결과창 배경색 지정
		 //up.setOpaque(true); // 배경색을 적용하기 위해 불투명 설정
		
		 down.setFont(new Font("Serif", Font.BOLD, 30)); // Font 지정
		 down.setBackground(Color.WHITE); // 결과창 배경색 지정
		 
		
		window.setLayout(new GridLayout(7,1,5,5));	
		w1.setLayout(new GridLayout(1,1,5,5));
		w1.add(up);
		w2.setLayout(new GridLayout(1,1,5,5));
		w2.add(down);
		
		w3.setLayout(new GridLayout(1, 5, 5 ,5));
		for(j = 0;j<5;j++) {
			w3.add(button[j]);
		}
		
		
		w4.setLayout(new GridLayout(1, 5, 5 ,5));
		for(j = 5;j<10;j++) {
			w4.add(button[j]);
		}
		
		w5.setLayout(new GridLayout(1, 5, 5 ,5));
		for(j = 10;j<15;j++) {
			w5.add(button[j]);
		}
		w6.setLayout(new GridLayout(1, 5, 5 ,5));
		for(j = 15;j<20;j++) {
			w6.add(button[j]);
		}
		w7.setLayout(new GridLayout(1, 5, 5 ,5));
		for(j = 20;j<25;j++) {
			w7.add(button[j]);
		}
		
		window.add(w1);
		window.add(w2);
		window.add(w3);
		window.add(w4);
		window.add(w5);
		window.add(w6);
		window.add(w7);
		///otsud a
		
		/*
		Button operator[]=new Button[5];
		operator[0]=new Button("/");
		operator[1]=new Button("*");
		operator[2]=new Button("-");
		operator[3]=new Button("+");
		operator[4]=new Button("=");
		operator[5]=new Button("ln");
		operator[6]=new Button("log");
		operator[7]=new Button("x!");
		operator[8]=new Button("AC");
		operator[9]=new Button(".");
		operator[10]=new Button("sqrt");
		operator[11]=new Button("(");
		operator[12]=new Button(")");
		for(int i = 0;i<12;i++);
		///dosudaa
		*/
		

		
		add("Center", window);
		
		
		setBounds(1000, 300, 500, 500);
		setBackground(new Color(255, 250, 250));
		setVisible(true);
		
		for(i = 0; i<25; i++) {
			button[i].addActionListener(new actionlistener());
		}
		this.addWindowListener(this);
			
	}
	
	class actionlistener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		
			String operation = e.getActionCommand(); // 버튼 누르기

		
			if(operation.equals("AC")) { //AC가 입력될 경우, 초기화 
				down.setText("");
				up.setText("");
			}
			else if(operation.equals("=")) {
				//결과 값 계산해서 출력하기
				String result = Double.toString(calculate(up.getText()));
				down.setText("" + result);
				n = "";
			}
			else if(operation.equals("+") || operation.equals("-") || operation.equals("/") || operation.equals("×")) {
				//연산자가 입력됬을 경우
				if(up.getText().equals("") && operation.equals("-")) {
					up.setText(up.getText() + e.getActionCommand());
				}
				else if(!up.getText().equals("") && !prev.equals("+") && !prev.equals("-") && !prev.equals("×") && !prev.equals("/") && !prev.equals("^")){
					up.setText(up.getText() + e.getActionCommand());
				}
			
			}
			else {
				up.setText(up.getText() + e.getActionCommand());
			}
			prev = e.getActionCommand();
		}
	}
	
	public void Parsing(String input) {
		equation.clear();
		//System.out.println(operation);
		for(int i = 0; i < input.length(); i++) {
			char k = input.charAt(i);
			
			
			if(k == '-' || k == '+' || k == '×' || k == '/' || k == '^' || k == '!' || k == '%' || k == '√') {
				//만약 연산 기호를 만났다면, 앞은 숫자이므로, 이 숫자를 arraylist에 추가해준다
				equation.add(n);
				//System.out.println(n);
				n = "";
				//연산자를 추가
				equation.add(k + "");
				//System.out.println(equation);
			}
			else if(k == 'l' || k == 'o' || k == 'n' || k == 'g'){
				//equation.add(n);
				n = "";
				//equation.add(operation + "");
				if(k == 'n') {
					//i = i + 1;
					equation.add("ln" + "");
					//System.out.println(equation);
				}
				else if(k == 'o'){
					i = i + 1;
					equation.add("log" + "");
				}
				ax++;
			}
			else
				n = n + k;
		}
		//System.out.println(n);
		equation.add(n);
		//System.out.println(equation);
		equation.remove("");
		//ax = 0;
		System.out.println(equation);
	}
	
	public double calculate(String input) {
		Parsing(input);
		
		
		double pre = 0;
		double cur = 0;
		
		String mode = "";
		
		
		//연산자 우선순위 
		for(int i = 0 ; i < equation.size(); i++) {
			String s = equation.get(i); //i번째 위치에 있는 객체를 리턴
			//System.out.println(equation.size());
			if(s.equals("+")) {
				mode = "+";
			}
			else if(s.equals("-")) {
				mode = "-";
			}
			else if(s.equals("×")) {
				mode = "×";
			}
			else if(s.equals("/")) {
				mode  = "/";
			}
			else if(s.equals("^")) {
				mode = "^";
			}
			else if(s.equals("log")) {
				mode = "log";
			}
			else if(s.equals("ln")) {
				mode = "ln";
			}
			else if(s.equals("√")) {
				mode = "√";
			}
			else if(s.equals("!") || s.equals("%")){
				Double temp = Double.parseDouble(equation.get(i-1));
				
				Double kkk = 1.0;
				
				if(s.equals("!")) {
					for(double q = temp; q > 0; q--) {
						kkk = kkk * q;
						//System.out.println(q);
					}
				}
				else if(s.equals("%")) {
					kkk = temp * 0.01;
				}
				
				equation.add(i + 1, Double.toString(kkk));
				
				for(int p = 0; p < 2; p++) {
					equation.remove(i - 1);
				}
				//System.out.println(equation.size());
				i -= 1;
				//System.out.println(equation);
			}
			else if(mode == "log" || mode == "ln" || mode== "√") {
				Double tmp = Double.parseDouble(equation.get(i));
				Double sss = 0.0;
				
				if(mode == "log") {
					sss = Math.log10(tmp);
				}
				else if(mode == "ln") {
					sss = Math.log(tmp);
				}
				else if(mode == "√") {
					sss = Math.sqrt(tmp);
				}
				equation.add(i + 1, Double.toString(sss));
				
				for(int p = 0; p < 2; p++) {
					equation.remove(i - 1);
				}
				//System.out.println(equation.size());
				i -= 1;
				//System.out.println(equation);
			}
			else { //전의 연산자가 곱셈 혹은 나눗셈이면서, 현재의 인덱스가 숫자인 경우
				if ((mode.equals("×") || mode.equals("/")) || mode.equals("^") && !s.equals("+") && !s.equals("-") && !s.equals("×") && !s.equals("÷")) {
					Double first = Double.parseDouble(equation.get(i-2));
					Double second = Double.parseDouble(equation.get(i));
	
					Double last = 0.0;
					
					if(mode == "×") {
						last = first * second;
					}
					else if(mode == "/") {
						last = first / second;
					}
					else if(mode == "^") {
						last = Math.pow(first, second);
					}
					//결과 값을 equation에 추가!!
					equation.add(i + 1, Double.toString(last));
					for (int j = 0; j < 3; j++) {
						equation.remove(i - 2);
					}
					//예를 들어 3 + 5 x 6에서  3 + 30이 되었으므로 인덱스를 2만큼 되돌아감
					i -= 2;	// 결과값이 생긴 인덱스로 이동
				}
				
			}
		}
		
		mode = "";
		//이제 덧셈, 뺄셈만 수행하면 됨!!
		for(String s : equation) {
			if(s.equals("+")) {
				mode = "+";
			}
			else if(s.equals("-")) {
				mode = "-";
			}
			else {
				cur = Double.parseDouble(s);
				
				if (mode.equals("+")) {
					pre += cur;
				} else if (mode.equals("-")) {
					pre = pre - (cur);
					//System.out.println(pre);
					//System.out.println(cur);
				} else {
					pre = cur;
				}
			}
		}
		
		return pre;
	}
					

	
	
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {System.exit(0);}
	public void WindowDeactivated(WindowEvent e) {}
	public void WindowOpened(WindowEvent e) {}
	public void Deiconfied(WindowEvent e) {}
	public void windowIconfied(WindowEvent e) {}
	
	
	
	
		public static void main(String[] args) {
			new Calculator_s0ul();
		}
	}