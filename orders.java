import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

public class orders extends JPanel {

	private JButton favoriteBack;
	public static JComboBox<String> comboBox;
	private JTextPane textPane;
	private ResultSet resultSet;

	public orders() throws SQLException {
		
//		try {
//		    String sql = "DELETE FROM history";
//		    boolean rowsDeleted = main.statement.execute(sql);
//		} catch (SQLException ex) {
//		    ex.printStackTrace();
//		}
		
		setBackground(new Color(34, 40, 44));
		setLayout(null);

		// 設定 ComboBox
		comboBox = new JComboBox<String>();
		comboBox.setBounds(100, 10, 200, 30);
		try {
			// 讀取所有的時間和店名
			String query = "SELECT time, store FROM history";
			resultSet = main.statement.executeQuery(query);
			while (resultSet.next()) {
				String time = resultSet.getString("time");
				String store = resultSet.getString("store");
				String comboBoxItem = time + " " + store;
				orders.comboBox.addItem(comboBoxItem);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		add(comboBox);
		textPane = new JTextPane();

		restaurant1.addJTextPane(textPane, 100, 50, 500, 300, this);
		String selectedInfo = "", selectedLocation = "", selectedMethod = "";
		String selectedItem = (String) comboBox.getSelectedItem();
		String[] parts = selectedItem.split(" ");
		String selectedTime = parts[0] + " " + parts[1];
		String selectedStore = parts[2] + " " + parts[3];

		loadInfo();
		
		// 設定 JButton
		favoriteBack = new JButton("");
		home.addJButton(favoriteBack, 0, 0, 50, 50, "/icons/Left.png", this);
		sign.back(favoriteBack);
//		search = new JButton("");
//		home.addJButton(search, 500, 500, 50, 50, "/icons/Left.png", this);

		// 加入到 orders() 的 JPanel 中
		add(favoriteBack);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadInfo();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
	public void loadInfo() throws SQLException{
		String selectedInfo = "", selectedLocation = "", selectedMethod = "";
		String selectedItem = (String) comboBox.getSelectedItem();
		String[] parts = selectedItem.split(" ");
		String selectedTime = parts[0] + " " + parts[1];
		String selectedStore = parts[2] + " " + parts[3];

		// 根據選擇的時間和店名，讀取相對應的 info
		String query = "SELECT info FROM history WHERE time='" + selectedTime + "' AND store='"
				+ selectedStore + "'";
		ResultSet resultSet = main.statement.executeQuery(query);
		if (resultSet.next()) {
			selectedInfo = resultSet.getString("info");
		}
		query = "SELECT location, method FROM history WHERE time='" + selectedTime + "' AND store='"
				+ selectedStore + "'";
		resultSet = main.statement.executeQuery(query);
		if (resultSet.next()) {
			selectedLocation = resultSet.getString("location");
			selectedMethod = resultSet.getString("method");
		}
		textPane.setText(selectedStore + "   " + selectedTime + "\n" + selectedInfo + "\n\n" + "Location: "
				+ selectedLocation + "\n" + "Method: " + selectedMethod);
	}
}
