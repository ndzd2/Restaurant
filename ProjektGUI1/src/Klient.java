import java.util.Scanner;

public class Klient {

    Restauracja restauracja;

    public Klient(Restauracja restauracja) {
        this.restauracja = restauracja;
    }

    public void panelKlienta() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            menuKlienta();
            int wybor = scan.nextInt();
            while (wybor != 0) {
                switch (wybor) {
                    case 1:
                        zlozZamowienie(); //pełne menu wyświetli się po okazaniu chęci złożenia zamówienia
                        //dodatkowo wtedy bedzie mozliwosc wybrania czy jest to zamowienie online czy stacjonarne
                        break;
                    case 2:
                        zlozLosoweZamowienie();
                        break;
                    case 3:
                        System.exit(0);
                }
            }
        }
    }

    private void menuKlienta() {
        System.out.println("1. Zloz zamowienie");
        System.out.println("2. Zloz losowe zamowienie");
        System.out.println("3. Wyjscie z programu");
        System.out.print(" >> ");
    }

    private void zlozZamowienie() {
        restauracja.zlozZamowienie();
        return;
    }

    private void zlozLosoweZamowienie() {
        restauracja.zlozZamowienieLosowe();
        return;
    }
}
