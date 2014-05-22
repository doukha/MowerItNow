package xebia.ch.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import xebia.ch.model.INC_TYPE;
import xebia.ch.model.Incident;
import xebia.ch.model.Mower;
import xebia.ch.model.Position;

/**
 * Control mowers.
 * 
 * Launch mowers one by one after verifying the start position.
 * 
 * @author samyboukhris
 * 
 */
public class MowerController implements ICallBack {

	private static final Logger LOGGER = Logger.getLogger(MowerController.class.getName());

	private List<Mower> mowers;
	private List<Position> recordedPositions;
	private List<Incident> incidents;

	public MowerController(List<Mower> mowers) {
		this.mowers = mowers;
		recordedPositions = new ArrayList<Position>();
		incidents = new ArrayList<Incident>();
	}

	/**
	 * Cross mowers one by one and performs its instructions.
	 * 
	 * If the mower's start position is occupied by another, the launching is
	 * Canceled.
	 * 
	 */
	public void run() {
		for (Mower currentMower : mowers) {
			// check position
			boolean occupiedPosition = recordedPositions.contains(currentMower.getPosition());
			if (occupiedPosition) {
				LOGGER.warn("The position {" + currentMower.getPosition() + "} of " + currentMower.getName()
						+ " is occupied so it will not be treated");
				incidents.add(new Incident(INC_TYPE.COLLISION, " The position is occupied"));
				continue;
			}
			currentMower.setCallBack(this);
			currentMower.setProhibedPositions(recordedPositions);
			currentMower.performsInstructions();
		}
	}

	/**
	 * @see ICallBack#endTreatment(Position);
	 */
	public void endTreatment(Position lastPosition) {
		recordedPositions.add(lastPosition);
	}

	public boolean hasNoIncident() {
		return incidents.isEmpty();
	}

}
