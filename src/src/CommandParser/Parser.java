package CommandParser;

import NPCs.Monster;
import Player.Player;
import Player.Bag;

import java.util.List;

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
    Player player;
    CommandTokenizer _tokenizer;

    public Parser(CommandTokenizer tokenizer, Player player) {
        this.player = player;
        _tokenizer = tokenizer;
    }

    // <command> := <save>|<exit>|<detect>|<move-command>|<take-command>|<drop command>|<talk-command>|
    //              <attack-command>|<retreat-command>|<defence-command>|<view-command>
    public void parseCommand() {
        // <save>    := save | save game
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.SAVE){
            if (!this._tokenizer.hasNext()){
                //            player.save();
                System.out.println("game saved");
            }
        }

        // <exit>    := exit | exit game
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.EXIT) {
            if (!this._tokenizer.hasNext()){
                // TODO: save the game and back to the main menu
//               player.save();
                System.out.println("exited game");
            }
        }

        // <detect>  := detect
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DETECT) {
            if (!this._tokenizer.hasNext()){
                // TODO: save the game and back to the main menu, need player to have location that stores monster
//            Monster monster = new Monster();
//            if (monster == null) {
//                System.out.println("There is a "+ monster.getName() +" around you, WATCH OUT!");
//            } else {
//                System.out.println("There is no monster near you, phew～");
//            }
                System.out.println("detected monster");
            } else {
                callError();
            }
        }

        // <move-command> := <move_action> <direction> | <direction>
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DIRECTION){
            if (!this._tokenizer.hasNext()){
                // TODO: complete this method which make the player go to the direction.
                System.out.println("headed to "+this._tokenizer.current().token()+" direction");
            }
        }
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DIRECTION_ACTION){
            this._tokenizer.next();
            if (this._tokenizer.hasNext()){
                if (this._tokenizer.current().type()==Token.Type.DIRECTION){
                    // TODO: complete this method which make the player go to the direction.
                    System.out.println("2 headed to "+this._tokenizer.current().token()+" direction");
                } else {
                    callError();
                }
            } else {
                callError();
            }
        }

        // <take-command> := <take_action> <item-name> | <take_action> gold
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.TAKE_ACTION){
            this._tokenizer.next();
            if (this._tokenizer.hasNext()){
                if (this._tokenizer.current().type()==Token.Type.GOLD){
                    // TODO: complete this method which make the player gain the gold inside the room
                    System.out.println("player took the gold");
                }
                else if (this._tokenizer.current().type()==Token.Type.ITEM){
                    // TODO: complete this method which make the player pick up the item inside the room
                    System.out.println("player took "+this._tokenizer.current().token());
                } else {
                    callError();
                }
            } else {
                callError();
            }
        }

        // <drop-command> := <drop_action> <item-name>
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DROP_ACTION){
            this._tokenizer.next();
            if (this._tokenizer.hasNext()){
                if (this._tokenizer.current().type()==Token.Type.ITEM){
                    // TODO: complete this method which make the player put back the item inside the room
                    System.out.println("player dropped the item");
                } else {
                    callError();
                }
            } else {
                callError();
            }
        }

        // <talk-command> := talk | chat | speak
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.TALK){
            if (!this._tokenizer.hasNext()){
                // TODO: complete this method which talk to NPC at the coordinate
                System.out.println("player talked to npc");
            }
        }

        // <view-command> := <look-action> <stat> | <look-action> <bag> | <stat> | <bag>
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.STAT){
            if (!this._tokenizer.hasNext()){
                // TODO: complete this method which print out player's stat
                System.out.println("player stat");
            }
        }
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.BACKPACK){            this._tokenizer.next();
            if (!this._tokenizer.hasNext()){
                // TODO: complete this method which print out player's backpack
                System.out.println("player backpack");
            }
        }
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.VIEW_ACTION){
            this._tokenizer.next();
            if (this._tokenizer.hasNext()){
                if (this._tokenizer.current().type()==Token.Type.STAT){
                    // TODO: complete this method which print out player's stat
                    System.out.println("player stat");
                }
                else if (this._tokenizer.current().type()==Token.Type.BACKPACK){
                    // TODO: complete this method which print out player's backpack
                    System.out.println("player backpack");
                } else {
                    callError();
                }
            } else {
                callError();
            }
        }

        // <attack-command> := attack
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.ATTACK){
            this._tokenizer.next();
            if (!this._tokenizer.hasNext()){
                // TODO: complete this method which attack to the monster
                System.out.println("player attacked");
            }
        }

        // <retreat-command> := retreat | run away | escape
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.RETREAT){
            this._tokenizer.next();
            if (!this._tokenizer.hasNext()){
                // TODO: complete this method which may let player escape from the fight
                System.out.println("player escaped");
            }
        }

        // <defence-command> := defence
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DEFENCE){
            this._tokenizer.next();
            if (!this._tokenizer.hasNext()){
                // TODO: complete this method which let player defense
                System.out.println("player defenced");
            }
        }

        // <help-command> := help
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.HELP){
            // TODO: complete this method which print out help menu
            System.out.println("help menu");
        }

        // this is printed when the command is not recognised.
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.ERROR){
            callError();
        }
    }

    public void callError() {
        System.out.println("message not recognised, please try again or use 'help'");
    }

    // test only, delete later
    public static void main(String[] args) {
        /**
         * tests done
         * - save       -> game saved
         * - save sds   -> error
         * - save sjbsj -> error
         * - save game  -> game saved
         * - go         -> error
         * - go north   -> headed to north direction
         * - go sds     -> error
         * - ss go sds  -> error
         * - north      -> headed to north direction
         * - south      -> headed to north direction
         * - xx south   -> error
         * - take       -> error
         * - take gold  -> player took the gold
         * - pick item  -> player took item
         * - drop item  -> player dropped the item
         * - drop dbsdbs-> error
         * - put down item -> player dropped the item
         * - put abandon item -> error
         * - talk       -> player talked to npc
         * - look       -> error
         * - look stats -> player stat
         * - look backpack -> player backpack
         * - bag        -> player backpack
         * - attack snsn -> error
         * - retreat    -> player escaped
         * - run        -> error
         * - run away   -> player escaped
         * - run sss    -> player escaped
         * - help       -> help menue
         * - defence    -> player defenced
         * - defence de -> error
         */
        Player ply = new Player("testname", 10,10,0);
        String cmd = "save game exit game";
        CommandTokenizer mt = new CommandTokenizer(cmd);
        new Parser(mt, ply).parseCommand();
    }
}
