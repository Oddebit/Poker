package be.oddebit.objects;

import be.oddebit.UserInterface.Terminal;

import java.util.ArrayList;
import java.util.Random;

public class HandCalculator {

    private int[][] gameBoard;
    private ArrayList<Card> bestHand;


    public HandCalculator() {

        this.gameBoard = new int[5][14];
        this.bestHand = new ArrayList<>();

        generateHand();
        sum();

        setBestHand();
    }

    private void generateHand() {

        Random random = new Random();

        for (int i = 0; i < 7; i++) {

            boolean occupied = true;
            while (occupied){
                int x = random.nextInt(4);
                int y = random.nextInt(13);

                if (gameBoard[x][y] != 1) {
                    gameBoard[x][y] = 1;
                    occupied = false;
                }
            }

        }



//        gameBoard[0][3] = 1;
//        gameBoard[0][4] = 1;
//        gameBoard[0][5] = 1;
//        gameBoard[0][6] = 1;
//        gameBoard[0][7] = 1;
//        gameBoard[0][9] = 1;
//        gameBoard[1][9] = 1;


    }


    private void sum() {

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 13; j++) {

                gameBoard[i][13] += gameBoard[i][j];
                gameBoard[4][j] += gameBoard[i][j];
                gameBoard[4][13] += gameBoard[i][j];
            }
        }

    }

    public void setBestHand() {

        addStraightFlush(checkStraightFlush());
        addFourOfAKind(checkFourOfAKind());
        addFullHouse(checkFullHouse());
        addFlush(checkFlush());
        addStraight(checkStraight());
        addThreeOfAKind(checkThreeOfAKind());
        addPairs(checkPairs());
        addHighCard();
    }

    public int[][] getGameBoard() {
        return gameBoard;
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

        for (int v = 12; v >= 0; v--) {

            for (int s = 0; s < 4; s++) {

                if (gameBoard[s][v] == 1) {

                    cards.add(0, new Card(v + 1, s + 1).getCard());
                }
            }
        }

        return cards;
    }


    // STRAIGHT FLUSH
    public int checkStraightFlush() {

        int suit = checkFlush();

        if (suit != -1) {

            for (int v1 = 12; v1 >= 0; v1--) {

                if (gameBoard[suit][v1] > 0) {

                    int count = 0;

                    for (int v2 = v1; v2 >= 0; v2--) {

                        if (gameBoard[suit][v2] == 1) {
                            count++;
                        } else {
                            break;
                        }
                    }

                    if (count >= 5) return v1;
                }
            }
        }

        return -1;
    }

    private void addStraightFlush(int value) {

        if (value != -1) {

            Terminal.sayStraightFlush(value + 1);

            for (int v = value; v >= value - 4; v--) {

                for (int i = 0; i < 4; i++) {

                    if (gameBoard[i][v] == 1) {
                        bestHand.add(0, new Card(v + 1, i + 1));
                        break;
                    }
                }
            }
        }
    }


    // FOUR OF A KIND
    public int checkFourOfAKind() {

        if (bestHand.size() == 0) {

            for (int v = 12; v >= 0; v--) {

                if (gameBoard[4][v] == 4) {

                    return v;
                }
            }
        }

        return -1;
    }

    private void addFourOfAKind(int value) {

        if (value != -1) {

            Terminal.sayFourOfAKind(value + 1);

            for (int s = 0; s < 4; s++) {

                bestHand.add(0, new Card(value + 1, s + 1));
            }
        }
    }


    // FULL HOUSE
    public boolean checkFullHouse() {

        if (checkThreeOfAKind() != -1 && checkPairs().size() > 0) return true;

        return false;
    }

    private void addFullHouse(boolean fullHouse) {

        if (fullHouse) {

            Terminal.sayFullHouse(1, 2);

            addThreeOfAKind(checkThreeOfAKind());
            addPairs(checkPairs());
        }
    }


    // FLUSH
    public int checkFlush() {

        if (bestHand.size() == 0) {

            for (int s = 0; s < 4; s++) {

                if (gameBoard[s][13] >= 5) {

                    return s;
                }
            }
        }

        return -1;
    }

    private void addFlush(int suit) {

        if (suit != -1) {

            Terminal.sayFlush(new Card(1, suit + 1).getSuit());

            int v = 12;
            while (bestHand.size() < 5) {

                if (gameBoard[suit][v] == 1) {

                    bestHand.add(0, new Card(v + 1, suit + 1));
                }
                v--;
            }
        }
    }



    // STRAIGHT
    public int checkStraight() {

        if (bestHand.size() == 0) {

            for (int v1 = 12; v1 >= 0; v1--) {

                if (gameBoard[4][v1] > 0) {

                    int count = 0;

                    for (int v2 = v1; v2 >= 0; v2--) {

                        if (gameBoard[4][v2] > 0) {
                            count++;
                        } else {
                            break;
                        }

                    }

                    if (count >= 5) return v1;
                }
            }
        }

        return -1;
    }

    private void addStraight(int value) {

        if (value != -1) {

            Terminal.sayStraight(value + 1);

            for (int v = value; v >= value - 4; v--) {

                for (int i = 0; i < 4; i++) {

                    if (gameBoard[i][v] == 1) {
                        bestHand.add(0, new Card(v + 1, i + 1));
                        break;
                    }
                }
            }
        }
    }


    // THREE OF A KIND
    public int checkThreeOfAKind() {

        if (bestHand.size() <= 2) {

            for (int v = 12; v >= 0; v--) {

                if (gameBoard[4][v] == 3) {

                    return v;
                }
            }
        }

        return -1;
    }

    private void addThreeOfAKind(int value) {

        if (value != -1) {

            Terminal.sayThreeOfAKind(value + 1);

            for (int s = 0; s < 4; s++) {

                if (gameBoard[s][value] == 1) {

                    bestHand.add(0, new Card(value + 1, s + 1));

                }
            }
        }
    }


    // PAIR
    public ArrayList<Integer> checkPairs() {

        ArrayList<Integer> pairs = new ArrayList<>();

        for (int v = 12; v >= 0; v--) {

            if (gameBoard[4][v] == 2) {

                pairs.add(pairs.size(), v);
            }
        }

        return pairs;
    }

    private void addPairs(ArrayList<Integer> value) {

        int i = 0;

        while (i < value.size() && bestHand.size() <= 3) {

            Terminal.sayPairs(value.get(i) +1);

            for (int s = 0; s < 4; s++) {

                if (gameBoard[s][value.get(i)] == 1) {

                    bestHand.add(0, new Card(value.get(i) + 1, s + 1));
                }
            }

            i++;
        }
    }


    public void addHighCard() {

        int v = 12;

        while (bestHand.size() < 5) {

            if (gameBoard[4][v] == 1) {

                for (int s = 0; s < 4; s++) {

                    if (gameBoard[s][v] == 1) {

                        bestHand.add(bestHand.size(), new Card(v + 1, s + 1));

                    }
                }
            }

            v--;
        }
    }
}

