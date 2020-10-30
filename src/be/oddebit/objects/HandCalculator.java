package be.oddebit.objects;

import be.oddebit.UserInterface.Terminal;

import java.util.ArrayList;

public class HandCalculator {

    private int[][] gameBoard;

    Table table;
    Player dealer;

    Player player;
    private ArrayList<Card> bestHand;


    public HandCalculator(Table table, Player player) {

        this.gameBoard = new int[5][14];
        this.bestHand = new ArrayList<>();

        this.table = table;
        this.player = player;
        this.dealer = table.dealer;


        boardTranslation();

        setBestHand();
    }


    private void boardTranslation() {

        for (int s = 0; s < 4; s++) {

            for (int v = 0; v < 13; v++) {

                if(isPlayersCard(s, v)) {

                    gameBoard[s][v] += 1;
                    gameBoard[s][13] += 1;
                    gameBoard[4][v] += 1;
                }
            }
        }

    }

    private boolean isPlayersCard(int suit, int value) {

        if (this.table.getGameBoard()[suit][value] == null) return false;

        return this.table.getGameBoard()[suit][value].equals(this.player.getName())
                || this.table.getGameBoard()[suit][value].equals(this.dealer.getName());
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

        player.setBestHand(bestHand);
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

            Terminal.sayStraightFlush(player, new Card(value + 1, 0).getFace());

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

            Terminal.sayFourOfAKind(player, new Card(value + 1, 0).getFace());

            for (int s = 0; s < 4; s++) {

                bestHand.add(0, new Card(value + 1, s + 1));
            }
        }
    }


    // FULL HOUSE
    public int[] checkFullHouse() {

        int threeOfAKind = checkThreeOfAKind();
        int pair = checkPairs().size() > 0 ? checkPairs().get(0) : -1;

        if (threeOfAKind != -1 && pair != -1) {
            return new int[]{threeOfAKind, pair};
        }

        return new int[]{-1, -1};
    }

    private void addFullHouse(int[] values) {

        if (values[0] != -1 && values[1] != -1) {

            addThreeOfAKind(values[0]);
            addPairs(checkPairs());
            Terminal.sayFullHouse(player, new Card(values[0] + 1, 0).getFace(), new Card(values[1] + 1, 0).getFace());
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

            Terminal.sayFlush(player, new Card(0, suit + 1).getSuit());

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

            Terminal.sayStraight(player, new Card(value + 1, 0).getFace());

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

            Terminal.sayThreeOfAKind(player, new Card(value + 1, 0).getFace());

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

    private void addPairs(ArrayList<Integer> values) {

        int i = 0;

        while (i < values.size() && bestHand.size() <= 3) {

            Terminal.sayPairs(player, new Card(values.get(i) + 1, 0).getFace());

            for (int s = 0; s < 4; s++) {

                if (gameBoard[s][values.get(i)] == 1) {

                    bestHand.add(0, new Card(values.get(i) + 1, s + 1));
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

