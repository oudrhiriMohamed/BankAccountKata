public class AccountManager {


    private TransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;

    public AccountManager(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(int amount) {
        if (amount != 0) {
            transactionRepository.addDeposit(amount);
        }
    }

    public void withdrawal(int amount) {
        if (amount != 0) {
            transactionRepository.addWithdrawal(amount);
        }
    }

    public void printStatement() {
        statementPrinter.print(transactionRepository.allTransactions());

    }
}
