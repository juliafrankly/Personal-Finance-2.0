import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.time.temporal.IsoFields;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TransactionUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void displayAllTransactions() {
        if (TransactionManager.isEmpty()) {
            System.out.println("\nInga transaktioner registrerade än.");
            return;
        }

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Alla transaktioner ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (Transaction t : TransactionManager.getAllTransactions()) {
            System.out.println(t);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void displayTransactionsWithSummary(List<Transaction> transactions, String period) {
        if (transactions.isEmpty()) {
            System.out.println("\nInga transaktioner hittades för " + period);
            return;
        }

        System.out.println("\n~~~~~~~~~~~ Transaktioner för " + period + " ~~~~~~~~~~~");
        for (Transaction t : transactions) {
            System.out.println(t);
        }

        System.out.println("\n~~~~~~~~~~~ Sammanfattning ~~~~~~~~~~~");
        TransactionManager.TransactionSummary summary = TransactionManager.calculateSummary(transactions);
        System.out.println(summary);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void viewByDay() {
        System.out.print("\nAnge datum (format: yyyy-MM-dd): ");
        String dateString = scanner.nextLine().trim();

        try {
            LocalDate date = LocalDate.parse(dateString);
            List<Transaction> filtered = TransactionManager.getTransactionsByDate(date);
            displayTransactionsWithSummary(filtered, "dag: " + dateString);
        } catch (DateTimeParseException e) {
            System.out.println("Felaktigt datumformat! Använd formatet: yyyy-MM-dd");
        }
    }

    public static void viewByWeek() {
        System.out.print("\nAnge år och veckonummer (format: yyyy-VV, t.ex. 2024-15): ");
        String weekInput = scanner.nextLine().trim();

        try {
            String[] split = weekInput.split("-");
            if (split.length != 2) {
                throw new IllegalArgumentException("Felaktigt format");
            }

            int year = Integer.parseInt(split[0]);
            int week = Integer.parseInt(split[1]);

            if (week < 1 || week > 53) {
                System.out.println("Veckonummer måste vara mellan 1 och 53");
                return;
            }

            LocalDate startDate = LocalDate.ofYearDay(year, 1)
                    .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week)
                    .with(DayOfWeek.MONDAY);

            LocalDate endDate = startDate.plusDays(7);

            List<Transaction> filtered = TransactionManager.getTransactionsByWeek(startDate, endDate);
            String period = "vecka " + week + ", " + year + " (" + startDate + " - " + endDate.minusDays(1) + ")";
            displayTransactionsWithSummary(filtered, period);
        } catch (Exception e) {
            System.out.println("Felaktigt format! Använd format: yyyy-VV (t.ex. 2025-15)");
        }
    }

    public static void viewByMonth() {
        System.out.print("\nAnge månad och år (format: yyyy-MM): ");
        String monthInput = scanner.nextLine().trim();

        try {
            YearMonth yearMonth = YearMonth.parse(monthInput);
            List<Transaction> filtered = TransactionManager.getTransactionsByMonth(
                    yearMonth.getYear(), yearMonth.getMonthValue());
            displayTransactionsWithSummary(filtered, "månad: " + monthInput);
        } catch (DateTimeParseException e) {
            System.out.println("Felaktigt format! Använd format: yyyy-MM (t.ex. 2025-01)");
        }
    }

    public static void viewByYear() {
        System.out.print("\nAnge år (format: yyyy): ");
        String yearInput = scanner.nextLine().trim();

        try {
            int year = Integer.parseInt(yearInput);
            if (year < 2020 || year > 2025) {
                System.out.println("Året måste vara mellan 2020 och 2025");
                return;
            }

            List<Transaction> filtered = TransactionManager.getTransactionsByYear(year);
            displayTransactionsWithSummary(filtered, "år: " + yearInput);
        } catch (NumberFormatException e) {
            System.out.println("Felaktigt årformat! Använd format: yyyy");
        }
    }

    public static void viewByType() {
        System.out.println("\nVälj typ:");
        System.out.println("1. Inkomster");
        System.out.println("2. Utgifter");
        System.out.print("Ditt val: ");

        String choice = scanner.nextLine().trim();
        TransactionType type = null;

        switch (choice) {
            case "1":
                type = TransactionType.INCOME;
                break;
            case "2":
                type = TransactionType.EXPENSE;
                break;
            default:
                System.out.println("Ogiltigt val!");
                return;
        }

        List<Transaction> filtered = TransactionManager.getTransactionsByType(type);
        displayTransactionsWithSummary(filtered, type.toString());
    }

    public static void displayBalance() {
        double balance = TransactionManager.calculateBalance();
        System.out.println("\n○-○-○-○-○-○ Aktuell Balans ○-○-○-○-○-○");
        System.out.printf("Balans: %.2f kr\n", balance);
        System.out.println("○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○-○");
    }

    public static void addTransaction() {
        try {
            System.out.println("\n~~~~~~~~~ Lägg till transaktion ~~~~~~~~~");

            System.out.println("Välj typ:");
            System.out.println("1. Inkomst");
            System.out.println("2. Utgift");
            System.out.print("Ditt val: ");
            String typeChoice = scanner.nextLine().trim();

            TransactionType type;
            if (typeChoice.equals("1")) {
                type = TransactionType.INCOME;
            } else if (typeChoice.equals("2")) {
                type = TransactionType.EXPENSE;
            } else {
                System.out.println("Ogiltigt val!");
                return;
            }

            System.out.print("\nAnge belopp: ");
            double amount = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Ange kommentar: ");
            String comment = scanner.nextLine().trim();

            System.out.print("Ange datum (yyyy-MM-dd) eller tryck 'enter' för dagens datum: ");
            String dateInput = scanner.nextLine().trim();
            LocalDate date;
            if (dateInput.isEmpty()) {
                date = LocalDate.now();
            } else {
                date = LocalDate.parse(dateInput);
            }

            Transaction transaction = new Transaction(amount, comment, date, type);
            TransactionManager.addTransaction(transaction);

            System.out.println("\nTransaktionen har lagts till!");
            System.out.println(transaction);
        } catch (NumberFormatException e) {
            System.out.println("Felaktigt belopp!");
        } catch (DateTimeParseException e) {
            System.out.println("Felaktigt datumformat!");
        } catch (IllegalArgumentException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }

    public static void removeTransaction() {
        if (TransactionManager.isEmpty()) {
            System.out.println("\nDet finns inga transaktioner att ta bort!");
            return;
        }

        displayAllTransactions();

        System.out.print("\nAnge ID för transaktionen du vill ta bort: ");
        String idInput = scanner.nextLine().trim();

        try {
            UUID id = UUID.fromString(idInput);
            Transaction toRemove = TransactionManager.getTransactionById(id);

            if (toRemove != null) {
                System.out.println("\nDu är på väg att ta bort:");
                System.out.println(toRemove);
                System.out.print("Är du säker? (ja/nej): ");
                String confirmation = scanner.nextLine().trim().toLowerCase();

                if (confirmation.equals("ja") || confirmation.equals("j")) {
                    TransactionManager.removeTransaction(id);
                    System.out.println("Transaktionen har tagits bort!");
                } else {
                    System.out.println("Avbryter borttagning...");
                }
            } else {
                System.out.println("Ingen transaktion hittades med det ID:t.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Felaktigt ID-format! Vänligen kopiera det exakta ID:t.");
        }
    }
}