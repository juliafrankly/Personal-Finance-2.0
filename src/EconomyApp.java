import java.util.Scanner;

class EconomyApp {
    private final Scanner scanner;
    private final MenuDisplay menuDisplay;
    private final MenuHandler menuHandler;
    private boolean running;

    public EconomyApp() {
        this.scanner = new Scanner(System.in);
        this.menuDisplay = new MenuDisplay();
        this.menuHandler = new MenuHandler(scanner);
        this.running = true;
    }

    public void start() {
        menuDisplay.displayWelcome();

        while (running) {
            menuDisplay.displayMainMenu();
            handleChoice(scanner.nextLine().trim());
        }

        menuDisplay.displayGoodbye();
    }

    private void handleChoice(String choice) {
        switch (choice) {
            case "1":
                menuHandler.handleAddTransaction();
                break;
            case "2":
                menuHandler.handleRemoveTransaction();
                break;
            case "3":
                menuHandler.handleDisplayAllTransactions();
                break;
            case "4":
                handlePeriodMenu();
                break;
            case "5":
                menuHandler.handleViewByType();
                break;
            case "6":
                menuHandler.handleDisplayBalance();
                break;
            case "0":
                running = false;
                break;
            default:
                System.out.println("Ogiltigt val! Försök igen.");
        }
    }

    private void handlePeriodMenu() {
        PeriodMenu periodMenu = new PeriodMenu(scanner, menuHandler);
        periodMenu.show();
    }
}