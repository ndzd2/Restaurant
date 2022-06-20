import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
	    Menu menu = Menu.utworzMenu();
	    menu.dodajPozycje(new Pozycja(1, "Pierogi", "Przykladowy opis", 12.5));
	    menu.dodajPozycje(new Pozycja(2, "Stek Argentynski", "250g miesa", 49.0));
	    menu.dodajPozycje(new Pozycja(3, "Zupa grochowa", "Talerz zupy grochowej", 25.0));
	    menu.dodajPozycje(new Pozycja(4, "Filet Mignon", "filet miesa", 55.0));
	    menu.dodajPozycje(new Pozycja(5, "Zeberka z grilla", "zeberka w sosie miodowo orzechowym", 42.0));
	    //menu.wypiszPozycje();

		Restauracja restauracja = new Restauracja();
	    for(int i = 0; i < 10; i++) {
	        restauracja.zlozZamowienieLosowe();
	    }
		//restauracja.wypiszZamowieniaOczekujace();
		//restauracja.rozpocznijPrace();
		//restauracja.zliczUtarg();
		Klient klient = new Klient(restauracja);
		Wlasciciel admin = new Wlasciciel(restauracja.menu, restauracja.kuchnia, restauracja);
		Scanner scan = new Scanner(System.in);
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
}
