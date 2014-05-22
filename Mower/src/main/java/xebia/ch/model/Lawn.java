package xebia.ch.model;

/**
 * 
 * @author samyboukhris
 * 
 */
public class Lawn {

	private final static int startX = 0;
	private final static int startY = 0;
	private int length;
	private int width;

	public Lawn(int length, int width) {
		setLength(length);
		setWidth(width);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Tell if the coordinate is inside the frame.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isContain(int x, int y) {
		return (x <= length) && (x >= startX) && (y <= width) && (y >= startY);
	}

}
