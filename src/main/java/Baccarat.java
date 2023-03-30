import java.util.Scanner;

public class Baccarat 
{
    public static void main(String[] args)
    {
        boolean interactive = false;
        if(args.length == 1)
        {
            if(args[0].equals("-i") || args[0].equals("--interactive"))
            {
                interactive = true;
            }
        }
        
        Scanner scanner = new Scanner(System.in);
        Shoe shoe = new Shoe(6);
        shoe.shuffle();

        BaccaratHand playerHand = new BaccaratHand();
        BaccaratHand bankerHand = new BaccaratHand();

        boolean cont = false;
        int rounds = 1;
        int playerWins = 0;
        int bankerWins = 0;
        int ties = 0;

        do
        {
            if(interactive)
            {
                System.out.println("Round " + rounds);
            }

            dealHands(playerHand, bankerHand, shoe);
            
            displayHands(playerHand, bankerHand);

            resolvePlayer(playerHand, bankerHand, shoe);

            switch(findWinner(playerHand, bankerHand))
            {
                case 1:
                    System.out.println("Player win!");
                    playerWins++;
                case 2:
                    System.out.println("Tie!");
                    ties++;
                case 3:
                    System.out.println("Banker win!");
                    bankerWins++;
            }

            if(interactive)
            {
                System.out.print("Another round? (y/n): ");
                String response = scanner.nextLine();
                if (response.equals("y") || response.equals("Y"))
                {
                    System.out.print("\n\n");
                    rounds++;
                    cleanHands(playerHand, bankerHand);
                    cont = true;
                }
                else
                {
                    System.out.printf(
                        "\n\n%d rounds played\n%d player wins\n%d Banker wins\n%d ties",
                        rounds,playerWins,bankerWins,ties);
                    cont = false;
                }
            }
        } while (interactive && cont);
        scanner.close();
    }

    public static void dealHands(BaccaratHand playerHand, BaccaratHand bankerHand, Shoe shoe)
    {
        for(int cards = 0; cards < 2; cards++)
        {
            playerHand.add(shoe.deal());
            bankerHand.add(shoe.deal());
        }
    }

    public static void displayHands(BaccaratHand playerHand, BaccaratHand bankerHand)
    {
        System.out.println("Player: " + playerHand.toString() + " = " + playerHand.value());
        System.out.println("Banker: " + bankerHand.toString() + " = " + bankerHand.value());
    }

    public static void resolvePlayer(BaccaratHand playerHand, BaccaratHand bankerHand, Shoe shoe)
    {
        if(playerHand.value() < 6)
        {
            System.out.println("Dealing third card to player...");
            BaccaratCard thirdCard = (BaccaratCard)shoe.deal();
            playerHand.add(thirdCard);
            displayHands(playerHand, bankerHand);
            resolveBanker(playerHand, bankerHand, thirdCard, shoe);
        }
    }

    public static void resolveBanker(BaccaratHand playerHand, BaccaratHand bankerHand, 
        BaccaratCard recentCard, Shoe shoe)
    {
        if(recentCard.value() == 9 || recentCard.value() == 0 || recentCard.value() == 1)
        {
            if(bankerHand.value() <= 2)
            {
                System.out.println("Dealing third card to banker...");
                bankerHand.add(shoe.deal());
                displayHands(playerHand, bankerHand);
            }
        }
        else if(recentCard.value() == 8)
        {
            if(bankerHand.value() <= 2)
            {
                System.out.println("Dealing third card to banker...");
                bankerHand.add(shoe.deal());
                displayHands(playerHand, bankerHand);
            }
        }
        else if(recentCard.value() > 5)
        {
            if(bankerHand.value() <= 6)
            {
                System.out.println("Dealing third card to banker...");
                bankerHand.add(shoe.deal());
                displayHands(playerHand, bankerHand);
            }
        }
        else if(recentCard.value() > 3)
        {
            if(bankerHand.value() <= 5)
            {
                System.out.println("Dealing third card to banker...");
                bankerHand.add(shoe.deal());
                displayHands(playerHand, bankerHand);
            }
        }
        else if(recentCard.value() >= 2)
        {
            if(bankerHand.value() <= 4)
            {
                System.out.println("Dealing third card to banker...");
                bankerHand.add(shoe.deal());
                displayHands(playerHand, bankerHand);
            }
        }
    }

    public static int findWinner(BaccaratHand playerHand, BaccaratHand bankerHand)
    {
        if(playerHand.isNatural() || bankerHand.isNatural())
        {
            if(playerHand.isNatural())
            {
                System.out.println("Player has a Natural");
                if(bankerHand.isNatural())
                {
                    System.out.println("Banker has a Natural");
                    return 2;
                }
                return 1;
            }
            else if(bankerHand.isNatural())
            {
                System.out.println("Banker has a Natural");
                return 3;

            }
        }
        if(playerHand.value() > bankerHand.value())
        {
            return 1;
        }
        else if(playerHand.value() == bankerHand.value())
        {
            return 2;
        }
        else
        {
            return 3;
        }
    }

    public static void cleanHands(BaccaratHand playerHand, BaccaratHand bankerHand)
    {
        playerHand.discard();
        bankerHand.discard();
    }
}

