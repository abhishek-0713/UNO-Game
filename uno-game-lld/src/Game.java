import java.util.ArrayList;

public class Game {

    private PlayingDeck playingDeck;
    private DiscardDeck discardDeck;
    private Players[] players;

    private boolean clockwisePlay;
    private boolean isGameOver;


    public Game(int Players, int NumberOfCards) {

        playingDeck = new PlayingDeck();

        createPlayersList(Players);
        distributeDeckToPlayers(NumberOfCards);
        discardDeck = new DiscardDeck(playingDeck);

        clockwisePlay = true;
    }

    private void createPlayersList(int Players) {

        players = new Players[Players + 1];
        players[0] = new HumanPlayer();

        for (int i = 1; i < players.length; i++) {
            players[i] = new ComputerPlayer();
        }
    }


    private void distributeDeckToPlayers(int NumberOfCards) {

        System.out.println("Dealing out playing deck...\n");

        ArrayList<Card> distributeCards;
        for (Players player : players) {
            distributeCards = playingDeck.dealPlayingDeck(NumberOfCards);
            player.addCardsToHand(distributeCards);
        }
    }

    private int nextPlayerIndex(int lastPos) {

        int currentPos = lastPos;

        if (clockwisePlay == true) {   // go right
            if (lastPos + 1 == players.length) {
                currentPos = 0;
            } else {
                currentPos += 1;
            }
        } else {                       // go left
            if (lastPos - 1 < 0) {
                currentPos = players.length - 1;
            } else {
                currentPos -= 1;
            }
        }
        return currentPos;
    }


    private int nextPlayerTurn(int lastPosition, boolean skip) {

        int nextTurn = nextPlayerIndex(lastPosition);

        if (skip == true) {
            System.out.println("\n Player number " + (nextTurn + 1) + " got skipped!");
            return nextPlayerTurn(nextTurn, false);
        }
        else {
            return nextTurn;
        }
    }


    private void logStartOfTurn(int currentPlayerPosition) {

        Players player = players[currentPlayerPosition];

        String playerString;
        playerString = player.isHuman() ? "Your" : "Player " + (currentPlayerPosition + 1) + "'s";
        System.out.printf("It is %s Turn!\n", playerString);
        System.out.printf("The cards in %s hand are: %s\n", playerString.toLowerCase(), player.displayHand());

        Card lastPlayedCard = discardDeck.getTopOfDiscardDeck();
        System.out.println("The top of the Discard Pile is a " + lastPlayedCard + " card");

        if (player.isHuman()) {
            System.out.print("Play a card: ");
        } else {
            System.out.println("Playing a card....");
        }
    }


    private boolean handlePlayedCard(Card cardPlayed, boolean skip, int currentPlayerPosition, Players players) {

        int topCardValue = cardPlayed.getValue();

        if (topCardValue > 9) {                                                  // Special card played.
            skip = specialCardHandler(topCardValue, skip, currentPlayerPosition);
        }

        switch (players.cardsOnHand()) {
            case 1:                                          // if a players has one card, prints UNO! in the terminal.
                System.out.println("UNO!\n");
                int nextPosition = nextPlayerTurn(currentPlayerPosition, skip);
                playGame(nextPosition);
                break;
            case 0:                                         // if a players runs out of cards, the game ends.
                players.gameOverAction();
                System.out.println("players " + (currentPlayerPosition + 1) + " wins!");
                isGameOver = true;
                break;
        }

        return skip;
    }


    private boolean specialCardHandler(int topCardValue, boolean skip, int currentPlayerPosition) {

        int nextPlayer;
        ArrayList<Card> dealtCards;

        switch (topCardValue) {

            case 10:                    // skip card
                skip = true;
                break;
            case 11:                    // reverse card
                clockwisePlay = !clockwisePlay;
                System.out.print("The direction got reversed, we are now playing in ");
                if (clockwisePlay)
                    System.out.println("clockwise");
                else
                    System.out.println("counter-clockwise");

                break;
            case 12:                    // draw two card
                nextPlayer = nextPlayerIndex(currentPlayerPosition);
                dealtCards = playingDeck.dealPlayingDeck(2);
                players[nextPlayer].addCardsToHand(dealtCards);
                skip = true;
                break;
            case 13:                  // wild card, handled by Player Objects
                break;
            case 14:                 // draw 4 wild card
                nextPlayer = nextPlayerIndex(currentPlayerPosition);
                dealtCards = playingDeck.dealPlayingDeck(4);
                players[nextPlayer].addCardsToHand(dealtCards);
                skip = true;
                break;
        }
        return skip;
    }


    private void goToNextPlayer(int currentPlayerPosition, boolean skip) {
        int nextPosition = nextPlayerTurn(currentPlayerPosition, skip);
        System.out.println("\n");
        gameLag();
        playGame(nextPosition);
    }


    private void gameLag() {
        final int milliseconds = 1000;
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void playGame(int currentPlayerPosition) {
        if (isGameOver == false) {
            boolean skip = false;

            logStartOfTurn(currentPlayerPosition);

            Players player = players[currentPlayerPosition];
            Card cardPlayed = player.playCard(playingDeck, discardDeck);

            if (cardPlayed != null) {
                skip = handlePlayedCard(cardPlayed, skip, currentPlayerPosition, player);
            }

            goToNextPlayer(currentPlayerPosition, skip);
        }
    }


}
