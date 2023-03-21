// TODO: Implement the BaccaratHand class in the file
public class BaccaratHand extends CardCollection
{
    public BaccaratHand()
    {
        super();
    }

    public String toString()
    {
        String s = "";
        for(int i = 0; i < size(); i++)
        {
            s = s + cards.get(i).toString();
            if(i != size()-1)
            {
                s = s + " ";  
            }
        }

        return s;
    }

    public boolean isNatural()
    {
        if(size() == 2 && (value() == 8 || value() == 9))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public int value() 
    {
        int sum = 0;
        for (Card card: cards) 
        {
            sum += card.value();
            if(sum > 9)
            {
                sum -= 10;
            }
        }
        return sum;
      }

    
}