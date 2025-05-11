package Project1;

public class Menu {
    int option;

    public void printManagerMenu(Library library , Input input) {

        System.out.println("1.overdue books list");
        System.out.println("2.assistant's report");
        System.out.println("3.most borrowed books list");
        System.out.println("4.add new book");

        managerOption(library , input);
    }

    public void printStudentMenu(Library library , Input input) {

        System.out.println("1.register");
        System.out.println("2.search book");
        System.out.println("3.borrow book");
        System.out.println("4.return book");

        studentOption(library , input);
    }

    public void managerOption(Library library , Input input) {
        option = input.scanInt();

        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                library.addBook();
                break;
            default:
                break;
        }
    }

    public void studentOption(Library library , Input input) {
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
                break;
        }
    }

}
