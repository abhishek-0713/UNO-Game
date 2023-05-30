import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("""
                 
                 ██╗░░░██╗███╗░░██╗░█████╗░  ░██████╗░░█████╗░███╗░░░███╗███████╗
                 ██║░░░██║████╗░██║██╔══██╗  ██╔════╝░██╔══██╗████╗░████║██╔════╝
                 ██║░░░██║██╔██╗██║██║░░██║  ██║░░██╗░███████║██╔████╔██║█████╗░░
                 ██║░░░██║██║╚████║██║░░██║  ██║░░╚██╗██╔══██║██║╚██╔╝██║██╔══╝░░
                 ╚██████╔╝██║░╚███║╚█████╔╝  ╚██████╔╝██║░░██║██║░╚═╝░██║███████╗
                 ░╚═════╝░╚═╝░░╚══╝░╚════╝░  ░╚═════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝


                """);

        GameTutorial();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose computer opponents you want to play with [1-7]: ");
        int Players = scanner.nextInt();

        System.out.print("Choose Number of cards you want to start with [ Recommended 7 ]: ");
        int Cards = scanner.nextInt();


        Game newGame = new Game(Players, Cards);
        newGame.playGame(0);

    }


    private static void GameTutorial() {
        System.out.println("--------------------------- Welcome to UNO! ---------------------------\n" +
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
                "\n------------------------- Thank You ---------------------------\n");
    }

}
