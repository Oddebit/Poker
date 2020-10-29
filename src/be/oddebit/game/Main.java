package be.oddebit.game;

import be.oddebit.objects.BestHand;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        BestHand bestHand = new BestHand();

        for (int[] nums : bestHand.getCommonHand()) {
            System.out.println(Arrays.toString(nums));
        }

        System.out.println("Best hand : " + bestHand.getBestHandFaces());
        System.out.println("Pairs : " + bestHand.pair());
        System.out.println("Three of a kind : " + bestHand.threeOfAKind());
        System.out.println("Four of a kind : " + bestHand.fourOfAKind());

    }

}
