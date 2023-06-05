import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

public class sign extends JPanel {
	
	private JPanel register,signBackGround;
	private JLabel registerTitle,registerNameT,registerSurnameT,registerMailT,registerPasswordT,lblNewLabel;
	private JButton registerContitnue,signBack;
	private JFormattedTextField registerName,registerSurname,registerMail,registerPassword;
	private JCheckBox registerAgree;
	
	public sign() {
		setBackground(new Color(34, 40, 44));
		setLayout(null);
		setBounds(0, 0, main.FRAME_WIDTH, main.FRAME_HEIGHT);
		
		register = new JPanel();
		signBackGround = new JPanel();
		
		registerTitle = new JLabel("Hunger X Hunter Sign Up");
		registerNameT = new JLabel("Name");
		registerSurnameT = new JLabel("Surname");
		registerMailT = new JLabel("E-mail address");
		registerPasswordT = new JLabel("Password");
		lblNewLabel = new JLabel("");
		
		registerName = new JFormattedTextField();
		registerSurname = new JFormattedTextField();
		registerMail = new JFormattedTextField();
		registerPassword = new JFormattedTextField();
		
		registerAgree = new JCheckBox("I have read the Terms of Use carefully. ");
		
		registerContitnue = new JButton("Continue");
		signBack = new JButton("");
		
		home.addJPanel(register,510, 0, 489, main.FRAME_HEIGHT,this);
		home.addJPanel(signBackGround,0, 0, 550, main.FRAME_HEIGHT,this);
		
		home.addJLabel(registerTitle,50, 39, 450, 35,"",register);
		registerTitle.setFont(new Font("Academy Engraved LET", Font.BOLD, 25));
		registerTitle.setForeground(new Color(3, 169, 244));
		home.addJLabel(registerNameT,50, 117, 450, 25,"",register);
		home.addJLabel(registerSurnameT,264, 119, 450, 25,"",register);
		home.addJLabel(registerMailT,50, 178, 450, 25,"",register);
		home.addJLabel(registerPasswordT,50, 237, 450, 25,"",register);
		home.addJLabel(lblNewLabel,0, 0, 550, main.FRAME_HEIGHT,"/RImg/color.png",signBackGround);
		
	
		logIn.addJButton(registerContitnue,191, 385, 120, 25,"",register);
		logIn.addJButton(signBack,430, 0, 50, 50,"/img/Arrow.png",register);
		back(signBack);
		
		home.addJCheckBox(registerAgree,152, 324, 250, 35,register);
		registerAgree.setFont(new Font("Academy Engraved LET", Font.BOLD, 13));
		registerAgree.setForeground(new Color(3, 169, 244));
		home.addJFormattedTextField(registerName,50, 142, 190, 35,register);
		home.addJFormattedTextField(registerSurname,260, 142, 190, 35,register);
		home.addJFormattedTextField(registerMail,50, 202, 400, 35,register);
		home.addJFormattedTextField(registerPassword,50, 262, 400, 35,register);
		
		register(registerContitnue,registerName,registerSurname,registerMail,registerPassword);
		
		
	}

	
	public void register(JButton b,JFormattedTextField n,JFormattedTextField s,JFormattedTextField m,JFormattedTextField p) {
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 取得輸入值
				String name = n.getText();
				String surname = s.getText();
				String email = m.getText();
				String password = p.getText();

				// 執行 SQL 語句
				try {
					if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()) {
						JOptionPane.showMessageDialog(null, "所有欄位都必須填寫！", "註冊失敗", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (!registerAgree.isSelected()) {
						JOptionPane.showMessageDialog(null, "請閱讀並同意條款", "警告", JOptionPane.WARNING_MESSAGE);
						return; // 終止註冊操作
					}
					String query = "INSERT INTO users (name, surname, email, password) VALUES ('" + name + "', '"
							+ surname + "', '" + email + "', '" + password + "')";
				
					int rowsInserted = main.statement.executeUpdate(query);
					if (rowsInserted > 0) {
						JOptionPane.showMessageDialog(null, "帳戶註冊成功！", "註冊成功", JOptionPane.INFORMATION_MESSAGE);
						main.cardLayout.show(main.root, "logIn");
					} else {
						JOptionPane.showMessageDialog(null, "帳戶註冊失敗！", "註冊失敗", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	
	public static void back(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.cardLayout.show(main.root, "main");
			}
		});
	}
}