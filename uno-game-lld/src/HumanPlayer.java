import java.util.Scanner;

public class HumanPlayer extends Players{

    private static Scanner scanner;

    public HumanPlayer() {
        super();
        scanner = new Scanner(System.in);
    }

    private void movingTime() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private Card discardCardFromHand(String card) {

        Card played = null;

        // Find the card the user wants to play
        for (int i = 0; i < cardsInHand.size(); i++) {
            if (cardsInHand.get(i).displayCard().equals(card)) {
                played = cardsInHand.remove(i);
                break;
            }
        }
        return played;
    }

    private String drawCardFromPlayingDeck(PlayingDeck playingDeck, String move) {

        System.out.println("Drawing card from deck...");

        movingTime();

        Card drawn = playingDeck.drawCardFromDeck();
        drawn.flipCard();
        addCardsToHand(drawn);

        move = drawn.displayCard();
        System.out.print("You drew a " + drawn + " card, ");

        return move;
    }


    private Card pickCard(PlayingDeck playingDeck, String move) {
        if (move.equals("help")) {

            gameTutorial();
            System.out.println("The cards in your hand are: " + displayHand());

            System.out.print("Make a move: ");
            move = scanner.nextLine();
            pickCard(playingDeck, move);
        }
        else if (move.equals("draw")) {
            move = drawCardFromPlayingDeck(playingDeck, move);
        }

        Card card = discardCardFromHand(move);
        return card;
    }

    private void changeSpecialCardColor(DiscardDeck discardDeck) {

        System.out.println("Choose color you like to change into: ");
        System.out.println("(r) Red, (b) Blue, (g) Green, (y) Yellow");

        String color = scanner.nextLine();
        Card lastPlayedCard = discardDeck.getTopOfDiscardDeck();

        movingTime();
        System.out.print("Changing the color into ");

        switch (color.charAt(0)) {
            case 'r':
                System.out.println("\033[31;1mred\033[0m");
                break;
            case 'b':
                System.out.println("\033[34;1mblue\033[0m");
                break;
            case 'g':
                System.out.println("\033[32;1mgreen\033[0m");
                break;
            case 'y':
                System.out.println("\033[33;1myellow\033[0m");
                break;
        }
        lastPlayedCard.changeColor(color);
    }

    @Override
    public Card playCard(PlayingDeck playingDeck, DiscardDeck discardDeck) {

        Card playedCard = null;
        String move = "";

        do {
            move = scanner.nextLine();

            playedCard = this.pickCard(playingDeck, move);
            if (playedCard == null) {
                System.out.println("You don't have that card");
            }
            else if (!isValidPlay(discardDeck, playedCard)) {
                System.out.println("You can't play that card");
            }
            else {
                movingTime();
                // If User plays a card that can change color
                if (discardDeck.getTopOfDiscardDeck().getValue() > 12) {
                    changeSpecialCardColor(discardDeck);
                }
                break; // end loop and return playedCard now
            }

            // otherwise print a try again prompt
            if (!move.equals("draw"))
                System.out.print("Play a different Card: ");
            else // or return nothing which means the user had no playable cards
                return null;
        }
        while (!move.equals("draw"));

        return playedCard;
    }

    @Override
    public boolean isHuman() {
        return true;
    }

    @Override
    public void gameOverAction() {
        scanner.close();
    }

    @Override
    public String displayHand() {
        String output = "";
        for (Card card : cardsInHand) {
            output += card;
            output += " ";
        }
        return output;
    }


    private static void gameTutorial() {
        System.out.println("\n--------------------------- Welcome to UNO! ---------------------------\n" +
                "Before we begin, let's go through a quick tutorial to get you started.\n" +
                "\n" +
                "To play a card, simply type the card you want to play. For example, if you have a blue 5, type 'b5' to play it.\n" +
                "\n" +
                "If you don't have a playable card in your hand, you can draw from the deck by typing 'draw'.\n" +
                "\n" +
                "Keep an eye on the top card in the discard pile. You can only play a card if it matches either the color or the number of the top card.\n" +
                "\n" +
                "Remember, special action cards can change the game. A 'skip' card skips the next player's turn, a 'reverse' card changes the direction of play, and a 'draw two' card makes the next player draw two cards.\n" +
                "\n" +
                "Pay attention to the color-changing wild cards! You can play a wild card on any color, and a wild draw four card forces the next player to draw four cards.\n" +
                "\n" +
                "Don't worry if you need a refresher during the game. Just type 'help' to see this tutorial again.\n" +
                "\n" +
                "Are you ready to have some fun? Let's start playing UNO!\n" +
                "------------------------- Thank You ---------------------------\n");
    }


}
