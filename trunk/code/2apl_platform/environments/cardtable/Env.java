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
	private HashMap<String,Agent> agentmap = new HashMap<String,Agent>();

	private int numberOfPlayers, numberOfNotaries, numberOfGatekeepers, numberOfDealers;

	private Deck deck;
	
	// The default constructor
	public Env()
	{
		super();
		// Create the window
		//m_window = new Window( this );
		
		numberOfPlayers=0;
		numberOfNotaries=0;
		numberOfGatekeepers=0;

		deck = new Deck();
	}
	
	public Term enterAsNotary(String sAgent) throws ExternalActionFailedException {
	    Agent agent = getAgent(sAgent);
	    writeToLog("Notary sit: " +agent.getName());
	    
	    if(numberOfNotaries>0)
	      throw new ExternalActionFailedException("There is already a notary at the table.");
	    else
	      numberOfNotaries++;
	    
	    agent._type=2;
	    
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
		
		// Redraw so we can see the agent
		//validatewindow();
		//m_window.repaint();
		
		// We came so far, this means success!
		//agent.signalMoveSucces.emit();

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

		notifyEvent("deckShuffled", deck);

		return wrapBoolean(true);
	}

	/* Standard functions --------------------------------------*/
	
	private void notifyAgents(APLFunction event, String... receivers) {
		 throwEvent(event, receivers);
	}
	
	private void notifyEvent(String parm1, Deck deck) throws ExternalActionFailedException
	{
		Agent d = getDealer();


		LinkedList returnList = new LinkedList();
		// Send an external event to all agents within the senserange.
		List deckList = deck.getDeck();

		for (Iterator<Card> i = deckList.iterator( ); i.hasNext( ); ) {
		  Card card = i.next();
		  Suit suit = card.getSuit();
		  APLIdent id = new APLIdent(suit.getName());		  
		  returnList.add(id);
		  Rank rank = card.getRank();
		  id = new APLIdent(rank.getName());
		  returnList.add(id);
		}
 
		if (d!=null)
		{
			notifyAgents(new APLFunction(parm1,new APLList(returnList)), d.getName());
			writeToLog("EVENT: "+parm1+"("+returnList.toString()+")"+" to "+d.getName());
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
    static public void writeToLog(String message) {
      System.out.println("cardtable: " + message);
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
}
