package xebia.ch.model;

/**
 * 
 * @author samyboukhris
 * 
 *         G rotation to the left.
 * 
 *         D rotation to the right.
 * 
 *         A go forward.
 * 
 */
public enum Instruction {

	G(true), D(true), A(false);

	private final boolean isRotation;

	private Instruction(boolean isRotation) {
		this.isRotation = isRotation;
	}

	public boolean isRotation() {
		return this.isRotation;
	}

}
