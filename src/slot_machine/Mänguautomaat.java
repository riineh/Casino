package slot_machine;

import slot_machine.KolmeneAutomaat;
import slot_machine.ViieneAutomaat;

import java.util.ArrayList;
import java.util.Scanner;


public class Mänguautomaat {
    int võidusumma = 0;
    int võit;
    int lõppSkoor = 0;

    public int getLõppSkoor() {
        return lõppSkoor;
    }

    public void print(ArrayList<String> seis, int mitmeneAutomaat) { //meetod
        try {
            int i = 0;
            for (String seis2 : seis) {
                System.out.print("<" + seis2 + "> ");
                if (i < (mitmeneAutomaat-1)) {
                    Thread.sleep(1000);
                    System.out.print(". ");
                    Thread.sleep(1000);
                    System.out.print(". ");
                    Thread.sleep(1000);
                    System.out.print(". ");
                    Thread.sleep(1000);
                    i++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   public void mäng() {
       int panus;
       int panusedKokku = 0;
       String jätkata = "jah";




       Scanner scanner = new Scanner(System.in);
       System.out.println("Tere tulemast!");
       System.out.println("\n" + "Kas soovid mängida kolmese mänguautomaadiga või viiese mänguautomaadiga?" + "\n" +
               "Kolmesega mängides on võiduvõimalused väiksemad, aga võidusummad suuremad, kui viiese mänguautomaadiga mängides." + "\n");

       while (jätkata.equals("jah")){
           System.out.println("Sisesta [3] või [5] vastava automaadiga mängimiseks." + "\n" + "Tee oma valik: ");
           int vastus = scanner.nextInt();
           scanner.nextLine();
           if (vastus == 5 || vastus == 3) {
               System.out.println("\n" +  "Sisesta täisarvuline panus:");
               while(true) {
                   String sisend = scanner.nextLine();
                   try {
                       panus = Integer.parseInt(sisend);
                       break;
                   } catch (Exception e){
                       System.out.println("Vale sisend");
                   }
               }
               if (panus <= 0) {
                   System.out.println("Panus ei saa olla 0 või negatiivne ning peab olema täisarvuline!");
                   scanner.nextLine();
               } else {
                   panusedKokku = panusedKokku + panus;
                   System.out.println("\n ");
                   if (vastus == 5) {
                       ViieneAutomaat viiene = new ViieneAutomaat();
                       ArrayList<String> seis = viiene.suvalisedNumbrid();
                       print(seis, 5);
                       võit = viiene.võiduArvutaja(seis, panus);
                       võidusumma = võidusumma + võit;
                       lõppSkoor = võidusumma - panusedKokku;
                       System.out.println("\n" + "Oled võitnud " + võit + " eurot!" +
                               "\n" + "Sinu üldseis on " + lõppSkoor + " eurot!"  );
                       System.out.println("Kas soovid jätkata?");
                       jätkata = scanner.nextLine();
                   } else {
                       KolmeneAutomaat kolmene = new KolmeneAutomaat();
                       ArrayList<String> seis = kolmene.suvalisedNumbrid();
                       print(seis, 3);
                       võit = kolmene.võiduArvutaja(seis, panus);
                       võidusumma = võidusumma + võit;
                       lõppSkoor = võidusumma - panusedKokku;
                       System.out.println("\n" + "Oled võitnud " + võit + " eurot!" +
                               "\n" + "Sinu üldseis on " + lõppSkoor + " eurot!"  );
                       System.out.println("Kas soovid jätkata?");
                       jätkata = scanner.nextLine().toLowerCase();
                   }
               }
           } else {
               System.out.println("Sisesta korrektne valik!");
           }
       }
       System.out.println("Sinu lõppskooriks mänguautomaatidest jäi " + lõppSkoor + " eurot!");
   }
}
