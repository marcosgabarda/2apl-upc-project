
package cardtable;


import java.util.*;

public class Rank {
   private String name;
   private String symbol;
   private static boolean aceHigh = false;

   /**
    * The rank ace.
    */
   public final static Rank ACE = new Rank( "ace", "a" );
   /**
    * The rank two.
    */
   public final static Rank TWO = new Rank( "two", "2" );
   /**
    * The rank three.
    */
   public final static Rank THREE = new Rank( "three", "3" );
   /**
    * The rank four.
    */
   public final static Rank FOUR = new Rank( "four", "4" );
   /**
    * The rank five.
    */
   public final static Rank FIVE = new Rank( "five", "5" );
   /**
    * The rank six.
    */
   public final static Rank SIX = new Rank( "six", "6" );
   /**
    * The rank seven.
    */
   public final static Rank SEVEN = new Rank( "seven", "7" );
   /**
    * The rank jack.
    */
   public final static Rank JACK = new Rank( "jack", "j" );
   /**
    * The rank queen.
    */
   public final static Rank QUEEN = new Rank( "queen", "q" );
   /**
    * The rank king.
    */
   public final static Rank KING = new Rank( "king", "k" );


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
