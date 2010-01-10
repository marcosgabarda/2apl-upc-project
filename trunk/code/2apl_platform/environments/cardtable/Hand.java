package cardtable;

import java.util.*;


public class Hand {

   private java.util.List hand = new ArrayList();

   public void addCard( Card card ) {
      hand.add( card );
   }

   public Card getCard( int index ) {
      return (Card) hand.get( index );
   }


   public Card removeCard( int index ) {
      return (Card) hand.remove( index );
   }


   public void sort() {
      Collections.sort( hand );
   }


   public boolean isEmpty() {
      return hand.isEmpty();
   }


   public boolean containsCard( Card card ) {
      return false;
   }


   public int findCard( Card card ) {
      return hand.indexOf( card );
   }



    public String toString() {
        return hand.toString();
    }

}