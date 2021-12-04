import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import utils.Clock;
import utils.TypeOfOperation;

import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryTest {

    private static final String TODAY = "03/12/2021";
    private TransactionRepository transactionRepository;

    @Mock
    Clock clock;

    @Before
    public void initialize() {
        given(clock.toDayDateAsStringFormat()).willReturn(TODAY);
        transactionRepository = new TransactionRepository(clock);
    }

    @Test
    public void should_add_a_deposit_transaction() {
        transactionRepository.addDeposit(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        Assert.assertEquals(transactions.size(), 1);
        Assert.assertEquals(transactions.get(0), transactionDeposit(TODAY, 100));
    }

    @Test
    public void should_add_a_withdrawal_transaction() {
        transactionRepository.addWithdrawal(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        Assert.assertEquals(transactions.size(), 1);
        Assert.assertEquals(transactions.get(0), transactionWithdraw(TODAY, -100));
    }

    private Transaction transactionDeposit(String date, int amount) {
        return new Transaction(String.valueOf(TypeOfOperation.DEPOSIT), date, amount);
    }
    private Transaction transactionWithdraw(String date, int amount) {
        return new Transaction(String.valueOf(TypeOfOperation.WITHDRAW), date, amount);
    }


}
