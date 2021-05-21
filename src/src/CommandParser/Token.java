package CommandParser;

/**
 * Declaring types of command, a subclass for tokenizer
 * @author Guanming Ou
 */
public class Token {
    public enum Type {UNKNOWN, ERROR, SAVE, EXIT, DETECT, DIRECTION_ACTION, DIRECTION, CONSUME_ACTION, TAKE_ACTION, DROP_ACTION,
        ITEM, TALK, SHOP, VIEW_ACTION, STAT, BACKPACK, ATTACK, RETREAT_ACTION, BRIBE, HELP};

    private String _token = "";
    private Type _type = Type.UNKNOWN;

    public Token(String token, Type type) {
        _token = token;
        _type = type;
    }

    public String token() {
        return _token;
    }

    public Type type() {
        return _type;
    }
}
