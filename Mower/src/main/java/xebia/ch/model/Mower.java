package xebia.ch.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import xebia.ch.controller.ICallBack;
import xebia.ch.helper.MowerHelper;

/**
 * 
 * @author samyboukhris
 */
public class Mower {

	private static final Logger LOGGER = Logger.getLogger(Mower.class.getName());

	private String name;
	private Lawn lawn;
	private Position position;
	private List<Instruction> instructions;
	private ICallBack iCallBack;
	private List<Position> prohibedPositions;
	private List<Incident> incidents;

	public Mower(String name, Lawn lawn, Position position, List<Instruction> programmedInstructions) {
		setName(name);
		setLawn(lawn);
		setPosition(position);
		setInstructions(programmedInstructions);
		setIncidents(new ArrayList<Incident>());
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Lawn getLawn() {
		return lawn;
	}

	public void setLawn(Lawn lawn) {
		this.lawn = lawn;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position postion) {
		this.position = postion;
	}

	public void setInstructions(List<Instruction> programmedInstructions) {
		this.instructions = programmedInstructions;
	}

	public void setCallBack(ICallBack callBack) {
		this.iCallBack = callBack;
	}

	public void setProhibedPositions(List<Position> recordedPositions) {
		this.prohibedPositions = recordedPositions;
	}

	private void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	/**
	 * Performs a list of instructions and return the final position.
	 * 
	 * @return the final position.
	 */
	public void performsInstructions() {
		LOGGER.info("*** " + getName() + ": is running ***");

		for (Instruction instruction : this.instructions) {
			performsInstruction(instruction);
		}
		LOGGER.info("*** End of instructions. My position is " + getPosition() + " ***");

		if (iCallBack != null)
			iCallBack.endTreatment(getPosition());
	}

	/**
	 * Check if the next position is free and still inside the lawn before
	 * moving.
	 * 
	 * @param instruction
	 */
	public void performsInstruction(Instruction instruction) {
		if (instruction.isRotation()) {
			rotateTower(instruction);
		} else {
			Position nextPosition = MowerHelper.updatePosition(1, getPosition());
			boolean isInsideLawn = lawn.isContain(nextPosition.getX(), nextPosition.getY());
			boolean isOccupiedPosition = (prohibedPositions == null) ? false : prohibedPositions.contains(nextPosition);

			if (isInsideLawn && !isOccupiedPosition) {
				setPosition(nextPosition);
				LOGGER.info(getName() + ": i'm in postion " + getPosition());
			} else {
				LOGGER.warn(getName() + ": Wrong destination !");
				Incident newIncident = MowerHelper.analyseIncident(isOccupiedPosition);
				getIncidents().add(newIncident);
			}
		}

	}

	private void rotateTower(Instruction instruction) {
		position.setDirection(MowerHelper.computeDirection(1, instruction, position.getDirection()));
		LOGGER.info(getName() + ": my new direction is " + position.getDirection());
	}

}
