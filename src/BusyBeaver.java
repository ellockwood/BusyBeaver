import java.util.Scanner;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BusyBeaver extends JPanel{
	private static final long serialVersionUID = 1L; //Default Serial
	/*
	 * locInTape - Location in the tape where there are 4 spaces below and 4 above?
	 * stateNum  - The stateNumber currently in
	 * countStep - Current count of the step
	 * numStates - Number of states defined
	 */
	private int locInTape=4, stateNum=1, countStep=0, numStates = 0;
	//String representation of number of states to define
	public static String strStates;
	//Final value of rest-time between steps
	public static final int RESTTIME = 575;
	
	public BusyBeaver(Dimension d) {
    	//sets the size of the panel
    	setSize(d);
    	startProgramMessages();
    	numStates = Integer.parseInt(strStates);
    	int countOne = 0;
		ArrayList<BState> stateList = askDefineStates();
		
		ArrayList<Boolean> tapeList = new ArrayList<Boolean>();
		for(int i=0; i<9; i++){
			tapeList.add(false);
		}
		do{
			displayRefresh(tapeList, true);
			countStep++;
		}while(iterate(tapeList, stateList)==true);
		displayRefresh(tapeList, true);
		for(Boolean place : tapeList){
			if(place==true) countOne++;
		}
		System.out.println("Your set achieved " + countOne + " \"1's\" in " + countStep + " steps.");
	}
	
	private void displayArrow(){
		System.out.println("\t\t \t \t \t \t^");
	}
	
	private void displayRefresh(ArrayList<Boolean> tapeList, Boolean displayCount){
		if(locInTape<=3) {
			locInTape++;
			tapeList.add(0, false);
		}
		//System.out.println(tapeList.size());
		if((locInTape+5)==tapeList.size()) tapeList.add(false);
		System.out.print("\t\t");
		for(int i=-4; i<5; i++){
			if(tapeList.get(locInTape+i)==false)
				System.out.print("0\t");
			else System.out.print("1\t");
		}
		if(displayCount) System.out.print("step: " + countStep);
		System.out.println();
		displayArrow();
		try{
			Thread.sleep(RESTTIME);
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }
	
	private boolean iterate(ArrayList<Boolean> tapeList, ArrayList<BState> stateList){
		String inst;
		if(tapeList.get(locInTape)==false) inst = stateList.get(stateNum).getB0();
		else inst = stateList.get(stateNum).getB1();
		if(inst.startsWith("0")&&(tapeList.get(locInTape)==true))
			tapeList.set(locInTape, false);
		else if(inst.startsWith("1")&&(tapeList.get(locInTape)==false))
			tapeList.set(locInTape, true);
		displayRefresh(tapeList, false);
		stateNum = Integer.parseInt(inst.substring(2)); //changing stateNum value every time
		if(Integer.parseInt(inst.substring(2))==0) return false; //halt state, programs stop
		return true;// to be considered: returning card number
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
        String message3 = "Default rest time between steps is " + BusyBeaver.RESTTIME + " milliseconds.";
		String message4 = "How many states to define?";
		JOptionPane test = new JOptionPane("Busy Beaver Game", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(test, message1, title, JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(test, message2, title, JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(test, message3, title, JOptionPane.PLAIN_MESSAGE);
		strStates = JOptionPane.showInputDialog(test, message4, title, JOptionPane.QUESTION_MESSAGE);
	}
	
	/*
	 * Method covers the defining of states by the user
	 */
	private ArrayList<BState> askDefineStates() {
		ArrayList<BState> stateList = new ArrayList<BState>();
		String title = "Busy Beaver!";
		String prompt = "Define each state: ";
		String variable0Input = ""; //String representing the prompt for ith 0 behavior
		String variable1Input = ""; //String representing the prompt for ith 1 behavior
		JOptionPane test = new JOptionPane("Busy Beaver Game", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(test, prompt, title, JOptionPane.PLAIN_MESSAGE);
		
		for(int i=0; i<numStates; i++){
			variable0Input = "Define state: " + i + "'s 0 behavior";
			variable1Input = "Define state: " + i + "'s 1 behavior";
			String b0 = JOptionPane.showInputDialog(test, variable0Input, title, JOptionPane.QUESTION_MESSAGE);
			String b1 = JOptionPane.showInputDialog(test, variable1Input, title, JOptionPane.QUESTION_MESSAGE);
			stateList.add(new BState(b0, b1));
		}
		return stateList;
	}
}
