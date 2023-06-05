import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class logIn extends JPanel {

	public static boolean logIn = false;
	public static String userName;
	private JPanel register, signBackGround;
	private JLabel registerTitle, registerMailT, registerPasswordT, lblNewLabel;
	private JButton registerContitnue, signBack, createA;
	private JFormattedTextField signInMail, registerPassword;

	public logIn() {
		setBackground(new Color(34, 40, 44));
		setLayout(null);
		setBounds(0, 0, main.FRAME_WIDTH, main.FRAME_HEIGHT);

		register = new JPanel();
		signBackGround = new JPanel();

		registerTitle = new JLabel("Hunger X Hunter Sign In");
		registerMailT = new JLabel("E-mail address");
		registerPasswordT = new JLabel("Password");
		lblNewLabel = new JLabel("");

		signInMail = new JFormattedTextField();
		registerPassword = new JFormattedTextField();

		registerContitnue = new JButton("Continue");
		createA = new JButton("Create account");
		signBack = new JButton("");
		createA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.sidebarVisible = false;
				main.sideBar.setBounds(-200, 0, 200, 572);
				main.cardLayout.show(main.root, "sign");
			}
		});

		home.addJPanel(register, 510, 0, 489, main.FRAME_HEIGHT, this);
		home.addJPanel(signBackGround, 0, 0, 550, main.FRAME_HEIGHT, this);

		home.addJLabel(registerTitle, 50, 39, 450, 35, "", register);
		home.addJLabel(registerMailT, 50, 178, 450, 25, "", register);
		home.addJLabel(registerPasswordT, 50, 237, 450, 25, "", register);
		home.addJLabel(lblNewLabel, 0, 0, 550, main.FRAME_HEIGHT, "/RImg/color.png", signBackGround);
		registerTitle.setFont(new Font("Academy Engraved LET", Font.BOLD, 25));
		registerTitle.setForeground(new Color(3, 169, 244));
		addJButton(registerContitnue, 190, 385, 150, 25, "", register);
		addJButton(signBack, 430, 0, 50, 50, "/img/Arrow.png", register);
		addJButton(createA, 175, 324, 180, 35, "", register);
		sign.back(signBack);

		home.addJFormattedTextField(signInMail, 50, 202, 400, 35, register);
		home.addJFormattedTextField(registerPassword, 50, 262, 400, 35, register);
		signIn();

	}

	public void signIn() {
		registerContitnue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 取得輸入值
				String email = signInMail.getText();
				String password = registerPassword.getText();

				try {
					String query = "SELECT * FROM users WHERE email='" + email + "' AND password='" + password + "'";
					ResultSet resultSet = main.statement.executeQuery(query);

					if (resultSet.next()) {
						JOptionPane.showMessageDialog(null, "登錄成功！", "登錄成功", JOptionPane.INFORMATION_MESSAGE);
						userName = resultSet.getString("name");
						logIn = true;
						user u = new user();
						main.addComponents(u, "user");
						main.cardLayout.show(main.root, "main");
					} else {
						JOptionPane.showMessageDialog(null, "登錄失敗！", "登錄失敗", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
		});
	}

	public static void addJButton(JButton b, int x, int y, int width, int height, String iconPath, JPanel j) {
		b.setBorder(null);
		b.setBackground(null);
		b.setForeground(Color.WHITE);
		b.setFont(new Font("Academy Engraved LET", Font.BOLD, 15));
		b.setBounds(x, y, width, height);
		b.setBackground(new Color(34, 40, 44));
		ImageIcon icon = new ImageIcon(home.class.getResource(iconPath));
		b.setIcon(icon);

		b.setUI(new BasicButtonUI());
		b.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				b.setBackground(new Color(54, 81, 207));
				b.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				b.setBackground(new Color(34, 40, 44));
			}
		});
		j.add(b);
	}
}
