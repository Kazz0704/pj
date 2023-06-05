import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicSliderUI;
import javax.swing.plaf.basic.BasicToggleButtonUI;
import javax.swing.plaf.metal.MetalToggleButtonUI;

public class home extends JPanel {

	private JPanel optionBar;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton align, Recommended1Icon, cart, search, location, Recommended1Name, Recommended2Icon,
			Recommended4Icon, Recommended3Name, Recommended3Icon, Recommended2Name, Recommended4Name;
	private JLabel Brand, pizza, dessert, fastfood, hambergur, sushi, price1, price2, RecommendedT, Recommended1Img,
			category, provide, price, restriction, Recommended2Img, Recommended3Img, Recommended4Img;
	private JTextField searchbar;
	private JRadioButton Category1, Category2, Category3;
	private JCheckBox provide1, provide2;
	private JSlider priceBar;
	private JToggleButton restriction1, restriction2, restriction3, restriction4;
	public static Timer timer;
	public static boolean sidebarVisible;
	private boolean favor1 = false, favor2 = false, favor3 = false, favor4 = false;

	public home() throws SQLException, IllegalArgumentException {

		setBounds(0, 0, 1000, 1000);
		setBorder(null);
		setBackground(new Color(34, 40, 44));
		setLayout(null);

		optionBar = new JPanel();
		optionBar.setBounds(0, 170, 270, 1000);
		this.add(optionBar);
		optionBar.setBackground(new Color(34, 40, 44));
		optionBar.setLayout(null);

		align = new JButton("");
		Recommended1Icon = new JButton("");
		cart = new JButton("");
		location = new JButton("⚑  No. 64, Section 2, Zhinan Road, Wenshan District, Taipei, Taiwan R.O.C.");
		location.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newAddress = JOptionPane.showInputDialog("Enter new address:");
				if (newAddress != null && !newAddress.isEmpty()) {
					location.setText("⚑ " + newAddress);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid address. Please enter a valid address.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		search = new JButton("");
		Recommended3Name = new JButton("Sushi Spot");
		Recommended4Icon = new JButton("");
		Recommended4Name = new JButton("Fast Bites");
		Recommended2Name = new JButton("Burger Barn");
		Recommended3Icon = new JButton("");
		Recommended1Name = new JButton("Pizza Palace");
		Recommended2Icon = new JButton("");

		Brand = new JLabel("Hunger X Hunter");
		pizza = new JLabel("");
		dessert = new JLabel("");
		fastfood = new JLabel("");
		hambergur = new JLabel("");
		sushi = new JLabel("");
		category = new JLabel("Category:");
		provide = new JLabel("Provided by");
		price = new JLabel("Price Range");
		price1 = new JLabel("＄");
		price2 = new JLabel("＄＄＄");
		restriction = new JLabel("DietaryRestrictions");
		RecommendedT = new JLabel("Hunter Recommended");
		Recommended1Img = new JLabel("New label");
		Recommended2Img = new JLabel("New label");
		Recommended3Img = new JLabel("New label");
		Recommended4Img = new JLabel("New label");

		searchbar = new JTextField();

		Category1 = new JRadioButton("Recommended Dishes");
		Category2 = new JRadioButton("Popular Dishes");
		Category3 = new JRadioButton("Ratings");

		provide1 = new JCheckBox("Discounts");
		provide2 = new JCheckBox("Highest Rated");

		priceBar = new JSlider();

		restriction1 = new JToggleButton("Vegetarian");
		restriction2 = new JToggleButton("Vegan\n");
		restriction3 = new JToggleButton("Gluten−Free");
		restriction4 = new JToggleButton("Vegan\n");

		addJButton(align, 30, 30, 30, 30, "/icons/Align Justify.png", this);
		addJButton(cart, 950, 30, 30, 30, "/icons/Shopping Cart.png", this);
		cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.cardLayout.show(main.root, "order");
				main.scrollPane3.getVerticalScrollBar().setValue(0);
			}
		});
		addJButton(location, 340, 35, 179, 25, "", this);
		location.setFont(new Font("Academy Engraved LET", Font.BOLD, 12));
		location.setForeground(new Color(3, 169, 244));
		addJButton(search, 855, 30, 30, 30, "/icons/Search.png", this);
		addJButton(Recommended1Icon, 580, 317, 50, 50, "/img/Heart.png", this);
		addJButton(Recommended2Icon, 940, 317, 50, 50, "/img/Heart.png", this);
		addJButton(Recommended3Icon, 580, 504, 50, 50, "/img/Heart.png", this);
		addJButton(Recommended4Icon, 940, 504, 50, 50, "/img/Heart.png", this);
		addFavorAction(Recommended1Icon, "Sushi Spot", "/img/sushi.png");
		addFavorAction(Recommended2Icon, "Fast Bites", "/img/fastfood.png");
		addFavorAction(Recommended3Icon, "Burger Barn", "/img/hamburger.png");
		addFavorAction(Recommended4Icon, "Pizza Palace", "/img/pizza.png");

		ImageIcon icon = new ImageIcon(home.class.getResource("/img/Heart.png"));
		Recommended1Icon.setIcon(icon);
		Recommended2Icon.setIcon(icon);
		Recommended3Icon.setIcon(icon);
		Recommended4Icon.setIcon(icon);

		addJButton(Recommended1Name, 290, 317, 290, 50, "", this);
		addJButton(Recommended2Name, 650, 317, 290, 50, "", this);
		addJButton(Recommended3Name, 290, 504, 290, 50, "", this);
		addJButton(Recommended4Name, 650, 504, 290, 50, "", this);

		change(Recommended1Name, 1);

		addJLabel(Brand, 85, 35, 236, 35, "", this);
		Brand.setFont(new Font("Academy Engraved LET", Font.BOLD, 30));
//		Brand.setForeground(new Color(3, 169, 244));
		addJLabel(pizza, 70, 75, 60, 60, "/icons/Pizza.png", this);
		addJLabel(dessert, 270, 75, 60, 60, "/icons/dessert.png", this);
		addJLabel(fastfood, 470, 75, 60, 60, "/icons/Street Food.png", this);
		addJLabel(hambergur, 670, 75, 60, 60, "/icons/Hamburger.png", this);
		addJLabel(sushi, 870, 75, 60, 60, "/icons/Salmon Sushi.png", this);
		addJLabel(category, 3, 7, 100, 24, "", optionBar);
		category.setForeground(new Color(3, 169, 244));
		addJLabel(provide, 8, 140, 153, 24, "", optionBar);
		provide.setForeground(new Color(3, 169, 244));
		addJLabel(price, 6, 244, 153, 24, "", optionBar);
		addJLabel(price1, 8, 275, 30, 30, "", optionBar);
		addJLabel(price2, 200, 275, 65, 30, "", optionBar);
		addJLabel(restriction, 13, 336, 194, 24, "", optionBar);
		addJLabel(RecommendedT, 270, 170, 323, 50, "", this);
		RecommendedT.setFont(new Font("Academy Engraved LET", Font.BOLD, 30));
//		RecommendedT.setForeground(new Color(3, 169, 244));
		addJLabel(Recommended1Img, 290, 217, 340, 100, "/img/sushi.png", this);
		addJLabel(Recommended2Img, 650, 217, 340, 100, "/img/fastfood.png", this);
		addJLabel(Recommended3Img, 290, 404, 340, 100, "/img/hamburger.png", this);
		addJLabel(Recommended4Img, 650, 404, 340, 100, "/img/pizza.png", this);

		addJTextField(searchbar, 550, 35, 300, 25);
		addJRadioButton(Category1, 13, 36, 296, 27);
		addJRadioButton(Category2, 13, 66, 153, 27);
		addJRadioButton(Category3, 13, 96, 135, 27);
		addJCheckBox(provide1, 13, 170, 117, 23, optionBar);
		addJCheckBox(provide2, 13, 200, 117, 23, optionBar);
		addJSlider(priceBar, 20, 300, 200, 16);

		addJToggleButton(restriction1, 19, 368, 100, 25);
		addJToggleButton(restriction2, 131, 368, 100, 25);
		addJToggleButton(restriction3, 19, 398, 100, 25);
		addJToggleButton(restriction4, 131, 398, 100, 25);

		align.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				slideIn();
			}
		});

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				slideOut();
			}
		});

		Recommended1Name.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				home.sidebarVisible = false;
				main.getSidebar().setBounds(-200, 0, 200, 572);
				main.cardLayout.show(main.root, "restaurant1");
			}
		});
		Recommended2Name.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				home.sidebarVisible = false;
				main.getSidebar().setBounds(-200, 0, 200, 572);
				main.cardLayout.show(main.root, "restaurant2");
			}
		});
		Recommended3Name.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				home.sidebarVisible = false;
				main.getSidebar().setBounds(-200, 0, 200, 572);
				main.cardLayout.show(main.root, "restaurant3");
			}
		});
		Recommended4Name.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				home.sidebarVisible = false;
				main.getSidebar().setBounds(-200, 0, 200, 572);
				main.cardLayout.show(main.root, "restaurant4");
			}
		});
	}

	// 增加 JButton
	public static void addJButton(JButton b, int x, int y, int width, int height, String iconPath, JPanel j) {
		if (b != null) {
			b.setBorder(null);
			b.setOpaque(false);
			b.setBackground(null);
			b.setForeground(Color.WHITE);
			b.setFont(new Font("Academy Engraved LET", Font.BOLD, 20));
			b.setBounds(x, y, width, height);
			ImageIcon icon = new ImageIcon(home.class.getResource(iconPath));
			b.setIcon(icon);

			b.setUI(new BasicButtonUI());
			b.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					b.setOpaque(true);
					b.setBackground(new Color(54, 81, 207));
					b.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					b.setOpaque(false);
					b.setBackground(null);
				}
			});
			j.add(b);
		}

	}

	public static void addFJButton(JButton b, int x, int y, int width, int height, String iconPath, JPanel j) {
		if (b != null) {
			b.setBorder(null);
			b.setOpaque(false);
			b.setBackground(null);
			b.setForeground(Color.WHITE);
			b.setFont(new Font("Academy Engraved LET", Font.BOLD, 20));
			b.setBounds(x, y, width, height);
			// 載入圖像並進行縮放
			ImageIcon originalIcon = new ImageIcon(home.class.getResource(iconPath));
			Image originalImage = originalIcon.getImage();
			Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);

			// 將縮放後的圖像設置為JLabel的圖標
			b.setIcon(scaledIcon);
			b.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					b.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			});
			j.add(b);
		}

	}

	public static void addJFormattedTextField(JFormattedTextField t, int x, int y, int width, int height, JPanel j) {
		if (t != null) {
			t.setLayout(null);
			t.setForeground(Color.WHITE);
			t.setBackground(new Color(34, 40, 44, 0));
			t.setFont(new Font("Academy Engraved LET", Font.BOLD, 30));
			t.setBounds(x, y, width, height);
			j.add(t);
		}
	}

	// 增加 JLabel
	public static void addJLabel(JLabel l, int x, int y, int width, int height, String iconPath, JPanel j) {
		if (l != null) {
			l.setForeground(Color.WHITE);
			l.setBackground(null);
			l.setFont(new Font("Academy Engraved LET", Font.BOLD, 18));
			l.setBounds(x, y, width, height);

			// 載入圖像並進行縮放
			ImageIcon originalIcon = new ImageIcon(home.class.getResource(iconPath));
			Image originalImage = originalIcon.getImage();
			Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);

			// 將縮放後的圖像設置為JLabel的圖標
			l.setIcon(scaledIcon);

			j.add(l);
		}
	}

	public static void addJPanel(JPanel p, int x, int y, int width, int height, JPanel j) {
		if (p != null) {
			p.setLayout(null);
			p.setForeground(Color.WHITE);
			p.setBackground(new Color(34, 40, 44, 0));
			p.setFont(new Font("Academy Engraved LET", Font.BOLD, 30));
			p.setBounds(x, y, width, height);
			j.add(p);
		}
	}

	// 增加 JTextField
	public void addJTextField(JTextField t, int x, int y, int width, int height) {
		t.setBounds(x, y, width, height);
		t.setColumns(10);
		this.add(t);
	}

	// 增加 JRadioButton
	public void addJRadioButton(JRadioButton r, int x, int y, int width, int height) {
		r.setForeground(Color.WHITE);
		r.setBackground(new Color(34, 40, 44, 0));
		r.setFont(new Font("Academy Engraved LET", Font.BOLD, 15));
		r.setBounds(x, y, width, height);
		buttonGroup.add(r);
		optionBar.add(r);
	}

	// 增加 JCheckBox
	public static void addJCheckBox(JCheckBox c, int x, int y, int width, int height, JPanel j) {
		c.setForeground(Color.WHITE);
		c.setBackground(new Color(34, 40, 44, 0));
		c.setFont(new Font("Academy Engraved LET", Font.BOLD, 18));
		c.setBounds(x, y, width, height);
		j.add(c);
	}

	// 增加 JSlider
	public void addJSlider(JSlider s, int x, int y, int width, int height) {
		s.setForeground(Color.WHITE);
		s.setBackground(new Color(34, 40, 44, 0));
		s.setFont(new Font("Academy Engraved LET", Font.BOLD, 12));
		s.setBounds(x, y, width, height);
		s.setUI(new BasicSliderUI());
		optionBar.add(s);
	}

	// 增加 JToggleButton
	public void addJToggleButton(JToggleButton b, int x, int y, int width, int height) {
		b.setBorder(null);
		b.setOpaque(false);
		b.setBackground(null);
		b.setForeground(Color.WHITE);
		b.setFont(new Font("Academy Engraved LET", Font.BOLD, 15));
		b.setBounds(x, y, width, height);

		b.setModel(new JToggleButton.ToggleButtonModel() {
			private boolean selected;

			@Override
			public boolean isSelected() {
				return selected;
			}

			@Override
			public void setSelected(boolean selected) {
				this.selected = selected;
				if (selected) {
					b.setOpaque(true);
					b.setBackground(new Color(54, 81, 207));
				} else {
					b.setOpaque(false);
					b.setBackground(null);
				}
			}
		});

		optionBar.add(b);
	}

	public static void slideOut() {
		JPanel sidebar = main.getSidebar();
		if (timer != null && timer.isRunning()) {
			timer.stop();
		}
		if (sidebarVisible) {
			// Animate the sidebar moving out of view
			timer = new Timer(10, new ActionListener() {
				int x = 0;

				@Override
				public void actionPerformed(ActionEvent e) {
					x -= 10;
					sidebar.setBounds(x, 0, 200, 572);
					if (x <= -200) {
						timer.stop();
						sidebarVisible = false;
					}
				}
			});
		}
		if (timer != null)
			timer.start();
	}

	public void slideIn() {
		JPanel sidebar = main.getSidebar();
		if (timer != null && timer.isRunning()) {
			timer.stop();
		}
		if (!sidebarVisible) {
			// Animate the sidebar moving into view
			timer = new Timer(10, new ActionListener() {
				int x = -200;

				@Override
				public void actionPerformed(ActionEvent e) {
					x += 10;
					sidebar.setBounds(x, 0, 200, 572);
					if (x >= 0) {
						timer.stop();
						sidebarVisible = true;
					}
				}
			});
		}
		timer.start();
	}

	public void change(JButton b, int i) {
		b.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				main.cardLayout.show(main.root, "restaurant" + i);
			}
		});
	}

	public void addFavor(String name, String img, JButton button) {

		// 將方法添加到button的點擊事件中

		try {
			// 創建一個SQL語句，用於更新favor列表中的name和img欄位
			String sql = "INSERT favor SET name = ?, img = ?";

			// 創建一個PreparedStatement對象，並將要更新的值設置為問號占位符
			PreparedStatement statement = main.conn.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, img);

			// 執行更新操作
			statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(home.class.getResource("/img/Love.png"));
		button.setIcon(icon);
		favor.clear();
		favor.update();
	}

	public void deleteFavor(String s, JButton button) {

		// 將方法添加到button的點擊事件中

		try {
			// 創建一個SQL語句，用於刪除favor列表中指定id的數據
			String sql = "DELETE FROM favor WHERE name = ?";

			// 創建一個PreparedStatement對象，並將要刪除的id設置為問號占位符
			PreparedStatement statement = main.conn.prepareStatement(sql);
			statement.setString(1, s);

			// 執行刪除操作
			statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(home.class.getResource("/img/Heart.png"));
		button.setIcon(icon);
		favor.clear();
		favor.update();
	}

	public void addFavorAction(JButton button, String name, String img) {
		Action action = new AbstractAction() {
			boolean state = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (state) {
					deleteFavor(name, button);
				} else {
					addFavor(name, img, button);
				}
				state = !state;
			}
		};
		button.setAction(action);
	}
}
