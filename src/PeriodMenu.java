import java.util.Scanner;

class PeriodMenu {
    private final Scanner scanner;
    private final MenuHandler menuHandler;
    private final MenuDisplay menuDisplay;

    public PeriodMenu(Scanner scanner, MenuHandler menuHandler) {
        this.scanner = scanner;
        this.menuHandler = menuHandler;
        this.menuDisplay = new MenuDisplay();
    }

    public void show() {
        menuDisplay.displayPeriodMenu();
        String choice = scanner.nextLine().trim();
        handleChoice(choice);
    }

    private void handleChoice(String choice) {
        switch (choice) {
            case "1":
                menuHandler.handleViewByDay();
                break;
            case "2":
                menuHandler.handleViewByWeek();
                break;
            case "3":
                menuHandler.handleViewByMonth();
                break;
            case "4":
                menuHandler.handleViewByYear();
                break;
            case "0":
                return;
            default:
                System.out.println("Ogiltigt val!");
        }
    }
}