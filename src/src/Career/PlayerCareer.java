package Career;

public enum PlayerCareer {
    Knight("0"),
    Ranger("1"),
    Wizard("2"),
    Paladin("3");

    private final String index;

    PlayerCareer(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }
}
