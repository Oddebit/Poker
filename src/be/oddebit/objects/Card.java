package be.oddebit.objects;

public class Card {

    private String face;
    private int value;
    private String suit;


    public Card(int value, int suit) {

        this.value = value;
        setFace(value);
        setSuit(suit);

    }


    public int getValue() {
        return value;
    }

    public String getFace() {
        return face;
    }


    private void setSuit(int suit) {

        switch (suit) {

            case 1:
                this.suit = "hearts";
                break;

            case 2:
                this.suit = "diamonds";
                break;

            case 3:
                this.suit = "clubs";
                break;

            case 4:
                this.suit = "spades";
                break;
        }

    }

    private void setFace(int number) {

        switch (number) {

            case 1:
                face = "A";
                break;

            case 11:
                face = "J";
                break;

            case 12:
                face = "Q";
                break;

            case 13:
                face = "K";
                break;

            default:
                face = String.valueOf(number);
                break;
        }
    }
}
