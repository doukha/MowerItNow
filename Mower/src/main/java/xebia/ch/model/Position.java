package xebia.ch.model;

public class Position {

	private int x;
	private int y;
	private Direction direction;

	public Position(int pX, int pY, Direction pDirection) {
		setX(pX);
		setY(pY);
		setDirection(pDirection);
	}

	public Position(Position position) {
		this(position.getX(), position.getY(), position.getDirection());
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction d) {
		this.direction = d;
	}

	/**
	 */
	@Override
	public int hashCode() {
		int hashCode = 1;
		hashCode = hashCode * 19 + x;
		hashCode = hashCode * 15 + y;
		hashCode = hashCode * 6 + (direction == null ? 0 : direction.hashCode());
		return hashCode;
	}

	/*
	 * The positions are equal if they have the same coordinate. (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof Position))
			return false;
		Position comparedPosition = (Position) other;
		return (comparedPosition.getX() == this.getX() && comparedPosition.getY() == this.getY());
	}

	@Override
	public String toString() {
		return "X :" + getX() + ", Y :" + getY() + ", Direction :" + getDirection();
	}
}
