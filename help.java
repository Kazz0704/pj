import java.awt.*;
import java.awt.event.*;


import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

public class help extends JPanel {

//	private JPanel helpBar;
	private JLabel helpT,helpCondition;
	private JButton helpBack;
	private JTextPane helpText;
	
	public help() {
		setBackground(new Color(245, 255, 250));
		setLayout(null);
		setPreferredSize(new Dimension(main.FRAME_WIDTH, main.FRAME_HEIGHT+200));
		
//		helpBar = new JPanel();
		helpT = new JLabel("");
		helpBack = new JButton("");
		helpCondition = new JLabel("Hunger X Hunter USER TERMS & CONDITIONS");
		helpText = new JTextPane();
		home.addJButton(helpBack,10, 0, 50, 50,"/icons/Left1.png",this);
//		home.addJPanel(helpBar,0, 0, 1000, 30,this);
		home.addJLabel(helpT,0, 0, main.FRAME_WIDTH, 55,"/RImg/images.png",this);
		helpT.setBackground(new Color(34, 40, 44));
		
		home.addJLabel(helpCondition,12, 90, 838, 40,"",this);
		helpCondition.setFont(new Font("Academy Engraved LET", Font.BOLD, 30));
		helpCondition.setForeground(Color.BLACK);
		
		
		sign.back(helpBack);
		addJTextPane(helpText);

		helpBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.cardLayout.show(main.root, "main");
			}
		});
		
		
	}
	
	public void addJTextPane(JTextPane p) {
		if (p != null) {
			p.setFont(new Font("Academy Engraved LET", Font.ITALIC, 14));
			p.setLocation(67, 188);
			p.setBounds(12, 140, 1000, 1000);
			p.setEditable(false);
			p.setText("1. Contractual Relationship\n"
					+ "Save for Terms unique to Taiwan, these Terms of Use (\"Terms\") govern the access or use by you, (“You”) an individual from within any country in the world (excluding the United States and its territories and possessions) of applications (including the Uber Eats App), websites, content (collectively, the “App”) for the purposes of using the Uber Eats platform (the “Access Services”), made available by Uber Portier B.V. (“UPBV”), a private limited liability company established in the Netherlands, having its offices at Mr. Treublaan 7, 1097 DP, Amsterdam, the Netherlands, registered at the Amsterdam Chamber of Commerce under number 56317441.\n"
					+ "\n"
					+ "The Terms also govern the request for Delivery Services on the Uber Eats platform (the “Delivery Services”), which are provided by Uber Portier Taiwan Co. Ltd (“Portier Taiwan”).\n"
					+ "\n"
					+ "In these Terms, UPBV and Portier Taiwan, are collectively referred to as “Uber” or “We”, and, the Access Services and Delivery Services, are collectively referred to as the “Services”\n"
					+ "\n" + "PLEASE READ THESE TERMS CAREFULLY BEFORE ACCESSING OR USING THE SERVICES.\n" + "\n"
					+ "Your access and use of the Services constitutes your agreement to be bound by these Terms, which establishes a contractual relationship between you and Uber. If you do not agree to these Terms, you may not access the Services. These Terms expressly supersede prior agreements or arrangements with you.\n"
					+ "\n"
					+ "Supplemental terms may apply to govern policies for a particular event, activity or promotion etc., and such supplemental terms will be disclosed to you in connection with the applicable event, activity or promotion. Supplemental terms are in addition to, and shall be deemed a part of, the Terms for the purposes of the applicable event, activity or promotion. Supplemental terms shall prevail over these Terms in the event of a conflict with respect to the applicable event, activity or promotion.\n"
					+ "\n"
					+ "Uber may amend the Terms, any policies or supplemental terms (including the Community Guidelines) related to the Services from time to time. Uber will provide you with at least 2 days' notice through a posting on its website, email or the notification function of the App, in the event of a material change to any Terms, policies or supplemental terms that detrimentally affects your rights under these Terms. Amendments will be effective upon Uber’s posting of such updated Terms at this location or the amended policies or supplemental terms on the applicable Service. Your continued access or use of the Services after such posting, or after the expiry of the notice period (whichever is later), constitutes your consent to be bound by the Terms, as amended.\n"
					+ "\n"
					+ "“Community Guidelines” means the Uber’s community guidelines policy available at https://www.uber.com/legal/en/document/?name=general-community-guidelines&country=taiwan&lang=en (as amended by Uber from time to time)\n"
					+ "\n"
					+ "Our collection and use of personal information in connection with the Services is as provided in Uber’s Privacy Policy located at https://www.uber.com/legal. Uber may provide to a claims processor, regulator or an insurer any necessary information (including your contact information) if there is a complaint, dispute or conflict, which may involve you, a Merchant and/or a delivery partner and such information or data is necessary to resolve the complaint, dispute or conflict.");

			this.add(p);
		}
	}
}
