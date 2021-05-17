package AbnormalPoints;

import java.util.List;

public class DialogTree {
    String rootString;
    Dialog root;
    DialogType currentType;

    private enum DialogType{
        END_ATTACK,
        END_GIVE_GOLD,
        END_GIVE_ITEM,
        END_NONE,
        CONTINUE;
    }

    /**
     * Dialog class that help information of player's reply, npc's dialog and next node
     */
    private static class Dialog {
        private int    index;
        private String playerReply;
        private String npcDialog;
        private List<Dialog> nextDialogs;
        private DialogType type;
        
        public Dialog(){
        }

        /**
         * Constructor of dialog
         * @param index index of the player's reply (as dialog is control by 1,2,3,4 input)
         * @param playerReply player's reply from previous dialog
         * @param npcDialog npc new dialog as respond to playerReply in this dialog.
         * @param nextDialogs next conversation
         */
        public Dialog(int index, String playerReply, String npcDialog, List<Dialog> nextDialogs, DialogType type) {
            this.index = index;
            this.playerReply = playerReply;
            this.npcDialog = npcDialog;
            this.nextDialogs = nextDialogs;
            this.type = type;
        }
    }

    /**
     * initialise DialogTree
     */
    public DialogTree() {
        root = new Dialog();
    }

    /**
     * Trace down the dialog tree, given the selection of reply by the player
     * @param indexChoice the choice of the player (int)
     * @return a new DialogTree with new npc dialog and children dialogs
     * @author: Guanming Ou
     */
    public DialogTree applyReply(int indexChoice){
        if (this.root == null | this.root.nextDialogs == null) return null;

        Dialog currentNode = this.root;
        List<Dialog> children = currentNode.nextDialogs;
        //TODO: Make it so that it make to npc do such things following its type
        for (Dialog d : children){
            if (d.index == indexChoice){
                DialogTree t = new DialogTree();
                t.root = d;
                t.rootString = d.npcDialog;
                return t;
            }
        }
        return null;
    }

    /**
     * Print all available option for player to continue on the dialog
     * @return
     * @author Guanming Ou
     */
    public String printAvailableDialog(){
        if (this.root == null | this.root.nextDialogs == null) return "no option available";
        Dialog currentNode = this.root;
        List<Dialog> children = currentNode.nextDialogs;
        String result = "";

        for (Dialog d : children){
            result += "["+d.index+"]: " + d.playerReply +"\n";
        }
        return result;
    }
}
