
package cardtable;


import java.util.*;

public class Rank {
   private String name;
   private String symbol;
   private static boolean aceHigh = false;

   /**
    * The rank ace.
    */
   public final static Rank ACE = new Rank( "Ace", "a" );
   /**
    * The rank two.
    */
   public final static Rank TWO = new Rank( "Two", "2" );
   /**
    * The rank three.
    */
   public final static Rank THREE = new Rank( "Three", "3" );
   /**
    * The rank four.
    */
   public final static Rank FOUR = new Rank( "Four", "4" );
   /**
    * The rank five.
    */
   public final static Rank FIVE = new Rank( "Five", "5" );
   /**
    * The rank six.
    */
   public final static Rank SIX = new Rank( "Six", "6" );
   /**
    * The rank seven.
    */
   public final static Rank SEVEN = new Rank( "Seven", "7" );
   /**
    * The rank jack.
    */
   public final static Rank JACK = new Rank( "Jack", "j" );
   /**
    * The rank queen.
    */
   public final static Rank QUEEN = new Rank( "Queen", "q" );
   /**
    * The rank king.
    */
   public final static Rank KING = new Rank( "King", "k" );


   final static java.util.List VALUES =
      Collections.unmodifiableList(
         Arrays.asList( new Rank[] { TWO, THREE, FOUR, FIVE, SIX, SEVEN,
                                     JACK, QUEEN, KING, ACE } ) );


   private Rank( String nameValue, String symbolValue ) {
      name = nameValue;
      symbol = symbolValue;
   }


   public String getName() {
      return name;
   }


   public String toString() {
      return name;
   }


   public String getSymbol() {
      return symbol;
   }


   public int compareTo( Object otherRankObject ) {
      Rank otherRank = (Rank) otherRankObject;
         return VALUES.indexOf( this ) - VALUES.indexOf( otherRank );

   }

}