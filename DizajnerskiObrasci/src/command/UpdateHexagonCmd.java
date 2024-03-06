package command;

import drawing_Stevanovic_Jelena_IT58_2018.HexagonAdapter;

public class UpdateHexagonCmd implements Command {

	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter original = new HexagonAdapter();
	
	public UpdateHexagonCmd(HexagonAdapter oldState, HexagonAdapter newState) {
	
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone(original);
		oldState =  newState.clone(oldState);
		
	}

	@Override
	public void unexecute() {
		oldState = original.clone(oldState);
		
	}

}
