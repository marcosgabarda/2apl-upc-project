package cardtable;

import java.util.*;

public class Scoreboard
{
	private HashMap<String,Integer> scoreboard;

	public Scoreboard() {
	  scoreboard =  new HashMap<String,Integer>();
	}

	public void addPlayer(String name) {
	  scoreboard.put(name, new Integer(0));
	}

	public void updateScore(String name, int score) {
	  Integer cur = (Integer) scoreboard.get(name);
	  scoreboard.remove(name);
	  scoreboard.put(name, new Integer(score*cur.intValue()));
	}

	public Integer getScore(String name) {
	  return (Integer) scoreboard.get(name);
	}
}