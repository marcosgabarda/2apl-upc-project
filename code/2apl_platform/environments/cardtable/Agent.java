package cardTable;

public class Agent 
{
	protected String _name;

	// 0 player
	// 1 gatekeeper
	// 2 notary
	protected int _type;

	// _position null means agent is not sit at the table 
	protected Integer _position = null;

	public Agent( String name ) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public int getType() {
		return _type;
	}

	public Integer getPosition() {
		return _position;
	}

	public boolean isSit() {
		return (_position != null);
	}

	public String toString() {
		return getName();
	}

}
