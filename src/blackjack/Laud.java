package blackjack;

import java.util.ArrayList;

public class Laud {
    private Mängija mängija;
    private Diiler diiler;

    public Laud(Mängija mängija, Diiler diiler) {
        this.mängija = mängija;
        this.diiler = diiler;
    }

    public Mängija getMängija() {
        return mängija;
    }

    public Diiler getDiiler() {
        return diiler;
    }
}
