import java.util.Scanner;

public class E6_3 {
    public static void main(String[] args) {
        System.out.println("write something: ");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        getUppercase(str);
        getSecondLetter(str);
        underscore(str);
        System.out.println("the number of vowels: " + numberOfVowels(str));
        positionOfVowels(str);
    }

    public static void getUppercase(String str) {
        System.out.print("uppercase letters: ");
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                System.out.print(str.charAt(i));
            }
        }
        System.out.println();
    }

    public static void getSecondLetter(String str) {
        System.out.print("every second letter: ");
        String[] parts = str.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].length() > 1) {
                System.out.print(parts[i].charAt(1) + " ");
            }
        }
        System.out.println();
    }

    public static void underscore(String str) {
        System.out.print("vowels to underscore: ");
        String newStr = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u' || str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U') {
                newStr += '_';
            } else
                newStr += str.charAt(i);
        }
        System.out.println(newStr);
    }

    public static int numberOfVowels(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u' || str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U') {
                count++;
            }
        }
        return count;
    }


    public static void positionOfVowels(String str) {
        System.out.print("the positions of the vowels: ");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u' || str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U') {
                System.out.print(i + " ");
            }
        }
    }
}


