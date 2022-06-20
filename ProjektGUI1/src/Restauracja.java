import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Restauracja {
    List<Zamowienie> doZrealizowania;
    List<Zamowienie> zrealizowane;
    Kuchnia kuchnia;
    Menu menu;
    
    public Restauracja() {
        doZrealizowania = new ArrayList<Zamowienie>();
        zrealizowane = new ArrayList<Zamowienie>();
        menu = Menu.utworzMenu();
        kuchnia = new Kuchnia();
        kuchnia.dodajKucharza(new Pracownik("Marek", "Jachim", "+48127412454"));
        kuchnia.dodajKucharza(new Pracownik("Piotr", "Nowak", "+48853285786"));
        kuchnia.dodajDostawce(new Pracownik("Janusz", "Pyrczak", "+48716233842"));
        kuchnia.dodajKelnera(new Pracownik("Sylwia", "Mazur", "+48123451616"));
    }

    public void zlozZamowienie() {
        Scanner scan = new Scanner(System.in);
        int wybor;
        System.out.print("Wybierz opcje.\n1. Zamowienie Stacjonarne\n2. Zamowienie Zdalne\n3. Powrot\n >> ");
        wybor = scan.nextInt();
        while(wybor < 1 || wybor > 3) {
            System.out.print("Nie ma takiej opcji.\nWybierz opcje [1-3].\n1. Zamowienie Stacjonarne\n2. Zamowienie Zdalne\n3. Powrot\n >> ");
            wybor = scan.nextInt();
        }
        if(wybor == 1) {
            System.out.print("Podaj numer stolika: ");
            int stolik = scan.nextInt();
            doZrealizowania.add(new ZamowienieStacjonarne(stolik));
        }else if (wybor == 3) {
            Wlasciciel admin = new Wlasciciel(menu, kuchnia, this);
            Klient klient = new Klient(this);
            while (true) {
                System.out.println("Jestes wlascicielem czy klientem restauracji? (W/K)");
                String odp = scan.next();
                if (odp.equalsIgnoreCase("w")) {
                    admin.panelWlasciciela();
                } else if (odp.equalsIgnoreCase("k")) {
                    klient.panelKlienta();
                } else {
                    System.out.println("Niepoprawny wybor");
                }
            }
        }
        else {
            System.out.print("Podaj adres dostawy: ");
            String adres = scan.nextLine();
            doZrealizowania.add(new ZamowienieZdalne(adres));
        }
        doZrealizowania.get(doZrealizowania.size() - 1).wybierzPozycje();
    }

    public void zlozZamowienieLosowe() {
        Random rd = new Random();
        if (rd.nextInt(2) == 0) {
            doZrealizowania.add(new ZamowienieStacjonarne(rd.nextInt(12) + 1));
        } else {
            doZrealizowania.add(new ZamowienieZdalne("Adres"));
        }
        doZrealizowania.get(doZrealizowania.size() - 1).losowePozycje();
    }

    public void wypiszZamowieniaOczekujace() {
        for(Zamowienie zamowienie : doZrealizowania) {
            System.out.println(zamowienie.toString());
        }
    }

    public void wypiszZamowieniaZrealizowane() {
        for(Zamowienie zamowienie : zrealizowane) {
            System.out.println(zamowienie.toString());
        }
    }

    public void zliczUtarg() {
        double suma = 0;
        for (Zamowienie zamowienie : zrealizowane) {
            for (Pozycja pozycja : menu.getPozycje()) {
                suma += pozycja.cena * zamowienie.zamowionePozycje.getOrDefault(pozycja.id, 0);
            }
        }
        System.out.println("Utarg wynosi: " + suma + " zł");
    }

    public void rozpocznijPrace() {
        int suma = 0;
        int stac = 0;
        while(doZrealizowania.size() > 0) {
            stac = 0;
            for(Zamowienie z : doZrealizowania) {
                if(z instanceof ZamowienieStacjonarne) {
                    stac++;
                }
            }
            if (stac == 0) {
                for(Zamowienie z : doZrealizowania) {
                    suma = 0;
                    for (Pozycja pozycja : menu.getPozycje()) {
                        suma += pozycja.cena * z.zamowionePozycje.getOrDefault(pozycja.id, 0);
                    }
                    kuchnia.wykonajZamowienie(z.ilePozycji(), true, suma);
                    zrealizowane.add(z);
                    doZrealizowania.remove(z);
                    break;
                }
            } else {
                for(Zamowienie z : doZrealizowania) {
                    if(z instanceof ZamowienieStacjonarne) {
                        suma = 0;
                        for (Pozycja pozycja : menu.getPozycje()) {
                            suma += pozycja.cena * z.zamowionePozycje.getOrDefault(pozycja.id, 0);
                        }
                        kuchnia.wykonajZamowienie(z.ilePozycji(), false, suma);
                        zrealizowane.add(z);
                        doZrealizowania.remove(z);
                        break;
                    }
                }
            }
        }
    }
}