package be.oddebit.objects;

import java.util.ArrayList;
import java.util.Random;

public class BestHand {

    private int[][] commonHand;
    private ArrayList<Card> bestHand;


    public BestHand() {

        this.commonHand = new int[5][14];
        this.bestHand = new ArrayList<>();

        generateHand();
        sum();

        setBestHand();
    }

    private void generateHand() {

        Random random = new Random();
//
//        for (int i = 0; i < 7; i++) {
//            int x = random.nextInt(4);
//            int y = random.nextInt(13);
//
//            commonHand[x][y] = 1;
//        }



        commonHand[2][3] = 1;
        commonHand[3][3] = 1;
        commonHand[1][3] = 1;
        commonHand[2][8] = 1;
        commonHand[3][8] = 1;
        commonHand[1][9] = 1;
        commonHand[2][9] = 1;


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

        addFourOfAKind(checkFourOfAKind());
        addFullHouse(checkFullHouse());
        addFlush(checkFlush());
        addThreeOfAKind(checkThreeOfAKind());
        addPairs(checkPairs());
        addHighCard();
    }

    public int[][] getCommonHand() {
        return commonHand;
    }

    public ArrayList<Card> getBestHand() {
        return bestHand;
    }

    public ArrayList<String> getBestHandCards() {

        ArrayList<String> cards = new ArrayList<>();

        for (Card card : bestHand) {
            cards.add(card.getCard());
        }

        return cards;
    }

    public ArrayList<String> getCommonHandCards() {

        ArrayList<String> cards = new ArrayList<>();

        for (int i = 12; i >= 0; i--) {

            for (int j = 0; j < 4; j++) {

                if (commonHand[j][i] == 1) {

                    cards.add(0, new Card(i + 1, j + 1).getCard());
                }
            }
        }

        return cards;
    }


    // FOUR OF A KIND
    public int checkFourOfAKind() {

        if (bestHand.size() < 5) {

            for (int i = 12; i >= 0; i--) {

                if (commonHand[4][i] == 4) {

                    return i;
                }
            }
        }

        return -1;
    }

    public void addFourOfAKind(int index) {

        if (index != -1) {

            for (int j = 0; j < 4; j++) {

                bestHand.add(0, new Card(index + 1, j + 1));
            }
        }
    }


    // FULL HOUSE
    public boolean checkFullHouse() {

        if (checkThreeOfAKind() != -1 && checkPairs().size() > 0) return true;

        return false;
    }

    public void addFullHouse(boolean fullHouse) {

        if (fullHouse) {

            addThreeOfAKind(checkThreeOfAKind());
            addPairs(checkPairs());
        }
    }


    // FLUSH
    public int checkFlush() {

        if (bestHand.size() < 5) {

            for (int j = 0; j < 4; j++) {

                if (commonHand[j][13] >= 5) {

                    return j;
                }
            }
        }

        return -1;
    }

    public void addFlush(int index) {

        if (index != -1) {

            int i = 12;
            while (bestHand.size() < 5) {

                if (commonHand[index][i] == 1) {

                    bestHand.add(0, new Card(i + 1, index + 1));
                }
                i--;
            }
        }
    }


    // THREE OF A KIND
    public int checkThreeOfAKind() {

        if (bestHand.size() <= 2) {

            for (int i = 12; i >= 0; i--) {

                if (commonHand[4][i] == 3) {

                    return i;
                }
            }
        }

        return -1;
    }

    public void addThreeOfAKind(int index) {

        if (index != -1) {

            for (int j = 0; j < 4; j++) {

                if (commonHand[j][index] == 1) {

                    bestHand.add(0, new Card(index + 1, j + 1));

                }
            }
        }
    }

    // PAIR
    public ArrayList<Integer> checkPairs() {

        ArrayList<Integer> pairs = new ArrayList<>();

        for (int i = 12; i >= 0; i--) {

            if (commonHand[4][i] == 2) {

                pairs.add(pairs.size(), i);
            }
        }

        return pairs;
    }

    public void addPairs(ArrayList<Integer> indexes) {

        int i = 0;

        while (i <= indexes.size() && bestHand.size() <= 3) {

            for (int j = 0; j < 4; j++) {

                if (commonHand[j][indexes.get(i)] == 1) {

                    bestHand.add(0, new Card(indexes.get(i) + 1, j + 1));
                }
            }

            i++;
        }
    }


    public void addHighCard() {

        int i = 12;

        while (bestHand.size() < 5 && i >= 0) {

            if (commonHand[4][i] == 1) {

                for (int j = 0; j < 4; j++) {

                    if (commonHand[j][i] == 1) {

                        bestHand.add(bestHand.size(), new Card(i + 1, j + 1));

                    }
                }
            }

            i--;
        }
    }
}

