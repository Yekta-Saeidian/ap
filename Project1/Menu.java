package Project1;

public class Menu {
    int option = 0;

    public void printManagerMenu(Library library, Input input) {

        System.out.println("1.add library assistant");
        System.out.println("2.overdue books list");
        System.out.println("3.assistant's report");
        System.out.println("4.most borrowed books list");

        managerOption(library, input);
    }

    public void printLibraryAssistantMenu(Library library, Input input) {

        System.out.println("1.log in");
        System.out.println("2.edit personal information");
        System.out.println("3.add new book");

        libraryAssistantOption(library, input);
    }

    public void printStudentMenu(Library library, Input input) {

        System.out.println("1.register");
        System.out.println("2.log in");
        System.out.println("3.search book");
        System.out.println("4.borrow book");
        System.out.println("5.return book");

        studentOption(library, input);
    }

    public void managerOption(Library library, Input input) {

        while (option != 1 || option != 2 || option != 3 || option != 4) {

            option = input.scanInt();
            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public void libraryAssistantOption(Library library, Input input) {

        while (option != 1 || option != 2 || option != 3) {

            option = input.scanInt();
            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    library.addBook();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public void studentOption(Library library, Input input) {

        while (option != 1 || option != 2 || option != 3 || option != 4) {

            option = input.scanInt();
            switch (option) {
                case 1:
                    library.register();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}
