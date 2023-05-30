import java.util.Objects;

public class Card {

    private char color;
    private int value;
    private boolean faceUp;  //A boolean representing if the card's face is up(true) or not(false)


    public Card(char color, int value) {
        this.color = color;
        this.value = value;
        faceUp = false;
    }


    public boolean isFaceUp() {
        return faceUp;
    }

    public void flipCard() {
        faceUp = !faceUp;
    }

    public char getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public void changeColor(char color) {
        if (value > 12) {
            this.color = color;
        }
    }

    public void changeColor(String color) {
        if (value > 12) {
            this.color = color.charAt(0);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Card) {
            Card cardPlayed = (Card) other;
            if (this.color == cardPlayed.color || this.color == 's') {
                return true;
            }
            return this.value == cardPlayed.value;
        }
        return false;
    }

    public String displayCard() {
        if (isFaceUp() == true) {
            switch(value) {
                case 10:
                    return color + "x";
                case 11:
                    return color + "<>";
                case 12:
                    return color + "+2";
                case 13:
                    return color + "w";
                case 14:
                    return color + "+4";
                default:
                    return color + String.valueOf(value);
            }
        } else {
            return "u";
        }
    }


    @Override
    public String toString() {
        String output = "";
        if (faceUp == true) {
            switch (color) {
                case 'r':
                    output += "\033[31;1m"; // bold red
                    break;
                case 'g':
                    output += "\033[32;1m"; // bold green
                    break;
                case 'b':
                    output += "\033[34;1m"; // bold blue
                    break;
                case 'y':
                    output += "\033[33;1m"; // bold yellow
                    break;
                case 's':
                    output += "\033[37;1m"; // bold white
                    break;
            }
        }
        if (isFaceUp() == true) {  // prints the card face if its up
            switch(value) {
                case 10:
                    return output + color + "x" + "\033[0m";
                case 11:
                    return output + color + "<>" + "\033[0m";
                case 12:
                    return output + color + "+2" + "\033[0m";
                case 13:
                    return output + color + "w" + "\033[0m";
                case 14:
                    return output + color + "+4" + "\033[0m";
                default:
                    return output + color + String.valueOf(value) + "\033[0m";
            }
        } else {
            return "\033[37;1mu\033[0m";
        }
    }



}
