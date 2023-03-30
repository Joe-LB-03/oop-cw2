import java.util.Collections;

// TODO: Implement the Shoe class in this file
public class Shoe extends CardCollection
{
    public Shoe(int decks) throws CardException
    {
        super();
        if(decks != 6 && decks != 8)
        {
            throw new CardException("Invalid number of decks.");
        }
        else
        {
            for(int i = 0; i < decks; i++)
            {
                for (Card.Suit s : Card.Suit.values()) 
                {
                    for (Card.Rank r : Card.Rank.values()) 
                    {
                        add(new BaccaratCard(r,s));
                    }
                }
            }
        }
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public Card deal() throws CardException
    {
        if(size() ==  0)
        {
            throw new CardException("Attempted to deal from an empty Shoe.");
        }
        else
        {
            Card card = cards.get(0);
            cards.remove(0);
            return card;
        }
    }
}