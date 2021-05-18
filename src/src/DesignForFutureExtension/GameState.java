package DesignForFutureExtension;

public class GameState {
    private static String[] playerHands = new String[10];
    private static String[] enemyHands = new String[10];


    /**
     * Create the initial combat state for the game.
     *
     * @return The state array that begins the game.
     */
    public static String[] generateInitialState(){
        return null;
    }

    /**
     * End a turn of player and switch to another player
     */
    public void endturn(){
    }

    /**
     * Check of the game has ended
     */
    public boolean hasGameEnd(){
        return false;
    }


}
