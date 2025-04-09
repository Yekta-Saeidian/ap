package exercises.ex1;

import java.util.Scanner;

public class E5_18 {
    public static void main(String[] args) {
        System.out.println("enter 3 names:");
        Scanner scan = new Scanner(System.in);
        String str1 = scan.nextLine();
        String str2 = scan.nextLine();
        String str3 = scan.nextLine();
        if (str1.compareTo(str2) > 0) {
            String temp = new String(str2);
            str2 = str1;
            str1 = temp;
        }
        if (str1.compareTo(str3) > 0) {
            String temp = new String(str3);
            str3 = str1;
            str1 = temp;
        }
        if (str2.compareTo(str3) > 0) {
            String temp = new String(str3);
            str3 = str2;
            str2 = temp;
        }
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
    }
}
