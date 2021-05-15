package CommandParser;

import java.util.Locale;

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

        if (Character.isLetter(firstChar)) {
            // create the first word
            StringBuilder createString = new StringBuilder();
            int count = 0;
            while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                createString.append(_buffer.charAt(count));
                count++;
            }
            createString = new StringBuilder(createString.toString().toLowerCase());

            // SAVE <save | save game>
            if (createString.toString().equals("save")) {
                count++; // skip white space
                StringBuilder creatNextString = new StringBuilder();
                while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                    creatNextString.append(_buffer.charAt(count));
                    count++;
                }
                creatNextString = new StringBuilder(creatNextString.toString().toLowerCase());

                if (creatNextString.toString().equals("game"))
                    currentToken = new Token(createString+" "+creatNextString,Token.Type.SAVE);
                else
                    currentToken = new Token(createString.toString(),Token.Type.SAVE);
            }

            // EXIT <exit | exit game>
            if (createString.toString().equals("exit")){
                count++; // skip white space
                StringBuilder creatNextString = new StringBuilder();
                while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                    creatNextString.append(_buffer.charAt(count));
                    count++;
                }
                creatNextString = new StringBuilder(creatNextString.toString().toLowerCase());

                if (creatNextString.toString().equals("game"))
                    currentToken = new Token(createString+" "+creatNextString,Token.Type.EXIT);
                else
                    currentToken = new Token(createString.toString(),Token.Type.EXIT);
            }

            // DETECT <detect>
            if (createString.toString().equals("detect"))
                currentToken = new Token(createString.toString(),Token.Type.DETECT);

            // DIRECTION_ACTION <go | move | head>
            if (createString.toString().equals("go") | createString.toString().equals("move") | createString.toString().equals("head"))
                currentToken = new Token(createString.toString(),Token.Type.DIRECTION_ACTION);

            // DIRECTION <north | south | east | west>
            if (createString.toString().equals("north") | createString.toString().equals("south") | createString.toString().equals("east")| createString.toString().equals("west"))
                currentToken = new Token(createString.toString(),Token.Type.DIRECTION);

            // TAKE_ACTION <pick | pick up>
            if (createString.toString().equals("take") | createString.toString().equals("pick"))
                currentToken = new Token(createString.toString(),Token.Type.TAKE_ACTION);

            if (createString.toString().equals("gold") | createString.toString().equals("golds") | createString.toString().equals("money"))
                currentToken = new Token(createString.toString(),Token.Type.GOLD);
                // TODO: make checkHasItem, return true if this is a valid item name
//            if (checkHasItem(createString))
//                currentToken = new Token(createString,Token.Type.ITEM);

            // DROP_ACTION <drop | put down | abandon>
            if (createString.toString().equals("drop") | createString.toString().equals("abandon") | createString.toString().equals("put")){
                count++; // skip white space
                StringBuilder creatNextString = new StringBuilder();
                while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                    creatNextString.append(_buffer.charAt(count));
                    count++;
                }
                creatNextString = new StringBuilder(creatNextString.toString().toLowerCase());

                if (creatNextString.toString().equals("down"))
                    currentToken = new Token(createString+" "+creatNextString,Token.Type.DROP_ACTION);
                else
                    currentToken = new Token(createString.toString(),Token.Type.DROP_ACTION);
            }

            // TALK <talk | chat | speak>
            if (createString.toString().equals("talk") | createString.toString().equals("chat") | createString.toString().equals("speak"))
                currentToken = new Token(createString.toString(),Token.Type.TALK);

            // VIEW_ACTION
            if (createString.toString().equals("look") | createString.toString().equals("view") | createString.toString().equals("see") | createString.toString().equals("browse"))
                currentToken = new Token(createString.toString(),Token.Type.VIEW_ACTION);

            // STAT
            if (createString.toString().equals("stat"))
                currentToken = new Token(createString.toString(),Token.Type.STAT);

            // BACKPACK
            if (createString.toString().equals("backpack"))
                currentToken = new Token(createString.toString(),Token.Type.BACKPACK);

            // ATTACK
            if (createString.toString().equals("attack"))
                currentToken = new Token(createString.toString(),Token.Type.ATTACK);

            // RETREAT
            if (createString.toString().equals("retreat") | createString.toString().equals("run") | createString.toString().equals("escape")){
                count++; // skip white space
                StringBuilder creatNextString = new StringBuilder();
                while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                    creatNextString.append(_buffer.charAt(count));
                    count++;
                }
                creatNextString = new StringBuilder(creatNextString.toString().toLowerCase());

                if (creatNextString.toString().equals("away"))
                    currentToken = new Token(createString+" "+creatNextString,Token.Type.RETREAT);
                else
                    currentToken = new Token(createString.toString(),Token.Type.RETREAT);
            }

            // DEFENCE
            if (createString.toString().equals("defence"))
                currentToken = new Token(createString.toString(),Token.Type.DEFENCE);

            // HELP
            if (createString.toString().equals("help"))
                currentToken = new Token(createString.toString(),Token.Type.HELP);

            else
                currentToken = new Token(createString.toString(),Token.Type.ERROR);

        }

        // ########## YOUR CODE ENDS HERE ##########

        // Remove the extracted token from buffer
        int tokenLen = currentToken.token().length();
        _buffer = _buffer.substring(tokenLen);
    }

    /**
     *  returned the current token extracted by {@code next()}
     *  **** please do not modify this part ****
     *
     * @return type: Token
     */
    public Token current() {
        return currentToken;
    }

    /**
     *  check whether there still exists another tokens in the buffer or not
     *  **** please do not modify this part ****
     *
     * @return type: boolean
     */
    public boolean hasNext() {
        return currentToken != null;
    }
}
