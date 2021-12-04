import utils.Clock;
import utils.TypeOfOperation;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class TransactionRepository {

    private Clock clock;
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public TransactionRepository(Clock clock) {

        this.clock = clock;
    }

    public void addDeposit(int amount) {
        Transaction depositTransaction = new Transaction(String.valueOf(TypeOfOperation.DEPOSIT), clock.toDayDateAsStringFormat(), amount);
        transactions.add(depositTransaction);
    }

    public void addWithdrawal(int amount) {
        Transaction withdrawTransaction = new Transaction(String.valueOf(TypeOfOperation.WITHDRAW), clock.toDayDateAsStringFormat(), -amount);
        transactions.add(withdrawTransaction);
    }

    public List<Transaction> allTransactions() {
        return unmodifiableList(transactions);
    }
}
