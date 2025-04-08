package exercises.ex3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EX3_LM_1_2 {
    public static void main(String[] args) {
        String[] books = {
                "1.Fundamentals of Physics - author: David Halliday - pages: 1200 - published: 2014",
                "2. Thomas Calculus - George B. Thomas Jr. - pages: 1200 - published: 2018",
                "3.The elegant universe - author: Brian Greene - pages:448 - published: 1999",
                "4.A brief history of time - author: Stephen Hawking - pages: 256 - published: 1988"
        };

        String[] students = {
                "1.Yekta Saeidian - 403463124 - computer science",
                "2.Neda Rashtchi - 403463121 - computer science",
                "3.Shahpoor Saeidian - 401463130 - physics",
        };

        saveDataInFile(books, students);
        readDataFromFile();
    }

    public static void saveDataInFile(String[] books, String[] students) {
        try {
            FileWriter bookWriter = new FileWriter("book.txt");
            for (String book : books) {
                bookWriter.write(book + "\n");
            }
            bookWriter.close();

            FileWriter studentWriter = new FileWriter("student.txt");
            for (String student : students) {
                studentWriter.write(student + "\n");
            }
            studentWriter.close();

            System.out.println("file saved successfully!");

        } catch (IOException e) {
            System.out.println("Error: file did not save!" + e.getMessage());
        }
    }

    public static void readDataFromFile() {
        String[] books = new String[4];
        String[] students = new String[3];

        try {
            BufferedReader bookReader = new BufferedReader(new FileReader("book.txt"));
            for (int i = 0; i < books.length; i++) {
                books[i] = bookReader.readLine();
            }
            bookReader.close();

            BufferedReader studentReader = new BufferedReader(new FileReader("student.txt"));
            for (int i = 0; i < students.length; i++) {
                students[i] = studentReader.readLine();
            }
            studentReader.close();

            System.out.println();
            System.out.println("books: ");
            for (String book : books) {
                System.out.println(book);
            }

            System.out.println();
            System.out.println("students: ");
            for (String student : students) {
                System.out.println(student);
            }

        } catch (IOException e) {
            System.out.println("Error: file did not load!" + e.getMessage());
        }
    }
}
