package Project1;

public class Main {

    public static void main(String[] args) {

        StorageType storageType = ConfigReader.readConfig();
        System.out.println("Using storage type: " + storageType);

        Library library = new Library("The Eternal Bookshelf");
        Input input = new Input();
        Menu menu = new Menu();

        System.out.println("\nUser Type:\n");
        System.out.println("1.Manager");
        System.out.println("2.Library Assistant");
        System.out.println("3.Student");
        System.out.println("4.exit");

        boolean exitProgram = false;

        while (!exitProgram) {

            int option = input.scanInt();

            switch (option) {
                case 1:
                    menu.printManagerMenu(library, input);
                    break;
                case 2:
                    menu.logIn(library, input);
                    break;
                case 3:
                    System.out.println("1.register");
                    System.out.println("2.log in");

                    int studentOption = input.scanInt();
                    switch (studentOption) {
                        case 1:
                            library.register(library, input);
                            break;
                        case 2:
                            menu.logIn(library, input);
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case 4:
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}
