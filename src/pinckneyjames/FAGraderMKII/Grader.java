package pinckneyjames.FAGraderMKII;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.*;

//define our J-Variables
@SuppressWarnings("serial")
class Grader extends JFrame{
	public JButton numbutton;
	public JFrame mainFrame;
	public JPanel topPanel;
	public JTable table;
	public JScrollPane scrollPane;
	public String[][] dataValues;
	public JTextField numberField;
	public JLabel questionLabel;
	public JLabel jl2;
	public JLabel infoLabelMaker;
	public JLabel versionNumber;
	double x;
	
	public Grader()
	{
		//Basic constructor
		setTitle("FA - Grader"); // We will change this later
		setSize(600,600);
		setBackground(Color.GRAY);
		setVisible(false);
		//Default number of rows
		x = 9.0;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	/**
	 * This is where all the JTable/Panel info is set and made
	 */
	public void main()
	{
		questionLabel = new JLabel("Enter number of points: (Default is 9)");
		mainFrame = new JFrame("FA - GRADER");
		mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		infoLabelMaker = new JLabel("Made by WHS Coding Club: Development Lead: James Pinckney");
		versionNumber = new JLabel("V. 1.0");
		numbutton = new JButton("Enter");
		numberField = new JTextField("", 8);
		mainFrame.add(questionLabel); //add JLabel
		mainFrame.add(numberField); //add text area
		mainFrame.add(numbutton); //add button
		//jf.pack(); //set window to a recommended size
		dataValues = new String[(int)x+1][2]; //This is our matrix that will become the table
		String columnNames[] = { "Number Correct", "Percentage" }; //table headers
		table = new JTable(dataValues, columnNames); //define our table
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout()); 
		scrollPane = new JScrollPane(table); //give our table a scroll bar
		topPanel.add(scrollPane, BorderLayout.CENTER);
		mainFrame.add(topPanel); //add everything to the JPanel we are working in
		mainFrame.add(infoLabelMaker);
		mainFrame.add(versionNumber);
		for(int i = 0; i <= x; i++) //loop to plug in Number of Questions
		{
			String num = "" + i;
			table.setValueAt(num, i, 0);
		}
		for(int i = 0; i <= x; i++) //loop to calculate percentages
		{
			double divide = i/x;
			double mult = divide*100.0;
			double finalNum = round(mult,2);
			String col2 = "" + finalNum;
			table.setValueAt(col2, i, 1);
		}
		//jf.pack(); //set size again
		mainFrame.setSize(600,600);
		mainFrame.setVisible(true);
		mainFrame.getContentPane().setLayout(new FlowLayout());
		mainFrame.setLocationRelativeTo(null); //set the window to the middle of the screen
		mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		run();
	}
	
	/**
	 * This will round the value we bring in to however many places we want it to.
	 * @param value is the double value we take in
	 * @param places is the number of paces we should round to, currently it is set to 2
	 * @return a double that is our new decimal value
	 */
	public static double round(double value, int places) {
	    if(places < 0) 
	    	throw new IllegalArgumentException(); // Since the places are hard-coded, this is just a safety measure

	    BigDecimal bd = new BigDecimal(value); //Make a new "Big Decimal" object to store our value in
	    bd = bd.setScale(places, RoundingMode.HALF_UP); //Set the BD to "ROUND_UP" to the number of places we told it to
	    return bd.doubleValue(); //Return the BD as a double
	}
	
	/**
	 * This will constantly make the JFrame active
	 */
	public void run()
	{
		while (mainFrame.isVisible()) {
			//jf.pack();
			if (numbutton.getModel().isPressed()) {
				String s = numberField.getText();
				if(!s.equals(""))
				{
					try {
						x = Double.parseDouble(s); //Parse the number put into the box
						mainFrame.dispose();
						main();
						mainFrame.setSize(600,600);
						mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					} catch (NumberFormatException nfe) { //Catch exception in case someone doesn't enter a number
													      //Then display an error window
						JOptionPane.showMessageDialog(null, "\"" + s + "\"" + " is not a number!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Plesae enter a number!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				mainFrame.setTitle("FA - GRADER");
				mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
			
			
		}
	}
}
