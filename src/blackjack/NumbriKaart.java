package blackjack;

public class NumbriKaart extends Kaart{
    private int number;

    public NumbriKaart(String mast, int number) {
        super(mast);
        this.number = number;
    }

    @Override
    public int getPunktid() {
        return number;
    }

    @Override
    public String toString() {
        return number + mast;
    }
}
