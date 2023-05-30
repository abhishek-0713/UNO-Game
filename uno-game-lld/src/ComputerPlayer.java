import java.util.ArrayList;

public class ComputerPlayer extends Players{

    public ComputerPlayer() {
        super();
    }

    private void think() {
        try {
            int thinkTime = 0;

            // Random time in milliseconds in range of 500 ms to 2500 ms inclusive
            thinkTime = (int) Math.random() * (2500 + 1) + 500;
            Thread.sleep(thinkTime);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private ArrayList<Card> findPlayableCards(DiscardDeck discardDeck) {

        Card lastPlayedCard = discardDeck.getTopOfDiscardDeck();

        ArrayList<Card> playable = new ArrayList<Card>();

        think();

        for (Card card : cardsInHand) {
            if (card.equals(lastPlayedCard)) {
                playable.add(card);
            }
        }

        return playable;
    }


    private void drawCardsFromDeck(PlayingDeck playingDeck, ArrayList<Card> playableCards) {
        think();
        System.out.println("Had no playable card in hand, drawing from deck...");
        Card drawn = playingDeck.drawCardFromDeck();
        drawn.flipCard();
        playableCards.add(drawn);
    }


    private Card pickCardToPlay(String action, ArrayList<Card> playableCards, PlayingDeck playingDeck) {

        if (playableCards.size() == 0) {
            drawCardsFromDeck(playingDeck, playableCards);
            action += "Drew a ";
        }
        Card chosenCard = playableCards.get(0);
        if (action.equals("Drew a ")) {
            System.out.print(action + chosenCard + " card, ");
        }
        return chosenCard;
    }

    private void discardCardFromHand(Card chosenCard) {

        for (Card card : cardsInHand) {
            if (chosenCard.toString().equals(card.toString())) {
                //System.out.println("Playing " + card);
                cardsInHand.remove(card);
                break;
            }
        }

    }

    private void changeSpecialCardColor(DiscardDeck discardDeck){

        char color = 's';
        for (Card card : cardsInHand) {
            if (card.getColor() != 's')
                color = cardsInHand.get(0).getColor();
        }
        Card lastPlayedCard = discardDeck.getTopOfDiscardDeck();

        think();

        System.out.print("Changing the color to ");
        switch (color) {
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

        ArrayList<Card> playableCards = findPlayableCards(discardDeck);
        String action = "";

        Card chosenCard = pickCardToPlay(action, playableCards, playingDeck);
        discardCardFromHand(chosenCard);

        think();

        if (isValidPlay(discardDeck, chosenCard)) {
            // cases where a color changing card is played
            if (discardDeck.getTopOfDiscardDeck().getValue() > 12) {
                changeSpecialCardColor(discardDeck);
            }
        }
        else {
            System.out.println("But couldn't play the " + chosenCard + " card");
            chosenCard = null;
        }
        return chosenCard;
    }

    @Override
    public boolean isHuman() {
        return false;
    }


    @Override
    public String displayHand() {

        String output = "";
        for (Card card : cardsInHand) {
            card.flipCard();
            output += card;
            card.flipCard();
            output += " ";
        }
        return output;
    }

    @Override
    public void gameOverAction() {
        System.out.println("Great Game!!!!");
    }


}
