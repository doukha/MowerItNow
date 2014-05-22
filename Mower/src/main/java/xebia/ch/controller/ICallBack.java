package xebia.ch.controller;

import xebia.ch.model.Position;

public interface ICallBack {
	
	/**
	 * It called by mower at the end of its instructions. So the controller
	 * could save the final position of this one.
	 */
	void endTreatment(Position lastPosition);

}
