import java.util.ArrayList;

public class Player
{
	private ArrayList<Card> hand;
	private int anteBet;
	private int playBet;
	private int pairPlusBet;
	private int totalWinnings;
	public int playFold; // tells us if player wants to play or fold. 1 - play, 2 - fold, 0 - not decided yet

	Player()
	{
		anteBet = 0;
		playBet = 0;
		pairPlusBet = 0;
		totalWinnings = 0;
		playFold = 0;
	}

	//Getters and Setters
	public ArrayList<Card> getHand() { return hand; }

	public void setHand(ArrayList<Card> hand) { this.hand = hand; }

	public int getAnteBet() { return anteBet; }

	public void setAnteBet(int anteBet) { this.anteBet = anteBet; }

	public int getPlayBet() { return playBet; }

	public void setPlayBet(int playBet) { this.playBet = playBet; }

	public int getPairPlusBet() { return pairPlusBet; }

	public void setPairPlusBet(int pairPlusBet) { this.pairPlusBet = pairPlusBet; }

	public int getTotalWinnings() { return totalWinnings; }

	public void setTotalWinnings(int totalWinnings) { this.totalWinnings = totalWinnings; }


}
