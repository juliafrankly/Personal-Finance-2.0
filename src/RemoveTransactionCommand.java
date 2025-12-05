public class RemoveTransactionCommand implements Command {
    private final TransactionUI transactionUI;

    public RemoveTransactionCommand(TransactionUI transactionUI) {
        this.transactionUI = transactionUI;
    }

    @Override
    public void execute() {
        transactionUI.removeTransaction();
    }
}