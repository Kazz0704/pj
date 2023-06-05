import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicSliderUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class wallet extends JPanel {

	private JPanel walletTitle, Cards, point, payment1, payment2;
	private JLabel wallettitle, pointT, payment1Name, payment1Name_1, paymentT;
	private JButton walletBack, payment1Icon_1, payment1Icon, payment2Icon;
	public static JLabel pointNum_1, pointNum_2, pointNum;
	private boolean favor1 = false, favor2 = false, favor3 = false, favor4 = false;

	public wallet() throws SQLException {
		setBounds(0, 0, main.FRAME_WIDTH, main.FRAME_HEIGHT);
		setBackground(new Color(34, 40, 44));
		setLayout(null);

		walletTitle = new JPanel();
		Cards = new JPanel();
		point = new JPanel();
		payment1 = new JPanel();
		payment2 = new JPanel();

		pointT = new JLabel("Cash");
		pointNum = new JLabel("$0.00");
		payment1Name = new JLabel("( Dog )");
		pointNum_1 = new JLabel("$0.00");
		pointNum_2 = new JLabel("$0.00");
		payment1Name_1 = new JLabel("( Dog )");
		paymentT = new JLabel("Payment");

		wallettitle = new JLabel("Hunger Wallet");

		walletBack = new JButton("");
		payment1Icon_1 = new JButton("");
		payment1Icon = new JButton("");
		payment2Icon = new JButton("");

		home.addJPanel(walletTitle, 0, 0, 1000, 78, this);
		home.addJPanel(Cards, 0, 77, 1000, 468, this);

		addRJPanel(point, 130, 25, 800, 100, Cards);
		addRJPanel(payment1, 130, 180, 350, 160, Cards);
		addRJPanel(payment2, 580, 180, 350, 160, Cards);

		point.setBackground(new Color(62, 62, 77));
		payment1.setBackground(new Color(62, 62, 77));
		payment2.setBackground(new Color(62, 62, 77));

		home.addJLabel(paymentT, 140, 137, 227, 36, "", Cards);
		paymentT.setFont(new Font("Academy Engraved LET", Font.BOLD, 30));
		paymentT.setForeground(new Color(3, 169, 244));
		home.addJLabel(wallettitle, 140, 30, 227, 36, "", walletTitle);
		wallettitle.setFont(new Font("Academy Engraved LET", Font.BOLD, 30));
		wallettitle.setForeground(new Color(3, 169, 244));
		home.addJLabel(pointT, 108, 12, 227, 36, "", point);
		home.addJLabel(pointNum, 46, 52, 227, 36, "", point);
		home.addJLabel(payment1Name, 87, 19, 227, 36, "", payment1);
		home.addJLabel(payment1Name_1, 80, 19, 227, 36, "", payment2);
		home.addJLabel(pointNum_1, 26, 95, 227, 36, "", payment1);
		home.addJLabel(pointNum_2, 23, 89, 227, 36, "", payment2);

		getPoint(pointNum, "point");
		getPoint(pointNum_1, "card1");
		getPoint(pointNum_2, "card2");

		logIn.addJButton(walletBack, 0, 0, 50, 50, "/icons/Left.png", walletTitle);
		home.addJButton(payment1Icon_1, 40, 5, 50, 43, "/icons/MasterCard.png", point);
		home.addJButton(payment1Icon, 26, 12, 50, 43, "/icons/MasterCard.png", payment1);
		home.addJButton(payment2Icon, 23, 12, 50, 43, "/icons/Visa.png", payment2);
		sign.back(walletBack);

		payment1Icon_1.setBackground(new Color(62, 62, 77));
		addPoint(payment1Icon_1, pointNum, "point");
		addPoint(payment1Icon, pointNum_1, "card1");
		addPoint(payment2Icon, pointNum_2, "card2");

	}

	public void addRJPanel(JPanel p, int x, int y, int width, int height, JPanel j) {
		if (p != null) {
			p.setBackground(new Color(51, 255, 153));// 设置半透明白色背景
			p.setOpaque(false); // 设置背景透明
			p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 设置内边距
			p.setUI(new main.RoundJPanelUI()); // 设置圆形形状
			p.setLayout(null);
			p.setForeground(Color.WHITE);
			p.setFont(new Font("Academy Engraved LET", Font.BOLD, 30));
			p.setBounds(x, y, width, height);
			j.add(p);
		}
	}

	public void getPoint(JLabel l, String s) throws SQLException {
		ResultSet rs = main.statement.executeQuery("SELECT " + s + " FROM users");
		if (rs.next()) {
			int card1Value = rs.getInt(s);
			l.setText("$ " + card1Value);
		}
	}

	public void addPoint(JButton b, JLabel l, String s) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "Payment Confirmation";
				String message = "Please enter the amount to be paid:";
				String amount = JOptionPane.showInputDialog(payment1Icon_1, message, title, JOptionPane.PLAIN_MESSAGE);
				if (amount != null && !amount.isEmpty()) {
					int payment = Integer.parseInt(amount);
//					System.out.println("The payment amount is: " + payment);
					// 更新資料庫中的 card1 欄位
					try {
						String query = "UPDATE users SET " + s + " = " + s + " + " + payment;
						int rowsUpdated = main.statement.executeUpdate(query);
						if (rowsUpdated > 0) {
//							System.out.println("Payment processed successfully!");
							JOptionPane.showMessageDialog(null,"Payment processed successfully!","Payment",JOptionPane.INFORMATION_MESSAGE);
							ResultSet rs = main.statement.executeQuery("SELECT " + s + " FROM users");
							if (rs.next()) {
								int card1Value = rs.getInt(s);
								l.setText("$ " + card1Value);
							}
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
	}

}
