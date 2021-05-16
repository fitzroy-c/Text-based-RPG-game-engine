package CommandParser;

public class CommandTokenizer {

    private String _buffer;		//save text
    private Token currentToken;	//save token extracted from next()

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
     *  This function will find and extract a next token from {@code _buffer} and
     *  save the token to {@code currentToken}.
     */
    public void next() {
        _buffer = _buffer.trim(); // remove whitespace

        if(_buffer.isEmpty()) {
            currentToken = null;	// if there's no string left, set currentToken null and return
            return;
        }
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
            createString = new StringBuilder(createString.toString().toLowerCase());

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

            else if (createString.toString().equals("gold") | createString.toString().equals("golds") | createString.toString().equals("money"))
                currentToken = new Token(createString.toString(),Token.Type.GOLD);

            else if (createString.toString().equals("item")) // TODO: make checkHasItem, return true if this is a valid item name
                currentToken = new Token(createString.toString(),Token.Type.ITEM);
//            if (checkHasItem(createString))
//                currentToken = new Token(createString,Token.Type.ITEM);

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

            // TALK <talk | chat | speak>
            else if (createString.toString().equals("talk") | createString.toString().equals("chat") | createString.toString().equals("speak"))
                currentToken = new Token(createString.toString(),Token.Type.TALK);

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

            // RETREAT <retreat| run away | escape | escape away>
            else if (createString.toString().equals("retreat") | createString.toString().equals("run") | createString.toString().equals("escape")){
                count++; // skip white space
                while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                    createNextString.append(_buffer.charAt(count));
                    count++;
                }
                createNextString = new StringBuilder(createNextString.toString().toLowerCase());

                // RETREAT only if the command is <retreat| escape>
                if (createNextString.length() == 0 && !createString.toString().equals("run"))
                    currentToken = new Token(createString.toString(),Token.Type.RETREAT);
                // RETREAT only if the command is <run away | retreat away | escape away>
                if (createNextString.toString().equals("away"))
                    currentToken = new Token(createString+" "+createNextString,Token.Type.RETREAT);
            }

            // DEFENCE
            else if (createString.toString().equals("defence"))
                currentToken = new Token(createString.toString(),Token.Type.DEFENCE);

            // HELP
            else if (createString.toString().equals("help"))
                currentToken = new Token(createString.toString(),Token.Type.HELP);

            else
                currentToken = new Token(createString.toString(),Token.Type.ERROR);
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