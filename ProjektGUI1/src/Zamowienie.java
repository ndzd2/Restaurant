import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map.Entry;

public class Zamowienie implements Scan {
    public String czasZlozeniaZamowienia;
    public Menu menu;
    public HashMap<Integer, Integer> zamowionePozycje;
    
    public Zamowienie() {
        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        czasZlozeniaZamowienia = data.format(format);
        zamowionePozycje = new HashMap<>();
        menu = Menu.utworzMenu();
    }
    
    public void wybierzPozycje() {
        int wybor, ilosc;
        menu.wypiszPozycje();
        System.out.print("\nWybierz pozycje (0 konczy zamowienie): ");
        wybor = scaner().nextInt();
        while(wybor != 0) {
            System.out.print("\nWybierz ilosc: ");
            ilosc = scaner().nextInt();
            zamowionePozycje.put(wybor, ilosc);
            System.out.print("\nWybierz pozycje (0 konczy zamowienie): ");
            wybor = scaner().nextInt();
        }
    }
    
    public void losowePozycje() {
        Random rd = new Random();
        for(int i = 0; i < rd.nextInt(menu.getPozycje().size()) + 1; i++) {
            zamowionePozycje.put(rd.nextInt(menu.getPozycje().size()) + 1, rd.nextInt(3) + 1);
        }
    }
    
    public String toString() {
        String wynik = "Zamowienie\nCzas: " + czasZlozeniaZamowienia + "\nZamowione pozycje:\n";
        for(Entry<Integer, Integer> e : zamowionePozycje.entrySet()) {
            wynik += e.getKey() + " " + e.getValue() + "\n";
        }
        return wynik;
    }
    
    public int ilePozycji() {
        int ilosc = 0;
        for(Entry<Integer, Integer> e : zamowionePozycje.entrySet()) {
            ilosc += e.getValue();
        }
        return ilosc;
    }
}