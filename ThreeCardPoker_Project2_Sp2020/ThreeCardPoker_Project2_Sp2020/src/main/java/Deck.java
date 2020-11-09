import java.util.ArrayList;
import java.util.	*;

public class Deck extends ArrayList<Card> {

		private void build_Deck ()
		{
			ArrayList<Character> suit = new ArrayList<Character>();
			suit.add('C'); suit.add('D'); suit.add('S'); suit.add('H');

			for(int i = 0; i < suit.size(); i++)
			{
				for(int j = 2; j <= 14; j++)
				{
					char c = suit.get(i);
					this.add(new Card(c, j));
				}
			}
			Collections.shuffle(this);
		}

	Deck(){
		build_Deck();
	}

	public void newDeck()
	{
		this.clear();
		build_Deck();
	}

}
