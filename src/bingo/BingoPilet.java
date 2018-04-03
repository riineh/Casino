package bingo;

import java.util.ArrayList;
import java.util.List;

public class BingoPilet {
    private char[] tähed = {'B','I','N','G','O'};
    private List<List<Integer>> bingoTabel = new ArrayList<>();
    private boolean nurkVõit;
    private boolean diagonaalVõit;
    private boolean täismängVõit;

    public BingoPilet() {
        loosiPilet();
    }

    public List<List<Integer>> loosiPilet() {
        for (char elem: tähed){
            PiletiNumbrid a = new PiletiNumbrid(elem);
            List<Integer> aNumbrid = a.loosiPiletiNumbrid();
            bingoTabel.add(aNumbrid);
        }
        return bingoTabel;
    }
    public void prindiPilet() {
        System.out.print("  ");
        for (char elem: tähed){
            System.out.print(elem + "  ");
        }
        System.out.println();
        for (int i=0; i<bingoTabel.size(); i++){
            for (int j=0; j<bingoTabel.get(i).size(); j++){
                System.out.print(String.format("% 3d", bingoTabel.get(j).get(i)));
            }
            System.out.println();
        }
    }
    public void kontrolli(int loositudNumber){
        for (int i=0; i<bingoTabel.size(); i++){
            for (int j=0; j<bingoTabel.get(i).size(); j++){
                if (bingoTabel.get(i).get(j) == loositudNumber) {
                    bingoTabel.get(i).set(j, 0);
                }
            }
        }
    }
    public boolean nurkademäng() {
        nurkVõit = false;
        int nurk1 = bingoTabel.get(0).get(0);
        int nurk2 = bingoTabel.get(0).get(4);
        int nurk3 = bingoTabel.get(4).get(0);
        int nurk4 = bingoTabel.get(4).get(4);

        if (nurk1==0 && nurk2==0 && nurk3==0 && nurk4==0){
            nurkVõit = true;
        }
        return nurkVõit;
    }
    public boolean diagonaalidemäng() {
        diagonaalVõit = true;
        for (int i=0; i<bingoTabel.size(); i++){
            for (int j=0; j<bingoTabel.get(i).size(); j++){
                if (i==j && bingoTabel.get(i).get(j) != 0){
                    diagonaalVõit = false;
                }
                else if (((i==0&&j==4)||(i==1&&j==3)||(i==2&&j==2)||(i==3&&j==1)||(i==4&&j==0))&&bingoTabel.get(i).get(j)!=0){
                    diagonaalVõit = false;
                }
            }
        }
        return diagonaalVõit;
    }
    public boolean täismäng() {
        täismängVõit = true;
        for (int i=0; i<bingoTabel.size(); i++){
            for (int j=0; j<bingoTabel.get(i).size(); j++){
                if (bingoTabel.get(i).get(j) != 0){
                    täismängVõit = false;
                }
            }
        }
        return täismängVõit;
    }
}