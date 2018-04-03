package bingo;

public class LoosiNumber {
    private int alumine;
    private int ülemine;
    private int suvalineArv;

    public LoosiNumber(int alumine, int ülemine) {
        this.alumine = alumine;
        this.ülemine = ülemine;
    }

    public int suvalineNumber() {
        suvalineArv = (int)Math.round(Math.random()*(this.ülemine-this.alumine)+this.alumine);
        return suvalineArv;
    }
}
