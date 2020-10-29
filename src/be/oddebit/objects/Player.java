package be.oddebit.objects;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private ArrayList<Card> ownHand;
    private ArrayList<Card> commonHand;
    private final String name;
    private int stack;
    private int bet;


    public Player(String name) {

        this.name = name;
    }


    public void setStack(int stack) {

        this.stack = stack;
    }

    public void receivesCards(Card... cards) {

        this.ownHand.addAll(Arrays.asList(cards));
    }

    public void receivesBet(int factor) {

        this.stack += bet * factor;
    }

    public void setBet(int bet) {

        this.bet = bet;
    }

    public void setCommonHand(Table table) {
        this.ownHand.addAll(table.getCommonCards());
    }


    public int getStack() {

        return stack;
    }

    public Card getCard(int index) {
        return ownHand.get(index);
    }


    public ArrayList<Card> getCommonHand(Table table) {
        return this.commonHand;
    }

    public String getName() {
        return name;
    }
}
