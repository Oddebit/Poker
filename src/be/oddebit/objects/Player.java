package be.oddebit.objects;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private ArrayList<Card> hand;

    private final String name;
    private int stack;
    private int bet;

    private ArrayList<Card> bestHand;


    public Player(String name) {

        this.name = name;
        this.hand = new ArrayList<>();
        this.stack = 5000;
    }

    public void clearHand() {

        this.hand.clear();
    }


// SETTERS
    public void setStack(int stack) {

        this.stack = stack;
    }

    public void receivesCard(Card card) {

        this.hand.add(card);
    }

    public void setBet(int bet) {

        this.bet = bet;
    }

    public void setBestHand(ArrayList<Card> bestHand) {
        this.bestHand = bestHand;
    }

    // GETTERS
    public int getStack() {

        return stack;
    }

    public int getBet() {

        return bet;
    }

    public ArrayList<String> getHand() {

        ArrayList<String> hand = new ArrayList<>();

        for (Card card : this.hand) {

            hand.add(card.getCard());
        }

        return hand;
    }

    public ArrayList<String> getHandFaces() {

        ArrayList<String> face = new ArrayList<>();

        for (Card card : hand) {
            face.add(card.getFace());
        }

        return face;
    }


    public String getName() {
        return name;
    }
}
