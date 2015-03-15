# Work description #

Explain which is the scenario that you want to solve, and which are the issues that make Multiagents a suitable solution

# Briscola Chiamata: Problem description #

Briscola Chiamata is the five-player version of Briscola. Every player is dealt eight cards, so that no cards remain undealt. Then, each player, starting from the dealer's right and proceeding counter-clockwise, bids in an auction to declare how many points they will score. A player may pass, and hence cannot bid again in that game. The bid represents the number of points that player believes he is capable of accumulating. Bidding continues until all but one player have passed in a round. This remaining player has then "won the bid" and therefore gets to declare the Briscola, i.e. the trump suit. The declarer also declares a specific Briscola card (example, the "Ace of Cups" if Cups was the declared Briscola) and the holder of this card is then determined to be the declarer's partner. Logically, the declarer would declare the highest Briscola card he does not already hold in the hopes of creating the strongest combined hand between him and his partner.
The remaining three players are partnered with each other, without their knowledge. Each player, other than the declarer's partner, acts independently, until it is clear which players are partners. Infrequently, the declarer may declare a Briscola card he already holds (if he feels he has a very strong hand), in which case the other four players are partenered against him.
Game strategy is often devised to determine which player is partnered with the declarer, whereas the declarer's partner may devise ruses and decoy strategies to fool the other players, such as not taking a trick, or playing points on a trick that will be won by an opponent.

## Scoring ##

Each player collects tricks as per the regular version of the game, and counts points collected similarly. Partners, which are known by the end of the game, then combine their points. Game points are assigned as follows:

  * if the declarer and partner accumulate card points greater than or equal to the points that were declared after the bidding process

  * the declarer earns two game points
  * the partner earns one game point
  * the other players each lose one game point

  * if the declarer and partner accumulate fewer card points than declared

  * the declarer loses two game points
  * the partner loses one game point
  * the other players each earn one game point

These points are accumulated after every game. The grand winner is the player with the most points at the end of the last match. Note that if the declarer calls a Briscola he holds, then the declarer will win or lose four points, and every other player will win or lose one point.

## Game Phases ##
Game phases :
  * Card giving phase
  * Bidding phase
  * Partner selection phase
  * Game phase
  * Winner/Loser phase
  * Point distribution phase

## What make SMA a suitable solution ##

  * Uncertainity (nobody knows who is actually its partner)
  * Sociality (its needed in order to discover partners/cheat opponents)
  * Cooperation inside teams needed once partners are discovered
  * Competition between teams needed once partners are discovered
  * Trust/Reputation models needed

## Agents tasks ##

An agent is a player

As a player, the Main goal is to win the game (ie: accumulate the amount of points required to win). The subgoal is to win each round.


### Main agent properties ###

  * Autonomy (players are autonomous, they plan alone once there are at least 5 players)
  * Flexibility
    * Reactivity (players has to play when its their turn)
    * Proactivity (players can decide if they want to play even if it's not their turn, players can decide to start cheating other players by sending them messages)
    * Social (players have to interact with each others in order to discover their partners)

### Environment properties ###
  * The environment is basically the table
    * fully accessible (all the players can watch to all the cards on the table)
    * indeterministic (playing cards cannot fail, simplicistic view but can work, but other actions may fail)
    * episodic (if i play a card, then i don't have that card anymore)
    * dynamic (players can play their cards even if it is not its turn)
    * discrete (it's a matter of philosophy ;-)