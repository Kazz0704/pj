import java.awt.*;
import javax.swing.*;


public class user extends JPanel {

	private JLabel label,l;
	private JButton b;

	public user() {
		setBackground(new Color(34, 40, 44));
		setLayout(null);
		
		l = new JLabel(logIn.userName);
		home.addJLabel(l, 100, 100, 200, 200, "", this);
		b = new JButton();
		home.addJButton(b, 0, 0, 50, 50, "/icons/Left.png", this);
		sign.back(b);
	}
}
