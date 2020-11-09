import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

class ThreeCardTest {

	ArrayList<Card> pHand = new ArrayList<Card>();
	ArrayList<Card> dHand = new ArrayList<Card>();

	int bet;

	Card straightFlush1 = new Card('C', 10);
	Card straightFlush2 = new Card('C', 9);
	Card straightFlush3 = new Card('C', 8);

	Card straightFlush4 = new Card('S', 9);
	Card straightFlush5 = new Card('S', 8);
	Card straightFlush6 = new Card('S', 7);

	Card ThreeOfaKind1 = new Card('C', 12);
	Card ThreeOfaKind2 = new Card('H', 12);
	Card ThreeOfaKind3 = new Card('S', 12);

	Card Straight1 = new Card('D', 8);
	Card Straight2 = new Card('C', 7);
	Card Straight3 = new Card('D', 6);

	Card Flush1 = new Card('D', 13);
	Card Flush2 = new Card('D', 9);
	Card Flush3 = new Card('D', 7);

	Card Pair1 = new Card('H', 13);
	Card Pair2 = new Card('C', 13);
	Card Pair3 = new Card('D', 9);

	Card HighCard1 = new Card('S', 9);
	Card HighCard2 = new Card('C', 13);
	Card HighCard3 = new Card('D', 11);

	@Test //1
	void isStraightFlush()
	{
		boolean t = ThreeCardLogic.isStraightFlush(straightFlush1, straightFlush2, straightFlush3);
		assertTrue(t, "Evaluation Failed");
	}

	@Test //2
	void isThreeOfAKind()
	{
		boolean t = ThreeCardLogic.isThreeOfAKind(ThreeOfaKind1, ThreeOfaKind2, ThreeOfaKind3);
		assertTrue(t, "Evaluation Failed");
	}

	@Test //3
	void isStraight()
	{
		boolean t = ThreeCardLogic.isStraight(Straight1, Straight2, Straight3);
		assertTrue(t, "Evaluation Failed");
	}

	@Test //4
	void isFlush()
	{
		boolean t = ThreeCardLogic.isFlush(Flush1, Flush2, Flush3);
		assertTrue(t, "Evaluation Failed");
	}

	@Test //5
	void isPair()
	{
		boolean t = ThreeCardLogic.isPair(Pair1, Pair2, Pair3);
		assertTrue(t, "Evaluation Failed");
	}

	@Test //6
	void adjacent()
	{
		boolean t = ThreeCardLogic.adjacent(straightFlush1, straightFlush2, Flush1 );
		assertFalse( t, "Evaluation Failed");
	}

	@Test //7
	void adjacent2()
	{
		boolean t = ThreeCardLogic.adjacent(Straight1, Straight1, Straight2 );
		assertFalse( t, "Evaluation Failed");
	}

	@Test //8
	void sameSuit()
	{
		boolean t = ThreeCardLogic.sameSuit(Flush1, Flush1, Flush1 );
		assertTrue( t, "Evaluation Failed");
	}

	@Test //9
	void sameValue()
	{
		boolean t = ThreeCardLogic.sameValue(ThreeOfaKind2, ThreeOfaKind3 );
		assertTrue( t, "Evaluation Failed");
	}

	@Test //10
	void testSameValue()
	{
		boolean t = ThreeCardLogic.sameValue(ThreeOfaKind1, ThreeOfaKind2, ThreeOfaKind3 );
		assertTrue( t, "Evaluation Failed");
	}

	@Test //11
	void evalHand1()
	{
		pHand.add(straightFlush1);
		pHand.add(straightFlush2);
		pHand.add(straightFlush3);
		assertEquals(1, ThreeCardLogic.evalHand(pHand), "Evaluation Failed with 1");
	}

	@Test //12
	void evalHand2()
	{
		pHand.add(ThreeOfaKind1);
		pHand.add(ThreeOfaKind2);
		pHand.add(ThreeOfaKind3);
		assertEquals(2, ThreeCardLogic.evalHand(pHand), "Evaluation Failed with 2");
	}

	@Test //13
	void evalHand3()
	{
		pHand.add(Straight1);
		pHand.add(Straight2);
		pHand.add(Straight3);
		assertEquals(3, ThreeCardLogic.evalHand(pHand), "Evaluation Failed with 3");
	}

	@Test //14
	void evalHand4()
	{
		pHand.add(Flush1);
		pHand.add(Flush2);
		pHand.add(Flush3);
		assertEquals(4, ThreeCardLogic.evalHand(pHand), "Evaluation Failed with 4");
	}

	@Test //15
	void evalHand5()
	{
		pHand.add(Pair1);
		pHand.add(Pair2);
		pHand.add(Pair3);
		assertEquals(5, ThreeCardLogic.evalHand(pHand), "Evaluation Failed with 5");
	}

	@Test //16
	void evalHand0()
	{
		pHand.add(HighCard1);
		pHand.add(HighCard2);
		pHand.add(HighCard3);
		assertEquals(0, ThreeCardLogic.evalHand(pHand), "Evaluation Failed with 0");
	}

	@Test //17
	void evalPPWinnings()
	{
		pHand.add(ThreeOfaKind1);
		pHand.add(ThreeOfaKind2);
		pHand.add(ThreeOfaKind3);
		bet = 30;
		assertEquals(930, ThreeCardLogic.evalPPWinnings(pHand, bet), "Evaluation Failed");

	}

	@Test //18
	void compareHands()
	{
		pHand.add(ThreeOfaKind1);
		pHand.add(ThreeOfaKind2);
		pHand.add(ThreeOfaKind3);

		dHand.add(straightFlush1);
		dHand.add(straightFlush2);
		dHand.add(straightFlush3);

		int cHand = ThreeCardLogic.compareHands(dHand, pHand);
		assertEquals(1, cHand, "Evaluation Failed");
	}

	@Test //19
	void checkHighCardWinner()
	{
		pHand.add(straightFlush4);
		pHand.add(straightFlush5);
		pHand.add(straightFlush6);

		dHand.add(straightFlush1);
		dHand.add(straightFlush2);
		dHand.add(straightFlush3);

		int highCard = ThreeCardLogic.checkHighCardWinner(dHand, pHand);
		assertEquals(1, highCard, "Evaluation Failed" );
	}

	@Test//20
	void dHandStatus()
	{
		ArrayList<Card> hand = new ArrayList<>();
		hand.add(ThreeOfaKind1);
		hand.add(Flush2);
		hand.add(Flush2);
		assertTrue(ThreeCardLogic.dHandStatus(hand));
	}

}
