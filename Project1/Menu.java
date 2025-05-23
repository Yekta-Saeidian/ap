package Project1;

public class Menu {
    int option = 0;
    FileHandler fileHandler = new FileHandler();

    public int logIn(Library library, Input input) {
        System.out.println("enter your id");
        int id = input.scanInt();
        boolean found = false;

        for (Student student : fileHandler.loadStudents()) {
            if (id == student.getId()) {
                printStudentMenu(library, input, id);
                found = true;
                return 1;
            }
        }

        if (!found) {
            for (LibraryAssistant libraryAssistant : fileHandler.loadAssistants()) {
                if (id == libraryAssistant.getId()) {
                    printLibraryAssistantMenu(library, input, id);
                    found = true;
                    return 1;
                }
            }
        }

        if (!found) {
            System.out.println("user not found!");
            System.out.println("1.try again");
            System.out.println("2.exit");

            option = 0;
            while (true) {
                option = input.scanInt();

                switch (option) {
                    case 1:
                        logIn(library, input);
                        return 1;
                    case 2:
                        return 0;
                    default:
                        System.out.println("invalid option");
                        break;
                }
            }
        }

        return 0;
    }

    public boolean printManagerMenu(Library library, Input input) {

        System.out.println("1.add library assistant");
        System.out.println("2.overdue books list");
        System.out.println("3.assistant's report");
        System.out.println("4.most borrowed books list");
        System.out.println("5.exit");

        while (true) {

            option = input.scanInt();
            switch (option) {
                case 1:
                    library.addLibraryAssistant(library, input);
                    break;
                case 2:
                    library.overdueBooksList(library, input);
                    break;
                case 3:
                    library.showAssistantsReport(library, input);
                    break;
                case 4:
                    library.showMostBorrowedBooks(library, input);
                    break;
                case 5:
                    return true;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public boolean printLibraryAssistantMenu(Library library, Input input, int assistantId) {

        System.out.println("1.edit personal information");
        System.out.println("2.add new book");
        System.out.println("3.confirmation of book borrowing");
        System.out.println("4.book return confirmation");
        System.out.println("5.exit");


        while (true) {
            int option = input.scanInt();
            switch (option) {
                case 1:
                    break;
                case 2:
                    library.addBook(library, input, assistantId);
                    break;
                case 3:
                    library.showPendingRequests(library, input, assistantId, option);
                    break;
                case 4:
                    library.showPendingRequests(library, input, assistantId, option);
                    break;
                case 5:
                    return true;
                default:
                    System.out.println("invalid selection");
            }
        }
    }

    public boolean printStudentMenu(Library library, Input input, int id) {

        System.out.println("1.search book");
        System.out.println("2.unreturned book list");
        System.out.println("3.return book");
        System.out.println("4.exit");

        while (true) {

            option = input.scanInt();
            switch (option) {
                case 1:
                    library.searchBook(library, input, id);
                    break;
                case 2:
                    library.unreturnedBookList(library, input, id);
                    break;
                case 3:
                    library.returnBookRequest(library, input, id);
                    break;
                case 4:
                    return true;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}
