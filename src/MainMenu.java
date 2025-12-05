import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenu extends AbstractMenu {
    private final TransactionUI transactionUI;

    public MainMenu(Scanner scanner, TransactionUI transactionUI) {
        super(scanner);
        this.transactionUI = transactionUI;
    }

    @Override
    protected Map<String, Command> createCommands() {
        Map<String, Command> map = new HashMap<>();
        map.put("1", new AddTransactionCommand(transactionUI));
        map.put("2", new RemoveTransactionCommand(transactionUI));
        map.put("3", new DisplayAllTransactionsCommand(transactionUI));
        map.put("4", new ShowSubMenuCommand(new PeriodMenu(scanner, transactionUI)));
        map.put("5", new ViewByTypeCommand(transactionUI));
        map.put("6", new DisplayBalanceCommand(transactionUI));
        map.put("0", new ExitCommand());
        return map;
    }

    @Override
    protected void displayMenu() {
        System.out.println("\n○-○-○-○-○-○-○-○ HUVUDMENY ○-○-○-○-○-○-○-○");
        System.out.println("1. Lägg till en transaktion");
        System.out.println("2. Ta bort en transaktion");
        System.out.println("3. Visa alla transaktioner");
        System.out.println("4. Visa transaktioner efter period");
        System.out.println("5. Visa transaktioner efter typ");
        System.out.println("6. Visa nuvarande kontobalans");
        System.out.println("0. Avsluta");
        System.out.print("\nDitt val: ");
    }
}