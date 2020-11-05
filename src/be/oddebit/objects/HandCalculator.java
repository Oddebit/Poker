package be.oddebit.objects;

import be.oddebit.UserInterface.Terminal;

import java.util.ArrayList;

public class HandCalculator {

    private int[][] gameBoard;

    Table table;
    Player dealer;

    Player player;


    // CONSTRUCTORS
    public HandCalculator(Table table, Player player) {

        this.gameBoard = new int[5][14];
        this.table = table;
        this.player = player;
        this.dealer = table.dealer;

        boardTranslation();


        setBestHand();

//        player.setBestHand(bestHand);
    }

    private void boardTranslation() {

        for (int s = 0; s < 4; s++) {

            for (int v = 0; v < 13; v++) {

                if (isPlayersCard(s, v)) {

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

        int straightFlush = checkStraightFlush();
        int fourOfAKind = checkFourOfAKind();
        int[] fullHouse = checkFullHouse();
        int flush = checkFlush();
        int straight = checkStraight();
        int threeOfAKind = checkThreeOfAKind(12);
        ArrayList<Integer> pairs = checkPairs();


        if (straightFlush != -1) {

            addStraightFlush(straightFlush);

        } else if (fourOfAKind != -1) {

            addFourOfAKind(fourOfAKind);

        } else if (fullHouse[0] != -1 && fullHouse[1] != -1) {

            addFullHouse(fullHouse);

        } else if (flush != -1) {

            addFlush(flush);

        } else if (straight != -1) {

            addStraight(straight);

        } else if (threeOfAKind != -1) {

            addThreeOfAKind(threeOfAKind);

        } else if (pairs.size() > 0) {

            addPairs(pairs);

        } else {

            addHighCard(5, -1);
        }
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
                            if (v2 == 0 && gameBoard[suit][13] > 0) {
                                count++;
                            }
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

            Terminal.sayStraightFlush(player, new Card(value + 2, 0).getFace());

            player.setHandCode(0, 8);
            player.setHandCode(1, value + 2);
        }
    }


    // FOUR OF A KIND
    public int checkFourOfAKind() {

        for (int v = 12; v >= 0; v--) {

            if (gameBoard[4][v] == 4) {

                return v;
            }

        }

        return -1;
    }

    private void addFourOfAKind(int value) {

        if (value != -1) {

            Terminal.sayFourOfAKind(player, new Card(value + 2, 0).getFace());
            player.setHandCode(0, 7);
            player.setHandCode(1, value + 2);

            addHighCard(1, value);
        }
    }


    // FULL HOUSE
    public int[] checkFullHouse() {

        int threeOfAKind = checkThreeOfAKind(12);
        int threeOfAKind2 = checkThreeOfAKind(threeOfAKind - 1);
        int pair;

        if (checkPairs().size() > 0) {
            pair = checkPairs().get(0);
        } else if (threeOfAKind2 != -1) {
            pair = threeOfAKind2;
        } else {
            pair = -1;
        }

        return new int[]{threeOfAKind, pair};
    }

    private void addFullHouse(int[] values) {

        if (values[0] != -1 && values[1] != -1) {

            Terminal.sayFullHouse(player, new Card(values[0] + 2, 0).getFace(),
                    new Card(values[1] + 2, 0).getFace());

            player.setHandCode(0, 6);
            player.setHandCode(1, values[0] + 2);
            player.setHandCode(2, values[1] + 2);
        }
    }


    // FLUSH
    public int checkFlush() {

        for (int s = 0; s < 4; s++) {

            if (gameBoard[s][13] >= 5) {

                return s;
            }
        }

        return -1;
    }

    private void addFlush(int suit) {

        if (suit != -1) {

            player.setHandCode(0, 5);

            int count = 0;
            boolean sayFlush = true;
            for (int v = 12; count < 5; v--) {

                if (gameBoard[suit][v] == 1) {

                    if (sayFlush) {
                        Terminal.sayFlush(player, new Card(v + 2, suit));
                        sayFlush = false;
                    }

                    player.setHandCode(count + 1, v + 2);
                    count++;
                }
            }
        }
    }


    // STRAIGHT
    public int checkStraight() {

        for (int v1 = 12; v1 >= 0; v1--) {

            if (gameBoard[4][v1] > 0) {

                int count = 0;

                for (int v2 = v1; v2 >= 0; v2--) {

                    if (gameBoard[4][v2] > 0) {
                        count++;
                        if (v2 == 0 && gameBoard[4][13] > 0) {
                            count++;
                        }
                    } else {
                        break;
                    }
                }

                if (count >= 5) return v1;
            }
        }

        return -1;
    }

    private void addStraight(int value) {

        if (value != -1) {

            Terminal.sayStraight(player, new Card(value + 2, 0).getFace());
            player.setHandCode(0, 4);
            player.setHandCode(1, value + 2);
        }
    }


    // THREE OF A KIND
    public int checkThreeOfAKind(int max) {

        for (int v = max; v >= 0; v--) {

            if (gameBoard[4][v] == 3) {

                return v;
            }
        }

        return -1;
    }

    private void addThreeOfAKind(int value) {

        if (value != -1) {

            Terminal.sayThreeOfAKind(player, new Card(value + 2, 0).getFace());
            player.setHandCode(0, 3);
            player.setHandCode(1, value + 2);

            addHighCard(2, value);
        }
    }


    // PAIR
    public ArrayList<Integer> checkPairs() {

        ArrayList<Integer> pairs = new ArrayList<>();

        for (int v = 12; v >= 0 && pairs.size() < 2; v--) {

            if (gameBoard[4][v] == 2) {

                pairs.add(v);
            }
        }

        return pairs;
    }

    private void addPairs(ArrayList<Integer> values) {

        if (values.size() != 0) {

            if (values.size() == 2) {

                addHighCard(1, values.get(0), values.get(1));
                player.setHandCode(0, 2);

            } else if (values.size() == 1) {

                addHighCard(3, values.get(0));
                player.setHandCode(0, 1);

            }

            for (int i = 0; i < 2; i++) {

                if (i < values.size()) {

                    Terminal.sayPair(player, new Card(values.get(i) + 2, 0).getFace());
                    player.setHandCode(i + 1, values.get(i) + 2);

                } else {

                    player.setHandCode(i + 1, -1);
                }
            }
        }
    }

    public void addHighCard(int n, int... inHand) {

        for (int v = 12; n > 0; v--) {

            if (gameBoard[4][v] != 0) {

                for (int i : inHand) {

                    if (v == i) {

                        break;
                    }

                    player.setHandCode(6 - n, v + 2);
                    n--;

                    break;
                }
            }
        }
    }
}