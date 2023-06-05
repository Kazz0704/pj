import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class favor extends JPanel {

	public static JPanel innerPanel;

	public favor() {
		setLayout(new BorderLayout());
		
		JButton b = new JButton("");
		b.setBorder(null);
		b.setOpaque(false);
		b.setBackground(null);
		b.setForeground(Color.WHITE);
		b.setFont(new Font("Academy Engraved LET", Font.BOLD, 20));
		ImageIcon icon = new ImageIcon(home.class.getResource("/img/Home.png"));
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
		sign.back(b);
		
		// 創建可滾動的panel
		innerPanel = new JPanel();
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(innerPanel);
		add(scrollPane, BorderLayout.CENTER);
		add(b, BorderLayout.NORTH);
		try {
		    String sql = "DELETE FROM favor";
		    boolean rowsDeleted = main.statement.execute(sql);
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
	}
	
	public static void update() {
		try {
			// 創建一個SQL語句，用於從favor表中讀取name和img欄位
			String sql = "SELECT name, img FROM favor";

			// 創建一個Statement對象，用於執行SQL語句
			Statement statement = main.conn.createStatement();

			// 執行查詢操作，並返回結果集
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				// 從結果集中讀取name和img欄位的值
				String name = resultSet.getString("name");
				String img = resultSet.getString("img");

				// 創建一個新的restaurantPanel，顯示餐廳名稱和圖片
				JPanel restaurantPanel = new JPanel(new BorderLayout());
				JLabel nameLabel = new JLabel(name);
				nameLabel.setPreferredSize(new Dimension(100, 200));
				ImageIcon originalIcon = new ImageIcon(home.class.getResource(img));
				Image originalImage = originalIcon.getImage();
				Image scaledImage = originalImage.getScaledInstance(500, 200, Image.SCALE_SMOOTH);
				ImageIcon scaledIcon = new ImageIcon(scaledImage);
				JLabel imgLabel = new JLabel(scaledIcon);
				JButton deleteButton = new JButton("刪除");
				deleteButton.addActionListener(e -> {
					innerPanel.remove(restaurantPanel);
					innerPanel.revalidate();
					innerPanel.repaint();
				});
				restaurantPanel.add(nameLabel, BorderLayout.WEST);
				restaurantPanel.add(imgLabel, BorderLayout.CENTER);
				restaurantPanel.add(deleteButton, BorderLayout.EAST);

				innerPanel.add(restaurantPanel);
				innerPanel.revalidate();
				innerPanel.repaint();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void clear() {
		innerPanel.removeAll();
	}
}