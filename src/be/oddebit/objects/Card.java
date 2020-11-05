package be.oddebit.objects;

public class Card {

    private String face;
    private char suit;


    // CONSTRUCTOR
    public Card(int value, int suit) {

        setFace(value);
        setSuit(suit);
    }

    @Override
    public String toString() {
        return face + suit;
    }


    // GETTERS
    public String getFace() {
        return face;
    }

    public char getSuit() {
        return suit;
    }


    // SETTERS
    private void setSuit(int suit) {

        switch (suit) {

            case 0:
                this.suit = '\u2665';
                break;

            case 1:
                this.suit = '\u2666';
                break;

            case 2:
                this.suit = '\u2667';
                break;

            case 3:
                this.suit = '\u2664';
                break;
        }

    }

    private void setFace(int number) {

        switch (number) {

            case 11:
                face = "J";
                break;

            case 12:
                face = "Q";
                break;

            case 13:
                face = "K";
                break;

            case 14:
                face = "A";
                break;

            default:
                face = String.valueOf(number);
                break;
        }
    }
}
