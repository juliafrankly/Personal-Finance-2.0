public class ViewByDayCommand implements Command {
    private final TransactionUI transactionUI;

    public ViewByDayCommand(TransactionUI transactionUI) {
        this.transactionUI = transactionUI;
    }

    @Override
    public void execute() {
        transactionUI.viewByDay();
    }
}