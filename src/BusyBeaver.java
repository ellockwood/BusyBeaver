import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class BusyBeaver {
	//changed all booleans to primitive types to conserve memory
	public static int locInTape=4, stateNum=1, countStep=0;
	public static String strStates;
	public static final int RESTTIME = 575;
	
	public static void main(String[] args) {
		startProgramMessages();
		Scanner input = new Scanner(System.in);
		int numStates = Integer.parseInt(strStates); 
		/*if(numStates>3){
			System.out.print("Rest time between steps?(In milliseconds): ");
			RESTTIME = Integer.parseInt(input.nextLine());
		}*/
		int countOne = 0;
		ArrayList<BState> stateList = new ArrayList<BState>();
		System.out.println("Define each state...");
		//changed from 0th index to no specified index
		stateList.add(null);
		//numstates is first input
		for(int i=0; i<numStates; i++){
			System.out.print("Define state " + (i+1) + ":\n0 behavior: ");
			String b0 = new String(input.nextLine());
			System.out.print("1 behavior: ");
			String b1 = new String(input.nextLine());
			stateList.add(new BState(b0, b1));
		}
		System.out.println("DISPLAY SPACE BELOW..."); try{Thread.sleep(250);}catch(Exception e){e.printStackTrace();}
		System.out.println("-----------------------"); try{Thread.sleep(275);}catch(Exception e){e.printStackTrace();}
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
		input.close();
		System.out.println("Your set achieved " + countOne + " \"1's\" in " + countStep + " steps.");
	}
	public static void displayArrow(){
		System.out.println("\t\t \t \t \t \t^");
	}
	public static void displayRefresh(ArrayList<Boolean> tapeList, Boolean displayCount){
		if(locInTape<=3){
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
	public static boolean iterate(ArrayList<Boolean> tapeList, ArrayList<BState> stateList){
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
	private static void startProgramMessages() {
		//Strings to be printed out in the showMessageDialog boxes
		String title = "Busy Beaver!";
		String message1 = "Welcome to the Busy Beaver Game!";
		String message2 = "State 0 is the \"Halt State\"";
        String message3 = "Default rest time between steps is " + RESTTIME + " milliseconds.";
		String message4 = "How many states to define?";
		JOptionPane test = new JOptionPane("Busy Beaver Game", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(test, message1, title, JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(test, message2, title, JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(test, message3, title, JOptionPane.PLAIN_MESSAGE);
		strStates = JOptionPane.showInputDialog(test, message4, title, JOptionPane.QUESTION_MESSAGE);
	}
}
