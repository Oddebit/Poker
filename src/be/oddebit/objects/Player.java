package be.oddebit.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Player {

    private ArrayList<Card> hand;

    private final String name;
    private double aggressiveness;

    private int stack;
    private int bet;
    private boolean isPlaying;

    private ArrayList<Card> bestHand;
    private int[] handCode = new int[6];


    // CONSTRUCTOR
    public Player(String name) {

        this.name = name;
        setAggressiveness(0,100);
        this.hand = new ArrayList<>();
        this.stack = 5000;
        this.isPlaying = true;
    }

    public void clearHand() {

        this.hand.clear();
        Arrays.fill(this.handCode, 0);
    }

    public void receivesCard(Card card) {

        this.hand.add(card);
    }

    public void receivesChips(int chips) {
        this.stack += chips;
    }


    // SETTERS
    public void setStack(int stack) {

        this.stack = stack;
    }

    public void setBet(int bet) {

        this.bet = bet;
    }

    public void setBestHand(ArrayList<Card> bestHand) {
        this.bestHand = bestHand;
    }

    public void setHandCode(int index, int value) {
        this.handCode[index] = value;
    }

    public void setAggressiveness(double min, double max) {

        Random random = new Random();
        final int EMPIRICAL_MIN = -4;
        final int EMPIRICAL_MAX = 4;

        double next = random.nextGaussian();

        this.aggressiveness = min+(max-min)*(next-EMPIRICAL_MIN)/(EMPIRICAL_MAX-EMPIRICAL_MIN);
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    // GETTERS
    public int getStack() {

        return stack;
    }

    public int getBet() {

        return bet;
    }

    public int bet(int bet) {
        return -1;
    }

    public ArrayList<String> getHand() {

        ArrayList<String> hand = new ArrayList<>();

        for (Card card : this.hand) {

            hand.add(card.toString());
        }

        return hand;
    }

    public Card getCard(int index) {
        return this.hand.get(index);
    }

    public ArrayList<String> getHandFaces() {

        ArrayList<String> face = new ArrayList<>();

        for (Card card : hand) {
            face.add(card.getFace());
        }

        return face;
    }

    public int getHandCode(int index) {
        return handCode[index];
    }

    public int[] getHandCode() {
        return handCode;
    }

    public String getName() {
        return name;
    }

    public double getAggressiveness() {
        return aggressiveness;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isHandSuited() {
        return hand.get(0).getSuit() == hand.get(1).getSuit();
    }
}
