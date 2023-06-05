import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class restaurant2 extends JPanel {

	private JButton btnNewButton, cart_1;
	private JPanel dish1;
	private JLabel restaurant1T, restaurant1Bar;
	private JTextPane dish1Info, dish2Info;
	public static JButton dish1Img, dish2Img;

	public restaurant2() {
		setBackground(new Color(34, 40, 44));
		setLayout(null);

		btnNewButton = new JButton("");
		cart_1 = new JButton("");
		restaurant1Bar = new JLabel("");
		restaurant1T = new JLabel("Fast Bites");
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
		home.addFJButton(dish1Img, 131, 186, 282, 118, "/RImg/cheesburgers.png", this);
		home.addFJButton(dish2Img, 131, 326, 282, 118, "/RImg/Nuggets.png", this);
		home.addJButton(cart_1, 681, 200, 173, 55, "/icons/Shopping Cart.png", this);
		restaurant1.addJTextPane(dish1Info, 31, 186, 130, 118, this);
		restaurant1.addJTextPane(dish2Info, 31, 326, 130, 118, this);
		restaurant1.quantity(dish1Img, "Cheeseburger", 6);
		restaurant1.quantity(dish2Img, "Chicken Nuggets", 4);

		cart_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.cardLayout.show(main.root, "order");
				main.scrollPane3.getVerticalScrollBar().setValue(0);
			}
		});
		restaurant1.summarize(cart_1,"Fast Bites");
		dish1Info.setText("\nCheeseburger\n$6");
		dish2Info.setText("\nChicken Nuggets\n$4");
		sign.back(btnNewButton);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				home.slideOut();
			}
		});
	}
}