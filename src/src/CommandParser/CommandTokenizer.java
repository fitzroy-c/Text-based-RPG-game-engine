package CommandParser;

import Player.Item;

import java.util.List;

public class CommandTokenizer {

    private String _buffer;		//save text
    private Token currentToken;	//save token extracted from next()
    private List<Item> ItemBook= Item.initializeItemBook();

    /**
     *  Tokenizer class constructor
     *  The constructor extracts the first token and save it to currentToken
     *  **** please do not modify this part ****
     */
    public CommandTokenizer(String text) {
        _buffer = text;		// save input text (string)
        next();		        // extracts the first token.
    }
    /**
     * check if a given string is a valid item name in a list with all possible item
     * @author: Yixiang Yin, modified by Zihong Yuan
     **/
    public static boolean isItem(List<Item> reference, String nameToBeCheck){
        for (Item i: reference){
            String name = i.name.toLowerCase();
            if (name.equals(nameToBeCheck.toLowerCase())) return true;
        }
        return false;
    }
    /**
     *  This function will find and extract a next token from {@code _buffer} and
     *  save the token to {@code currentToken}.
     */
    public void next() {
        Boolean checkingItem = true; // make sure the while loop is working

        _buffer = _buffer.trim(); // remove whitespace

        if(_buffer.isEmpty()) {
            currentToken = null;	// if there's no string left, set currentToken null and return
            return;
        }

        // This allows user use corresponding digit
        // to choose command.
        // By Zihong Yuan
        if (_buffer.matches("^[0-9]+$")) {
            currentToken = new Token(_buffer,Token.Type.ERROR);}

        char firstChar = _buffer.charAt(0);
        StringBuilder createString = new StringBuilder();
        StringBuilder createNextString = new StringBuilder();

        if (Character.isLetter(firstChar)) {
            // create the first word
            int count = 0;
            while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                createString.append(_buffer.charAt(count));
                count++;
            }
            createString = new StringBuilder(createString.toString());

            // SAVE <save | save game>
            if (createString.toString().equals("save")) {
                count++; // skip white space
                while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                    createNextString.append(_buffer.charAt(count));
                    count++;
                }
                createNextString = new StringBuilder(createNextString.toString().toLowerCase());

                // save only if the command is 'save'
                if (createNextString.length() == 0)
                    currentToken = new Token(createString.toString(),Token.Type.SAVE);
                // save only if the command is 'save game'
                if (createNextString.toString().equals("game"))
                    currentToken = new Token(createString+" "+createNextString,Token.Type.SAVE);
            }

            // EXIT <exit | exit game>
            else if (createString.toString().equals("exit")){
                count++; // skip white space
                while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                    createNextString.append(_buffer.charAt(count));
                    count++;
                }
                createNextString = new StringBuilder(createNextString.toString().toLowerCase());

                // save only if the command is 'exit'
                if (createNextString.length() == 0)
                    currentToken = new Token(createString.toString(),Token.Type.EXIT);
                // save only if the command is 'exit game'
                if (createNextString.toString().equals("game"))
                    currentToken = new Token(createString+" "+createNextString,Token.Type.EXIT);
            }

            // DETECT <detect>
            else if (createString.toString().equals("detect"))
                currentToken = new Token(createString.toString(),Token.Type.DETECT);

            // DIRECTION_ACTION <go | move | head>
            else if (createString.toString().equals("go") | createString.toString().equals("move") | createString.toString().equals("head"))
                currentToken = new Token(createString.toString(),Token.Type.DIRECTION_ACTION);

            // DIRECTION <north | south | east | west>
            else if (createString.toString().equals("north") | createString.toString().equals("south") | createString.toString().equals("east")| createString.toString().equals("west"))
                currentToken = new Token(createString.toString(),Token.Type.DIRECTION);

            // TAKE_ACTION <pick | pick up>
            else if (createString.toString().equals("take") | createString.toString().equals("pick"))
                currentToken = new Token(createString.toString(),Token.Type.TAKE_ACTION);
            // ITEM
            else if (isItem(this.ItemBook,  createString.toString()))
                currentToken = new Token(createString.toString(),Token.Type.ITEM);

            // DROP_ACTION <drop | put down | abandon>
            else if (createString.toString().equals("drop") | createString.toString().equals("abandon") | createString.toString().equals("put")){
                count++; // skip white space
                while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                    createNextString.append(_buffer.charAt(count));
                    count++;
                }
                createNextString = new StringBuilder(createNextString.toString().toLowerCase());

                // save only if the command is 'drop | abandon'
                if (createString.toString().equals("drop")| createString.toString().equals("abandon") | createNextString.length() == 0)
                    currentToken = new Token(createString.toString(),Token.Type.DROP_ACTION);
                // save only if the command is 'put down'
                if (createString.toString().equals("put") && createNextString.toString().equals("down"))
                    currentToken = new Token(createString+" "+createNextString,Token.Type.DROP_ACTION);
            }
            // CONSUME_ACTION <consume | eat | drink | use>
            else if (createString.toString().equals("consume") | createString.toString().equals("eat") | createString.toString().equals("drink")|createString.toString().equals("use"))
                currentToken = new Token(createString.toString(),Token.Type.CONSUME_ACTION);

            // TALK <talk | chat | speak>
            else if (createString.toString().equals("talk") | createString.toString().equals("chat") | createString.toString().equals("speak"))
                currentToken = new Token(createString.toString(),Token.Type.TALK);

            //SHOP := shop | buy | purchase | shopping | trade
            else if (createString.toString().equals("shop") | createString.toString().equals("buy") | createString.toString().equals("purchase")|createString.toString().equals("shopping")|createString.toString().equals("trade"))
                currentToken = new Token(createString.toString(),Token.Type.SHOP);

            // VIEW_ACTION
            else if (createString.toString().equals("look") | createString.toString().equals("view") | createString.toString().equals("see") | createString.toString().equals("browse"))
                currentToken = new Token(createString.toString(),Token.Type.VIEW_ACTION);

            // STAT
            else if (createString.toString().equals("stats") | createString.toString().equals("stat") | createString.toString().equals("statistic"))
                currentToken = new Token(createString.toString(),Token.Type.STAT);

            // BACKPACK
            else if (createString.toString().equals("backpack") | createString.toString().equals("bag") )
                currentToken = new Token(createString.toString(),Token.Type.BACKPACK);

            // ATTACK
            else if (createString.toString().equals("attack"))
                currentToken = new Token(createString.toString(),Token.Type.ATTACK);

            // RETREAT <retreat| run away | escape | escape away | back>
            else if (createString.toString().equals("retreat") | createString.toString().equals("run") | createString.toString().equals("escape")
                    |createString.toString().equals("back")){
                count++; // skip white space
                while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                    createNextString.append(_buffer.charAt(count));
                    count++;
                }
                createNextString = new StringBuilder(createNextString.toString().toLowerCase());

                // RETREAT only if the command is <retreat| escape>
                if (createNextString.length() == 0 && !createString.toString().equals("run"))
                    currentToken = new Token(createString.toString(),Token.Type.RETREAT_ACTION);
                // RETREAT only if the command is <run away | retreat away | escape away>
                if (createNextString.toString().equals("away"))
                    currentToken = new Token(createString+" "+createNextString,Token.Type.RETREAT_ACTION);
            }

            // BRIBE
            else if (createString.toString().equals("bribe"))
                currentToken = new Token(createString.toString(),Token.Type.BRIBE);

            // HELP
            else if (createString.toString().equals("help"))
                currentToken = new Token(createString.toString(),Token.Type.HELP);

            else{
                if (checkingItem){ // items may have multiple spaces
                    StringBuilder itemString = createString;
                    int itemStringCount = count;
                    Boolean itemFound = false;

                    while (checkingItem){
                        if (itemStringCount == _buffer.length())
                            checkingItem = false;
                        // check if the string is a item name
                        if (isItem(this.ItemBook,  itemString.toString())){
                            itemFound = true;
                            break;
                        }
                        itemStringCount++; // skip the white space
                        StringBuilder tempItemString = new StringBuilder();
                        // add next word into string (define by space separation)
                        while (itemStringCount < _buffer.length() && _buffer.charAt(itemStringCount) != ' ') { // get separate words
                            tempItemString.append(_buffer.charAt(itemStringCount));
                            itemStringCount++;
                        }
                        if (tempItemString.length() != 0)
                            tempItemString.insert(0,' ');
                        itemString.append(tempItemString);
                    }
                    if (itemFound)
                        currentToken = new Token(itemString.toString(),Token.Type.ITEM);
                }
                if (currentToken != null && currentToken.type() != Token.Type.ITEM)
                    currentToken = new Token(createString.toString(),Token.Type.ERROR);
            }
        }
        // if there is no other type match, it is a unknown command
        if (currentToken == null){
            if (createNextString.length()==0)
                currentToken = new Token(createString.toString(),Token.Type.ERROR);
            else
                currentToken = new Token(createString+" "+createNextString.toString(),Token.Type.ERROR);
        }

        // Remove the extracted token from buffer
        int tokenLen = currentToken.token().length();
        _buffer = _buffer.substring(tokenLen);
    }

    /**
     *  returned the current token extracted by {@code next()}
     *
     * @return type: Token
     */
    public Token current() {
        return currentToken;
    }

    /**
     *  check whether there still exists another tokens in the buffer or not
     *
     * @return type: boolean
     */
    public boolean hasNext() {
        return currentToken != null;
    }
}
