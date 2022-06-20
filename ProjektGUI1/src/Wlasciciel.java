import java.util.Scanner;

public class Wlasciciel {
    Menu menu; //dodawanie usuwanie pozycji, dostepnosc, pliki
    Kuchnia kuchnia; //pracownicy
    Restauracja restauracja; //czekajace i zrealizowane i utarg, rozpocznij prace

    Pracownik pracownik;

    public Wlasciciel(Menu menu, Kuchnia kuchnia, Restauracja restauracja) {
        this.menu = menu;
        this.kuchnia = kuchnia;
        this.restauracja = restauracja;
    }

    public void panelWlasciciela() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            menuAdmina();
            int wybor = scan.nextInt();
            while (wybor <= 0 || wybor > 6) {
                System.out.println("Nie ma takiej opcji!");
                wybor = scan.nextInt();
            }
            switch (wybor) {
                case 1:
                    menuOptions(); //dodawanie usuwanie oznaczanie pozycji zapisywanie wczytywanie
                    break;
                case 2:
                    zamowienia(); //czekajace i zrealizowane
                    break;
                case 3:
                    utarg();
                    break;
                case 4:
                    pracownicy(); //zatrudnienie, wyrzucenie, wypisanie inforamcji o pracowniku wraz z zamowieniami i napiwkami, oraz wyswietlenie wszystkich
                    break;
                case 5:
                    praca(); //rozpoczecie pracy
                    break;
                case 6:
                    //System.exit(0);
                    return;
            }
        }
    }

    private void menuAdmina() {
        System.out.println("1. Opcje menu");
        System.out.println("2. Opcje zamowien");
        System.out.println("3. Pokaz utarg");
        System.out.println("4. Opcje pracownikow");
        System.out.println("5. Rozpoczecie pracy");
        System.out.println("6. Wyjscie z programu");
        System.out.print(" >> ");
    }

    private void menuOptions() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Dodaj pozycje");
        System.out.println("2. Usun pozycje");
        System.out.println("3. Oznacz pozycje");
        System.out.println("4. Zapisz plik menu");
        System.out.println("5. Wczytaj plik menu");
        System.out.println("6. Powrot");
        System.out.print(" >> ");
        int wybor = scan.nextInt();
        switch (wybor) {
            case 1:
                menu.dodajPozycje();
                break;
            case 2:
                System.out.println("Wybierz numer pozycji ktora chcesz usunac: ");
                int n1 = scan.nextInt();
                menu.usunPozycje(n1);
                break;
            case 3:
                System.out.println("Wybierz numer pozycji ktorej dostepnosc chcesz zmienic: ");
                int n2 = scan.nextInt();
                menu.zmienDostepnosc(n2);
                break;
            case 4:
                menu.zapiszMenu("C:/Users/Patryk/Documents/menu.txt");
                break;
            case 5:
                menu.wczytajMenu("C:/Users/Patryk/Documents/menu.txt");
                break;
            case 6:
                return;
        }
    }

    private void zamowienia() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Oczekujace zamowienia");
        System.out.println("2. Zrealizowane zamowienia");
        System.out.println("3. Powrot");
        System.out.print(" >> ");
        int wybor = scan.nextInt();
        switch (wybor) {
            case 1:
                restauracja.wypiszZamowieniaOczekujace();
                break;
            case 2:
                restauracja.wypiszZamowieniaZrealizowane();
                break;
            case 3:
                return;
        }
    }

    private void utarg() {
        restauracja.zliczUtarg();
    }

    private void pracownicy() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Zatrudnij pracownika");
        System.out.println("2. Zwolnij pracownika");
        System.out.println("3. Informacje o pracowniku");
        System.out.println("4. Wyswietl pracownikow");
        System.out.println("5. Powrot");
        System.out.print(" >> ");
        int wybor = scan.nextInt();
        switch (wybor) {
            case 1:
                System.out.println("1. Zatrudnij kucharza");
                System.out.println("2. Zatrudnij kelnera");
                System.out.println("3. Zatrudnij dostawce");
                System.out.println("4. Powrot");
                int n1 = scan.nextInt();
                System.out.println("Podaj imie, nazwisko i numer telefonu");
                switch (n1) {
                    case 1:
                        kuchnia.dodajKucharza(new Pracownik(scan.next(), scan.next(), scan.next()));
                        break;
                    case 2:
                        kuchnia.dodajKelnera(new Pracownik(scan.next(), scan.next(), scan.next()));
                        break;
                    case 3:
                        kuchnia.dodajDostawce(new Pracownik(scan.next(), scan.next(), scan.next()));
                        break;
                    case 4:
                        return;
                }
                break;
            case 2:
                System.out.println("1. Zwolnij kucharza");
                System.out.println("2. Zwolnij kelnera");
                System.out.println("3. Zwolnij dostawce");
                System.out.println("4. Powrot");
                int n2 = scan.nextInt();
                switch (n2) {
                    case 1:
                        for (int i = 0; i < kuchnia.kucharze.size(); i++) {
                            System.out.println("Kucharz nr " + (i + 1) + " " + kuchnia.kucharze.get(i));
                        }
                        System.out.println("Podaj numer kucharza aby go zwolnic");
                        int n3 = scan.nextInt();
                        kuchnia.usunKucharza(kuchnia.kucharze.get(n3 - 1));
                        break;
                    case 2:
                        for (int i = 0; i < kuchnia.kelnerzy.size(); i++) {
                            System.out.println("Kelner nr " + (i + 1) + " " + kuchnia.kelnerzy.get(i));
                        }
                        System.out.println("Podaj numer kelnera aby go zwolnic");
                        int n4 = scan.nextInt();
                        kuchnia.usunKelnera(kuchnia.kelnerzy.get(n4 - 1));
                        break;
                    case 3:
                        for (int i = 0; i < kuchnia.dostawcy.size(); i++) {
                            System.out.println("Dostawca nr " + (i + 1) + " " + kuchnia.dostawcy.get(i));
                        }
                        System.out.println("Podaj numer dostawcy aby go zwolnic");
                        int n5 = scan.nextInt();
                        kuchnia.usunDostawce(kuchnia.dostawcy.get(n5 - 1));
                        break;
                    case 4:
                        return;
                }
                break;
            case 3:
                System.out.println("Wybierz nr pracownika: ");
                for (int i = 0; i < Pracownik.ekstensja.size(); i++) {
                    System.out.println((i + 1) + ". " + Pracownik.ekstensja.get(i).toString());
                }
                int n6 = scan.nextInt();
                System.out.println(Pracownik.ekstensja.get(n6 - 1).toString());
                break;
            case 4:
                System.out.println("Pracownicy: ");
                for (Pracownik p : Pracownik.ekstensja) {
                    System.out.println(p.toString());
                }
                break;
            case 5:
                return;
        }
    }

    private void praca() {
        System.out.println("Rozpoczynamy prace");
        restauracja.rozpocznijPrace();
    }
}
