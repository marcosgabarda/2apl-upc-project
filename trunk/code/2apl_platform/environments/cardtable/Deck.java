package cardtable;

import java.util.*;
import javax.swing.*;
import javax.swing.ImageIcon;

public class Deck {
   private List deck;
   private int index;

   public Deck() {
      deck = new ArrayList();
      index = 0;
		Iterator suitIterator = Suit.VALUES.iterator();
		while ( suitIterator.hasNext() ) {
		    Suit suit = (Suit) suitIterator.next();
		    Iterator rankIterator = Rank.VALUES.iterator();
		    while ( rankIterator.hasNext() ) {
		        Rank rank = (Rank) rankIterator.next();
		        ImageIcon cardImage = new ImageIcon( "cards/" + Card.getFilename( suit, rank ) );
		        Card card = new Card( suit, rank, cardImage );
		        addCard( card );
		    }
		}
   }

   public void addCard( Card card ) {
      deck.add( card );
   }

   public Card dealCard() {
      if ( index >= deck.size() )
         return null;
      else
         return (Card) deck.get( index++ );
   }


   public void shuffle() {
      Collections.shuffle( deck );
      index = 0;
   }

   public boolean isEmpty() {
      if ( index >= deck.size() )
         return true;
      else
         return false;
   }

  public List getDeck() {
      return deck;
   }

}
