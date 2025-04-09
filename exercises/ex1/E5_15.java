package exercises.ex1;

import java.util.Scanner;

public class E5_15 {
    public static void main(String[] args){
        System.out.println("enter the income amount:");
        Scanner scan = new Scanner(System.in);
        double income = scan.nextDouble();
        double incomeTax = 0;
        if(income <= 50000)
            incomeTax = income * .01;
        else if(income > 50000 && income <= 75000)
            incomeTax = income * .02;
        else if(income > 75000 && income <= 100000)
            incomeTax = income * .03;
        else if(income > 100000 && income <= 250000)
            incomeTax = income * .04;
        else if(income > 250000 && income <= 500000)
            incomeTax = income * .05;
        else if(income > 500000)
            incomeTax = income * .06;
        System.out.println("income tax: " + incomeTax);
    }
}
