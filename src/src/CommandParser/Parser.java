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

public class Parser {

    CommandTokenizer _tokenizer;

    public Parser(CommandTokenizer tokenizer) {
        _tokenizer = tokenizer;
    }











}
