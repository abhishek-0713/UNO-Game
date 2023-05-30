import java.util.ArrayList;
import java.util.Collections;

public class PlayingDeck {

    private ArrayList<Card> playingDeck;
    private final char[] colors = { 'b', 'g', 'r', 'y' };


    public PlayingDeck() {

        System.out.println("Unboxing playing deck...");
        unboxingDeck();

        Collections.shuffle(playingDeck);
        System.out.println("Shuffling playing deck...");
    }

    private void unboxingDeck() {

        playingDeck = new ArrayList<Card>();

        // Adding Zero Cards
        for (char color : colors) {
            playingDeck.add(new Card(color, 0));
        }

        // Adding 1-9 and skip, reverse, draw two cards
        for (int i = 1; i < 13; i++) {
            for (char color : colors) {
                playingDeck.add(new Card(color, i));
                playingDeck.add(new Card(color, i));
            }
        }

        // Add the wild colors
        for (int i = 0; i < 4; i++) {
            playingDeck.add(new Card('s', 13));
            playingDeck.add(new Card('s', 14));
        }
    }


    public Card drawCardFromDeck() {
        return playingDeck.remove(playingDeck.size() - 1);
    }

    public int deckSize() {
        return playingDeck.size();
    }

    public Card getTopOfDeck() {
        return playingDeck.get(playingDeck.size() - 1);
    }


    public ArrayList<Card> dealPlayingDeck(int cardAmount) {

        ArrayList<Card> drawnCards = new ArrayList<Card>();
        for (int i = 0; i < cardAmount; i++) {
            drawnCards.add(drawCardFromDeck());
        }
        return drawnCards;
    }

//    public void resetDeck(DiscardDeck discardDeck) {
//        System.out.println("Emptying, adding discard deck back in");
//        for (int i = discardDeck.deckSize(); i >= 0; i--){
//            Card card = discardDeck.getTopOfDeck();
//            card.flipCard();
//            playingDeck.add(card);
//            discardDeck.removeTopCard();
//        }
//        Collections.shuffle(playingDeck);
//    }

    public boolean isDeckEmpty() {
        if (playingDeck.size() == 0) {
            return true;
        } else {
            return false;
        }
    }


}
