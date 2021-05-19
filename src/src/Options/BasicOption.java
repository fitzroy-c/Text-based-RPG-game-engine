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

    public void printOut() {
        for (int i = 1; i < option.size()+1; i++) {
            Option op = option.get(i-1);
            if(op.getDescription() != null) {
                System.out.println("["+ i + "] " + op.getCommand() + " : " + op.getDescription());
            } else {
                System.out.println("["+ i + "] " + op.getCommand());
            }
        }
    }

    public boolean chooseOp(Token token) {
        //this.printOut();
        String command = token.token();

        if (token.type()== Token.Type.ERROR)
            return true;

        if (optionInterface.containsKey(command)) {
            return true;
        } else {
            System.out.println("I don't know what '" + command + "' means.");
            return false;
        }
    }

    public CommandTokenizer convert(CommandTokenizer t){
        CommandTokenizer cmdTok;
        String s = t.current().token();
        if (optionInterface.containsKey(s)) {
            String cmd = optionInterface.get(s).getCommand();
            cmdTok = new CommandTokenizer(cmd);
            return cmdTok;
        }
        return t;
    }

    public void showMenu() {
        for (int i = 0; i < option.size(); i++) {
            optionInterface.put(String.valueOf(i+1), option.get(i));
            optionInterface.put(option.get(i).getCommand(), option.get(i));
        }
    }

}

