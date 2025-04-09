package exercises.ex1;

import java.util.Scanner;

public class E6_2 {
    public static void main(String[] args) {
        System.out.println("enter the number of values:");
        int number = 0;
        Scanner scan = new Scanner(System.in);
        number = scan.nextInt();
        System.out.println("enter the values:");
        int[] value = new int[number];
        for (int i = 0; i < number; i++) {
            value[i] = scan.nextInt();
        }
        System.out.println("smallest input: " + smallestInput(value));
        System.out.println("largest input: " + largestInput(value));
        System.out.println("the number of even inputs: " + numberOfEven(value));
        System.out.println("the number of odd inputs: " + numberOfOdd(value));
        cumulativeTotal(value);
        adjacentDuplicate(value);
    }

    public static int smallestInput(int[] value) {
        int smallest = value[0];
        for (int i = 1; i < value.length; i++) {
            if (value[i] < smallest)
                smallest = value[i];
        }
        return smallest;
    }

    public static int largestInput(int[] value) {
        int largest = value[0];
        for (int i = 1; i < value.length; i++) {
            if (value[i] > largest)
                largest = value[i];
        }
        return largest;
    }

    public static int numberOfEven(int[] value) {
        int numberOfEven = 0;
        for (int i = 0; i < value.length; i++) {
            if (value[i] % 2 == 0)
                numberOfEven++;
        }
        return numberOfEven;
    }

    public static int numberOfOdd(int[] value) {
        int numberOfOdd = 0;
        for (int i = 0; i < value.length; i++) {
            if (value[i] % 2 == 1)
                numberOfOdd++;
        }
        return numberOfOdd;
    }

    public static void cumulativeTotal(int[] value) {
        int cumulativeTotal = 0;
        for (int i = 0; i < value.length; i++) {
            cumulativeTotal += value[i];
            System.out.print(cumulativeTotal);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void adjacentDuplicate(int[] value) {
        int[] repetedNum = new int[value.length];
        int j = 0;
        for (int i = 0; i < value.length - 1; i++) {
            int check = 0;
            for (int k = 0; k < value.length; k++) {
                if (value[i] == repetedNum[k])
                    check++;
            }
            if (check == 0) {
                if (value[i] == value[i + 1]) {
                    repetedNum[j] = value[i];
                    j++;
                }
            }
        }
        System.out.print("adjacent duplicates: ");
        for (int i = 0; i < j; i++) {
            System.out.print(repetedNum[i] + " ");
        }
    }
}

