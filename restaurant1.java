import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class restaurant1 extends JPanel {

	private JButton btnNewButton, cart_1;
	private JPanel dish1;
	private JLabel restaurant1T, restaurant1Bar;
	private JTextPane dish1Info, dish2Info;
	public static JButton dish1Img, dish2Img;

	public restaurant1() {
		setBackground(new Color(34, 40, 44));
		setLayout(null);

		btnNewButton = new JButton("");
		cart_1 = new JButton("");
		restaurant1Bar = new JLabel("");
		restaurant1T = new JLabel("Sushi Spot");
		dish1 = new JPanel();
		dish1Info = new JTextPane();
		dish2Info = new JTextPane();
		dish1Img = new JButton("");
		dish2Img = new JButton("");

		home.addJButton(btnNewButton, 12, 32, 50, 50, "/icons/Left.png", this);
		home.addJLabel(restaurant1Bar, 0, 0, 1000, 110, "", this);
		home.addJLabel(restaurant1T, 10, 130, 250, 50, "", this);
		restaurant1T.setFont(new Font("Academy Engraved LET", Font.BOLD, 40));

		home.addJLabel(restaurant1Bar, 0, 0, 1000, 110, "", this);
//		sign.addJPanel(dish1, 31, 186, 413, 118, this);
		home.addFJButton(dish1Img, 131, 186, 282, 118, "/RImg/Salmon.png", this);
		home.addFJButton(dish2Img, 131, 326, 282, 118, "/RImg/shutterstock.png", this);
		home.addJButton(cart_1, 681, 200, 173, 55, "/icons/Shopping Cart.png", this);
		addJTextPane(dish1Info, 31, 186, 130, 118, this);
		addJTextPane(dish2Info, 31, 326, 130, 118, this);
		quantity(dish1Img, "Salmon Sashimi", 12);
		quantity(dish2Img, "California Roll", 8);

		cart_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.cardLayout.show(main.root, "order");
				main.scrollPane3.getVerticalScrollBar().setValue(0);
			}
		});
		summarize(cart_1,"Sushi Spot");
		dish1Info.setText("\nSalmon Sashimi\n$12");
		dish2Info.setText("\nCalifornia Roll\n$8");
		sign.back(btnNewButton);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				home.slideOut();
			}
		});
	}

	public static void addJTextPane(JTextPane t, int x, int y, int width, int height, JPanel j) {
		if (t != null) {
			t.setLayout(null);
			t.setForeground(Color.BLACK);
			t.setBackground(new Color(245, 255, 250));
			t.setFont(new Font("Academy Engraved LET", Font.BOLD, 14));
			t.setBounds(x, y, width, height);
			t.setEditable(false);
			j.add(t);
		}
	}

	public static void quantity(JButton b, String s, int n) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "Purchase Quantity";
				String message = "Please enter the quantity you want to purchase:";
				String name = s;
				int price = n;
				String input = JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
				if (input != null && !input.isEmpty()) {
					try {
						int quantity = Integer.parseInt(input);
						String query = "INSERT INTO cart (name, price, quantity) VALUES ('" + name + "', " + price
								+ ", " + quantity + ")";
						int rowsInserted = main.statement.executeUpdate(query);
						if (rowsInserted > 0) {
							JOptionPane.showMessageDialog(null, "A new record was inserted successfully!", "success",
									JOptionPane.PLAIN_MESSAGE);
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Please enter a valid quantity.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please enter a quantity.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public static void summarize(JButton b,String s) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					order.totalPrice = 0;
					ResultSet rs = main.statement.executeQuery("SELECT * FROM cart");
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();

					String cartContent = "";
					String header = "";
					for (int i = 1; i <= columnCount; i++) {
						header += String.format("%-10s", rsmd.getColumnName(i));
					}
					cartContent += header + "\n\n";
					while (rs.next()) {
						String name = rs.getString("name");
						int price = rs.getInt("price");
						int quantity = rs.getInt("quantity");
						order.totalPrice += price * quantity;
						cartContent += String.format("%-20s%10d%10d\n", name, price, quantity);
					}
					cartContent += "\ntotal price:  $";
					cartContent += String.format("%d", order.totalPrice);
					// Get the current time in the desired format
					order.timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					String query = "INSERT INTO history (time, info, store) VALUES ('" + order.timeStamp + "', '" + cartContent + "', '" + s + "')";
					int rowsInserted = main.statement.executeUpdate(query);
					// 將購物車內容顯示在textfield中
					order.orderSummaryText.setText(cartContent);
					// 清空購物車
					main.statement.executeUpdate("DELETE FROM cart");

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
	}
}
