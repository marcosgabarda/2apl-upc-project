package cardtable;

// 2APL imports
import apapl.Environment;
import apapl.ExternalActionFailedException;
import apapl.data.*;

// Standard java imports
import java.awt.Point;
import java.util.*;
import javax.swing.SwingUtilities;
import java.awt.Dimension;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Env extends Environment
{
	final protected Table table;
    
	private HashMap<String,Agent> agentmap = new HashMap<String,Agent>();

	private int numberOfPlayers, numberOfNotaries, numberOfGatekeepers, numberOfDealers;

	private Deck deck;

	private Scoreboard sb;
	
	// The default constructor
	public Env()
	{
		super();		
		numberOfPlayers=0;
		numberOfNotaries=0;
		numberOfGatekeepers=0;

		deck = new Deck();
		sb = new Scoreboard();

		//Create the window
		table = new Table();
	}
	
	public Term enterAsNotary(String sAgent) throws ExternalActionFailedException {
	    Agent agent = getAgent(sAgent);
	    writeToLog("Notary sit: " +agent.getName());
	    
	    if(numberOfNotaries>0)
	      throw new ExternalActionFailedException("There is already a notary at the table.");
	    else
	      numberOfNotaries++;
	    
	    agent._type=2;
	    agent._position=-1; /* notary hasn't a real position, but it needs a value in order to be sit at the table */
	    
	    return wrapBoolean(true);
	}
	
	public Term enterAsDealer(String sAgent) throws ExternalActionFailedException {
	    Agent agent = getAgent(sAgent);
	    writeToLog("Dealer sit: " +agent.getName());
	    
	    if(numberOfDealers>0)
	      throw new ExternalActionFailedException("There is already a dealer at the table.");
	    else
	      numberOfDealers++;
	    
	    agent._type=3;
	    agent._position=-1; /* dealer hasn't a real position, but it needs a value in order to be sit at the table */
	    
	    return wrapBoolean(true);
	}

	public Term enterAsGatekeeper(String sAgent) throws ExternalActionFailedException {
	    Agent agent = getAgent(sAgent);
	    writeToLog("Gatekeeper in the system: " +agent.getName());
	    
	    if(numberOfGatekeepers>0)
	      throw new ExternalActionFailedException("There is already a gatekeeper in the system.");
	    else
	      numberOfGatekeepers++;
	      
	    agent._type=1;
	    agent._position=-1; /* gk hasn't a real position, but it needs a value in order to be sit at the table */
	      
	    return wrapBoolean(true);
	}

	// Sit the player at the table
	public Term sit(String sAgent, APLIdent player) throws ExternalActionFailedException
	{
		String player_name = player.toString();
		
		Agent agent = getAgent(player_name);		
		
		// is already sit?
		if(agent.isSit()) 
		{
			writeToLog( "agent already sit" );
			throw new ExternalActionFailedException("Agent \""+agent.getName()+"\" is already sit.");
		}
		
		// are there already 5 players at the table?
		if (getNumberOfPlayers()==5) 
		{
			throw new ExternalActionFailedException("Already 5 players sit at the table.");
		}
		
		// Update the agent his position
		agent._position = getPosition();
		agent._type=0; // is a player

		// Increase number of players
		increaseNumberOfPlayers();
		
		// Add it to the scoreboard
		sb.addPlayer(agent.getName());

		// Add it to the gui
		table.addPlayer(player_name, agent._position, 0, 0);
		validatewindow();
		table.repaint();

		writeToLog("Player sit: " +agent.getName());

		return new APLNum(agent._position);
	}

	public Term chooseDealer(String sAgent) throws ExternalActionFailedException
	{
		if (getNumberOfPlayers()!=5) 
		{
			throw new ExternalActionFailedException("There are not 5 players sit at the table.");
		}

		Random rand = new Random();
		int n = 5;
		int randnum = rand.nextInt(n+1);

		Agent a = getAgent(randnum);

		return new APLIdent(a.getName());
	}

	public Term shuffleDeck(String sAgent) throws ExternalActionFailedException {
		Agent agent = getAgent(sAgent);

		if (agent.getType()!=3) 
		{
			throw new ExternalActionFailedException("Only the dealer can shuffle the deck.");
		}

		deck.shuffle();

		Agent d = getDealer();
		notifyEvent("deckShuffled", deck, d);

		return wrapBoolean(true);
	}

	public Term playCard(String sAgent, APLIdent suit, APLIdent rank) {
	      notifyEvent("cardPlayed", suit, rank);
	      return wrapBoolean(true);
	}

	public Term updateScore(String sAgent, APLIdent player_name, APLNum score) {
	      sb.updateScore(player_name.toString(), score.toInt());
	      notifyEvent("scoreUpdated", player_name, score);
	      return wrapBoolean(true);
	}

	/* Standard functions --------------------------------------*/
	
	private void notifyAgents(APLFunction event, String... receivers) {
		 throwEvent(event, receivers);
	}
	
	private void notifyEvent(String parm1, Deck deck, Agent d)
	{
		LinkedList returnList = new LinkedList();
		List deckList = deck.getDeck();

		String deckStr = new String("[ ");

		for (Iterator<Card> i = deckList.iterator( ); i.hasNext( ); ) {
		  Card card = i.next();
		  Suit suit = card.getSuit();
		  APLIdent id = new APLIdent(suit.getName());		  
		  deckStr += "<" + suit.getName() + ", ";
		  returnList.add(id);
		  Rank rank = card.getRank();
		  id = new APLIdent(rank.getName());
		  deckStr += rank.getName() + ">";
		  returnList.add(id);
		  if(i.hasNext())
		      deckStr += ", ";
		}
		deckStr += " ]";
 
		if (d!=null)
		{
			notifyAgents(new APLFunction(parm1,new APLList(returnList)), d.getName());
			writeToLog("EVENT: "+parm1+" "+deckStr+" "+" to "+d.getName());
		}
	}

	private void notifyEvent(String parm1, APLIdent suit, APLIdent rank) {
		ArrayList<String> targetAgents = new ArrayList<String>();
		for (Agent a : agentmap.values())
		{
			if ((a.isSit() && a.getType()==0) || (a.isSit() && a.getType()==2)) // player or notary
				targetAgents.add(a.getName());
		}

		if (!targetAgents.isEmpty())
		{
			notifyAgents(new APLFunction(parm1,suit,rank),targetAgents.toArray(new String[0]));
			writeToLog("EVENT: "+parm1+"("+suit.toString()+","+rank.toString()+")"+" to "+targetAgents);
		}
	}

	private void notifyEvent(String parm1, APLIdent name, APLNum score) {
		ArrayList<String> targetAgents = new ArrayList<String>();
		for (Agent a : agentmap.values())
		{
			if ((a.isSit() && a.getType()==0)) // player
				targetAgents.add(a.getName());
		}

		if (!targetAgents.isEmpty())
		{
			notifyAgents(new APLFunction(parm1,name,score),targetAgents.toArray(new String[0]));
			writeToLog("EVENT: "+parm1+"("+name.toString()+","+score.toString()+")"+" to "+targetAgents);
		}
	}
	
	// Add an agent to the environment
    public synchronized void addAgent(String sAgent) {
        String sAgentMain = getMainModule(sAgent);
        // Agent not yet in the environment
        if (agentmap.keySet().contains(sAgentMain)) {
            agentmap.put(sAgent,agentmap.get(sAgentMain));  
            writeToLog("linking " + sAgent + "");
        } else{
            final Agent agent = new Agent(sAgentMain);
            //_agents.add(agent);
            agentmap.put(sAgent, agent);
            writeToLog("agent " + agent + " added");
        }                
    }
	
	// Remove the agent from the environment
	public synchronized void removeAgent(String sAgent)
	{
		try 
		{
			//String sAgentMain = getMainModule(sAgent);
			
			Agent a = getAgent(sAgent);			
			agentmap.remove( sAgent );
			
			// there can be several agent
			if (!agentmap.containsValue(a)) {
			    //_agents.remove(a);		
			    //a.reset();
			} 
			
			writeToLog("Agent removed: " + sAgent);
	
			synchronized( this ) 
			{
				notifyAll();
			}
		}
		catch (ExternalActionFailedException e) {}
	}
	
	/* END Standard functions --------------------------------------*/
	
 	/* Helper functions --------------------------------------*/

	// Get the agent from its name
	private synchronized Agent getAgent(String name) throws ExternalActionFailedException
	{    
		Agent a = null;
		//a = agentmap.get(getMainModule(name));
		a = agentmap.get(name);
		if (a==null) throw new ExternalActionFailedException("No such agent: "+name);
		else return a;
		
	}

	// Get the agent from its position
	private synchronized Agent getAgent(int position) throws ExternalActionFailedException
	{    
		Agent a = null;
		Iterator it = agentmap.keySet().iterator();
		while(it.hasNext()) {	
		  Object name = it.next();
		  a = agentmap.get(name);
		  if(a!=null) {
		    if(a.getPosition()!=null) {
		      if(a.getPosition().intValue()==position) {
			return a;
		      }
		    }
		  }
		}
		if (a==null) throw new ExternalActionFailedException("No such agent at position: "+position);

		return a;
	}

	private synchronized Agent getDealer() throws ExternalActionFailedException {
		Agent a = null;
		Iterator it = agentmap.keySet().iterator();
		while(it.hasNext()) {	
		  Object name = it.next();
		  a = agentmap.get(name);
		  if(a!=null) {
		      if(a.getType()==3) {
			return a;
		    }
		  }
		}
		if (a==null) throw new ExternalActionFailedException("There is no dealer at the moment.");

		return a;
	}
	
	private static String getMainModule(String sAgent)
	{
		int dotPos;
		if ((dotPos = sAgent.indexOf('.')) == -1)
			return sAgent;
		else
			return sAgent.substring(0, dotPos);
	}
	
	
	// Redrawing the window is a nightmare, this does some redraw stuff
// 	private void validatewindow()
// 	{
// 		Runnable repaint = new Runnable()
// 		{
// 			public void run()
// 			{
// 				//try {Thread.sleep(500);} catch(Exception e) {}
// 				m_window.doLayout();
// 				
// 				/*if (!m_window.isVisible())
// 				{
// 					m_window.setVisible( true );
// 				}*/
// 			}
// 		};
// 		SwingUtilities.invokeLater(repaint);
// 	}

	
	// Print a message to the console
    public void writeToLog(String message) {
      System.out.println("cardtable: " + message);
      table.writeLog(message);
    }
	
	// helper function to wrap a boolean value inside a ListPar.
	static public APLListVar wrapBoolean( boolean b )
	{
		return new APLList(new APLIdent(b ? "true" : "false"));
	}
	/* END Helper functions --------------------------------------*/


	private int getNumberOfPlayers() {
	  return numberOfPlayers;
	}
	
	private void increaseNumberOfPlayers() {
	  numberOfPlayers++;
	}
	
	private void decreaseNumberOfPlayers() {
	  numberOfPlayers--;
	}
	
	private Integer getPosition() {
	  return new Integer(numberOfPlayers);
	}

	private void validatewindow()
	{
		Runnable repaint = new Runnable()
		{
			public void run()
			{
				//try {Thread.sleep(500);} catch(Exception e) {}
				table.doLayout();
				
				/*if (!m_window.isVisible())
				{
					m_window.setVisible( true );
				}*/
			}
		};
		SwingUtilities.invokeLater(repaint);
	}
}
