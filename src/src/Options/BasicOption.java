package Options;

import CommandParser.CommandTokenizer;
import CommandParser.Parser;
import CommandParser.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasicOption {
    public List<Option> option = new ArrayList<>();
    HashMap<String, Option> optionInterface = new HashMap();

    public void printOut(List<Option> o) {
        for (int i = 1; i < o.size()+1; i++) {
            Option op = o.get(i-1);
            if(op.getDescription() != null) {
                System.out.println("["+ i + "] " + op.getCommand() + " : " + op.getDescription());
            } else {
                System.out.println("["+ i + "] " + op.getCommand());
            }
        }
    }

    public boolean chooseOp(List<Option> o, Token token) {
        this.printOut(o);
        String command = token.token();
        if (optionInterface.containsKey(command)) {
            return true;
        } else {
            System.out.println("I don't know what '" + command + "' means.");
            return false;
        }
    }

    public void showMenu(List<Option> o) {
        for (int i = 0; i < o.size(); i++) {
            optionInterface.put(String.valueOf(i+1), o.get(i));
            optionInterface.put(o.get(i).getCommand(), o.get(i));
        }
    }

}

