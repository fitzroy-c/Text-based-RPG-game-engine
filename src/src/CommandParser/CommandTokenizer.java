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
            String createString = "";
            int count = 0;
            while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                createString = createString+""+_buffer.charAt(count);
                count++;
            }
            createString = createString.toLowerCase();

            // SAVE <save | save game>
            if (createString.equals("save")) {
                count++; // skip white space
                String creatNextString = "";
                while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                    creatNextString = creatNextString+""+_buffer.charAt(count);
                    count++;
                }
                creatNextString = creatNextString.toLowerCase();

                if (creatNextString.equals("game"))
                    currentToken = new Token(createString+" "+creatNextString,Token.Type.SAVE);
                else
                    currentToken = new Token(createString,Token.Type.SAVE);
            }

            // EXIT <exit | exit game>
            if (createString.equals("exit")){
                count++; // skip white space
                String creatNextString = "";
                while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                    creatNextString = creatNextString+""+_buffer.charAt(count);
                    count++;
                }
                creatNextString = creatNextString.toLowerCase();

                if (creatNextString.equals("game"))
                    currentToken = new Token(createString+" "+creatNextString,Token.Type.EXIT);
                else
                    currentToken = new Token(createString,Token.Type.EXIT);
            }

            // DETECT <detect>
            if (createString.equals("detect"))
                currentToken = new Token(createString,Token.Type.DETECT);

            // DIRECTION_ACTION <go | move | head>
            if (createString.equals("go") | createString.equals("move") | createString.equals("head"))
                currentToken = new Token(createString,Token.Type.DIRECTION_ACTION);

            // DIRECTION <north | south | east | west>
            if (createString.equals("north") | createString.equals("south") | createString.equals("east")| createString.equals("west"))
                currentToken = new Token(createString,Token.Type.DIRECTION);

            // TAKE_ACTION <pick | pick up>
            if (createString.equals("take") | createString.equals("pick"))
                currentToken = new Token(createString,Token.Type.TAKE_ACTION);

            if (createString.equals("gold"))
                currentToken = new Token(createString,Token.Type.GOLD);
                // TODO: make checkHasItem, return true if this is a valid item name
//            if (checkHasItem(createString))
//                currentToken = new Token(createString,Token.Type.ITEM);

            // DROP_ACTION <drop | put down | abandon>
            if (createString.equals("drop") | createString.equals("abandon") | createString.equals("put")){
                count++; // skip white space
                String creatNextString = "";
                while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                    creatNextString = creatNextString+""+_buffer.charAt(count);
                    count++;
                }
                creatNextString = creatNextString.toLowerCase();

                if (creatNextString.equals("down"))
                    currentToken = new Token(createString+" "+creatNextString,Token.Type.DROP_ACTION);
                else
                    currentToken = new Token(createString,Token.Type.DROP_ACTION);
            }

            // TALK <talk | chat | speak>
            if (createString.equals("talk") | createString.equals("chat") | createString.equals("speak"))
                currentToken = new Token(createString,Token.Type.TALK);

            // VIEW_ACTION
            if (createString.equals("look") | createString.equals("view") | createString.equals("see") | createString.equals("browse"))
                currentToken = new Token(createString,Token.Type.VIEW_ACTION);

            // STAT
            if (createString.equals("stat"))
                currentToken = new Token(createString,Token.Type.STAT);

            // BACKPACK
            if (createString.equals("backpack"))
                currentToken = new Token(createString,Token.Type.BACKPACK);

            // ATTACK
            if (createString.equals("attack"))
                currentToken = new Token(createString,Token.Type.ATTACK);

            // RETREAT
            if (createString.equals("retreat") | createString.equals("run") | createString.equals("escape")){
                count++; // skip white space
                String creatNextString = "";
                while (count < _buffer.length() && Character.isLetter(_buffer.charAt(count))) {
                    creatNextString = creatNextString+""+_buffer.charAt(count);
                    count++;
                }
                creatNextString = creatNextString.toLowerCase();

                if (creatNextString.equals("away"))
                    currentToken = new Token(createString+" "+creatNextString,Token.Type.RETREAT);
                else
                    currentToken = new Token(createString,Token.Type.RETREAT);
            }

            // DEFENCE
            if (createString.equals("defence"))
                currentToken = new Token(createString,Token.Type.DEFENCE);

            else
                currentToken = new Token(createString,Token.Type.ERROR);
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
