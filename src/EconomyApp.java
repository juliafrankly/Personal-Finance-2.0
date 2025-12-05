import java.util.Scanner;

public class EconomyApp {
    private final Scanner scanner;
    private final TransactionUI transactionUI;

    public EconomyApp() {
        this.scanner = new Scanner(System.in);
        this.transactionUI = new TransactionUI(scanner);
    }

    public void start() {
        displayWelcome();

        MainMenu mainMenu = new MainMenu(scanner, transactionUI);
        mainMenu.show();

        displayGoodbye();
        scanner.close();
    }

    private void displayWelcome() {
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║  Välkommen till din ekonomi-app!  ║");
        System.out.println("╚═══════════════════════════════════╝");
    }

    private void displayGoodbye() {
        System.out.println("\nTack för att du använde programmet!");
    }
}