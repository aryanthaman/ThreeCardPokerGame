import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ThreeCardLogic{

    //Rank checks
    public static boolean isStraightFlush(Card one, Card two, Card three){
        return ( adjacent(one, two, three) &&  sameSuit(one, two, three) );
    }

    public static boolean isThreeOfAKind(Card one, Card two, Card three){
        return sameValue(one, two, three);
    }

    public static boolean isStraight(Card one, Card two, Card three){
        return adjacent(one, two, three);
    }

    public static boolean isFlush(Card one, Card two, Card three){
        return sameSuit(one, two, three);
    }

    public static boolean isPair(Card one, Card two, Card three){
        return ( sameValue(one, two)||sameValue(two, three)||sameValue(three, one) );
    }

    public static boolean adjacent(Card cOne, Card cTwo, Card cThree){

        int diff1 = Math.abs(cOne.getValue() - cTwo.getValue() );
        int diff2 = Math.abs(cTwo.getValue() - cThree.getValue() );
        int diff3 = Math.abs(cThree.getValue() - cOne.getValue() );

        int diffArr[] = {diff1, diff2, diff3};
        int type1[] = {1,1,2};
        int type2[] = {1,2,1};
        int type3[] = {2,1,1};

        if(Arrays.equals(diffArr,type1) || Arrays.equals(diffArr,type2) || Arrays.equals(diffArr,type3)){
            return true;
        }else{
            return false;
        }

    }

    public static boolean sameSuit(Card cOne, Card cTwo, Card cThree){

        if( (cOne.getSuit() == cTwo.getSuit() ) && (cTwo.getSuit()== cThree.getSuit()) ){
            return true;
        }else{
            return false;
        }

    }

    public static boolean sameValue(Card cOne, Card cTwo){

        if( (cOne.getValue() == cTwo.getValue() ) ){
            return true;
        }else{
            return false;
        }

    }

    public static boolean sameValue(Card cOne, Card cTwo, Card cThree){

        if( (cOne.getValue() == cTwo.getValue()) && ( cTwo.getValue() == cThree.getValue()) ){
            return true;
        }else{
            return false;
        }

    }

    /*  • 0 if the hand just has a high card
        • 1 for a straight flush
        • 2 for three of a kind
        • 3 for a straight
        • 4 for a flush
        • 5 for a pair */

    public static int evalHand(ArrayList<Card> hand){

        Card card1 = hand.get(0), card2 = hand.get(1), card3 = hand.get(2);
        if( isStraightFlush( card1, card2, card3)){  return 1; } else
        if(isThreeOfAKind( card1, card2, card3)){  return 2; } else
        if(isStraight( card1, card2, card3)){  return 3; } else
        if(isFlush( card1, card2, card3)){  return 4; } else
        if(isPair( card1, card2, card3)){  return 5; } else {
            return 0;
        }
    }

    /*It will evaluate the hand and then evaluate the winnings and return the amount won. If the
    player lost the Pair Plus bet, it will just return 0.*/
    public static int evalPPWinnings(ArrayList<Card> hand, int bet){
        int handVal = evalHand(hand);
        int PPVal = 0;
        if(handVal == 0){
            return 0;
        }else{
            switch (handVal){
                case 1: PPVal = bet*40 + bet; break;
                case 2: PPVal = bet*30 + bet; break;
                case 3: PPVal = bet*6 + bet; break;
                case 4: PPVal = bet*3 + bet; break;
                case 5: PPVal = bet*1 + bet; break;
            }
        }
        return PPVal;
    }

    /*The method compareHands will compare the two hands passed in and return an
    integer based on which hand won:
        • 0 if neither hand won
        • 1 if the dealer hand won
        • 2 if the player hand won*/
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player){

        int playerVal = evalHand(player);
        int dealerVal = evalHand(dealer);

        if(playerVal == dealerVal ){
            if(playerVal == 0)
                return checkHighCardWinner(dealer, player);
            if(playerVal == 1)
                return checkHighCardWinner(dealer, player);
            if(playerVal == 2)
                return checkHighCardWinner(dealer, player);
            if(playerVal == 3)
                return checkHighCardWinner(dealer, player);
            if(playerVal == 4)
                return checkHighCardWinner(dealer, player);
            if(playerVal == 5)
                return checkHighCardWinner(dealer, player);
        }

        // If Both have different Ranks, but one of them is 0
        if(playerVal == 0)
            playerVal = 6;

        if(dealerVal == 0)
            dealerVal = 6;

        if(dealerVal == playerVal){
            return 0; // neither won
        }else if(dealerVal < playerVal){
            return 1; // dealer won
        }else{
            return 2; // player won
        }

    }

    // Checks which ArrayList has high Value cards.
    static int checkHighCardWinner(ArrayList<Card> dealer, ArrayList<Card> player){

        ArrayList<Integer> dealerVals = new ArrayList<Integer>();
        ArrayList<Integer> playerVals = new ArrayList<Integer>();

        for(int i=0; i<3; i++){
            dealerVals.add(dealer.get(i).getValue() );
            playerVals.add(player.get(i).getValue() );
        }

        Collections.sort(dealerVals);
        Collections.reverse(dealerVals);
        Collections.sort(playerVals);
        Collections.reverse(playerVals);

        for(int i=0; i<3; i++){
            if(dealerVals.get(i) > playerVals.get(i) ){
                return 1;
            }else if(playerVals.get(i) > dealerVals.get(i)){
                return 2;
            }
        }

        //must be neither.
        return 0;
    }

    // returns true if the hand has Q Higher or better.
    static public boolean dHandStatus(ArrayList<Card> hand)
    {
        int res = evalHand(hand);
        if(res != 0 ) return true;
        else
        {
            return hand.get(0).getValue() >= 12 ||
                    hand.get(1).getValue() >= 12 ||
                    hand.get(2).getValue() >= 12;
        }
    }



}

