package AbnormalPoints;

import java.util.List;

public class DialogTree {
    Dialog root;

    public enum DialogType{
        END_ATTACK,
        END_GIVE_GOLD,
        END_GIVE_ITEM,
        END_NONE,
        CONTINUE,
        END_BLESS_HP,
        END_BLESS_ARMOR,
        END_BLESS_DAMAGE;
    }

    /**
     * initialise DialogTree
     */
    public DialogTree() {
        root = new Dialog();
    }

    /**
     * Print all available option for player to continue on the dialog
     * @return all options
     * @author Guanming Ou
     */
    public String printAvailableDialog(){
        if (this.root == null | this.root.nextDialogs == null) return "no option available";
        List<Dialog> children = this.root.nextDialogs;
        String result = "";

        for (Dialog d : children)
            result += "["+d.index+"]: " + d.playerReply +"\n";

        return result;
    }

    /**
     * Input a player's respond, and match the corresponding children dialog
     * @return return the matched dialog, else null
     * @author Guanming Ou
     */
    public Dialog matchDialog(int input){
        if (this.root == null | this.root.nextDialogs == null) return null;
        List<Dialog> children = this.root.nextDialogs;

        // find the dialog with that index
        for (Dialog d : children){
            if (d.index == input)
                return d;
        }
        return null;
    }

    public Dialog getRoot() {
        return root;
    }

    public void setRoot(Dialog root) {
        this.root = root;
    }

    /**
     * Dialog class that help information of player's reply, npc's dialog and next node
     */
    public static class Dialog {
        int    index;
        String playerReply;
        String npcDialog;
        List<Dialog> nextDialogs;
        DialogType dtype;
        
        public Dialog(){
        }

        /**
         * Constructor of dialog
         * @param index index of the player's reply (as dialog is control by 1,2,3,4 input)
         * @param playerReply player's reply from previous dialog
         * @param npcDialog npc new dialog as respond to playerReply in this dialog.
         * @param nextDialogs next conversation
         */
        public Dialog(int index, String playerReply, String npcDialog, List<Dialog> nextDialogs, DialogType dtype) {
            this.index = index;
            this.playerReply = playerReply;
            this.npcDialog = npcDialog;
            this.nextDialogs = nextDialogs;
            this.dtype = dtype;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getPlayerReply() {
            return playerReply;
        }

        public void setPlayerReply(String playerReply) {
            this.playerReply = playerReply;
        }

        public String getNpcDialog() {
            return npcDialog;
        }

        public void setNpcDialog(String npcDialog) {
            this.npcDialog = npcDialog;
        }

        public List<Dialog> getNextDialogs() {
            return nextDialogs;
        }

        public void setNextDialogs(List<Dialog> nextDialogs) {
            this.nextDialogs = nextDialogs;
        }

        public DialogType getDtype() {
            return dtype;
        }

        public void setDtype(DialogType dtype) {
            this.dtype = dtype;
        }
    }
}
