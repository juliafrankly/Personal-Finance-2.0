import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PeriodMenu extends AbstractMenu {
    private final TransactionUI transactionUI;

    public PeriodMenu(Scanner scanner, TransactionUI transactionUI) {
        super(scanner);
        this.transactionUI = transactionUI;
    }

    @Override
    protected Map<String, Command> createCommands() {
        Map<String, Command> map = new HashMap<>();
        map.put("1", new ViewByDayCommand(transactionUI));
        map.put("2", new ViewByWeekCommand(transactionUI));
        map.put("3", new ViewByMonthCommand(transactionUI));
        map.put("4", new ViewByYearCommand(transactionUI));
        map.put("0", new ExitCommand());
        return map;
    }

    @Override
    protected void displayMenu() {
        System.out.println("\n~~~~~~~~~~ Visa efter period ~~~~~~~~~~");
        System.out.println("1. Dag");
        System.out.println("2. Vecka");
        System.out.println("3. Månad");
        System.out.println("4. År");
        System.out.println("0. Tillbaka");
        System.out.print("Välj period: ");
    }
}