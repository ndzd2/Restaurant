public class Pozycja {
    public int id;
    public String nazwa;
    public String opis;
    public double cena;
    
    public boolean dostepna;
    
    public Pozycja(int id, String nazwa, String opis, double cena) {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
        this.cena = cena;
        dostepna = true;
    }
    
    public String wyswietlInformacje() {
        return "Identyfikator: " + id + "\nNazwa: " + nazwa + "\nOpis: " + opis + "\nCena: " + cena + "\nDostepnosc: " + dostepna + "\n";
    }
    
    public String toString() {
        return id + "," + nazwa + "," + opis + "," + cena;
    }
}