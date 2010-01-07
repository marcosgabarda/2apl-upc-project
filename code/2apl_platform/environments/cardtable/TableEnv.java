 package cardTable;

// 2APL imports
import apapl.Environment;
import apapl.ExternalActionFailedException;
import apapl.data.*;

// Standard java imports
import java.awt.Point;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.LinkedList;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TableEnv extends Environment
{
	// To hold our reference to the window
	final protected Window m_window;
	
	private HashMap<String,Agent> agentmap = new HashMap<String,Agent>();

	private int numberOfPlayers, numberOfNotaries, numberOfGatekeepers;
	
	// The default constructor
	public TableEnv()
	{
		super();
		// Create the window
		m_window = new Window( this );
		
		numberOfPlayers=0;
		numberOfNotaries=0;
		numberOfGatekeepers=0;
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
		writeToLog("Player sit: " +agent.getName());
		
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
		validatewindow();
		m_window.repaint();
		
		// We came so far, this means success!
		//agent.signalMoveSucces.emit();

		return wrapBoolean(true);
	}
	
	/* Standard functions --------------------------------------*/
	
	private void notifyAgents(APLFunction event, String... receivers) {
//		 throwEvent(event, receivers);
	}
	
	private void notifyEvent(String parm1, Point ptPosition)
	{
// 		APLNum	nX	= new APLNum((double)(ptPosition.getX()));
// 		APLNum	nY	= new APLNum((double)(ptPosition.getY()));
// 
// 		// Send an external event to all agents within the senserange.
// 		ArrayList<String> targetAgents = new ArrayList<String>();
// 		for (Agent a : agentmap.values())
// 		{
// 			// Changed SA: I got no idea why there is always 1 agent which does not exists, 
// 			// but this fixes the exceptions
// 			if ((a.getPosition() != null) && (ptPosition.distance(a.getPosition()) <= getSenseRange()))
// 				targetAgents.add(a.getName());
// 		}
// 
// 		writeToLog("EVENT: "+parm1+"("+nX+","+nY+")"+" to "+targetAgents);
// 
// 		if (!targetAgents.isEmpty())
// 		{
// 			notifyAgents(new APLFunction(parm1,nX,nY),targetAgents.toArray(new String[0]));
// 		}
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
            _agents.add(agent);
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
			    _agents.remove(a);		
			    a.reset();
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
	
	private static String getMainModule(String sAgent)
	{
		int dotPos;
		if ((dotPos = sAgent.indexOf('.')) == -1)
			return sAgent;
		else
			return sAgent.substring(0, dotPos);
	}
	
	
	// Redrawing the window is a nightmare, this does some redraw stuff
	private void validatewindow()
	{
		Runnable repaint = new Runnable()
		{
			public void run()
			{
				//try {Thread.sleep(500);} catch(Exception e) {}
				m_window.doLayout();
				
				/*if (!m_window.isVisible())
				{
					m_window.setVisible( true );
				}*/
			}
		};
		SwingUtilities.invokeLater(repaint);
	}

	
	// Print a message to the console
    static public void writeToLog(String message) {
      System.out.println("card_table: " + message);
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
