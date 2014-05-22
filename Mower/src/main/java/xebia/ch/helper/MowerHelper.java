package xebia.ch.helper;


import org.apache.log4j.Logger;

import xebia.ch.model.Direction;
import xebia.ch.model.INC_TYPE;
import xebia.ch.model.Incident;
import xebia.ch.model.Instruction;
import xebia.ch.model.Position;

/**
 * Contains static methods, helping @Mower to execute its methods..
 * 
 * @author samyboukhris
 * 
 */
public class MowerHelper {

	private static final Logger LOGGER = Logger.getLogger(MowerHelper.class.getName());

	/**
	 * Compute a new direction from a quarterTurn and an instruction.
	 * 
	 * 
	 * @param quarterTurn
	 *            1 (90°), 2 (180°), 3 (270°).
	 * @param instruction
	 *            G,D,A.
	 * @param direction
	 *            N,W,E,S.
	 * @return
	 */
	public static Direction computeDirection(int quarterTurn, Instruction instruction, Direction direction) {

		switch (quarterTurn % 4) {
		case 1:
			Direction computedDirection = (instruction.equals(Instruction.G) ? rotateQuarterToTheLeft(direction)
					: rotateQuarterToTheRight(direction));
			return computedDirection;
		case 2:
			// To implement : Rotate twice
			break;
		case 3:
			// To implement : Rotate three time
			break;
		case 4:
			return direction;
		default:
			LOGGER.warn("the number " + quarterTurn + " isn't correct ");
		}
		return direction;
	}

	/**
	 * Rotate to the left.
	 * 
	 * @param direction
	 * @return new direction.
	 */
	private static Direction rotateQuarterToTheLeft(Direction direction) {
		if (direction.equals(Direction.NORTH)) {
			return Direction.WEST;
		} else if (direction.equals(Direction.EAST)) {
			return Direction.NORTH;
		} else if (direction.equals(Direction.WEST)) {
			return Direction.SOUTH;
		} else if (direction.equals(Direction.SOUTH)) {
			return Direction.EAST;
		} else {
			LOGGER.warn("Have you invinted a new direction ?");
			return direction;
		}
	}

	/**
	 * Rotate to the right.
	 * 
	 * @param direction
	 * @return new direction.
	 */
	private static Direction rotateQuarterToTheRight(Direction direction) {
		if (direction.equals(Direction.NORTH)) {
			return Direction.EAST;
		} else if (direction.equals(Direction.EAST)) {
			return Direction.SOUTH;
		} else if (direction.equals(Direction.WEST)) {
			return Direction.NORTH;
		} else if (direction.equals(Direction.SOUTH)) {
			return Direction.WEST;
		} else {
			LOGGER.warn("Have you invinted a new direction ?");
			return direction;
		}
	}

	/**
	 * Update the position and return it.
	 * 
	 * @param step
	 *            number of step.
	 * @param position
	 *            to be cloned.
	 * @return the cloned object with updated position.
	 */
	public static Position updatePosition(int step, final Position position) {
		Position nexPosition = new Position(position);// Copy
		if (nexPosition.getDirection().equals(Direction.NORTH)) {
			nexPosition.setY(nexPosition.getY() + step);
		} else if (nexPosition.getDirection().equals(Direction.EAST)) {
			nexPosition.setX(nexPosition.getX() + step);
		} else if (nexPosition.getDirection().equals(Direction.WEST)) {
			nexPosition.setX(nexPosition.getX() - step);
		} else if (nexPosition.getDirection().equals(Direction.SOUTH)) {
			nexPosition.setY(nexPosition.getY() - step);
		}
		return nexPosition;
	}

	/**
	 * if position is occupied then it's collision otherwise it's a overrun.
	 * 
	 * @param isOccupiedPosition
	 * @return computed incident.
	 */
	public static Incident analyseIncident(boolean isOccupiedPosition) {
		INC_TYPE inc_TYPE = isOccupiedPosition ? INC_TYPE.COLLISION : INC_TYPE.OVERRUN;
		String message = isOccupiedPosition ? "Occupied position, collision risque" : " Overrun ";
		return new Incident(inc_TYPE, message);
	}

}
