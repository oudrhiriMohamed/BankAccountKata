import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountManagerTest {


    @Mock
    TransactionRepository transactionRepository;
    @Mock
    StatementPrinter statementPrinter;
    private AccountManager account;

    @Before
    public void initialize() {
        account = new AccountManager(transactionRepository, statementPrinter);
    }


    @Test
    public void should_store_deposit_transaction() {
        account.deposit(100);
        verify(transactionRepository).addDeposit(100);
    }

    @Test
    public void should_not_store_deposit_transaction_when_amount_invalid(){
        account.deposit(0);
        verify(transactionRepository, never()).addDeposit(0);
    }
    @Test
    public void should_store_withdraw_transaction() {
        account.withdrawal(100);
        verify(transactionRepository).addWithdrawal(100);
    }
    @Test
    public void should_not_store_withdraw_transaction_when_amount_invalid(){
        account.withdrawal(0);
        verify(transactionRepository, never()).addWithdrawal(0);
    }
    @Test
    public void print_a_statement() {
        List<Transaction> transactions = Arrays.asList(new Transaction("03/12/2021", 100));
        given(transactionRepository.allTransactions()).willReturn(transactions);

        account.printStatement();

        verify(statementPrinter).print(transactions);
    }
}
