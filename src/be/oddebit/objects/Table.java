package be.oddebit.objects;

import java.util.ArrayList;
import java.util.Arrays;

public class Table {

    private ArrayList<Card> commonCards = new ArrayList<>();


    public Table(){

        this.commonCards = new ArrayList<>();
    }

    public void flopTurnRiver(Card... cards) {

        this.commonCards.addAll(Arrays.asList(cards));
    }

    public ArrayList<Card> getCommonCards() {

         return this.commonCards;
    }

}
