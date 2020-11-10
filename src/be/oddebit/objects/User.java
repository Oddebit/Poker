package be.oddebit.objects;

import be.oddebit.UserInterface.Terminal;

public class User extends Player{

    public User(String name) {
        super(name);
    }

     @Override
    public int bet(int bet) {

        Terminal.showHand(this);
        int userBet = Terminal.askBet(this);
        this.setBet(userBet);
        return userBet;
     }
}
