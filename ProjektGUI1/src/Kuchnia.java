import java.util.List;
import java.util.ArrayList;
import java.lang.Thread;
import java.util.Random;

public class Kuchnia {
    List<Pracownik> kucharze;
    List<Pracownik> dostawcy;
    List<Pracownik> kelnerzy;
    int czasNaWykonanieDania;
    
    public Kuchnia() {
        kucharze = new ArrayList<>();
        dostawcy = new ArrayList<>();
        kelnerzy = new ArrayList<>();
        czasNaWykonanieDania = 30;
    }

    public void dodajKucharza(Pracownik kucharz) {
        kucharze.add(kucharz);
        czasNaWykonanieDania = 30 / kucharze.size();
    }

    public void dodajDostawce(Pracownik dostawca) {
        dostawcy.add(dostawca);
    }

    public void dodajKelnera(Pracownik kelner) {
        kelnerzy.add(kelner);
    }

    public void usunKucharza(Pracownik kucharz) throws RuntimeException {
        if(dostawcy.size() == 1) {
            throw new RuntimeException("Ostatni kucharz!");
        } else {
            kucharze.remove(kucharz);
            czasNaWykonanieDania = 30 / kucharze.size();
        }
    }

    public void usunDostawce(Pracownik dostawca) throws RuntimeException {
        if(dostawcy.size() == 1) {
            throw new RuntimeException("Ostatni dostawca!");
        } else {
            dostawcy.remove(dostawca);
        }
    }

    public void usunKelnera(Pracownik kelner) throws RuntimeException {
        if(kelnerzy.size() == 1) {
            throw new RuntimeException("Ostatni kelner!");
        } else {
            kelnerzy.remove(kelner);
        }
    }
    
    public int wykonajZamowienie(int iloscPozycji, boolean czyZdalne, double cena) {
        int czas = 0;
        try {
            czas = iloscPozycji * czasNaWykonanieDania;
            Thread.sleep(czas);
            for(Pracownik kucharz : kucharze) {
                kucharz.zrealizowaneZamowienia += iloscPozycji / kucharze.size();
            }
        } catch (Exception expn) {}

        if(!czyZdalne) {
            if (czas/1000 < 15) {
                for(Pracownik dostawca : dostawcy)
                    dostawca.zarobek += ((15 - (czas/1000))/10)*cena;
            }
        }
        return czas + (120 / dostawcy.size()) * (czyZdalne ? 1 : 0);
    }
}