package slot_machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//test

public interface Loos {
    List<String> sümbolid = Arrays.asList("KIRSS", "PLOOM", "SIDRUN", "APELSIN", "BANAAN", "MAASIKAS");

    ArrayList<String> suvalisedNumbrid(); //genereerib suvalised numbrid ja nende kaudu automaadist väljastatava seisu

    int võiduArvutaja(ArrayList<String> seis, int panus); //arvutab vastavalt seisule ja panusele võidusumma
}
