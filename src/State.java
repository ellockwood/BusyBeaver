
public class State {
	private int direction;
	private int nextState;
	
	public State(int inDirection, int inNextState){
		direction = inDirection;
		nextState = inNextState;
	}
	public int getDirection(){
		return direction;
	}
	public int getNextState(){
		return nextState;
	}

}
