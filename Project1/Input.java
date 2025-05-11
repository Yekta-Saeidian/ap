package Project1;

import java.util.Scanner;

public class Input {
    Scanner scan = new Scanner(System.in);

    public int scanInt(){
        return scan.nextInt();
    }

    public String scanString(){
        return scan.next();
    }
}
