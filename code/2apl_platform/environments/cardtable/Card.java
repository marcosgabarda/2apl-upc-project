package cardtable;

import javax.swing.ImageIcon;

public class Card {

   private Suit suitValue;
   private Rank rankValue;
   private ImageIcon cardImage;




   public Card( Suit suit, Rank rank, ImageIcon cardFace ) {
      cardImage = cardFace;
      suitValue = suit;
      rankValue = rank;
   }


   public static String getFilename( Suit suit, Rank rank ) {
      return rank.getSymbol() + suit.getSymbol() + ".gif";
   }


   public Suit getSuit() {
      return suitValue;
   }

   public Rank getRank() {
      return rankValue;
   }


   public ImageIcon getCardImage() {
      return cardImage;
   }


   public int compareTo( Card AnotherCard ) {
      Card otherCard = AnotherCard;
      int suitDiff = suitValue.compareTo( otherCard.suitValue );
      int rankDiff = rankValue.compareTo( otherCard.rankValue );


         if ( rankDiff != 0 )
            return rankDiff;
         else
            return suitDiff;

   }



}
