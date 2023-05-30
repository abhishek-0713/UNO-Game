import java.util.ArrayList;

public abstract class Players {

    protected ArrayList<Card> cardsInHand;

    public Players() {
        cardsInHand = new ArrayList<Card>();
    }

    public void addCardsToHand(ArrayList<Card> distributeCards) {
        for (Card card : distributeCards) {
            card.flipCard();
            cardsInHand.add(card);
        }
    }

    public void addCardsToHand(Card card) {
        cardsInHand.add(card);
    }

    public int cardsOnHand() {
        return cardsInHand.size();
    }

    public boolean isValidPlay(DiscardDeck discardDeck, Card card) {

        if (card.equals(discardDeck.getTopOfDiscardDeck())) {
            String playerType;
            if (isHuman()) {
                playerType = "You played";
            } else {
                playerType = "Played";
            }
            System.out.printf("%s a %s card\n", playerType, card);
            discardDeck.addToDiscardDeck(card);
            return true;
        } else {
            addCardsToHand(card);
            return false;
        }
    }


    public abstract Card playCard(PlayingDeck playingDeck, DiscardDeck discardDeck);

    public abstract String displayHand();

    public abstract void gameOverAction();

    public abstract boolean isHuman();


}

