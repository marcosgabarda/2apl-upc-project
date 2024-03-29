package cardtable;

public class Agent 
{
	protected String _name;

	// 0 player
	// 1 gatekeeper
	// 2 notary

	/* BUG: add set/get method and remove protected */
	protected int _type;

	/* BUG: _position null means agent is not sit at the table, add set/get method */
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
