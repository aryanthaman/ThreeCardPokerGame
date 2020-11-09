import java.util.ArrayList;
import java.util.Random;

public class Dealer {

    private Deck theDeck;
    private ArrayList<Card> dealersHand; //contains dealer's hand for a game

    Dealer(){
        theDeck = new Deck();
    }

    public ArrayList<Card> dealHand(){

        if(theDeck.size() <= 34){
            theDeck.newDeck();
        }
        ArrayList<Card> tempList = new ArrayList<Card>();
        Random rand = new Random();
        for(int i= 0; i< 3; i++) {
            int index = rand.nextInt(theDeck.size()); //returns 0 to size-1 both inclusive
            tempList.add( theDeck.remove(index) );
        }
        return tempList;

    }

    public Deck getTheDeck() { return theDeck; }

    public void setTheDeck(Deck theDeck) { this.theDeck = theDeck; }

    public ArrayList<Card> getDealersHand() { return dealersHand; }

    public void setDealersHand(ArrayList<Card> dealersHand) { this.dealersHand = dealersHand; }

}
