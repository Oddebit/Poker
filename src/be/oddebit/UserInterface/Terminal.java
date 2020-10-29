package be.oddebit.UserInterface;

import be.oddebit.objects.Card;
import be.oddebit.objects.HandCalculator;

import java.util.ArrayList;

public class Terminal {

    public static void sayStraightFlush(int value) {

        System.out.println("You got a " + value + "-high straight flush.");
    }

    public static void sayFourOfAKind(int value) {

        System.out.println("You got four of a kind, " + value + "'s.");
    }

    public static void sayFullHouse(int value1, int value2) {

        System.out.println("You got a full house, " + value1 + "'s over " + value2 + "'s.");
    }

    public static void sayFlush(String face) {

        System.out.println("You got a " + face + " flush.");
    }

    public static void sayStraight(int value) {

        System.out.println("You got a " + value + "-high straight.");
    }

    public static void sayThreeOfAKind(int value) {

        System.out.println("You got three of a kind, " + value + "'s.");
    }

    public static void sayPairs(int value) {

        System.out.println("You got a pair of " + value + "'s .");
    }

    public static void sayNothing() {

        System.out.println("You got nothing. Cry like a poker player!");
    }
}

