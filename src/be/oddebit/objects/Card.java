package be.oddebit.objects;

public class Card {

    private int value;

    private String face;
    private String suit;

    private String card;


    public Card(int value, int suit) {

        this.value = value;
        setFace(value);
        setSuit(suit);
        setCard(this.face, this.suit);

    }

    public String getCard() {
        return card;
    }

    public String getFace() {
        return face;
    }

    public String getSuit() {
        return suit;
    }


    private void setSuit(int suit) {

        switch (suit) {

            case 1:
                this.suit = "(H)";
                break;

            case 2:
                this.suit = "(D)";
                break;

            case 3:
                this.suit = "(C)";
                break;

            case 4:
                this.suit = "(S)";
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

    private void setCard(String face, String suit) {

        this.card = face + suit;
    }
}
