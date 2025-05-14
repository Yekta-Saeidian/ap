package Project1;

public class Main {

    public static void main(String[] args) {

        Library library = new Library("The Eternal Bookshelf");
        Input input = new Input();
        Menu menu = new Menu();

        System.out.println("User Type:\n");
        System.out.println("1.Manager");
        System.out.println("2.Library Assistant");
        System.out.println("3.Student");

        int option1 = 0;
        int option2 = 0;

        while (option1 != 1 && option1 != 2 && option1 != 3) {

            option1 = input.scanInt();
            switch (option1) {
                case 1:
                    menu.printManagerMenu(library, input);
                    break;
                case 2:
                    menu.logIn(library, input);
                    break;
                case 3:
                    System.out.println("1.register");
                    System.out.println("2.log in");
                    while (option1 != 1 && option1 != 2) {
                        option2 = input.scanInt();
                        switch (option2) {
                            case 1:
                                library.register();
                                break;
                            case 2:
                                menu.logIn(library, input);
                                break;
                            default:
                                System.out.println("Invalid option");
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}
