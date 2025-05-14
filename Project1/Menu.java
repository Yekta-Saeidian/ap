package Project1;

public class Menu {
    int option = 0;

    public void logIn() {
        System.out.println("enter your id");
    }

    public void printManagerMenu(Library library, Input input) {

        System.out.println("1.add library assistant");
        System.out.println("2.overdue books list");
        System.out.println("3.assistant's report");
        System.out.println("4.most borrowed books list");
        System.out.println("5.exit");

        managerOption(library, input);
    }

    public void printLibraryAssistantMenu(Library library, Input input) {

        //System.out.println("1.log in");
        System.out.println("1.edit personal information");
        System.out.println("2.add new book");
        System.out.println("3.exit");

        libraryAssistantOption(library, input);
    }

    public void printStudentMenu(Library library, Input input) {

        System.out.println("1.register");
//        System.out.println("2.log in");
        System.out.println("2.search book");
        System.out.println("3.borrow book");
        System.out.println("4.return book");
        System.out.println("5.exit");

        studentOption(library, input);
    }

    public void managerOption(Library library, Input input) {

        while (option != 5) {

            option = input.scanInt();
            switch (option) {
                case 1:
                    library.addLibraryAssistant();
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

    public void libraryAssistantOption(Library library, Input input) {

        while (option != 4) {

            option = input.scanInt();
            switch (option) {
                case 1:
                    break;
                case 2:
                    library.addBook();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public void studentOption(Library library, Input input) {

        while (option != 6) {

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
