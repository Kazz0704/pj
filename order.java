import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class order extends JPanel {

	private JButton cart_1, dish1Img_1, orderPlaceOrder, orderBackHome;
	private JLabel ordeSummary, orderT, orderPickUp, orderPayment, orderImg;
	private JTextPane dish1Info_1;
	private JTextField orderPickUpLocation;
	private JComboBox<String> orderPaymentBox, orderPickUpBox;
	private int card1Value, card2Value, pointsValue;
	public static JTextPane orderSummaryText;
	public static int totalPrice;
	public static String timeStamp;

	public order() throws SQLException {
		setPreferredSize(new Dimension(main.FRAME_WIDTH, main.FRAME_HEIGHT+80));
		setBackground(new Color(34, 40, 44));
		setLayout(null);

		orderBackHome = new JButton("");
		orderBackHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.cardLayout.show(main.root, "main");
			}
		});

		orderPickUpLocation = new JTextField();
		JScrollPane scrollPane = new JScrollPane(orderPickUpLocation);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(46, 427, 296, 40);
		this.add(scrollPane);

		ordeSummary = new JLabel("Order Summary");
		orderT = new JLabel("Delivery Details");
		orderPickUp = new JLabel("Pick up method");
		orderPayment = new JLabel("Payment Settings");
		orderImg = new JLabel("");

		orderSummaryText = new JTextPane();

		orderPaymentBox = new JComboBox<>();
//		ResultSet rs = main.statement.executeQuery("SELECT * FROM users");
//		ResultSetMetaData rsmd = rs.getMetaData();
//		int columnCount = rsmd.getColumnCount();
//
//		for (int i = 5; i <= columnCount; i++) {
//			orderPaymentBox.addItem(rsmd.getColumnName(i));
//		}
		orderPaymentBox.addItem("point");
		orderPaymentBox.addItem("card1");
		orderPaymentBox.addItem("card2");
		orderPaymentBox.addItem("cash");
		orderPaymentBox.setBounds(259, 503, 120, 24);
		this.add(orderPaymentBox);

		orderPickUpBox = new JComboBox<>();
		orderPickUpBox.addItem("Delivery");
		orderPickUpBox.addItem("Pickup");
		orderPickUpBox.setBounds(259, 391, 120, 24);

		// 监听orderPickUpBox的选项变化
		orderPickUpBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// 如果选中了Pickup选项，则禁止在orderPickUpLocation文本框中输入地址，并显示指定餐厅的领取信息
				String PICKUP_MESSAGE = "Thank you for choosing our platform. We kindly request that you visit the restaurant to collect your order.";
				if (e.getItem().equals("Pickup")) {
					orderPickUpLocation.setEditable(false);
					orderPickUpLocation.setText(PICKUP_MESSAGE);
				} else {
					orderPickUpLocation.setEditable(true);
					orderPickUpLocation.setText("");
				}
			}
		});

		this.add(orderPickUpBox);

		orderPlaceOrder = new JButton("Place Order");

		home.addJLabel(ordeSummary, 48, 110, 413, 76, "", this);
		ordeSummary.setFont(new Font("Academy Engraved LET", Font.BOLD, 22));
		home.addJLabel(orderT, 35, 40, 413, 76, "", this);
		orderT.setFont(new Font("Academy Engraved LET", Font.BOLD, 30));
		orderT.setForeground(new Color(3, 169, 244));
		home.addJLabel(orderPickUp, 48, 367, 413, 76, "", this);
		orderPickUp.setFont(new Font("Academy Engraved LET", Font.BOLD, 22));
		home.addJLabel(orderPayment, 48, 479, 413, 76, "", this);
		orderPayment.setFont(new Font("Academy Engraved LET", Font.BOLD, 22));
		home.addJLabel(orderImg, 468, 0, 532, main.FRAME_HEIGHT+80, "/RImg/color.png", this);

		home.addJButton(orderPlaceOrder, 48, 567, 284, 70, "", this);
		home.addJButton(orderBackHome, 0, 0, 50, 50, "/icons/Left.png", this);
		restaurant1.addJTextPane(orderSummaryText, 48, 173, 271, 174, this);
		JScrollPane scrollPane2 = new JScrollPane(orderSummaryText);
		scrollPane2.setBounds(48, 173, 271, 174);
		this.add(scrollPane2);

		placeOrder();

	}

	public void placeOrder() {
		orderPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String paymentType = (String) orderPaymentBox.getSelectedItem();
				try {

					ResultSet rs = main.statement.executeQuery("SELECT card1 FROM users");
					if (rs.next()) {
						card1Value = rs.getInt("card1");
					}
					rs = main.statement.executeQuery("SELECT card2 FROM users");
					if (rs.next()) {
						card2Value = rs.getInt("card2");
					}
					rs = main.statement.executeQuery("SELECT point FROM users");
					if (rs.next()) {
						pointsValue = rs.getInt("point");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				if (paymentType.equals("card1")) {
					if (card1Value >= totalPrice) {
						// 扣除信用卡餘額
						card1Value -= totalPrice;
						// 更新資料庫中的信用卡餘額
						try {
							String query = "UPDATE users SET card1 = " + card1Value;
							main.statement.executeUpdate(query);
							String message = "Payment successful! Your point balance is " + card1Value + ".";
							JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);

							main.cardLayout.show(main.root, "main");
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						wallet.pointNum_1.setText("$ " + card1Value);
					} else {
						JOptionPane.showMessageDialog(null, "Insufficient balance. Please top up your card.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (paymentType.equals("point")) {
					if (pointsValue >= totalPrice) {
						// 扣除點數
						pointsValue -= totalPrice;
						// 更新資料庫中的點數餘額
						try {

							String query = "UPDATE users SET point = " + pointsValue;
							main.statement.executeUpdate(query);
							String message = "Payment successful! Your point balance is " + pointsValue + ".";
							JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);

							main.cardLayout.show(main.root, "main");
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						wallet.pointNum.setText("$ " + pointsValue);
					} else {
						JOptionPane.showMessageDialog(null, "Insufficient points. Please top up your points.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (paymentType.equals("card2")) {
					if (card2Value >= totalPrice) {
						// 扣除點數
						card2Value -= totalPrice;
						// 更新資料庫中的點數餘額
						try {
							String query = "UPDATE users SET card2 = " + card2Value;
							main.statement.executeUpdate(query);
							String message = "Payment successful! Your point balance is " + card2Value + ".";
							JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);

							main.cardLayout.show(main.root, "main");
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						wallet.pointNum_2.setText("$ " + card2Value);
					} else {
						JOptionPane.showMessageDialog(null, "Insufficient points. Please top up your points.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (paymentType.equals("Cash")) {
					JOptionPane.showMessageDialog(null, "Payment successful!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
				String query = String.format("UPDATE history SET location = '%s', method = '%s' WHERE time = '%s'", orderPickUpLocation.getText(), (String) orderPickUpBox.getSelectedItem(), timeStamp);
				try {
					boolean rowsUpdated = main.statement.execute(query);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
	}
}
