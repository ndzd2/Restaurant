import java.util.Scanner;

public interface Scan {

    default Scanner scaner() {
        Scanner scan = new Scanner(System.in);
        return scan;
    }
}
