Notes about desing of 2APL Multiagent System.

# Work description #

Design your multiagent system using an Agent-oriented methodology (GAIA, Prometheus or any other existing, of your choice). The diagrams of concepts, roles, agents... shoudl
be part of your documentation. You should also explain in the report your design decisions (e.g why grouping these functionalities or roles in Agent X).

# Introduction #

The Prometheus Design Tool is a graphical editor which supports the design tasks specified within the Prometheus methodology for designing agent systems. The Prometheus methodology involves 3 phases: System Specification, High-level (Architectural) Design and Detailed Design.invited to leave the board (table/system) if they want

The PDT software can be downloaded from [here](http://www.cs.rmit.edu.au/agents/pdt/pdt.shtml). To launch it: java -jar PDT\_3\_3.jar

Description of problem can be found [here](A_ProblemDescription.md).

## System Specification ##

In this phase, actors (human or software) expected to interact with the system, are identified, along with the interface to the system in terms of actions and percepts; system goals are elaborated, and scenarios described in terms of sequences of steps are developed. Roles encompassing small chunks of functionality (identified by goals, percepts and actions) are described and captured.

### Analisys Overview diagram ###

  * Specify key external actors that will use the system, and key scenarios they will be involved in.
  * Link the actors to the scenarios they are involved in, using percepts and actions. Percepts are signals from actor to system. Actions are responses from the system, possibly directed towards a particular actor. This diagram can be used very freely to sketch a design of the system.

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/system_specification/analysis_overview.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/system_specification/analysis_overview.png)

#### Actors ####

  * Player
  * (Card Dealer) not really needed as an actor

#### Inputs/outputs (percepts/actions) ####

| **Input/Percept** | **Output/Action** |
|:------------------|:------------------|
| Join request | Subscribe user and manage request (the request has been accepted or queued) |
| 5 players in the system | Choose the card dealer and start shuffling and dealing cards to the players |
| Dealing is over | Start bidding in turn |
| Bidding is over | Declare the Briscola card |
| Briscola declared | Start playing in turn |
| Card played | Update internal beliefs about teams setting |
| Message from other players (Sign exchange) | Update internal beliefs about teams setting |
| The hand is over | Count points |
| The game is over | Declare the winner |
| Winner declared | Update the system scoring |
| System scoring updated  | Release the agents from the system |

#### Scenarios ####

  1. **Start the game scenario**: join requests are received. Two options are possible: the request is accepted or queued. A message is then sent back acknowledging the request.
  1. **Dealing scenario**: if there are 5 players subscribed in the system, then a card dealer is chosen that shuffle the deck and deal the cards to all the players.
  1. **Bidding scenario**: once the dealing is over, players can start bidding in turn. The player who wins the bid, must declare the Briscola card. If nobody bids, then the game has to be restarted.
  1. **Play the Game scenario**: once the Briscola card has been declared, the players start playing. The teams setting is unknown to all the players until the Briscola card is played. The players have to update their beliefs about teams setting each time a card is played. Of course, cheating is part of the game. Communication (sign exchange) among players is allowed to exchange information about players and cards. After each hand, points are counted.
  1. **End the game scenario**: once the winner team is declared, scoring is updated and players are released from the system.

### Scenarios Diagram ###

A scenarios diagram represents the different scenarios that can exist in the system.

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/system_specification/scenarios.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/system_specification/scenarios.png)

  1. **Start the game scenario**
    1. Register players and inform them to wait if there are less than 5 players registered
    1. Queue players if there are already 5 players registered
  1. **Dealing scenario**
    1. Choose the card dealer
    1. Shuffle the deck
    1. Deal cards
  1. **Bidding scenario**
    1. Start bidding in turn until the highest bid is established
    1. Declare the Briscola card
  1. **Play the Game scenario**
    1. Play cards in turn
    1. Collect points for each hand
  1. **End the game scenario**
    1. Count points
    1. Declare the winner
    1. Release the players

### Goals Overview Diagram ###

The goal overview diagram is a directed acyclic graph of all goals in the system.                                           Sub-goals can be generated by asking "how will the system accomplish this goal" Parent goals can be generated by asking "why does the system accomplish this goal".

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/system_specification/goal_overview.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/system_specification/goal_overview.png)

  * Start the game
    * Manage join request
    * Wait for 5 players in the system
  * Play the game
    * Dealing
      * Identify the card dealer
      * Shuffling cards
      * Dealing cards to players
    * Bidding
      * Identify the highest bid
      * Declare the Briscola card
    * Turn Selection
    * Play cards
      * Identify best card to play for the turn
      * Cheat Opponents
      * Identifying teams
        * Obtain reputation
        * Calculate trust
    * Count point for the hand
  * End the Game
    * Declare the winner
    * Update the scoring
    * Release players from the system

### System Roles Diagram ###

In a system roles diagram, we group different goals, percepts and actions under roles.
This helps in further modularizing the system. The input is represented as a percept
and the output as an action.

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/system_specification/system_roles.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/system_specification/system_roles.png)


## High-level (Architectural) Design ##

In this phase, the agent types that will exist in the system are defined by combining roles, the overall structure of the system is described using a system overview diagram, and interaction protocols are used to capture the dynamics of the system in terms of legal message sequences.

### Data Coupling Diagram ###

The roles that were formed in the last step of the previous phase are linked to data that
has been identified as necessary for performing that role. In the data-coupling diagram you can see all roles and data types in the system.

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/architectural_design/data_coupling.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/architectural_design/data_coupling.png)

### Agent-Role Grouping Diagram ###

In this diagram we group the roles into agent types. Decisions regarding how to group
depend on role similarity, as well as analysis of data usage. The agent-role coupling diagram shows the group of roles that come under an agent.

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/architectural_design/aget-role_grouping.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/architectural_design/aget-role_grouping.png)


### Agent Acquaintance Diagram ###

In the agent acquaintance diagram you can see all agents within the system and which
agents interact

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/architectural_design/agent_acquaintance.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/architectural_design/agent_acquaintance.png)


### System Overview Diagram ###

In the system overview diagram you can see all agents in the system, along with their
interface and interactions. This diagram is the central diagram of the entire system design.

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/architectural_design/system_overview.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/architectural_design/system_overview.png)

## Detailed design **OLD! To be updated** ##

In this phase, the internals of each agent are developed in terms of capabilities, events, plans and data. Detailed Design is done at the level of individual agents.

### Agent Overview Diagram ###

The agent overview diagram shows the internals of an agent. There is one agent overview diagram for every agent in the system. In the detailed design, much finer details of the system are established. The messages used to communicate between the agents are decided upon. If the roles within an agent grow very large, they are grouped into capabilities. As in the system overview diagram entities can be inserted or removed and edges can be reated or deleted.

**Dealer**

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/dealer_overview_diagram.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/dealer_overview_diagram.png)

**Non-Player Character**

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/non-player_character_overview_diagram.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/non-player_character_overview_diagram.png)


### Capability Overview Diagram ###

In the capability overview diagram you can see the direct internal of a capability. This diagram allows you to specify the internals of a capability in terms of plans, or sub-capabilities and messages between them. Data internal to the capability can also be specified.

**Dealer**

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/dealing_capability_overview_diagram.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/dealing_capability_overview_diagram.png)

**Non-Player Character**

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/start_the_game_capability_overview_diagram.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/start_the_game_capability_overview_diagram.png)

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/receive_a_hand_capability_overview_diagram.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/receive_a_hand_capability_overview_diagram.png)

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/bidding_capability_overview_diagram.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/bidding_capability_overview_diagram.png)

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/declare_briscola_capability_overview_diagram.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/declare_briscola_capability_overview_diagram.png)

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/playing_capability_overview_diagram.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/playing_capability_overview_diagram.png)

![http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/end_the_game_capability_overview_diagram.png](http://2apl-upc-project.googlecode.com/svn/trunk/docs/briscola_chiamata/pdt/images/detailed_design/end_the_game_capability_overview_diagram.png)