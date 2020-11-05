package be.oddebit.UserInterface;

import be.oddebit.objects.Card;
import be.oddebit.objects.Player;
import java.util.Scanner;

public class Terminal {

    static Scanner scanner = new Scanner(System.in);

    public static void sayNewGame() {

        System.out.println("\n\n--- NEW GAME ---");
    }

    public static int askBet(Player player) {

        System.out.println("\nYour stack : " + player.getStack());
        System.out.println("What is your bet?");
        int input = scanner.nextInt();

        while (input > player.getStack()) {

            System.out.println("Current stack : " + player.getStack() + ". Do not bet more.");
            input = scanner.nextInt();
        }

        while (0 > input || input > 500) {

            System.out.println("Please bet between 1 and 500.");
            input = scanner.nextInt();
        }

        System.out.println();
        return input;
    }

    public static void showHand(Player player) {

        System.out.println(player.getName() + " : " + player.getHand());
    }

    public static int askFoldCallRaise() {

        System.out.println("Fold (1), Call (2) or Raise(3)?");
        int input = scanner.nextInt();

        System.out.println();
        return input;
    }

    public static void sayStraightFlush(Player player, String face) {

        System.out.println(player.getName() + " got a " + face + "-high straight flush.");
    }

    public static void sayFourOfAKind(Player player, String face) {

        System.out.println(player.getName() + " got four of a kind, " + face + "'s.");
    }

    public static void sayFullHouse(Player player, String face1, String face2) {

        System.out.println(player.getName() + " got a full house, " + face1 + "'s over " + face2 + "'s.");
    }

    public static void sayFlush(Player player, Card card) {

        System.out.println(player.getName() + " got a " + card.getFace() + "-high flush of " + card.getSuit() + ".");
    }

    public static void sayStraight(Player player, String face) {

        System.out.println(player.getName() + " got a " + face + "-high straight.");
    }

    public static void sayThreeOfAKind(Player player, String face) {

        System.out.println(player.getName() + " got three of a kind, " + face + "'s.");
    }

    public static void sayTwoPairs(Player player, String face1, String face2) {

        System.out.println(player.getName() + " got a pair of " + face1 + "'s and a pair of " + face2 + "'s.");
    }

    public static void sayPair(Player player, String face) {

        System.out.println(player.getName() + " got a pair of " + face + "'s.");
    }

    public static void sayWin(Player player) {

        System.out.println(player.getName() + " wins.");
    }

    public static void sayDraw() {

        System.out.println("It's a draw.");
    }

    public static boolean askPlay() {

        System.out.println("Press '1' to restart.");
        int input = scanner.nextInt();

        return input == 1;
    }

    public static void printLine() {

        System.out.println();
    }
}

