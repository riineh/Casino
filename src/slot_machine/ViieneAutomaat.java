package slot_machine;

import slot_machine.Loos;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ViieneAutomaat implements Loos {

    @Override
    public ArrayList<String> suvalisedNumbrid() {
        ArrayList<String> lõppseis = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Random rand = new Random();
            int number = rand.nextInt(6);
            lõppseis.add(sümbolid.get(number));
        }
        return lõppseis;
    }


    public int võiduArvutaja(ArrayList<String> seis, int panus) {
        Set<String> set = new HashSet<>(seis);
        if (set.size() == 1) { //kõik pildid on samad
            return panus*5;
        } else if (set.size() == 2) { //kolmik ja paar või neli ühesugust pilti
            return panus*3;
        } else if (set.size() == 3) { //kolm pilti on samad või kaks paari
            return panus*2;
        } else { //ükski ei klapi
            return 0;
        }
    }

}
