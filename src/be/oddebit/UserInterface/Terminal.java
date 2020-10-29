package be.oddebit.UserInterface;

import be.oddebit.objects.Card;
import be.oddebit.objects.HandCalculator;

import java.util.ArrayList;

public class Terminal {

    public static void sayStraightFlush(String face) {

        System.out.println("You got a " + face + "-high straight flush.");
    }

    public static void sayFourOfAKind(String face) {

        System.out.println("You got four of a kind, " + face + "'s.");
    }

    public static void sayFullHouse(String face1, String face2) {

        System.out.println("You got a full house, " + face1 + "'s over " + face2 + "'s.");
    }

    public static void sayFlush(String suit) {

        System.out.println("You got a " + suit + " flush.");
    }

    public static void sayStraight(String face) {

        System.out.println("You got a " + face + "-high straight.");
    }

    public static void sayThreeOfAKind(String face) {

        System.out.println("You got three of a kind, " + face + "'s.");
    }

    public static void sayPairs(String face) {

        System.out.println("You got a pair of " + face + "'s .");
    }

    public static void sayNothing() {

        System.out.println("You got nothing. Cry like a poker player!");
    }
}

