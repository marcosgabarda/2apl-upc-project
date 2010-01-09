package cardtable;

import java.util.*;


public final class Suit {
   private String name;
   private String symbol;

   /**
    * The suit clubs.
    */
   public final static Suit CLUBS = new Suit( "clubs", "c" );
   /**
    * The suit diamonds.
    */
   public final static Suit DIAMONDS = new Suit( "diamonds", "d" );
   /**
    * The suit hearts.
    */
   public final static Suit HEARTS = new Suit( "hearts", "h" );
   /**
    * The suit spades.
    */
   public final static Suit SPADES = new Suit( "spades", "s" );


   public final static java.util.List VALUES =
      Collections.unmodifiableList(
         Arrays.asList( new Suit[] { CLUBS, DIAMONDS, HEARTS, SPADES } ) );


   private Suit( String nameValue, String symbolValue ) {
      name = nameValue;
      symbol = symbolValue;
   }

   public String getName() {
       return name;
   }

   public String getSymbol() {
      return symbol;
   }

   public String toString() {
      return name;
   }



   public int compareTo( Object otherSuitObject ) {
      Suit otherSuit = (Suit) otherSuitObject;
      return VALUES.indexOf( this ) - VALUES.indexOf( otherSuit );
   }

}

