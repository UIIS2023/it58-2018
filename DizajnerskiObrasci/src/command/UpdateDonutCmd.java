package command;

import drawing_Stevanovic_Jelena_IT58_2018.Donut;

public class UpdateDonutCmd implements Command{

	private Donut oldState;
	private Donut newState;
	private Donut original = new Donut();
	
	public UpdateDonutCmd(Donut oldState, Donut newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone(original);
		oldState = newState.clone(oldState);
		
	}

	@Override
	public void unexecute() {
		oldState = original.clone(oldState);
		
	}

}
