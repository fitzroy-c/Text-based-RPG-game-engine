package CommandParser;

import NPCs.Monster;
import Player.Player;
import Player.Bag;

import java.util.List;

/**
 * Grammar:
 * <command> := <save>|<exit>|<detect>|<move-command>|<take-command>|<drop command>|<talk-command>|
 *              <attack-command>|<retreat-command>|<defence-command>|<view-command>|<help-command>
 * <save>    := save | save game
 * <exit>    := exit | exit game
 * <detect>  := detect
 * <move-command> := <move_action> <direction> | <direction>
 * <move_action> := go | move | head
 * <direction> := north | south | east | west
 * <take-command> := <take_action> <item-name> | <take_action> <gold | golds | money>
 * <item-name> := "item inside the room"
 * <take_action> := take | pick
 * <drop-command> := <drop_action> <item-name>
 * <drop_action> := drop | put down | abandon
 * <talk-command> := talk | chat | speak
 * <view-command> := <look-action> <stat> | <look-action> <bag> | <stat> | <bag>
 * <stat>         := <stat | stats | statistic>
 * <bag>          := <backpack | bag>
 * <look-action> := look | view | see | browse
 * <attack-command> := attack
 * <retreat-command> := retreat | run away | escape
 * <defence-command> := defence
 * <help-command> := help
 *
 * Specification
 * - Will only execute the first command if there are two command inside the user input
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
        boolean cmdExecuted = false; // allow only one command to execute per command
        // <save>    := save | save game
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.SAVE){
            this._tokenizer.next();
            if (!(this._tokenizer.current().type()==Token.Type.ERROR)){
                //            player.save();
                System.out.println("game saved");
                cmdExecuted = true;
            }
        }

        // <exit>    := exit | exit game
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.EXIT) {
            this._tokenizer.next();
            if (!(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: save the game and back to the main menu
//               player.save();
                System.out.println("exited game");
                cmdExecuted = true;
            }
        }

        // <detect>  := detect
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DETECT) {
            if (!(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: save the game and back to the main menu, need player to have location that stores monster
//            Monster monster = new Monster();
//            if (monster == null) {
//                System.out.println("There is a "+ monster.getName() +" around you, WATCH OUT!");
//            } else {
//                System.out.println("There is no monster near you, phew～");
//            }
                System.out.println("detected monster");
                cmdExecuted = true;
            } else {
                callError();
            }
        }

        // <move-command> := <move_action> <direction> | <direction>
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DIRECTION){
            cmdExecuted = true;
            this._tokenizer.next();
            if (!(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which make the player go to the direction.
                System.out.println("headed to "+this._tokenizer.current().token()+" direction");
            }
        }
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DIRECTION_ACTION){
            cmdExecuted = true;
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
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.TAKE_ACTION){
            cmdExecuted = true;
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
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DROP_ACTION){
            cmdExecuted = true;
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
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.TALK){
            this._tokenizer.next();
            if (!(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which talk to NPC at the coordinate
                System.out.println("player talked to npc");
                cmdExecuted = true;
            }
        }

        // <view-command> := <look-action> <stat> | <look-action> <bag> | <stat> | <bag>
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.STAT){
            this._tokenizer.next();
            if (!(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which print out player's stat
                System.out.println("player stat");
                cmdExecuted = true;
            }
        }
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.BACKPACK){
            this._tokenizer.next();
            if (!(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which print out player's backpack
                System.out.println("player backpack");
                cmdExecuted = true;
            }
        }
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.VIEW_ACTION){
            cmdExecuted = true;
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
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.ATTACK){
            this._tokenizer.next();
            if (!(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which attack to the monster
                System.out.println("player attacked");
                cmdExecuted = true;
            }
        }

        // <retreat-command> := retreat | run away | escape
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.RETREAT){
            this._tokenizer.next();
            if (!(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which may let player escape from the fight
                System.out.println("player escaped");
                cmdExecuted = true;
            }
        }

        // <defence-command> := defence
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DEFENCE){
            this._tokenizer.next();
            if (!(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which let player defense
                System.out.println("player defenced");
                cmdExecuted = true;
            }
        }

        // <help-command> := help
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.HELP){
            this._tokenizer.next();
            if (!(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which print out help menu
                System.out.println("help menu");
                cmdExecuted = true;
            }
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
         * - retreat defence -> error
         * - go defence
         * - save game exit game -> game saved
         * - go defence -> error
         */
        Player ply = new Player("testname", 10,10,0);
        String cmd = "go defence";
        CommandTokenizer mt = new CommandTokenizer(cmd);
        new Parser(mt, ply).parseCommand();
    }
}
