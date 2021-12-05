import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
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
        AtomicReference<BigDecimal> runningBalance = new AtomicReference<>(new BigDecimal(0));
        transactions.stream()
                .map(transaction -> statementLine(transaction, runningBalance))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(console::printLine);
    }

    /**
     * Build one statement line according to one transaction
     * print amount and balance with precision 2
     *
     * @param transaction
     * @param runningBalance
     * @return
     */
    private String statementLine(Transaction transaction, AtomicReference<BigDecimal> runningBalance) {
        BigDecimal amount = new BigDecimal(transaction.getAmount());
        return transaction.getTypeOfOperation()
                + " | "
                + transaction.getDate()
                + " | "
                + amount.setScale(2, BigDecimal.ROUND_CEILING)
                + " | "
                + runningBalance.accumulateAndGet(amount, (f, b) -> f.add(b)).setScale(2, BigDecimal.ROUND_CEILING);
    }
}
