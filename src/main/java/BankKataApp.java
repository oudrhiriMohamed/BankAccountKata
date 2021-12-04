import utils.Clock;

public class BankKataApp {

    public static void main(String[] args){

        //Real Application
        Clock clock = new Clock();
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        Console console = new Console();
        StatementPrinter statementPrinter = new StatementPrinter(console);
        AccountManager account = new AccountManager(transactionRepository, statementPrinter);



        account.deposit(1000);
        account.withdrawal(400);
        account.deposit(2000);
        account.printStatement();
    }
}
