import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TransactionManager {
    private static final List<Transaction> transactions = new ArrayList<>();

    public static void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaktionen kan inte ha ett null-värde");
        }
        transactions.add(transaction);
    }

    public static boolean removeTransaction(UUID id) {
        return transactions.removeIf(t -> t.getId().equals(id));
    }

    public static List<Transaction> getAllTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public static boolean isEmpty() {
        return transactions.isEmpty();
    }

    public static Transaction getTransactionById(UUID id) {
        return transactions.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static double calculateBalance() {
        return transactions.stream()
                .mapToDouble(Transaction::getSignedAmount)
                .sum();
    }

    public static List<Transaction> getTransactionsByDate(LocalDate date) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getDate().equals(date)) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    public static List<Transaction> getTransactionsByWeek(LocalDate startDate, LocalDate endDate) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            LocalDate tDate = t.getDate();
            if (!tDate.isBefore(startDate) && tDate.isBefore(endDate)) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    public static List<Transaction> getTransactionsByMonth(int year, int month) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            LocalDate tDate = t.getDate();
            if (tDate.getYear() == year && tDate.getMonthValue() == month) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    public static List<Transaction> getTransactionsByYear(int year) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getDate().getYear() == year) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    public static List<Transaction> getTransactionsByType(TransactionType type) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getType() == type) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    public static TransactionSummary calculateSummary(List<Transaction> transactionList) {
        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction t : transactionList) {
            if (t.getType() == TransactionType.INCOME) {
                totalIncome += t.getAmount();
            } else {
                totalExpense += t.getAmount();
            }
        }

        return new TransactionSummary(totalIncome, totalExpense);
    }

    public static class TransactionSummary {
        private final double totalIncome;
        private final double totalExpense;

        public TransactionSummary(double totalIncome, double totalExpense) {
            this.totalIncome = totalIncome;
            this.totalExpense = totalExpense;
        }

        public double getTotalIncome() {
            return totalIncome;
        }

        public double getTotalExpense() {
            return totalExpense;
        }

        public double getNet() {
            return totalIncome - totalExpense;
        }

        @Override
        public String toString() {
            return String.format("Total inkomst: %.2f kr\nTotal utgift: %.2f kr\nNetto: %.2f kr",
                    totalIncome, totalExpense, getNet());
        }
    }
}