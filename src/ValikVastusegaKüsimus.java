import java.util.List;
import java.util.Scanner;

public class ValikVastusegaKüsimus {
    String küsimus;
    List<String> variandid;  //vastuse variandid on listis, et saaks erineva arvuliste vastustega küsimusi esitada
    String sisend;

    public ValikVastusegaKüsimus(String küsimus, List<String> variandid) {
        this.küsimus = küsimus;
        this.variandid = variandid;
    }

    public String tekst() {   //tagastab küsimuse teksti
        return this.küsimus;
    }

    public String küsiVastus(Scanner sc) {   //see esitab kasutajale küsimuse ning tagastab vastuse Stringina
        System.out.println(tekst());
        for (int i = 0; i < this.variandid.size(); i++) {   //prindib välja kõik valikuvariandid
            System.out.println("- " + this.variandid.get(i));
        }
        while (true) {
            sisend = sc.nextLine();
            if (!this.variandid.contains(sisend)){   //kui pole sobiv sisend siis reageerib vastavalt
                System.out.println("Palun tee õige valik!");
            }
            else {   //kui sisend sobib, siis tsükkel lõpetab töö
                break;
            }
        }
        return sisend;   //tagastab viimase ehk sobiva sisendi
    }
}
