
/**
 * 
 * @author Drew Morton, Nick McCarty, Scotty Greer
 * 
 * TODO: Manually enter number for die roll
 * TODO: Determine best way to handle the file
 * TODO: Roll dice in spell text
 *
 */

import java.util.Random;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {

	private Random rand = new Random();
	private int dice = 0;
	private String spell = "";

	public static void main(String args[]) // This is the main function
	{
		System.out.println("Hello cruel world!");
		Main main = new Main();
		File file = new File("src/Magic_Table.txt");

		JFrame frame = new JFrame("Wild Magic Table");
		JPanel panel = new JPanel();

		// Roll the Dice
		main.setDieRoll(main.rollDice());
		int x = main.getDieRoll();
		int y = main.checkDice(x);
		main.setSpell(main.scanTable(file, y));

		//System.out.println(x);
		//System.out.println(main.checkDice(x));
		//System.out.println(main.scanTable(file, y));

		main.setFrame(frame, panel, file);

	}

	public int rollDice() {
		int i;
		i = rand.nextInt(100) + 1;
		return i;
	}

	public int getDieRoll() {
		return dice;
	}

	public void setDieRoll(int dice) {
		this.dice = dice;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public int checkDice(int spellNum) {
		if ((spellNum % 2) == 1) // if the value is odd
		{
			spellNum = (spellNum / 2) + 1;
		} else // if the value is even
		{
			spellNum = (spellNum / 2);
		}
		return spellNum;
	}

	public String scanTable(File file, int spellNum) {
		String spell = "";
		String spellList = spellNum + ":";
		try {
			Scanner s = new Scanner(file);
			while (s.hasNext()) {
				if (s.next().equals(spellList)) {
					spell = s.nextLine();
				} else {

				}
			}
			s.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

		spell = spell.substring(1);
		return spell;
	}

	public void setFrame(JFrame frame, JPanel panel, File file) {
		JButton button;
		JLabel rollValue;
		JTextArea spellText;

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		panel.setBackground(Color.orange);
		panel.setOpaque(true);

		rollValue = new JLabel("##");
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 3;
		rollValue.setFont(new Font(rollValue.getName(), Font.PLAIN, 30));
		panel.add(rollValue, c);

		button = new JButton("Wildly Roll");
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		panel.add(button, c);

		spellText = new JTextArea("You're a wizard Harry!");
		spellText.setPreferredSize(new Dimension(300, 100));
		spellText.setLineWrap(true);
		spellText.setWrapStyleWord(true);
		spellText.setEditable(false);
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 3;
		panel.add(spellText, c);

		frame.add(panel);
		frame.pack();
		//frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		button.addActionListener(new ActionListener() {

			int x, y;
			
			@Override
			public void actionPerformed(ActionEvent e) {

				setDieRoll(rollDice());
				x = getDieRoll();
				y = checkDice(x);
				setSpell(scanTable(file, y));

				rollValue.setText("" + getDieRoll());
				spellText.setText(spell);

			}
		});

	}

}