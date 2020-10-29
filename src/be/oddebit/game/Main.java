package be.oddebit.game;

import be.oddebit.objects.BestHand;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        BestHand bestHand = new BestHand();

        for (int[] nums : bestHand.getCommonHand()) {
            System.out.println(Arrays.toString(nums));
        }

        System.out.println();
        System.out.println("Common hand : " + bestHand.getCommonHandCards());
        System.out.println("Best hand : " + bestHand.getBestHandCards());


    }

}