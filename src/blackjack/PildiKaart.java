package blackjack;

public class PildiKaart extends Kaart {
    private String pilt;

    @Override
    public int getPunktid() {
        if (pilt.equals("Q")|| pilt.equals("K") || pilt.equals("J")) {
            return 10;
        } else {
            return 1; //Seda, kas 1 teisendada 11-ks kontrollin mänguloogikaga.
        }
    }

    public PildiKaart(String mast, String pilt) {
        super(mast);
        this.pilt = pilt;
    }

    @Override
    public String toString() {
        return pilt + mast;
    }
}
