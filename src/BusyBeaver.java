import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.imgscalr.Scalr;
import static org.imgscalr.Scalr.*;

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
	
	private LinkedList<Integer> tape = new LinkedList<Integer>();
	
	private ArrayList<State> stateList;
	
	BufferedImage beaver = null;
	BufferedImage zero = null;
	BufferedImage one = null;
	
	public BusyBeaver(Dimension d) {
    	//sets the size of the panel
    	setSize(d);
    	startProgramMessages();
    	numStates = Integer.parseInt(strStates);
		stateList = askDefineStates();
		for(int i = 0; i < 20; i++) {
			tape.add(0);
		}
		loadImages();
	}
	
	/*
	 * Take model and repaint view (MVC)
	 */
	 @Override
	 public void paintComponent(Graphics g) {
		 Graphics2D g2d = (Graphics2D) g;
		 super.paintComponent(g2d);

 		g2d.drawImage(beaver, null, getWidth()/2 - beaver.getWidth() / 2, getHeight()/2 - beaver.getHeight()/2);
 		g2d.fillRect(0, getHeight()/2, getWidth(), beaver.getHeight()/5);
 		for(int i = 0; i < 10; i++) {
 			if(tape.get(i) == 0) {
 				g2d.drawImage(zero, null, getWidth()/2 - beaver.getWidth()/2, getHeight()/2);
 			} else {
 				g2d.drawImage(one, null, getWidth()/2 - beaver.getWidth()/2, getHeight()/2);
 			}
 		}
	 }
	 
	 /*
	  * LoadImages! So paintComponent() doesn't have to do it
	  */
	 private void loadImages() {
		 try {
	 		    beaver = ImageIO.read(new File("beaver.png"));
	 		} catch (IOException e) {
	 			System.out.println("Error loading image");
	 		}
	 		try {
	 		    zero = ImageIO.read(new File("zero.png"));
	 		    zero = Scalr.resize(zero, beaver.getWidth()/5, beaver.getHeight()/5);
	 		} catch (IOException e) {
	 			System.out.println("Error loading image");
	 		}
	 		try {
	 		    one = ImageIO.read(new File("one.png"));
	 		    one = Scalr.resize(one, beaver.getWidth()/5, beaver.getHeight()/5);
	 		} catch (IOException e) {
	 			System.out.println("Error loading image");
	 		}
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
