package blackjack;

import java.util.ArrayList;


public class Diiler extends Mängija {
    private Kaardipakk kaardipakk;
    private Laud laud;

    public Diiler(Kaardipakk kaardipakk, String nimi) {
        super(nimi);
        this.kaardipakk = kaardipakk;
    }

    public void setLaud(Laud laud) {
        this.laud = laud;
    }

    public ArrayList<Kaart> jagaEsimesedKaksKaarti() {
        ArrayList<Kaart> kaardid = new ArrayList<>();
        kaardipakk.sega();
        kaardid.add(kaardipakk.getJärgmineKaart());
        kaardid.add(kaardipakk.getJärgmineKaart());
        return kaardid;
    }

    public Kaart võtaJärgmineKaart() {
        return kaardipakk.getJärgmineKaart();
    }

    public void jagaJärgmineKaartMängijale(Mängija mängija) {
        mängija.getKäsi().add(võtaJärgmineKaart());
    }

    public String kysiMängijaltJärgmineTegevus(Mängija mängija) {
        System.out.println("Saate valida, kas küsida kaart juurde 1)Hit või passida 2)Stand");
        return mängija.vastaDiileriJärgmiseTegevuseKysimusele();
    }



}
