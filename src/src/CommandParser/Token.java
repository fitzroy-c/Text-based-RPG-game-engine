package CommandParser;

/**
 * <command> := <save>|<exit>|<detect>|<move-command>|<take-command>|<drop command>|<talk-command>|
 *              <attack-command>|<retreat-command>|<defence-command>|<view-command>
 * <save>    := save | save game
 * <exit>    := exit | exit game
 * <detect>  := detect
 * <move-command> := <move_action> <direction> | <direction>
 * <move_action> := go | move | head
 * <direction> := north | south | east | west
 * <take-command> := <take_action> <item-name> | <take_action> gold
 * <item-name> := "item inside the room"
 * <take_action> := take | pick
 * <drop-command> := <drop_action> <item-name>
 * <drop_action> := drop | put down | abandon
 * <talk-command> := talk | chat | speak
 * <view-command> := <look-action> stats | <look-action> backpack
 * <look-action> := look | view | see | browse
 * <attack-command> := attack
 * <retreat-command> := retreat | run away | escape
 * <defence-command> := defence
 */

public class Token {
    public enum Type {UNKNOWN, ERROR, SAVE, EXIT, DETECT, DIRECTION_ACTION, DIRECTION, TAKE_ACTION, DROP_ACTION,
    GOLD, ITEM, TALK, VIEW_ACTION, STAT, BACKPACK, ATTACK, RETREAT, DEFENCE};

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
