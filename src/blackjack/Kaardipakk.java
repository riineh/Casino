package blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Kaardipakk {
    private ArrayList<Kaart> kaardid;

    public Kaardipakk() {
        looKaardipakk();
    }

    public void sega() {
        Collections.shuffle(kaardid);
    }

    public ArrayList<Kaart> getKaardid() {
        return kaardid;
    }

    public Kaart getJärgmineKaart() {
        Kaart kaart = kaardid.get(0);
        kaardid.remove(kaart);
        return kaart;
    }

    private void looKaardipakk(){
        kaardid = new ArrayList<>();
        for (String mast : getMastid()) {
            //Genereerib numbrikaardid
            for (int i = 2; i <= 10 ; i++) {
                kaardid.add(new NumbriKaart(mast, i));
            }

            //Genereerib pildikaardid
            for (String pilt : getPildid()) {
                kaardid.add(new PildiKaart(mast, pilt));
            }
        }
    }

    private ArrayList<String> getMastid() {
        ArrayList<String> mastid = new ArrayList<>();
        mastid.add("♠");
        mastid.add("♣");
        mastid.add("♥");
        mastid.add("♦");
        return mastid;
    }

    private ArrayList<String> getPildid() {
        ArrayList<String> pildid = new ArrayList<>();
        pildid.add("J");
        pildid.add("Q");
        pildid.add("K");
        pildid.add("A");
        return pildid;
    }


}
