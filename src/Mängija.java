public class Mängija {
    private String nimi;
    private int vanus;
    private int raha;

    public Mängija(String nimi, int vanus) {  //isendi loomiseks on vaja ainult nime ja vanust
        this.nimi = nimi;
        this.vanus = vanus;
    }

    public String getNimi() {
        return nimi;
    }

    public int getRaha() {
        return raha;
    }

    public void setRaha(int raha) {  //selleks, et määrata mängijale alguses kindel raha summa
        this.raha = raha;
    }
}
