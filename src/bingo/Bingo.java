package bingo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bingo {
    private BingoPilet pilet1;
    private BingoPilet pilet2;

    public Bingo(BingoPilet pilet1) throws FileNotFoundException {
        this.pilet1 = pilet1;
    }

    public Bingo(BingoPilet pilet1, BingoPilet pilet2) throws FileNotFoundException {
        this.pilet1 = pilet1;
        this.pilet2 = pilet2;
    }



    /*
    private int võit;
    private int võiduSumma;

    public int getVõiduSumma() {
        return võiduSumma;
    }

    public void mäng() throws InterruptedException {

        BingoPilet pilet = new BingoPilet();
        võiduSumma = 0;
        int aeg = 1000;

        System.out.println("Sinu Bingo pilet:");

        Thread.sleep(aeg);

        pilet.prindiPilet();

        List<Integer> loositudArvud = new ArrayList<>();
        LoosiNumber loos = new LoosiNumber(1, 75);

        Thread.sleep(aeg);
        System.out.print("Nurkademängu numbrid: ");
        numbriLoos(loositudArvud, loos, pilet, 40);
        Thread.sleep(aeg);
        System.out.println("\nHetkeseis:");
        Thread.sleep(aeg);
        pilet.prindiPilet();

        võit = 0;
        if (pilet.nurkademäng()) {
            võit = 20;
            võiduSumma += võit;
        }
        Thread.sleep(aeg);
        System.out.println("Nurkademängus võidetud summa: " + võit + "€");
        Thread.sleep(aeg);
        System.out.println("--------------------------------");
        Thread.sleep(aeg);
        System.out.print("Diagonaalidemängu numbrid: ");
        numbriLoos(loositudArvud, loos, pilet, 17);
        Thread.sleep(aeg);
        System.out.println("\nHetkeseis:");
        Thread.sleep(aeg);
        pilet.prindiPilet();

        võit = 0;
        if (pilet.diagonaalidemäng()) {
            võit = 50;
            võiduSumma += võit;
        }
        Thread.sleep(aeg);
        System.out.println("Diagonaalidemängus võidetud summa: " + võit + "€");
        Thread.sleep(aeg);
        System.out.println("--------------------------------");
        Thread.sleep(aeg);
        System.out.print("Täismängu numbrid: ");
        numbriLoos(loositudArvud, loos, pilet, 15);
        Thread.sleep(aeg);
        System.out.println("\nLõppseis:");
        Thread.sleep(aeg);
        pilet.prindiPilet();

        võit = 0;
        if (pilet.täismäng()) {
            võit = 150;
            võiduSumma += võit;
        }
        Thread.sleep(aeg);
        System.out.println("Täismängus võidetud summa: " + võit + "€");
        Thread.sleep(aeg);
        System.out.println("Bingo võidusumma: " + võiduSumma + "€");
        Thread.sleep(aeg);
        System.out.println("--------------------------------");
        Thread.sleep(aeg);

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
    */
}
