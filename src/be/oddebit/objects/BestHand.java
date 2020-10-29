package be.oddebit.objects;

import be.oddebit.objects.Card;

import java.util.ArrayList;
import java.util.Random;

public class BestHand {

    private int[][] commonHand;
    private ArrayList<Card> bestHand;


    public BestHand() {

        this.commonHand= new int[5][14];
        this.bestHand = new ArrayList<>();

        generateHand();
        sum();

        setBestHand();
    }

    private void generateHand() {

        Random random = new Random();

        for (int i = 0; i < 7; i++) {
            int x = random.nextInt(4);
            int y = random.nextInt(13);

            commonHand[x][y] = 1;
        }
    }


    private void sum() {

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 13; j++) {

                commonHand[i][13] += commonHand[i][j];
                commonHand[4][j] += commonHand[i][j];
                commonHand[4][13] += commonHand[i][j];
            }
        }

    }

    public void setBestHand() {
        fourOfAKind();
        threeOfAKind();
        pair();

        while(bestHand.size() <= 5) {
            highCard();
        }

    }

    public int[][] getCommonHand() {
        return commonHand;
    }

    public ArrayList<Card> getBestHand() {
        return bestHand;
    }

    public ArrayList<String> getBestHandFaces() {

        ArrayList<String> face = new ArrayList<>();

        for (Card card : bestHand) {
            face.add(card.getFace());
        }

        return face;
    }


    public boolean fourOfAKind() {

        for (int i = 12; i >= 0; i--) {
            if (commonHand[4][i] == 4) {
                for (int j = 0; j < 4; j++) {
                    bestHand.add(0, new Card(i + 1, j + 1));
                }

                return true;
            }
        }

        return false;
    }

    public boolean threeOfAKind() {

        for (int i = 12; i >= 0; i--) {

            if (commonHand[4][i] == 3) {

                for (int j = 0; j < 4; j++) {

                    if (commonHand[j][i] == 1) {

                        bestHand.add(0, new Card(i + 1, j + 1));
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public int pair() {

        int count = 0;
        for (int i = 12; i >= 0; i--) {

            if (commonHand[4][i] == 2 && count <= 2) {

                count += 1;
                for (int j = 0; j < 4; j++) {

                    if (commonHand[j][i] == 1) {

                        bestHand.add(bestHand.size(), new Card(i + 1, j + 1));

                    }
                }
            }
        }

        return count;
    }

    public void highCard() {

        for (int i = 12; i >= 0; i--) {

            if (commonHand[4][i] == 1) {

                for (int j = 0; j < 4; j++) {

                    if (commonHand[j][i] == 1) {

                        bestHand.add(bestHand.size(), new Card(i + 1, j + 1));

                    }
                }
            }
        }
    }
}
