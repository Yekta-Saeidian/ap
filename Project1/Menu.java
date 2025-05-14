package Project1;

public class Menu {
    int option = 0;
    FileHandler fileHandler = new FileHandler();

    public void logIn(Library library, Input input) {
        System.out.println("enter your id");
        int id = input.scanInt();
        boolean found = false;

        for (Student student : fileHandler.loadStudents()) {
            if (id == student.getId()) {
                printStudentMenu(library, input);
                found = true;
                break;
            }
        }

        if (!found) {
            for (LibraryAssistant libraryAssistant : fileHandler.loadAssistants()) {
                if (id == libraryAssistant.getId()) {
                    printLibraryAssistantMenu(library, input);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("user not found!");
            System.out.println("1.try again");
            System.out.println("2.exit");

            option = 0;
            while (option != 2) {
                option = input.scanInt();

                if (option == 1)
                    logIn(library, input);
                else if (option != 2)
                    System.out.println("invalid option");
            }
        }
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

        System.out.println("1.edit personal information");
        System.out.println("2.add new book");
        System.out.println("3.exit");

        libraryAssistantOption(library, input);
    }

    public void printStudentMenu(Library library, Input input) {

        System.out.println("1.search book");
        System.out.println("2.borrow book");
        System.out.println("3.return book");
        System.out.println("4.exit");

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

        while (option != 3) {

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

        while (option != 4) {

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
}
