// TODO: Implement the BaccaratCard class in this file
public class BaccaratCard extends Card
{
    public BaccaratCard(Rank r, Suit s)
    {
        super(r, s);
    }
    //test
    public int value()
    {
        if (getRank() == Rank.TEN || getRank() == Rank.JACK || getRank() == Rank.QUEEN || getRank() == Rank.KING)
        {
            return 0;
        }
        else
        {
            return getRank().ordinal() + 1;
        }
    }
}