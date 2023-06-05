import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

public class sideBar extends JPanel {

	private JButton sideAccount, sideFavorite, sideWallet, sideCoupons, sideHelp, sideOrder, sideLogout;

	public sideBar() {
		setBounds(-200, 0, 200, 1000);
		setForeground(Color.WHITE);
		setBackground(new Color(54, 57, 63));
		setLayout(null);

		sideAccount = new JButton("Account");
		sideFavorite = new JButton("Favorite");
		sideWallet = new JButton("Wallet");
		sideCoupons = new JButton("Coupons");
		sideHelp = new JButton("Help");
		sideOrder = new JButton("Order");
		sideLogout = new JButton("");

		addJButton(sideAccount, 0, 0, 200, 60, "/icons/Circled User.png");
		addJButton(sideFavorite, 23, 132, 205, 38, "/icons/Favorite.png");
		addJButton(sideWallet, 23, 192, 205, 38, "/icons/Wallet.png");
		addJButton(sideCoupons, 23, 252, 205, 38, "/icons/Tags.png");
		addJButton(sideHelp, 23, 312, 205, 38, "/icons/Help.png");
		addJButton(sideOrder, 23, 72, 205, 38, "/icons/Purchase Order.png");
		addJButton(sideLogout, -20, 490, 65, 50, "/icons/Logout.png");

		sideAccount.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				home.sidebarVisible = false;
				sideBar.this.setBounds(-200, 0, 200, 572);
				if (logIn.logIn)
					main.cardLayout.show(main.root, "user");
				else
					main.cardLayout.show(main.root, "logIn");
			}
		});
		sideFavorite.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				home.sidebarVisible = false;
				sideBar.this.setBounds(-200, 0, 200, 572);
				main.cardLayout.show(main.root, "favor");
			}
		});
		sideWallet.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				home.sidebarVisible = false;
				sideBar.this.setBounds(-200, 0, 200, 572);
				main.cardLayout.show(main.root, "wallet");
			}
		});
		sideCoupons.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
//				home.sidebarVisible = false;
//				sideBar.this.setBounds(-200, 0, 200, 572);
//				main.cardLayout.show(main.root, "wallet");

				String cardNumber = JOptionPane.showInputDialog("Enter coupon number:");
				if (cardNumber != null && !cardNumber.isEmpty()) {
					// 在这里检查商品卡序号是否有效
					int cashAmount = 50; // 假设兑换每张卡可获得50元现金
					JOptionPane.showMessageDialog(null,
							"You have received " + cashAmount + " cash for the coupon #" + cardNumber, "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid card number. Please enter a valid card number.",
							"Warning", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		sideHelp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				home.sidebarVisible = false;
				sideBar.this.setBounds(-200, 0, 200, 572);
				main.cardLayout.show(main.root, "help");
				main.scrollPane.getVerticalScrollBar().setValue(0);
			}
		});
		sideOrder.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				home.sidebarVisible = false;
				sideBar.this.setBounds(-200, 0, 200, 572);
				main.cardLayout.show(main.root, "orders");
			}
		});
		sideLogout.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				home.sidebarVisible = false;
				sideBar.this.setBounds(-200, 0, 200, 572);
				logIn.logIn = false;
				main.cardLayout.show(main.root, "logIn");
			}
		});
	}

	public void addJButton(JButton b, int x, int y, int width, int height, String iconPath) {
		b.setBorder(null);
		b.setBackground(new Color(34, 40, 44, 0));
		b.setForeground(Color.WHITE);
		b.setFont(new Font("Academy Engraved LET", Font.BOLD, 20));
		b.setBounds(x, y, width, height);
		b.setIcon(new ImageIcon(main.class.getResource(iconPath)));
		b.setUI(new BasicButtonUI());
		b.setHorizontalAlignment(SwingConstants.LEFT);
		b.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				b.setBackground(new Color(54, 81, 207));
				b.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				b.setBackground(new Color(54, 57, 63));
			}
		});

		this.add(b);
	}
}
