package slot_machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class KolmeneAutomaat implements Loos {
    @Override
    public ArrayList<String> suvalisedNumbrid() {
        ArrayList<String> lõppseis = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Random rand = new Random();
            int number = rand.nextInt(6);
            lõppseis.add(Loos.sümbolid.get(number));
        }
        return lõppseis;
    }

    @Override
    public int võiduArvutaja(ArrayList<String> seis, int panus) {
        Set<String> set = new HashSet<>(seis);
        if (set.size() == 1) { //kõik pildid on samad
            return panus*10;
        } else if (set.size() == 2) { //kaks pilti on samad
            return panus*5;
        } else { //ükski ei klapi
            return 0;
        }
    }
}
