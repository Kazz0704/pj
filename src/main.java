import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicPanelUI;

public class main extends JFrame {

	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 600;

//	private int pointsValue, card2Value, totalePrice;
	private String server, database, url, username, Mypassword;
	public static JPanel root, contentPane, Main;
	public static JScrollPane scrollPane,scrollPane2,scrollPane3;
	public static CardLayout cardLayout;
	public static Connection conn;
	public static Statement statement;
	public static sideBar sideBar;
	private home home;
	public static wallet wallet;
	private sign sign;
	private help help;
	private orders orders;
	private restaurant1 restaurant1;
	private restaurant2 restaurant2;
	private restaurant3 restaurant3;
	private restaurant4 restaurant4;
	private order order;
	private logIn logIn;
	private animation animation;
	private favor favor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public main() throws SQLException {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, FRAME_WIDTH, FRAME_HEIGHT);
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		root = new JPanel();
		root.setBackground(new Color(34, 40, 44));
		root.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		
		animation = new animation(10000); // 设置动画时间为5秒
		contentPane.add(animation); // 将动画加入内容面板
		animation.setVisible(true); // 显示动画

		Timer timer = new Timer(12500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 移除动画，添加滚动面板，并更新布局
				contentPane.remove(animation);
				contentPane.add(root);
				
//				scrollPane.getViewport().setViewPosition(new Point(0, 0));
				validate();
			}
		});
		timer.setRepeats(false); // 只执行一次
		timer.start();
		
		root.setLayout(new CardLayout(0, 0));
		cardLayout = (CardLayout) root.getLayout();
		server = "jdbc:mysql://140.119.19.73:3315/";
		database = "111306004";
		url = server + database + "?useSSL=false";
		username = "111306004";
		Mypassword = "ddqqt";

		conn = DriverManager.getConnection(url, username, Mypassword);
		System.out.println("DB Connectd");
		statement = conn.createStatement();

		sideBar = new sideBar();
		home = new home();
		wallet = new wallet();
		sign = new sign();
		order = new order();
		help = new help();
		logIn = new logIn();
		restaurant1 = new restaurant1();
		restaurant2 = new restaurant2();
		restaurant3 = new restaurant3();
		restaurant4 = new restaurant4();
		orders = new orders();
		favor = new favor();
		addComponents();
	}

	public static class RoundJPanelUI extends BasicPanelUI {
		@Override
		public void paint(Graphics g, JComponent c) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(c.getBackground());
			g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 50, 50);
			super.paint(g, c);
		}
	}

	public static JPanel getSidebar() {
		return sideBar;
	}

	public static JPanel getWallet() {
		return wallet;
	}

	private void addComponents() throws SQLException {

		Main = new JPanel();
		Main.setPreferredSize(new Dimension(main.FRAME_WIDTH, main.FRAME_HEIGHT+50));
		Main.setLayout(null);

		Main.add(sideBar);
		Main.add(home);
		
		scrollPane2 = new JScrollPane(Main);
		scrollPane2.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		root.add(scrollPane2, "main");
		scrollPane2.getVerticalScrollBar().setValue(0);
		
		
		root.add(wallet, "wallet");
		root.add(sign, "sign");
		
		scrollPane = new JScrollPane(help);
		scrollPane.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		root.add(scrollPane, "help");
		
		root.add(orders, "orders");
		root.add(restaurant1, "restaurant1");
		root.add(restaurant2, "restaurant2");
		root.add(restaurant3, "restaurant3");
		root.add(restaurant4, "restaurant4");

		
		scrollPane3 = new JScrollPane(order);
		scrollPane3.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		root.add(scrollPane3, "order");
		root.add(scrollPane, "help");
		root.add(logIn, "logIn");
		root.add(favor, "favor");

		JLayeredPane layeredPane = this.getLayeredPane();
		layeredPane.add(sideBar, JLayeredPane.POPUP_LAYER);
	}

	public static void addComponents(JPanel p, String s) throws SQLException {
		root.add(p, s);
	}
}
