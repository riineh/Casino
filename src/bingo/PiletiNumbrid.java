package bingo;

import bingo.LoosiNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PiletiNumbrid {
    private char täht;
    private List<Integer> veerg;
    private List<Integer> numbrid;

    public PiletiNumbrid(char täht) {
        this.täht = täht;
    }
    public List<Integer> numbriteList(int kogus, LoosiNumber loositav) {
        numbrid = new ArrayList<>();
        while (true) {
            if (numbrid.size() == kogus){
                break;
            }
            int number = loositav.suvalineNumber();
            if (!numbrid.contains(number)) {
                numbrid.add(number);
            }
        }
        Collections.sort(numbrid);
        return numbrid;
    }
    public List<Integer> loosiPiletiNumbrid() {
        if (this.täht == 'B'){
            LoosiNumber loos = new LoosiNumber(1, 15);
            veerg = numbriteList(5, loos);
        }
        else if (this.täht == 'I'){
            LoosiNumber loos = new LoosiNumber(16, 30);
            veerg = numbriteList(5, loos);
        }
        else if (this.täht == 'N'){
            LoosiNumber loos = new LoosiNumber(31, 45);
            veerg = numbriteList(5, loos);
        }
        else if (this.täht == 'G'){
            LoosiNumber loos = new LoosiNumber(46, 60);
            veerg = numbriteList(5, loos);
        }
        else if (this.täht == 'O'){
            LoosiNumber loos = new LoosiNumber(61, 75);
            veerg = numbriteList(5, loos);
        }
        return veerg;
    }
}
