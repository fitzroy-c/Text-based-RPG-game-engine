package CommandParser;

import AbnormalPoints.Monster;
import Options.Control;
import Player.Player;
import Player.Bag;
import Player.Item;
import navigation.Coordinate;

import java.util.List;

/**
 * Grammar:
 * <command> := <save>|<exit>|<detect>|<move-command>|<take-command>|<shop-command>|<drop command>|<talk-command>|
 *              <consume-command>|<attack-command>|<retreat-command>|<defence-command>|<view-command>|<help-command>
 * <save>    := save | save game
 * <exit>    := exit | exit game
 * <detect>  := detect
 * <move-command> := <move_action> <direction> | <direction>
 * <move_action> := go | move | head
 * <direction> := north | south | east | west
 * <consume-command> := <consume-action> <item-name>
 * <consume-action> := consume | eat | drink | use
 * <take-command> := <take_action> <item-name> | <take_action> <gold | golds | money>
 * <shop-command> := shop | buy | purchase | shopping | trade
 * <item-name> := "item inside the room"
 * <take_action> := take | pick
 * <drop-command> := <drop_action> <item-name>
 * <drop_action> := drop | put down | abandon
 * <talk-command> := talk | chat | speak
 * <view-command> := <look-action> <stat> | <look-action> <bag> | <stat> | <bag>
 * <stat>         := stat | stats | statistic
 * <bag>          := <backpack | bag>
 * <look-action> := look | view | see | browse
 * <attack-command> := attack
 * <retreat-command> := retreat | run away | escape | back
 * <bribe-command> := bribe
 * <help-command> := help
 *
 * Specification
 * - Will only execute the first command if there are two command inside the user input
 */


public class Parser {
    Player player;
    CommandTokenizer _tokenizer;
    //Monster monster;
    //Item item;


    public Parser(CommandTokenizer tokenizer, Player player) {
        this.player = player;
        _tokenizer = tokenizer;
    }
    /*
    public Parser(CommandTokenizer tokenizer, Player player, Monster monster) {
        this.player = player;
        _tokenizer = tokenizer;
        this.monster = monster;
    }

     */

    // <command> := <save>|<exit>|<detect>|<move-command>|<take-command>|<drop command>|<talk-command>|
    //              <attack-command>|<retreat-command>|<bribe-command>|<view-command>
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
                System.out.println(player.checkMonster());
                this.player.detectNPCAroundPlayer();
                if (player.getMap_bagData().get(player.getPlace().getCoordinate())==null) System.out.println("Nothing on the floor");
                else player.getMap_bagData().get(player.getPlace().getCoordinate()).showPlaceBag2();
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                System.out.println(player.checkMonster());
                cmdExecuted = true;
            }
        }

        // <move-command> :=  <direction>
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DIRECTION){
            Token current = this._tokenizer.current();
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                System.out.println(player.goToDirection(current.token()));
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                System.out.println(player.goToDirection(current.token()));
                cmdExecuted = true;
            }
        }
        // <move-command> := <move_action> <direction>
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.DIRECTION_ACTION){
            cmdExecuted = true;
            this._tokenizer.next();
            if (this._tokenizer.hasNext()){
                if (this._tokenizer.current().type()==Token.Type.DIRECTION){
                    System.out.println(player.goToDirection(this._tokenizer.current().token()));
                } else {
                    callError();
                }
            } else {
                callError();
            }
        }

        // <consume-command> := <consume-action> <item-name>
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.CONSUME_ACTION){
            cmdExecuted = true;
            this._tokenizer.next();
            if (this._tokenizer.hasNext()){
                if (this._tokenizer.current().type()==Token.Type.ITEM){
                    System.out.println(player.consumeByItemName(this._tokenizer.current().token()));
                } else {
                    callError();
                }
            } else {
                callError();
            }
        }

        // <take-command> := <take_action> <item-name>
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.TAKE_ACTION){
            cmdExecuted = true;
            this._tokenizer.next();
            if (this._tokenizer.hasNext()){
                if (this._tokenizer.current().type()==Token.Type.ITEM){
                    System.out.println(player.getItemFromRoom(this._tokenizer.current().token()));
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
                    if (player.dropItemFromBag(this._tokenizer.current().token()))
                        System.out.println(this._tokenizer.current().token()+" is dropped");
                    else
                        System.out.println("Item of "+this._tokenizer.current().token()+" is not found from you bag");
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
                System.out.println(player.talk());
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                System.out.println(player.talk());
                cmdExecuted = true;
            }
        }

        // <shop-command> := shop | buy | purchase | shopping | trade
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.SHOP){
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                player.buyCommandHandler();
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                System.out.println(player.talk());
                cmdExecuted = true;
            }
        }

        // <view-command> := <look-action> <stat> | <look-action> <bag> | <stat> | <bag>
        // Look at player's stat with <stat>
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.STAT){
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                System.out.println(player.showPlayerStat());
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                System.out.println(player.showPlayerStat());
                cmdExecuted = true;
            }
        }
        // Look at player's bag with <bag>
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.BACKPACK){
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                player.getBag().showMyBag();
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
                    System.out.println(player.showPlayerStat());
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
                System.out.println(player.attack());
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                System.out.println(player.attack());
                cmdExecuted = true;
            }
        }
        //  Todo need fix
        //  * <retreat-command> := <retreat-action>
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.RETREAT_ACTION){
            cmdExecuted = true;
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                player.retreat();
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                player.retreat();
                cmdExecuted = true;
            }
        }

        // Todo need fix
        // <bribe-command> := bribe
        if (cmdExecuted==false && this._tokenizer.hasNext() && this._tokenizer.current().type()==Token.Type.BRIBE){
            this._tokenizer.next();
            if (this._tokenizer.hasNext() == false){
                player.bribe();
                cmdExecuted = true;
            } else if (this._tokenizer.hasNext() && !(this._tokenizer.current().type()==Token.Type.ERROR)){
                player.bribe();
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
                "<drop_action><item-name>: put back item inside current coordinate (if any)\n"+
                "   <drop_action> := drop | put down | abandon\n"+
                "   <item-name>   := the available item name\n"+
                "<look_action><stat> | <stat> : view player's stat\n"+
                "   <look_action> := look | view | see | browse\n"+
                "   <stat>        := stat | stats | statistic\n"+
                "<look_action><bag> | <bag> : view player's bag\n"+
                "<attack>: attack enemy\n"+
                "<defence>: defence \n"+
                "<retreat_action><direction>: escape from combat\n"+
                "    <retreat_action> := retreat | run away | escape\n"+
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
         * //- defence    -> player defenced
         * //- defence de -> error
         * - retreat defence -> error
         * - go bribe
         * - save game exit game -> game saved
         * - go bribe -> error
         * - take Lesser Healing Potion -> taked item
         */
        Player ply = new Player("testname");
        String cmd = "eddede";
        CommandTokenizer mt = new CommandTokenizer(cmd);
        new Parser(mt, ply).parseCommand();
    }
}
