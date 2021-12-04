import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {


    public static final String HEADER = "OPERATION | DATE | AMOUNT | BALANCE";
    private Console console;

    public StatementPrinter(Console console) {

        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        console.printLine(HEADER);

        printStatementLines(transactions);

    }

    private void printStatementLines(List<Transaction> transactions) {
        AtomicInteger runningBalance = new AtomicInteger(0);

        transactions.stream()
                .map(transaction -> statementLine(transaction, runningBalance))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(console::printLine);
    }

    /**
     * Build one statement line according to one transaction
     *
     * @param transaction
     * @param runningBalance
     * @return
     */
    private String statementLine(Transaction transaction, AtomicInteger runningBalance) {

        return transaction.getTypeOfOperation()
                + " | "
                + transaction.getDate()
                + " | "
                + transaction.getAmount()
                + " | "
                + runningBalance.addAndGet(transaction.getAmount());
    }
}
