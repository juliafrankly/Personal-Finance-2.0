import java.util.Scanner;

class MenuHandler {
    private final Scanner scanner;

    public MenuHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public void handleAddTransaction() {
        TransactionUI.addTransaction();
    }

    public void handleRemoveTransaction() {
        TransactionUI.removeTransaction();
    }

    public void handleDisplayAllTransactions() {
        TransactionUI.displayAllTransactions();
    }

    public void handleViewByType() {
        TransactionUI.viewByType();
    }

    public void handleDisplayBalance() {
        TransactionUI.displayBalance();
    }

    public void handleViewByDay() {
        TransactionUI.viewByDay();
    }

    public void handleViewByWeek() {
        TransactionUI.viewByWeek();
    }

    public void handleViewByMonth() {
        TransactionUI.viewByMonth();
    }

    public void handleViewByYear() {
        TransactionUI.viewByYear();
    }
}