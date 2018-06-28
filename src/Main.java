/**
 * 
 * @author Drew Morton, Nick McCarty, Scotty Greer
 *
 */

import java.util.Random;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;
import java.awt.BorderLayout;
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
		
		//Roll the Dice
		main.setDieRoll(main.rollDice());
		int x = main.getDieRoll();
		int y = main.checkDice(x);
		main.setSpell(main.scanTable(file, y));
		
		System.out.println(x);
		System.out.println(main.checkDice(x));
		System.out.println(main.scanTable(file, y));
		
		main.setFrame(frame, panel, file);
		
	}
  
	public int rollDice()
	{
		int i;
		i = rand.nextInt(100) + 1;
		return i;
	}
	
	public int getDieRoll()
	{
		return dice;
	}
	
	public void setDieRoll(int dice)
	{
		this.dice = dice;
	}
	
	public String getSpell()
	{
		return spell;
	}
	
	public void setSpell(String spell)
	{
		this.spell = spell;
	}
	
	  public int checkDice(int spellNum)
	  {
	    if ( (spellNum % 2) == 1) // if the value is odd
		  {
		    spellNum = (spellNum / 2) + 1;
		  }
	    else // if the value is even
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
				while(s.hasNext()) {
					if (s.next().equals(spellList)) {
						spell = s.nextLine();
					}
					else {
						
					}
				}
				s.close();
			} catch (Exception e){//Catch exception if any
				System.err.println("Error: " + e.getMessage());
			}
		  
		  spell = spell.substring(1);
		  return spell;
	  }
	  
	  public void setFrame(JFrame frame, JPanel panel, File file)
	  {
	       JButton button = new JButton("Press");
	       JLabel rollValue = new JLabel("",SwingConstants.CENTER);
	       JLabel spellText = new JLabel("",SwingConstants.CENTER);
	       
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	       //rollValue.setText("" + getDieRoll());
	       //spellText.setText(spell);
	       
	       panel.setLayout(new BorderLayout());
	       panel.add(rollValue, BorderLayout.NORTH);
	       panel.add(button, BorderLayout.CENTER);
	       panel.add(spellText, BorderLayout.SOUTH);
	      
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
	       
	       frame.add(panel);
	       //frame.pack();
	       frame.setSize(500, 500);
	       frame.setLocationRelativeTo(null);
	       frame.setVisible(true);
	       
	  }

}