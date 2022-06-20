import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.IOException;
import java.nio.file.Paths;

public class Menu {
    private static Menu single_instance = null;
    private List<Pozycja> pozycje;
    
    private Menu() {
        pozycje = new ArrayList<Pozycja>();
    }
  
    public static Menu utworzMenu() {
        if (single_instance == null)
            single_instance = new Menu();
        return single_instance;
    }
    
    // Funkcjonalnosc 1
    public void wypiszPozycje() {
        for(Pozycja pozycja : pozycje) {
            System.out.println(pozycja.wyswietlInformacje());
        }
    }

    public void dodajPozycje() {
        Scanner scan = new Scanner(System.in);
        
        int nowyId = pozycje.size() + 1;
        System.out.print("Wprowadz nazwe dania: ");
        String nowaNazwa = scan.nextLine();
        System.out.print("Wprowadz opis dania: ");
        String nowyOpis = scan.nextLine();
        System.out.print("Wprowadz cene dania: ");
        double nowaCena = scan.nextDouble();
        
        pozycje.add(new Pozycja(nowyId, nowaNazwa, nowyOpis, nowaCena));
    }
    
    public void dodajPozycje(Pozycja pozycja) {
        pozycje.add(pozycja);
    }
    
    public void usunPozycje(Pozycja pozycja) {
        pozycje.remove(pozycja);
    }
    
    public void usunPozycje(int id) {
        for(Pozycja pozycja : pozycje) {
            if(pozycja.id == id) {
                pozycje.remove(pozycja);
                return;
            }
        }
    }

    public void zmienDostepnosc(int id) {
        for(Pozycja pozycja : pozycje) {
            if(pozycja.id == id) {
                pozycja.dostepna ^= true;
                return;
            }
        }
    }

    public void zapiszMenu(String nazwaPliku) {
        try {
            FileWriter plik = new FileWriter(nazwaPliku);
            for (Pozycja pozycja : pozycje) {
                plik.write(pozycja.toString() + "\n");
            }
            plik.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void wczytajMenu(String nazwaPliku) { 
        try (Scanner scan = new Scanner(Paths.get(nazwaPliku).toFile())) {
            String delimiter = ",";
            scan.useDelimiter(delimiter);
            int licznik = 0;
            int id = 0;
            String nazwa = "";
            String opis = "";
            double cena = 0;
            while (scan.hasNext()) {
                if (licznik % 4 == 0) {
                    id = scan.nextInt();
                } else if (licznik % 4 == 1) {
                    nazwa = scan.next();
                } else if (licznik % 4 == 2) {
                    opis = scan.next();
                } else if (licznik % 4 == 3) {
                    cena = scan.nextDouble();
                } else {
                    dodajPozycje(new Pozycja(id, nazwa, opis, cena));
                }
                licznik++;
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Pozycja> getPozycje() {
        return pozycje;
    }
}