import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import utils.TypeOfOperation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterTest {

    private static final List<Transaction> NO_TRANSACTIONS = Collections.EMPTY_LIST;
    @Mock
    Console console;
    private StatementPrinter statementPrinter;

    @Before
    public void initialize() {

        statementPrinter = new StatementPrinter(console);
    }

    @Test
    public void should_always_print_the_header() {

        statementPrinter.print(NO_TRANSACTIONS);

        verify(console).printLine("OPERATION | DATE | AMOUNT | BALANCE");
    }

    @Test
    public void should_print_statements_in_reverse_chronological_order() {

        List<Transaction> transactions = Arrays.asList(
                deposit("10/10/2020", 1000),
                withdrawal("15/10/2020", 100),
                deposit("20/10/2020", 500)
        );
        statementPrinter.print(transactions);

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("OPERATION | DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("DEPOSIT | 20/10/2020 | 500 | 1400");
        inOrder.verify(console).printLine("WITHDRAW | 15/10/2020 | -100 | 900");
        inOrder.verify(console).printLine("DEPOSIT | 10/10/2020 | 1000 | 1000");
    }


    private Transaction deposit(String date, int amount) {
        return new Transaction(String.valueOf(TypeOfOperation.DEPOSIT) , date, amount);
    }

    private Transaction withdrawal(String date, int amount) {
        return new Transaction(String.valueOf(TypeOfOperation.WITHDRAW), date, -amount);
    }
}
