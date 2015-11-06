import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BusyBeaver extends JPanel{
	private static final long serialVersionUID = 1L; //Default Serial
	/*
	 * numStates - Number of states defined
	 * curVal    - Current value in the tape
	 */
	private int  numStates = 0;
	private int curVal = 0;
	private int moveDirection = 0;
	//String representation of number of states to define
	public static String strStates;
	
	private ArrayList<State> stateList;
	
	public BusyBeaver(Dimension d) {
    	//sets the size of the panel
    	setSize(d);
    	startProgramMessages();
    	numStates = Integer.parseInt(strStates);
		stateList = askDefineStates();
		
	}
	
	/*
	 * Take model and repaint view (MVC)
	 */
	 @Override
	 public void paintComponent(Graphics g) {
		 super.paintComponent(g);
	 }
	 
	 /*
	  * Recursive solution
	  */
	 @SuppressWarnings("unused")
	private void transition(State b, int valInTape) {
		 curVal = valInTape;
		 moveDirection = b.getDirection();
		 transition(stateList.get(b.getNextState()), curVal);
	 }
	 
    /*
	 * Method covers the introductory messages
	 * and concludes with asking for total number of cards
	 */
	private void startProgramMessages() {
		//Strings to be printed out in the showMessageDialog boxes
		String title = "Busy Beaver!";
		String message1 = "Welcome to the Busy Beaver Game!";
		String message2 = "State 0 is the \"Halt State\"";
		String message3 = "How many states to define?";
		JOptionPane test = new JOptionPane("Busy Beaver Game", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(test, message1, title, JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(test, message2, title, JOptionPane.PLAIN_MESSAGE);
		strStates = JOptionPane.showInputDialog(test, message3, title, JOptionPane.QUESTION_MESSAGE);
	}
	
	/*
	 * Method covers the defining of states by the user
	 */
	private ArrayList<State> askDefineStates() {
		ArrayList<State> stateList = new ArrayList<State>();
		String title = "Busy Beaver!";
		String prompt = "Define each state: ";
		String variable0Input = ""; //String representing the prompt for ith 0 behavior
		String variable1Input = ""; //String representing the prompt for ith 1 behavior
		JOptionPane test = new JOptionPane("Busy Beaver Game", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(test, prompt, title, JOptionPane.PLAIN_MESSAGE);
		
		for(int i=1; i<=numStates; i++){
			variable0Input = "Define state: " + i + "'s 0 behavior";
			variable1Input = "Define state: " + i + "'s 1 behavior";
			String b0 = JOptionPane.showInputDialog(test, variable0Input, title, JOptionPane.QUESTION_MESSAGE);
			String b1 = JOptionPane.showInputDialog(test, variable1Input, title, JOptionPane.QUESTION_MESSAGE);
			stateList.add(new State(Integer.parseInt(b0), Integer.parseInt(b1)));
		}
		return stateList;
	}
}
