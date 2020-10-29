package be.oddebit.game;

import be.oddebit.objects.HandCalculator;

import java.util.Arrays;

public class Game {

    HandCalculator hand;

    public Game() {

        for (int i = 0; i < 100; i++) {

            hand = new HandCalculator();

            for (int[] nums : hand.getGameBoard()) {
                System.out.println(Arrays.toString(nums));
            }

            System.out.println();
            System.out.println("Common hand : " + hand.getCommonHandCards());
            System.out.println("Best hand : " + hand.getBestHandCards());
            System.out.println();
            System.out.println();
        }
    }
}
