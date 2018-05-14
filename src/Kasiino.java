import bingo.Bingo;
import blackjack.BlackjackLoogika;
import slot_machine.Mänguautomaat;

import java.util.Arrays;
import java.util.Scanner;

public class Kasiino {
    public static void main(String[] args) throws InterruptedException {
        boolean onPiisavVanus = true;

        Scanner sc = new Scanner(System.in); //loon mängude jooksul kasutatava skänneri
        System.out.println("Tere tulemas kasiinosse!");
        Thread.sleep(1000); //sekundiline paus, et kõik laused üksteise otsa ei tuleks

        System.out.println("Palun sisesta oma nimi:");
        String nimi = sc.nextLine();

        System.out.println("Palun sisesta oma vanus:");

        while (!sc.hasNextInt()) {
            System.out.println("Vanus peab olema täisarv. Palun sisesta oma vanus:");
            sc.next();
        }
        String sõnaVanus = sc.next();
        int vanus = Integer.parseInt(sõnaVanus);

        if (vanus < 21) {
            onPiisavVanus = false;
            System.out.println("Kahjuks oled kasiino külastamiseks veel liiga noor. Vanusepiirang on 21.");
            sc.close();

        }

        //Eelnevalt küsitud mängija andmete põhjal loob uue mängija
        Mängija mängija = new Mängija(nimi, vanus);
        mängija.setRaha(200); //mängija alustab kindla rahasummaga

        while (onPiisavVanus) { //kasiino tsükkel (siin saab mängija valida, mis mängu soovib)
            ValikVastusegaKüsimus küsimus = new ValikVastusegaKüsimus(mängija.getNimi()+", palun vali mäng:",
                    Arrays.asList("blackjack", "bingo", "slot machine"));    //loon valikvastusega küsimuse
            System.out.println("Kontoseis: " + mängija.getRaha());   //näitab mängija kontoseisu
            String valik = küsimus.küsiVastus(sc);    //mängijalt küsitakse eelnevalt loodud küsimus ning tagastatakse vastus

            //bingo mängu if haru
            /*
            if (valik.equals("bingo")) {
                while (true) {    //mäng on tsükklis selleks, et mängija ei peaks vahepeal uuesti kasiino menüüsse tagasi tulema
                    Bingo mäng = new Bingo();    //loob uue bingo mängu
                    mäng.mängi(sc);    //bingo mäng hakkab tööle
                    mängija.setRaha(mängija.getRaha() + mäng.getVõiduSumma());   //pärast mängu kantakse võidusumma mängija kontole
                    if (mäng.getLahku()){    //kui mängija valib enne bingo ostmist,
                        break;                       //et soovib lahkuda, siis süsteem ei küsi kas ta soovib uuesti mängida
                    }
                    ValikVastusegaKüsimus küsimus1 = new ValikVastusegaKüsimus("Kas soovid uuesti mängida?",
                            Arrays.asList("jah", "ei"));    //loon uue küsimuse, et kas mängija tahab veel mängida bingot
                    String vastus1 = küsimus1.küsiVastus(sc);
                    if (vastus1.equals("ei")) {    //kui pärast eelmise bingo lõppu ei soovi enam mängida, läheb tagasi kasiino menüüsse
                        break;
                    } else if (vastus1.equals("jah")) {    //jah korral hakkab mäng uuesti tööle ehk läheb tsükli algusesse
                        continue;
                    }
                }
                //mänguautomaadi if haru
            } else if (valik.equals("slot machine")) {
                while (true) {
                    Mänguautomaat mäng = new Mänguautomaat();
                    mäng.mäng();
                    mängija.setRaha(mängija.getRaha() + mäng.getLõppSkoor());
                    break;
                }
            } else if (valik.equals("blackjack")) {
                BlackjackLoogika blackjack = new BlackjackLoogika(mängija.getNimi(), mängija.getRaha());
                blackjack.alustaMängu();
            }*/

            //kasiinost lahkumise osa
            ValikVastusegaKüsimus küsimus2 = new ValikVastusegaKüsimus("Kas soovid ka kasiinost lahkuda?",
                    Arrays.asList("jah", "ei"));  //seda küsitakse siis, kui mängija on lahkunud Bingost
            String vastus2 = küsimus2.küsiVastus(sc);
            if (vastus2.equals("jah")){
                System.out.println("Sinu lõppskooriks jäi kasiinost lahkudes " + mängija.getRaha() + " eurot!");
                break; //kui mängija vastab jah, siis programmi töö lõpeb
            }
            else if (vastus2.equals("ei")){ //ei korral algab kasiino tsükkel uuesti ning mängija saab valida mängu
                continue;
            }
        }
        sc.close(); //sulgeb skänneri
    }
}