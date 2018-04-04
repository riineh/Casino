package blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Mängija {
    private String nimi;
    private int panus;
    private ArrayList<Kaart> käsi;

    public Mängija(String nimi) {
        this.nimi = nimi;
    }

    public void setPanus(int panus) {
        this.panus = panus;
    }

    public void setKäsi(ArrayList<Kaart> käsi) {
        this.käsi = käsi;
    }

    public ArrayList<Kaart> getKäsi() {
        return käsi;
    }

    public boolean onLõhki() {
        return getMaxLegaalneKaartidePunktid() > 21;
    }

    // Kui ässa arvestatakse 11na ja sel juhul mindakse lõhki, siis arvestatakse ässa 1na
    public int getMaxLegaalneKaartidePunktid() {
        return getPunktid(true) > 21 ? getPunktid(false) : getPunktid(true);
    }

    private int getPunktid(boolean max) {
        int summa = 0;
        for (Kaart kaart : käsi) {
            summa += kaart.getPunktid() == 1 ? (max ? 11 : 1) : kaart.getPunktid();
        }
        return summa;
    }

    public void stand() {
        System.out.println("Mängija ei küsi uut kaarti.");
    }


    public String vastaDiileriJärgmiseTegevuseKysimusele() {
        return (new Scanner(System.in)).nextLine();
    }

    public String getKäsiTekstina(boolean näitaAinultÜhteKaarti) {
        String tagastus = "";

        tagastus += (this.nimi != "Diiler" ? "Sinul" : "Diileril") + " on käes kaardid: ";

        int kaartNr = 0;

        for (Kaart kaart : käsi) {
            kaartNr += 1;
            if (näitaAinultÜhteKaarti && kaartNr > 1) {
                tagastus += "X ";
            } else {
                tagastus += kaart + " ";
            }
        }

        if (näitaAinultÜhteKaarti) {
            tagastus += "  (X-dega kaardid on peidetud)";
        }

        return tagastus;
    }
}
