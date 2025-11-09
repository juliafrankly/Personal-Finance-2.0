import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean running = true;

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║  Välkommen till din ekonomi-app!  ║");
        System.out.println("╚═══════════════════════════════════╝");

        while (running) {
            displayMainMenu();
            handleMainMenuChoice();
        }

        System.out.println("\nTack för att du använde programmet!");
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n○-○-○-○-○-○-○-○ HUVUDMENY ○-○-○-○-○-○-○-○");
        System.out.println("1. Lägg till en transaktion");
        System.out.println("2. Ta bort en transaktion");
        System.out.println("3. Visa alla transaktioner");
        System.out.println("4. Visa transaktioner efter period");
        System.out.println("5. Visa transaktioner efter typ");
        System.out.println("6. Visa nuvarande kontobalans");
        System.out.println("0. Avsluta");
        System.out.println("○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○");
        System.out.print("\nDitt val: ");
    }

    private static void handleMainMenuChoice() {
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                TransactionUI.addTransaction();
                break;
            case "2":
                TransactionUI.removeTransaction();
                break;
            case "3":
                TransactionUI.displayAllTransactions();
                break;
            case "4":
                showPeriodMenu();
                break;
            case "5":
                TransactionUI.viewByType();
                break;
            case "6":
                TransactionUI.displayBalance();
                break;
            case "0":
                running = false;
                break;
            default:
                System.out.println("Ogiltigt val! Försök igen.");
        }
    }

    private static void showPeriodMenu() {
        System.out.println("\n~~~~~~~~~~ Visa efter period ~~~~~~~~~~");
        System.out.println("1. Dag");
        System.out.println("2. Vecka");
        System.out.println("3. Månad");
        System.out.println("4. År");
        System.out.println("0. Tillbaka");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print("Välj period: ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                TransactionUI.viewByDay();
                break;
            case "2":
                TransactionUI.viewByWeek();
                break;
            case "3":
                TransactionUI.viewByMonth();
                break;
            case "4":
                TransactionUI.viewByYear();
                break;
            case "0":
                return;
            default:
                System.out.println("Ogiltigt val!");
        }
    }
}