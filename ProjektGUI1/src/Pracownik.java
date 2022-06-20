import java.util.ArrayList;
import java.util.List;

public class Pracownik {
    public String imie;
    private String nazwisko;
    private String numerTelefonu;
    public double zarobek;
    public int zrealizowaneZamowienia;

    static List<Pracownik> ekstensja = new ArrayList<>();
    
    public Pracownik(String imie, String nazwisko, String numerTelefonu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerTelefonu = numerTelefonu;
        zarobek = 0;
        zrealizowaneZamowienia = 0;

        ekstensja.add(this);
    }

    public String toString() {
        return imie + " " + nazwisko + ", numer telefonu: " + numerTelefonu + ", zarobek " + zarobek + ", zrealizowane zamowienia: " + zrealizowaneZamowienia;
    }
}