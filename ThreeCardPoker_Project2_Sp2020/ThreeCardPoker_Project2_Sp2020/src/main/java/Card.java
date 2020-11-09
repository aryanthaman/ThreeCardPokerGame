public class Card {

    private char suit; // 'C' 'D' 'S' 'H'
    private int value; // 2 - 14

    Card(char suit, int value){
        this.suit = suit;
        this.value = value;
    }

    public char getSuit() { return suit; }

    public void setSuit(char suit) { this.suit = suit; }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }
}
