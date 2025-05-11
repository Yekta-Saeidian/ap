package Project1;

public class Main {

    public static void main(String[] args) {

        Library library = new Library("The Eternal Bookshelf");
        Input input = new Input();
        Menu menu = new Menu();

        System.out.println("User Type:\n");
        System.out.println("1.Manager");
        System.out.println("2.Student");

        int option = 0;

        while (option!=1 || option!=2) {

            option = input.scanInt();
            switch (option) {
                case 1:
                    menu.printManagerMenu(library , input);
                    break;
                case 2:
                    menu.printStudentMenu(library , input);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}
