import java.util.Map.Entry;

public class ZamowienieStacjonarne extends Zamowienie {
    int numerStolika;
    
    public ZamowienieStacjonarne(int numerStolika) {
        super();
        this.numerStolika = numerStolika;
    }
    
    @Override
    public String toString() {
        String wynik = "Zamowienie Stacjonarne\nCzas: " + czasZlozeniaZamowienia + "\nNumer stolika: " + numerStolika + "\nZamowione pozycje:\n";
        for(Entry<Integer, Integer> e : zamowionePozycje.entrySet()) {
            wynik += e.getKey() + " " + e.getValue() + "\n";
        }
        return wynik;
    }
}