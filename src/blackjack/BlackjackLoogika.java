package blackjack;

import java.util.Scanner;

public class BlackjackLoogika {
    private Scanner sc;
    private String nimi;
    private int raha;
    private Laud laud;
    private int roundNr = 1;

    public BlackjackLoogika(String nimi, int raha) {
        this.sc = new Scanner(System.in);
        this.nimi = nimi;
        this.raha = raha;
    }

    public void alustaMängu() {
        System.out.println("Alustasid mängu Blackjack");

        System.out.println("Mängid diileri vastu");
        System.out.println("Mängu reeglid:");
        System.out.println("1. Diiler jagab nii endale kui ka sinule (mängijale) kaks kaarti");
        System.out.println("2. Diileri kaartidest on näha alguses vaid ühte");
        System.out.println("3. Sina (Mängija) saad otsustada, kas võtta kaarte juurde (Hit) või olla rahul (Stand)");
        System.out.println("4. Kui läksid juurde võtmisega lõhki (>21), on diiler võitud. Kui said 21, oled sina võitnud. Kui nii sina kui diiler saite 21, on viik.");
        System.out.println("5. Vastasel juhul vaadatakse peale stand'imist diileri teist kaarti");
        System.out.println("6. Kui diileri kaartide summa on suurem või võrdne 17ga, siis diiler kaarte juurde ei tohi võtta");
        System.out.println("7. Vastasel juhul peab diiler juurde võtma");
        System.out.println("8. Kui diiler läks lõhki, siis oled sina võitnud, vastasel juhul vaadatakse, kellel on punkte lähemal 21-le");
        System.out.println();
        Mängija mängija = new Mängija(nimi);
        Kaardipakk kaardipakk = new Kaardipakk();
        Diiler diiler = new Diiler(kaardipakk, "Diiler");

        laud = new Laud(mängija, diiler);
        diiler.setLaud(laud);

        alustaRoundi();
    }


    private void alustaRoundi() {
        Mängija mängija = laud.getMängija();
        Diiler diiler = laud.getDiiler();
        System.out.println("Algas round " + roundNr + "!");
        System.out.println("Sinu hetkesaldo: " + raha);
        System.out.println("Sisesta panus, millega mängida: ");
        int panus = sc.nextInt();

        laud.getMängija().setPanus(panus);

        //Jagan mängijale ja diilerile kaks kaarti
        laud.getDiiler().setKäsi(diiler.jagaEsimesedKaksKaarti());
        laud.getMängija().setKäsi(diiler.jagaEsimesedKaksKaarti());

        System.out.println(laud.getDiiler().getKäsiTekstina(true));
        System.out.println(laud.getMängija().getKäsiTekstina(false));

        while (!mängija.onLõhki()) {
            String tegevus = diiler.kysiMängijaltJärgmineTegevus(mängija);

            if (tegevus.toLowerCase().equals("hit") || tegevus.toLowerCase().equals("1")) {
                diiler.jagaJärgmineKaartMängijale(mängija);
                System.out.println(laud.getMängija().getKäsiTekstina(false));
            } else if (tegevus.toLowerCase().equals("stand")){
                mängija.stand();
                break;
            }
        }

        if (mängija.onLõhki()) {
            System.out.println("Läksid lõhki. Diiler võitis.");
        } else {
            System.out.println("Nüüd avalikustab diiler oma teise kaardi...");
            System.out.println(diiler.getKäsiTekstina(false));

            if (diiler.getMaxLegaalneKaartidePunktid() < 17) {
                System.out.println("Kuna diileri kaardid on väärt alla 17 punkti, siis peab ta juurde võtma");
                diiler.jagaJärgmineKaartMängijale(diiler);
                System.out.println("Diiler võttis kaardi juurde");
                System.out.println(diiler.getKäsiTekstina(false));
                if (diiler.getMaxLegaalneKaartidePunktid() > 21) {
                    System.out.println("Diiler läks lõhki. Võitsid hoopis sina");
                }
            }
        }



        if (mängija.getMaxLegaalneKaartidePunktid() == 21 && diiler.getMaxLegaalneKaartidePunktid() != 21 ) {
            System.out.println("Mängija sai blackjacki ja võitis!!!");
        } else if (mängija.getMaxLegaalneKaartidePunktid() == 21 && diiler.getMaxLegaalneKaartidePunktid() == 21) {
            System.out.println("Nii diileril kui ka mängijal on blackjack - seis jäi viiki!");
        } else if (diiler.getMaxLegaalneKaartidePunktid() == 21) {
            System.out.println("Diiler võitis");
        } else {
            boolean mängijaVõitis = mängija.getMaxLegaalneKaartidePunktid() > diiler.getMaxLegaalneKaartidePunktid();
            if (mängijaVõitis && !mängija.onLõhki() || diiler.onLõhki()) {
                System.out.println("Võitsid!!!");
            } else {
                System.out.println("Kaotasid");
            }
        }


    }
    
    private boolean onKumbkiMängijaLõhki() {
        return laud.getMängija().onLõhki() || laud.getDiiler().onLõhki();
    }




}
