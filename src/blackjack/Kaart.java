package blackjack;

public abstract class Kaart {
    protected String mast;

    public Kaart(String mast) {
        this.mast = mast;
    }

    public abstract int getPunktid();
}
