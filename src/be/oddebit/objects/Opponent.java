package be.oddebit.objects;

import be.oddebit.UserInterface.Terminal;
import be.oddebit.game.Utility;

public class Opponent extends Player {

    public Opponent(String name) {
        super(name);
    }

    @Override
    public int bet(int bet) {

        int x;
        int y;

        if(this.isHandSuited()) {

            x = 14 - Math.max(this.getCard(0).getValue(), this.getCard(1).getValue());
            y = 14 - Math.min(this.getCard(0).getValue(), this.getCard(1).getValue());

        } else {

            x = 14 - Math.min(this.getCard(0).getValue(), this.getCard(1).getValue());
            y = 14 - Math.max(this.getCard(0).getValue(), this.getCard(1).getValue());

        }

        if (super.getAggressiveness() <= Utility.probabilityArray[y][x]) {
            Terminal.sayCall(this);
            this.setStack(getStack() - bet);
            return bet;
        } else {
            Terminal.sayFold(this);
            return -1;
        }
    }
}
