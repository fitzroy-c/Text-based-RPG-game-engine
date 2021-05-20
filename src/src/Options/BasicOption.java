package Options;

import CommandParser.CommandTokenizer;
import CommandParser.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The class is used to construct basic option menu.
 *
 * @author Zihong Yuan
 */
public class BasicOption {
    public List<Option> option = new ArrayList<>();
    HashMap<String, Option> optionInterface = new HashMap();
    public List<Token.Type> tokenType = new ArrayList<>();

    /**
     * The method can print out options for player in
     * a specific format.
     *
     * @author Zihong Yuan
     */
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

    /**
     * The method can filter invalid user inputs,
     * this made option menu more compact.
     * Noticed, it allows single digit input become valid.
     *
     * @author Zihong Yuan
     * @param token user's input after tokenize.
     * @return true if input is valid.
     */
    public boolean chooseOp(Token token) {
        //this.printOut();
        String command = token.token();
        Token.Type type = token.type();

        if (token.type()== Token.Type.ERROR)
            return true;
        if (tokenType.contains(type)) {
            return true;
        } else {
            System.out.println("I don't know what '" + command + "' means.");
            return false;
        }
    }

    /**
     * The method converts single digit input to
     * valid command which Parser.java can take
     * as input.
     *
     * @author Zihong Yuan
     * @param command corresponding digit that indicate valid command.
     * @return valid command that can be recognized by program.
     */
    public CommandTokenizer convert(CommandTokenizer command){
        CommandTokenizer cmdTok;
        Token to = command.current();
        if (optionInterface.containsKey(to.token()) && to.type()== Token.Type.ERROR) {
            String cmd = optionInterface.get(to.token()).getCommand();
            cmdTok = new CommandTokenizer(cmd);
            return cmdTok;
        }
        return command;
    }

    /**
     * The method rearrange all commands which are valid at current surrounding
     * into a HashMap. This HashMap can be used to find correct command through
     * command name.
     *
     * @author Zihong Yuan
     */
    public void showMenu() {
        for (int i = 0; i < option.size(); i++) {
            optionInterface.put(String.valueOf(i+1), option.get(i));
            optionInterface.put(option.get(i).getCommand(), option.get(i));
        }
    }

}

