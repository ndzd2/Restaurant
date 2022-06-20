import java.util.Map.Entry;

public class ZamowienieZdalne extends Zamowienie {
    String adresDostawy;
    
    public ZamowienieZdalne(String adresDostawy) {
        super();
        this.adresDostawy = adresDostawy;
    }
    
    @Override
    public String toString() {
        String wynik = "Zamowienie Zdalne\nCzas: " + czasZlozeniaZamowienia + "\nAdres dostawy: " + adresDostawy + "\nZamowione pozycje:\n";
        for(Entry<Integer, Integer> e : zamowionePozycje.entrySet()) {
            wynik += e.getKey() + " " + e.getValue() + "\n";
        }
        return wynik;
    }
}