package pinckneyjames.FAGraderMKII;

import javax.swing.JFrame;

public class Runner{
	
	
	public static void main(String[] args)
	{
		Grader grader = new Grader(); //Instantiate our class
		grader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		grader.main(); //Run our main class
	}
}
