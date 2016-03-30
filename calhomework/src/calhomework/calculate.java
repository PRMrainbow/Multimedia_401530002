package calhomework;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class calculate {
	private static JFrame frm = new JFrame("多功能小算盤");
	private static Panel pn1 = new Panel(new GridLayout(4, 3));
	private static Panel pn2 = new Panel(new GridLayout(4, 1));
	private static Panel pn3 = new Panel(new GridLayout(1, 2));
	private static Panel pn4 = new Panel(new GridLayout(2, 1));
	private static Panel pn5 = new Panel(new GridLayout(2, 1));
	private static Label lab0 = new Label("", Label.RIGHT);
	private static Label lab = new Label("0", Label.RIGHT);
	private static Label lab1 = new Label("", Label.RIGHT);
	private static Label lab2 = new Label ("", Label.RIGHT);
	private static Label lab3 = new Label ("", Label.LEFT);
	private static Label lab4 = new Label ("", Label.LEFT);
	private static Label history = new Label("", Label.RIGHT);
	// 依序為清除、加、減、乘、除、等於、暫存、紀錄、上筆、下筆、刪除、退後
	private static Button cn, ad, sub, mul, div, amo, save, record, up, down, del, back, up2, down2;
	// 顯示於lab0
	private static String calculate = "";
	//為了:算完後馬上顯示答案，輸入新數字前歸零
	private static boolean calculated = true;
	private static Button digits[] = new Button[10];
	private static String rec[] = new String[10]; 
	private static String temp[] = new String[5]; ;
	//目前筆數
	private static int recnum = 0;
	private static int tempnum = 0;
	//顯示第幾筆
	private static int show, show1;
	//顯示於面板上的數字
	private static long number;
	//(+-*/)數與被(+-*/)數
	private static long num1, num2;
	// 代表運算子
	private static byte op;

	public static void main(String args[]) {
		
		frm.setBackground(new Color(131,186,132));
        frm.setLayout(null);
        frm.setBounds(450, 250, 305, 530);
        frm.setResizable(false);
        lab.setBounds(0, 0, 300, 105);//現有結果
        lab.setBackground(new Color(51, 181, 229));
        lab.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
        lab0.setBackground(new Color(51, 181, 229)); 
        lab0.setBounds(100, 70, 195, 35); //公式
        lab0.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        lab0.setForeground(Color.white);
        lab1.setBounds(0, 40, 90, 65); //暫存
        lab1.setBackground(new Color(120, 205, 237));
        lab1.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        //lab1.setBorder(BorderFactory.createLineBorder(Color.white));
        pn3.setBounds(0, 105, 225, 50);//暫存&紀錄
        pn1.setBounds(0, 155, 225, 300);//數字
        pn2.setBounds(225, 105, 75, 350);//+_*/
        history.setBounds(300, 10, 300, 500);
        history.setBackground(new Color(253, 245, 230));
        pn4.setBounds(0, 40, 15, 65);//上下
        pn5.setBounds(285, 455, 15, 46);//上下
        lab2.setBounds(50, 40, 40, 20);
        lab2.setBackground(new Color(253, 245, 230));
        lab3.setBounds(50, 455, 250, 50);
        lab3.setBackground(new Color(253, 245, 230));
        lab4.setBounds(0, 455, 350, 50);
        lab4.setBackground(new Color(253, 245, 230));
                
                             
                // 設置0~9數字鈕
                for (int i = 9; i > 0; i = i - 3) {
                        for (int k = 2; k >= 0; k--) {
                                digits[i - k] = new Button(Integer.toString(i - k));
                                pn1.add(digits[i - k]);
                                digits[i - k].addActionListener(new ActLis());
                                digits[i - k].setBackground(new Color(120, 205, 237));
                        }
                }
                
        //設置0
        digits[0] = new Button(Integer.toString(0));
        pn1.add(digits[0]);
        digits[0].addActionListener(new ActLis());
        digits[0].setBackground(new Color(120, 205, 237));
        
                // 設置清除鈕
                cn = new Button("Ｃ");
                pn1.add(cn);
                cn.addActionListener(new ActLis());
                cn.setBackground(new Color(51, 181, 229));
             // 設置等於鈕
                amo = new Button("＝");
                pn1.add(amo);
                amo.addActionListener(new ActLis());
                amo.setBackground(new Color(51, 181, 229));
                // 設置加鈕
                ad = new Button("＋");
                pn2.add(ad);
                ad.addActionListener(new ActLis());
                ad.setBackground(new Color(51, 181, 229));
                // 設置減鈕
                sub = new Button("－");
                pn2.add(sub);
                sub.addActionListener(new ActLis());
                sub.setBackground(new Color(51, 181, 229));
                // 設置乘鈕
                mul = new Button("＊");
                pn2.add(mul);
                mul.addActionListener(new ActLis());
                mul.setBackground(new Color(51, 181, 229));
                // 設置除鈕
                div = new Button("／");
                pn2.add(div);
                div.addActionListener(new ActLis());
                div.setBackground(new Color(51, 181, 229));
                //設置暫存鈕
                save = new Button("暫存");
                pn3.add(save);
                save.addActionListener(new ActLis());
                save.setBackground(new Color(51, 181, 229));

                //設置紀錄鈕
                //record = new Button("紀錄");
                //pn3.add(record);
                //record.addActionListener(new ActLis());
                //record.setBackground(new Color(51, 181, 229));
                //設置刪除鈕
                del = new Button("刪除");
                pn3.add(del);
                del.addActionListener(new ActLis());
                del.setBackground(new Color(51, 181, 229));
                //設置退後紐
                back = new Button("←");
                pn3.add(back);
                back.addActionListener(new ActLis());
                back.setBackground(new Color(51, 181, 229));
                //設置上筆鈕
                up = new Button("↑");
                pn4.add(up);
                up.addActionListener(new ActLis());
                //up.setBackground(new Color(51, 181, 229));
                //設置下筆鈕
                down = new Button("↓");
                pn4.add(down);
                down.addActionListener(new ActLis());
                //down.setBackground(new Color(51, 181, 229));

                //設置上筆鈕2
                up2 = new Button("↑");
                pn5.add(up2);
                up2.addActionListener(new ActLis());
                //up2.setBackground(new Color(51, 181, 229));
                //設置下筆鈕2
                down2 = new Button("↓");
                pn5.add(down2);
                down2.addActionListener(new ActLis());
                //down2.setBackground(new Color(51, 181, 229));
                
                
                
                frm.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                                System.exit(0);
                        }
                });
                frm.add(pn4);
                frm.add(lab2);
                frm.add(pn5);
                frm.add(lab0);
                frm.add(lab1);          
                frm.add(lab3);
                frm.add(pn1);
                frm.add(pn2);
                frm.add(pn3);
                frm.add(lab);
                frm.add(lab4);
                
                frm.setVisible(true);
	}

	public static class ActLis implements ActionListener {
		
		public void actionPerformed(ActionEvent e)
				throws NumberFormatException, ArithmeticException {

			Button btn = (Button) e.getSource();
			try {
				//暫存
				if(btn == save)
				{
					temp[tempnum%5] = lab.getText().toString();
					show = tempnum%5;
					lab1.setText(temp[show]);
					lab2.setText("暫存"+(show+1));
					tempnum++;
				}
				//上筆
				if(btn == up)
				{
					if(show > 0)
					{
						show--;
						lab1.setText(temp[show]);
						lab2.setText("暫存"+(show+1));
					}
				}
				//上筆2
				if(btn == up2)
				{
					if(show1 > 0)
					{
						show1--;
						lab3.setText(rec[show1]);
						lab4.setText("紀錄"+(show1+1));
					}
				}
				//下筆
				if(btn == down)
				{
					if(show < 4)
					{
						show++;
						lab1.setText(temp[show]);
						lab2.setText("暫存"+(show+1));
					}

				}
				//下筆2
				if(btn == down2)
				{
					if(show < 9)
					{
						show1++;
						lab3.setText(rec[show1]);
						lab4.setText("紀錄"+(show1+1));
					}
				}
				//刪除
				if(btn == del)
				{
					for (int i = show; i < 4; i++)
					{
						temp[i] = temp[i+1];
					}
					lab1.setText(temp[show]);
					tempnum--;
				}
				// ←
				if(btn == back)
				{
					number = number/10;
					lab.setText(number+"");
					if ( op == 0)				//只有第一組數字會是num1
						num1 = number;
					else						//其他的都是num2
						num2 = number;
				}
				// 處理數值1-9
				for (int i = 0; i <= 9; i++) {
					if (btn == digits[i]) {
						if (calculated == true)		//當輸入的是數字中的第一個時
						{
							number = 0;
							lab.setText(number+"");
							calculated = false;
						}
						number = 10 * number + i;
						lab.setText(number+""); //把原本的數字+新增的數字顯示於lab
						if ( op == 0)				//只有第一組數字會是num1
							num1 = number;
						else						//其他的都是num2
							num2 = number;
						break;
					}
				}
				if (btn == cn) {
										// 歸0
					calculate = "";
					lab0.setText(calculate);
					num1 = 0L;
					num2 = 0L;
					op = 0;
					lab.setText(Long.toString(0L));
				} else if (btn == ad) {// 加
					if (number != 0)
						calculate += number;
					calculate += "+";
					lab0.setText(calculate);
					calculated = true;
					if (op != 0)
						calculate();
					op = 1;
				} else if (btn == sub) {// 減
					if (number != 0)
						calculate += number; 
					calculate += "-";
					lab0.setText(calculate);
					calculated = true;
					if (op != 0)
						calculate();
					op = 2;
				} else if (btn == mul) {// 乘
					if (number != 0)
						calculate += number; 
					calculate += "*";
					lab0.setText(calculate);
					calculated = true;
					if (op != 0)
						calculate();
					op = 3;
				} else if (btn == div) {// 除
					if (number != 0)
						calculate += number; 
					calculate += "/";
					lab0.setText(calculate);
					calculated = true;
					if (op != 0)
						calculate();
					op = 4;
				} else if (btn == amo) {   //算答案
					calculate();
					calculate += number; 
					calculate += "=";
					calculate += num1;
					lab0.setText(calculate);
					lab.setText(Long.toString(num1));
					op = 0;
					rec[recnum%10] = calculate;
					recnum++;
					calculate = (Long.toString(num1));
					number = 0;
					
					show1 = recnum%10-1;
					lab3.setText(rec[show1]);
					lab4.setText("紀錄"+(show1+1));
				}
			} catch (NumberFormatException ne) {
				// 捕捉例外
			} catch (ArithmeticException ae) {
				// 捕捉被除數是零的例外
			}
		}


		// 計算num1(+-*/)num2
		private void calculate() {
			switch (op)
			{
				case 1:
					num1 = num1 + num2 ;
					break;
				case 2:
					num1 = num1 - num2 ;
					break;
				case 3:
					num1 = num1 * num2 ;
					break;
				case 4:
					num1 = num1 / num2 ;
					break;
			}
			lab.setText(Long.toString(num1));
		}
	}
}