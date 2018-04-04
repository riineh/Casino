package bingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bingo {
    private int võit;
    private int võiduSumma;
    private boolean lahku = false;

    public boolean getLahku() {
        return lahku;
    }

    public int getVõiduSumma() {
        return võiduSumma;
    }

    public void mängi(Scanner sc) throws InterruptedException {
        String sisend;
        System.out.println("Tere tulemast!");
        System.out.println("bingo.Bingo koosneb kolmest osast: nurkademängust (võidusumaa: 20€), " +
                "diagonaalidemängust (võidusumma: 50€) ja täismängust (võidusumma (150€).\n" +
                "Nurkademängus loositakse 33 numbrit. Võitmiseks, tuleb saada täis kõik 4 nurka.\n" +
                "Diagonaalidemängus loositakse veel 5 numbrit. Selle vooru võitmiseks tuleb saada täis mõlemad diagonaalid.\n" +
                "Täismängus loositkse viimased 10 numbrit. Võitmiseks tuleb saada täis kõik mänguvälja ruudud.\n" +
                "P.S Täidetud ruutu tähistab sümbol \"0\"");
        System.out.println("---------------------");
        System.out.println("bingo.Bingo pileti hind 10€");
        System.out.println();
        System.out.println("- Pileti ostmiseks sisesta: \"osta\"");
        System.out.println("- Mängust lahkumiseks sisesta: \"lahku\"");
        while (true) {
            sisend = sc.nextLine();
            if (sisend.equals("osta")){
                mäng();
                võiduSumma -= 10;
                break;
            }
            else if (sisend.equals("lahku")){
                lahku = true;
                break;
            }
            else {
                System.out.println("Palun tee õige valik!");
            }
        }
    }
    public void mäng() throws InterruptedException {
        BingoPilet pilet = new BingoPilet();
        võiduSumma = 0;

        System.out.println("Sinu bingo.Bingo pilet:");
        Thread.sleep(100);
        pilet.prindiPilet();

        List<Integer> loositudArvud = new ArrayList<>();
        LoosiNumber loos = new LoosiNumber(1, 75);

        Thread.sleep(100);
        System.out.print("Nurkademängu numbrid: ");
        numbriLoos(loositudArvud, loos, pilet, 40);
        Thread.sleep(100);
        System.out.println("\nHetkeseis:");
        Thread.sleep(100);
        pilet.prindiPilet();

        võit = 0;
        if (pilet.nurkademäng()) {
            võit = 20;
            võiduSumma += võit;
        }
        Thread.sleep(100);
        System.out.println("Nurkademängus võidetud summa: " + võit + "€");
        Thread.sleep(100);
        System.out.println("--------------------------------");
        Thread.sleep(100);
        System.out.print("Diagonaalidemängu numbrid: ");
        numbriLoos(loositudArvud, loos, pilet, 17);
        Thread.sleep(100);
        System.out.println("\nHetkeseis:");
        Thread.sleep(100);
        pilet.prindiPilet();

        võit = 0;
        if (pilet.diagonaalidemäng()) {
            võit = 50;
            võiduSumma += võit;
        }
        Thread.sleep(100);
        System.out.println("Diagonaalidemängus võidetud summa: " + võit + "€");
        Thread.sleep(100);
        System.out.println("--------------------------------");
        Thread.sleep(100);
        System.out.print("Täismängu numbrid: ");
        numbriLoos(loositudArvud, loos, pilet, 15);
        Thread.sleep(100);
        System.out.println("\nLõppseis:");
        Thread.sleep(100);
        pilet.prindiPilet();

        võit = 0;
        if (pilet.täismäng()) {
            võit = 150;
            võiduSumma += võit;
        }
        Thread.sleep(100);
        System.out.println("Täismängus võidetud summa: " + võit + "€");
        Thread.sleep(100);
        System.out.println("bingo.Bingo võidusumma: " + võiduSumma + "€");
        Thread.sleep(100);
        System.out.println("--------------------------------");
        Thread.sleep(100);

    }

    public void numbriLoos(List<Integer> list, LoosiNumber loos, BingoPilet pilet, int kogus) throws InterruptedException {
        int loositud;
        for (int i=0; i<kogus; i++) {
            while (true) {
                int loositudArv = loos.suvalineNumber();
                if (!list.contains(loositudArv)) {
                    list.add(loositudArv);
                    loositud = loositudArv;
                    break;
                }
            }
            Thread.sleep(100);
            System.out.print(loositud + " ");
            pilet.kontrolli(loositud);
        }
    }
}
