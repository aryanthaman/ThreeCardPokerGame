import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckDealerTest {

	Dealer D = new Dealer();
	Deck  theDeck = new Deck();

	@Test //Check Instance of Class Deck
	void CheckClassName()
	{
		assertEquals("Deck", theDeck.getClass().getName(), "Wrong Instance Of Class Deck");
	}

	@Test //Check Instance of Class Dealer
	void CheckClassName2()
	{
		assertEquals("Dealer", D.getClass().getName(), "Wrong Instance Of Class Dealer");
	}

	@Test // check size after constructor
	void checkSizeTest()
	{
		assertEquals(52, theDeck.size(), "Wrong Size Deck");
	}

	@Test // check size after newDeck
	void checkSize2Test()
	{
		theDeck.newDeck();
		assertEquals(52, theDeck.size(), "Wrong Size Deck");
	}

	@Test
	void CheckIsCard(){ // check if dealHand is an ArrayList of Card instances
		D.setDealersHand(D.dealHand());
		System.out.println(D.getDealersHand().get(0).getClass().getName());
		assertEquals("Card", D.getDealersHand().get(0).getClass().getName());
	}

	@Test // check if dealer's theDeck is actually an instance of class Deck
	void CheckDealersDeck(){
		assertEquals("Deck", D.getTheDeck().getClass().getName());
	}

	@Test
	void dealHandSize(){ // check size of theDeck after dealing cards to a player.
		Player P = new Player();
		P.setHand(D.dealHand());
		assertEquals(49, D.getTheDeck().size(), "Wrong size after dealing cards");
	}

	@Test // check size of theDeck before and after the 34 limit is reached.
	void dealHandSize2(){
		Player P = new Player();
		P.setHand(D.dealHand());
		P.setHand(D.dealHand());
		P.setHand(D.dealHand());
		P.setHand(D.dealHand());
		P.setHand(D.dealHand());
		P.setHand(D.dealHand());
		assertEquals(34, D.getTheDeck().size(), "Wrong size after dealing cards");
		P.setHand(D.dealHand());
		assertEquals(49, D.getTheDeck().size(), "Wrong Size after calling newDeck()");
	}

	@Test // check if multiples of a single card exists.
	void NoMultiples(){
		Card c = new Card ('C', 2);
		ArrayList<Integer> IndexList = new ArrayList<>();
		for(int i=0; i<D.getTheDeck().size(); i++){
			if(D.getTheDeck().get(i).getSuit() == c.getSuit() && D.getTheDeck().get(i).getValue() == c.getValue()){
				IndexList.add(i);
			}
		}
		assertEquals(1, IndexList.size(), "Multiples of C,2 Exist in List");
	}

	@Test // check if all cards match a standard deck of cards with suit C, D, S, H and values from 2 to 14.
	void AllCardsExists(){
		ArrayList<Card> myDeck = new ArrayList<>();

		for(int i = 2; i<15; i++){
			Card c = new Card('C', i);
			Card d = new Card('D', i);
			Card h = new Card('H', i);
			Card s = new Card('S', i);
			myDeck.add(c);
			myDeck.add(d);
			myDeck.add(h);
			myDeck.add(s);
		}

		int counter = 0;
		for(int i = 0; i < myDeck.size(); i++){
			for(int j=0; j < D.getTheDeck().size(); j++){
				if(D.getTheDeck().get(j).getSuit() == myDeck.get(i).getSuit() && D.getTheDeck().get(j).getValue() == myDeck.get(i).getValue()){
					counter++;
				}
			}
		}
		assertEquals(52, counter, "Some Cards are wrongly added o the Deck");
	}

}