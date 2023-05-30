import java.util.ArrayList;

public class DiscardDeck {

    private ArrayList<Card> discardDeck;

    public DiscardDeck(PlayingDeck playingDeck) {
        discardDeck = new ArrayList<Card>();
        DiscardPile(playingDeck);
    }

    private void DiscardPile(PlayingDeck playingDeck) {

        discardDeck.add(playingDeck.drawCardFromDeck());
        discardDeck.get(discardDeck.size() - 1).flipCard();

        if (getTopOfDiscardDeck().getValue() > 9) {
            DiscardPile(playingDeck);
        }
    }

    public Card getTopOfDiscardDeck() {
        return discardDeck.get(discardDeck.size() - 1);
    }

    public void addToDiscardDeck(Card card) {
        discardDeck.add(card);
    }

    public void removeTopCard() {
        discardDeck.remove(discardDeck.size() - 1);
    }

    public int discardDeckSize() {
        return discardDeck.size();
    }

}

