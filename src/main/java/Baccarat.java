public class Baccarat 
{
    public static void main(String[] args)
    {
        Shoe shoe = new Shoe(6);
        shoe.shuffle();

        BaccaratHand playerHand = new BaccaratHand();
        BaccaratHand bankerHand = new BaccaratHand();

        for(int cards = 0; cards < 2; cards++)
        {
            playerHand.add(shoe.deal());
            bankerHand.add(shoe.deal());
        }

        System.out.println("Player: " + playerHand.toString() + " = " + playerHand.value());
        System.out.println("Banker: " + bankerHand.toString() + " = " + bankerHand.value());

        if(playerHand.isNatural())
        {
            System.out.println("Player has a Natural\n");
        }
        if(bankerHand.isNatural())
        {
            System.out.println("Banker has a Natural\n");
        }
    }
}
