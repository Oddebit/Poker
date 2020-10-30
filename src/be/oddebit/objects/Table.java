package be.oddebit.objects;

import be.oddebit.UserInterface.Terminal;

import java.util.Random;

public class Table {

    private String[][] gameBoard;

    Random random = new Random();

    Player player;
    Player opponent;
    Player dealer;

    public Table() {

        this.player = new Player("Player");
        this.opponent = new Player("Opponent");
        this.dealer = new Player("On the table");

        boolean play = true;
        while (play && player.getStack() > 0) {

            newGame();

            dealHand(player, 2);
            dealHand(opponent, 2);

            Terminal.showHand(player);
            player.setBet(Terminal.askBet(player));

            dealHand(dealer, 3);
            Terminal.showHand(dealer);

            if (foldCallRaise(Terminal.askFoldCallRaise())) {

                dealHand(dealer, 2);
                revealCards(player, opponent);
            }


            play = Terminal.askPlay();
        }
    }

    public void newGame() {

        Terminal.sayNewGame();

        this.player.clearHand();
        this.opponent.clearHand();
        this.dealer.clearHand();

        gameBoard = new String[4][13];

    }

    public void dealHand(Player player, int cards) {

        for (int i = 0; i < cards; i++) {

            boolean occupied = true;
            while (occupied) {

                int suit = random.nextInt(4);
                int value = random.nextInt(13);

                if (gameBoard[suit][value] == null) {

                    gameBoard[suit][value] = player.getName();
                    player.receivesCard(new Card(value + 1, suit + 1));
                    occupied = false;
                }
            }

        }
    }

    public boolean foldCallRaise(int input) {

        switch (input) {

            case 1:
                player.setStack(player.getStack() - player.getBet());
                return false;

            case 3:
                player.setBet(2 * player.getBet());
                break;
        }

        return true;
    }

    public void revealCards(Player player, Player opponent) {

        Terminal.showHand(dealer);
        Terminal.printLine();

        Terminal.showHand(player);
        new HandCalculator(this, player);
        Terminal.printLine();

        Terminal.showHand(opponent);
        new HandCalculator(this, opponent);
        Terminal.printLine();
    }

    public String[][] getGameBoard() {
        return gameBoard;
    }
}