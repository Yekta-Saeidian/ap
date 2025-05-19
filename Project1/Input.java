package Project1;

import java.util.Scanner;

public class Input {
    Scanner scan = new Scanner(System.in);

    public int scanInt() {
        int num = scan.nextInt();
        scan.nextLine();
        return num;
    }

    public String scanString() {
        return scan.nextLine();
    }
}
