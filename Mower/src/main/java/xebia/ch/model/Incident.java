package xebia.ch.model;

public class Incident {

	private INC_TYPE type;
	private String message;

	public Incident(INC_TYPE inc_type, String message) {
		setType(inc_type);
		setMessage(message);
	}

	public INC_TYPE getType() {
		return type;
	}

	public void setType(INC_TYPE type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
