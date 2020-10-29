package be.oddebit.objects;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>();

    public Deck() {

        for (int i = 0; i < 13; i++) {

            for (int j = 0; j < 4; j++) {

                deck.add(new Card(i + 1, j + 1));
            }
        }
    }

    public Card removeCard() {

        Random random = new Random();
        int index = random.nextInt(deck.size());

        return this.deck.remove(index);
    }
}
