import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.lang.Math;

public class Dice {

	public Dice(){
		// GUI Frame
		JFrame guiFrame = new JFrame();
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make sure the program exits when the frame closes
		guiFrame.setTitle("Dice Game");
		guiFrame.setSize(400,150);
		guiFrame.setLocationRelativeTo(null); //This will center the JFrame in the middle of the screen


		// Game Setup Panel setup
		final JPanel setupPanel = new JPanel();
		JLabel comboLbl1 = new JLabel("Number of Dice:");
		String[] numOptions = {"1", "2", "3", "4", "5", "6"};
		JComboBox nums = new JComboBox(numOptions);
		setupPanel.add(comboLbl1);
		setupPanel.add(nums);
		JLabel comboLbl2 = new JLabel("Type of Dice:");
		String[] designOptions = {"Traditional", "Graphic"};
		JComboBox designs = new JComboBox(designOptions);
		setupPanel.add(comboLbl2);
		setupPanel.add(designs);
		JButton chooseNum = new JButton("Play Game");
		setupPanel.add(chooseNum);

		// Bottom, Back-to-Setup Panel setup
		JPanel gamePanel = new JPanel(); // the panel is not visible in output
		gamePanel.setVisible(false);
		JButton rollDice = new JButton("Roll the Dice");
		JButton backToSetup = new JButton("Change Settings");
		gamePanel.add(rollDice);
		gamePanel.add(backToSetup);

		// Dice Panel setup
		JPanel diceRoll = new JPanel();
		diceRoll.setVisible(false);
		String[] dieFaces = {"\n     *     \n", " *        \n\n        * ", " *        \n     *     \n        * ",
			" *       * \n\n *       * ", " *       * \n     *     \n *       * ", " *       * \n *       * \n *       * "};
		String[] graphicDieFaces = {"/#1.jpg", "/#2.png", "/#3.jpg", "/#4.jpeg", "/#5.jpeg", "/#6.jpg"};

		// Setup Panel Actions
		chooseNum.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// When the button is pressed, the setVisible value of the panels are switched from true to false or vice versa.
				gamePanel.setVisible(!gamePanel.isVisible());
				// Depending on selection in dropdown. display that many dice. start them all on 6
				diceRoll.removeAll();
				String selection = (String) nums.getSelectedItem();
				String designSelection = (String) designs.getSelectedItem();
				for(int i = 0; i < Integer.parseInt(selection); i++) {
					if(designSelection == "Traditional") { // Traditional
						JTextArea newDie = new JTextArea(dieFaces[5]);
						newDie.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
						diceRoll.add(newDie);
					}
					else { // Graphic
						ImageIcon originalImage = new ImageIcon(getClass().getResource(graphicDieFaces[5]));
						ImageIcon scaledImage = new ImageIcon(originalImage.getImage()
							.getScaledInstance(50,50, Image.SCALE_SMOOTH));
						JLabel picLabel = new JLabel(scaledImage);
						picLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
						diceRoll.add(picLabel);

					}
				}
				diceRoll.setVisible(!diceRoll.isVisible());
				setupPanel.setVisible(!setupPanel.isVisible());
			}
		});
		// Dice Panel Actions
		rollDice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// have 6 different strings(die numbers) stored in an array.
				// Generate a random int (1-6). access corresponding random int location in array, print string to text area
				diceRoll.setVisible(!diceRoll.isVisible());
				diceRoll.removeAll();
				String selection = (String) nums.getSelectedItem();
				String designSelection = (String) designs.getSelectedItem();
				for(int i = 0; i < Integer.parseInt(selection); i++) {
					int roll = (int) (Math.random() * 6);
					if(designSelection == "Traditional") { // Traditional
						JTextArea newDie = new JTextArea(dieFaces[roll]);
						newDie.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
						diceRoll.add(newDie);
					}
					else { // Graphic
						ImageIcon originalImage = new ImageIcon(getClass().getResource(graphicDieFaces[roll]));
						ImageIcon scaledImage = new ImageIcon(originalImage.getImage()
							.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
						JLabel picLabel = new JLabel(scaledImage);
						picLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
						diceRoll.add(picLabel);
					}
				}
				diceRoll.setVisible(!diceRoll.isVisible());
			}
		});
		// Back-to-Setup Panel Actions
		backToSetup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				gamePanel.setVisible(!gamePanel.isVisible());
				diceRoll.setVisible(!diceRoll.isVisible());
				setupPanel.setVisible(!setupPanel.isVisible());
			}
		});

		//make sure the JFrame is visible, list each panel created
		guiFrame.getContentPane().add(BorderLayout.CENTER, setupPanel);
		guiFrame.getContentPane().add(BorderLayout.NORTH, diceRoll);
		guiFrame.getContentPane().add(BorderLayout.SOUTH, gamePanel);
		guiFrame.setVisible(true);
	}
}
