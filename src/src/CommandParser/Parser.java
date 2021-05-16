package CommandParser;

import Player.Player;
import Player.Bag;

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
    public boolean parseCommand() {
        boolean cmdExecuted = false; // allow only one command to execute per command

        // Save the game
        // <save>    := save | save game
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.SAVE){
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                System.out.println("saving...");
                player.save();
                System.out.println("game saved!");
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                System.out.println("saving...");
                player.save();
                System.out.println("game saved!");
                cmdExecuted = true;
            }
        }
        // Save the game, and back to the main menu
        // <exit>    := exit | exit game
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.EXIT) {
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                System.out.println("saving...");
                player.save();
                System.out.println("game exited");
                cmdExecuted = true;
                return false;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                System.out.println("saving...");
                player.save();
                System.out.println("game exited");
                cmdExecuted = true;
                return false;
            }
        }

        // <detect>  := detect
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DETECT) {
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                // TODO: save the game and back to the main menu, need player to have location that stores monster
//            Monster monster = new Monster();
//            if (monster == null) {
//                System.out.println("There is a "+ monster.getName() +" around you, WATCH OUT!");
//            } else {
//                System.out.println("There is no monster near you, phew～");
//            }
                System.out.println("detected monster");
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: save the game and back to the main menu, need player to have location that stores monster
//            Monster monster = new Monster();
//            if (monster == null) {
//                System.out.println("There is a "+ monster.getName() +" around you, WATCH OUT!");
//            } else {
//                System.out.println("There is no monster near you, phew～");
//            }
                System.out.println("detected monster");
                cmdExecuted = true;
            }
        }

        // <move-command> := <move_action> <direction> | <direction>
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DIRECTION){
            Token current = this._tokenizer.current();
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                // TODO: complete this method which make the player go to the direction.
                System.out.println("headed to "+this._tokenizer.current().token()+" direction");
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which make the player go to the direction.
                System.out.println("headed to "+current.token()+" direction");
                cmdExecuted = true;
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
            if (this._tokenizer.hasNext() == false){
                // TODO: complete this method which talk to NPC at the coordinate
                System.out.println("player talked to AbnormalPoint");
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which talk to NPC at the coordinate
                System.out.println("player talked to AbnormalPoint");
                cmdExecuted = true;
            }
        }

        // <view-command> := <look-action> <stat> | <look-action> <bag> | <stat> | <bag>
        // Look at player's stat with <stat>
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.STAT){
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                // TODO: complete this method which print out player's stat
                System.out.println("player stat");
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which print out player's stat
                System.out.println("player stat");
                cmdExecuted = true;
            }
        }
        // Look at player's bag with <bag>
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.BACKPACK){
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                // TODO: complete this method which print out player's backpack
                System.out.println("player backpack");
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                player.getBag().showMyBag();
                cmdExecuted = true;
            }
        }
        // look at player's stat or bag via <look-action> <stat> | <look-action> <bag>
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.VIEW_ACTION){
            cmdExecuted = true;
            this._tokenizer.next();
            if (this._tokenizer.hasNext()){
                if (this._tokenizer.current().type()==Token.Type.STAT){
                    // TODO: complete this method which print out player's stat
                    System.out.println("player stat");
                }
                else if (this._tokenizer.current().type()==Token.Type.BACKPACK){
                    player.getBag().showMyBag();
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
            if (this._tokenizer.hasNext() == false){
                // TODO: complete this method which attack to the monster
                System.out.println("player attacked");
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which attack to the monster
                System.out.println("player attacked");
                cmdExecuted = true;
            }
        }

        // <retreat-command> := retreat | run away | escape
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.RETREAT){
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                // TODO: complete this method which may let player escape from the fight
                System.out.println("player escaped");
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which may let player escape from the fight
                System.out.println("player escaped");
                cmdExecuted = true;
            }
        }

        // <defence-command> := defence
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DEFENCE){
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                // TODO: complete this method which let player defense
                System.out.println("player defenced");
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                // TODO: complete this method which let player defense
                System.out.println("player defenced");
                cmdExecuted = true;
            }
        }

        // <help-command> := help
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.HELP){
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                printHelpMenu();
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                printHelpMenu();
                cmdExecuted = true;
            }
        }

        // this is printed when the command is not recognised.
        if (this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.ERROR){
            callError();
        }

        return true;
    }

    public void callError() {
        System.out.println("message not recognised, please try again or use 'help'");
    }

    public void printHelpMenu(){
        System.out.println("Help menu: \n" +
                "<save | save game>: Save current game\n"+
                "<exit | exit game>: Exit current game\n"+
                "<detect>: detect if monster is near\n"+
                "<talk | chat | speak>: Talk to npc\n"+
                "<move-command><direction>: move to given direction\n"+
                "   <move-command>:= go | move | head\n"+
                "   <direction>   := north | south | east | west\n"+
                "<take_action><item-name>: take a item inside current coordinate (if any)\n"+
                "   <take_action> := take | pick\n"+
                "   <item-name>   := the available item name\n"+
                "<take_action><gold>: take the gold inside current coordinate (if any)\n"+
                "   <gold>        := gold | golds | money\n"+
                "<drop_action><item-name>: put back item inside current coordinate (if any)\n"+
                "   <drop_action> := drop | put down | abandon\n"+
                "   <item-name>   := the available item name\n"+
                "<look_action><stat> | <stat> : view player's stat\n"+
                "   <look_action> := look | view | see | browse\n"+
                "   <stat>        := stat | stats | statistic\n"+
                "<look_action><bag> | <bag> : view player's bag\n"+
                "<attack>: attack enemy\n"+
                "<defence>: defence \n"+
                "<retreat | run away | escape>: escape from combat\n"+
                "<help>: Prints this info\n");
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
         * - north go east -> headed to north direction
         * - xx south   -> error
         * - take       -> error
         * - take gold  -> player took the gold
         * - pick item  -> player took item
         * - drop item  -> player dropped the item
         * - drop dbsdbs-> error
         * - put down item -> player dropped the item
         * - put abandon item -> error
         * - talk       -> player talked to AbnormalPoint
         * - look       -> error
         * - look stats -> player stat
         * - look backpack -> player backpack
         * - bag        -> player backpack
         * - attack snsn -> error
         * - retreat    -> player escaped
         * - run        -> error
         * - run away   -> player escaped
         * - run sss    -> player escaped
         * - help       -> help menu
         * - defence    -> player defenced
         * - defence de -> error
         * - retreat defence -> error
         * - go defence
         * - save game exit game -> game saved
         * - go defence -> error
         */
        Player ply = new Player("testname", 10,10,0);
        String cmd = "north go east";
        CommandTokenizer mt = new CommandTokenizer(cmd);
        new Parser(mt, ply).parseCommand();
    }
}
