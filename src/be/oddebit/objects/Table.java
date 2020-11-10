package be.oddebit.objects;

import be.oddebit.UserInterface.Terminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Table {

    Random random = new Random();

    private String[][] gameBoard;
    private int pot;

    User user;
    Opponent[] opponents;
    Player dealer;

    ArrayList<Player> allPlayers;

    public Table(String playerName, int opponents) {

        this.dealer = new Player("On the table");
        this.user = new User(playerName);
        this.opponents = new Opponent[opponents];

        for (int i = 0; i < opponents; i++) {
            this.opponents[i] = new Opponent("Opponent " + i);
        }

        boolean play = true;
        while (play && user.getStack() > 0) {

            newGame();
            dealHands();

            proceedBets();

            if(allPlayers.size() < 2) {
                win(allPlayers.get(0));
                play = Terminal.askPlay();
                continue;
            }

            dealHand(dealer, 3);
            Terminal.showHand(dealer);

            if (foldCallRaise(Terminal.askFoldCallRaise())) {

                dealHand(dealer, 2);
                revealCards();
                compareHands();
            }

            play = Terminal.askPlay();
        }
    }

    public void newGame() {

        Terminal.sayNewGame();

        this.user.clearHand();

        for (Player opponent : opponents) {
            opponent.clearHand();
        }

        this.dealer.clearHand();


        allPlayers = new ArrayList<>();
        allPlayers.add(user);
        allPlayers.addAll(Arrays.asList(this.opponents));

        gameBoard = new String[4][13];
        pot = 0;
    }

    public void dealHands() {

        dealHand(user, 2);
        for (Player opponent : this.opponents) {
            dealHand(opponent, 2);
        }
    }

    public void dealHand(Player player, int cards) {

        for (int i = 0; i < cards; i++) {

            boolean occupied = true;
            while (occupied) {

                int suit = random.nextInt(4);
                int value = random.nextInt(13);

                if (gameBoard[suit][value] == null) {

                    gameBoard[suit][value] = player.getName();
                    player.receivesCard(new Card(value + 2, suit));
                    occupied = false;
                }
            }

        }
    }

    public void proceedBets() {

        ArrayList<Player> toRemove = new ArrayList<>();
        int minBet = 0;
        for (Player player : allPlayers) {

            int playerBet = player.bet(minBet);
            minBet = playerBet;

            if (playerBet != -1) {
                pot += playerBet;
            } else {
                toRemove.add(player);
            }
        }

        for (Player player : toRemove) {
            allPlayers.remove(player);
        }
    }

    public boolean foldCallRaise(int input) {

        switch (input) {

            case 1:
                user.setStack((int) (user.getStack() - (1 / 2D) * user.getBet()));
                return false;

            case 3:
                user.setBet(2 * user.getBet());
                break;
        }

        return true;
    }

    public void revealCards() {

        Terminal.showHand(dealer);
        Terminal.printLine();

        Terminal.showHand(user);
        new HandCalculator(this, user);
        Terminal.printLine();

        for (Player opponent : opponents) {
            Terminal.showHand(opponent);
            new HandCalculator(this, opponent);
            Terminal.printLine();
        }
    }

    public void compareHands() {

        for (int i = 0; i < 6 && allPlayers.size() > 1; i++) {

            ArrayList<Player> toRemove = new ArrayList<>();

            int max = 0;
            for (Player player : allPlayers) {
                max = Math.max(max, player.getHandCode(i));
            }

            for (Player player : allPlayers) {
                if (player.getHandCode(i) < max) {
                    toRemove.add(player);
                }
            }

            for (Player player : toRemove) {
                allPlayers.remove(player);
            }
        }

        if (allPlayers.size() == 1) {
            win(allPlayers.get(0));
        } else {
            draw(allPlayers);
        }
    }

    public String[][] getGameBoard() {
        return gameBoard;
    }

    public void win(Player player) {
        Terminal.sayWin(player);
        player.receivesChips(pot);
    }

    public void draw(ArrayList<Player> players) {
        Terminal.sayDraw(players);
        for (Player player : players) {
            player.receivesChips(pot / players.size());
        }
    }
}