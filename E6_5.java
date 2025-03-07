import java.util.Scanner;

public class E6_5 {
    public static void main(String[] args) {
        int number = 0;
        System.out.println("enter the number of the values: ");
        Scanner scan = new Scanner(System.in);
        number = scan.nextInt();
        System.out.println("enter the values: ");
        double[] value = new double[number];
        for (int i = 0; i < number; i++) {
            value[i] = scan.nextDouble();
        }
        double min = getSmallest(value);
        double max = getLargest(value);
        System.out.println("the average of the values: " + getAverage(value));
        System.out.println("the smallest of the values: " + min);
        System.out.println("the largest of the values: " + max);
        System.out.println("the range of the largest and smallest values: " + getRange(max, min));
    }

    public static double getAverage(double[] values) {
        double sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        return sum / values.length;
    }

    public static double getSmallest(double[] values) {
        double min = values[0];
        for (int i = 0; i < values.length; i++) {
            if (values[i] < min) {
                min = values[i];
            }
        }
        return min;
    }

    public static double getLargest(double[] values) {
        double max = values[0];
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }
        return max;
    }

    public static double getRange(double max, double min) {
        return max - min;
    }
}
